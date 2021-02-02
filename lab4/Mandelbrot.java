import java.awt.geom.Rectangle2D;

/*
  Класс предоставляет общий интерфейс и операции для генераторов фракталов
 */
public class Mandelbrot extends FractalGenerator
{
    /*
       Константа максимального кол-ва операций
     */
    public static final int MAX_ITERATIONS = 2000;

    /*
      Этот метод позволяет генератору фракталов указать, какая часть комплексной плоскости наиболее интересна
      для фрактала. Ему передается объект прямоугольника, и метод изменяет поля прямоугольника, чтобы показать
      правильный начальный диапазон для фрактала. Эта реализация устанавливает начальный диапазон в
      (-2 - 1,5i) - (1 + 1,5i) или x = -2, y = -1,5, ширина = высота = 3.
      */
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }

    /*
        Этот метод реализует итерационную функцию для фрактала Мандельброта.
        Он принимает два двойных значения для действительной и мнимой частей комплексной плоскости и возвращает
        количество итераций для соответствующей координаты.
      */
    public int numIterations(double x, double y)
    {
        /* Начинаем с итерации 0 */
        int iteration = 0;
        /* Инициализция */
        double zreal = 0;
        double zimaginary = 0;

       /*
           Вычисляем Zn = Zn-1 ^ 2 + c, где значения представляют собой комплексные числа,
           представленные zreal и zimaginary, Z0 = 0, а c - это конкретная точка во фрактале,
           который мы отображаем (задается x и y). Он повторяется до тех пор, пока Z ^ 2> 4
           (абсолютное значение Z больше 2) или пока не будет достигнуто максимальное количество итераций.
         */
        while (iteration < MAX_ITERATIONS &&
                zreal * zreal + zimaginary * zimaginary < 4)
        {
            double zrealUpdated = zreal * zreal - zimaginary * zimaginary + x;
            double zimaginaryUpdated = 2 * zreal * zimaginary + y;
            zreal = zrealUpdated;
            zimaginary = zimaginaryUpdated;
            iteration += 1;
        }

       /*
           Если достигнуто количество максимальных итераций, возврат -1 означает, что точка не вышла за границу.
         */
        if (iteration == MAX_ITERATIONS)
        {
            return -1;
        }

        return iteration;
    }

    /*
      Реализация toString () в этой реализации фрактала. Возвращает имя фрактала: «Мандельброт».
      */
    public String toString() {
        return "Mandelbrot";
    }

}
