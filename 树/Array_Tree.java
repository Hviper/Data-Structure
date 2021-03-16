package 树;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class Array_Tree {
    public static void main(String[] args) {
        Tree_Array_Node t1 = new Tree_Array_Node(1);
        Tree_Array_Node t2 = new Tree_Array_Node(2);
        Tree_Array_Node t3 = new Tree_Array_Node(3);
        Tree_Array_Node t4 = new Tree_Array_Node(4);
        Tree_Array_Node t5 = new Tree_Array_Node(5);
        Array_Tree array_tree = new Array_Tree();
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t3.right=t5;
        array_tree.stack_function(t1);


    }
    public void Tree_stack(int[] arr){
        int index=0;
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[index]);
        while (!stack.empty()){
            Integer pop = stack.pop();
            System.out.println(pop);
            while (index<arr.length-1){
                index+=1;
                stack.push(arr[index]);
            }
        }
    }



    public void stack_showAll(Single_Node S1){
        Stack<Single_Node> stack = new Stack<>();

        Single_Node temp=S1;
        stack.push(temp);

        while (!stack.empty()){
            Single_Node pop = stack.pop();      //update(更新)参数
            if(temp.next!=null){
                stack.push(temp.next);
            }
        }
        while (!stack.empty()){
            Single_Node pop = stack.pop();      //核心!!!【切换while循环的参数】 实现局部变量的最大化利用
            System.out.println(pop);
            if(pop.next!=null){
                stack.push(pop.next);
            }
        }
    }



    public void stack_function(Tree_Array_Node head){
        Stack<Tree_Array_Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.empty()){     //while循环和递归类似，都是重复执行下方代码，直到条件不符合跳出循环
            Tree_Array_Node pop = stack.pop();
            System.out.println(pop);
            if(pop.right!=null){
                stack.push(pop.right);
            }
            if(pop.left!=null){
                stack.push(pop.left);
            }
        }
    }
}

//队列
class Queue{
    private  Single_Node Queue_head;


    private int count=1;
    //入队
    public void push(Single_Node head,Single_Node node){
        Queue_head=head;
        head.add(node);
        count++;
    }
    //先进先出
    public Single_Node pop(){
        if(Queue_head!=null){
            Single_Node shop;
            shop=Queue_head;
            Queue_head=Queue_head.next;
            count--;
            return shop;
        }
        else return null;
    }

    public int size(){
        return count;
    }
}


//单链表节点类
class Single_Node{
    private String date;
    public Single_Node next;

    public Single_Node(String date){
        this.date=date;
    }


    public void add(Single_Node node){
        if(this.next==null){
            this.next=node;
            return;
        }
        Single_Node temp=this;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=node;
    }
    public void show(){
        Single_Node temp=this;
        while (temp.next!=null){
            System.out.println(temp);
            temp=temp.next;
        }
        System.out.println(temp);
    }

    @Override
    public String toString() {
        return "Single_Node{" +
                "date='" + date + '\'' +
                '}';
    }
}

//树节点类
class Tree_Array_Node{
    public Tree_Array_Node left;
    public Tree_Array_Node right;
    public int id;
    public Tree_Array_Node(int id){
        this.id=id;
    }

    //通过子节点来递归
    public ArrayList<Tree_Array_Node> show_All(ArrayList<Tree_Array_Node> list){


        list.add(this);
        if(this.left!=null){
            this.left.show_All(list);
        }
        if(this.right!=null){
            this.right.show_All(list);
        }
        return list;
    }

    @Override
    public String toString() {
        return "Tree_Array_Node{" +
                "id=" + id +
                '}';
    }
}

class Array_Node{
    int[] arr;
    public Array_Node(int[] arr){
        this.arr=arr;
    }
    public void show(int index){
        if(arr.length==0){
            System.out.println("该数组为空");
            return;
        }
        System.out.print(arr[index]+"\t");
        if((2*index+1)<arr.length){
            show(2*index+1);       //原数组中以index下标的元素的左子节点为原数组下标为（2*index+1）的元素
        }
        if((2*index+2)<arr.length){
            show(2*index+2);     //右子节点
        }
    }
}
