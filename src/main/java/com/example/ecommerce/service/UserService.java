package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.request.UserRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CartService cartService;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, CartService cartService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cartService = cartService;
        this.jwtService = jwtService;
    }

    public void assignRefreshToken(String refreshToken, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found"));
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::new)
                .toList();
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    public UserDto getUserByName(String username) {
        return userRepository.findByUsername(username)
                .map(UserDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found"));
    }

    public UserDto createUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        User savedUser = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartService.save(cart);

        savedUser.setCart(cart);
        userRepository.save(savedUser);

        return new UserDto(savedUser);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

        return user.orElseThrow(EntityNotFoundException::new);
    }
}
