package Package1;

public class Cat extends Animal {

    private String name;

    public Cat(){
        this.setName("");
    }

    public Cat(String name){
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void sayName(){
        System.out.println(this.getName());
    }

    public void sayType(){
        //super.sayType();
        System.out.println("I a cat.");
    }

}
