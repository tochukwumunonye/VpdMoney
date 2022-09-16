package com.example.vpdmoney.presentation.list

import com.example.vpdmoney.data.remote.dto.UsersDtoItem
import com.example.vpdmoney.data.remote.repository.UsersRepositoryImplementation
import com.example.vpdmoney.domain.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UsersViewModelTest {


    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var usersViewModel: UsersViewModel
    private val mockRepository: UsersRepositoryImplementation = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When data source returns success Then emit success view state`() {

        val expectedList = getFlowOfUsers()
        runTest {
            whenever(mockRepository.getUsersFromServer()).thenReturn(expectedList)
            usersViewModel = UsersViewModel(mockRepository)
            usersViewModel.getListOfUser()
            val stateFlow = usersViewModel.viewState.value
            assertEquals(stateFlow, UsersViewState.Success(
                listOf(
                    UsersDtoItem(
                        email = "emeka@gmail.com",
                        id = 1,
                        name = "emeka",
                        phone = "0805",
                        username = "emeka",
                        website = "emeka.com",
                    )
                )
            ))
        }
    }

    private fun getFlowOfUsers() : Flow<List<UsersDtoItem>> {
        return flowOf(
            listOf(
                UsersDtoItem(
                    email = "emeka@gmail.com",
                    id = 1,
                    name = "emeka",
                    phone = "0805",
                    username = "emeka",
                    website = "emeka.com",
                )
            )
        )
    }
}
