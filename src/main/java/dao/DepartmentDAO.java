package dao;

import connection.MyConnection;
import model.Department;
import model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    public List<Department> getAll() {

        final String sql = "SELECT * FROM departments ";

        List<Department> departmentList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Department d = new Department();
                d.setDepartmentId(rs.getInt("DepartmentId"));
                d.setDepartmentName(rs.getString("DepartmentName"));
                d.setManagerId(rs.getInt("ManagerId"));
                departmentList.add(d);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return departmentList;
    }

    public Department getById(int id) {
        Department department = null;
        try {

            Connection conn = MyConnection.getConnection();

            String sql = "SELECT * FROM departments WHERE DepartmentId = " + id + " LIMIT 1";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                department = new Department();
                department.setDepartmentId(rs.getInt("DepartmentId"));
                department.setDepartmentName(rs.getString("DepartmentName"));
                department.setManagerId(rs.getInt("ManagerId"));
            }
            // Dong tai nguyen
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }

    public void insert(Department d) {
        String sql = String.format("INSERT INTO departments VALUES ('%d','%s','%d')",
                d.getDepartmentId(), d.getDepartmentName(), d.getManagerId());
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

    public void update(Department d, int id) {
        Department tmp = getById(id);
        if (tmp == null) {
            throw new RuntimeException("Bộ phận không tồn tại!");
        }
        final String sql = String.format("UPDATE departments SET " +
                        " `DepartmentName` = '%s'," +
                        " `ManagerId` = '%d' " +
                        "  WHERE `DepartmentId` = '%d'",
                d.getDepartmentName(), d.getManagerId(), id);
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
        Department d = getById(id);
        if (d == null) {
            throw new RuntimeException("Bộ phận không tồn tại!");
        }

        final String sql = "DELETE FROM `departments` WHERE  `DepartmentId` =  '"+id+"' ";
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


    // update manager
    public void updateManager(int managerId, int departmentId) {
        String sql = "" ;
        if (managerId == 0){
            sql = "UPDATE departments SET `ManagerID`= NULL WHERE `DepartmentID` = "+departmentId+"";
        }else {
            sql = "UPDATE departments SET `ManagerID`= "+managerId+" WHERE `DepartmentID` = "+departmentId+"";
        }
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

    public Department getByDeparmentId(int id) {
        Department department = null;
        try {

            Connection conn = MyConnection.getConnection();

            String sql = " SELECT d.DepartmentID as DepartmentID, d.ManagerID as ManagerID" +
                    " FROM employees e" +
                    " INNER JOIN departments d " +
                    " ON e.DepartmentID = d.DepartmentID" +
                    " AND e.EmployeeID = d.ManagerID" +
                    " WHERE EmployeeID = "+id+"";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                department = new Department();
                department.setDepartmentId(rs.getInt("DepartmentID"));
                department.setManagerId(rs.getInt("ManagerID"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }
}
