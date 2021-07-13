package com.smokelaboratory.jollify

import android.animation.Animator
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
    startDelay = jolly.startDelay
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

fun View.jollify(jolly: Jolly): Animator {
    clearAnimation()

    return prepareObjectAnimator(jolly).apply {
        start()
    }
}

fun View.jollify(jollies: Together): Animator {
    clearAnimation()

    return prepareObjectAnimator(jollies).apply {
        start()
    }
}

fun View.jollify(jollies: Sequence): Animator {
    clearAnimation()

    return AnimatorSet().apply {
        playSequentially(jollies.getObjectAnimators {
            prepareObjectAnimator(it)
        })
        start()
    }
}