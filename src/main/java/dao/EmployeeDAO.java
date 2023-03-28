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

        final String sql = "SELECT * FROM employees ";

        List<Employee> employeeList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeID(rs.getInt("EmployeeID"));
                e.setFullName(rs.getString("FullName"));
                e.setPhone(rs.getString("Phone"));
                e.setEmail(rs.getString("Email"));
                e.setDateOfBirth(rs.getString("DateOfBirth"));
                e.setGender(rs.getString("Gender"));
                e.setAddress(rs.getString("Address"));
                e.setPosition(rs.getString("Position"));
                e.setSalary(rs.getInt("Salary"));
                e.setDepartmentID(rs.getInt("DepartmentID"));
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

    public Employee getById(int id) {
        Employee employee = null;
        try {

            Connection conn = MyConnection.getConnection();

            String sql = "SELECT * FROM employees WHERE EmployeeID = " + id + " LIMIT 1";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                employee = new Employee();
                employee.setEmployeeID(rs.getInt("EmployeeID"));
                employee.setFullName(rs.getString("FullName"));
                employee.setPhone(rs.getString("Phone"));
                employee.setEmail(rs.getString("Email"));
                employee.setDateOfBirth(rs.getString("DateOfBirth"));
                employee.setGender(rs.getString("Gender"));
                employee.setAddress(rs.getString("Address"));
                employee.setPosition(rs.getString("Position"));
                employee.setSalary(rs.getInt("Salary"));
                employee.setDepartmentID(rs.getInt("DepartmentID"));
            }
            // Dong tai nguyen
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void insert(Employee e) {
        String sql = String.format("INSERT  INTO employees VALUES ('%s','%s','%s','%s','%s','%s','%s','%d',NULL,NULL)",
                e.getEmployeeID(), e.getFullName(), e.getPhone(),e.getEmail(), e.getDateOfBirth(),
                e.getGender(),e.getAddress(), e.getSalary(),e.getPosition(), e.getDepartmentID());
        try{
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Thêm thất bại");
            }

            stmt.close();
            conn.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void update(Employee e, int id) {
        Employee tmp = getById(id);
        if (tmp == null) {
            throw new RuntimeException("Nhân viên không tồn tại!");
        }
        final String sql = String.format("UPDATE employees SET " +
                        "`FullName` = '%s'," +
                        "`Phone` = '%s'," +
                        "`Email` = '%s'," +
                        "`DateOfBirth` = '%s'," +
                        "`Gender` = '%s'," +
                        "`Address` = '%s'," +
                        "`Salary` = %d," +
                        "`Position`= NULL, " +
                        "`DepartmentID`= NULL " +
                        " WHERE `EmployeeID` = "+id+"",
                e.getFullName(), e.getPhone(),e.getEmail(), e.getDateOfBirth(),
                e.getGender(),e.getAddress(), e.getSalary(),e.getPosition(), e.getDepartmentID());
        try {

            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }

            stmt.close();
            conn.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void delete(int id) {
        Employee e = getById(id);
        if (e == null) {
            throw new RuntimeException("Nhân viên không tồn tại!");
        }

        final String sql = "DELETE FROM employees WHERE  EmployeeID =  "+id+" ";
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void updateDepartment(Employee e, int id) {
        Employee tmp = getById(id);
        if (tmp == null) {
            throw new RuntimeException("Nhân viên không tồn tại!");
        }
        final String sql = String.format("UPDATE employees SET " +
                "`Position`= '%s', " +
                "`DepartmentID`= '%d' " +
                " WHERE `EmployeeID` = "+id+"", e.getPosition(), e.getDepartmentID());
        try {

            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }

            stmt.close();
            conn.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void deleteEmpFromDep(int id) {
        Employee tmp = getById(id);
        if (tmp == null) {
            throw new RuntimeException("Nhân viên không tồn tại!");
        }
        final String sql = "UPDATE employees SET " +
                "`Position`= NULL, " +
                "`DepartmentID`= NULL " +
                " WHERE `EmployeeID` = "+id+"";
        try {

            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }

            stmt.close();
            conn.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
