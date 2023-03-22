package model;

public class Employee {
    private long employee_id;
    private String fullname;
    private String birthday;
    private String address;
    private String gender;
    private String phone;
    private String salary;
    private  String email;
    private  String position;
    private long department_id;

    public Employee() {
    }

    public Employee(long employee_id, String fullname, String birthday, String address, String gender, String phone, String salary, String email, String position, long department_id) {
        this.employee_id = employee_id;
        this.fullname = fullname;
        this.birthday = birthday;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
        this.salary = salary;
        this.email = email;
        this.position = position;
        this.department_id = department_id;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", fullname='" + fullname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", salary='" + salary + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", department_id=" + department_id +
                '}';
    }
}
