package com.example.advertisementapp.service;

import com.example.advertisementapp.Exception.UserException;
import com.example.advertisementapp.common.JWTGeneratorService;
import com.example.advertisementapp.dao.UserRepository;
import com.example.advertisementapp.domain.User;
import com.example.advertisementapp.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTGeneratorService jwtGeneratorService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JWTGeneratorService jwtGeneratorService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtGeneratorService = jwtGeneratorService;
    }


    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) throws UserException {
        if(userDto != null) {

            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());

            User existUser = userRepository.getUserByEmail(userDto.getEmail());
            if(existUser != null){
                throw new UserException("User already exist");
            }
            user.setEmail(userDto.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

            User createdUser = userRepository.save(user);
            if(createdUser != null) {

                UserDto returnDto = new UserDto();
                returnDto.setId(createdUser.getId());
                returnDto.setFirstName(createdUser.getFirstName());
                returnDto.setLastName(createdUser.getLastName());
                returnDto.setEmail(createdUser.getEmail());

                return returnDto;
            }else throw new UserException("Something went wrong");
        }else throw new UserException("Empty object");
    }

    @Override
    public UserDto login(UserDto userDto) throws UserException {

        User user = userRepository.getUserByEmail(userDto.getEmail());

        if(bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())){

            UserDto returnDto = new UserDto();
            returnDto.setId(user.getId());
            returnDto.setFirstName(user.getFirstName());
            returnDto.setLastName(user.getLastName());
            returnDto.setEmail(user.getEmail());

            String jwtToken = jwtGeneratorService.getNewToken(userDto.getEmail(),
                    user.getId());

            returnDto.setJwtToken(jwtToken);

            return returnDto;
        }else throw new UserException("Credential not match");
    }
}
