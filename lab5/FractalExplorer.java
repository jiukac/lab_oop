import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.JFileChooser.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO.*;
import java.awt.image.*;

/*
  Класс позволяет исследовать различные части фрактала с помощью
  создания и отображения графического интерфейса Swing и обработки событий, вызванных различными
  взаимодействия с пользователем
 */
public class FractalExplorer
{
    /** Fields for save button, reset button, and combo box for enableUI. **/
    private JButton saveButton;
    private JButton resetButton;
    private JComboBox myComboBox;
    
    /** Number of rows remaining to be drawn. **/
    private int rowsRemaining;

    /* Целочисленный размер дисплея - это ширина и высота дисплея в пикселях */
    private int displaySize;

    /*
       JImageDisplay - ссылка для обновления отображения с помощью различных методов по мере вычисления фрактала
       */
    private JImageDisplay display;

    /* Объект FractalGenerator для каждого типа фрактала */
    private FractalGenerator fractal;

    /*
       Объект Rectangle2D.Double, который указывает диапазон комплекса,
       который мы в настоящее время отображаем
       */
    private Rectangle2D.Double range;

    /*
      Конструктор, который принимает размер дисплея, сохраняет его и
       инициализирует объекты диапазона и фрактального генератора
      */
    public FractalExplorer(int size) {
        /* размер экрана */
        displaySize = size;

        /* Инициализируем фрактальный генератор и объекты диапазона */
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
        
    }

    /*
      Этот метод инициализирует графический интерфейс Swing с помощью JFrame,
      содержащий объект JImageDisplay и кнопку для сброса дисплея,
       кнопку для сохранения текущего фрактального изображения и JComboBox для выбора типа фрактала.
        JComboBox находится в панели JPanel с меткой
      */
    public void createAndShowGUI()
    {
        /* Настроим фрейм для использования java.awt.BorderLayout для его содержимого */
        display.setLayout(new BorderLayout());
        JFrame myFrame = new JFrame("Fractal Explorer");
        
        /*Добавляем объект отображения изображения в позицию BorderLayout.CENTER */
        myFrame.add(display, BorderLayout.CENTER);

        /* Создать кнопку сброса */
        resetButton = new JButton("Reset");

        /* Экземпляр ButtonHandler на кнопке сброса */
        ButtonHandler resetHandler = new ButtonHandler();
        resetButton.addActionListener(resetHandler);

        /* Экземпляр MouseHandler в компоненте фрактального отображения */
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        /* Установить операцию закрытия фрейма по умолчанию на "выход" */
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Настроить поле со списком */
        myComboBox = new JComboBox();

        /* Добавим каждый объект типа фрактала в поле со списком */
        FractalGenerator mandelbrotFractal = new Mandelbrot();
        myComboBox.addItem(mandelbrotFractal);
        FractalGenerator tricornFractal = new Tricorn();
        myComboBox.addItem(tricornFractal);
        FractalGenerator burningShipFractal = new BurningShip();
        myComboBox.addItem(burningShipFractal);

        /* Экземпляр ButtonHandler в поле со списком */
        ButtonHandler fractalChooser = new ButtonHandler();
        myComboBox.addActionListener(fractalChooser);
        
       /*
          Создам новый объект JPanel, добавим к нему объект JLabel и объект JComboBox и добавим панель во фрейм
          в СЕВЕРНОЙ позиции макета.
         */
        JPanel myPanel = new JPanel();
        JLabel myLabel = new JLabel("Fractal:");
        myPanel.add(myLabel);
        myPanel.add(myComboBox);
        myFrame.add(myPanel, BorderLayout.NORTH);
        
        /*Создали кнопку сохранения, добавили ее в JPanel в позиции BorderLayout.SOUTH вместе с кнопкой сброса*/
        // position along with the reset button.
        saveButton = new JButton("Save");
        JPanel myBottomPanel = new JPanel();
        myBottomPanel.add(saveButton);
        myBottomPanel.add(resetButton);
        myFrame.add(myBottomPanel, BorderLayout.SOUTH);

        /* Экземпляр ButtonHandler на кнопке сохранения */
        ButtonHandler saveHandler = new ButtonHandler();
        saveButton.addActionListener(saveHandler);
        
        
         /*
          Разместите содержимое фрейма, сделайте его видимым и запретите изменение размера окна
         */
        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setResizable(false);
        
    }

