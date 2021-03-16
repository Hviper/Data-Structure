package 二叉堆.src;

import java.util.Arrays;

public class text {
    public static void main(String[] args) {
        text text = new text();
//        int[] arr={80, 8, 85, 35, 79, 409, 7, 46, 55, 5, 3, 64, 559, 69};
        int[] arr={7,15,68,1,25,3,2,14,48,9,84,52,67,43,100};
        text.build(arr);
    }
    public void sort(int size,int[] arr){
        int[] elements = new int[size];
        for (int i = 0; i < arr.length; i++) {
            if(i<=size){
                add(i,arr[i],elements);
            }
            
        }
    }

    public void build(int[] arr){
        int size = arr.length;
        int half = (size>>1)-1;
        for (int i = half; i >= 0; i--) {
            down(i,arr);
        }
        System.out.println(Arrays.toString(arr));
    }
    private void add(int index,int element,int[] arr){
        arr[index] = element;
        up(index,arr);
    }
    private void up(int index,int[] arr){
        int current = arr[index];
        while (index > 0){
            int parentIndex = (index-1)>>1;
            int parent = arr[parentIndex];
            if(compareTo(parent,current))break;
            arr[index] = parent;
            index = parentIndex;
        }
        arr[index] = current;
    }

    public void down(int index,int[] arr){
        int size = arr.length;
        int half = size>>1;
        int current = arr[index];
        while (index < half){       //先执行再判断的原则，或者说是先进入while执行语句再判断
            int left = (index<<1)+1;
            int right = left+1;
            int child = arr[left];
            if(right < size && compareTo(arr[right],child)){
                left = right;
                child = arr[right];
            }
            if(compareTo(current,child)){
                break;
            }
            arr[index] = child;
            index = left;
        }
        arr[index] = current;
    }

    public boolean compareTo(int a,int b){
        return a < b;
    }
}
