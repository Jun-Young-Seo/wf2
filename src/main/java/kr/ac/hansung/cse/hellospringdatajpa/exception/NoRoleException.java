package kr.ac.hansung.cse.hellospringdatajpa.exception;

public class NoRoleException extends RuntimeException {
    public NoRoleException(String role) {
        super("no role : "+role);
    }
}
