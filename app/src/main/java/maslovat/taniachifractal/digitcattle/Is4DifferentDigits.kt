package maslovat.taniachifractal.digitcattle

/** This function returns True if the input integer is a 4 digits number with all digits different
(0123 is the smallest, 9876 is the biggest)*/
public fun is4differentDigits(input: Int): Boolean
{
    if (input<123 || input>9876) return false
    val digitArrayFromInput = input.toString().toCharArray()
    return digitArrayFromInput.distinct().size == 4
}