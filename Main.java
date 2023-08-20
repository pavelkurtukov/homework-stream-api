import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> filteredList = new ArrayList<>();
        // Сначала фильтруем массив
        for (int element : intList) {
            if (element > 0 && element % 2 == 0) {
                filteredList.add(element);
            }
        }
        // Сортируем отфильтрованный массив
        Collections.sort(filteredList);
        // Выводим на экран
        for (int element : filteredList) {
            if (element > 0) {
                System.out.println(element);
            }
        }
    }
}
