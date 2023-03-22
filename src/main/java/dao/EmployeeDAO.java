package dao;

import connection.MyConnection;
import model.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM employees";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployee_id(rs.getLong("id"));
                e.setFullname(rs.getString("fullname"));
                e.setBirthday(rs.getString("birthday"));
                e.setAddress(rs.getString("address"));
                e.setGender(rs.getString("gender"));
                e.setPhone(rs.getString("phone"));
                e.setSalary(rs.getString("salary"));
                e.setEmail(rs.getString("email"));
                e.setPosition(rs.getString("position"));
                e.setDepartment_id(rs.getInt("department_id"));
                employeeList.add(e);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void insert(Employee e) {
        final String sql = String.format("INSERT INTO employees VALUES (NULL,'%s','%s','%s','%s','%s','%s','%s','%s','%d')",
                e.getFullname(), e.getBirthday(), e.getAddress(), e.getGender(), e.getPhone(), e.getSalary(),e.getEmail(),e.getPosition(), e.getDepartment_id()
        );

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            int rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Thêm thất bại!");
            }
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Employee getById(long id) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM employees WHERE id = " + id;

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            Employee e = null;
            if (rs.next()) {
                e = new Employee();
                e.setEmployee_id(rs.getLong("id"));
                e.setFullname(rs.getString("fullname"));
                e.setBirthday(rs.getString("birthday"));
                e.setAddress(rs.getString("address"));
                e.setGender(rs.getString("gender"));
                e.setPhone(rs.getString("phone"));
                e.setSalary(rs.getString("salary"));
                e.setEmail(rs.getString("email"));
                e.setPosition(rs.getString("position"));
                e.setDepartment_id(rs.getInt("department_id"));
            }
            rs.close();
            stmt.close();
            conn.close();
            return e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(Employee e, long id) {
        Employee tmp = getById(id);
        if(tmp == null){
            System.out.println("Không tồn tại nhân viên có id = " + id);
            return;
        }
        final String sql = String.format("UPDATE employees SET `full_name`='%s',`birthday`='%s',`address`='%s',`gender`='%s',`phone`='%s',`salary`='%s',`email`='%s',`position`='%s' WHERE `employee_id`='%d' " ,
                e.getFullname(), e.getBirthday(), e.getAddress(), e.getGender(), e.getPhone(), e.getSalary(), e.getEmail(),e.getPosition(), id
        );

        System.out.println(sql);
        try{
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void delete(long id) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "DELETE FROM employees WHERE id = " + id;

            Statement stmt = conn.createStatement();

            long rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
