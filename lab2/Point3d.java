
public class Point3d {

    /* координата X */
    private double xCoord;

    /* координата Y */
    private double yCoord;

    /* координата Z */
    private double zCoord;

    /* Конструктор инициализации */
    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    //Вызовите конструктор с тремя параметрами и определите источник.
    public Point3d() {
        this (0, 0, 0);
    }

    /* Возвращение координаты X */
    public double getX() {
        return xCoord;
    }

    /* Возвращение координаты Y */
    public double getY() {
        return yCoord;
    }

    /* Возвращение координаты Z */
    public double getZ() {
        return zCoord;
    }

    /* Установка значения координаты X. */
    public void setX(double val) {
        xCoord = val;
    }

    /* Установка значения координаты Y. */
    public void setY(double val) {
        yCoord = val;
    }

    /* Установка значения координаты Z. */
    public void setZ(double val) {
        zCoord = val;
    }

    /* сравнение точек */
    public boolean compare( Point3d p1) {
        if ((p1.xCoord == xCoord) && (p1.yCoord == yCoord) && (p1.zCoord == zCoord)){
            return true;
        }
        else{
            return false;
        }
    }
    /* определение расстояний между точками */
    public double distanceTo( Point3d p1 ) {
        return Math.round(Math.sqrt(Math.pow(p1.xCoord-xCoord,2)+Math.pow(p1.yCoord-yCoord,2)+Math.pow(p1.zCoord-zCoord,2))*100)/100D;
    }
    /* определение площади треугольника при помощи формулы Герона */
    public static double computeArea( Point3d p1, Point3d p2, Point3d p3 ) {
        // объявили стороны треугольника и полупериметр
        double a, b, c, p;
        // рассчитали стороны треугольника и полупериметр
        a = p1.distanceTo(p2);
        b = p2.distanceTo(p3);
        c = p3.distanceTo(p1);
        p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}


