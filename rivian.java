import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("Now Starting The Car Loan Simulation...");
    CarLoanAnalyzerApp app = new CarLoanAnalyzerApp();
    app.run();
  }
}

class CarLoanAnalyzerApp {
  public void run() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter The Number of Months You Would Like Your Term:");
    int months = sc.nextInt();

    System.out.println("Enter the car MSRP:");
    double msrp = sc.nextDouble();
    double msrpDown = msrp * .34; //###REDUCE THIS NUMBER TO .34 WHEN TESTING RIVIAN###

    System.out.println("Enter the Monthly Payment with $0 Down:");
    double monthlyPaymentZeroDown = sc.nextDouble();

    analyzeCarLoan(msrpDown, months, monthlyPaymentZeroDown);
    sc.close();
  }

  public void analyzeCarLoan(double msrpDown, int months, double monthlyPaymentZeroDown) {
    double cheapest = Double.MAX_VALUE;
    double expensive = Double.MIN_VALUE;
    double downPayment = 0;
    double monthlyPayment = 0;
    double cheapestMonthlyPayment = monthlyPaymentZeroDown;
    double expensiveDown = 0;
    double expensiveMonthlyPayment = 0;

    for (double down = 0; down <= msrpDown; down += 500) {
      double totalCost = down + (months * cheapestMonthlyPayment);

      if (totalCost < cheapest && monthlyPayment >= 0) {
        cheapest = totalCost;
        downPayment = down;
        monthlyPayment = cheapestMonthlyPayment;
      }
      if (totalCost > expensive) {
        expensive = totalCost;
        expensiveDown = down;
        expensiveMonthlyPayment = cheapestMonthlyPayment;
       // System.out.println("#####EXPENSIVE ERROR#######");
      }
      cheapestMonthlyPayment -= 14.75; //###CHANGE THIS NUMBER TO 14.75 WHEN TESTING RIVIAN###
    }
    if (cheapest == Double.MAX_VALUE) {
      System.out.println("There is no option where the monthly car payment is zero");
      
    } else {
      System.out.println("The cheapest option is with a downpayment of $" + downPayment + " and a monthly payment of $"
          + monthlyPayment + "." + "The total cost is $" + cheapest);
      
      System.out.println("The most expensive option is with a downpayment of $" + expensiveDown + " and a monthly payment of $"
          + expensiveMonthlyPayment + "." + "The total cost is $" + expensive);
      
      System.out.println(
          "NOTICE!!! These costs do not include additional fees or taxes usually due on signing. This Program uses Rivian's general lease policies but is applicable for most EV Manufacturers. 7500 Ev Bonus Included. Monthly rates are accurate for calculation but might not be spot on with Manufacturer Website.");
    }
  }
}
