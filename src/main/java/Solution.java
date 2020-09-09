import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
Домашнее задание №2

        На входе имеется файл, заполненный словами. Необходимо считать все слова из файла, отсортировать их в алфавитном порядке и вывести на экран. Посчитать, сколько раз встречается в файле каждое из слов и вывести статистику на экран.
        Найти слово, встречающееся максимальное число раз в файле и его частоту и вывести на экран.
        Замечания:
            -В случае нескольких слов с максимальной частотой выводить 	необходимо все.
            -Предусмотреть, что слова могут быть разделены не только 	пробелами, но и знаками 	препинания, табуляции и переноса 	строки, все эти символы не должны учитываться.
            -Допускается только один полный обход файла.
            -Максимально предусмотреть возможные исключения.
            - (*) Предусмотреть ввод пользователем пути к файлу, как 	абсолютного, так и 	относительного.
*/
public class Solution {

    public static void main(String[] args){

//        String fileName = ".\\java№2.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        try {
            fileName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Solution solution = new Solution();
        Map<String, Integer> map = solution.fileToMap(fileName);
        solution.myPrintMap(map);

        solution.mostFrequency(map);

        Map<String, Integer> sortedMap = solution.sortAlph(map);
        System.out.println("Отсортированные слова в алфавитном порядке:");
        solution.myPrintMap(sortedMap);





    }
    // метод сортировки мапы по ключу в алфавитном порядке
    public Map<String, Integer> sortAlph(Map<String, Integer> unsortMap){
        return new TreeMap<String, Integer>(unsortMap);
    }

    public void myPrintMap (Map<String, Integer> map){
        for (Map.Entry<String, Integer> pair : map.entrySet()){
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }

    public void mostFrequency (Map<String, Integer> map){
        int maxValue = 0;
        for (Map.Entry<String, Integer> pair : map.entrySet()){
            if (pair.getValue() > maxValue){
                maxValue = pair.getValue();
            }
        }

        System.out.println("Наиболее часто встречающиеся слова:");
        for (Map.Entry<String, Integer> pair : map.entrySet()){
            if (pair.getValue() == maxValue){
                System.out.println(pair.getKey() + " : " + pair.getValue());
            }
        }
    }

    public Map<String, Integer> fileToMap(String fileName){
        Map<String, Integer> map = new HashMap<String, Integer>();
        String punct = "!\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~ \t\n";
        String fileString = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            int data = bufferedReader.read();
            while (data != -1){



                if (punct.contains("" + (char) data) && !fileString.toLowerCase().trim().equals("")){


                    if (map.containsKey(fileString.toLowerCase().trim())){

                        for (Map.Entry<String, Integer> pair : map.entrySet()){

                            if (fileString.toLowerCase().trim().equals(pair.getKey())){
                                pair.setValue(pair.getValue() + 1);
                                fileString = "";
                                break;
                            }
                        }
                    }
                    else {
                        map.put(fileString.toLowerCase().trim(), 1);
                        fileString = "";
                    }

                }
                else if (punct.contains("" + (char) data) && fileString.toLowerCase().trim().equals("")){
                    data = bufferedReader.read();
                    continue;
                }
                else {
                    fileString += (char) data;
                }
                data = bufferedReader.read();
            }

            if (!fileString.toLowerCase().trim().equals("")){
                if (map.containsKey(fileString.toLowerCase().trim())){

                    for (Map.Entry<String, Integer> pair : map.entrySet()){

                        if (fileString.toLowerCase().trim().equals(pair.getKey())){
                            pair.setValue(pair.getValue() + 1);
                            break;
                        }
                    }
                }
                else {
                    map.put(fileString.toLowerCase().trim(), 1);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
