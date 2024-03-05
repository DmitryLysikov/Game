import java.util.Random;
import java.util.Scanner;

public class Game {
    //создаем переменную которая хранит "фишку" Х
    private final char sign_X = 'X';
    //создаем переменную которая хранит "фишку" О
    private final char sign_O = 'O';
    //создаем переменную которая хранит точку, это грубо говоря наши места на игровом поле
    private final char sign_Empty = '.';
    //создаем переменную игрового поля
    private final char[][] table;
    //нужно для хода бота
    private final Random random;
    //нужно чтобы был ввод с клавиатуры
    private final Scanner scanner;

    //конструктор где мы инициализируем наш массив, то есть игровое поле, ну и рандом и сканер
    public Game() {
        this.table = new char[3][3];
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    //наша сама игра которая будет запускаться через майн
    public String PlayGame() {
        System.out.println("Добро пожаловать в игру. Как вас зовут?");
        String name = scanner.nextLine();
        //важно ввести большими буквами на англ, потому что когда будет сравнение символом другой формат записи не будет равен стандарту Х и О
        System.out.println(name + ", выбери за кого будешь играть(англ. символ): Х или О");
        //я тут так делаю потому что сканер не дает записать один символ и мы из строки вынимаем только первый символ
        char chip = scanner.next().charAt(0);
        //тут все зависит от выбора игроком фишки, если Х то он играет первый, если О то второй.
        if (chip == sign_X) {
            //выводим наше игровое поле
            InitTable();
            //бесконечный цикл нужен чтобы игра была непрерывна
            while (true) {
                //ход самого игрока, мы передаем в метод нашу фишку которую выбрал игрок
                MoveHuman(chip);
                //проверка на победу
                if (ChekIsWin(chip)) {
                    System.out.println("Ты выиграл!!!");
                    break;
                }
                //проверка на ничью
                if (IsTableFull()) {
                    System.out.println("Ничья!!!");
                    break;
                }
                //ход бота
                MoveBot(sign_O);
                //вывод нашего игрового поля так как уже были сделланы ходы
                PrintTable();
                if (ChekIsWin(sign_O)) {
                    System.out.println("Ты проиграл!!!");
                    break;
                }
                if (IsTableFull()) {
                    System.out.println("Ничья!!!");
                    break;
                }
            }
            //альтернативная версия все то же самое что и выше только меняется порядок хода игрока
        } else if (chip == sign_O) {
            InitTable();
            while (true) {
                MoveBot(sign_X);
                if (ChekIsWin(sign_X)) {
                    System.out.println("Ты проиграл!!!");
                    break;
                }
                if (IsTableFull()) {
                    System.out.println("Ничья!!!");
                    break;
                }
                //вывожу пустую строку так как дважды выводится поля и они сливаются
                System.out.println();
                PrintTable();
                MoveHuman(chip);
                if (ChekIsWin(chip)) {
                    System.out.println("Ты выиграл!!!");
                    break;
                }
                if (IsTableFull()) {
                    System.out.println("Ничья!!!");
                    break;
                }
            }
            //это проверка мало ли игрок введет что-то другое, кроме Х или О
        } else {
            System.out.println("Извините, но вы ввели что-то не так!!!!");
            return PlayGame();
        }
        PrintTable();
        return "Игра окончена!!!";
    }

    //метод заполнения поля и его сразу же вывод
    public void InitTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = sign_Empty;
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }

    //чисто вывод поля
    private void PrintTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }

    //метод хода игрока
    private void MoveHuman(char chip) {
        System.out.println("Введите координаты x и y, куда бы хотели поставить " + chip);
        System.out.print("Координаты Х (от 1 до 3): ");
        //отнимаю 1 так как в массиве начинается с 0 отсчет
        int x = scanner.nextInt() - 1;
        System.out.print("Координаты Y (от 1 до 3): ");
        int y = scanner.nextInt() - 1;
        //проверка на постановку фишки
        if (CheckForFreeSpace(x, y)) {
            table[x][y] = chip;
        } else {
            System.out.println("Занято или вы вышли за поле, попробуйте еще раз");
            PrintTable();
            MoveHuman(chip);
        }
    }

    //метод проверки куда поставить фишку
    private boolean CheckForFreeSpace(int x, int y) {
        if (x < 0 || y < 0 || x > 3 || y > 3) {
            return false;
        }
        return table[x][y] == sign_Empty;
    }

    // проверка на победу
    private boolean ChekIsWin(char chip) {
        for (int i = 0; i < table.length; i++) {
            if ((table[i][0] == chip && table[i][1] == chip && table[i][2] == chip) ||
                    (table[0][i] == chip && table[1][i] == chip && table[2][i] == chip)) {
                return true;
            } else if ((table[0][0] == chip && table[1][1] == chip && table[2][2] == chip) ||
                    (table[0][2] == chip && table[1][1] == chip && table[2][0] == chip)) {
                return true;

            }
        }
        return false;
    }

    //проверка на ничью
    private boolean IsTableFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (table[i][j] == sign_Empty)
                    return false;
        return true;
    }

    //ход бота все то же самое что и у игрока
    private void MoveBot(char chip) {
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        if (CheckForFreeSpace(x, y)) {
            table[x][y] = chip;
        } else {
            MoveBot(chip);
        }
    }
}
