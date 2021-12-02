package xetzer.TargetProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var addTargetBtn: FloatingActionButton
    lateinit var closeApp: FloatingActionButton
    lateinit var curTargetTextView: TextView
    lateinit var targetList:List<TargetClass>
    var curTarget:Int = 0                                                                           // Это id сообщения, которое рандомно будет присваиваться из списка
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RestoreTargets()
        BindWidgets()
    }

    private fun RestoreTargets(){
        // TODO добавить функцию восставновления целей из памяти устройства и добавления их в коллекцию
        targetList = listOf(
            TargetClass("Измени образ мышления и ты именишь свою жизнь."),
            TargetClass("Что имеешь приносит выгоду, что не имеешь - пользу."),
            TargetClass("Путь воина - это гармония между действиями и решениями.")
        )
        curTarget = (0 until targetList.size).random()                                              // получим новую случайную цель
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
        curTargetTextView = findViewById(R.id.target_textView)
        if (curTarget != null){
            curTargetTextView.text = targetList[curTarget].target
        }
    }


}