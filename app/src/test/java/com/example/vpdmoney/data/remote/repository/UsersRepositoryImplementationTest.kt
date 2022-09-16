package com.example.vpdmoney.data.remote.repository

import com.example.vpdmoney.data.remote.api.APIService
import com.example.vpdmoney.data.remote.dto.UsersDtoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class UsersRepositoryImplementationTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val api: APIService = mock()


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when successful return a list of makeup items`() {
        val apiResponse = getResponseFromAPI()

        runTest {
            whenever(api.getUsers()).thenReturn(apiResponse)
            val repository = UsersRepositoryImplementation(api)
            val makeupListItems = repository.getUsersFromServer();
            assertEquals(makeupListItems.first().first().name, "emeka")
        }
    }


    private fun getResponseFromAPI(): ArrayList<UsersDtoItem> {
        return arrayListOf(
            UsersDtoItem(
                email = "emeka@gmail.com",
                id = 1,
                name = "emeka",
                phone = "080235",
                username = "emesky",
                website = "emeka.com"
            )
        )
    }


}
