import service.AuthenService;

import java.util.Scanner;

public class Application {
    private static boolean isLoginSuccess = false;

    // Khai bao service
    private static AuthenService authenService = new AuthenService();

    private static void showMenu(){
        System.out.println("---QUAN LY NHAN SU---");

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if(isLoginSuccess== false){
            // Dang nhap truoc
            System.out.print("Nhap username: ");
            String username = in.nextLine();
            System.out.print("Nhap password: ");
            String password = in.nextLine();

            // Kiem tra xem dang nhap dc ko?
            isLoginSuccess = authenService.login(username, password);
        }

        if(isLoginSuccess == false){
            System.out.println("Dang nhap that bai");
            System.exit(0);
        }

        // Dang nhap thanh cong
        showMenu();

        in.close();
    }
}
