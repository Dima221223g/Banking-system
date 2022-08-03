import java.util.Scanner;

public class Bigdata {
    Scanner input = new Scanner(System.in);
    String syspwd = "20f2320o072<90s627";
    String qwerty = " ";
    int qwert1 = 0;
    int size = 3;
    public int cardNo;
    public String identity;
    public String userName;
    public String passWord;
    public String phone;
    public double balance;

    public void systemMenu() {
        System.out.println("---------------------------- Добро пожаловать в System Menu -----------------------------");
        System.out.println("---------------------------------- Введите код доступа ----------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        qwerty = input.next();
        if (qwerty.equals(syspwd)) {
            System.out.println(
                    "-------------------------------- Выполнен вход в систему --------------------------------");
            this.bigdata();
            this.System();
        } else {
            System.out.println(
                    "-------------------------------- Ошибка. Доступ запрещен. -------------------------------");
        }
    }

    public void System() {
        int x = 1;
        do {
            System.out.println(
                    "------------------------ 1. Обновить данные 0. Выход из системы -------------------------");
            qwert1 = input.nextInt();
            switch (qwert1) {
                case 1:
                    this.bigdata();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println(
                            "----------------------- Ошибка ввода. Пожалуйста, введите заново ------------------------");
            }
        } while (x != 0);
    }

    int no1 = 0;
    Bank bank = new Bank();

    public void bigdata() {

        no1 = input.nextInt();
        switch (no1) {
            case 0:
                System.out.println();
        }

        User[] users = new User[1000000000];
        User user = new User();
        user.setCardNo(1);
        user.setIdentity("131000199811111234");
        user.setUserName("Андрей Михайлов");
        user.setPassWord("12345678");
        user.setPhone("12345670000");
        user.setBalance(1000.00);
        users[0] = user;

        users[1] = new User();
        users[1].setCardNo(2);
        users[1].setIdentity("131000111111114567");
        users[1].setUserName("Мaленький король");
        users[1].setPassWord("12345678");
        users[1].setPhone("12345670001");
        users[2] = new User(3, "131000199811115678", "Григорий Смирнов", "12345678", "12345670002", 3000.00);

    }

}
