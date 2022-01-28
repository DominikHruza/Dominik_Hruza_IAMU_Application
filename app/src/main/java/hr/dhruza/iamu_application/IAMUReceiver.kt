package hr.dhruza.iamu_application

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.dhruza.iamu_application.framework.startActivity

class IAMUReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity<HostActivity>()
    }
}