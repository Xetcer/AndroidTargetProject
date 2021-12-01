package xetzer.TargetProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var addTargetBtn: FloatingActionButton
    lateinit var closeApp: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BindWidgets()
    }

    // secondActivityIntent.putExtra(HELLO_KEY, "Hello from main activity")
    private fun BindWidgets() {
        addTargetBtn = findViewById(R.id.addTarget_btn)
        addTargetBtn.setOnClickListener {
            val secondActivityIntent: Intent = Intent(this, AddTargetActivity::class.java)
            startActivity(secondActivityIntent)
        }
        closeApp = findViewById(R.id.closeApp_btn)
        closeApp.setOnClickListener {
            finish()
        }
    }


}