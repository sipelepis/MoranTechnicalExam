package com.example.morantechnicalexam

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


val database = Firebase.database;
fun createItem(itemName: String, price: String, color: String){

    val data = ItemModel(color, itemName, price.toInt() )
    database.getReference("items").push().setValue(data)
}
fun deleteData(key: String){
//    val data = ItemModel(color, itemName, price.toInt() )
    database.getReference("items").child(key).removeValue().addOnSuccessListener {

    }.addOnFailureListener {

    }
//    val data = ItemModel(color, itemName, price.toInt() )
//    database.getReference("items").
//    database.getReference("items").orderByChild("itemName").equalTo(itemName).addListenerForSingleValueEvent(object : ValueEventListener {
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            for (userSnapshot in dataSnapshot.children) {
//                userSnapshot.ref.removeValue()
//            }
//        }
//
//        override fun onCancelled(databaseError: DatabaseError) {
//            // Handle error
//        }
//    });
//    database.getReference("items");
//    val listener: ValueEventListener = object : ValueEventListener {
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            for (ds in dataSnapshot.children) {
//                ds.ref.removeValue()
//            }
//        }
//        override fun onCancelled(databaseError: DatabaseError) {}
//    }
}