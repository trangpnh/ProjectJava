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
        final String sql = "SELECT * FROM `departments`";

        List<Department> departmentList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Department dept = new Department();
                dept.setDepartment_id(rs.getLong("id"));
                dept.setDepartment_name(rs.getString("Department_name"));
                dept.setManager_id(rs.getLong("Manager_id"));
                departmentList.add(dept);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return departmentList;
    }

    public void insert(Department e) {
        final String sql = String.format("INSERT INTO departments VALUES (NULL,'%s','%d')",
                e.getDepartment_name(), e.getManager_id()
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
    public void update(Department e, long id) {
        Department dept = getById(id);
        if(dept == null){
            System.out.println("Không tồn tại nhân viên có id = " + id);
            return;
        }
        final String sql = String.format("UPDATE departments SET `department_name`='%s' 'manager_id' = '%d' WHERE `department_id`='%d' " ,
                e.getDepartment_name(), e.getManager_id(), id
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

    public Department getById(long id) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM departments WHERE id = " + id;

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            Department dept = null;
            if (rs.next()) {
                dept = new Department();
                dept.setDepartment_id(rs.getLong("id"));
                dept.setDepartment_name(rs.getString("Department_name"));
                dept.setManager_id(rs.getLong("Manager_id"));
            }
            rs.close();
            stmt.close();
            conn.close();
            return dept;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(long id) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "DELETE FROM dapartments WHERE id = " + id;

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
