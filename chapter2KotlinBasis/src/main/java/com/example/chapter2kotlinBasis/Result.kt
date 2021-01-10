package com.example.chapter2kotlinBasis

import java.lang.Exception

/**
 * 密封类实现可能结果的封装
 * 好处新增一个类时，编译器会自动告知我们需要处理所有的可能
 * 局限：只能定义在同一个文件的顶层
 */
sealed class Result
class Success(val msg : String) : Result()
class Failure(val error : Exception) : Result()

fun test(){
    /**
     * 传入一个结果，然后将两种可能的结果进行处理
     */
    fun getResultMsg(result: Result) = when(result){
        is Success -> result.msg
        is Failure -> "Error is ${result.error.message}"
    }
}