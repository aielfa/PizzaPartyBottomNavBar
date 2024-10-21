import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.AccountCircle
import androidx.navigation.NavHostController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun DrawerMenu(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
            .background(Color.DarkGray)
    ) {
        DrawerMenuItem(
            label = "Pizza Order",
            icon = Icons.Filled.ShoppingCart,
            onClick = {
                navController.navigate("pizzaScreen") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        DrawerMenuItem(
            label = "GPA App",
            icon = Icons.Filled.Info,
            onClick = {
                navController.navigate("gpaAppScreen") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        DrawerMenuItem(
            label = "Screen 3",
            icon = Icons.Filled.AccountCircle,
            onClick = {
                navController.navigate("screen3") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}

@Composable
fun DrawerMenuItem(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp).padding(end = 16.dp),
            tint = Color.White
        )
        Text(
            text = label,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}