    /*
       Приватный вспомогательный метод для отображения фрактала. Этот метод зацикливается
       через каждый пиксель на дисплее и вычисляет количество итераций для соответствующих координат
        в области отображения фрактала. Если количество итераций равно -1, устанавливаем черный цвет пикселя.
        В противном случае выбираем значение, основанное на количестве итераций.
        Обновляем дисплей цветом для каждого пикселя и перекрасим JImageDisplay,
         когда все пиксели будут нарисованы
      */
    private void drawFractal()
    {
        // Call enableUI(false) to disable all the UI controls during drawing.
        enableUI(false);
        
        // Set rowsRemaining to the total number of rows.
        rowsRemaining = displaySize;
        
        // Loop through every row in the display and call FractalWorker
        // to draw it.
        for (int x=0; x<displaySize; x++){
            FractalWorker drawRow = new FractalWorker(x);
            drawRow.execute();
        }

    }
    /**
     * Enables or disables the interface's buttons and combo-box based on the
     * specified value.  Updates the enabled-state of the save button, reset
     * button, and combo-box.
     */
    private void enableUI(boolean val) {
        myComboBox.setEnabled(val);
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
    }
    /**
     * An inner class to handle ActionListener events.
     */
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            /*  Получить источник действия */
            String command = e.getActionCommand();
            /*
                Если источником является поле со списком,
                 получить фрактал, выбранный пользователем и отобразить его
             */
            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                fractal = (FractalGenerator) mySource.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();
                
            }
             /*
             Если источником является кнопка сброса, сбросить дисплей и нарисовать фрактал
             */
            else if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            /*
             Если источником является кнопка сохранения, сохранить текущее фрактальное изображение
             */
            else if (command.equals("Save")) {

                /* Разрешить пользователю выбрать файл для сохранения изображения */
                JFileChooser myFileChooser = new JFileChooser();

                /* Сохранять тольков в PNG */
                FileFilter extensionFilter =
                new FileNameExtensionFilter("PNG Images", "png");
                myFileChooser.setFileFilter(extensionFilter);

                 /*
                    Гарантия, что средство выбора файлов не разрешит имена файлов, отличных от ". Png"
                 */
                myFileChooser.setAcceptAllFileFilterUsed(false);
                
                /*
                  Всплывание окна «Сохранить файл», в котором пользователь может выбрать каталог и файл для сохранения
                 */
                int userSelection = myFileChooser.showSaveDialog(display);
                
                /*
                 Если результатом операции выбора файла является APPROVE_OPTION, продолжаем операцию сохранения файла
                 */
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    
                    // Get the file and file name. 
                    java.io.File file = myFileChooser.getSelectedFile();
                    String file_name = file.toString();

                    /* Пытаемся сохранить фрактал на диск */
                    try {
                        BufferedImage displayImage = display.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    }
                     /*Отлавливаем все исключения и выводим с ними сообщения*/
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(display,
                        exception.getMessage(), "Cannot Save Image",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }
                /*
                   Если операция сохранения не APPROVE_OPTION - return
                 */
                else return;
            }
        }
    }

    /*
      Внутренний класс для обработки событий MouseListener с дисплея
      */
    private class MouseHandler extends MouseAdapter
    {
        /*
          Когда обработчик получает событие щелчка мыши, он сопоставляет пиксельные координаты щелчка с
          областью отображаемого фрактала, а затем вызывает метод генератора RecenterAndZoomRange () с координатами,
           по которым был выполнен щелчок, и шкалой 0,5
         */
        @Override
        public void mouseClicked(MouseEvent e)
        {
            // Возвращает если rowsRemaining не равен 0
            if (rowsRemaining != 0) {
                return;
            }
            /* Получить координату x области отображения щелчка мыши */
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x,
            range.x + range.width, displaySize, x);

            /* Получить координату y области отображения щелчка мыши */
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y,
            range.y + range.height, displaySize, y);
            
            /*
              Вызвать метод RecenterAndZoomRange () генератора с координатами,
              по которым был выполнен щелчок, и масштабом 0,5
             */
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            
            /*
              Перерисовать фрактал после изменения отображаемой области
             */
            drawFractal();
        }
    }
    
    /*
     Вычисляет значения цвета для одной строки фрактала.
     */
    private class FractalWorker extends SwingWorker<Object, Object>
    {
        /*
         Поле для целочисленной y-координаты строки которая будет вычислена.
         */
        int yCoordinate;
        
        /*
         Массив int для хранения вычисленных значений цвета для каждого пикселя в строке
         */
        int[] computedRGBValues;
        
        /*
         Конструктор принимает координату y в качестве аргумента и сохраняет ее
         */
        private FractalWorker(int row) {
            yCoordinate = row;
        }
        
        /*
         Этот метод вызывается в фоновом потоке. Он вычисляет
         значение цвета для всех пикселей в 1 строке и сохраняет его в
         соответствующем элементе целочисленного массива. Возвращает null
         */ 
        protected Object doInBackground() {
            
            computedRGBValues = new int[displaySize];
            
            // Повторяет все пиксели в строке
            for (int i = 0; i < computedRGBValues.length; i++) {

                // Ищет соответствующие координаты xCoord и yCoord в области отображения фрактала
                double xCoord = fractal.getCoord(range.x,
                range.x + range.width, displaySize, i);
                double yCoord = fractal.getCoord(range.y,
                range.y + range.height, displaySize, yCoordinate);

                // Вычислите количество итераций для координат в области отображения фрактала.
                int iteration = fractal.numIterations(xCoord, yCoord);
                        
                // If number of iterations is -1, set current int in the
                // computed RGB values int array to black.
                if (iteration == -1){
                    computedRGBValues[i] = 0;
                }
            
                else {
                   // В противном случае выберитает значение оттенка, основанное на количестве итераций
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                
                    // Обновите массив цветом текущего пикселя.
                    computedRGBValues[i] = rgbColor;
                }
            }
            return null;
            
        }
        /*
         Вызывается после завершения фоновой задачи.
         Рисует пиксели для текущей строки и обновляет отображение для этой строки.
         */
        protected void done() {
            // Повторяет итерацию по массиву данных строк, рисуя пиксели, которые были вычислены в doInBackground().
            // Перерисовывает строку, которая была изменена.
            for (int i = 0; i < computedRGBValues.length; i++) {
                display.drawPixel(i, yCoordinate, computedRGBValues[i]);
            }
            display.repaint(0, 0, yCoordinate, displaySize, 1);
            
            // Уменьшает оставшиеся строки. Если 0, вызывает enableUI(true)
            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }

    /*
        Статический метод main () для запуска FractalExplorer.
        Инициализирует новый экземпляр FractalExplorer с размером отображения 600,
         вызывает createAndShowGUI () в объекте проводника, а затем вызывает drawFractal () в проводнике,
         чтобы увидеть начальное представление
       */
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(600);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
    
}