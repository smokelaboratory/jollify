package com.smokelaboratory.jollify

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator.RESTART
import android.animation.ValueAnimator.REVERSE
import android.view.View
import android.view.animation.Animation

private fun View.prepareObjectAnimator(jolly: BaseJolly) = ObjectAnimator.ofPropertyValuesHolder(
    this,
    *when (jolly) {
        is Jolly -> arrayOf(jolly.getValueHolder())
        is Jollies -> jolly.getValueHolders().toTypedArray()
    }
).apply {
    duration = jolly.duration
    setEvaluator(jolly.evaluator)
    interpolator = jolly.interpolator

    jolly.repeat?.let {
        repeatMode = when (it.repeatMode) {
            JollyRepetition.RepeatMode.RESTART -> RESTART
            JollyRepetition.RepeatMode.REVERSE -> REVERSE
        }

        repeatCount = it.repeatCount ?: Animation.INFINITE
    }

    jolly.listener?.let {
        addListener(it)
    }
}

fun View.jollify(jolly: Jolly) {
    clearAnimation()

    prepareObjectAnimator(jolly).start()
}

fun View.jollify(jollies: Together) {
    clearAnimation()

    prepareObjectAnimator(jollies).start()
}

fun View.jollify(jollies: Sequence) {
    clearAnimation()

    AnimatorSet().apply {
        playSequentially(jollies.getObjectAnimators {
            prepareObjectAnimator(it)
        })
        start()
    }
}