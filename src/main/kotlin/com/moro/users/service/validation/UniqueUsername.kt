package com.moro.users.service.validation
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [UniqueUsernameValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class UniqueUsername(
    val message: String = "Username is already taken",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
