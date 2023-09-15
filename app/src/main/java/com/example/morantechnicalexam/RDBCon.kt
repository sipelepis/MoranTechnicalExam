package com.example.morantechnicalexam

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainViewModel: ViewModel(){
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty);
    init {
        FetchDataFromRDB()
    }
    private fun FetchDataFromRDB(){

        val database = Firebase.database;
        val itemList = mutableListOf<ItemModel>()
        val itemListwithKey = mutableListOf<Pair<String?, ItemModel>>()
        database.getReference("items").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    var i: Int = 0;
                    for(DataSnap in snapshot.children) {
                        val key = DataSnap?.key;
                        val item = DataSnap.getValue(ItemModel::class.java);
                        if(key != null && item != null){
                            itemListwithKey.add(Pair(key, item))
                            itemList.add(item);
                            i++;
                        }
                        response.value = DataState.Success(itemList, itemListwithKey);
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                response.value = DataState.Failure(error.message);
            }
        })
    }
}
