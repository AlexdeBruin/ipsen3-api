package io.dropwizard;

public class View {

    public static class OnlyAdmins extends OnlyEmployees {}

    public static class OnlyEmployees extends Public {}

    public static class Public {}

}
