package fr.isen.gaire.androidtoolbox2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.graphics.Bitmap
import kotlinx.android.synthetic.main.activity_informations.*
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class InformationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informations)

        pickimagebutton.setOnClickListener{
            val imagefromgalleryIntent = Intent(Intent.ACTION_PICK)
            imagefromgalleryIntent.setType(getString(R.string.img))

            val imagefromcameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            val chooseIntent = Intent.createChooser(imagefromgalleryIntent, getString(R.string.gallery))
            chooseIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(imagefromcameraIntent))
            startActivityForResult(chooseIntent,11)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK ){
            if(data?.data == null){
                val bitmap = data?.extras?.get(getString(R.string.data)) as? Bitmap
                bitmap?.let{
                    pickimagebutton.setImageBitmap(it)
                }
            }else{
                pickimagebutton.setImageURI(data?.data)

            }

        }
    }

    fun requestPermission(permissionToRequest: String, requestCode: Int, handler: ()-> Unit) {
        if(ContextCompat.checkSelfPermission(this, permissionToRequest) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissionToRequest)) {
                //display toast
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permissionToRequest), requestCode)
            }
        } else {
            handler()
        }
    }
}
