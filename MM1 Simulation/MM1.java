// Name : Christopher Li
// M/M/1 Simulation

import java.util.*;
import java.text.*;
import java.lang.Math;


class MM1 {

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

  public static Singleq runSim() {
    Singleq sum = new Singleq();
    sum.initVariables();
    double arrivalTime;
    double serviceTime;
    double departureTime = 0;
    double delay;
    int delayCounter = 0;
    double wait;
    int firstTwenty = 1;
    int busyCounter = 1;
    sum.complete = 1;

    // Track the additions of the service times and the departures
    sum.service += getService();
    departureTime += sum.service;

    // Simulate for 1000 service completions
    for (int i = 1; i <= 1000; i++) {
      arrivalTime = getArrival();
      sum.arrival += arrivalTime;
      if (sum.arrival < departureTime) {
        delay = departureTime - sum.arrival;
        delayCounter++;
      } else {
        delay = 0.0;
      }
      serviceTime = getService();
      // Split if (firstTwenty < 21) to preserve output columns
      if (firstTwenty < 10) {
      System.out.println("Arriving Customer " + firstTwenty + "             : " + arrivalTime + "seconds");
      }
      if (firstTwenty < 10) {
      System.out.println("Serving Customer " + firstTwenty + "              : " + serviceTime + "seconds");
      }
      if (firstTwenty > 9 && firstTwenty < 21) {
      System.out.println("Arriving Customer " + firstTwenty + "            : " + arrivalTime + "seconds");
      }
      if (firstTwenty > 9 && firstTwenty < 21) {
      System.out.println("Serving Customer " + firstTwenty + "             : " + serviceTime + "seconds");
      }
      // debug for every 100 customers
      if(i%100 == 0){
        System.out.println("Customer " + i);
        System.out.println("Average Arrival Time            : " + sum.avgArrival() + " seconds");
        System.out.println("Average Service Time            : " + sum.avgServicetime() + " seconds");
        System.out.println("Average Delay                   : " + sum.avgDelay() + " seconds");
        System.out.println("Average Wait Time               : " + sum.avgWait() + " seconds");
      }
      wait = delay + serviceTime;
      departureTime = sum.arrival + wait;
      sum.service += serviceTime;
      sum.delay += delay;
      sum.wait += wait;
      sum.complete = i;
      if (wait == 0 || i == 1000){
        System.out.println("Busy Period " + busyCounter + "                   : " + departureTime + " seconds");
        busyCounter++;
      }
      firstTwenty++;
    }
    System.out.println("Number of Delays                : "  + delayCounter);
    return sum;
  }

  public static void printResults(Singleq sum) {
    DecimalFormat f = new DecimalFormat("###0.00");

    System.out.println("Services                        : " + sum.complete + " jobs");
    System.out.println("Average Arrival Time            : " + f.format(sum.avgArrival()) + " seconds");
    System.out.println("Average Service Time            : " + f.format(sum.avgServicetime()) + " seconds");
    System.out.println("Average Delay                   : " + f.format(sum.avgDelay()) + " seconds");
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
  int complete;

  // Initializing the variables
  void initVariables() {
    arrival = 0.0;
    service = 0.0;
    delay = 0.0;
    wait = 0.0;
    complete = 0;
    busy = 0.0;
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
}