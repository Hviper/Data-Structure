package Tire数据结构;

import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class Tire<V> {
    private int size;
    private final Node<V> root = new Node<V>();
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void clear(){
        size=0;
        root.child.clear();
    }
    public V get(String key){
        Node<V> node = node(key);
        return node==null ? null : node.v;
    }
    public V add(String key,V value){
        Key_check(key);
        Node<V> node = root;            //指针作用
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            Node<V> childNode = node.get_Child().get(c);
            if(childNode == null){
                childNode = new Node<>();
                node.get_Child().put(c,childNode);

            }
            node = childNode;
        }

        if(node.flag){
            V oldValue = node.v;
            node.v = value;
            return oldValue;
        }
        //新添一个单词
        node.flag = true;
        node.v = value;
        size ++;
        return null;
    }

    public V remove(){
        return null;
    }
    private Node<V> node(String key){
        Key_check(key);
        Node<V> node = root;           //一个node多次使用，指针作用
        if(node.get_Child().isEmpty())return null;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            if(node.get_Child().isEmpty())return null;
            char c = key.charAt(i);
            node = node.get_Child().get(c);
            if(node == null)return null;
        }
        return node.flag ? node:null;
    }



    public void Key_check(String key){
        if(key == null || key.length() == 0){
            throw new IllegalArgumentException("key myst no be empty");
        }
    }


    static class Node<V>{
        //因为对于tire这个数据结构而言：孩子节点有多个【多叉树】，依次不能判断节点数量   ----【采用我们之前学习的一种 ”数据结构“ 来存储不确定的子节点】
        //将子节点全部映射至hashmap中，根据对应的 ‘character’ 字符来实现查找对应的映射
        HashMap<Character,Node<V>> child;
        V v;
        boolean flag;
        public HashMap<Character,Node<V>> get_Child(){
            //new 出的对象需要赋值给child，再返回，不然child一直为null
//            return child == null ? new HashMap<>() : child;         【之前错误代码】
            return child == null ? (child=new HashMap<>()) : child;
        }

    }

}
