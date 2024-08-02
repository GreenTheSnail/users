package com.moro.users.service.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class SizeIfNotNullValidator : ConstraintValidator<SizeIfNotNull, String?> {

    private var min: Int = 0
    private var max: Int = 0

    override fun initialize(constraintAnnotation: SizeIfNotNull) {
        this.min = constraintAnnotation.min
        this.max = constraintAnnotation.max
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }
        return value.length in min..max
    }
}
