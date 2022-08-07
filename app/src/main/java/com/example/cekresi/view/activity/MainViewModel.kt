package com.example.cekresi.view.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cekresi.base.NetworkResult
import com.example.cekresi.network.response.ResponseResi
import com.example.cekresi.repository.ResiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ResiRepository
): ViewModel() {

    private val _resi = MutableLiveData<NetworkResult<ResponseResi>>()
    val resi: LiveData<NetworkResult<ResponseResi>> = _resi

    fun getResi(
        api_key: String?,
        courier: String?,
        awb: String?
    ) = viewModelScope.launch {
        repository.getResi(api_key, courier, awb)
            .collect {
                _resi.value = it
            }
    }

}