package 二叉堆.src;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class BinaryHeap<E> {
    E[] arr;
    private int size;
    private final int Capital = 20;
    public compareTo compareTo;
    public BinaryHeap(compareTo compareTo){
        this.compareTo=compareTo;
        arr = (E[])(new Object[Capital]);
    }
    public BinaryHeap(){
        arr = (E[])(new Object[Capital]);
    }

    public void add(E element){
        elementNotNullCheck(element);
        ensureCapital(size+1);
        arr[size] = element;
        siftUp(size);
        size++;
    }
    public void clear(){
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size=0;
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]+"\t");
        }
    }

    public E[] Build_Heap(E[] elements){

        int length = elements.length;
        size = length;
        this.arr =elements;

        for (int i = 1; i < length; i++) {
            siftUp(i);
        }
        return this.arr;
    }

    public E replace(E element){
        E root = arr[0];
        E replace = arr[size-1];
        arr[size-1] = null;
        arr[0] = replace;
        siftDown(0);
        return root;
    }



    public E remove(){
        E root = arr[0];
        arr[0] = arr[size-1];
        arr[--size] = null;
        siftDown(0);
        return root;
    }

    private void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("element is must not null");
        }
    }

    private void ensureCapital(int capital){
        int oldCapital = arr.length;
        if(oldCapital >= capital){
            return;
        }
        int new_Capital=oldCapital+(oldCapital>>1);
        E[] new_elements = (E[])(new Object[Capital]);
        for (int i = 0; i < size; i++) {
            new_elements[i]= arr[i];
        }
        arr = new_elements;
    }

    /**
     * JAVA默认为向下取整
     * 向   “上滤 ”（和它的父节点比较），一层比一层复杂：即对于元素的添加而言，复杂度会依次叠加
     * @param index
     */
    private void siftUp(int index){
        E child = arr[index];
        //只要index大于0，就会有父节点
        while (index > 0){
            int ParentIndex = (index-1)>>1;
            E parent = arr[ParentIndex];
            if(!compare(child,parent)){
                break;
            }
            arr[index] = parent;
            index = ParentIndex;
        }
        arr[index] = child;
    }

    //下滤（和它的子节点比较）
    private void siftDown(int index){
        int half = size>>1;
        E root = arr[index];         //保存当前的节点元素
        while (index < half){
            int i = (index<<1)+1;
            E child = arr[i];            //默认走左子树
            int right = i+1;
            // 小于size相等于 -----> 小于等于size-1
            //为什么需要这个判断：right < size，因为可能没有右子节点【满二叉树，如果有右子树说明right<size】
            if(right < size && compare(arr[right],child)){
                i = right;
                child = arr[right];
            }
            if(compare(root,child)){
                break;
            }
            arr[index] = child;
            index = i;
        }
        arr[index] = root;
    }


    public void build(E[] arr){
        int size = arr.length;
        int index = (size>>1)-1;
        for (int i = index; i >= 0; i--) {
            Down(i,arr);
        }
        System.out.println(Arrays.toString(arr));
    }

    //外部赛选最大值：堆排序：二叉堆
    private void down(int index,E[] elements){
       E element = elements[index];
       int size = elements.length;
       int half = size>>1;
       //一定是非叶子节点 ------> 一定有左子树,但不能保证一定有右子树，需要判断右子树的数组下标是否越界
       while (index<half){
           int left = (index<<1)+1;
           E child =elements[left];
           int right = left+1;
           if(right < size && compare(elements[right],child)){
               left = right;
               child = elements[right];
           }
           if(compare(element,child)){break;}
           elements[index] = child;
           index = left;
       }
       elements[index] = element;

    }

    /**
     * 实现：从一个数组中挑选出一个最大值   【时间复杂度为：log（n）】
     * @param index
     * @param arr
     */
    public void Down(int index,E[] arr){
        E current = arr[index];
        int size = arr.length;
        int begin_leafIndex = size>>1;
        while (index < begin_leafIndex){
            int left = (index<<1)+1;
            int right = left+1;
            E child = arr[left];
            if(right < size && compare(arr[right],child)){
                left = right;
                child = arr[right];
            }
            if(compare(current,child)){
                break;
            }
            arr[index] = child;
            index = left;
        }
        arr[index] = current;
        System.out.println(Arrays.toString(arr));
    }


    public boolean compare(E e1,E e2){
        if(compareTo != null){
            return compareTo.compare(e1,e2);
        }

        else if(e1 instanceof Comparable && e2 instanceof Comparable){
            return ((Comparable) e1).compareTo(e2) > 0;
        }
        throw new IllegalArgumentException("请实现接口");
    }


}
//外界提供比较接口
interface compareTo<E>{
    boolean compare(E e1,E e2);
}
