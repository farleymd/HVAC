package com.clara;

import java.util.LinkedList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HVAC {


    /** Program to manage service calls to furnaces and AC units
     */

    private static LinkedList<ServiceCall> todayServiceCalls;
    private static LinkedList<ServiceCall> resolvedServiceCalls;


    public static void main(String[] args) {


        //Use todayServiceCalls as a Queue
        //So, add new calls to the end with add()
        //Remove a resolved call from the front of the queue with remove()
        //Check on the current call - the one at the head of the queue - with peek()

        //This will enable us to deal with calls in the order in which they were received

        todayServiceCalls = new LinkedList<ServiceCall>();

        // This will be used to store a list of resolved service calls.

        resolvedServiceCalls = new LinkedList<ServiceCall>();


        // ServiceCall currentCall;  //The one we are working on. Will be required to resolve this before moving onto the next


        while (true) {

            System.out.println("1. Add service call to queue");
            System.out.println("2. Resolve current call");
            System.out.println("3. Print current call");
            System.out.println("4. Print all outstanding calls");
            System.out.println("5. Print all resolved calls ");
            System.out.println("6. Quit");

            int choice = getPositiveIntInput();

            switch (choice) {

                case 1: {
                    addServiceCall();
                }
                case 2: {
                    //Resolve service call

                    //Remove from head of the queue

                    ServiceCall resolvedCall = todayServiceCalls.remove();

                    System.out.println("Enter resolution for " + resolvedCall);

                    String resolution = getStringInput();

                    double fee = getPositiveDoubleInput();

                    resolvedCall.setResolution(resolution);
                    resolvedCall.setFee(fee);
                    resolvedCall.setResolvedDate(new Date());  //default resolved date is now

                    //Add this call to the list of resolved calls
                    resolvedServiceCalls.add(resolvedCall);

                }
                case 3: {
                    //Print next service call - it is the one at the top of the queue
                    System.out.println(todayServiceCalls.peek());
                }
                //Print all service calls
                case 4: {
                    System.out.println("Today's service calls are: ");
                    for (ServiceCall c : todayServiceCalls) {
                        System.out.println(c);
                    }
                }

                case 5: {
                    System.out.println("List of resolved calls: ");
                    for (ServiceCall c : resolvedServiceCalls) {
                        System.out.println(c);
                    }
                }

                case 6: {
                    break;
                }

                default: {
                    System.out.println("Enter a valid number");
                }
            }


        }
    }


    private static void printAllResolvedCalls() {
        //TODO
    }

    private static void resolveServiceCall(ServiceCall call, String resolution, double fee) {
        //TODO
        //Store resolution string, date, feein this call, remove from queue, add to list of resolved calls


    }

    private static void printAllCalls() {
        //TODO

    }



    private static void addServiceCall() {

        //TODO

        //What type of thing needs servicing?

        System.out.println("1. Add service call for furnace");
        System.out.println("2. Add service call for AC unit");
        System.out.println("3. Quit");


        int choice = getPositiveIntInput();

        switch (choice) {

            case 1: {

                System.out.println("Enter address of furnace");
                String address = getStringInput();
                System.out.println("Enter description of problem");
                String problem = getStringInput();
                int type = 0;
                while (type < 1 || type > 3) {
                    System.out.println("Type of furnace: " +
                            Furnace.FurnaceTypes.FORCED_AIR + "  = forced air \n"
                            + Furnace.FurnaceTypes.BOILER + " = boiler/radiators\n"
                            + Furnace.FurnaceTypes.OCTOPUS + " = old 'octopus' furnace");
                    type = getPositiveIntInput();
                }

                Furnace f = new Furnace(address, problem, new Date(), type);

                todayServiceCalls.add(f);

            }
            case 2: {
                //TODO

                System.out.println("Enter address of furnace");
                String address = getStringInput();
                System.out.println("Enter description of problem");
                String problem = getStringInput();
                System.out.println("Enter model of AC unit");
                String model = getStringInput();

                CentralAC ac = new CentralAC(address, problem, new Date(), model);
                todayServiceCalls.add(ac);

            }
            case 3: {
                return;
            }

        }

    }

    private static int getPositiveIntInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int selection = scanner.nextInt();
                if (selection >= 0) {
                    scanner.close();
                    return selection;
                } else {
                    System.out.println("Please enter a positive integer number");
                    continue;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please type a positive integer number");
                String dumpRestOfInput = scanner.next();  //Force scanner to throw away the last (invalid) input
            }
        }
    }

    private static double getPositiveDoubleInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                double selection = scanner.nextDouble();
                if (selection >= 0) {
                    scanner.close();
                    return selection;
                } else {
                    System.out.println("Please enter a positive number number");
                    continue;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please type a positive number");
                String dumpRestOfInput = scanner.next();  //Force scanner to throw away the last (invalid) input
            }
        }

    }

    private static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        String entry = scanner.next();
        return entry;

    }
}

