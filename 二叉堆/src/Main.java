package 二叉堆.src;

import java.util.Arrays;

public class Main {

    static void test(){
        compareTo<Integer> compareTo = new compareTo<Integer>() {
            @Override
            public boolean compare(Integer e1, Integer e2) {
                int i1 = e1;
                int i2 = e2;
                return (i1 - i2) > 0;
            }
        };
        BinaryHeap<Integer> BinaryHeap = new BinaryHeap<Integer>(compareTo);
        Integer[] arr={86, 8, 85, 35, 79, 409, 7, 46, 55, 5, 3, 64, 559, 69};

        BinaryHeap.build(arr);


    }

    public static void main(String[] args) {

        Main main = new Main();
        int[] arr={86, 8, 85, 35, 79, 409, 7, 46, 55, 5, 3, 64, 559, 69};
        System.out.println("------------------------------------->");
        int[] ints = main.Heap_Sort(arr,arr.length);
        main.sort(ints);

        }




    public void sort(int[] arr){
        int size = arr.length;
        for (int i = size; i > 1; i--) {
            System.out.println("----------------------------------------------");
            swap(0,i-1,arr);           //交换完以后size马上减一
            System.out.println(Arrays.toString(arr));
            down(0,arr,i-1);
            System.out.println(Arrays.toString(arr));
    }


    }
    public void swap(int i,int j,int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int[] Heap_Sort(int[] elements,int size){
//        int size = elements.length;
        int half = (size>>1)-1;
        for (int i = half; i >= 0 ; i--) {
            down(i,elements,size);
        }
        System.out.println(Arrays.toString(elements));
        return elements;
    }
    private void down(int index,int[] arr,int size){
        int current = arr[index];
//        int size = arr.length;
        int half = size>>1;
        while (index < half){
            int left = (index<<1)+1;
            int right = left+1;
            int child = arr[left];
            if(right < size && arr[right]>child){
                left = right;
                child = arr[right];
            }
            if(current > child){break;}
            arr[index] = child;
            index = left;
        }
        arr[index] = current;
    }
    //对于完全二叉树而言：非叶子节点：size >> 1
    public void downs(int index,int[] arr,int size){
        int current = arr[index];
        int half = size >>1;
        while (index < half){
            int left = (index<<1)+1;
            int right = left+1;
            int child = arr[left];
            if(right < size && arr[right]>child){
                left = right;
                child = arr[right];
            }
            if(current > child){break;}
            arr[index] = child;
            index = left;
        }
        arr[index] = current;
    }
    public void up(int index,int[] arr){
        int current = arr[index];
        while (index > 0 ){
            int parentIndex = (index-1)>>1;
            int parent = arr[parentIndex];
            if(parent > current){
                break;
            }
            arr[index] = parent;
            index = parentIndex;
        }
        arr[index] = current;
    }


}
