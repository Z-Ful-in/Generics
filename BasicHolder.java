public class BasicHolder <T>{
    T element;

    public void set(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    void f(){
        System.out.println(element.getClass().getSimpleName());
    }
}
