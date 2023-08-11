package com.demos.paymybuddy.mapper;

import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.dto.UpdateUserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomUserMapper {
    CustomUser customUserDtoToCustomUser(CustomUserDto customUserDto);
    @Mapping(target = "password", ignore = true)
    CustomUserDto customUserToCustomUserDto(CustomUser customUser);
    CustomUserDto updateUserRequestDtoToCustomUser(UpdateUserRequestDto updateUserRequestDto);
    UpdateUserRequestDto customUserDtoToUpdateUserRequestDto(CustomUserDto customUserDto);

}
