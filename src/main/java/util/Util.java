package util;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import model.Department;
import model.Employee;

import java.util.List;
import java.util.regex.Pattern;

public class Util {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final DepartmentDAO departmentDAO = new DepartmentDAO();
    private final List<Department> departmentList = departmentDAO.getAll();
    private final List<Employee> employeeList = employeeDAO.getAll();
    public boolean isCheckIdEmp(int id){
        for (Employee empId : employeeList){
            if (empId.getEmployeeID() == id){
                return true;
            }
        }
        return false;
    }
    public boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern p = Pattern.compile(emailRegex);
        if (p.matcher(email).find()){
            return p.matcher(email).matches();
        }else {
            return false;
        }
    }

    public boolean isValidPhone(String phone)
    {
        String regex = "^(0)+([0-9]{9})$";
        Pattern p = Pattern.compile(regex);
        if (p.matcher(phone).find()){
            return p.matcher(phone).matches();
        }else {
            return false;
        }
    }

    public boolean compareEmail(String email){
        String [] inputEmail = email.split("@");
        String ipEmailHead = inputEmail[0];
        String ipEmailTail = inputEmail[1];

        for (Employee empEmail : employeeList){
            String [] outputEmail = empEmail.getEmail().split("@");
            String opEmailHead = outputEmail[0];
            String opEmailTail = outputEmail[1];
            return ipEmailHead.equalsIgnoreCase(opEmailHead) &&
                    ipEmailTail.equalsIgnoreCase(opEmailTail);
        }
        return false;
    }

    public boolean comparePhone(String phone){
        for (Employee empPhone : employeeList){
            if (empPhone.getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }
    public boolean isCheckGender(String gender){
        return gender.equalsIgnoreCase("Nam")
                || gender.equalsIgnoreCase("Nữ")
                || gender.equalsIgnoreCase("Khác");
    }

    public boolean isCheckDepIdEmployee(int id){
        Employee employee = employeeDAO.getById(id);
        for (int i = 0; i<= employeeList.size() ; i++){
            if (employee.getDepartmentID() == 0){
                return true;
            }
        }
        return false;
    }

    public boolean isCheckDepId(int id){
        for (Department depId : departmentList){
            if (depId.getDepartmentId() == id){
                return true;
            }
        }
        return false;
    }

    public boolean isCheckDepName(String name){
        for (Department depName : departmentList){
            if (depName.getDepartmentName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public boolean isCheckManagerId(int id){
        for (Department managerId : departmentList){
            if (managerId.getManagerId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean isCheckManagement( int employeeID, int deparmentId){
        Department department = departmentDAO.getById(deparmentId);
        for (int i = 0; i<departmentList.size(); i++){
            if (department.getManagerId() != 0 && department.getManagerId() == employeeID){
                return true;
            }
        }
        return false;
    }
}
