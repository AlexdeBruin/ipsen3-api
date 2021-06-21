package io.dropwizard.persistence.DAO;

import io.dropwizard.persistence.ConnectionPool;

public class DAO {
    protected ConnectionPool pool;

    public DAO(ConnectionPool pool){
        this.pool = pool;
    }
    public void setConnectionPool(ConnectionPool pool){
        this.pool = pool;
    }


}
