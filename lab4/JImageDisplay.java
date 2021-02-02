import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
/**
 класс JImageDisplay, производный от
 javax.swing.JComponent
 */
class JImageDisplay extends JComponent
{
    /*
     BufferedImage управляет
     изображением, содержимое которого можно записать
     */
    private BufferedImage displayImage;

    /*
       Метод получения отображаемого изображения из другого класса
      */
    public BufferedImage getImage() {
        return displayImage;
    }

    /*
      Конструктор JImageDisplay принимает целочисленные
      значения ширины и высоты, и инициализирует объект BufferedImage новым
      изображением с этой шириной и высотой, и типом изображения
      TYPE_INT_RGB
     */
    public JImageDisplay(int width, int height) {
        displayImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

       /*
         Вызываем метод setPreferredSize () родительского класса с заданной шириной и высотой.
         */
        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension);

    }
    /*
      Вызывается реализация суперкласса paintComponent (g), поэтому границы и объекты отображаются правильно.
      Затем изображение втягивается в компонент.
    */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(displayImage, 0, 0, displayImage.getWidth(),
                displayImage.getHeight(), null);
    }
    /*
     Устанавливает все пиксели изображения в черный цвет
     */
    public void clearImage()
    {
        int[] blankArray = new int[getWidth() * getHeight()];
        displayImage.setRGB(0, 0, getWidth(), getHeight(), blankArray, 0, 1);
    }
    /*
       Устанавливает пиксель в определенный цвет
    */
    public void drawPixel(int x, int y, int rgbColor)
    {
        displayImage.setRGB(x, y, rgbColor);
    }
}
