import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.farmingdale.pizzapartybottomnavbar.BottomNavigationItems
import edu.farmingdale.pizzapartybottomnavbar.GpaAppScreen
import edu.farmingdale.pizzapartybottomnavbar.Screen3
import edu.farmingdale.pizzapartybottomnavbar.SplashScreen


@Composable
fun NavigationGraph(
    navController: NavHostController,
    onBottomBarVisibilityChanged: (Boolean) -> Unit
) {
   val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerMenu(navController = navController)
        }
    ) {
        NavHost(navController, startDestination = BottomNavigationItems.Welcome.route) {
            composable(BottomNavigationItems.Welcome.route) {
                onBottomBarVisibilityChanged(false)
                SplashScreen(navController = navController)
            }
            composable(BottomNavigationItems.PizzaScreen.route) {
                onBottomBarVisibilityChanged(true)
                PizzaPartyScreen(drawerState = drawerState)
            }
            composable(BottomNavigationItems.GpaAppScreen.route) {
                onBottomBarVisibilityChanged(true)
                GpaAppScreen()
            }
            composable(BottomNavigationItems.Screen3.route) {
                onBottomBarVisibilityChanged(true)
                Screen3()
            }
        }
    }
}
