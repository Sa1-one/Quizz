package first.sai.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback


class GameLevels : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gamelevels)

        val buttonBack = findViewById<Button>(R.id.button_back)
        buttonBack.setOnClickListener {
            try {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                try {
                    val intent = Intent(this@GameLevels, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        val textView1 = findViewById<TextView>(R.id.textView1)
        textView1.setOnClickListener {
            try {
                val intent = Intent(this, Level2one::class.java)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)
    }
}
