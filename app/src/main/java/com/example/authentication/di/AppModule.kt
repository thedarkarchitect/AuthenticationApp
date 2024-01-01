package com.example.authentication.di

import com.example.authentication.domain.useCases.ValidateEmail
import com.example.authentication.domain.useCases.ValidatePassword
import com.example.authentication.domain.useCases.Validations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUsesCases(): Validations{
        return Validations(
            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword()
        )
    }

}