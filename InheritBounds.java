class HoldItem<T>{
    T item;
    HoldItem(T item){
        this.item=item;
    }
    T getItem(){
        return item;
    }
}
class Color2 <T extends HasColor> extends HoldItem<T>{
    // 外部的继承表明这个对象可以被当作HoldItem使用
    // <>内的extends表示 这个对象的 泛型参数 必须是HasColor的子类
    //Color2<HasColor> a =new Color2<>(new HasColor())
    //这里a可以调用的方法取决于Color2和HoldItem的方法
    //而a.item可以调用的方法取决于HasColor的方法
    Color2(T item){
        super(item);
    }
    java.awt.Color color(){
        return item.getColor();
    }
}
class ColorDimension2 <T extends Dimension&HasColor> extends Color2<T>{
    ColorDimension2(T item){
        super(item);
    }
    int getX(){
        return item.x;
    }
    int getY(){
        return item.y;
    }
    int getZ(){
        return item.z;
    }
}
class Solid2<T extends Dimension&HasColor&Weight> extends ColorDimension2<T>{
    Solid2(T item){
        super(item);
    }
    int weight(){
        return item.weight();
    }
}
public class InheritBounds {
    public static void main(String[] args) {
        Solid2<Bounded> solid2 = new Solid2<>(new Bounded());
        solid2.color();
        solid2.getY();
        solid2.weight();
    }
}
