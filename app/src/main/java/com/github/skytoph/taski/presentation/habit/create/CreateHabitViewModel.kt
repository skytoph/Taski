package com.github.skytoph.taski.presentation.habit.create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.taski.domain.habit.HabitRepository
import com.github.skytoph.taski.presentation.habit.icon.IconState
import com.github.skytoph.taski.presentation.habit.icon.SelectIconEvent
import com.github.skytoph.taski.presentation.habit.list.mapper.HabitDomainMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateHabitViewModel @Inject constructor(
    private val state: MutableState<CreateHabitState> = mutableStateOf(CreateHabitState()),
    private val iconState: MutableState<IconState>,
    private val repository: HabitRepository,
    private val mapper: HabitDomainMapper,
) : ViewModel() {

    init {
        SelectIconEvent.Clear.handle(iconState)
    }

    fun saveHabit() = viewModelScope.launch(Dispatchers.IO) {
        val habit = state.value.toHabitUi().map(mapper)
        repository.insert(habit)
    }

    fun onEvent(event: CreateHabitEvent) = event.handle(state)

    fun state(): State<CreateHabitState> = state

    fun iconState(): State<IconState> = iconState
}