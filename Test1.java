class Foo<T> {
    T var;
}
class Cat{
    public void meow(){
        System.out.println("meow");
    }
}
class Dog{
    public void bark(){
        System.out.println("bark");
    }
}
public class Test1 {
    public static void main(String[] args) {
        Foo<Dog> dogFoo = new Foo<Dog>();
        dogFoo.var.bark();
    }
}
