package hr.dhruza.iamu_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import hr.dhruza.iamu_application.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        binding = ActivityHostBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        configureToolbar()
        configureBottomBarNavigation()
        configureDrawerMenuNavigation()
    }


    private fun configureDrawerMenuNavigation() {
        val hostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = hostFragment.navController
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    private fun configureBottomBarNavigation() {
        val hostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = hostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }

    private fun configureToolbar() {
        val hostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = hostFragment.navController
        val builder = AppBarConfiguration.Builder(navController.graph)
        builder.setOpenableLayout(binding.drawerLayout)
        val appBarConfiguration = builder.build()

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        detectToolbarButtonPressed(item)
        val navController = findNavController(R.id.nav_host_fragment)
        return  item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }

    private fun detectToolbarButtonPressed(item: MenuItem) {
        when (item.itemId) {
            R.id.btnExit -> {
                exitApp()
            }
        }
    }

    private fun exitApp() {
       AlertDialog.Builder(this).apply {
           setTitle(R.string.exit)
           setMessage(getString(R.string.really))
           setIcon(R.drawable.ic_exit)
           setCancelable(true)
           setPositiveButton("Ok"){_, _  -> finish()}
           setNegativeButton("Cancel", null)
           show()
       }
    }
}