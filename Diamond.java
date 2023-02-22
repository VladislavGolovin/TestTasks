import java.util.Scanner;

/*
    Написать программу на языке Джава 8/11 или С# принимающую с консоли два параметра ширину
    и высоту ромба и затем печатающую ромб в текстовом режиме символами #.
    Размеры ромба могут быть любыми, наример
    5x5
    #
    # #
    # #
    # #
    #

    6x3
    #
    ##
    # #
    # #
    ##
    #

    Это значит что программа не обязательно печатает идеальный ромб,
    идеальным он будет только в случае одинаковых нечетных высоты и
    ширины.
    Входные параметры
    Высота: от 1 до 80
    Ширина: от 1 до 80

    public class Main {

    public static void main(String[] args)
    {
    // Ваш код получающий параметры с консоли
    ---
    Main.printDiamond(height, width);
    }
    private static void printDiamond(int height, int width)
    {
    ---
    Код печатающий ромб
    }
    }
    Дополнительные условия, требуется выполнить построение за один цикл, с
    минимумом переменных
 */

public class Diamond {
    
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите высоту ромба (1-80): ");
        int height = scan.nextInt();

        System.out.print("Введите ширину ромба(1-80): ");
        int width = scan.nextInt();

        scan.close();
        
        printDiamond(height, width);
    }


    private static void printDiamond(int height, int width) {

        // Стартовые параметры
        char symbol = '#';

        // Округляется вниз, так что аккуратнее с четными
        int centerHeight = ((height - 1) / 2);
        int centerWidth = ((width - 1) / 2);

        int i = 0;
        boolean invert = false;
        while (i >= 0) {
            // Проставляем пустую строку нужного размера
            StringBuilder builder = new StringBuilder(" ".repeat(width));
            if (i == 0 || i == height - 1) {
                // Крайние случаи, верхний и нижний углы ромба
                builder.insert(centerWidth, symbol);
            } else if (i == centerHeight) {
                // Крайние случаи, правый и левый углы ромба.
                // Также это точка, на которой мы начинаем зеркалить ромб, invert=true
                // В случае четного дублируем строку, смазывая угол
                builder.insert(0, symbol).insert(width - 1, symbol);
                if (height % 2 == 0) {
                    builder.append("\n")
                           .append(new StringBuilder(" ".repeat(width)).insert(0, symbol).insert(width - 1, symbol));
                }
                invert = true;
            } else {
                // Основная формула для расчета, по которой рисуем все что между углами
                int delta = Math.round((float)centerWidth * (float) i / (centerHeight));
                // int delta = i * width / height; на случай если экономим

                // Определяем шаг, на который мы отступаем от центра чтобы поставить символ
                int step = Math.abs(centerWidth - delta);
                // Проставляем символ сразу в два места - слева и справа от центра на определенный ранее шаг
                // Пишем получившуюся строку в билдер
                builder.insert(Math.abs(step), symbol)
                       .insert(Math.abs(width - step) - 1, symbol);
            }
            // Хитрое зеркалирование - обрати внимание что while ждет пока i не станет меньше нуля
            if (!invert) {
                i++;
            } else {
                i--;
            }
            // Отрисовываем строку 
            System.out.println(builder);
        }
    }
}