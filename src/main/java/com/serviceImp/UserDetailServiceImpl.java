//package com.serviceImp;
//
//import com.model.SecurityUser;
//import com.model.User;
//import com.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//
//@Repository
//@Transactional
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.getUserByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("couldn't found user");
//        }
//        return new SecurityUser(user);
//    }
//}
