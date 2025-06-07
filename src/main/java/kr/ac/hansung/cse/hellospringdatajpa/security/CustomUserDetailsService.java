package kr.ac.hansung.cse.hellospringdatajpa.security;

import jakarta.transaction.Transactional;
import kr.ac.hansung.cse.hellospringdatajpa.entity.Role;
import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    //UserDetails 구현체
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //옵셔널처리
        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        //성공했다면 User 객체를 반환한다
        //여기서 User는 내가만든게 아니고 Spring Security의 UserDetails의 구현체 User
        //여기서 반환된 객체는 DB에서 가져온 정보로 설정된 User
        //Security에 등록은 나중에 UsernameAuthFilter가 알아서 함
        //JWT로 했을 때는 직접 등록까지 했어야 했다(setAuthentication(token))
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    //꼭 스태틱 아니어도 됨
    private static Collection<? extends GrantedAuthority> getAuthorities(User user)
    {
        String[] userRoles = user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .toArray(String[]::new);

        //이건 SpringSecurity가 제공하는 메서드
        //문자열 배열을 authroityList로 반환한다
        //Collection<GrantedAuthority> 결국 이게 반환 타입이 된다
        //그냥 내 User안에 포함된 Role을 Security가 읽을 수 없으므로 읽을 수 있는 GrantedAuthority로 변환해주는것
        return AuthorityUtils.createAuthorityList(userRoles);
    }
}