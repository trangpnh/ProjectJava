package model;

public class Department {
    private int DepartmentId;
    private String DepartmentName;
    private int ManagerId;

    public Department() {
    }

    public Department(int departmentId, String departmentName, int managerId) {
        this.DepartmentId = departmentId;
        this.DepartmentName = departmentName;
        this.ManagerId = managerId;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.DepartmentName = departmentName;
    }

    public int getManagerId() {
        return ManagerId;
    }

    public void setManagerId(int managerId) {
        this.ManagerId = managerId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "department_id=" + DepartmentId +
                ", department_name='" + DepartmentName + '\'' +
                ", manager_id=" + ManagerId +
                '}';
    }
}
