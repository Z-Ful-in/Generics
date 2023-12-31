import java.lang.reflect.*;
public class GenericWithTypeToken<T> {
    private T[] array;
    @SuppressWarnings("unchecked")
    public GenericWithTypeToken(Class<T> type,int sz){
        array=(T[])Array.newInstance(type,sz);
    }
    public void put(int index, T item){
        array[index]=item;
    }
    public T get(int index){
        return array[index];
    }
    // Expose the underlying representation:
    public T[] rep(){
        return array;
    }

    public static void main(String[] args) {
        GenericWithTypeToken<Integer> gai = new GenericWithTypeToken<>(Integer.class, 10);
        Integer[] ia=gai.rep();
    }
}
