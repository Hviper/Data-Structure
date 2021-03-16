package hash表.哈希表;

/**
 * 节点为保存两个数据，一个为 key-value
 * @param <K>
 * @param <V>
 */
public class Node<K,V>{
    int hashcode;
    public Node left;
    public Node right;
    public V v;
    public K k;
    public Node next=null;
    public Node pre=null;

    public Node(K k,V v){
        hashcode=k==null?0:k.hashCode();
        this.k=k;
        this.v=v;
    }
    private int index(K key){
        int hashcode = key==null?0:key.hashCode();
        hashcode=hashcode ^ hashcode>>>16;           //对你的32为hashcode再进行位运算
        return hashcode;
    }
}
