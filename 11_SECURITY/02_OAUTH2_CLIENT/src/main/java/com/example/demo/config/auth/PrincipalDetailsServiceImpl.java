package com.example.demo.config.auth;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@Slf4j
public class PrincipalDetailsServiceImpl implements UserDetailsService{

	@Autowired
    private UserRepository userRepository;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> userOptional = userRepository.findById(username);
		if(userOptional.isEmpty())
			throw new UsernameNotFoundException(username+ " 은 존재하지 않는 계정입니다");

		User user = userOptional.get();

		//entity -> dto
		UserDto userDto = UserDto.builder()
				.username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

		return new PrincipalDetails(userDto);

//		UserDto userDto = userMapper.SelectOne(username);
//		if(userDto==null)
//			throw new UsernameNotFoundException(username+" 은 존재하지 않은 계정입니다.");
//

	}

}
