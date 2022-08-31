/*
Предметная область - больница(пациент - ключ, номер палаты - значение). В соответствие 1 пациенту
можно выбрать только 1 палату, в одну палату может быть поселено несколько пациентов.
Если пациент встречается несколько раз в исходном списке, то в map запишется последнее вхождение
номера его палаты.
*/

import java.io.*;
import java.util.*;


public class Main {
    // Метод чтения из файла
    public static TreeMap read(String fileName) {

        TreeMap people = new TreeMap();                              // создаём отображение

        // создаём FileReader
        try (FileReader fr = new FileReader(fileName)) {

            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);

            // считываем сначала первую строку
            String line = reader.readLine();
            String[] components;

            while (line != null) {

                components = line.split(": ");

                if (components.length == 2 && Integer.parseInt(components[1]) > 0)
                    people.put(new User(components[0]), Integer.parseInt(components[1]));
                else
                    throw new IllegalArgumentException("Incorect key from file");

                // считываем остальные строки в цикле
                line = reader.readLine();


            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        return people;
    }

    // Метод записи в файл
    // append используется для обозначения режима записи( t - дозапись, f - перезапись)
    public static void write(TreeMap people, String fileName, boolean append) {

        // FileWriter
        try (FileWriter writer = new FileWriter(fileName, append)) {
            Set set = people.entrySet();
            Iterator i = set.iterator();


            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                writer.write(me.getKey() + ": " + me.getValue());
                writer.append('\n');
            }
            // очистка потока
            writer.flush();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

    }

    // метод вывода содержимого map в виде таблицы
    public static void show(TreeMap map) {
        // Получаем вид элементов
        Set set = map.entrySet();

        // Получаем итератор
        Iterator i = set.iterator();

        String leftAlignFormat = "| %-18s | %-5d | %-4d |%n";

        System.out.format("+--------------------+-------+------+%n");
        System.out.format("|       User         |  Age  | Ward |%n");
        System.out.format("+--------------------+-------+------+%n");
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.format(leftAlignFormat, ((User) me.getKey()).getName() + " " + ((User) me.getKey()).getSurname(), ((User) me.getKey()).getAge(), me.getValue());
        }
        System.out.format("+--------------------+-------+------+%n");


    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String line = new String();
        TreeMap<User, Integer> map = read("Users.txt");
        int k = 1;


        while (k == 1 || k == 2 || k == 3) {
            show(map);
            System.out.println("1 - search room number");
            System.out.println("2 - get all patients");
            System.out.println("3 - delete patients");
            System.out.println("0 - finish");
            System.out.print("Choise: ");
            k = in.nextInt();
            in.useDelimiter("\n");


            if (k == 1) {
                System.out.println("Enter key(Name Surname, Age): ");
                line = in.next();
                System.out.println(map.get(new User(line)));
            }
            if (k == 2)
                map.keySet().forEach(System.out::println);
            if (k == 3) {
                System.out.println("Enter key(Name Surname, Age): ");
                line = in.next();
                map.remove(new User(line));
            }
        }
        in.close();
        write(map,"Result.txt",false);
    }
}
