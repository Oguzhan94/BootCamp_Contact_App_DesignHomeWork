package com.oguzhan.contactapp.presentation.screens.addContact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhan.contactapp.data.local.ContactEntity
import com.oguzhan.contactapp.presentation.navigation.Screen
import com.oguzhan.contactapp.presentation.screens.addContact.components.Header
import com.oguzhan.contactapp.presentation.screens.addContact.components.IconWithTextField
import com.oguzhan.contactapp.ui.theme.lightBlue

@Composable
fun AddContactScreen(navController: NavController, isEdit: Boolean, id: Int?) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val viewModel = hiltViewModel<AddContactViewModel>()
    val contact = viewModel.contact.observeAsState()

    if (isEdit && id != null) {
        viewModel.getContactById(id)
        contact.value?.let {
            if (name.isEmpty() && lastName.isEmpty() && phone.isEmpty() && email.isEmpty()) {
                name = it.name
                lastName = it.surname
                phone = it.phoneNumber
                email = it.email
            }
        }
    }
    val insets = WindowInsets.systemBars.asPaddingValues()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(insets)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .padding(horizontal = 5.dp)
        ) {
            Header(navController, isEdit) {
                if (name != "" || lastName != "") {
                    if (phone != "" || email != "") {
                        if (id != null) {
                            viewModel.updateContact(
                                ContactEntity(
                                    id = id,
                                    image = "",
                                    name = name,
                                    surname = lastName,
                                    phoneNumber = phone,
                                    email = email
                                )
                            )
                        } else {
                            viewModel.addContact(
                                ContactEntity(
                                    image = "",
                                    name = name,
                                    surname = lastName,
                                    phoneNumber = phone,
                                    email = email
                                )
                            )
                        }

                        navController.navigate(Screen.Home)
                    }
                }
            }
            Spacer(Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .background(lightBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Close",
                            tint = Color.Black,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Add Picture"
                    )
                }
                Spacer(Modifier.height(25.dp))

                IconWithTextField(
                    icon = Icons.Outlined.Person,
                    label = "First Name",
                    text = name,
                    onValueChange = { name = it })
                IconWithTextField(
                    label = "Last Name",
                    text = lastName,
                    onValueChange = { lastName = it })
                Spacer(Modifier.height(5.dp))
                IconWithTextField(
                    icon = Icons.Outlined.Phone,
                    label = "Phone",
                    text = phone,
                    onValueChange = { phone = it })
                Spacer(Modifier.height(5.dp))
                IconWithTextField(
                    icon = Icons.Outlined.Email,
                    label = "Email",
                    text = email,
                    onValueChange = { email = it })
                Spacer(Modifier.height(5.dp))
                IconWithTextField(
                    icon = Icons.Outlined.LocationOn,
                    label = "Location",
                    text = email,
                    onValueChange = { })
                Spacer(Modifier.height(5.dp))
                IconWithTextField(
                    icon = Icons.Outlined.Home,
                    label = "Home",
                    text = email,
                    onValueChange = { })
                Spacer(Modifier.height(5.dp))
                IconWithTextField(
                    icon = Icons.Outlined.Home,
                    label = "WhatsApp",
                    text = email,
                    onValueChange = { })
                Spacer(Modifier.height(5.dp))
                IconWithTextField(
                    icon = Icons.Outlined.Home,
                    label = "Telegram",
                    text = email,
                    onValueChange = { })
                Spacer(Modifier.height(5.dp))
                IconWithTextField(
                    icon = Icons.Outlined.Home,
                    label = "X",
                    text = email,
                    onValueChange = { })
                Spacer(Modifier.height(5.dp))
                IconWithTextField(
                    icon = Icons.Outlined.Home,
                    label = "Instagram",
                    text = email,
                    onValueChange = { })

            }
        }
    }
}
