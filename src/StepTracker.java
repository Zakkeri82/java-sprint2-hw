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
        int month;
        int day;
        int steps;
        System.out.println("Введите номер месяца");
        while (true) {
            try {
                month = scanner.nextInt();
                if (month < 1 || month > 12) {
                    System.out.println("Такого номера месяца нет. Введите номер месяца от 1 до 12");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Номер месяца должен быть введен цифрами от 1 до 12. Введите номер месяца");
            }
        }
        System.out.println("Введите день от 1 до 30 (включительно)");
        while (true) {
            try {
                day = scanner.nextInt();
                if (day < 1 || day > 30) {
                    System.out.println("Такого числа в месяце нет. Введите день от 1 до 30 (включительно)");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Номер дня должен быть введен цифрами от 1 до 30 (включительно). Введите день.");
            }
        }
        System.out.println("Введите количество шагов");
        while (true) {
            try {
                steps = scanner.nextInt();
                if (steps < 1) {
                    System.out.println("Шагов должно быть больше нуля. Введите количество шагов");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Количество шагов должно быть введено цифрами. Введите количество шагов");
            }
        }
        MonthData monthData = monthToData[month - 1];
        monthData.days[day - 1] = steps;
    }

    void changeStepGoal() {
        System.out.println("Введите новую цель по количеству шагов.");
        while (true) {
            try {
                int changeStep = scanner.nextInt();
                if (changeStep < 1) {
                    System.out.println("Шагов должно быть больше нуля. Введите новую цель по количеству шагов.");
                } else {
                    goalByStepsPerDay = changeStep;
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Количество шагов должно быть введено цифрами. Введите новую цель по количеству шагов.");
            }
        }
    }

    void printStatistic() {
        System.out.println("Введите число месяца");
        MonthData monthData;
        while (true) {
            try {
                int month = scanner.nextInt();
                if (month < 1 || month > 12) {
                    System.out.println("Такого номера месяца нет. Введите номер месяца от 1 до 12");
                } else {
                    monthData = monthToData[month - 1];
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Номер месяца должен быть введен цифрами от 1 до 12. Введите номер месяца");
            }
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