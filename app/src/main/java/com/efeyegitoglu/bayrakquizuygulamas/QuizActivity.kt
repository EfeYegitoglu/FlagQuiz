package com.efeyegitoglu.bayrakquizuygulamas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private lateinit var sorular: ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler: ArrayList<Bayraklar>
    private lateinit var dogruSoru: Bayraklar
    private lateinit var tumSecenekler: HashSet<Bayraklar>
    private lateinit var vt: Veritabani

    private var soruSayac = 0
    private var dogruSayac = 0
    private var yanlisSayac = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        vt = Veritabani(this)


        sorular = Bayraklardao().rasgele5BayrakGetir(vt)

        soruYukle()

        buttonA.setOnClickListener {
            dogruKontrol(buttonA)
            soruSayacKontrol()
        }

        buttonB.setOnClickListener {
            dogruKontrol(buttonB)
            soruSayacKontrol()
        }

        buttonC.setOnClickListener {
            dogruKontrol(buttonC)
            soruSayacKontrol()
        }

        buttonD.setOnClickListener {
            dogruKontrol(buttonD)
            soruSayacKontrol()
        }
    }

    fun soruYukle() {
        textSoruSayi.text = "${soruSayac + 1}. Soru"

        dogruSoru = sorular.get(soruSayac)

        imageBayrak.setImageResource(
            resources.getIdentifier(
                dogruSoru.bayrak_resim,
                "drawable",
                packageName
            )
        )

        yanlisSecenekler = Bayraklardao().rasgele3YanlisBayrakGetir(vt, dogruSoru.bayrak_id)

        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))

        buttonA.text = tumSecenekler.elementAt(0).bayrak_ad
        buttonB.text = tumSecenekler.elementAt(1).bayrak_ad
        buttonC.text = tumSecenekler.elementAt(2).bayrak_ad
        buttonD.text = tumSecenekler.elementAt(3).bayrak_ad
    }

    fun soruSayacKontrol() {
        soruSayac++
        if (soruSayac != 5) {
            soruYukle()
        } else {
            val intent=Intent(applicationContext, ResultActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            intent.putExtra("yanlisSayac",yanlisSayac)
            startActivity(intent)
            finish()
        }
    }

    fun dogruKontrol(button: Button) {
        val buttonYazi = button.text.toString()
        val dogruCevap = dogruSoru.bayrak_ad

        if (buttonYazi == dogruCevap) {
            dogruSayac++

        } else {
            yanlisSayac++
        }

        textDogru.text = "Doğru: $dogruSayac"
        textYanlis.text = "Yanlış: $yanlisSayac"
    }
}
