package kr.ac.hansung.cse.hellospringdatajpa.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
//역할분리를 위한 Dto
public class UserRegisterDto {
    private String email;
    private String password;
}
