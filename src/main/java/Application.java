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
    static EmployeeDAO employeeDAO = new EmployeeDAO();
    static List<Employee> employeeList = new ArrayList<>();
    static List<Department> departmentList = new ArrayList<>();
    static Employee employee = new Employee();
    static Department department = new Department();
    static AuthenService authenService = new AuthenService();
    static EmployeeService employeesService = new EmployeeService();
    static DepartmentService departmentService = new DepartmentService();

    static UserInterface ui = new UserInterface();

    static Scanner in = new Scanner(System.in);
    static int option = -1;

    // Main
    public static void main(String[] args) {
        checkLogin();
        handleMenu();

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
                System.out.println("Sai tên tài khoản hoặc mật khẩu\n");
                System.out.printf("Bạn còn %d lần nhập !\n", count);
            }
        }
        if(!isLoginSuccess){
            System.out.println("\năng nhập thất bại");
            System.exit(0);
        }
        System.out.println("Đăng nhập thành công");
    }

    private static void handleEmployeesManager(){
        do {
            ui.showEmployeesMenu();
            inputMenu(8);
            switch (option) {
                case 1 -> employeesService.showAllEmployee();
                case 2-> employeesService.insertEmployee(employee);
                case 3-> employeesService.updateEmployee(employee, employee.getEmployee_id());
                case 4-> employeesService.deleteEmployee(employee.getEmployee_id());
                case 5 -> employeesService.getByIDEmployee(employee.getEmployee_id());
                case 8 -> handleMenu();
            }

        }
        while (option != 0);
        in.close();
    }

    private static void handleDepartmentsManager(){
        do {
            ui.showDepartmentsMenu();
            inputMenu(5);
            switch (option) {
                case 1 -> departmentService.showAllDepartment();
                case 2 -> departmentService.insertDepartment(department);
                case 3 -> departmentService.updateDepartment(department, department.getDepartment_id());
                case 4 -> departmentService.deleteDepartment(department.getDepartment_id());
                case 5 -> handleMenu();
            }
        }
        while (option != 0);
        in.close();
    }

    private static void handleMenu(){
        do {
            ui.showMainMenu();
            inputMenu(3);
            switch (option) {
                case 1 -> handleDepartmentsManager();
                case 2 -> handleEmployeesManager();
                case 3 -> {
                    System.out.println("Đăng xuất thành công");
                    checkLogin();
                }
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
                System.exit(0);
            }else if (option > max){
                System.out.println("Không hợp lệ");
            }
        }
    }
}
