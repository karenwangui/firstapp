//package net.ezra.ui.dashboard
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.SharedPreferences
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Refresh
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.google.firebase.auth.FirebaseAuth
//import net.ezra.R
//import net.ezra.navigation.ROUTE_ADD_PRODUCT
//import net.ezra.navigation.ROUTE_DASHBOARD
//import net.ezra.navigation.ROUTE_HOME
//import net.ezra.navigation.ROUTE_LOGIN
//import net.ezra.navigation.ROUTE_PROFILE
//import net.ezra.navigation.ROUTE_REGISTER
//import net.ezra.navigation.ROUTE_VIEW_PROD
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun UserDashboardScreen(navController: NavHostController) {
//    val context = LocalContext.current
//    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
//    val isLoggedIn: Boolean = sharedPreferences.getBoolean("isLoggedIn", false)
//    var userEmail by remember { mutableStateOf("No Email") }
//    var userName by remember { mutableStateOf("No Name") }
//
//    if (!isLoggedIn) {
//        LaunchedEffect(Unit) {
//            navController.navigate(ROUTE_LOGIN) {
//                popUpTo(navController.graph.startDestinationId) {
//                    inclusive = true
//                }
//            }
//        }
//    } else {
//        LaunchedEffect(Unit) {
//            val currentUser = FirebaseAuth.getInstance().currentUser
//            if (currentUser != null) {
//                userEmail = currentUser.email ?: "No Email"
//                userName = currentUser.displayName ?: "No Name"
//            }
//        }
//
//        Scaffold(
//            topBar = {
//                CenterAlignedTopAppBar(
//                    title = {
//                        Text(
//                            text = "Dashboard",
//                            fontSize = 20.sp,
//                            color = Color.White
//                        )
//                    },
//                    navigationIcon = {
//                        IconButton(onClick = { navController.popBackStack() }) {
//                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
//                        }
//                    },
//                    actions = {
//                        IconButton(onClick = { navController.navigate(ROUTE_REGISTER) }) {
//                            Icon(Icons.Default.Person, contentDescription = "Add Students", tint = Color.White)
//                        }
//                        IconButton(onClick = {
//                            navController.navigate(ROUTE_HOME) {
//                                popUpTo(navController.graph.startDestinationId) {
//                                    inclusive = true
//                                }
//                            }
//                        }) {
//                            Icon(Icons.Default.Refresh, contentDescription = "Refresh", tint = Color.White)
//                        }
//                        IconButton(onClick = {
//                            val editor = sharedPreferences.edit()
//                            editor.putBoolean("isLoggedIn", false)
//                            editor.apply()
//                            navController.navigate(ROUTE_LOGIN) {
//                                popUpTo(navController.graph.startDestinationId) {
//                                    inclusive = true
//                                }
//                            }
//                        }) {
//                            Icon(Icons.Default.Settings, contentDescription = "Logout", tint = Color.White)
//                        }
//                    },
//                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                        containerColor = Color.Gray
//                    )
//                )
//            },
//            content = {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.White)
//                        .padding(16.dp)
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .background(Color.White)
//                            .padding(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = "Welcome: $userName",
//                            style = MaterialTheme.typography.bodyLarge.copy(
//                                fontSize = 32.sp,
//                                color = Color.Gray,
//                                fontWeight = FontWeight.Bold
//                            ),
//                            modifier = Modifier.padding(bottom = 16.dp),
//                            textAlign = TextAlign.Center
//                        )
//
//                        Text(
//                            text = "Email: $userEmail",
//                            style = MaterialTheme.typography.bodyLarge.copy(
//                                fontSize = 20.sp,
//                                color = Color.Gray
//                            ),
//                            modifier = Modifier.padding(bottom = 16.dp),
//                            textAlign = TextAlign.Center
//                        )
//
//                        Button(
//                            onClick = {
//                                navController.navigate(ROUTE_HOME) {
//                                    popUpTo(navController.graph.startDestinationId) {
//                                        inclusive = true
//                                    }
//                                }
//                            },
//                            modifier = Modifier
//                                .padding(bottom = 16.dp)
//                                .fillMaxWidth(),
//                            colors = ButtonDefaults.buttonColors(Color.Gray)
//                        ) {
//                            Text("Go to Home", color = Color.White)
//                        }
//
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center
//                        ) {
//
//                            Image(
//                                painter = painterResource(id = R.drawable.home),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .clickable {
//                                        navController.navigate(ROUTE_HOME) {
//                                            popUpTo(ROUTE_PROFILE) { inclusive = true }
//                                        }
//                                    }
//                                    .size(100.dp)
//                                    .clip(RoundedCornerShape(10.dp))
//                            )
//                            androidx.compose.material3.Text(
//                                modifier = Modifier,
//                                text = "Home",
//                                textAlign = TextAlign.Center,
//                                fontSize = 20.sp,
//                                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
//                            )
//                            Column(
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                                verticalArrangement = Arrangement.Center
//                            ) {
//
//                                Image(
//                                    painter = painterResource(id = R.drawable.addproduct),
//                                    contentDescription = null,
//                                    modifier = Modifier
//                                        .clickable {
//                                            navController.navigate(ROUTE_ADD_PRODUCT) {
//                                                popUpTo(ROUTE_PROFILE) { inclusive = true }
//                                            }
//                                        }
//                                        .size(100.dp)
//                                        .clip(RoundedCornerShape(10.dp))
//                                )
//                                androidx.compose.material3.Text(
//                                    modifier = Modifier,
//                                    text = "Add Product",
//                                    textAlign = TextAlign.Center,
//                                    fontSize = 20.sp,
//                                    color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
//                                )
//                                Column(
//                                    horizontalAlignment = Alignment.CenterHorizontally,
//                                    verticalArrangement = Arrangement.Center
//                                ) {
//
//                                    Image(
//                                        painter = painterResource(id = R.drawable.clothes),
//                                        contentDescription = null,
//                                        modifier = Modifier
//                                            .clickable {
//                                                navController.navigate(ROUTE_VIEW_PROD) {
//                                                    popUpTo(ROUTE_PROFILE) { inclusive = true }
//                                                }
//                                            }
//                                            .size(100.dp)
//                                            .clip(RoundedCornerShape(10.dp))
//                                    )
//                                    androidx.compose.material3.Text(
//                                        modifier = Modifier,
//                                        text = "Dashboard",
//                                        textAlign = TextAlign.Center,
//                                        fontSize = 20.sp,
//                                        color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
//                                    )
//                        Button(
//                            onClick = {
//                                FirebaseAuth.getInstance().signOut()
//                                val editor = sharedPreferences.edit()
//                                editor.putBoolean("isLoggedIn", false)
//                                editor.apply()
//                                navController.navigate(ROUTE_LOGIN) {
//                                    popUpTo(navController.graph.startDestinationId) {
//                                        inclusive = true
//                                    }
//                                }
//                            },
//                            modifier = Modifier
//                                .padding(bottom = 16.dp)
//                                .fillMaxWidth(),
//                            colors = ButtonDefaults.buttonColors(Color.Red)
//                        ) {
//                            Text("Logout", color = Color.White)
//                        }
//                    }
//                }
//            }
//
//    } }
//            }
//        )}}