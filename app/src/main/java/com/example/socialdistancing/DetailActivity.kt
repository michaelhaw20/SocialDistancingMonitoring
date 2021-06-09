package com.example.socialdistancing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.alphabangkit.socialdistancing.databinding.ActivityDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var detailbinding: ActivityDetailBinding
    companion object {
        const val EXTRA_USER = "extra_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailbinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailbinding.root)
        supportActionBar?.title = "Detail Pelanggaran"
        val urlphoto = intent.getStringExtra(EXTRA_USER)
        Glide.with(applicationContext)
            .load(urlphoto)
            .apply(RequestOptions())
            .into(detailbinding.imgDetail)
        fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }
        fun getCurrentDateTime() : Date {
            return Calendar.getInstance().time
        }
        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy/MM/dd")
        detailbinding.tanggalDetail.text = dateInString
        detailbinding.btnNotif.setOnClickListener {
            /*
            val intentnot = Intent(this@DetailActivity, NotifActivity::class.java)
            startActivity(intentnot)*/

            val moveWithObjectIntent = Intent(this@DetailActivity, NotifActivity::class.java)
                moveWithObjectIntent.putExtra(NotifActivity.EXTRA_IMAGE, urlphoto)
                Log.d("mbuh", urlphoto.toString())
                startActivity(moveWithObjectIntent)

        }
    }
}
