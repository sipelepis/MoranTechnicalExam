package com.example.morantechnicalexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth() , verticalArrangement = Arrangement.Top){
                var showOptionDialog by remember { mutableStateOf(false) }
                val database = Firebase.database;
                SetData(viewModel);
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End

            ) {
                var showDialog by remember { mutableStateOf(false) }
                FloatingActionButton(onClick = {
                    showDialog = showDialog.not();
//                    FetchDataFromRDB();
                },
                    containerColor  = Color(0xFF5dbea3),contentColor = Color.White) {
                    Icon(Icons.Filled.Add, "");
                }
                if(showDialog){
                    FormDialog(
                        onDismissRequest = { showDialog = showDialog.not(); },
                        onConfirmation = {
                            showDialog = showDialog.not();
                            println("Confirmation registered") // Add logic here to handle confirmation.
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun SetData(viewModel: MainViewModel){
    when (val result = viewModel.response.value) {
        is DataState.Success -> {
            ShowLazyList(result.data, result.key)
        }
        is DataState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = result.message,
                )
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error Fetching data"
                )
            }
        }
    }
}
@Composable
fun ShowLazyList(items: MutableList<ItemModel>, key: MutableList<Pair<String?, ItemModel>>) {
//    LazyColumn {
//        items(items){
//
//        }
//    }
    LazyColumn {
        items(key) { index ->
            CardItem(index)
        }
    }
}

@Composable
fun CardItem(
//    item: ItemModel,
    key: Pair<String?, ItemModel>
){
    var showOptionDialog by remember { mutableStateOf(false) }
    val database = Firebase.database;
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(5.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showOptionDialog = showOptionDialog.not();
                    }
                )
            },
    ) {
        if(showOptionDialog){
//            DialogDelete(
//                onDismissRequest = { showOptionDialog = showOptionDialog.not(); longPressTriggered = longPressTriggered.not();},
//                onConfirmation = {
//                    showOptionDialog = showOptionDialog.not();longPressTriggered = longPressTriggered.not();
//                    println("Confirmation registered") // Add logic here to handle confirmation.
//                }
//            )
            DialogPatchDelOpt(
                onDismissRequest = { showOptionDialog = showOptionDialog.not(); },
                key.first.toString(), key.second.itemName!!, key.second.price.toString(), key.second.color!!, key) {
            }
        }
        Column() {

            Text( //title
                text = key.second.itemName!!,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color(0xFF581845),
                fontWeight = FontWeight.Bold
            )
            Text( //price
                text = key.second.price.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                fontSize = 15.sp,
            )
            Text( //color
                text = key.second.color!!,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                fontSize = 15.sp,
            )
        }
    }
}
