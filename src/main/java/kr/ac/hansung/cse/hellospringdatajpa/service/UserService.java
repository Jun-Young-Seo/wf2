package kr.ac.hansung.cse.hellospringdatajpa.service;

import kr.ac.hansung.cse.hellospringdatajpa.dto.UserRegisterDto;
import kr.ac.hansung.cse.hellospringdatajpa.entity.Role;
import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.exception.NoRoleException;
import kr.ac.hansung.cse.hellospringdatajpa.exception.UserAlreadyExistException;
import kr.ac.hansung.cse.hellospringdatajpa.repo.RoleRepository;
import kr.ac.hansung.cse.hellospringdatajpa.repo.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(UserRegisterDto user) {
        //email 중복검사
        if(checkEmailExists(user.getEmail())) {
            throw new UserAlreadyExistException(user.getEmail());
        }
        //관리자계정 검사
        boolean isAdmin = false;
        if(checkAdminEmail(user.getEmail())) {
            isAdmin = true;
        }

        User userEntity = new User();
        userEntity.setEmail(user.getEmail());
        //해시값으로 설정
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        if(isAdmin) {
            String findingRole = "ROLE_ADMIN";
            Role adminRole= roleRepository.findByRoleName(findingRole).orElseThrow(
                    //사실 없어도 되는 예외지만 확장성있는 설계라면 있어야하지 않을까
                    ()-> new NoRoleException(findingRole)
            );
            userEntity.setRoles(List.of(adminRole));
        }
        else{
            String findingRole = "ROLE_USER";
            Role userRole = roleRepository.findByRoleName(findingRole).orElseThrow(
                    ()-> new NoRoleException(findingRole)
            );
            userEntity.setRoles(List.of(userRole));
        }

        userRepository.save(userEntity);
    }

    public boolean checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return true;
        }

        return false;
    }

    public boolean checkAdminEmail(String email){
        if(email.startsWith("admin")){
            return true;
        }
        return false;
    }

    //JPA Repository 상속이므로 Repo 클래스에서 작성해줄 필요가 없음
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}