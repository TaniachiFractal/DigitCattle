package maslovat.taniachifractal.digitcattle

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import maslovat.taniachifractal.digitcattle.databinding.ActivityMainBinding
import android.graphics.Color
import countCows

/**Working with the form*/
class MainActivity : AppCompatActivity() {

    /** Link to the main layout activity_main.xml; "fld" means "field"*/
    private lateinit var fld: ActivityMainBinding

    private var secretCode = newSecretCode()
    private var currentInputCode = 0

    /** What's currently written into number input*/
    private var currentInputString = ""

    /** Screen dimensions */
    private var screenHeight = 0
    private var screenWidth = 0

    /**Loading*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // convert xml elements to widgets kotlin can work with
        fld = ActivityMainBinding.inflate(layoutInflater)
        setContentView(fld.root)

        fld.answer.text = secretCode.toString()

        // region initialise onClick event for digit buttons
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
        // endregion

        fld.btEnter.setOnClickListener{btEnterClick()}
        fld.btBackspace.setOnClickListener{removeDigit()}
    }

    private fun btEnterClick()
    {
        if (currentInputString.length<4) return

        currentInputCode = getInputDigits()
        clearInput()
    }

    /**Read input s*/
    private fun getInputDigits():Int
    {
        return fld.numberInput.text.toString().toInt()
    }
    /**fld.numberInput.text = currentInputString*/
    private fun updateInputTB()
    {
        fld.numberInput.text = currentInputString
    }
    /** Check if currentInputString is valid*/
    private fun currentInputValid(input: String):Boolean
    {
        if (input.length>4)
            return false
        else if(DigitAlreadyInString(input))
            return false
        return true
    }
    /** Type a digit into number input based on sender*/
    private fun typeDigit(digit: Int)
    {
        val newCurrentInputString = currentInputString+digit
        if (currentInputValid(newCurrentInputString)) {
            currentInputString = newCurrentInputString
        }
        updateInputTB()
    }
    /** Remove last digit from number input*/
    private fun removeDigit()
    {
        var newString = ""
        for(i in 0..<currentInputString.length-1)
        {
            newString+=currentInputString[i]
        }
        currentInputString=newString
        updateInputTB()
    }
    /**Reset input tb*/
    private fun clearInput()
    {
        currentInputString = ""
        updateInputTB()
    }
}