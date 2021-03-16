package 堆排序;

public class test {
    public static void main(String[] args) {
        int[] arr = {16, 7, 3, 20, 17, 8, 15, 102, 56, 54};
//        test05 test = new test05();
//        test.sort(arr);
        for (int i : arr) {
            System.out.print(i + "\t");
        }

    }

    public void sort(int[] arr) {
        //这个for循环length依然是原来的值，因为没有找到最大值放入顶部然后再和最后面的元素交换再弹出（即length-1）
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子节点从下至上，从右至左调整结构
            adjust(arr, i, arr.length);
        }
        //调整堆结构+交换堆顶元素和末尾元素  【下沉（len-1）次即可】
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);
            //下沉
            adjust(arr, 0, j);
        }
    }

    //对于单个三插树
    public void adjust(int[] arr, int index, int len) {
        int temp = arr[index];            //总是
        for (int k = index * 2 + 1; k < len; k = k * 2 + 1) {
            if (k + 1 < len && arr[k] < arr[k + 1]) {
                k++;                    //对于父节点和左右子节点间的比较，比较左节点的同时只需要比较左右节点之间的比较即可
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (temp < arr[k]) {
                arr[index] = arr[k];
                index = k;
            } else {
                break;
            }
        }
        arr[index] = temp;
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
