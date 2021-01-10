package com.example.chapter2kotlinBasis

/**
 * Kotlin中创建静态方法的方式：
 * 1、object类里的方法都是静态的
 *          缺点：这里是不是和单例有点冲突了呢
 *                所有的方法都是静态的，局限性强
 * 2、companion object里声明的方法
 *          缺点：在Java里也想和Kotlin一样用需要加@JvmStatic注解而且性能上有一定损失
 * 3、顶层方法：
 *          在.kt class外面定义的方法
 *          优势：koltin里哪里都可以调用，而不需要指定类名（这里的问题是太抽象了，没有类名来告知具体是哪一方面的工具方法）
 *               java里，文件名.funName(...)，这其实就和Java的工具类调用是一样了
 */

fun doSomething(){
    println("do something")
}

class Util {
    fun  doFunction1(){
        println("do action 1")
        doAction2()
        doSomething()
    }

    companion object{

        // 如果不加，在Java中就不能Util.doAction2这么调了，实际上也是调用该伴生类对象的doAction2方法
        // 只是在Util里加了一个名称一样的静态方法，去调用伴生类对象方法=。=，好低效这样
        @JvmStatic
        fun doAction2(){
            println("do action 2")
        }
    }
}