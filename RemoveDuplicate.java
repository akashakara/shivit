public class RemoveDuplicate {
    public int removeDuplicate(int[] nums) {
        if (nums.length == 0) return 0;
        
        int k = 1; 
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[k - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        RemoveDuplicate solution = new RemoveDuplicate();
        int[] nums1 = {1, 1, 2};
        int k1 = solution.removeDuplicate(nums1);
        System.out.println("result: k = " + k1 + ", nums = " + arrayToString(nums1, k1));

        
    }

    private static String arrayToString(int[] nums, int k) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < k; i++) {
            sb.append(nums[i]);
            if (i < k - 1) sb.append(", ");
        }
        sb.append(", _]");
        return sb.toString();
    }
}