package com.github.skytoph.taski.di.habit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.github.skytoph.taski.presentation.habit.create.CreateHabitState
import com.github.skytoph.taski.presentation.habit.create.NewHabitValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CreateHabitModule {

    @Provides
    fun state(): MutableState<CreateHabitState> = mutableStateOf(CreateHabitState())

    @Provides
    fun validator(): NewHabitValidator = NewHabitValidator()
}