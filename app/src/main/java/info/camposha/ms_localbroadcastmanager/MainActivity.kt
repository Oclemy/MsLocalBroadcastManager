package info.camposha.ms_localbroadcastmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

//Our main activity
class MainActivity : AppCompatActivity(), View.OnClickListener {
    //override onCreate
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton.setOnClickListener(this)
    }

    //when activity is paused
    override fun onPause() {
        super.onPause()
        // unregister local broadcast
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver)
    }

    //when activity is resumed
    override fun onResume() {
        super.onResume()

        // register local broadcast
        val filter = IntentFilter(MyIntentService.CUSTOM_ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filter)
    }

    /**
    Create a BroadcastReceiver

     * BroadcastReceiver is a Base class for code that will receive intents sent by sendBroadcast().
     * Broadcast receiver to receive the data
     */
    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val date = intent.getStringExtra("DATE")
            dateText.text = date
        }
    }

    //when user clicks the start button, start our intent service
    override fun onClick(view: View) {
        if (view.id == R.id.startButton) {
            // start intent service
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
    }
}
