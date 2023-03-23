package service;


import dao.EmployeeDAO;
import model.Employee;

public class EmployeeService {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    public void showAllEmployee(){
        System.out.println(employeeDAO.getAll());
    }
    public void insertEmployee(Employee employee){
        employeeDAO.insert(employee);
    }

    public void updateEmployee(Employee employee, long id){
        employeeDAO.update(employee, id);
    }
    public void getByIDEmployee(long id){
        employeeDAO.getById(id);
    }
    public void deleteEmployee(long id){
        employeeDAO.delete(id);
    }
}
