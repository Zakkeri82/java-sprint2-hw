import java.util.InputMismatchException;
import java.util.Scanner;

class StepTracker {
    Scanner scanner;

    MonthData[] monthToData = new MonthData[12];

    int goalByStepsPerDay;


    StepTracker(Scanner scan) {
        scanner = scan;
        goalByStepsPerDay = 10000;
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumberStepsPerDay() {
        System.out.println("Введите номер месяца");
        try {
            int month = scanner.nextInt();
            if (month < 1 || month > 12) {
                System.out.println("Такого номера месяца нет");
                return;
            }
            System.out.println("Введите день от 1 до 30 (включительно)");
            int day = scanner.nextInt();
            if (day < 1 || day > 30) {
                System.out.println("Такого числа нет");
                return;
            }
            System.out.println("Введите количество шагов");
            int steps = scanner.nextInt();
            if (steps < 1) {
                System.out.println("Шагов должно быть больше нуля");
                return;
            }
            MonthData monthData = monthToData[month - 1];
            monthData.days[day - 1] = steps;
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Введите пожалуйста цифры.");
        }
    }

    void changeStepGoal() {
        System.out.println("Введите новую цель по количеству шагов.");
        try {
            int changeStep = scanner.nextInt();
            if (changeStep < 1) {
                System.out.println("Шагов должно быть больше нуля");
                return;
            }
            goalByStepsPerDay = changeStep;
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Введите пожалуйста цифры.");
        }
    }

    void printStatistic() {
        System.out.println("Введите число месяца");
        MonthData monthData;
        try {
            int month = scanner.nextInt();
            if (month < 1 || month > 12) {
                System.out.println("Такого месяца нет");
                return;
            }
            monthData = monthToData[month - 1];
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Введите пожалуйста цифры.");
            return;
        }
        int sumSteps = monthData.sumStepsFromMonth();
        System.out.println("Статистика по дням: ");
        monthData.printDaysAndStepsFromMonth();
        System.out.println("Общая сумма шагов за месяц: " + sumSteps);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + monthData.maxSteps());
        System.out.println("Среднее колличество пройденных шагов за месяц: " + monthData.averageStepsFromMonth());
        System.out.println("Пройденная дистанция (в километрах): " + Converter.convertToKm(sumSteps));
        System.out.println("Количество сожжённых килокалорий: " + Converter.convertStepsToKilocalories(sumSteps));
        System.out.println("Лучшая серия за месяц: " + monthData.bestSeries(goalByStepsPerDay));
        System.out.println();
    }
}