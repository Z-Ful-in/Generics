class Manipulator<T> {
    private T obj;
    public Manipulator(T x){ obj=x;}
    public void manipulate(){
        //obj.f();  //Error
    }
}
public class Manipulation {
    public static void main(String[]args){
        HasF hf=new HasF();
        Manipulator<HasF> manipulator=new Manipulator<HasF>(hf);
        manipulator.manipulate();
    }
}
