interface HasColor{
    java.awt.Color getColor();
}
class Colored <T extends HasColor>{
    T item;
    public Colored(T item){ this.item=item;}
    public T getItem(){return item;}
    // the bound allows u to call a method:
    public java.awt.Color color(){return item.getColor();}
}
class Dimension{
    public int x,y,z;
}
// this won't work-- class must be first, then interfaces:
// class ColoredDimension<T extends HasColor & Dimension>{}

// Multiple bounds:
class ColoredDimension<T extends Dimension&HasColor>{
    T item;
    public ColoredDimension(T item){this.item=item;}
    public T getItem(){return item;}
    public java.awt.Color color(){return item.getColor();}
    int getX(){return item.x;}
    int getY(){return item.y;}
    int getZ(){return item.z;}
}
interface Weight{ int weight();}

//As with inheritance, you can have only one concrete class but multiple interfaces:
class Solid <T extends Dimension&HasColor&Weight>{
    T item;
    public Solid(T item){ this.item=item;}
    public T getItem(){return item;}
    public java.awt.Color color(){return item.getColor();}
    int getX(){return item.x;}
    int getY(){return item.y;}
    int getZ(){return item.z;}
    int weight(){return item.weight();}
}

class Bounded extends Dimension implements HasColor,Weight{
    public java.awt.Color getColor(){return null;}
    public int weight(){return 0;}
}

public class BasicBound {
    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<Bounded>(new Bounded());//Bounded is a concrete class
        solid.color();
        solid.getY();
        solid.weight();
    }
}
