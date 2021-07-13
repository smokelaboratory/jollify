package com.smokelaboratory.jollify

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import android.view.View
import android.view.animation.Interpolator

//region base classes

/**
 * base classes for jolly operations
 */
sealed class BaseJolly {
    var startDelay: Long = 0L
    var duration: Long = 1000L
    var interpolator: Interpolator? = null
    var evaluator: TypeEvaluator<*>? = null
    var repeat: JollyRepetition? = null
    var listener: Animator.AnimatorListener? = null
}

sealed class Jolly : BaseJolly() {
    abstract val begin: Any
    abstract val end: Any
    abstract fun getValueHolder(): PropertyValuesHolder
}

abstract class Jollies : BaseJolly() {
    abstract fun getValueHolders(): List<PropertyValuesHolder>
}
//endregion

//region jollies

class TranslateX : Jolly() {
    override var begin: Float = 0f
    override var end: Float = 0f

    override fun getValueHolder(): PropertyValuesHolder = PropertyValuesHolder.ofFloat(
        View.TRANSLATION_X, begin, end
    )
}

class TranslateY : Jolly() {
    override var begin: Float = 0f
    override var end: Float = 0f

    override fun getValueHolder(): PropertyValuesHolder = PropertyValuesHolder.ofFloat(
        View.TRANSLATION_Y, begin, end
    )
}

class TranslateZ : Jolly() {
    override var begin: Float = 0f
    override var end: Float = 0f

    override fun getValueHolder(): PropertyValuesHolder = PropertyValuesHolder.ofFloat(
        View.TRANSLATION_Z, begin, end
    )
}

class ScaleX : Jolly() {
    override var begin: Float = 1f
    override var end: Float = 0f

    override fun getValueHolder(): PropertyValuesHolder = PropertyValuesHolder.ofFloat(
        View.SCALE_X, begin, end
    )
}

class ScaleY : Jolly() {
    override var begin: Float = 1f
    override var end: Float = 0f

    override fun getValueHolder(): PropertyValuesHolder = PropertyValuesHolder.ofFloat(
        View.SCALE_Y, begin, end
    )
}

class Rotate : Jolly() {
    override var begin: Float = 0f
    override var end: Float = 0f

    override fun getValueHolder(): PropertyValuesHolder = PropertyValuesHolder.ofFloat(
        View.ROTATION, begin, end
    )
}

class RotateX : Jolly() {
    override var begin: Float = 0f
    override var end: Float = 0f

    override fun getValueHolder(): PropertyValuesHolder = PropertyValuesHolder.ofFloat(
        View.ROTATION_X, begin, end
    )
}

class RotateY : Jolly() {
    override var begin: Float = 0f
    override var end: Float = 0f

    override fun getValueHolder(): PropertyValuesHolder = PropertyValuesHolder.ofFloat(
        View.ROTATION_Y, begin, end
    )
}

class Fade : Jolly() {
    override var begin: Float = 0f
    override var end: Float = 0f

    override fun getValueHolder(): PropertyValuesHolder = PropertyValuesHolder.ofFloat(
        View.ALPHA, begin, end
    )
}
//endregion

//region multiple jollies

//region parallel
class Together(private vararg var jollies: Jolly) : Jollies() {
    override fun getValueHolders(): List<PropertyValuesHolder> = jollies.map {
        it.getValueHolder()
    }
}
//endregion

//region serial
class Sequence(private vararg var jollies: BaseJolly) {
    fun getObjectAnimators(prepareObjectAnimator: (BaseJolly) -> ObjectAnimator): List<ObjectAnimator> =
        jollies.map {
            prepareObjectAnimator.invoke(it)
        }
}
//endregion

//endregion

//region repeat configuration
class JollyRepetition {
    var repeatMode: RepeatMode = RepeatMode.RESTART

    /**
     * [repeatCount] = null => infinite repetition
     */
    var repeatCount: Int? = null

    enum class RepeatMode {
        REVERSE, RESTART
    }
}
//endregion
