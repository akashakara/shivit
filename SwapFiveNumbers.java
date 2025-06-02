import java.util.Scanner;

public class SwapFiveNumbers {
    public void swapNumbers(int a, int b, int c, int d, int e) {
        System.out.println("before : a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e);
        
        a = a + e; 
        e = a - e; 
        a = a - e;
        
        b = b + d;
        d = b - d; 
        b = b - d; 
        
        a = a + c; 
        c = a - c; 
        a = a - c; 
        
        System.out.println("after : a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e);
    }

    public static void main(String[] args) {
        SwapFiveNumbers solution = new SwapFiveNumbers();
        
        int a = 21, b = 25, c = 30, d = 40, e = 45;
        solution.swapNumbers(a, b, c, d, e);
      
    }
}