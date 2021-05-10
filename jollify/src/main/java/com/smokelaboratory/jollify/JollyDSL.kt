package com.smokelaboratory.jollify

fun translateX(initialize: TranslateX.() -> Unit): TranslateX {
    val jolly = TranslateX()
    initialize.invoke(jolly)
    return jolly
}

fun translateY(initialize: TranslateY.() -> Unit): TranslateY {
    val jolly = TranslateY()
    initialize.invoke(jolly)
    return jolly
}

fun translateZ(initialize: TranslateZ.() -> Unit): TranslateZ {
    val jolly = TranslateZ()
    initialize.invoke(jolly)
    return jolly
}

fun scaleX(initialize: ScaleX.() -> Unit): ScaleX {
    val jolly = ScaleX()
    initialize.invoke(jolly)
    return jolly
}

fun scaleY(initialize: ScaleY.() -> Unit): ScaleY {
    val jolly = ScaleY()
    initialize.invoke(jolly)
    return jolly
}

fun rotate(initialize: Rotate.() -> Unit): Rotate {
    val jolly = Rotate()
    initialize.invoke(jolly)
    return jolly
}

fun rotateX(initialize: RotateX.() -> Unit): RotateX {
    val jolly = RotateX()
    initialize.invoke(jolly)
    return jolly
}

fun rotateY(initialize: RotateY.() -> Unit): RotateY {
    val jolly = RotateY()
    initialize.invoke(jolly)
    return jolly
}

fun fade(initialize: Fade.() -> Unit): Fade {
    val jolly = Fade()
    initialize.invoke(jolly)
    return jolly
}


/**
 * repetition
 */
fun repeat(initialize: JollyRepetition.() -> Unit): JollyRepetition {
    val repeatConfig = JollyRepetition()
    initialize.invoke(repeatConfig)
    return repeatConfig
}