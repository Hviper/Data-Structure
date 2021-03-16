package 栈;

public class zhan {
    public static void main(String[] args) {
        new_Stack stack = new new_Stack(4);
        stack.push("kiwi");
        stack.push("apache");
        stack.push("linux");
        stack.push("python");
        stack.show();
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
    }
}

class new_Stack {
    private Object[] arr;
    private int max;

    //设置一个指针（对于数组的指针：只需要考虑数组下标即可，temp辅助指针++--即可）,表示栈底
    private int pointer=-1;


    public new_Stack(int max){
        this.max=max;
        this.arr=new Object[this.max];
    }

    public new_Stack() {

    }

    public boolean isEmpty(){
        return pointer==-1;
    }
    public boolean isFull(){
        return pointer==this.max-1;
    }

    //进栈
    public void push(Object o){
        if(isFull()){
            System.out.println("栈空间已经满了！！！");
            return;
        }
        ++pointer;
        arr[pointer]=o;
    }

    //出栈
    public Object pop(){
        if(isEmpty()){
            System.out.println("栈空间已空！！！");
            return null;
        }
        return arr[pointer--];

    }

    //遍历
    public void show(){
        while (!isEmpty()){
            System.out.println(arr[pointer--]);
        }
    }

}