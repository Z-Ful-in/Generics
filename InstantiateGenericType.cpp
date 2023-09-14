template<class T> class Foo{
    T x;
    T* y;
public:
    Foo(){
        y = new T();
    }
};
class Bar{};
int main(){
    Foo<Bar> fb;
    Foo<int> fi;
}