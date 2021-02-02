/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 * * Этот класс представляет собой конкретное место на 2D-карте. Координаты
  * целочисленные значения.
 **/
public class Location
{
    /**Координата X этого места. **/
    public int xCoord;

    /** Координата Y этого места **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. 
     * Создает новое местоположение с указанными целыми координатами.**/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). 
     * Создает новое местоположение с координатами (0, 0).**/
    public Location()
    {
        this(0, 0);
    }

    /** Compares this Location to another.
     * * Сравнивает это местоположение с другим. **/
    public boolean equals(Object obj) {
 
        // Is obj a Location? Является ли объект местоположением?
        if (obj instanceof Location) {

            // Cast another object to Location type,
            // then compare.  Return true if equal.
            // Приведение другого объекта к типу Location,
             // затем сравните. Вернуть истину, если равно.
            Location other = (Location) obj;
            if (xCoord == other.xCoord && yCoord == other.yCoord) {
                return true;
            }
        }

        // If we got here then they're not equal.  Return false.
        //Если мы попали сюда, они не равны. Вернуть false.
        return false;
    }

    /** Provides a hashCode for each Location. Предоставляет хэш-код для каждого местоположения **/
    public int hashCode() {
        int result = 23; // Some prime value

        // Use another prime value to comnbine
        //Используйте другое простое значение для объединения
        result = 37 * result + xCoord;
        result = 37 * result + yCoord;
        return result;
    }
}

