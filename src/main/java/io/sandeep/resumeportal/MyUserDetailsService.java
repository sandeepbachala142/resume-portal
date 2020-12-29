package io.sandeep.resumeportal;

import io.sandeep.resumeportal.models.MyUserDetails;
import io.sandeep.resumeportal.models.User;
import io.sandeep.resumeportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    /**UserDetailsService is the spring security internal service that deals with logins.
     * So we need to Override the spring defaut userdetails with our Userdetails from DB
     */
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Calling the reposity to fetch the UserData
        Optional<User> user = userRepository.findByUserName(userName);

        //If User not found, throwing an exception
        user.orElseThrow(()-> new UsernameNotFoundException("Not found : " + userName));

        /* Now we have our class, but we need to give these details to the Spring
        in its bean which it use to validate*/
        return user.map(MyUserDetails::new).get();
    }
}
