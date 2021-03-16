package hash表.哈希表;


import java.util.Arrays;
import java.util.Objects;

public class HashMap<K,V> {
    private int size;
    private Node[] table=null;       //存储的都是单链表的根节点


    public int CompareTo(K k1,K k2){
        //因为对于hashcode不一样也可能会进入同一个桶tables[i]中，对于同一个树中的hashcode必定相同
        int h1 = hashcode(k1);
        int h2 = hashcode(k2);
        int result = h1-h2;
        if(result!=0)return result;

        /**
         * 处理哈希冲突问题
         * 即处理对于 hashcode“差” 等于0的情况
         */
        if(Objects.equals(k1,k2))return 0;    //覆盖操作

        /**
         * 对于上面的哈希值比较和equals比较后，针对之后的分为：
         * 1.不同类
         * 2.同类的比较
         */
        if(k1!=null && k2!=null){
            String n1 = k1.getClass().getName();
            String n2 = k2.getClass().getName();
            result = n1.compareTo(n2);
            if(result!=0){
                return result;
            }
            //同一种类型，比如都是String类，那就直接调用它的compareTo()方法
            if(k1 instanceof Comparable){
                return ((Comparable) k1).compareTo(k2);
            }

        }
        /**
         * 同一种类型，哈希值相等，但是不equals，但是不具备可比较性
         * k1不为null，k2为null
         * k1为null，k2不为null
         */
        return System.identityHashCode(k1)-System.identityHashCode(k2);

    }



    private int hashcode(K k){
        return k==null?0:k.hashCode();
    }


    public HashMap(){
        table=new Node[16];          //数组大小为二的n次方
    }
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public boolean clear(){
        if(size==0)return true;
        Arrays.fill(table, null);
        size=0;
        return true;
    }



    public boolean puts(K key,V value){
        int index = index(key);
        Node root=table[index];
        if(root==null){
            root = new Node<>(key,value);
            table[index]=root;
            size++;
            return true;
        }
        Node temp=root;
        if(temp.v==value){
            return false;
        }
        while (temp.next!=null){
            temp=temp.next;
            if(temp.v==value){
                return false;
            }
        }
        Node<K, V> node = new Node<>(key,value);
        temp.next=node;
        node.pre=temp;
        size++;
        return true;
    }
    public V get(K key){
        Node<K, V> node = Node(key);
        return node==null?null:node.v;
    }

    //通过Value进行判断是否该数据是否在该哈希表中，遍历整个tables
    public boolean containValue(V v){
        if(size==0)return false;
        for (Node root : table) {
            if (root == null) continue;
            Node temp = root;
            while (temp != null) {
                if (Objects.equals(temp.v,v)) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    private Node<K,V> Node(K k){
        int index = index(k);
        return find_Node(table[index], k);
    }

    //！！！重要
    private Node<K,V> find_Node(Node root,K k){
        if(root==null)return null;
        Node temp = root;
        while (temp!=null){
            if(temp.k==k){
                return temp;
            }
            temp=temp.next;
        }
        return null;
    }

    public Node<K,V> put(K key,V value){
        int hashcode1 = hashcode(key);      //新添加节点的hashcode
        int index = hashcode1%table.length;
        Node root = table[index];
        if(root==null){
            root =new Node(key,value);
            table[index]=root;
            size++;
            return null;
        }
        Node temp =root;
        int cmp=0;
        do {
            int hashcode2=hashcode((K) temp.k);     //树上的节点的hashcode
            if(hashcode2>hashcode1){
                cmp=1;
            }else if(hashcode2<hashcode1){
                cmp=-1;
            }else if(Objects.equals(temp.v,value)){
                cmp=0;
            }


            if(cmp>0){
                temp=temp.left;
            }else if(cmp<0){
                temp=temp.right;
            }else {
                //覆盖
            }

        }while (temp!=null);
        return null;
    }




    private int index(K key){
        int hashcode = key==null?0:key.hashCode();
        hashcode=hashcode ^ hashcode>>>16;           //对你的32为hashcode再进行位运算
        return hashcode%(table.length-1);
    }

}
