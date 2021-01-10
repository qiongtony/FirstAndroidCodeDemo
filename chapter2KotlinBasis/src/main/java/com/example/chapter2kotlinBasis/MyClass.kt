package com.example.chapter2kotlinBasis

import java.lang.StringBuilder
import java.util.*

fun main(args: Array<String>) {
    getMaxLengthFruit()

    fruitToUpperCase()

    fruit2filter()

    fruitAnyAndAll()

    kotlinCallJavaFunctionApi()

    withRunAndApply()

    example { content, count ->
        run {
            println("字符串是$content 数量是$count")
        }
    }
    val num1 = 100
    val num2 = 80
    // 这里有点像Callback？ ::funName 表示调用函数的引用，即将函数作为参数传入
    val add = num1AndNum2(num1, num2, ::plus)
    val minus = num1AndNum2(num1, num2, ::minus)

    // Lambda简化版本
    val addLambda = num1AndNum2(num1, num2){
        n1, n2 ->
        n1 + n2
    }

    val minusLambda = num1AndNum2(num1, num2){
        n1, n2 ->
        n1 - n2
    }

    println("addResult = $add minusResult = $minus addLambda = $addLambda minusLambda = $minusLambda")


    higherTest()

}

/**
 * 获取长度最长的水果名
 */
private fun getMaxLengthFruit() {
    val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    // 定义lambda最完整形式，参数名:类型... -> 函数体（最后一行是返回值，如果是Void怎么声明？这里应该是没有空的情况，因为你需要用
    // Lambda的返回值去进行一些处理，不然你为什么要用呢？
    // 总结Lambda很像是一种条件，通过它对原始数据做一些转化，一种通用的抽象，比如这里表达的意思是我们比较的尺度：长度
    val lambda = { fruit: String -> fruit.length }
    // maxBy要的是一个lambda表达式，这个表达式传入字符串可以返回长度，然后maxBy根据长度找出最长的字符串返回
    val maxLengthFruit = list.maxBy(lambda)
    println("maxLengthFruit = $maxLengthFruit")
}

private fun getMaxLengthFruitSimplify(){
    val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    // 参数的类型大多可以自动推导，Lambda是函数的最后一个参数时，可以放在外面
    // 感觉放在外面不太好理解啊？
    val maxLengthFruit = list.maxBy(){ fruit -> fruit.length}
    println("maxLengthFruit = $maxLengthFruit")
}

/**
 * 将水果列表里每个水果名称换成大写
 */
private fun fruitToUpperCase(){
    println("fruitToUpperCase=========================")
    val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    // map传入的lambda参数是将T转成R，从一个值转换成另一个值
    val newList = list.map { it -> it.toUpperCase(Locale.getDefault()) }
    for (fruit in newList){
        println(fruit)
    }
    println("fruitToUpperCase=========================")
}

/**
 * filter过略
 * 编写的过滤条件，字符串长度不大于5的水果，且转化为大写
 */
private fun fruit2filter(){
    println("fruit2filter=========================")
    val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val condication = {fruit : String -> fruit.length <=5 }
    val newList = list.filter(condication)
        .map { it.toUpperCase() }
    for (fruit in newList){
        println(fruit)
    }
    println("fruit2filter=========================")
}

/**
 * any和all
 * any，水果里是否存在长度不大于5的
 * all，水果里是否都是长度不大于5的
 */
private fun fruitAnyAndAll(){
    println("fruitAnyOrAll=========================")
    val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    println("anyResult = ${anyResult} allResult = $allResult")
    println("fruitAnyOrAll=========================")
}


/**
 * 在Kotlin调用Java函数式API
 * 条件：1、接口
 *      2、单方法
 * 从这里也可以看出Lambda可以是没有返回值的
 */
private fun kotlinCallJavaFunctionApi(){
    Thread {
        println("threadName = ${Thread.currentThread().name} is running")
    }.start()
}

/**
 * kotlin的标准函数：存放在standard.kt里的函数
 * with简化函数调用，最后一行是返回结果
 * run和with类似，只是它只能被对象调用，传入的是Lambda函数
 * apply与run类似，但Lambda的返回值是对象本身
 */
private fun withRunAndApply(){
    println("withRunAndApply=========================")
    val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val str = with(StringBuilder()){
        append("Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        toString()
    }
    println(str)

    val runStr = StringBuilder().run {
        append("Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        toString()
    }
    println("runResult = ${runStr}")

    val applyResult = StringBuilder().apply {
        append("Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println("applyResult = ${applyResult.toString()}")
    println("withRunAndApply=========================")
}

/**
 * example是高阶函数，根据"->"可以划分成两边，左边定义传入的参数类型，右边定义返回值
 */
fun example(func : (String, Int) -> Unit){
    func("hello", 123)
}

fun num1AndNum2(num1 : Int, num2 : Int, operation : (Int, Int) -> Int) : Int{
    val result = operation(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int) : Int{
    return num1 + num2
}

fun minus(num1 : Int, num2: Int) : Int{
    return num1 - num2
}

fun higherTest(){
    println("higherTest======================")
    val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())
    println("higherTest======================")
}

class MyClass {

}