package com.clara;

import java.util.Date;

/**
 * Represents a generic service call to a furnace, boiler, AC unit...
 */
public class ServiceCall {

    protected String serviceAddress;
    protected String problemDescription;
    protected Date reportedDate;
    protected Date resolvedDate;

    protected final static double UNRESOLVED = -1  ;  //Flag to indicate this hasn't been resolved so we don't have a fee yet
    public Date getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    protected String resolution;
    protected double fee;

    public ServiceCall(String serviceAddress, String problemDescription, Date date) {
        this.serviceAddress = serviceAddress;
        this.problemDescription = problemDescription;
        this.reportedDate = date;
        this.fee = UNRESOLVED;
    }



    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }


    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }


}
