package com.oguzhan.contactapp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhan.contactapp.R
import com.oguzhan.contactapp.presentation.screens.home.components.CustomSearchBar
import com.oguzhan.contactapp.presentation.screens.home.components.LazyRowComponent

@Composable
fun HomeScreen(
    navController: NavController,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToAddContact: (Boolean, Int?) -> Unit
) {

    var searchText = remember { mutableStateOf("") }
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val contactList = viewModel.contacts.observeAsState()

    val filteredContactList = remember(searchText.value, contactList.value) {
        if (searchText.value.isEmpty()) {
            contactList.value
        } else {
            contactList.value?.filter {
                it.name.contains(searchText.value, ignoreCase = true) ||
                        it.surname.contains(searchText.value, ignoreCase = true) ||
                        it.email.contains(searchText.value, ignoreCase = true) ||
                        it.phoneNumber.contains(searchText.value, ignoreCase = true)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Contacts",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                CustomSearchBar(searchText = searchText)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "Recent Added",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                contactList.value?.let { LazyRowComponent(it) }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "My Contacts (${contactList.value?.size})",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            filteredContactList?.let {
                items(it.size) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable {
                                contactList.value?.let { contactList ->
                                    onNavigateToDetail(
                                        filteredContactList[it].id
                                    )
                                }
                            }) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray), contentAlignment = Alignment.Center
                        ) {
                            filteredContactList[it].image?.let { it1 ->
                                if (it1.isEmpty()) {
                                    Text(
                                        text = "${
                                            filteredContactList[it].name.firstOrNull()?.uppercase()
                                        }${
                                            filteredContactList[it].surname.firstOrNull()
                                                ?.uppercase()
                                        }",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                } else {
                                    Image(
                                        painter = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        val text = if (filteredContactList[it].email.isNotEmpty()) {
                            filteredContactList[it].email
                        } else {
                            filteredContactList[it].phoneNumber
                        }
                        Column {
                            Text(
                                text = "${filteredContactList[it].name} ${filteredContactList[it].surname}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = text, fontSize = 14.sp, color = Color.Gray
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Column(Modifier.width(100.dp)) {
                            Button(
                                onClick = {
                                    viewModel.deleteContact(filteredContactList[it].id)
                                },
                                Modifier.fillMaxWidth()
                            ) {
                                Text("Sil")
                            }
                            Spacer(Modifier.height(10.dp))
                            Button(
                                onClick = {
                                    onNavigateToAddContact(true, filteredContactList[it].id)
                                },
                                Modifier.fillMaxWidth()
                            ) {
                                Text("Duzenle")
                            }
                        }
                    }
                    Spacer(Modifier.height(15.dp))
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { onNavigateToAddContact(false, null) },
            containerColor = Color(0xFFFF5722),
            contentColor = Color.White,
            shape = RoundedCornerShape(25.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contact")
        }
    }
}
