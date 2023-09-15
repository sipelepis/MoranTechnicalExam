package com.example.morantechnicalexam

sealed class DataState {
    class Success(val data: MutableList<ItemModel>, val key: MutableList<Pair<String?, ItemModel>>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}
