package com.example.vpdmoney.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vpdmoney.data.remote.dto.UsersDtoItem
import com.example.vpdmoney.domain.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.example.vpdmoney.presentation.list.UsersViewState.Loading
import com.example.vpdmoney.presentation.list.UsersViewState.Success
import com.example.vpdmoney.presentation.list.UsersViewState.Error
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UsersRepository
) : ViewModel() {

    private var _viewState = MutableStateFlow<UsersViewState>(Success(emptyList()))

    val viewState: StateFlow<UsersViewState> = _viewState

    init {
        getListOfUser()
    }

    fun getListOfUser() {
        _viewState.value = Loading

        viewModelScope.launch {
            repository.getUsersFromServer()
                .catch { exception ->
                    _viewState.value = Error("Check Internet connection" ?: exception.localizedMessage)
                }
                .collect {
                    _viewState.value = Success(it)
                }
        }
    }
}


sealed class UsersViewState {
    object Loading : UsersViewState()
    data class Error(val error: String) : UsersViewState ()
    data class Success(val userList: List<UsersDtoItem>) : UsersViewState ()
}
