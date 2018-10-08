package com.sponsors.security;

import com.sponsors.dto.CustomUserDetail;
import com.sponsors.exception.NotFoundException;
import com.sponsors.model.User;
import com.sponsors.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sponsors.exception.NotFoundException.NotFound.USER_NOT_FOUND;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

		List<String> userRoles = user.getUserRoles().stream()
				.map(userRole -> userRole.getRole().getName())
				.collect(Collectors.toList());

		List<GrantedAuthority> objAuthorities = new ArrayList<>();
		userRoles.forEach(role -> {
			SimpleGrantedAuthority objAuthority = new SimpleGrantedAuthority(role);
			objAuthorities.add(objAuthority);
		});

		return new CustomUserDetail(email, user.getPassword(), objAuthorities, user);
	}

}
