package maslovat.taniachifractal.digitcattle

import countBulls
import countCows

/**Generate a secret 4-digit code that the user will try to guess */
fun newSecretCode():String
{
    var secretCode = (123..9876).random()
    while (!is4differentDigits(secretCode))
    {
        secretCode = (123..9876).random()
    }
    return fourDigitToString(secretCode)
}
/**Convert 812, 345 to "0812","0345" instead of "812", "345"*/
private fun fourDigitToString(number: Int):String
{
    return if (number>999) "$number"
    else "0${number}"
}
