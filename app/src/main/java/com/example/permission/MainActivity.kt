package com.example.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val permission_list = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = ""
        for(permission in permission_list){
            //권한 여부 확인
            val chk = checkCallingOrSelfPermission(permission)
            if(chk == PackageManager.PERMISSION_GRANTED){ // 허용 되어있으면
                textView.append("$permission : 허용 \t\n")
            }else if(chk == PackageManager.PERMISSION_DENIED){
                textView.append("$permission : 거부 \t\n")
            }
        }
        btn.setOnClickListener {
            //거부되어 있는 권한들을 사용자에게 확인받는다.
            requestPermissions(permission_list, 0)
        }
    }

    //사용자에게 허용 여부를 확인 받으면 자동으로 호출되는 메소드
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        textView.text = ""
        for(idx in grantResults.indices){ // indices : 개수, size와 유사
            if(grantResults[idx] == PackageManager.PERMISSION_GRANTED){
                textView.append("${permissions[idx]} : 허용 \t\n")
            }else if(grantResults[idx] == PackageManager.PERMISSION_DENIED){
                textView.append("${permissions[idx]} : 거부 \t\n")
            }
        }
    }

}