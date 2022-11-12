package com.marangoz.eczane.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marangoz.eczane.model.District
import com.marangoz.eczane.model.Objetc
import com.marangoz.eczane.model.Province
import com.marangoz.eczane.model.Result
import com.marangoz.eczane.repository.Repository
import com.marangoz.eczane.utils.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    val eczaneList: MutableLiveData<Response<Objetc>> = MutableLiveData()
    val provinceList: MutableLiveData<List<Province>> = MutableLiveData()
    val districtList: MutableLiveData<List<District>> = MutableLiveData()
    var sehirId: MutableLiveData<Int> = MutableLiveData()

    fun allProvince() {
        viewModelScope.launch() {
            provinceList.value = repository.allProvince()
        }

    }

    fun getDistrict(sehirId: Int) {
        viewModelScope.launch() {
            districtList.value = repository.getDistrict(sehirId)
        }
    }

    fun getCustomEczane(ilce: String? = null, il: String) {
        viewModelScope.launch {
            try {
                eczaneList.value = repository.getCustomEczane(ilce, il)
            }catch (e:Exception){

            }

        }

    }


}