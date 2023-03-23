package service;

import dao.DepartmentDAO;
import model.Department;

public class DepartmentService {
    private final DepartmentDAO departmentDAO = new DepartmentDAO();
    public void showAllDepartment(){
        System.out.println(departmentDAO.getAll());
    }
    public void insertDepartment(Department department){
        departmentDAO.insert(department);
    }

    public void updateDepartment(Department department, long id){
        departmentDAO.update(department, id);
    }
    public void deleteDepartment(long id){
        departmentDAO.delete(id);
    }
}
