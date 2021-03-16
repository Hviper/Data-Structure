package 栈;

import java.util.Arrays;
import java.util.Stack;

public class test {
    public static void main(String[] args) {
        test test = new test();
        System.out.println(9/10);
        System.out.println("---------------");
        test.reserve_ints(-123456);
        System.out.println("\n-------------------------");
        System.out.println(test.reserve(123456));
        System.out.println();
        System.out.println(test.reverse(123456789));
    }

    public int[] twoSum(int[] nums, int target) {
        boolean flag=false;
        int i=0;
        int j;
        int[] arr=new int[2];
        for(i=0;i<nums.length;i++){
            for(j=1;j<nums.length;j++){
                if(nums[i]+nums[j]==target&&i!=j){
                    flag=true;
                    arr[1]=j;
                    arr[0]=i;
                    break;
                }
            }
            if(flag){
                break;
            }

        }
        return arr;
    }
    public void reserve_ints(int x){
        char flag=' ';
        Stack<Character> stack = new Stack<>();
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if(aChar=='-'){
                flag=aChar;
            }
            else {stack.push(aChar);}

        }
        stack.push(flag);
        while (!stack.empty()){
            System.out.print(stack.pop());
        }
    }

    public int reserve(int x){
        int result=0;
        while (x!=0){
            int a=x%10;

            x=x/10;

            result=result*10+a;
        }
        return result;
    }

    public int reverse(int x) {
        int res = 0;
        while(x!=0) {
            //每次取末尾数字
            int tmp = x%10;
            //判断是否 大于 最大32位整数
            if (res>214748364 || (res==214748364 && tmp>7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }


}
