package fi.jamk.sumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var number: Int = 0
    private var sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun numberInput(view: View) {
        // view is a button (pressed one) get text and convert to Int
        if (textViewResult.text == "0") {
            textViewResult.text= ""
        }
        val digit = (view as Button).text.toString().toInt()
        textViewResult.append(digit.toString())

    }

    fun clearScreen(view: View) {
        textViewResult.text = ""
        number=0
        sum=0
    }

    fun sumCalc(view: View) {

    }
}
