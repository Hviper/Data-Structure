package 堆排序;

public class test_堆排序 {
    public static void main(String[] args) {
        int[] arr={15,8,48,95,62,1,4,84,6};
        test_堆排序 s = new test_堆排序();

        s.Quick_Sort(arr);
        for (int i : arr) {
            System.out.print(i+"\t");
        }
    }


    public void Quick_Sort(int[] arr){
        int len=arr.length;
        for(int i=len/2-1;i>=0;i--){
            adjust(arr,i,len);
        }

        //下沉！！
        for(int l=len-1;l>0;l--){
            swap(arr,0,l);
            adjust(arr,0,l);
        }
    }





    //index:父节点，len数组长度,index为从数组的（len/2-1）为下标开始
    public void adjust(int[] arr, int index, int len){
        int temp=arr[index];
        //为确保每次index指向后都是（index*2+1）为下标的左子节点
        for(int i=index*2+1;i<len-1;i=i*2+1){
            if(i+1<len-1 && arr[i]<arr[i+1]){
                i++;
            }
            if(temp<arr[i]){
                arr[index]=arr[i];
                index=i;
            }
            else break;
        }
        arr[index]=temp;
    }

    public void swap(int[] arr,int a,int b){
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }
}
