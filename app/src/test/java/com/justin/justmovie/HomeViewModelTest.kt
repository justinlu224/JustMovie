package com.justin.justmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.justin.justmovie.model.LoginResponse
import com.justin.justmovie.model.ResultStatus
import com.justin.justmovie.model.Success
import com.justin.justmovie.model.onLoading
import io.codetheworld.viewmodelunittestdemo.helpers.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import retrofit2.Response

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val apiRepository = mockk<ApiRepository>(relaxed = true)
    private val _loginApiresult: Observer<ResultStatus<LoginResponse>> = mockk(relaxed = true)

    lateinit var loginApiSa: MutableList<ResultStatus<LoginResponse>>

    lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {

        viewModel = HomeViewModel(apiRepository)
        loginApiSa = mutableListOf()
        viewModel.loginApiResult.observeForever {
            loginApiSa.add(it)
        }

        viewModel.loginApiResult.observeForever(_loginApiresult)

    }

    @Test
    fun loginWithFlow()  {


        coEvery {
            apiRepository.getLogin("")
        } returns flowOf(Response.success(LoginResponse(
            null,null
        )))

        viewModel.loginWithFlowd(null)
        System.out.println("#######: ${viewModel.loginApiResult.value}")
        System.out.println("#######: ssss${loginApiSa}")

        assertTrue(viewModel.loginApiResult.value is Success)

    }

    @Test
    fun login2() {

            coEvery {
                apiRepository.getLogin2("")
            } returns Response.success(
                LoginResponse(
                    null, null
                )
            )


            viewModel.login2(null, UnconfinedTestDispatcher())
            System.out.println("#######: ${viewModel.loginApiResult.value}")
            System.out.println("#######: ssss${loginApiSa}")

            assertTrue(viewModel.loginApiResult.value is Success)
        }
}