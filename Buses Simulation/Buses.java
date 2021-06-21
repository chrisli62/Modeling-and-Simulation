// Name : Christopher Li
// Bus Simulation

import java.util.*;
import java.text.*;
import java.lang.Math;


class Main {

  public static double exponential(double m) {
    return (-m * Math.log(Math.random()));
  }

  public static double getArrival() {
    // -6 * ln(r)
    float lambda = 6.0f;
    return exponential(lambda);
  }

  public static double getService() {
    // -5 * ln(r)
    float mu = 5.0f;
    return exponential(mu);
  }

  public static double travel(){
    Random rand = new Random();
    int noTraffic = 15;
    double traffic = 20+(40-20) * rand.nextDouble();
    // Coin fiip 50/50 chance of traffic or no traffic
    if(Math.random() < 0.5){
      return noTraffic;
    }
    else {
      return traffic;
    }
  }

  // Random stop gets a spawn of a passenger
  public static int spawn(){
    Random rand = new Random();
    int spawn = rand.nextInt(3);
    return spawn;
  }

  public static Singleq runSim() {
    Singleq sum = new Singleq();
    sum.initVariables();
    double arrivalTime;
    double serviceTime;
    double departureTime = 0;
    double delay;
    int delayCounter = 0;
    double wait;
    int customer = 1;
    int busyCounter = 1;
    sum.complete = 1;
    int departureCounter = 0;
    int stop = 0;
    int busSize = 20;

    // Track the additions of the service times and the departures
    sum.service += getService();
    departureTime += sum.service;

    // Simulate for 100000 passenger departures from the bus
    while(departureCounter < 100001) {

      // Loop bus back to stop 1 if reached stop 4
      if(stop == 4){
        stop = 0;
      }

      // Add travel time to the sum
      sum.transit += travel();
      stop++;

      arrivalTime = getArrival();
      sum.arrival += arrivalTime;
      // If bus is full add to wait/delay
      if (busSize == 20 && (sum.arrival < departureTime)) {
        delay = departureTime - sum.arrival;
        delayCounter++;
      } else {
        delay = 0.0;
      }
      if(busSize != 0){
        if(stop == spawn()-1){
          sum.depart += 1;
          busSize--;
        }
      }
      serviceTime = getService();
      sum.load += 2;

      // Split if (customer < 21) to preserve output columns
      if (customer < 10) {
      System.out.println("Arriving Customer " + customer + "             : " + arrivalTime + "seconds");
      }
      if (customer < 10) {
      System.out.println("Serving Customer " + customer + "              : " + serviceTime + "seconds");
      }
      if (customer > 9 && customer < 21) {
      System.out.println("Arriving Customer " + customer + "            : " + arrivalTime + "seconds");
      }
      if (customer > 9 && customer < 21) {
      System.out.println("Serving Customer " + customer + "             : " + serviceTime + "seconds");
      }

      // debug for customer 100 to 120
      if(customer >= 100 && customer < 121){
        System.out.println("Customer " + customer);
        System.out.println("Average Arrival Time            : " + sum.avgArrival() + " seconds");
        System.out.println("Average Service Time            : " + sum.avgServicetime() + " seconds");
        System.out.println("Average Delay                   : " + sum.avgDelay() + " seconds");
        System.out.println("Average Wait Time               : " + sum.avgWait() + " seconds");
      }

      wait = delay + serviceTime + 1;
      departureTime = sum.arrival + wait;
      sum.service += serviceTime;
      sum.delay += delay;
      sum.wait += wait;
      sum.complete = departureCounter;
      if (busSize == 20 || departureCounter == 100000){
        System.out.println("Busy Period " + delayCounter + "                   : " + departureTime + " seconds");
        busyCounter++;
      }
      customer++;
      departureCounter++;
    }
    System.out.println("Number of Delays                : "  + delayCounter);
    return sum;
  }

  public static void printResults(Singleq sum) {
    DecimalFormat f = new DecimalFormat("###0.00");

    System.out.println("Services                        : " + sum.complete + " jobs");
    System.out.println("Average Arrival Time            : " + f.format(sum.avgArrival()) + " seconds");
    System.out.println("Average Service Time            : " + f.format(sum.avgServicetime()) + " seconds");
    System.out.println("Average Delay                   : " + sum.avgDelay() + " seconds");
    System.out.println("Average Wait Time               : " + f.format(sum.avgWait()) + " seconds");
  }

  public static void main(String[] args) {
    System.out.println("Name                            : Christopher Li");
    Singleq sum = runSim();
    printResults(sum);
  }
}

class Singleq {
  // Holds values of the statistics
  double arrival;
  double service;
  double delay;
  double wait;
  double busy;
  double transit;
  int load;
  int depart;
  int complete;

  // Initializing the variables
  void initVariables() {
    arrival = 0.0;
    service = 0.0;
    delay = 0.0;
    wait = 0.0;
    complete = 0;
    busy = 0.0;
    transit = 0.0;
    load = 0;
    depart = 0;
  }

  // Returns average times
  double avgArrival() {
    return arrival / complete;
  }

  double avgServicetime() {
    return service / complete;
  }

  double avgDelay() {
    return delay / complete;
  }

  double avgWait() {
    return wait / complete;
  }

  double getBusy() {
    return busy;
  }
  
  double getTransit(){
    return transit;
  }
}