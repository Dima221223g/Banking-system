import java.util.Arrays;
import java.util.Scanner;

public class Bank {
    Scanner input = new Scanner(System.in);
    User[] users = new User[3];
    int cardNo = 4;
    int size = 3;

    public Bank() {
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

    public void login() {
        System.out.print("Пожалуйста, введите номер карты:");
        int no = input.nextInt();
        System.out.print("Пожалуйста, введите пароль (8 - 16 цифр):");
        String pwd = input.next();
        User check = loginCheck(no, pwd);

        if (check != null) {
            this.showMenu(check);
        } else {
            System.out.println("Неверное имя пользователя или пароль. Пожалуйста, введите заново.");
            this.login();
        }
    }

    public void register() {
        System.out.print("Пожалуйста, введите свое имя:");
        String userName = input.next();
        String identity;
        do {
            System.out.print("Пожалуйста, введите идентификационный номер:");
            identity = input.next();
        } while (identity.length() != 18);
        String phone;
        do {
            System.out.print("Пожалуйста, введите предварительно сохраненный номер мобильного:");
            phone = input.next();
        } while (phone.length() != 11);
        String passWord;
        do {
            System.out.print("Пожалуйста, введите ваш пароль:");
            passWord = input.next();
        } while (passWord.length() < 8 || passWord.length() > 16);
        double money;
        do {
            System.out.print("Пожалуйста, введите сумму депозита (больше или равную 100 рублей):");
            money = input.nextInt();
        } while (money < 100);

        User user = new User();
        user.setCardNo(cardNo);
        user.setUserName(userName);
        user.setIdentity(identity);
        user.setPhone(phone);
        user.setPassWord(passWord);
        user.setBalance(money);
        System.out.println("Счет успешно открыт. Номер вашей карты:" + cardNo);
        cardNo++;

        if (size > (users.length - 1)) {
            User[] newUsers = Arrays.copyOf(users, size + 1);
            users = newUsers;
            users[size] = user;
        }
        size++;
    }

    public User loginCheck(int no, String pwd) {
        for (int i = 0; i < size; i++) {
            if (users[i] != null) {
                if ((no == users[i].getCardNo() && pwd.equals(users[i].getPassWord()))) {
                    return users[i];
                }
            }
        }
        return null;
    }

    public User transferCheck(int no) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (no == users[i].getCardNo()) {
                    return users[i];
                }
            }
        }
        return null;
    }

    public void welcomeMenu() {
        System.out.println("------------ Добро пожаловать в систему самообслуживания банкоматов SberBank ------------");
        System.out.println("--------------------------- 1. Войти 2. Открыть счет 0. Выйти ---------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.print("Пожалуйста, введите код операции:");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                this.login();
                break;
            case 2:
                this.register();
                this.welcomeMenu();
                break;
            case 0:
                exit();
            default:
                System.out.println("Ошибка ввода. Пожалуйста, введите заново.");
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void showMenu(User user) {
        int choice;
        do {
            System.out.println(
                    "------------ Добро пожаловать в систему самообслуживания банкоматов SberBank ------------");
            System.out.println(
                    "-- 1. Пополнить счет 2. Снять деньги 3. Перевод 4. Проверить баланс 5. Изменить пароль --");
            System.out.println(
                    "-- 6. Изменить номер мобильного телефона 7. Отменить учетную запись 0. Выйти ------------");
            System.out.println(
                    "-----------------------------------------------------------------------------------------");
            System.out.print("Пожалуйста, введите код операции:");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    this.deposit(user);
                    break;
                case 2:
                    this.withdraw(user);
                    break;
                case 3:
                    this.transfer(user);
                    break;
                case 4:
                    this.inquire(user);
                    break;
                case 5:
                    this.changePwd(user);
                    break;
                case 6:
                    this.changePhone(user);
                    break;
                case 7:
                    this.cancel(user);
                    this.welcomeMenu();
                    break;
                case 0:
                    System.out.println("Спасибо за использование.");
                    this.welcomeMenu();
                default:
                    System.out.println("Ошибка ввода. Пожалуйста, введите заново.");
            }
        } while (true);
    }

    public void withdraw(User user) {
        System.out.print("Пожалуйста, введите сумму вывода:");
        double money = input.nextDouble();
        if (money <= user.getBalance()) {
            user.setBalance(user.getBalance() - money);
            System.out.println("Вывод средств успешен. Текущий баланс: " + user.getBalance() + " рублей.");
        } else {
            System.out.println("Недостаточный баланс.");
        }
    }

    public void deposit(User user) {
        System.out.print("Пожалуйста, введите сумму депозита:");
        double money = input.nextDouble();
        user.setBalance(user.getBalance() + money);
        System.out.println("Удачный депозит.");
    }

    public void transfer(User user) {
        System.out.print("Пожалуйста, введите номер карты:");
        int no = input.nextInt();
        User check = transferCheck(no);

        if (check != null) {
            System.out.print("Пожалуйста, введите сумму перевода:");
            Double money = input.nextDouble();
            if (user.getBalance() > money) {
                user.setBalance(user.getBalance() - money);
                for (int i = 0; i < size; i++) {
                    if (users[i].getCardNo() == no) {
                        users[i].setBalance(users[i].getBalance() + money);
                    }
                }
                System.out.println("Перевод прошел успешно.");
            } else {
                System.out.println("Недостаточный баланс.");
            }
        } else {
            System.out.println("Введен неправильный номер карты.");
        }
    }

    public void inquire(User user) {
        System.out.println("Остаток на счете: " + user.getBalance() + " рублей.");
    }

    public void changePwd(User user) {
        String newPwd1;
        do {
            System.out.print("Пожалуйста, введите новый пароль из 8 - 16 цифр:");
            newPwd1 = input.next();
        } while (newPwd1.length() < 8 || newPwd1.length() > 16);
        System.out.print("Пожалуйста, введите новый пароль ещё раз:");
        String newPwd2 = input.next();
        if (newPwd1.equals(newPwd2)) {
            user.setPassWord(newPwd2);
            System.out.println("Успешно изменено.");
        } else {
            System.out.println("Ошибка. Две записи пароля разные.");
        }
    }

    public void changePhone(User user) {
        System.out.print("Пожалуйста, введите правильный оригинальный зарезервированный номер мобильного телефона:");
        String oldPhone = input.next();
        if (oldPhone.equals(user.getPhone())) {
            String newPhone;
            do {
                System.out.print("Пожалуйста,введите правильный новый номер телефона:");
                newPhone = input.next();
            } while (newPhone.length() != 11);
            user.setPhone(newPhone);
            System.out.println("Зарезервированный номер телефона успешно изменен.");
        } else {
            System.out.println("Исходный зарезервированный номер мобильного телефона неверен.");
        }
    }

    public void cancel(User user) {
        int index = size + 1;
        for (int i = 0; i < size; i++) {
            if (users[i] == user) {
                index = i;
                break;
            }
        }

        for (int i = index; i < size - 1; i++) {
            users[i] = users[i + 1];
        }
        size--;
        System.out.println("Выход выполнен успешно.");
    }
}
