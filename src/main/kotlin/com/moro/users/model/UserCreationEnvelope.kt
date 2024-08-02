package com.moro.users.model

import com.moro.users.service.validation.SizeIfNotNull
import com.moro.users.service.validation.UniqueUsername

data class UserCreationEnvelope(
    @SizeIfNotNull(message = "Name should have at least 1 character")
    val name: String,
    @UniqueUsername
    @SizeIfNotNull(message = "Name should have at least 1 character")
    val username: String,
    @SizeIfNotNull(message = "Name should have at least 1 character")
    val password: String,
)