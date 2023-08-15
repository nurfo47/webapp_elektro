package com.elektro.elektro.service.serviceImpl;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elektro.elektro.model.Role;
import com.elektro.elektro.model.User;
import com.elektro.elektro.repository.UserRepository;
import com.elektro.elektro.service.UserService;
import com.elektro.elektro.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(),
        registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("USER")));

        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email);
    
       if (user != null) {
           return new org.springframework.security.core.userdetails.User(
               user.getEmail(),
               user.getPassword(),
               mapRolesToAuthorities(user.getRoles())
           );
       } else {
           throw new UsernameNotFoundException("User not found: " + email);
       }
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
       return roles.stream().map(role-> new SimpleGrantedAuthority("ROLE_"+ role.getName())).collect(Collectors.toList());
    }



    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}
