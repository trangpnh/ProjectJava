package service;

import dao.AccountDAO;
import model.Account;

public class AuthenService {
    private final AccountDAO accountDAO = new AccountDAO();

    public boolean login(String userName, String password){
        Account account = accountDAO.getByUserNameAndPassword(userName, password);
        return account != null;
    }

    public void showAllAccount(){
        System.out.println(accountDAO.getAll());
    }

    public void insertAccount(Account account){
        accountDAO.insert(account);
    }

    public void updateAccount(Account account, int id){
        accountDAO.update(account, id);
    }

    public void deleteAccount(int id){
        accountDAO.delete(id);
    }
}
