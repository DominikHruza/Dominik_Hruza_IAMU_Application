package hr.dhruza.iamu_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import hr.dhruza.iamu_application.databinding.ActivitySplashScreenBinding
import hr.dhruza.iamu_application.framework.callDelayed
import hr.dhruza.iamu_application.framework.getBooleanPreference
import hr.dhruza.iamu_application.framework.isOnline
import java.time.Duration

const val DATA_IMPORTED = "hr.dhruza.iamu_application.data_imported"
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        binding.twSplash.startAnimation(
            AnimationUtils.loadAnimation(this, R.anim.blink)
        )

        binding.ivSplash.startAnimation(
            AnimationUtils.loadAnimation(this, R.anim.rotate)
        )
    }

    private fun redirect() {
        if(getBooleanPreference(DATA_IMPORTED)) {
            callDelayed(3000L) { startActivity(Intent(this, HostActivity::class.java)) }
        } else {
            if(isOnline()){
                Intent(this, IAMUService::class.java).apply {
                    IAMUService.enqueue(
                        this@SplashScreenActivity,
                        this
                    )
                }

            } else {
                binding.twSplash.isVisible = false
                Toast.makeText(this, "Connection failed!", Toast.LENGTH_LONG).show()
                callDelayed(3000L){finish()}
            }
        }
    }


}