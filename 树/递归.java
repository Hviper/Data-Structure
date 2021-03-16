package 树;

import java.util.Arrays;

public class 递归 {
    public static void main(String[] args) {
        teacher t1 = new teacher("kiwi",15);
        teacher t2 = new teacher("Apache",5);
        teacher t3 = new teacher("Tomcat",8);
        teacher t4 = new teacher("Python",11);
        teacher t5 = new teacher("apple",12);
        teacher t6 = new teacher("Linux",4);
        t1.right=t2;
        t1.left=t3;
        t2.right=t4;
        t3.right=t5;
        t4.left=t6;
        t1.shows_back();
        System.out.println("-----------------------");


    }
    public void show_list(int[] arr,int index){
        System.out.println(arr[index]);
        if((2*index+1)<arr.length){
            show_list(arr,2*index+1);
        }
        if((2*index+2)<arr.length){
            show_list(arr,2*index+2);
        }
    }

    //非递归,冲小到大排序
    public void sort_Byr(int[] arr){
        int len=arr.length;         //长度为5，则0，1，2，3，4,主for四下，
        boolean flag=false;
        for (int i = 0; i < len-1; i++) {
            for(int j=0;j<len-i-1;j++){
                if(arr[j]<arr[j+1]){
                    flag=true;
                    //{10,15,2,588,65,150,354,457,1,2};
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if(!flag){break;}
            else {flag=false;}
        }
    }

    //选择排序【无法改进】
    public void sort_change(int[] arr){
        int len=arr.length-1;
        for (int i = 0; i < len; i++) {
            for(int j=i+1;j<len-i;j++){
                if(arr[i]<arr[j]){
                    int temp=arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;
                }
            }
        }
    }

    //快速排序
    public void quick_sort(int[] arr,int left,int right){
        int mid=(left+right)/2;
        while (left<right){      //true类似，因为已经有退出break的语句
            while (arr[left]<arr[mid]){
                left++;
            }
            while (arr[right]>arr[mid]){
                right--;
            }
            //左右找发现1.左边的数不再小于arr[mid]的数了，如果l>r说明已经按照左边全是小于等于mid的值，右边都是大于mid的值了
            if(left>right){
                break;
            }
            //上面找到以后进行交换
            int temp=arr[left];
            arr[left]=arr[right];
            arr[right]=temp;
        }



    }


}


class Deal_teacher{
    private teacher root;
    public void setRoot(teacher root){
        this.root=root;
    }
    public teacher getRoot(){
        return root;
    }

    public void search_copy(teacher root,int id){

    }

}

class teacher{
    public teacher right;
    public teacher left;
    public String date;
    public int id;
    public void shows_back(){
        if(this.left==null){
            return;
        }
        this.left.shows_back();  //左键点--->栈堆建到高
        System.out.println(this);
        if(this.right==null){
            return;
        }
        this.right.shows_back();




    }
    public void shows(){
        System.out.println(this);
        if(this.left!=null){
            this.left.shows();
        }
        if(this.right!=null){
            this.right.shows();
        }
    }
    public teacher(String date,int id){
        this.date=date;
        this.id=id;
    }

    @Override
    public String toString() {
        return "teacher{" +
                "date='" + date + '\'' +
                ", id=" + id +
                '}';
    }
}

