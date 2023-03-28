package service;


import dao.DepartmentDAO;
import dao.EmployeeDAO;
import model.Department;
import model.Employee;
import util.Util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {
    private EmployeeDAO  employeeDAO = new EmployeeDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private Employee  employee = new Employee();
    private List<Employee> employeeList = new ArrayList<>();
    private final DepartmentService departmentService = new DepartmentService();
    private static final Scanner in = new Scanner(System.in);
    private static final Util util = new Util();
    private boolean flag = false;
    public void showEmployees(){
        employeeList = employeeDAO.getAll();
        String leftAlignFormat = "| %-12d | %-20s | %-11s  | %-12s  | %-18s  |  %-13s | %-19s | %-10d |%n";
        System.out.format("+--------------+----------------------+--------------+---------------+---------------------+----------------+---------------------+------------+%n");
        System.out.format("| Mã nhân viên |     Tên nhân viên    |  Giới tính   |   Ngày sinh   |       Địa chỉ       |  Số điện thoại |        Email        |    Lương   |%n");
        System.out.format("+--------------+----------------------+--------------+---------------+---------------------+----------------+---------------------+------------+%n");
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.format(leftAlignFormat, employeeList.get(i).getEmployeeID(),employeeList.get(i).getFullName(),
                    employeeList.get(i).getGender(), employeeList.get(i).getDateOfBirth(),employeeList.get(i).getAddress(),
                    employeeList.get(i).getPhone(),employeeList.get(i).getEmail(),employeeList.get(i).getSalary());
        }
        System.out.format("+--------------+----------------------+--------------+---------------+---------------------+----------------+---------------------+------------+%n");
    }
    public void insertEmployee(){
        System.out.print("\tNhập id: ");
        int id;
        try {
            id = Integer.parseInt(in.nextLine());
            if (util.isCheckIdEmp(id)){
                System.out.println("Id đã tồn tại");
                return;
            }
            if (inputData(employee)){
                employee.setEmployeeID(id);
                employeeDAO.insert(employee);
                System.out.println("Thêm thành công");
            }
        }catch (Exception e){
            System.out.println("Id không đúng định dạng");
        }

    }
    public void updateEmployee(){
        System.out.print("\tNhập id: ");
        int id = Integer.parseInt(in.nextLine());
        if (!util.isCheckIdEmp(id)){
            System.out.println("Không tìm thấy id");
            return;
        };
        if (inputData(employee)){
            employeeDAO.update(employee, id);
            System.out.println("Cập nhật thành công");
        }
    }
    public void deleteEmployee(){
        System.out.print("\tNhập id cần xóa: ");
        int id = Integer.parseInt(in.nextLine());
        if (!util.isCheckIdEmp(id)){
            System.out.println("Không tìm thấy id");
            return;
        };
        employeeDAO.delete(id);
        System.out.println("* Xóa thành công *");
    }
    public void getEmployeeById(){
        System.out.print("Nhập id cần tìm: ");
        int id = in.nextInt();
        if (!util.isCheckIdEmp(id)){
            System.out.println("<!> Không tìm thấy nhân viên nào <!>");
            return;
        };
        System.out.println(employeeDAO.getById(id));
    }
    public void insertEmployeeToDepartment(){
        System.out.print("\tNhập id nhân viên: ");
        int employeeId = in.nextInt();
        if (!util.isCheckIdEmp(employeeId)){
            System.out.println("Không tìm thấy nhân viên nào");
            return;
        };
        if (!util.isCheckDepIdEmployee(employeeId)){
            System.out.println("Nhân viên này đã có phòng ban");
            return;
        }
        System.out.print("\tNhập tên công việc: ");
        String position = in.next();
        employee.setPosition(position);
        departmentService.showDepartments();
        System.out.print("\tNhập mã bộ phận: ");
        int deparmentId = in.nextInt();

        if (!util.isCheckDepId(deparmentId)){
            System.out.println("Không tìm thấy mã bộ phận nào");
            return;
        }
        employee.setDepartmentID(deparmentId);
        // update
        employeeDAO.updateDepartment(employee, employeeId);
        System.out.println("Thêm nhân viên vào bộ phận thành công");
        if (!util.isCheckManagement(employeeId,deparmentId)){
            departmentDAO.updateManager(employeeId, deparmentId);
            System.out.println("Nhân viên này được xét lên quản lý");
        }
    }
    public void tranferDepartment(){
        System.out.print("\tNhập id nhân viên: ");
        int employeeId = in.nextInt();
        if (!util.isCheckIdEmp(employeeId)){
            System.out.println("<!> Không tìm thấy nhân viên nào <!>");
            return;
        }
        System.out.print("\tNhập tên công việc: ");
        String position = in.next();
        employee.setPosition(position);
        departmentService.showDepartments();
        System.out.print("\tNhập mã bộ phận muốn chuyển: ");
        int deparmentId = in.nextInt();
        if (!util.isCheckDepId(deparmentId)){
            System.out.println("<!> Không tìm thấy mã bộ phận nào <!>");
            return;
        }
        employee.setDepartmentID(deparmentId);
        Department departmentDepID = departmentDAO.getByDeparmentId(employeeId);
        if (departmentDepID.getManagerId() == employeeId){
            departmentDAO.updateManager(0, departmentDepID.getDepartmentId());
        }
        employeeDAO.updateDepartment(employee, employeeId);
        System.out.println("Chuyển bộ phận thành công");
        if (!util.isCheckManagement(employeeId, deparmentId)){
            departmentDAO.updateManager(employeeId, deparmentId);
            System.out.println("Nhân viên này được xét lên quản lý");
        }
    }
    public void deleteEmployeeFromDepartment(){
        System.out.print("\tNhập id nhân viên cần xóa: ");
        int employeeId = in.nextInt();
        if (!util.isCheckIdEmp(employeeId)){
            System.out.println("Không tìm thấy nhân viên nào");
            return;
        }
        Department departmentDepID = departmentDAO.getByDeparmentId(employeeId);
        if (departmentDepID.getManagerId() == employeeId){
            departmentDAO.updateManager(0, departmentDepID.getDepartmentId());
            System.out.println("* Nhân viên này không còn là quản lý *");
        }
        employeeDAO.deleteEmpFromDep(employeeId);
        System.out.println("* Xóa khỏi bộ phận thành công *");
    }
    public void caculateTax(){
        System.out.print("\tNhập id cần tìm: ");
        int id = in.nextInt();
        if (!util.isCheckIdEmp(id)){
            System.out.println("<!> Không tìm thấy nhân viên nào <!>");
            return;
        }

        String leftAlignFormat = "| %-12d | %-20s | %-15d |%n";
        System.out.format("+--------------+----------------------+-----------------+%n");
        System.out.format("| Mã nhân viên |     Tên nhân viên    |       Lương     |%n");
        System.out.format("+--------------+----------------------+-----------------+%n");
        Employee employee = employeeDAO.getById(id);
        System.out.format(leftAlignFormat, employee.getEmployeeID(),
                employee.getFullName(),employeeDAO.getById(id).getSalary());
        System.out.format("+--------------+----------------------+-----------------+%n");

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        System.out.print("\tNhập lương đóng bảo hiểm: ");
        int luongBh = in.nextInt();
        System.out.print("\tNhập số người phụ thuộc: ");
        int soNguoiPT = in.nextInt();
        double BHBB = (luongBh * 0.08) + (luongBh * 0.015) + (luongBh * 0.01);
        System.out.println("\t-Bảo hiểm bắt buộc = "+formatter.format(BHBB)+" VNĐ");
        int GTBT = 11000000;
        System.out.println("\t-Giảm trừ bản thân = "+formatter.format(GTBT)+" VNĐ");
        int GTNPT = soNguoiPT * 4400000;
        System.out.println("\t-Giảm trừ người phụ thuộc = "+formatter.format(GTNPT)+" VNĐ");
        int luong = employeeDAO.getById(id).getSalary();
        double TNTT = luong - BHBB - GTBT - GTNPT;
        System.out.println("\t-Thu nhập tính thuế = "+formatter.format(TNTT)+" VNĐ");
        double TTNCN;
        if (luong <= 5000000 ){
            TTNCN = TNTT * 0.05;
        }else if (luong <= 10000000){
            TTNCN = (TNTT * 0.1) - 0.25;
        }else if (luong <= 18000000){
            TTNCN = (TNTT * 0.15) - 0.75;
        }else if (luong <= 32000000){
            TTNCN = (TNTT * 0.2) - 1.65;
        }else if (luong <= 52000000){
            TTNCN = (TNTT * 0.25) - 3.25;
        }else if (luong <= 80000000){
            TTNCN = (TNTT * 0.3) - 5.85;
        }else {
            TTNCN = (TNTT * 0.35) - 9.85;
        }
        if (luong > 0){
            System.out.println("\t-Thuế thu nhập cá nhân phải nộp = "+formatter.format(TTNCN)+" VNĐ");
        }else {
            System.out.println("\t-Thuế thu nhập cá nhân phải nộp = 0 VNĐ");
        }
    }
    public boolean inputData(Employee employee){
        System.out.print("\tNhập họ tên: ");
        String fullName = in.nextLine();
        if (fullName.isEmpty()){
            System.out.println("Tên không được để trống");
            return false;
        }
        employee.setFullName(fullName);
        //
        System.out.print("\tNhập số điện thoại: ");
        String phone = in.nextLine();
        if (!util.isValidPhone(phone) && phone.length() != 10){
            System.out.println("SĐT không đúng định dạng");
            return false;
        }
        if (util.comparePhone(phone)){
            System.out.println("SĐT đã tồn tại");
            return false;
        }
        employee.setPhone(phone);
        //
        System.out.print("\tNhập email: ");
        String email = in.nextLine();
        if (!util.isValidEmail(email)){
            System.out.println("Email không hợp lệ");
            return false;
        }
        if (util.compareEmail(email)){
            System.out.println("Email đã tồn tại");
            return false;
        }
        employee.setEmail(email);
        //
        System.out.print("\tNhập ngày/tháng/năm: ");
        String date = in.nextLine();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(date);
        }
        catch (ParseException e) {
            System.out.println("Ngày/tháng/năm không đúng định dạng");
            return false;
        }
        employee.setDateOfBirth(date);
        //
        System.out.print("\tNhập giới tính: ");
        String gender = in.nextLine();
        if (!util.isCheckGender(gender)){
            System.out.println("Giới tính không chính xác");
            return false;
        }
        employee.setGender(gender);
        //
        System.out.print("\tNhập địa chỉ: ");
        String address = in.nextLine();
        if (address == null){
            System.out.println("Địa chỉ không được để rỗng");
            return false;
        }
        employee.setAddress(address);
        //
        System.out.print("\tNhập lương: ");
        int salary;
        try {
            salary = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println("Lương không đúng định dạng");
            return false;
        }
        employee.setSalary(salary);
        return true;
    }
}
