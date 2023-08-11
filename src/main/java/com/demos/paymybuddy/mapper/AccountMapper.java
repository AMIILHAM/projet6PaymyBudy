package com.demos.paymybuddy.mapper;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto AccountToAccountDto(Account account);
    Account AcountDtoToAccount(AccountDto accountDto);
}
