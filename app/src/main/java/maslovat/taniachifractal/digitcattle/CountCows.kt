/** This function returns the number of matching digits on wrong positions in 2 numbers - Cows*/
fun countCows(input: String, reference: String):Int
{
    var output = 0

    val digitArrayFromInput = input.toCharArray()
    val digitArrayFromReference = reference.toCharArray()

    val maskOfInput =     "qwertyuiop".toCharArray()
    val maskOfReference = "asdfghjklz".toCharArray()

    for (digit in digitArrayFromInput)
    {
        maskOfInput[digit.toString().toInt()] = digit
    }
    for(digit in digitArrayFromReference)
    {
        maskOfReference[digit.toString().toInt()] = digit
    }

    for (i in 0..9)
    {
        if (maskOfInput[i]==maskOfReference[i])
        {
            output++
        }
    }
    output-=countBulls(input,reference)
    return output
}