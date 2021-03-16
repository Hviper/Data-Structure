package 队列;

public class test {
    public static void main(String[] args) {
        Double_Path s = new Double_Path();
        for (int i = 0; i < 9; i++) {
            s.enqueue(i);
        }
        s.dequeue();
        for (int i = 9; i < 12; i++) {
            s.enqueue(i);
        }
        for (int i = 0; i < 11; i++) {
            System.out.print(s.dequeue()+"\t");
        }

    }

}
class Double_Path{
    private int head;
    private int[] elements;
    private int size;
    public Double_Path(){
        elements=new int[10];
    }
    //头部进队
    public void enqueue(int element){
        ensure_Capacity(size+1);
        elements[(size+head)%elements.length]=element;
        size++;
    }

    //尾部出队
    public int dequeue(){
        int head_element = elements[head];
        head=(head+1)%elements.length;
        size--;
        return head_element;
    }

    //至少确保size+1个元素
    public void ensure_Capacity(int size){
        int old_Capacity = elements.length;
        if(size<old_Capacity)return;
        int new_Capacity=old_Capacity+(old_Capacity>>1);
        int[] new_elements=new int[new_Capacity];
        for (int i = 0; i < size; i++) {
            new_elements[i]=elements[(head+i)%elements.length];
        }
        elements=new_elements;
        head=0;
    }
    public boolean isEmpty(){
        return size==0;
    }


}
