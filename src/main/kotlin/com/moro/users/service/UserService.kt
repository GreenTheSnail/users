package com.moro.users.service

import com.moro.users.dao.UserRepository
import com.moro.users.model.UserCreationEnvelope
import com.moro.users.model.UserUpdateEnvelope
import com.moro.users.model.entity.User as UserEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<UserEntity> = userRepository.findAll()

    fun getUserById(id: UUID): UserEntity? = userRepository.findById(id).orElse(null)

    fun createUser(user: UserCreationEnvelope): UserEntity = userRepository.save(user.toEntity())

    fun updateUser(id: UUID, updatedUser: UserUpdateEnvelope): UserEntity? {
        val existingUser = userRepository.findById(id)
        return if (existingUser.isPresent) {
            val user = existingUser.get()
            val userToSave = user.copy(
                name = updatedUser.name ?: user.name,
                username = updatedUser.username ?: user.username,
                password = updatedUser.password ?: user.password,
            )
            userRepository.save(userToSave)
        } else {
            null
        }
    }

    fun deleteUser(id: UUID): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

private fun UserCreationEnvelope.toEntity() =
    UserEntity(
        id = UUID.randomUUID(),
        name = name,
        username = username,
        password = password,
    )
