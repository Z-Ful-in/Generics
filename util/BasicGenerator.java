package util;

public class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;
    public BasicGenerator(Class<T> type){this.type=type;}
    public T next(){
        try{
            // Assumes type is a public class:
            return type.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static <T> Generator<T> create(Class<T> type){
        return new BasicGenerator<>(type);
    }
}
