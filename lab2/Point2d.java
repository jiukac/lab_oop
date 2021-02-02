/**
 * A two-dimensional point class.
 */
public class Point2d {

    /* координата X */
    private double xCoord;

    /* координата Y */
    private double yCoord;

    /* Конструктор инициализации */
    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    //Вызовите конструктор с двумя параметрами и определите источник.
    public Point2d() {
        this(0, 0);
    }

    /* Возвращение координаты X */
    public double getX() {
        return xCoord;
    }

    /* Возвращение координаты Y */
    public double getY() {
        return yCoord;
    }
    /* Установка значения координаты X. */
    public void setX(double val) {
        xCoord = val;
    }

    /* Установка значения координаты Y. */
    public void setY(double val) {
        yCoord = val;
    }
    /* сравнение точек */
    public boolean compare( Point2d p1) {
        if ((p1.xCoord == xCoord) && (p1.yCoord == yCoord)){
            return true;
        }
        else{
            return false;
        }
    }
}

