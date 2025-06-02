public class MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        MaxProfit solution = new MaxProfit();

        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("result: " + solution.maxProfit(prices1));
       
    }
}