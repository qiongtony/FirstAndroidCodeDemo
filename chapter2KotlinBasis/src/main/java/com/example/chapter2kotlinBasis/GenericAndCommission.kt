package com.example.chapter2kotlinBasis

import kotlin.reflect.KProperty

// 泛型和委托

/**
 * 泛型
 */
fun <T> T.build(block : T.() -> Unit) : T{
    block()
    return this
}

/**
 * 委托：分类：委托类和委托属性
 * 委托类即为包装类Wrapper
 */

// 1、委托类，比起Java实现，简化了相同代码的实现以及可能出现的遗漏
class SetWrapper<T>(val wrapper : HashSet<T>) : Set<T> by wrapper{
    // 这里除了要重写的类，其余方法都是以HashSet的实现为准
    override fun isEmpty(): Boolean {
        return false
    }
}

// 2、委托属性：
// 特点：属性的具体实现由某个类去完成
class MyClass{
    var p by Delegate()

}

class Delegate{
    var propValue : Any? = null

    /**
     * 第一个参数表明该Delegate类的委托功能可在什么类中使用
     */
    operator fun getValue(myClass: MyClass, property: KProperty<*>): Any? {
        return propValue
    }

    operator fun setValue(myClass: MyClass, property: KProperty<*>, any: Any) {
       propValue = property
    }

}

