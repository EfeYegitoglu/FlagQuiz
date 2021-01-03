package com.efeyegitoglu.bayrakquizuygulamas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val dogruSayac= intent.getIntExtra("dogruSayac",0)
        val yanlisSayac=intent.getIntExtra("yanlisSayac",0)

        textSonuc.text="$dogruSayac Doğru $yanlisSayac Yanlis"

        textSonucYuzde.text="%${20*dogruSayac} Başarı"

        buttonTekrarDene.setOnClickListener {
            startActivity(Intent(applicationContext,QuizActivity::class.java))
            finish()
        }
    }
}
