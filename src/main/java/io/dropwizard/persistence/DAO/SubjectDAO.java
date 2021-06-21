package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Subject;
import io.dropwizard.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class SubjectDAO extends DAO{


    public SubjectDAO(ConnectionPool pool) {
        super(pool);
    }

    public Subject getSubjectByPIDSName(int projectID, String subjectName) {
        Connection con = pool.checkout();
        Subject subject = null;
        try {
            PreparedStatement getSubject = con.prepareStatement("SELECT onderwerp_ID, onderwerp_naam, project_ID FROM onderwerp WHERE project_ID = (?) and onderwerp_naam = (?) ");
            getSubject.setInt(1, projectID);
            getSubject.setString(2, subjectName);
            ResultSet results = getSubject.executeQuery();
            if (results.next()) {
                subject = new Subject(results.getInt("onderwerp_ID"), results.getString("onderwerp_naam"), results.getInt("project_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return subject;
    }


    public void addSubject(String onderwerpnaam, int projectID) {
        Connection con = pool.checkout();
        try {
            PreparedStatement addSubject = con.prepareStatement("INSERT INTO onderwerp (onderwerp_naam, project_ID) VALUE (?,?)");
            addSubject.setString(1, onderwerpnaam);
            addSubject.setInt(2,projectID);
            addSubject.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
    }

    public List<Subject> getSubjects(int projectID) {
        Connection con = pool.checkout();
        List<Subject> subjects = null;
        try {
            PreparedStatement getSubjects = con.prepareStatement("SELECT onderwerp_ID, onderwerp_naam, project_ID FROM onderwerp WHERE project_ID = (?)");
            getSubjects.setInt(1,projectID);
            ResultSet results = getSubjects.executeQuery();
            subjects = new ArrayList<>();
            if (results != null) {
                while (results.next()) {
                    subjects.add(new Subject(results.getInt("onderwerp_ID"), results.getString("onderwerp_naam"), results.getInt("project_ID")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return subjects;

    }
}
