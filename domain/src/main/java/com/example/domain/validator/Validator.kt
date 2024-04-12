package com.example.domain.validator

class Validator:BaseValidator<String> {

    override fun isValid(input: String): Boolean {
        return true
    }

}