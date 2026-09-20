package com.stacksimplify.restservices.Mappers;

import com.stacksimplify.restservices.Dto.UserMsDto;
import com.stacksimplify.restservices.Entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //user to userDto

    @Mapping(source = "email", target = "emailaddress")
    UserMsDto usertoUserDto(Client client);

    //List<User> to List<UserMsDto>

    List<UserMsDto> userstoUserDtos(List<Client> clients);

}
