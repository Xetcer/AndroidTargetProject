package xetzer.TargetProject

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

public const val ADD_TARGET_TAG = "NewTarget"

class MainActivity : AppCompatActivity() {
    private lateinit var addTargetBtn: FloatingActionButton
    lateinit var closeApp: FloatingActionButton
    lateinit var curTargetTextView: TextView
    lateinit var targetTimetextView: TextView
    lateinit var targetList: MutableList<TargetClass>

    // Это id сообщения, которое рандомно будет присваиваться из списка
    var curTarget: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restoreTargets()
        bindWidgets()
        // Получаем новую цель из AddTargetActivity
        val newTarget: String? = intent.extras?.getString(ADD_TARGET_TAG, "Нет цели")
        val dateTime: String = getDayTime()
        if (newTarget != null){
            if (newTarget != R.string.NoTarget.toString()) {
                // Поищем к коллеции повторы и если найдем то не будет добавлять новую цель.
                var repeatSearchFlag : Boolean = false
                for (item in targetList){
                    if (item.target == newTarget.toString()){
                        repeatSearchFlag = true
                        break
                    }
                }
                if (!repeatSearchFlag){
                    targetList.add(TargetClass(newTarget.toString(), dateTime))
                }
            }
        }
    }

    private fun getDayTime(): String {
        val dayTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val date = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
            date.format(formatter)
        } else {
            val date = Date()
            val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
            formatter.format(date)
        }
        return dayTime
    }

    private fun restoreTargets() {
        // TODO добавить функцию восставновления целей из памяти устройства и добавления их в коллекцию
        val dateTime = getDayTime()
        targetList = mutableListOf(
            TargetClass("Измени образ мышления и ты именишь свою жизнь.", dateTime),
            TargetClass("Что имеешь приносит выгоду, что не имеешь - пользу.", dateTime),
            TargetClass("Путь воина - это гармония между действиями и решениями.", dateTime)
        )
        // получим новую случайную цель
        curTarget = (0 until targetList.size).random()
    }

    // secondActivityIntent.putExtra(HELLO_KEY, "Hello from main activity")
    private fun bindWidgets() {
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
        targetTimetextView = findViewById(R.id.targetTime_textView)
        if (curTarget != null) {
            curTargetTextView.text = targetList[curTarget].target
            targetTimetextView.text = targetList[curTarget].dateTime
        }
    }
}