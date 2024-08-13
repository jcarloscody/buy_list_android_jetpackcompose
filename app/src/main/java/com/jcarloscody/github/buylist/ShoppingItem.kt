package com.jcarloscody.github.buylist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp


data class ShoppingItem(
    var id: Int,
    var name:String,
    var quantity: Int,
    var isEditing: Boolean = false,
){}



@Composable
fun ShoppingItem(){
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember {
        mutableStateOf(false)
    }
    
    var itemName by remember {
        mutableStateOf("")
    }
    
    var itemQunat by remember {
        mutableStateOf("")
    }

    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = { showDialog=true },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {

            Text(text = "Add Item")
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ){
            items(sItems){
                ShoppingListItem(
                    item = it,
                    onDeleteClick = {

                    },
                    onEditClick = {}
                )
            }
        }
    }
    
    if (showDialog){
        AlertDialog(
            onDismissRequest = { showDialog=false},
            confirmButton = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Button(
                        onClick = {
                            showDialog=false
                            sItems = sItems +  ShoppingItem(sItems.size+1, name = itemName, quantity = itemQunat.toInt(),isEditing = false)
                        }
                    ) {
                        Text(text = "Add")
                    }
                    Button(onClick = { showDialog=false}) {
                        Text(text = "Cancel")
                    }
                }

            },
            title = {
                Text(
                    text = "Add Shopping Item"
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange =  {itemName=it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    OutlinedTextField(
                        value = itemQunat,
                        onValueChange =  {itemQunat=it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        )
        
    }
}

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: ()-> Unit,
    onDeleteClick: ()-> Unit,
){
    Row (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, color = Color(0XFF018790)),
                shape = RoundedCornerShape(20)
            )
    ) {
        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))
        Row (
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}


@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int)-> Unit){
    var editedName by remember {
        mutableStateOf(item.name)
    }

    var editedQtd by remember {
        mutableStateOf(item.quantity.toString())
    }

    var isEditing by remember {
        mutableStateOf(item.isEditing)
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Column(

        ) {
            BasicTextField(
                value = editedName,
                onValueChange = {editedName=it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            ) {

            }

            BasicTextField(
                value = editedQtd,
                onValueChange = {editedQtd=it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            ) {

            }
        }

        Button(
            onClick = {
                isEditing = false
                onEditComplete (editedName, editedQtd.toIntOrNull()?:1)
            }
        ) {
            Text(text = "Save!")
        }
    }
}