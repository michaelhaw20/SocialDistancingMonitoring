package com.example.socialdistancing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.socialdistancing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: EntryAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        supportActionBar?.title="Login"
        activityMainBinding.login.setOnClickListener{
            val username = activityMainBinding.name.text.toString()
            val password = activityMainBinding.pass.text.toString()
            if(username =="admin" && password =="admin"){
                val intent = Intent(this,ListActivity::class.java)
                startActivity(intent)
            }
            else{Toast.makeText(this,"username/password salah",Toast.LENGTH_SHORT).show()}
        }
    }
}