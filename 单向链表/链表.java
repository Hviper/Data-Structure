package 单向链表;

public class 链表 {
    public static void main(String[] args) {
        Student s1=new Student("kiwi",18);
        Student s2=new Student("apple",18);
        Student s3=new Student("banana",18);
        Deal_Student deal_student = new Deal_Student();
        deal_student.add(s1);
        deal_student.add(s2);
        deal_student.add(s3);
        deal_student.show();
        System.out.println(deal_student.size());
        System.out.println(deal_student.head);
    }
}

class Deal_Student{
    public Student head;
    private Student foot;
    private int count;
    public void add(Student student){
        if(head==null){
            //在add方法内部，temp相对于head也是引用类型
            head=student;
            foot=head;
            foot.next=null;
        }else {
            foot.next=student;
            student.next=null;
            foot=student;
        }
        count++;
    }
    public void show(){
        Student temp =head;
        while (temp.next!=null){
            System.out.println(temp);
            temp=temp.next;
        }
        System.out.println(foot);
    }
    public int size(){
        return count;
    }
}

class Student{
    private String name;
    private int age;
    public Student next;
    public Student(String name,int age){
        this.age=age;
        this.name=name;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", age=" + age;
    }
}