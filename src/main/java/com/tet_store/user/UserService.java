package com.tet_store.user;

import com.tet_store.utils.StandardJsonResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public StandardJsonResponse createUser(User user) {
        StandardJsonResponse response = new StandardJsonResponse();
        try {
            boolean emailExists = userRepository.existsByUsrEmail(user.getUsrEmail());
            if (emailExists) throw new Exception("Email already exists");
            userRepository.save(user);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("message", e.getLocalizedMessage(), response);
        }
        return response;
    }

    public StandardJsonResponse fetchUsers() {
        StandardJsonResponse response = new StandardJsonResponse();
        try {
            List<User> users = userRepository.findAll();
            response.setSuccess(true);
            response.setData("result", users, response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("message", e.getLocalizedMessage(), response);
        }
        return response;
    }

    public StandardJsonResponse fetchUser(String id) {
        StandardJsonResponse response = new StandardJsonResponse();
        try {
            User user = userRepository.findByUsrCid(id);
            if (user == null) {
                response.setSuccess(false);
                response.setMessage("message", "User not found", response);
            } else {
                response.setSuccess(true);
            }
            response.setData("result", user, response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("message", e.getLocalizedMessage(), response);
        }
        return response;
    }
}
