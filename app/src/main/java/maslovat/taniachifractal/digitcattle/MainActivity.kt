package maslovat.taniachifractal.digitcattle

import android.R
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import maslovat.taniachifractal.digitcattle.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    /** Link to the main layout activity_main.xml; "fld" means "field"*/
    private lateinit var fld: ActivityMainBinding

    /** What's currently written into number input*/
    private var currentInputString = ""

    lateinit var
    /** Screen dimensions */
    private var screenHeight = 0
    private var screenWidth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // convert xml elements to widgets kotlin can work with
        fld = ActivityMainBinding.inflate(layoutInflater)
        setContentView(fld.root)

        // region get screen dimens
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight = displayMetrics.heightPixels
        screenWidth = displayMetrics.widthPixels
        // endregion get screen dimens

        // region initialise digit buttons
        fld.bt1.setOnClickListener{typeDigit(1)}
        fld.bt2.setOnClickListener{typeDigit(2)}
        fld.bt3.setOnClickListener{typeDigit(3)}
        fld.bt4.setOnClickListener{typeDigit(4)}
        fld.bt5.setOnClickListener{typeDigit(5)}
        fld.bt6.setOnClickListener{typeDigit(6)}
        fld.bt7.setOnClickListener{typeDigit(7)}
        fld.bt8.setOnClickListener{typeDigit(8)}
        fld.bt9.setOnClickListener{typeDigit(9)}
        fld.bt0.setOnClickListener{typeDigit(0)}
        // endregion initialise backspace button



        fld.btBackSpace.setOnClickListener{removeDigit()}

    }
    /** Check if currentInputString is valid*/
    private fun currentInputValid():Boolean
    {
        if (currentInputString.length>3)
            return false
        else
        {

        }
        return true
    }
    /** Type a digit into number input based on sender*/
    private fun typeDigit(digit: Int)
    {
        if (!currentInputValid()) return

        currentInputString += digit
        fld.numberInput.text = currentInputString
    }
    /** Remove a digit from number input*/
    private fun removeDigit()
    {
        var newString = ""
        for(i in 0..<currentInputString.length-1)
        {
            newString+=currentInputString[i]
        }
        currentInputString=newString
        fld.numberInput.text = currentInputString
    }

}