package fr.isen.gaire.androidtoolbox2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_form.*
import org.json.JSONObject
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.io.File
import java.util.*

class FormActivity : AppCompatActivity() {

    companion object {
        val kNameKey = "kFirstNameKey"
        val kSurnameKey = "kLastNameKey"
        val kBirthDay = "kBirthDay"
        val kFilename = "data.json"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        saveuserbutton.setOnClickListener {
            save()
        }
    }
        fun save() {
            if (NameeditText.text.toString().isNotEmpty() &&
                surnameeditText.text.toString().isNotEmpty() &&
                dateofbirtheditText.text.toString().isNotEmpty()) {
                val json = JSONObject()
                json.put(FormActivity.kNameKey, NameeditText.text.toString())
                json.put(FormActivity.kSurnameKey, surnameeditText.text.toString())
                json.put(FormActivity.kBirthDay, dateofbirtheditText.text.toString())
                Toast.makeText(this, json.toString(), Toast.LENGTH_LONG).show()

                val file = File(cacheDir.absolutePath+"/"+FormActivity.kFilename)
                file.writeText(json.toString())

            } else {
                Toast.makeText(this, "Remplissez les champs", Toast.LENGTH_LONG).show()
            }
        }
}

