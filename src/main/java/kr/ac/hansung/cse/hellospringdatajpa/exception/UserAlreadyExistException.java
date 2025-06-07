package kr.ac.hansung.cse.hellospringdatajpa.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super(email+" already existed");
    }
}
