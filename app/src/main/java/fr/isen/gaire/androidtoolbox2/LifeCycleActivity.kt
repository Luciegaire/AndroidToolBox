package fr.isen.gaire.androidtoolbox2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_life_cycle.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        lifeCycleText.text = "onCreate"

        val firstFragment = LifeCycleFragment()
        val secondFragment = LifeCycleFragment2()
        supportFragmentManager.beginTransaction().add(R.id.LifeCycleLayout, firstFragment).commit()

        buttonFragment.setOnClickListener {
            if(firstFragment.isResumed){
                buttonFragment.text = "Fragment 1"
                Log.d("TAG","Fragment 1 is resumed")
                supportFragmentManager.beginTransaction().replace(R.id.LifeCycleLayout, secondFragment).commit()
            }
            else{
                buttonFragment.text = "Fragment 2"
                Log.d("TAG","Fragment 2 is resumed")
                supportFragmentManager.beginTransaction().replace(R.id.LifeCycleLayout, firstFragment).commit()
            }
        }

    }
    override fun onStart() {
        super.onStart()
        lifeCycleText.text = "onStart"
        Log.d("TAG", "onStart")
    }
    override fun onResume() {
        super.onResume()
        lifeCycleText.text = "onResume"
        Log.d("TAG", "onResume")
    }
    override fun onPause() {
        super.onPause()
        lifeCycleText.text = "onPause"
        Log.d("TAG", "onPause")
    }
    override fun onStop() {
        super.onStop()
        lifeCycleText.text = "onStop"
        Log.d("TAG", "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG)
        Log.d("TAG", "onDestroy")
    }

}
