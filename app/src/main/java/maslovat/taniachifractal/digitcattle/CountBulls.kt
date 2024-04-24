/** This function returns the number matching digits in 2 numbers - Bulls */
fun countBulls(input: String, reference: String):Int
{
    var output = 0

    val digitArrayFromInput = input.toCharArray()
    val digitArrayFromReference = reference.toCharArray()

    for (i in digitArrayFromInput.indices)
    {
        if (digitArrayFromInput[i]==digitArrayFromReference[i])
        {
            output++
        }
    }
    return output
}