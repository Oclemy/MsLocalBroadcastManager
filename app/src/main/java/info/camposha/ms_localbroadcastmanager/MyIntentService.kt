package info.camposha.ms_localbroadcastmanager

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.util.*

//IntentService is a ntentService is a base class for Services that handle asynchronous
// requests (expressed as Intents) on demand.
class MyIntentService : IntentService("MyIntentService") {
    override fun onHandleIntent(arg0: Intent?) {
        val intent = Intent(CUSTOM_ACTION)
        //get date to send
        intent.putExtra("DATE", Date().toString())
        Log.d(MyIntentService::class.java.simpleName, "sending broadcast")

        // send local broadcast
        //LocalBroadcastManager is a Helper to register for and send broadcasts of Intents
        // to local objects within your process.
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    companion object {
        const val CUSTOM_ACTION = "YOUR_CUSTOM_ACTION"
    }
}