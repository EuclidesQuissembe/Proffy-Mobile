package ao.co.euclidesquissembe.proffy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ao.co.euclidesquissembe.proffy.R
import kotlinx.android.synthetic.main.act_main.*

class ProffyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        study.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        teach.setOnClickListener {
            val intent = Intent(this, TeachActivity::class.java)
            startActivity(intent)
        }
    }
}