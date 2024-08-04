package com.moro.users.controller

import com.moro.users.model.UserCreationEnvelope
import com.moro.users.model.UserUpdateEnvelope
import com.moro.users.model.entity.User
import com.moro.users.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import java.util.UUID

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/test_data_init")
    fun init(): String {
        listOf(
            UserCreationEnvelope(name = "Alice Johnson", username = "alice_j", password = "password123"),
            UserCreationEnvelope(name = "Bob Smith", username = "bob_s", password = "passw0rd!"),
            UserCreationEnvelope(name = "Charlie Brown", username = "charlie_b", password = "P@ssw0rd"),
            UserCreationEnvelope(name = "Diana Prince", username = "diana_p", password = "WonderW0man"),
            UserCreationEnvelope(name = "Eve Adams", username = "eve_a", password = "Ev3ryS3cr3t")
        ).forEach { user ->
            if (userService.getUserByUsername(user.username) == null) {
                userService.createUser(user)
            }
        }
        return "success"
    }

    @GetMapping
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(@RequestBody @Valid user: UserCreationEnvelope): User = userService.createUser(user)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: UUID, @RequestBody @Valid updatedUser: UserUpdateEnvelope): ResponseEntity<User> {
        val currentUser = getCurrentUser()
        val user = userService.getUserById(id)
        return if (currentUser.id != user?.id) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        } else {
            val updated = userService.updateUser(id, updatedUser)
            if (updated != null) {
                ResponseEntity.ok(updated)
            } else {
                ResponseEntity.notFound().build()
            }
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<Void> {
        val currentUser = getCurrentUser()
        val user = userService.getUserById(id)
        return if (currentUser.id != user?.id) {
            ResponseEntity.status(HttpStatus.FORBIDDEN)
        } else {
            if (userService.deleteUser(id)) {
                ResponseEntity.noContent()
            } else {
                ResponseEntity.notFound()
            }
        }.build()
    }

    private fun getCurrentUser(): User {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = (authentication.principal as UserDetails).username
        return userService.getUserByUsername(username)
            ?: throw RuntimeException("Current user not found")
    }
}