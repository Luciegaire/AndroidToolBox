package fr.isen.gaire.androidtoolbox2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_form.*
import org.json.JSONObject
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.io.File
import java.util.*
import android.app.AlertDialog
import java.text.SimpleDateFormat

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

        informationsbutton.setOnClickListener {
            read()
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
                Toast.makeText(this, getString(R.string.remplissez_champs), Toast.LENGTH_LONG).show()
            }
        }

        fun read(){

            val file = File(cacheDir.absolutePath+"/"+FormActivity.kFilename)
            val readingfile = file.readText()
            val json = JSONObject(readingfile)
            val NameString = json.getString(FormActivity.kNameKey)
            val SurnameString = json.getString(FormActivity.kSurnameKey)
            val dateString = json.getString(FormActivity.kBirthDay)
            val age = calculateage()
            val alertinfos : AlertDialog.Builder = AlertDialog.Builder(this)
            alertinfos.setTitle(getString(R.string.infos)).setMessage("Nom : ${NameString} \nPrénom : ${SurnameString} \nDate de naisance : ${dateString} \nAge : $age ans ")
            var alertDialog: AlertDialog = alertinfos.create()
            alertDialog.show()
        }

        fun calculateage():Int{
            var currentDate = Date()
            val formatter = SimpleDateFormat(getString(R.string.date) )
            val dateString1 = formatter.format(currentDate)
            val currentDateSplit = dateString1.split("/")
            val currentday = currentDateSplit[0].toInt()
            val currentmonth = currentDateSplit[1].toInt()
            val currentyear = currentDateSplit[2].toInt()

            val file = File(cacheDir.absolutePath+"/"+FormActivity.kFilename)
            val readingfile = file.readText()
            val json = JSONObject(readingfile)
            val dateString = json.getString(FormActivity.kBirthDay)
            val components = dateString.split("/")
            val birthday = components[0].toInt()
            val birthmonth = components[1].toInt()
            val birthyear = components[2].toInt()

            var age = currentyear - birthyear

            if((birthmonth > currentmonth) || (birthmonth == currentmonth && birthday < currentday)){
                age -= 1
            }
            return age
        }
}

