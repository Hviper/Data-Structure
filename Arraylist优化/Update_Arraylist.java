package Arraylist优化;

public class Update_Arraylist {
    //头指针,用于遍历
    private int front;
    private int[] elements;
    private final int DEFINE=10;
    private int size;
    public Update_Arraylist(){
        elements=new int[DEFINE];
    }
    public void Insert(int index,int element){
        if(index<0){return;}
        ensure_Capacity();
        int temp = elements[index];        //先保存当前的元素
        if(index<front){

        }

        size++;
    }

    public void ADD(int element){
        ensure_Capacity();
        elements[size++]=element;
    }

    private void ensure_Capacity(){
        if(size > (elements.length>>1)){
            int[] new_element=new int[elements.length+elements.length>>1];
            for (int i = 0; i < size; i++) {
                new_element[i]=elements[(front++) % elements.length];
            }
            elements=new_element;
            front=0;
        }
    }

    public void ensure_IndexOut(int index){
        if(index>elements.length-1){
            throw new IndexOutOfBoundsException("数字不规范！");
        }
    }

}
