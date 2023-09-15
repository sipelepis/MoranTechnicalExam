package com.example.morantechnicalexam

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogDelete(onDismissRequest: () -> Unit,
                 onConfirmation: () -> Unit,){
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text("Are you sure you want to delete this?") },
        text = { Text("This action cannot be undone") },
        confirmButton = {

        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("CANCEL")
            }
        }
    )
}

