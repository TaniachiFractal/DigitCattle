package maslovat.taniachifractal.digitcattle

/**Returns true if the digit has already been placed into the string*/
public fun DigitAlreadyInString(input: String): Boolean
{
    val digitArrayFromInput = input.toCharArray()
    for(i in (0..digitArrayFromInput.size-1))
    {
        for (j in (i+1..digitArrayFromInput.size-1))
        {
            if (digitArrayFromInput[i]==digitArrayFromInput[j]) return true
        }
    }
    return false
}