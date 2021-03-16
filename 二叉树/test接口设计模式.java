package 二叉树;


interface Comparable<E>{           //元素内部的比较
    int compareTo(E e);
}

interface Comparators<E>{        //接收泛型参数     比较器
    int compared(E e1,E e2);
}

class Binary_Tree<E>{
    private Comparators<E> comparators;
    public Binary_Tree(Comparators<E> comparators){
        this.comparators=comparators;
    }

    public int MyCompare(E e1,E e2){
        if(comparators!=null){
            return comparators.compared(e1,e2);
        }
        else {
            return ((Comparable<E>)e1).compareTo(e2);
        }

    }


}
public class test接口设计模式 {
    public static void main(String[] args) {
        Comparators<Integer> c = new Comparators<Integer>() {
            @Override
            public int compared(Integer e1, Integer e2) {
                return 0;
            }
        };
    }

}

