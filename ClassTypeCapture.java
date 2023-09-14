import java.util.HashMap;
import java.util.Map;

class Building{}
class House extends Building{}
public class ClassTypeCapture<T> {
    Class<T> kind;//获得类型信息,T被擦除，所以kind实际上是一个Class对象
    Map<String,Class<?>> map;
    public ClassTypeCapture(Class<T> kind){
        this.kind=kind;
        map=new HashMap<String,Class<?>>();
    }
    public boolean f(Object arg){
        return kind.isInstance(arg);
    }
    public void addType(String typename,Class<?> kind){
        map.put(typename,kind);
    }
    public void createNew(String typename){
        if(!map.containsKey(typename))
            throw new RuntimeException("Bad key: "+typename);
        try{
            map.get(typename).getDeclaredConstructor().newInstance();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));
        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);
        System.out.println(ctt2.f(new Building()));
        System.out.println(ctt2.f(new House()));
        ctt1.addType("House",House.class);
        ctt1.createNew("Building");
    }
}
