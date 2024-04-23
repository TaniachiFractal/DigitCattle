package maslovat.taniachifractal.digitcattle

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import countBulls
import countCows
import maslovat.taniachifractal.digitcattle.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var fld: ActivityMainBinding

    private var secretCode = newSecretCode()
    private var currentInputCode = 0

    private var currCowCount = 0
    private var currBullCount = 0

    private var triesCount = 0

    private var triesStrList = mutableListOf("")

    /** What's currently written into number input*/
    private var currentInputString = ""

    /** Array of cow sprites*/
    private var cowImg = arrayOf(R.drawable.cow_mura,R.drawable.cow_zlatka,R.drawable.cow_frederika,R.drawable.cow_moona)
    /** Array of bull sprites*/
    private var bullImg = arrayOf(R.drawable.bull_ugol,R.drawable.bull_kefir,R.drawable.bull_borka,R.drawable.bull_ketchup,)

    /**Load the game*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fld = ActivityMainBinding.inflate(layoutInflater)
        setContentView(fld.root)

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

        triesStrList.removeAt(0)
    }

    /**Check the user input*/
    private fun btEnterClick()
    {
        if (currentInputString.length<4) return

        currentInputCode = getInputDigits()
        clearInput()
        clearAllCowImg()
        currCowCount = countCows(currentInputCode,secretCode)
        currBullCount = countBulls(currentInputCode,secretCode)
        updateCowImg(currCowCount)
        updateBullImg(currCowCount,currBullCount)
        triesCount++
        updatePreviousTries()
        updateTriesCount()
    }

    private var prevTries_stringCount = 0
    /**Put the previous try into the previous tries list*/
    private fun updatePreviousTries()
    {
        triesStrList.add("${currentInputCode}\n  ${currCowCount}c${currBullCount}v")
        prevTries_stringCount+=2
        printPreviousTries()
    }

    private fun printPreviousTries()
    {
        if (triesStrList.size>5)
            triesStrList.removeAt(0)
        fld.previousTries.text=""
        for (i in 0..<triesStrList.size)
        {
            if (i==0)
                fld.previousTries.text="${triesStrList[i]}"
            else
                fld.previousTries.text="${fld.previousTries.text}\n\n${triesStrList[i]}"
        }
    }


    private fun updateTriesCount()
    {
        fld.triesCount.text=triesCount.toString()
        if (triesCount==100)
        {
            Toast.makeText(applicationContext,"Ты усердный. Но глупый. Или ты просто хотел узнать, что произойдет? Зашёл бы на гитхаб этого проекта.",Toast.LENGTH_LONG).show()
            fld.triesCount.setBackgroundResource(R.drawable.textinput_wood)
        }
        if (triesCount==10000)
        {
            Toast.makeText(applicationContext,"Как и почему. 10 тысяч?! За такое усердие я тебе сейчас обнулю попытки)))",Toast.LENGTH_LONG).show()
            triesCount=-1
            fld.triesCount.setBackgroundResource(R.drawable.triescount_wood)
        }
    }

    // region render cows

    /**Clear all cow views*/
    private fun clearAllCowImg()
    {
        for (i in 0..3) clearImg(i)
    }

    /**Load all cow images*/
    private fun updateBullImg(cows:Int,bulls:Int)
    {
        for (i in cows..<bulls+cows) setBullImg(i)
    }

    /**Load all cow images*/
    private fun updateCowImg(cows:Int)
    {
        for (i in 0..<cows) setCowImg(i)
    }

    /**Get a cow view depending on id*/
    private fun cowView(id: Int):ImageView
    {
        return when(id) {
            0-> fld.cow0
            1-> fld.cow1
            2-> fld.cow2
            3-> fld.cow3
            else-> fld.cow0
        }
    }

    /**Set cow img depending on view id*/
    private fun setCowImg(id: Int)
    {
        val cowView = cowView(id)
        cowView.setImageResource(cowImg[id])
    }
    /**Set bull img depending on view id*/
    private fun setBullImg(id: Int)
    {
        val cowView = cowView(id)
        cowView.setImageResource(bullImg[id])
    }
    /**Remove image from cow view*/
    private fun clearImg(id:Int)
    {
        val cowView = cowView(id)
        cowView.setImageResource(0)
    }
    // endregion

    // region input
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
    // endregion
}