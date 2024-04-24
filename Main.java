
/**
 * Main
 */

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        getUserData();
    }

    public static void getUserData() {
        String lastName;
        String firstName;
        String middleName;
        String birthDate;
        long phoneNumber;
        char gender;
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Введите данные разделенные пробелом Фамилия Имя Отчество, дата рождения в формате - dd.mm.yyyy, номер телефона - цифры, пол - f или m");
        String input = scanner.nextLine();
        String[] data = input.split(" ");
        if (data.length < 6)
            throw new RuntimeException("Введено муньше данных чем требуется ");
        else if (data.length > 6)
            throw new RuntimeException("Введено больше данных чем требуется ");
        

        lastName = data[0];
        firstName = data[1];
        middleName = data[2];
        birthDate = data[3];

        if (data[5].length() != 1 || !Arrays.asList("f", "m").contains(data[5])) {
            throw new IllegalArgumentException("Пол должен быть символом латиницей f или m");
        }
        gender = data[5].charAt(0);

        try {
            phoneNumber = Long.parseLong(data[4]);
        } catch (NumberFormatException e) {
            System.out.println("Номер телефона должен быть целым числом");
            phoneNumber = Long.parseLong(data[4]);
        }

        try {
            writeToFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            System.out.println("Данные успешно записаны в файл.");
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }

    private static void writeToFile(String lastName, String firstName, String middleName,
            String birthDate, long phoneNumber, char gender) throws IOException {
        // String fileName = "lastName.txt";
        try (FileWriter writer = new FileWriter(lastName, true)) {
            writer.write(String.format("%s %s %s %s %d %c%n",
                    lastName,
                    firstName,
                    middleName,
                    birthDate,
                    phoneNumber,
                    gender));
        }

    }
}
