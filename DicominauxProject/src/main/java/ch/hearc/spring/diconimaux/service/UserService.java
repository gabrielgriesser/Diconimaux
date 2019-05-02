package ch.hearc.spring.diconimaux.service;

import ch.hearc.spring.diconimaux.model.User;

public interface UserService {
	 public User findUserByEmail(String email);
	 public void saveUser(User user);
}