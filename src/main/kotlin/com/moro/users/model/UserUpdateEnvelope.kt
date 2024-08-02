package com.moro.users.model

import com.moro.users.service.validation.SizeIfNotNull
import com.moro.users.service.validation.UniqueUsername

data class UserUpdateEnvelope(
    @SizeIfNotNull(message = "Name should have at least 1 character")
    val name: String? = null,
    @SizeIfNotNull(message = "Username should have at least 1 character")
    @UniqueUsername
    val username: String? = null,
    @SizeIfNotNull(message = "Password should have at least 1 character")
    val password: String? = null,
)