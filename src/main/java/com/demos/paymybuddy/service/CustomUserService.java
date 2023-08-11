package com.demos.paymybuddy.service;

import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.dto.UpdateUserRequestDto;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface CustomUserService {
    Optional<CustomUser> findById(Long id);
    CustomUserDto getCustomUserDtoByEmail(String email);
    CustomUserDto getConnectedUser(HttpSession session);
    UpdateUserRequestDto getConnectedUserAsUpdateUserRequestDto(HttpSession session);
    CustomUser findByEmail(String email);
    boolean isExistByEmail(String email);
    void save(CustomUserDto userDto);
    CustomUserDto update(CustomUserDto userDto,UpdateUserRequestDto updateUserRequestDto);
    String encodePassword(String password);
    boolean isPasswordValid(String oldPassword, String newPassword);

    void addFriend(Long userId, String friendEmail);

    void deleteFriend(Long userId, Long friendId);
}
