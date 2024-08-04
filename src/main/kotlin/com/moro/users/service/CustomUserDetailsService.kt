package com.moro.users.service

import com.moro.users.dao.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull
import org.springframework.security.core.userdetails.User as SecurityUser

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username).getOrNull()
            ?: throw UsernameNotFoundException("User not found with username: $username")

        return SecurityUser(
            user.username,
            user.password,
            listOf(GrantedAuthority { "ROLE_USER" })
        )
    }
}
