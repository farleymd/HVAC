package com.clara;


import java.util.Date;

public class Furnace extends ServiceCall {

    private int furnaceType;

    public Furnace(String serviceAddress, String problemDescription, Date date, int furnaceType) {
        super(serviceAddress, problemDescription, date);
        //TODO Error checking - is this a valid furnace type?
        this.furnaceType = furnaceType;
    }


    protected class FurnaceTypes {
        final static int FORCED_AIR = 1;   //forced air heating
        final static int BOILER = 2;    //For radiators
        final static int OCTOPUS = 3;    //Old furnaces


    }

}