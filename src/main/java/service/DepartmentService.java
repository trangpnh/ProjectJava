package service;

import dao.DepartmentDAO;
import model.Department;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentService {
    private final DepartmentDAO departmentDAO = new DepartmentDAO();
    private final Department department = new Department();
    private List<Department> departmentList = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);
    private static final Util util = new Util();

    private boolean flag = false;

    public void showDepartments(){
        departmentList = departmentDAO.getAll();
        String leftAlignFormat = "| %-11d | %-18s | %-9d  | %n";
        System.out.format("+-------------+--------------------+------------+%n");
        System.out.format("| Mã bộ phận       Tên bộ phận     Mã quản lý |%n");
        System.out.format("+-------------+--------------------+------------+%n");
        for (int i = 0; i < departmentList.size(); i++) {
            System.out.format(leftAlignFormat, departmentList.get(i).getDepartmentId(),
                    departmentList.get(i).getDepartmentName(),departmentList.get(i).getManagerId());
        }
        System.out.format("+-------------+--------------------+------------+%n");
    }

    public void insertDepartment(){
        System.out.print("\tNhập mã bộ phận: ");
        int id = Integer.parseInt(in.nextLine());
        if (util.isCheckDepId(id)){
            System.out.println("Id đã tồn tại");
            return;
        };
        department.setDepartmentId(id);

        System.out.print("\tNhập tên bộ phận: ");
        String name = in.nextLine();
        if (util.isCheckDepName(name)){
            System.out.println("Tên bộ phận đã tồn tại");
            return;
        }
        department.setDepartmentName(name);

        System.out.print("\tNhập mã quản lý: ");
        int managerId = Integer.parseInt(in.nextLine());
        if (util.isCheckManagerId(managerId)){
            System.out.println("Id đã tồn tại");
            return;
        };
        department.setManagerId(managerId);

        departmentDAO.insert(department);
        System.out.println("Thêm thành công");
    }

    public void updateDepartment(){
        showDepartments();
        System.out.print("\tNhập mã bộ phận cần sửa: ");
        int id = Integer.parseInt(in.nextLine());
        if (!util.isCheckDepId(id)){
            System.out.println("Không tìm thấy id");
            return;
        };

        System.out.print("\tNhập tên bộ phận: ");
        String name = in.nextLine();
        if (util.isCheckDepName(name)){
            System.out.println("Tên bộ phận đã tồn tại");
            return;
        }
        department.setDepartmentName(name);

        System.out.print("\tNhập mã quản lý: ");
        int managerId = Integer.parseInt(in.nextLine());
        if (util.isCheckManagerId(managerId)){
            System.out.println("Id đã tồn tại");
            return;
        };
        department.setManagerId(managerId);

        departmentDAO.update(department , id);
        System.out.println("Update thành công");
    }

    public void deleteDepartment(){
        showDepartments();
        System.out.print("\tNhập mã bộ phận cần xóa: ");
        int id = Integer.parseInt(in.nextLine());
        if (!util.isCheckDepId(id)){
            System.out.println("Không tìm thấy id");
            return;
        };
        departmentDAO.delete(id);
        System.out.println("Xóa thành công");
    }

    public void getDepartmentById(){
        System.out.print("Nhập id cần tìm: ");
        int id = in.nextInt();
        if (!util.isCheckDepId(id)){
            System.out.println("Không tìm thấy id");
            return;
        };
        System.out.println(departmentDAO.getById(id));
    }
}
