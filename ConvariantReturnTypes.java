class Base{}
class Derived extends Base{}
interface OrdinaryGetter{
    Base get();
}
interface DerivedGetter extends OrdinaryGetter{
    // Return type of overridden method is allowed to vary:
    Derived get();
}
public class ConvariantReturnTypes {
    void test(DerivedGetter d){
        Derived d2=d.get();
    }
}
