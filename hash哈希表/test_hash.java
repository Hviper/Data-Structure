package hash哈希表;

public class test_hash {
    public static void main(String[] args) {
        Node n1 = new Node("kiwi",6);
        Node n2 = new Node("Apache",4);
        Node n3 = new Node("Tomcat",1);
        Node n4 = new Node("Selves",18);
        Hash_dealNode hashmap = new Hash_dealNode(10);
        hashmap.hash_add(n1);
        hashmap.hash_add(n2);
        hashmap.hash_add(n3);
        hashmap.hash_add(n4);
        hashmap.hash_show();

    }
}

class Hash_dealNode{
    deal_Node[] arr;
    int size;
    public Hash_dealNode(int size){
        this.size=size;
        arr=new deal_Node[size];            //创建了，但是默认该数组为空！！，需要初始化为deal_Node类型
        for (int i = 0; i < size; i++) {
            arr[i]=new deal_Node();            //对每一个deal_Node【】数组里的值初始化，数组里的每一个元素都是deal_Node类型数据，都可以管理Node数据
        }
    }

    //散列函数,确定数据存储在hash表中的那个链表中！！！
    public int hashcode(int id){
        return id%size;
    }

    public void hash_add(Node date){
        int hashcode = hashcode(date.id);
        arr[hashcode].add(date);
        System.out.println("该数据被"+date.date+"存储在第"+(hashcode+1)+"条链表中");
    }

    public void hash_show(){
        for (int i = 0; i < size; i++) {
            arr[i].show();
        }
    }
}


class deal_Node{
    Node head=null;
    public void add(Node node){
        if (head != null) {
            node.next = head;
        }
        head=node;
    }
    public void show(){
       if(head!=null){
           Node temp=head;
           System.out.println(temp);
           while (temp.next!=null){
               System.out.println(temp.next);
               temp=temp.next;
           }
       }
    }


}


class Node{
    Node next;
    String date;
    int id;
    public Node(String date,int id){
        this.date=date;
        this.id=id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "date='" + date + '\'' +
                ", id=" + id +
                '}';
    }
}
