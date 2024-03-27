package maslovat.taniachifractal.digitcattle

import countBulls
import countCows

/**Generate a secret 4-digit code that the user will try to guess */
fun newSecretCode():Int
{
    var secretCode = (123..9876).random()
    while (!is4differentDigits(secretCode))
    {
        secretCode = (123..9876).random()
    }
    return secretCode
}

