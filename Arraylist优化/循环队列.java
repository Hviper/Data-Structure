package Arraylist优化;

public class 循环队列 {
    public static void main(String[] args) {
        int[] elements={3,4,5,6,7,8,9,1,2};
        循环队列 s = new 循环队列();
        s.Update_List(elements,7);
    }
    public void Update_List(int[] elements,int head){

        while (elements[head%elements.length]!=0){

            System.out.println(elements[head%elements.length]);

            elements[head%elements.length]=0;

            head++;
        }
    }

}

//https://www.bilibili.com/video/BV1HJ411z7ZU?p=76
//循环队列
class Update_ArrayList{
    private final int[] elements;
    private int front;     //队头
    private int size;


    public Update_ArrayList(){
        elements=new int[10];
    }

    //出队:队头出队,front指针后移
    public int pop(){
        int frontElement= elements[front];
        elements[front]=0;         //出队以后该位置置空,对象则置为null
        front=(front+1) % elements.length;
        size--;
        return frontElement;
    }

    //入队
    public void push(int element){
        elements[(front+size)%elements.length]=element;
        size++;
    }


    public boolean isEmpty(){
        return size==0;
    }

    public int peek(){
        return elements[front];
    }

}
