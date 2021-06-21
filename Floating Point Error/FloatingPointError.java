//Christopher Li

class FloatingPointError {
  public static void main(String[] args) {
    double dsum = 0.0;
    float fsum = 0;
    for(int i=0; i<1000000; i++){
      dsum += 0.2;
      fsum += 0.2;
    }
    System.out.println("Christopher Li")
    System.out.println("Double sum: " + dsum);
    System.out.println("Float sum: " + fsum);
  }
}