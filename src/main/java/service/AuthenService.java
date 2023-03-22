package service;

import dao.AccountDAO;
import model.Account;

public class AuthenService {
    private AccountDAO accountDAO = new AccountDAO();

    // Service ve viec dang nhap

    public boolean login(String userName, String password) {
        Account account = accountDAO.getByUserNameAndPassword(userName, password);
        if(account == null){
            return false;
        }
        // Dang nhap thanh cong
        return true;
    }
}
