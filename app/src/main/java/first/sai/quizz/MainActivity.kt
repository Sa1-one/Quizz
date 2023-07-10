package first.sai.quizz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import first.sai.quizz.R


class MainActivity : AppCompatActivity() {

    var backPtime: Long? = null
    var backToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart = findViewById<Button>(R.id.buttonStart)

        buttonStart.setOnClickListener {
            try {
                val intent = Intent(this, GameLevels::class.java)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            private var backPressedOnce = false

            override fun handleOnBackPressed() {
                if (backPressedOnce) {
                    backToast?.cancel()
                    isEnabled = false
                    finish()
                } else {
                    backPressedOnce = true
                    backToast = Toast.makeText(this@MainActivity, "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT)
                    backToast?.show()
                    Handler().postDelayed({ backPressedOnce = false }, 2000)
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)
    }
}
