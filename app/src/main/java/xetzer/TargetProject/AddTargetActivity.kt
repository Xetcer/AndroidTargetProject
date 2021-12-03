package xetzer.TargetProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class AddTargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_target)
        val firstActivity = Intent(this, MainActivity::class.java)
        val addTargetEditText: EditText = findViewById(R.id.addTarget_editText)
        addTargetEditText.imeOptions = EditorInfo.IME_ACTION_DONE
        addTargetEditText.setOnEditorActionListener() { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                firstActivity.putExtra("NewTarget", addTargetEditText.text.toString())
                startActivity(firstActivity)
                true
            } else {
                false
            }
        }
    }
}