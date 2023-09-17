import java.util.*;
public class GenericReading {
    static <T> T readExact(List<T> list){
        return list.get(0);
    }
    static List<Apple> apples=new ArrayList<>();
    static List<Fruit> fruit=new ArrayList<>();
    static void f1(){
        Apple a=readExact(apples);
        Fruit f=readExact(fruit);
        f=readExact(apples);
    }
    // If, however, you have a class, then its type is established when the class is instantiated
    static class Reader<T>{
        T readExact(List<T> list){
            return list.get(0);
        }
    }
    static void f2(){
        Reader<Fruit> fruitReadr=new Reader<>();
        Fruit f=fruitReadr.readExact(fruit);
        //Fruit a=fruitReadr.readExact(apples);
        //Error
        //List<Fruit> cannot be applied to List<Apple>
    }
    static class Convariant<T>{
        T readConvariant(List<? extends T> list){
            return list.get(0);
        }
    }
    static void f3(){
        Convariant<Fruit> fruitConvariant=new Convariant<>();
        Fruit f=fruitConvariant.readConvariant(fruit);
        Fruit a=fruitConvariant.readConvariant(apples);
    }
    public static void main(String[] args) {
        f1();
        f2();
        f3();
    }
}
