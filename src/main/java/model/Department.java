package model;

public class Department {
    private long department_id;
    private String department_name;
    private long manager_id;

    public Department() {
    }

    public Department(long department_id, String department_name, long manager_id) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.manager_id = manager_id;
    }

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public long getManager_id() {
        return manager_id;
    }

    public void setManager_id(long manager_id) {
        this.manager_id = manager_id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                ", manager_id=" + manager_id +
                '}';
    }
}
