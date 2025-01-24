package kambi.victor.walenje.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kambi.victor.walenje.core.authentication.Biometrics
import kambi.victor.walenje.feature.home.HomeScreen
import kambi.victor.walenje.feature.welcome.SecureWalletScreen
import kambi.victor.walenje.feature.welcome.SetPinScreen
import kambi.victor.walenje.feature.welcome.Welcome
import kotlinx.serialization.Serializable

sealed interface Route {
  @Serializable data object Welcome : Route

  @Serializable data object SecureWallet : Route

  @Serializable data object SetPin : Route

  @Serializable data object Home : Route

  @Serializable data object Analytics : Route

  @Serializable data object Profile : Route
}

@Composable
fun WalenjeNavGraph(
  navController: NavHostController = rememberNavController(),
  biometrics: Biometrics,
) {
  NavHost(navController = navController, startDestination = Route.Home) {
    composable<Route.Welcome> {
      Welcome(onNavigateToSecureWallet = { navController.navigate(Route.SecureWallet) })
    }
    composable<Route.SecureWallet> {
      SecureWalletScreen(
        onNavigateBack = { navController.navigateUp() },
        onNavigateToSetPin = { navController.navigate(Route.SetPin) },
        configureBiometrics = {},
        biometrics = biometrics,
        onNavigateToNext = {
          navController.popBackStack(Route.Welcome, true)
          navController.navigate(Route.Home)
        },
      )
    }
    composable<Route.SetPin> {
      SetPinScreen(
        onNavigateBack = { navController.navigateUp() },
        onNavigateToHomeScreen = {
          navController.popBackStack(Route.Welcome, true)
          navController.navigate(Route.Home)
        },
      )
    }
    composable<Route.Home> { HomeScreen(onNavigateBack = { navController.navigateUp() }) }
    composable<Route.Analytics> {}
    composable<Route.Profile> {}
  }
}
