package Package1;

public class Main {

    public static void main (String[] args){
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape s1 = shapeFactory.getShape("SQUARE");
        s1.draw();
        Shape s2 = shapeFactory.getShape("RECTANGLE");
        s2.draw();
        Shape s3 = shapeFactory.getShape("TRIANGLE");
        s3.draw();
    }

}
