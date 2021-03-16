package hash表.测试代码;

import java.util.HashMap;
import java.util.Objects;

public class test {
    Object[] arr=new Object[1^4];
    public static void main(String[] args) {
        HashMap<Object, Object> s = new HashMap<>();
        Person p1 = new Person(18,"kiwi",18);
        Person p2 = new Person(18,"kiwi",18);




        //完全是针对key来进行计算hashcode的，假如key为正常的int则直接返回，否则调用该方法的hashcode等方法
        s.put(p1,18);
        Object put = s.put(p2, 1);
        System.out.println("数字 “1”" +
                "覆盖了 "+put);

        System.out.println(s.get(p1));
        System.out.println(s.get(p2));
    }
    public void hello(){
        int size = 0;
        //do---while的逻辑是少一次判断的处理，https://www.bilibili.com/video/BV1dh411R7K2?p=229  第九分钟
        do {
            System.out.println("哈哈哈");
            size++;
        }while (size<10);
    }


    public int hashcode2(long date){
        return (int)(date ^ (date>>>32));
    }

    public int index(Person person){
        return person.hashCode()%(arr.length-1);
    }
    public void put(Person person){
        int index = index(person);
        if(arr[index] == null){
            arr[index] = person;
            return;
        }
        System.out.println("继续添加下一个元素。。。。。");

    }


}
class Person{
    int date;
    String name;
    float height;

    public Person(int date, String name, float height) {
        this.date = date;
        this.name = name;
        this.height = height;
    }

    @Override
    public int hashCode() {
        int hashcode=this.date;
        hashcode = 31*hashcode+Float.hashCode(height);
        hashcode=hashcode*31+(name!=null?name.hashCode():0);
        return hashcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //能来到这里必然是person类型的数据
        Person person = (Person) o;
        return date == person.date &&
                Float.compare(person.height, height) == 0 &&
                Objects.equals(name, person.name);
    }
}
