package com.github.skytoph.taski.presentation.habit.edit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.taski.presentation.habit.HabitScreens
import com.github.skytoph.taski.presentation.habit.icon.IconState
import com.github.skytoph.taski.presentation.habit.list.mapper.HabitUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditHabitViewModel @Inject constructor(
    private val state: MutableState<EditHabitState> = mutableStateOf(EditHabitState()),
    private val iconState: MutableState<IconState>,
    private val interactor: EditHabitInteractor,
    private val habitMapper: HabitUiMapper,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    init {
        onEvent(EditHabitEvent.Progress(true))
        viewModelScope.launch(Dispatchers.IO) {
            val habit = habitMapper.map(interactor.habit(savedStateHandle.id()))
            withContext(Dispatchers.Main) { onEvent(EditHabitEvent.Init(habit)) }
        }
    }

    fun saveHabit(navigateUp: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        interactor.insert(state.value.toHabitUi())
        withContext(Dispatchers.Main) { navigateUp() }
    }

    fun onEvent(event: EditHabitEvent) = event.handle(state, iconState)

    fun state(): State<EditHabitState> = state

    fun iconState(): State<IconState> = iconState

    fun SavedStateHandle.id(): Long = this[HabitScreens.EditHabit.habitIdArg]
        ?: throw IllegalStateException("Edit Habit screen must contain habit id")
}