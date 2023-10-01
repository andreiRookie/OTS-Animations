package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var purpleCircle: CircleView
    private lateinit var fuchsiaCircle: CircleView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        purpleCircle = findViewById(R.id.circle_1)
        fuchsiaCircle = findViewById(R.id.circle_2)

        startAnimation(purpleCircle, fuchsiaCircle)
    }

    private fun startAnimation(firstCircle: CircleView, secondCircle: CircleView) {

        val firstCircleRadius = PropertyValuesHolder.ofFloat(
            RADIUS_VALUE,
            firstCircle.radius,
            firstCircle.radius + 50f,
            firstCircle.radius,
            firstCircle.radius,
            firstCircle.radius
        )

        val firstCircleX = PropertyValuesHolder.ofFloat(
            X_VALUE, firstCircle.x, firstCircle.x + 500f, firstCircle.x
        )

        val firstCircleZ = PropertyValuesHolder.ofFloat(
            Z_VALUE, 0f, 40f, 0f, 0f, 0f
        )

        val firstCircleValueAnimator = ValueAnimator.ofPropertyValuesHolder(
            firstCircleX,
            firstCircleRadius,
            firstCircleZ,
        ).apply {
            duration = DURATION
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            startDelay = DELAY

            addUpdateListener { animator ->
                firstCircle.radius = animator.getAnimatedValue(RADIUS_VALUE) as Float
                firstCircle.x = animator.getAnimatedValue(X_VALUE) as Float
                firstCircle.z = animator.getAnimatedValue(Z_VALUE) as Float

                firstCircle.invalidate()
            }
        }

        val secondCircleRadius = PropertyValuesHolder.ofFloat(
            RADIUS_VALUE,
            secondCircle.radius,
            secondCircle.radius - 50f,
            secondCircle.radius,
            secondCircle.radius + 50f,
            secondCircle.radius
        )

        val secondCircleAlpha = PropertyValuesHolder.ofFloat(
            ALPHA_VALUE,
            secondCircle.alpha,
            secondCircle.alpha - 0.95f,
            secondCircle.alpha + 0.95f,
            secondCircle.alpha,
            secondCircle.alpha
        )

        val secondCircleX = PropertyValuesHolder.ofFloat(
            X_VALUE, 500f, 0f, 500f
        )

        val secondCircleZ = PropertyValuesHolder.ofFloat(
            Z_VALUE, 0f, -40f, 0f, 40f, 0f
        )

        val secondCircleValueAnimator = ValueAnimator.ofPropertyValuesHolder(
            secondCircleRadius, secondCircleAlpha, secondCircleX, secondCircleZ
        ).apply {
            duration = DURATION
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            startDelay = DELAY

            addUpdateListener { animator ->

                secondCircle.alpha = animator.getAnimatedValue(ALPHA_VALUE) as Float
                secondCircle.radius = animator.getAnimatedValue(RADIUS_VALUE) as Float
                secondCircle.x = animator.getAnimatedValue(X_VALUE) as Float
                secondCircle.z = animator.getAnimatedValue(Z_VALUE) as Float

                secondCircle.invalidate()
            }
        }

        firstCircleValueAnimator.start()
        secondCircleValueAnimator.start()


//         ObjectAnimator.ofFloat(circle1, View.X, 0f, 400f)
//            .apply {
//            duration = 1500
//            interpolator = LinearInterpolator()
//            repeatCount = Animation.INFINITE
//            repeatMode = ValueAnimator.RESTART
////            startDelay = 100
//        }.start()
//        ObjectAnimator.ofFloat(circle1, View.TRANSLATION_X, 0f, 500f, 0f)
//            .apply {
//                duration = 3000
//                interpolator = LinearInterpolator()
//                repeatCount = Animation.INFINITE
//                repeatMode = ValueAnimator.RESTART
//            startDelay = 500
//            }.start()
//        ObjectAnimator.ofFloat(circle2, View.TRANSLATION_X, 0f, -500f, 0f)
//            .apply {
//                duration = 3000
//                interpolator = LinearInterpolator()
//                repeatCount = Animation.INFINITE
//                repeatMode = ValueAnimator.RESTART
//            startDelay = 500
//            }.start()
//        ObjectAnimator.ofFloat(circle1, View.TRANSLATION_Z, 0f, 40f, 0f)
//            .apply {
//                duration = 3000
//                interpolator = LinearInterpolator()
//                repeatCount = Animation.INFINITE
//                repeatMode = ValueAnimator.RESTART
//                startDelay = 500
//            }.start()
////        ObjectAnimator.ofFloat(circle2, View.TRANSLATION_Z, 0f, -40f, 0f)
////            .apply {
////                duration = 2500
////                interpolator = LinearInterpolator()
////                repeatCount = Animation.INFINITE
////                repeatMode = ValueAnimator.RESTART
////                startDelay = 500
////            }.start()
//        ObjectAnimator.ofFloat(circle2, View.ALPHA, 0xFF.toFloat(),  0x00.toFloat())
//            .apply {
//                duration = 3000
//                interpolator = LinearInterpolator()
//                repeatCount = Animation.INFINITE
//                repeatMode = ValueAnimator.RESTART
//                startDelay = 500
//            }.start()

    }

    companion object {
        private const val DURATION = 3000L
        private const val DELAY = 500L

        private const val RADIUS_VALUE = "RADIUS_VALUE"
        private const val X_VALUE = "X_VALUE"
        private const val Z_VALUE = "Z_VALUE"
        private const val ALPHA_VALUE = "ALPHA_VALUE"
    }
}