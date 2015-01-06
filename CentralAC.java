package com.clara;

import java.util.Date;

/**
 * Represents a service call to fix a central AC until
 */
public class CentralAC extends ServiceCall{

    private String model;


    public CentralAC(String serviceAddress, String problemDescription, Date date, String model) {
        super(serviceAddress, problemDescription, date);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
