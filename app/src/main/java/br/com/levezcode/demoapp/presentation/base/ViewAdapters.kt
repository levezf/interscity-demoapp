package br.com.levezcode.demoapp.presentation.base

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import androidx.databinding.BindingAdapter
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

@BindingAdapter("isLoading")
fun setIsLoading(view: View, isLoading: Boolean) {
    val animation: Animation? = view.animation
    if (isLoading && animation == null) {
        view.startAnimation(createRotateAnimation())
    } else if (animation != null) {
        animation.cancel()
        view.animation = null
    }
}

private fun createRotateAnimation(): Animation {
    val anim = RotateAnimation(
        0F,
        360F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        Animation.RELATIVE_TO_SELF,
        0.5F
    )
    anim.interpolator = FastOutSlowInInterpolator()
    anim.duration = 500
    anim.repeatCount = TranslateAnimation.INFINITE
    anim.repeatMode = TranslateAnimation.RESTART
    return anim
}
