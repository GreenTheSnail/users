package com.moro.users.dao

import com.moro.users.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun existsByUsername(username: String): Boolean

    fun findByUsername(username: String): Optional<User>
}