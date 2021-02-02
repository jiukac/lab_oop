/**
 * This class represents a simple two-dimensional map composed of square cells.
 * Each cell specifies the cost of traversing that cell.
 * * Этот класс представляет собой простую двухмерную карту, состоящую из квадратных ячеек.
  * Каждая ячейка определяет стоимость прохождения этой ячейки.
 **/
public class Map2D
{
    /** The width of the map. Ширина карты. **/
    private int width;

    /** The height of the map. Высота карты **/
    private int height;

    /**
     * The actual map data that the pathfinding algorithm needs to navigate.
     * Фактические данные карты, которые необходимы алгоритму поиска пути.
     **/
    private int[][] cells;

    /** The starting location for performing the A* pathfinding. / ** Начальное место для выполнения поиска пути A *.\/ **/ 

    private Location start;

    /** The ending location for performing the A* pathfinding. Конечное место для поиска пути A **/
    private Location finish;


    /** Creates a new 2D map, with the specified width and height. Создает новую 2D-карту с указанной шириной и высотой.**/
    public Map2D(int width, int height)
    {
        if (width <= 0 || height <= 0)
        {
            throw new IllegalArgumentException(
                    "width and height must be positive values; got " + width +
                    "x" + height);
        }
        
        this.width = width;
        this.height = height;
        
        cells = new int[width][height];
        
        // Make up some coordinates for start and finish. Составьте некоторые координаты начала и конца.
        start = new Location(0, height / 2);
        finish = new Location(width - 1, height / 2);
    }


    /**
     * This helper method checks the specified coordinates to see if they are
     * within the map's boundaries.  If the coordinates are not within the map
     * then the method throws an <code>IllegalArgumentException</code>.
     * * Этот вспомогательный метод проверяет указанные координаты на предмет соответствия
      * в пределах карты. Если координаты не в пределах карты
      * тогда метод генерирует исключение <code> IllegalArgumentException </code>.
     **/
    private void checkCoords(int x, int y)
    {
        if (x < 0 || x > width)
        {
            throw new IllegalArgumentException("x must be in range [0, " + 
                    width + "), got " + x);
        }
        
        if (y < 0 || y > height)
        {
            throw new IllegalArgumentException("y must be in range [0, " + 
                    height + "), got " + y);
        }
    }    
    
    /** Returns the width of the map. Возвращает ширину карты. **/
    public int getWidth()
    {
        return width;
    }
    
    /** Returns the height of the map.Возвращает высоту карты. **/
    public int getHeight()
    {
        return height;
    }
    
    /**
     * Returns true if the specified coordinates are contained within the map
     * area.
     * * Возвращает истину, если указанные координаты содержатся в пределах карты
      * площадь.
     **/
    public boolean contains(int x, int y)
    {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }
    
    
    /** Returns true if the location is contained within the map area.
     *  Возвращает истину, если местоположение содержится в области карты.**/
    public boolean contains(Location loc)
    {
        return contains(loc.xCoord, loc.yCoord);
    }
    
    /** Returns the stored cost value for the specified cell.
     * Возвращает сохраненное значение стоимости для указанной ячейки. **/
    public int getCellValue(int x, int y)
    {
        checkCoords(x, y);
        return cells[x][y];
    }
    
    /** Returns the stored cost value for the specified cell.
     * Возвращает сохраненное значение стоимости для указанной ячейки. **/
    public int getCellValue(Location loc)
    {
        return getCellValue(loc.xCoord, loc.yCoord);
    }
    
    /** Sets the cost value for the specified cell. 
     * Устанавливает значение стоимости для указанной ячейки.**/
    public void setCellValue(int x, int y, int value)
    {
        checkCoords(x, y);
        cells[x][y] = value;
    }
    
    /**
     * Returns the starting location for the map.  This is where the generated
     * path will begin from.
     * * Возвращает начальное местоположение для карты. Здесь сгенерированный
      * путь будет начинаться с.
     **/
    public Location getStart()
    {
        return start;
    }
    
    /**
     * Sets the starting location for the map.  This is where the generated path
     * will begin from.
     * * Устанавливает начальную точку для карты. Здесь сгенерированный путь
      * начнется с.
     **/
    public void setStart(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc cannot be null");
        
        start = loc;
    }

    /**
     * Returns the ending location for the map.  This is where the generated
     * path will terminate.
     * * Возвращает конечное местоположение карты. Здесь сгенерированный
      * путь будет завершен.
     **/
    public Location getFinish()
    {
        return finish;
    }
    
    /**
     * Sets the ending location for the map.  This is where the generated path
     * will terminate.
     * * Устанавливает конечное местоположение для карты. Здесь сгенерированный путь
      * прекратится.
     **/
    public void setFinish(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc cannot be null");
        
        finish = loc;
    }
}
