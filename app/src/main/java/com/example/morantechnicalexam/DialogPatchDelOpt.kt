package com.example.morantechnicalexam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogPatchDelOpt(
    onDismissRequest: () -> Unit,
    keyVal: String,
    itemName: String,
    price: String,
    color: String,
    key: Pair<String?, ItemModel>,
    function: () -> Unit
) {
    var dialogDelete by remember { mutableStateOf(false) }
    var dialogUpdate by remember { mutableStateOf(false) }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        ElevatedCard(
            modifier = Modifier
//                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "What do you want to do with this data?",
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest(); },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("CANCEL")
                    }
                    TextButton(
                        onClick = {
                            onDismissRequest();

                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("EDIT")
                    }
                    TextButton(
                        onClick = {
                            deleteData(keyVal);
                            onDismissRequest();
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("DELETE")
                    }
                }
            }
        }
    }
}