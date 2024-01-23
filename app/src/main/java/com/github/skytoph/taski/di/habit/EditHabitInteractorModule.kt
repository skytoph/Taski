package com.github.skytoph.taski.di.habit

import com.github.skytoph.taski.core.Now
import com.github.skytoph.taski.domain.habit.HabitRepository
import com.github.skytoph.taski.presentation.habit.edit.EditHabitInteractor
import com.github.skytoph.taski.presentation.habit.list.mapper.HabitDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object EditHabitInteractorModule {

    @Provides
    fun interactor(repository: HabitRepository, now: Now, mapper: HabitDomainMapper): EditHabitInteractor =
        EditHabitInteractor.Base(repository, now, mapper)
}