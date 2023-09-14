abstract class CreateWithGeneric<T>{
    final T element;
    CreateWithGeneric(){
        element=create();
    }
    abstract T create();
}
class X{}
class creator extends CreateWithGeneric<X>{
    X create(){
        return new X();
    }
    void f(){
        System.out.println(element.getClass().getSimpleName());
    }
}
public class CreateGeneric {
    public static void main(String[] args) {
        creator c=new creator();
        c.f();
    }
}
