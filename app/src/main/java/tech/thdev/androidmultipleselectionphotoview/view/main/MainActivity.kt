package tech.thdev.androidmultipleselectionphotoview.view.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tech.thdev.androidmultipleselectionphotoview.R
import tech.thdev.androidmultipleselectionphotoview.view.sample.SampleMainActivity

/**
 * Created by Taehwan on 21/02/2018.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_load_image.setOnClickListener {
            if (requestExternalPermission()) {
                startLoadImage()
            }
        }
    }

    private fun requestExternalPermission(): Boolean {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.w("TEMP", "ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ${ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)}")
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE)
            Toast.makeText(this, "get permission!!!!!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                grantResults.takeIf { it.isNotEmpty() && it[0] == PackageManager.PERMISSION_GRANTED }?.let {
                    Log.d("TEMP", "WRITE_EXTERNAL_STORAGE permission granted")
                    startLoadImage()
                }
            }
        }
    }

    private fun startLoadImage() {
        startActivity(Intent(this, SampleMainActivity::class.java))
    }
}