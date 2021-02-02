import java.io.*;

import java.util.Scanner;
/* главный класс, в котором работаем с классами точек*/
public class Lab1 {
    /* главная функция */
    public static void main(String[] args){
        /* работа с точками в двумерном пространстве */
        Point2d myPoint = new Point2d (5, 3);//создает точку (0,0)
        Point2d myOtherPoint = new Point2d (5,3);//создает точку (5,3)
        Point2d aThirdPoint = new Point2d ();

        /* работа с точками в трехмерном пространстве*/
        Point3d[] points = new Point3d[3];
        // для считывания данных
        Scanner scanner = new Scanner(System.in);
        String value;
        double coord;
        int len = 3; // кол-во точек

        /* идём по циклу и считывает координаты точек */
        for(int i = 0; i<len; i++){
            points[i] = new Point3d();
            // x
            System.out.printf("Введите координату х %d-й точки: ", i+1);
            value = scanner.nextLine();
            coord = Double.valueOf(value);
            points[i].setX(coord);
            // y
            System.out.printf("Введите координату y %d-й точки: ", i+1);
            value = scanner.nextLine();
            coord = Double.valueOf(value);
            points[i].setY(coord);
            // z
            System.out.printf("Введите координату z %d-й точки: ", i+1);
            value = scanner.nextLine();
            coord = Double.valueOf(value);
            points[i].setZ(coord);
        }
        /* сравниваем точки на равенство между собой */
        // результат сравнения точек. изначально считаем, что они не равны
        boolean eql = false;
        // проверили три точки на равенство
        if (points[0].compare(points[1])) {
            eql = true;
            System.out.printf("Ошибка! Точка 1 с координатами (%f;%f;%f) и точка 2 с координатами с координатами (%f;%f;%f) равны",
                    points[0].getX(), points[0].getY(), points[0].getZ(), points[1].getX(), points[1].getY(), points[1].getZ());
        }
        if (points[1].compare(points[2])) {
            eql = true;
            System.out.printf("Ошибка! Точка 2 с координатами (%f;%f;%f) и точка 3 с координатами с координатами (%f;%f;%f) равны",
                    points[1].getX(), points[1].getY(), points[1].getZ(), points[2].getX(), points[2].getY(), points[2].getZ());
        }
        if (points[2].compare(points[0])) {
            eql = true;
            System.out.printf("Ошибка! Точка 3 с координатами (%f;%f;%f) и точка 1 с координатами с координатами (%f;%f;%f) равны",
                    points[2].getX(), points[2].getY(), points[2].getZ(), points[0].getX(), points[0].getY(), points[0].getZ());

        }
        /* если у нас нет ни одни точки не равны друг другу - находим площаль треугольника */
        if (eql == false){
            System.out.printf("Площадь треугольника, образованного тремя точками равна %f", Point3d.computeArea(points[0], points[1], points[2]));
        }
    }
}


