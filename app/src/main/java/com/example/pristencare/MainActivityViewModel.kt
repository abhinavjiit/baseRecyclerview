package com.example.pristencare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repositoryImpl: Repository) : ViewModel() {

    private var _layoutManger = MutableLiveData<Pair<Int, GridLayoutManager>>()
    val layoutManager: LiveData<Pair<Int, GridLayoutManager>> = _layoutManger


    private var _images = MutableLiveData<IResult<ResponseModel>>()
    val images: LiveData<IResult<ResponseModel>> = _images


    fun changeLayoutManager(layoutManager: Pair<Int, GridLayoutManager>) {
        _layoutManger.postValue(layoutManager)
    }


    fun getImages(requestModel: RequestModel) {
        viewModelScope.launch {
            repositoryImpl.getImages(requestModel).catch {
                _images.postValue(IResult.Error(it))
            }
                .collect {
                    _images.postValue(it)
                }
        }


    }

}