package MainActivity;

import java.util.Scanner;
public class Main {

    public static String nameSql="Postgresql";
    public static String nameDataBase="World";

    public static void main(String args[]) {

       /* Scanner in = new Scanner(System.in);
        System.out.printf("Введите СУБД :");
        nameSql = in.nextLine();
        System.out.printf("Введите название БД :");
        nameDataBase = in.nextLine();*/
        Controller.GetData();
    }
}