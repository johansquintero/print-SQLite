package com.connection.controllers;

import com.connection.dto.UserDTO;
import com.connection.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        return ResponseEntity.of(this.userService.save(userDTO));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        return ResponseEntity.of(this.userService.update(userDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.userService.delete(id), HttpStatus.OK);
    }
}
