package com.example.chapter2kotlinBasis

/**
 * 扩展函数
 * fun ClassName.methodName(...)
 */
fun String.lettersCount() : Int{
    var count = 0
    for (char in this){
        if (char.isLetter())
            count++
    }
    return count
}