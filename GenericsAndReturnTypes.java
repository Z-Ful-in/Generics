interface GenericGetter<T extends GenericGetter<T>>{T get();}
interface Getter extends GenericGetter<Getter>{}//OK
public class GenericsAndReturnTypes {
    void test(Getter g){
        Getter result=g.get();
        GenericGetter gg=g.get();
    }
}
