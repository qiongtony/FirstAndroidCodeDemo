package com.example.chapter2kotlinBasis

import java.util.*

fun main(args: Array<String>) {
    getMaxLengthFruit()

    fruitToUpperCase()
}

/**
 * 获取长度最长的水果名
 */
private fun getMaxLengthFruit() {
    val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    // 定义lambda最完整形式，参数名:类型... -> 函数体（最后一行是返回值，如果是Void怎么声明？
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
    val list = listOf<String>("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    // map传入的lambda参数是将T转成R，从一个值转换成另一个值
    val newList = list.map { it -> it.toUpperCase(Locale.getDefault()) }
    for (fruit in newList){
        println(fruit)
    }
}

class MyClass {

}