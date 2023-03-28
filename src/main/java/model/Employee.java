package model;

public class Employee {
    private int EmployeeID;
    private String FullName;
    private String Phone;
    private String Email;
    private String DateOfBirth;
    private String Gender;
    private String Address;
    private int Salary;
    private String Position;
    private int DepartmentID;


    public Employee() {
    }

    public Employee(int employeeID, String fullName, String phone, String email, String dateOfBirth, String gender, String address, String position, int salary, int departmentID) {
        EmployeeID = employeeID;
        FullName = fullName;
        Phone = phone;
        Email = email;
        DateOfBirth = dateOfBirth;
        Gender = gender;
        Address = address;
        Position = position;
        Salary = salary;
        DepartmentID = departmentID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int departmentID) {
        DepartmentID = departmentID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeID=" + EmployeeID +
                ", FullName='" + FullName + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Email='" + Email + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Address='" + Address + '\'' +
                ", Position='" + Position + '\'' +
                ", Salary=" + Salary +
                ", DepartmentID=" + DepartmentID +
                '}';
    }
}
