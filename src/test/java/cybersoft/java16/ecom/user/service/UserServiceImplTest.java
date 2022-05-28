package cybersoft.java16.ecom.user.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import cybersoft.java16.ecom.user.dto.UserDTO;
import cybersoft.java16.ecom.user.dto.UserUpdateDTO;
import cybersoft.java16.ecom.user.mapper.UserMapper;
import cybersoft.java16.ecom.user.model.EcomUser;
import cybersoft.java16.ecom.user.model.UserStatus;
import cybersoft.java16.ecom.user.repository.UserRepository;

@DisplayName("UserServiceImplementTest")
@SpringBootTest
public class UserServiceImplTest {
	@Mock
	private UserRepository repository;
	@Mock
	private PasswordEncoder encoder;

	@InjectMocks
	private UserService service = new UserServiceImpl();

	@DisplayName("Should return emplty list when no entity found")
	@Test
	public void shouldReturnEmptyListWhenNoEntityFound() {
		when(repository.findAll()).thenReturn(List.of());
		assertEquals(0, service.findAllUser().size());
	}

	@DisplayName("Should return new user")
	@Test
	public void shouldReturnNewUser() {
		UserDTO dto = UserDTO.builder().username("moderator").password("12345678")
				.status(UserStatus.ACTIVE).build();
		String password = encoder.encode(dto.getPassword());
		EcomUser user = UserMapper.INSTANCE.toUser(dto);
		user.setPassword(password);
		EcomUser newUser = EcomUser.builder().username(dto.getUsername()).password(password)
				.status(dto.getStatus()).build();
		when(repository.save(user)).thenReturn(newUser);
		assertDoesNotThrow(() -> service.createNewUser(dto));
	}
	
	@DisplayName("Should delete user with Id")
	@Test
	public void shouldDeleteUserWithId() {
		String userId = UUID.randomUUID().toString();
		EcomUser user = EcomUser.builder().username("moderator").password("12345678")
				.status(UserStatus.ACTIVE).build();
		when(repository.getById(UUID.fromString(userId))).thenReturn(user);
		assertNotEquals(null, service.deleteUser(userId));
	}
}
