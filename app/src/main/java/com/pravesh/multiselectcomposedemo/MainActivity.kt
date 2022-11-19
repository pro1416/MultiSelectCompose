package com.pravesh.multiselectcomposedemo

import android.app.LauncherActivity.ListItem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pravesh.multiselectcomposedemo.ui.theme.MultiSelectComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiSelectComposeDemoTheme {
                var itemList by remember {
                    mutableStateOf(
                        (1..20).map {
                            MyListItem(
                                name = "Item $it",
                                isSelected = false
                            )
                        }
                    )
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = "${itemList.filter { item -> item.isSelected }.size} item selected")


                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 16.dp)
                    ) {
                        items(itemList.size) { i ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        itemList = itemList.mapIndexed() { k, item ->
                                            if (i == k) {
                                                item.copy(isSelected = !item.isSelected)
                                            } else {
                                                item
                                            }
                                        }
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = itemList[i].name)
                                if (itemList[i].isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = "checked"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class MyListItem(val name: String, var isSelected: Boolean)