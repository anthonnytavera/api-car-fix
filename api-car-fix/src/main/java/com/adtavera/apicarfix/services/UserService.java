package com.adtavera.apicarfix.services;

import com.adtavera.apicarfix.dtos.UserDto;
import com.adtavera.apicarfix.dtos.UserUpdateDto;
import com.adtavera.apicarfix.exceptions.BadRequestException;
import com.adtavera.apicarfix.exceptions.NotFoundException;
import com.adtavera.apicarfix.models.User;
import com.adtavera.apicarfix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID: " + id + " not found."));
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public User create(UserDto userDto) {
        User checkEmail = userRepository
                .findByEmail(userDto.getEmail())
                .orElse(null);

        if (checkEmail != null) {
            throw new BadRequestException("Email is already in use.");
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, UserUpdateDto userUpdateDto) {
        User currentUser = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID: " + id + " not found."));

        User checkEmail = userRepository
                .findByEmailAndIdNot(userUpdateDto.getEmail(), id)
                .orElse(null);

        if (checkEmail != null) {
            throw new BadRequestException("Email is already in use.");
        }

        currentUser.setFirstName(userUpdateDto.getFirstName());
        currentUser.setLastName(userUpdateDto.getLastName());
        currentUser.setEmail(userUpdateDto.getEmail());

        return userRepository.save(currentUser);
    }

    @Transactional
    public boolean deleteById(Long id) {
        userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID: " + id + " not found." ));

        return true;
    }

}
