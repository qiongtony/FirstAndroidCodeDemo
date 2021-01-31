package com.example.chapter2kotlinBasis

import java.lang.StringBuilder

/**
 * 高阶函数的完整的语法：
 * methodName : 定义类名.(函数参数类型) -> 返回类型，好处是高阶函数内使用能获取定义类的上下文
 * 本质：1、创建了一个静态方法，传入函数参类型，Function类实例对象---Fuction里的invoke方法即是高阶函数的“替身”（参数列表、具体执行逻辑、返回类型都包含在内)
 *      2、执行function.invoke，得到返回结果，返回
 * so：高阶函数会存在性能的开销（每次执行都需要创建匿名类Function的实例)
 * 解决办法：内联函数
 */
fun StringBuilder.build(block : StringBuilder.() -> Unit) : StringBuilder{
    block()
    return this
}


/**
 * 内联函数，在调用时会直接转换成具体的值
 * 优点：消除运行时开销
 */
inline fun num1AndNum2Inline(num1 : Int, num2 : Int, operation:(Int, Int) -> Int) : Int{
    return operation(num1, num2)
}

/**
 * Lambda函数block2禁止内联
 * why？
 */
inline  fun inlineTest(block1 : () -> Unit, noinline block2 : () -> Unit){

}

/**
 * 内联函数,return时不加高阶函数名，不可局部返回
 * 原因分析：内联会替换形参，而不内联是创建匿名类
 */
fun printString(str : String, block : (String ) ->Unit){
    println("printString begin")
    block(str)
    println("printString end")
}

inline fun  printStringInline(str : String, block : (String ) ->Unit){
    println("printStringInline begin")
    block(str)
    println("printStringInline end")
}


/**
 * 在Lambda表达式中不允许调用内联函数
 * why？
 * 因为内联函数支持函数返回，但Lambda表达式不可能支持函数返回，最多支持局部返回
 */
/*inline fun runRunnable(block : () -> Unit){
    val runnable = Runnable {
        block
    }
    runnable.run()
}*/

/**
 * 如何解决呢？
 * 用crossline关键字
 * 表明：我不会做函数返回的，只做局部返回（这样其实inline相当于不生效了）
 */
inline fun runRunnableCrossLine(crossinline block : () -> Unit){
    val runnable = Runnable {
        block()
    }
    runnable.run()
}

fun main() {
   /* val result = num1AndNum2Inline(100.toInt(), 80.toInt()){
        num1, num2 -> num1 - num2
    }

    println("main start")
    printString(""){
        str ->
        println("Lambda start")
        if (str.isEmpty()) return@printString
        println(str)
        println("lambda end")
    }

    println("main end")

    println()*/

    println("main start")

    printStringInline(""){
            str ->
        println("Lambda start")
        if (str.isEmpty()) return@printStringInline// 这里加的话是局部，不加是全局
        println(str)
        println("lambda end")
    }

    println("main end")

    runRunnableCrossLine(){
        // 只支持局部返回
        return@runRunnableCrossLine
    }

    Int.build {
        println("Int build")
    }

    Long.build {
        println("Long build")
    }
}