package hr.dhruza.iamu_application.framework

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import androidx.core.content.getSystemService
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import android.R
import android.net.Uri


inline fun<reified T : Activity> Context.startActivity()
        = startActivity(Intent(this, T::class.java).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
})

fun Context.setBooleanPreference(key: String, value: Boolean) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()


fun Context.getBooleanPreference(key: String): Boolean =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)


fun Context.isOnline() : Boolean {
    val connectivityManager  = getSystemService<ConnectivityManager>()

    connectivityManager?.activeNetwork?.let {  network ->
        connectivityManager.getNetworkCapabilities(network)?.let { networkCapabilities ->
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }

    return false;
}

fun callDelayed(delay: Long, function: Runnable){
    Handler(Looper.getMainLooper()).postDelayed(
        function,
        delay
    )
}

inline fun<reified T: BroadcastReceiver> Context.sendBroadcast()
    = sendBroadcast(Intent(this, T::class.java))
