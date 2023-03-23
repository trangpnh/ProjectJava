package ui;

public class UserInterface {
    public void showMainMenu(){
        System.out.println("---------------- M E N U ----------------");
        System.out.println("1. Quản lý thông tin phòng ban");
        System.out.println("2. Quản lý nhân viên");
        System.out.println("3. Đăng xuất");
        System.out.println("0. Thoát");
    }
    public void showDepartmentsMenu(){
        System.out.println("--------QUẢN LÝ THÔNG TIN PHÒNG BAN--------");
        System.out.println("1. Danh sách phòng ban");
        System.out.println("2. Thêm phòng ban mới");
        System.out.println("3. Cập nhật phòng ban");
        System.out.println("4. Xóa phòng ban theo mã");
        System.out.println("5. Quay lại");
        System.out.println("0. Thoát");
    }
    public void showEmployeesMenu() {
        System.out.println("--------QUẢN LÝ THÔNG TIN NHÂN VIÊN--------");
        System.out.println("1. Danh sách nhân viên");
        System.out.println("2. Thêm nhân viên mới");
        System.out.println("3. Cập nhật thông tin nhân viên");
        System.out.println("4. Xóa nhân viên theo mã");
        System.out.println("5. Tìm kiếm viên theo theo mã");
        System.out.println("6. Chuyển phòng ban cho một nhân viên");
        System.out.println("7. Tính thuế thu nhập cá nhân");
        System.out.println("8. Quay lại");
        System.out.println("0. Thoát");
    }
}
