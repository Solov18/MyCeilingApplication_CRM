package ru.gb.diplom.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.fail;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import ru.gb.diplom.role.RoleRepository;
import ru.gb.diplom.user.CreateUserDto;
import ru.gb.diplom.user.User;
import ru.gb.diplom.user.UserDto;
import ru.gb.diplom.user.UserRepository;

public class UserServiceTest {

	private UserRepository repository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private UserService service;

	@BeforeEach
	public void setUp() throws Exception {
		repository = Mockito.mock(UserRepository.class);
		roleRepository = Mockito.mock(RoleRepository.class);
		passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
		service = new UserService(repository, roleRepository, passwordEncoder);
	}

	@Test
	public void test() {
	}

	@Test
	public void testGetUser() {
		// given
		User user = User.builder()
				.username("alfa")
				.firstname("beta")
				.lastname("gamma")
				.password("delta")
				.email("alfa@alfa.pl")
				.build();
		when(repository.findByUsername("alfa")).thenReturn(user);

		// when
		User user2 = service.findByUserName("alfa");

		// then
		assertEquals("alfa", user.getUsername());
		assertEquals("beta", user.getFirstname());
		assertEquals("gamma", user.getLastname());
		assertEquals("alfa@alfa.pl", user.getEmail());
	}

	@Test
	public void given_user_when_find_other_then_isNotEqual() {
		// given
		User user = User.builder()
				.username("alfa")
				.firstname("beta")
				.lastname("gamma")
				.password("delta")
				.email("alfa@alfa.pl")
				.build();
		when(repository.findByUsername("alfa")).thenReturn(user);

		// when
		User user2 = service.findByUserName("alfa");

		// then
		assertNotEquals("alfa1", user2.getUsername());
		assertNotEquals("beta1", user2.getFirstname());
		assertNotEquals("gamma1", user2.getLastname());
		assertNotEquals("beta@alfa.pl", user2.getEmail());
	}

	@Test
	public void given_CreateUserDto_with_empty_name_when_creating_user_then_null_pointer_exception_is_thrown() {
		// given
		CreateUserDto createUserDto = CreateUserDto.builder()
				.username(null)
				.build();

		// when
		try {
			Long userId = service.createUser(createUserDto);
			fail("Expected a NullPointerException to be thrown");
		} catch (NullPointerException e) {
			// then exception is thrown
		}
	}

	@Test
	public void given_user_should_return_userDto() {
		// given
		User user = User.builder()
				.firstname("Alan")
				.lastname("Delone")
				.email("alan@delone.fr")
				.build();
		when(repository.findById(Mockito.any())).thenReturn(Optional.of(user));

		// when
		UserDto userDto = service.getUser(1L);

		// then
		assertEquals(user.getFirstname(), userDto.getFirstname());
		assertEquals(user.getLastname(), userDto.getLastname());
		assertEquals(user.getEmail(), userDto.getEmail());
	}
}