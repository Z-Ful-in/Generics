import java.util.*;
public class ListOfGeneric<T> {
    private List<T> array=new ArrayList<>();
    public void add(T item){
        array.add(item);
    }
    public T get(int index){
        return array.get(index);
    }
}
