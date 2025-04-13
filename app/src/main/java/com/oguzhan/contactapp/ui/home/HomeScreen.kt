package com.oguzhan.contactapp.ui.home

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.oguzhan.contactapp.R
import com.oguzhan.contactapp.model.Contact
import com.oguzhan.contactapp.navigation.Screen
import com.oguzhan.contactapp.ui.home.components.CustomSearchBar
import com.oguzhan.contactapp.ui.home.components.LazyRowComponent

val contactList = mutableListOf(
    Contact(
        image = "",
        name = "İhsan",
        surname = "Arslan",
        email = "ihsan.arslan@gmail.com",
        phoneNumber = "+90 111 111 11 11"
    ), Contact(
        image = "",
        name = "Ayşe",
        surname = "Yılmaz",
        email = "ayse.yilmaz@hotmail.com",
        phoneNumber = "+90 111 111 11 11"
    ), Contact(
        image = "",
        name = "Mehmet",
        surname = "Kaya",
        email = "mehmetkaya@outlook.com",
        phoneNumber = "+90 111 111 11 11"
    ), Contact(
        image = "",
        name = "Zeynep",
        surname = "Demir",
        email = "z.demir@company.com",
        phoneNumber = "+90 111 111 11 11"
    ), Contact(
        image = "",
        name = "Can",
        surname = "Öztürk",
        email = "can.ozturk@gmail.com",
        phoneNumber = "+90 111 111 11 11"
    ), Contact(
        image = "",
        name = "Elif",
        surname = "Çelik",
        email = "elif.celik@yahoo.com",
        phoneNumber = "+90 111 111 11 11"
    ), Contact(
        image = "",
        name = "Burak",
        surname = "Şahin",
        email = "buraksahin@gmail.com",
        phoneNumber = "+90 111 111 11 11"
    ), Contact(
        image = "",
        name = "Selin",
        surname = "Yıldız",
        email = "selinyildiz@company.com",
        phoneNumber = "+90 111 111 11 11"
    ), Contact(
        image = "",
        name = "Emre",
        surname = "Aydın",
        email = "emre.aydin@outlook.com",
        phoneNumber = "+90 111 111 11 11"
    ), Contact(
        image = "",
        name = "Deniz",
        surname = "Koç",
        email = "deniz.koc@gmail.com",
        phoneNumber = ""
    ), Contact(
        image = "",
        name = "Eylul",
        surname = "Koç",
        email = "deniz.koc@gmail.com",
        phoneNumber = ""
    ), Contact(
        image = "",
        name = "Kenan",
        surname = "Koç",
        email = "deniz.koc@gmail.com",
        phoneNumber = ""
    )
)

@Composable
fun HomeScreen(navController: NavController, onNavigateToDetail: (Int) -> Unit) {

    var searchText = remember { mutableStateOf("") }

    val filteredContactList = remember(searchText.value) {
        if (searchText.value.isEmpty()) {
            contactList
        } else {
            contactList.filter {
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
                LazyRowComponent(kisiList = contactList)
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "My Contacts (${contactList.size})",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(filteredContactList.size) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable {
                            onNavigateToDetail(filteredContactList.indexOf(contactList[it]))
                        }) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray), contentAlignment = Alignment.Center
                    ) {
                        if (filteredContactList[it].image.isEmpty()) {
                            Text(
                                text = "${
                                    filteredContactList[it].name.firstOrNull()?.uppercase()
                                }${filteredContactList[it].surname.firstOrNull()?.uppercase()}",
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
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { navController.navigate(Screen.AddContact) },
            containerColor = Color(0xFFFF5722),
            contentColor = Color.White,
            shape = RoundedCornerShape(25.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contact")
        }
    }
}
