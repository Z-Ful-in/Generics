import coffee.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

interface Addable<T>{ void add(T t); }
public class Fill2 {
    //ClassToken version:
    public static<T> void fill(Addable<T> addable, Class<? extends T> classToken, int size){
        try{
            for (int i = 0; i < size; i++) {
                addable.add(classToken.getDeclaredConstructor().newInstance());
            }
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    //Generator version:
    /*public static<T> void fill(Addable<T> addable, Generator<T> generator,int size){
        for (int i = 0; i < size; i++) {
            addable.add(generator.next());
        }
    }*/
}

// to adapt a base type, you must use composition
// Make any Collection Addable using composition:
class AddableCollectionAdapter <T> implements Addable<T>{
    private Collection<T> c;
    public AddableCollectionAdapter(Collection<T> c){
        this.c=c;
    }

    @Override
    public void add(T t) {
        c.add(t);
    }
}
// a helper to capture type automatically:
class Adapter{
    public static <T> Addable<T> CollectionAdapter(Collection<T> c){
        return new AddableCollectionAdapter<>(c);
    }
}

class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T>{
    @Override
    public void add(T t) {
        super.add(t);
    }
}

class Fill2Test{
    public static void main(String[] args) {
        List<Coffee> carriar=new ArrayList<>();
        Fill2.fill(new AddableCollectionAdapter<Coffee>(carriar),
                Coffee.class,3);
        // help method captures the type:
        Fill2.fill(Adapter.CollectionAdapter(carriar),
                Latte.class,2);
        for(Coffee c: carriar){
            System.out.println(c);
        }
        System.out.println("----------------------");
        // Use an adapted class:
        AddableSimpleQueue<Coffee> coffeeQueue=new AddableSimpleQueue<>();
        Fill2.fill(coffeeQueue,Mocha.class,4);
        Fill2.fill(coffeeQueue,Latte.class,1);
        for(Coffee c: coffeeQueue){
            System.out.println(c);
        }
    }
}
