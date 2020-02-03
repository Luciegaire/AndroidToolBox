package fr.isen.gaire.androidtoolbox2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_life_cycle.*

class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        lifeCycleText.text = "onCreate"
    }

    override fun onStart() {
        super.onStart()
        lifeCycleText.text = "onStart"

    }
}
