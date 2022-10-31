package com.app.post.servicies;




import com.app.post.entities.User;
import com.app.post.repos.UserRepository;
import com.app.post.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
    private UserRepository userRepository;

/*  public UserDetailsServiceImpl(UserRepository userRepository){
       this.userRepository=userRepository;
    }*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username);
        return JwtUserDetails.create(user);
    }
    public  UserDetails loadUserById(Long id){
        User user =userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
