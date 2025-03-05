package com.tet_store.user;

import com.tet_store.utils.StandardJsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public StandardJsonResponse createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/all")
    public StandardJsonResponse fetchUsers(){
        return userService.fetchUsers();
    }

    @GetMapping("")
    public StandardJsonResponse fetchUsers(@RequestParam("usrCid") String usrCid){
        return userService.fetchUser(usrCid);
    }
}
