package service;

import dao.AccountDAO;
import model.Account;

import java.util.List;
import java.util.Scanner;

public class AuthenService {
    private final AccountDAO accountDAO = new AccountDAO();
    private final List<Account> accountList = accountDAO.getAll();
    private final Account account = new Account();
    private final Scanner in = new Scanner(System.in);
    private boolean flag = false;

    public boolean login(String userName, String password){
        Account account = accountDAO.getByUserNameAndPassword(userName, password);
        return account != null;
    }
}
