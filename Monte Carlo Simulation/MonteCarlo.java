// Christopher Li
// Monte Carlo Simulation V2
import java.util.*;

class MonteCarloV2 {
  public static void main(String[] args) {
    System.out.println("Name: Christopher Li");
    System.out.println("Enter the number of rolls you would like to perform: ");
    Scanner scan = new Scanner(System.in);
    int iter = scan.nextInt();
    scan.close();
    // from 0 to 5
    double max = 5;
    double min = 0;
    double diff = max-min;
    Random r = new Random();
    double sum = 0;
    double temp;
    double tempy;
    int counter = 0;
    // integral of 3x^4-10x
    for(int i=0; i<iter; i++){
      double x = diff * r.nextDouble();
      // x^4
      temp = Math.pow(x, 4);
      // 3x^4
      temp = temp * 3;
      // 3x^4-10x
      temp -= (10 * x);
      double tempx = temp;

      double y = 1825 * r.nextDouble();

      // f(x) < y
      if(temp > 0){
        if(temp < y){
          // add to running total
          sum += tempx;
          counter++;
        }
      }
      
    }
    
    System.out.println(diff*sum/counter);
    System.out.println("NUMBER OF HITS: " + counter);
  }
}