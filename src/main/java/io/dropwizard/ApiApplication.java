package io.dropwizard;



import com.google.inject.Module;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.models.Employee;
import io.dropwizard.persistence.ConnectionPool;
import io.dropwizard.persistence.DAO.*;
import io.dropwizard.resources.*;
import io.dropwizard.services.*;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;


public class ApiApplication extends Application<ApiConfiguration> {
    private String apiName;
    private ConfiguredBundle assetsBundle;
    private GuiceBundle guiceBundle;

    public static void main(final String[] args) throws Exception {
        new ApiApplication().run(args);
    }

    @Override
    public String getName() {
        return apiName;
    }


    @Override
    public void initialize(final Bootstrap<ApiConfiguration> bootstrap) {
        assetsBundle = (ConfiguredBundle) new ConfiguredAssetsBundle("/assets/", "/", "index.html");
        guiceBundle = createGuiceBundle(ApiConfiguration.class, new ApiGuiceModule());
        bootstrap.addBundle(assetsBundle);
        bootstrap.addBundle(guiceBundle);
    }

    private GuiceBundle createGuiceBundle(Class<ApiConfiguration> configurationClass, Module module) {
        GuiceBundle.Builder guiceBuilder = GuiceBundle.<ApiConfiguration>newBuilder()
                .addModule(module)
                .enableAutoConfig(new String[] { "nl.hsleiden" })
                .setConfigClass(configurationClass);

        return guiceBuilder.build();
    }

    @Override
    public void run(final ApiConfiguration configuration,
                    final Environment environment) {
        apiName = configuration.getApiName();
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin, Authorization");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
            // EnumSet.allOf(DispatcherType.class), true, "/*");


        ConnectionPool connectionPool = new ConnectionPool("org.mariadb.jdbc.Driver", "jdbc:mariadb://localhost:3306:/UrenregistratieDatabase", "root", "ipsen123");

        /**
         * Initialise all the DAO objects.
         */
        CustomerDAO customerDAO = new CustomerDAO(connectionPool);
        EmployeeDAO employeeDAO = new EmployeeDAO(connectionPool);
        ProjectDAO projectDAO = new ProjectDAO(connectionPool);
        SubjectDAO subjectDAO = new SubjectDAO(connectionPool);
        RegisteredHourDAO registeredHourDAO = new RegisteredHourDAO(connectionPool);

        /**
         * Initialise all the Service objects.
         */

        final AuthService authService = new AuthService(employeeDAO);
        final CustomerService customerService = new CustomerService(customerDAO, projectDAO, subjectDAO);
        final EmployeeService employeeService = new EmployeeService(employeeDAO);
        final ProjectService projectService = new ProjectService(projectDAO, customerDAO, subjectDAO);
        final SecurityFilterService security = new SecurityFilterService(employeeDAO);
        final SubjectService subjectService = new SubjectService(subjectDAO, projectDAO, customerDAO);
        final RegisteredHourService registeredHourService = new RegisteredHourService(registeredHourDAO, customerDAO, projectDAO, subjectDAO);


        /**
         * Initialise all the Resource objects.
         */
        final EmployeeResource employeeResource = new EmployeeResource(employeeService);
        final HourResource hourResource = new HourResource(registeredHourService);
        final LogInResource logInResource = new LogInResource();
        final CustomerResource customerResource = new CustomerResource(customerService);
        final ProjectResource projectResource = new ProjectResource(projectService);
        final SubjectResource subjectResource = new SubjectResource(subjectService);

        environment.jersey().register(employeeResource);
        environment.jersey().register(hourResource);
        environment.jersey().register(security);
        environment.jersey().register(logInResource);
        environment.jersey().register(customerResource);
        environment.jersey().register(projectResource);
        environment.jersey().register(subjectResource);

        setupAuthentication(environment);

    }
    private void setupAuthentication(Environment environment)
    {
        AuthService authenticationService = guiceBundle.getInjector().getInstance(AuthService.class);
        ApiUnauthorizedHandler unauthorizedHandler = guiceBundle.getInjector().getInstance(ApiUnauthorizedHandler.class);

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<Employee>()
                        .setAuthenticator(authenticationService)
                        .setAuthorizer(authenticationService)
                        .setRealm("SUPER SECRET STUFF")
                        .setUnauthorizedHandler(unauthorizedHandler)
                        .buildAuthFilter())
        );

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Employee.class));
    }

}
