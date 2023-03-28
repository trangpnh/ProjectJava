import dao.EmployeeDAO;
import model.Department;
import model.Employee;
import service.AuthenService;
import service.DepartmentService;
import service.EmployeeService;
import ui.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    static AuthenService authenService = new AuthenService();
    static EmployeeService employeesService = new EmployeeService();
    static DepartmentService departmentService = new DepartmentService();
    static UserInterface ui = new UserInterface();
    static Scanner in = new Scanner(System.in);
    static int option = -1;
    public static void main(String[] args) {
        checkLogin();
    }
    private static void checkLogin(){
        System.out.println("-----------------ĐĂNG NHẬP-----------------");
        int count = 3;
        boolean isLoginSuccess = false;
        while(count != 0){
            count = count - 1;
            if(!isLoginSuccess){
                System.out.print("- Username: ");
                String username = in.nextLine();
                System.out.print("- Password: ");
                String password = in.nextLine();
                isLoginSuccess = authenService.login(username, password);
            }
            if(count != 0 && !isLoginSuccess){
                System.out.printf("Bạn còn %d lần nhập !\n", count);
            }
        }
        if(!isLoginSuccess){
            System.out.println("\nĐăng nhập thất bại");
            System.exit(0);
        }
        System.out.println("Đăng nhập thành công");
        handleEmployeesMenu();
    }
    private static void handleEmployeesMenu(){
        do {
            ui.showEmployeesMenu();
            inputMenu(14);
            switch (option) {
                case 1 -> employeesService.showEmployees();
                case 2 -> employeesService.insertEmployee();
                case 3 -> employeesService.updateEmployee();
                case 4 -> employeesService.deleteEmployee();
                case 5 -> employeesService.getEmployeeById();
                case 6 -> departmentService.showDepartments();
                case 7 -> departmentService.insertDepartment();
                case 8 -> departmentService.updateDepartment();
                case 9 -> departmentService.deleteDepartment();
                case 10 -> employeesService.insertEmployeeToDepartment();
                case 11 -> employeesService.deleteEmployeeFromDepartment();
                case 12 -> employeesService.tranferDepartment();
                case 13 -> employeesService.caculateTax();
            }
        }
        while (option != 0);
        in.close();
    }
    private static void inputMenu(int max) {
        boolean isCheck = false;
        System.out.print("Nhập lựa chọn: ");
        try {
            option = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println("Sai định dạng");
            isCheck = true;
        }
        if (!isCheck) {
            if (option == 0) {
                System.out.println("Thoát");
            }else if (option > max){
                System.out.println("Không hợp lệ");
            }

        }
    }
}
