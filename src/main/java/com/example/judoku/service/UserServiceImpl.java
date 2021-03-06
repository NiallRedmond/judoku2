package com.example.judoku.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.judoku.dto.UserRegistrationDto;
import com.example.judoku.model.Role;
import com.example.judoku.model.User;
import com.example.judoku.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setBelt("White");
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        
        User userAdmin = userRepository.findByEmail("admin@email.com");
        if (userAdmin == null){
           userAdmin = new User();
           userAdmin.setFirstName("Niall");
           userAdmin.setLastName("Redmond");
           userAdmin.setBelt("Yellow");
           userAdmin.setEmail("admin@email.com");
           userAdmin.setPassword(passwordEncoder.encode("admin"));
           userAdmin.setRoles(Arrays.asList(new Role("ROLE_Admin")));
                 
           userRepository.save(userAdmin);
     
           
        }
        User A = userRepository.findByEmail("A@email.com");
        if (A == null){
           A = new User();
           A.setBelt("White");
           A.setFirstName("ALex");
           A.setLastName("Smith");
           A.setEmail("A@email.com");
           A.setPassword(passwordEncoder.encode("password"));
           A.setRoles(Arrays.asList(new Role("ROLE_USER")));
           
           userRepository.save(A);
        }
        User B = userRepository.findByEmail("B@email.com");
        if (B == null){
           B = new User();
           B.setBelt("White");
           B.setFirstName("Brian");
           B.setLastName("Conners");
           B.setEmail("B@email.com");
           B.setPassword(passwordEncoder.encode("password"));
           B.setRoles(Arrays.asList(new Role("ROLE_USER")));
           
           userRepository.save(B);
        }
        User C = userRepository.findByEmail("C@email.com");
        if (C == null){
           
           C = new User();
           C.setBelt("Yellow");
           C.setFirstName("Ciara");
           C.setLastName("Doyle");
           C.setEmail("C@email.com");
           C.setPassword(passwordEncoder.encode("password"));
           C.setRoles(Arrays.asList(new Role("ROLE_USER")));
           
           userRepository.save(C);
        }
        User D = userRepository.findByEmail("D@email.com");
        if (D == null){
           D = new User();
           D.setBelt("White");
           D.setFirstName("Daniel");
           D.setLastName("Mullen");
           D.setEmail("D@email.com");
           D.setPassword(passwordEncoder.encode("password"));
           D.setRoles(Arrays.asList(new Role("ROLE_USER")));
           
           
           userRepository.save(D);
        }
        User E = userRepository.findByEmail("E@email.com");
        if (E == null){
           E = new User();
           E.setBelt("Blue");
           E.setFirstName("Ethan");
           E.setLastName("Smith");
           E.setEmail("E@email.com");
           E.setPassword(passwordEncoder.encode("password"));
           E.setRoles(Arrays.asList(new Role("ROLE_USER")));
           
           userRepository.save(E);
        }
        User F = userRepository.findByEmail("F@email.com");
        if (F == null){
           F = new User();
           F.setBelt("Blue");
           F.setFirstName("Fionn");
           F.setLastName("Dalton");
           F.setEmail("F@email.com");
           F.setPassword(passwordEncoder.encode("password"));
           F.setRoles(Arrays.asList(new Role("ROLE_USER")));
           
           userRepository.save(F);
        }
        User G = userRepository.findByEmail("G@email.com");
        if (G == null){
           G = new User();
           G.setBelt("Green");
           G.setFirstName("Graham");
           G.setLastName("Campion");
           G.setEmail("G@email.com");
           G.setPassword(passwordEncoder.encode("password"));
           G.setRoles(Arrays.asList(new Role("ROLE_USER")));
           
           userRepository.save(G);
        }
        User H = userRepository.findByEmail("H@email.com");
        if (H == null){
           H = new User();
           H.setBelt("Blue");
           H.setFirstName("Helen");
           H.setLastName("O'Bryne");
           H.setEmail("H@email.com");
           H.setPassword(passwordEncoder.encode("password"));
           H.setRoles(Arrays.asList(new Role("ROLE_USER")));
           
           userRepository.save(H);
        }
        
        
        
        return userRepository.save(user);
        
        
        
        
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}