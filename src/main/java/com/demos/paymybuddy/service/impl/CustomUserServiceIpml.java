package com.demos.paymybuddy.service.impl;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.dto.UpdateUserRequestDto;
import com.demos.paymybuddy.mapper.CustomUserMapper;
import com.demos.paymybuddy.repository.CustomUserRepository;
import com.demos.paymybuddy.service.AccountService;
import com.demos.paymybuddy.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserServiceIpml implements CustomUserService {

    private final CustomUserRepository userRepository;
    private final AccountService accountService;
    private final CustomUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<CustomUser> findById(Long id) {
        return this.userRepository.findById(id);
    }
    @Override
    public CustomUserDto getCustomUserDtoByEmail(String email) {
        return this.userMapper.customUserToCustomUserDto(this.userRepository.findByEmail(email));
    }

    @Override
    public CustomUserDto getConnectedUser(HttpSession session) {
        String email = (String) session.getAttribute("username");
        return this.getCustomUserDtoByEmail(email);
    }
    @Override
    public UpdateUserRequestDto getConnectedUserAsUpdateUserRequestDto(HttpSession session) {
        return this.userMapper.customUserDtoToUpdateUserRequestDto(this.getConnectedUser(session));
    }

    @Override
    public CustomUser findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public boolean isExistByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public void save(CustomUserDto userDto) {
        CustomUser user = this.userMapper.customUserDtoToCustomUser(userDto);
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        Account account = this.accountService.generateAccount();
        user.setAccount(account);
        this.userRepository.save(user);
    }
    @Override
    public CustomUserDto update(CustomUserDto userDto, UpdateUserRequestDto updateUserRequestDto) {
        CustomUserDto userUpdatedVersion = this.userMapper.updateUserRequestDtoToCustomUser(updateUserRequestDto);

        userUpdatedVersion.setPassword(updateUserRequestDto.getNewPassword());
        userUpdatedVersion.setAccount(userDto.getAccount());
        userUpdatedVersion.setFriendsList(userDto.getFriendsList());

        this.userRepository.save(this.userMapper.customUserDtoToCustomUser(userUpdatedVersion));

        return userUpdatedVersion;
    }

    @Override
    public String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    @Override
    public boolean isPasswordValid(String oldPassword, String newPassword) {
        return this.passwordEncoder.matches(oldPassword,newPassword);
    }

    @Override
    public void addFriend(Long userId, String friendEmail) {
        CustomUser user = this.userRepository.findById(userId).orElseThrow();
        CustomUser friend = this.userRepository.findByEmail(friendEmail);
        if (!user.getFriendsList().contains(friend)) {
            user.getFriendsList().add(friend);
            this.userRepository.save(user);
        }
    }

    @Override
    public void deleteFriend(Long userId, Long friendId) {
        CustomUser user = this.userRepository.findById(userId).orElseThrow();
        CustomUser friend = this.userRepository.findById(friendId).orElseThrow();
        if (!user.getFriendsList().contains(friend)) {
            user.getFriendsList().remove(friend);
            this.userRepository.save(user);
        }
    }
}
