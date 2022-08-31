import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/* Класс "Пользователь" включает в себя имя, фамилию и возраст, конструкторы, геттеры и сеттеры под все поля,
так же переопределённые toString и equals. */

/*Класс Пользователь(Человек)*/
public class User implements Comparable<User>{
    private String name, surname;       // имя, фамилия
    private int age;                    // возраст

    // Конструктор с параметрами
    User(String n, String s, int a) {
        name = n; surname = s;
        if (a < 0)
            throw new IllegalArgumentException("Value can`t be negative!");
        age = a;
    }

    // Конструктор для инициализации обьекта через строку

    User(String allInfo) {
        String[] data = allInfo.split(", ");                                       // строка, поделенная на масив подстрок, разделитель - ", "

        String n = data[0].split(" ")[0], s = data[0].split(" ")[1];          // определяем имя и фамилию
        int a = Integer.parseInt(data[1]);                                                // определяем возраст и кол-во проработанных дней
        if (a < 0)
            throw new IllegalArgumentException("Value can`t be negative!");

        name = n;surname = s;age = a;                                                     // инициализируем поля класса
    }

    // Конструктор по умолчанию
    User() {this("Name","Surname",0);}


    // гетеры и сетеры для имени, фамилии и возраста
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    // переопределение метода toString
    @Override
    public String toString() {return  name + " " + surname + ", " + age;}

    // переопределение метода equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
    }


    @Override
    public int compareTo( User user) {
        String n = name, s = surname, n1 = user.name, s1 = user.surname;
        if(n != n1)
            return n.compareTo(n1);
        else if(s != s1)
            return s.compareTo(s1);
        else
            return 1;

    }
}
