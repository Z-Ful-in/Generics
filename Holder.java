public class Holder <T>{
    private T value;
    public Holder(){}
    public Holder(T value){ this.value=value;}
    public T get(){ return value;}
    public void set(T value){ this.value=value;}
    public boolean equals(Object obj){
        return value.equals(obj);
    }

    public static void main(String[] args) {
        Holder<Apple> Apple=new Holder<>(new Apple());
        Apple d=Apple.get();
        Apple.set(d);
        // Holder<Fruit> Fruit=Apple; // Cannot upcast
        Holder<? extends Fruit> fruit=Apple; // OK
        Fruit p=fruit.get();
        d=(Apple) fruit.get(); // Returns 'Object'
        try {
            Orange c=(Orange) fruit.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // fruit.set(new Apple()); // Cannot call set()
        // fruit.set(new Fruit()); // Cannot call set()
        System.out.println(fruit.equals(d)); // OK
    }
}
