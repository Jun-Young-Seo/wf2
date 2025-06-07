package kr.ac.hansung.cse.hellospringdatajpa.exception;

public class UserNameNotFoundException extends RuntimeException {
    public UserNameNotFoundException(String userName) {

        super("Email: " + userName + " not found");
    }
}
