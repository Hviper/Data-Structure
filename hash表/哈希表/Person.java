package hash表.哈希表;

import java.util.Objects;

public class Person {
    public static void main(String[] args) {
        Person p1 = new Person("kiwi", 15, 10.5);
        Person p4 = new Person("kiwi", 15, 10.5);
        Person p2 = new Person("java", 18, 10.5);
        Person p3 = new Person("python", 5, 10.5);

    }
    private final String name;
    private final int age;
    private final double source;
    public Person(String name,int age,double source){
        this.name=name;
        this.age=age;
        this.source=source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Double.compare(person.source, source) == 0 &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, source);
    }



}
