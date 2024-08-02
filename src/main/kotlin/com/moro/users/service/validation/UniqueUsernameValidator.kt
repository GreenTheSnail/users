package com.moro.users.service.validation

import com.moro.users.dao.UserRepository
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UniqueUsernameValidator : ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun isValid(username: String?, context: ConstraintValidatorContext): Boolean {
        if (username.isNullOrEmpty()) {
            return true
        }
        return !userRepository.existsByUsername(username)
    }
}
