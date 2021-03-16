package 二叉堆.test;

import java.util.HashMap;
@SuppressWarnings("unchecked")
public class Tire代码<V> {

    private int size;
    private final Single_Node<V> root = new Single_Node<V>();
    

    public V add(String key,V v){
        KeyCheck(key);
        Single_Node<V> temp = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            Single_Node<V> child = (Single_Node<V>) temp.get_Child().get(c);
            if(child == null){
                child = new Single_Node<>();
                temp.get_Child().put(c,child);
            }
            temp = child;
        }
        if(temp.word){
            V oldValue=temp.value;
            temp.value = v;
            return oldValue;
        }
        //新添一个单词
        temp.word = true;
        temp.value = v;
        size ++;
        return null;
    }


    private Single_Node<V> getNode(String key){
        KeyCheck(key);
        Single_Node<V> temp = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            temp = (Single_Node<V>) temp.get_Child().get(c);
            if(temp == null){
                return null;
            }
        }
        return temp.word ? temp:null;
    }

    private void KeyCheck(String key){
        if(key == null || key.length()==0){
            throw new IllegalArgumentException("key must not be null");
        }
    }








    static class Single_Node<V>{
        /**
         * 通过hashmap来存储所有子节点，并通过character来实现查找该子节点
         */
        HashMap<Character,Single_Node> child;
        V value;
        boolean word;
        public HashMap get_Child(){
            return child==null ? (child=new HashMap<>()) : child;
        }
    }
}
