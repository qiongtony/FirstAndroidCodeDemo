package com.example.chapter2kotlinBasis

import java.lang.StringBuilder

/**
 * 高阶函数的完整的语法：
 * methodName : 定义类名.(函数参数类型) -> 返回类型，好处是函数内使用能获取定义类的上下文
 */
fun StringBuilder.build(block : StringBuilder.() -> Unit) : StringBuilder{
    block()
    return this
}