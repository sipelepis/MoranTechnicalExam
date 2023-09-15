package com.example.morantechnicalexam

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

val database = Firebase.database;
fun createItem(itemName: String, price: String, color: String){

    val data = ItemModel(color, itemName, price.toInt() )
    database.getReference("items").push().setValue(data)
}
fun deleteData(itemName: String, price: String, color: String, key: MutableList<Pair<String, ItemModel>>){
    val data = ItemModel(color, itemName, price.toInt() )
    database.getReference("items").removeValue();
}