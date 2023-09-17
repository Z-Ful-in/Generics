import java.util.*;
public class GenericWriting {
    static <T> void writeExact(List<T> list, T item){
        list.add(item);
    }
    static List<Apple> apples=new ArrayList<Apple>();
    static List<Fruit> fruit=new ArrayList<Fruit>();
    static void f1(){
        writeExact(apples,new Apple());
        //writeExact(fruit,new Apple());
        //JDK1.5会把T识别为Apple从而导致Error
        //但是JDK8就会把T识别为Fruit了，所以并不会报错
    }
    static <T> void writeWithExact(List<? super T> list,T item){
        list.add(item);
    }
    static void f2(){
        writeWithExact(apples,new Apple());
        writeWithExact(fruit,new Apple());
    }

    public static void main(String[] args) {
        f1();
        f2();
    }
}
