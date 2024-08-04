package com.moro.users

import com.moro.users.model.UserCreationEnvelope
import com.moro.users.service.UserService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UsersApplication

fun main(args: Array<String>) {
    val context = runApplication<UsersApplication>(*args)
    val userService = context.getBean(UserService::class.java)
    userService.createUser(UserCreationEnvelope(name = "Alice Johnson", username = "alice_j", password = "password123"))
    userService.createUser(UserCreationEnvelope(name = "Bob Smith", username = "bob_s", password = "passw0rd!"))
    userService.createUser(UserCreationEnvelope(name = "Charlie Brown", username = "charlie_b", password = "P@ssw0rd"))
    userService.createUser(UserCreationEnvelope(name = "Diana Prince", username = "diana_p", password = "WonderW0man"))
    userService.createUser(UserCreationEnvelope(name = "Eve Adams", username = "eve_a", password = "Ev3ryS3cr3t"))
    runApplication<UsersApplication>(*args)
}
