package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var purpleCircle: CircleView
    private lateinit var fuchsiaCircle: CircleView

    private lateinit var rippleCircle1: CircleView
    private lateinit var rippleCircle2: CircleView
    private lateinit var rippleCircle3: CircleView
    private lateinit var rippleCircle4: CircleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        purpleCircle = findViewById(R.id.circle_1)
        fuchsiaCircle = findViewById(R.id.circle_2)

        findViewById<ViewGroup>(R.id.tik_tok_anim).setOnClickListener {
            startTikTokAnimation(purpleCircle, fuchsiaCircle)
        }

        rippleCircle1 = findViewById(R.id.ripple_circle_1)
        rippleCircle2 = findViewById(R.id.ripple_circle_2)
        rippleCircle3 = findViewById(R.id.ripple_circle_3)
        rippleCircle4 = findViewById(R.id.ripple_circle_4)

        findViewById<ViewGroup>(R.id.ripple_anim).setOnClickListener {
            startRippleAnimation()
        }
    }

    private fun startRippleAnimation() {

        val circle1Radius = PropertyValuesHolder.ofFloat(
            CIRCLE_1_RADIUS_VALUE, 0f, 100f
        )
        val circle1Alpha = PropertyValuesHolder.ofFloat(
            CIRCLE_1_ALPHA_VALUE, 1.0f, 0.75f
        )

        val circle2Radius = PropertyValuesHolder.ofFloat(
            CIRCLE_2_RADIUS_VALUE, 100f, 200f
        )
        val circle2Alpha = PropertyValuesHolder.ofFloat(
            CIRCLE_2_ALPHA_VALUE, 0.75f, 0.50f
        )

        val circle3Radius = PropertyValuesHolder.ofFloat(
            CIRCLE_3_RADIUS_VALUE, 200f, 300f
        )
        val circle3Alpha = PropertyValuesHolder.ofFloat(
            CIRCLE_3_ALPHA_VALUE, 0.50f, 0.25f
        )

        val circle4Radius = PropertyValuesHolder.ofFloat(
            CIRCLE_4_RADIUS_VALUE, 300f, 400f
        )
        val circle4Alpha = PropertyValuesHolder.ofFloat(
            CIRCLE_4_ALPHA_VALUE, 0.25f, 0.0f
        )

        val rippleCirclesValueAnimator = makeRippleValueAnimator(
            circle1Radius, circle1Alpha,
            circle2Radius, circle2Alpha,
            circle3Radius, circle3Alpha,
            circle4Radius, circle4Alpha
        )
        rippleCirclesValueAnimator.start()
    }

    private fun makeRippleValueAnimator(
        circle1Radius: PropertyValuesHolder, circle1Alpha: PropertyValuesHolder,
        circle2Radius: PropertyValuesHolder, circle2Alpha: PropertyValuesHolder,
        circle3Radius: PropertyValuesHolder, circle3Alpha: PropertyValuesHolder,
        circle4Radius: PropertyValuesHolder, circle4Alpha: PropertyValuesHolder
    ): ValueAnimator {
        return ValueAnimator.ofPropertyValuesHolder(
            circle1Radius, circle1Alpha,
            circle2Radius, circle2Alpha,
            circle3Radius, circle3Alpha,
            circle4Radius, circle4Alpha
        ).apply {
            duration = DURATION_RIPPLE
            interpolator = LinearInterpolator()
            repeatCount = RIPPLE_REPEAT_COUNT
            repeatMode = ValueAnimator.RESTART

            addUpdateListener { animator ->
                rippleCircle1.radius = animator.getAnimatedValue(CIRCLE_1_RADIUS_VALUE) as Float
                rippleCircle1.alpha = animator.getAnimatedValue(CIRCLE_1_ALPHA_VALUE) as Float

                rippleCircle2.radius = animator.getAnimatedValue(CIRCLE_2_RADIUS_VALUE) as Float
                rippleCircle2.alpha = animator.getAnimatedValue(CIRCLE_2_ALPHA_VALUE) as Float

                rippleCircle3.radius = animator.getAnimatedValue(CIRCLE_3_RADIUS_VALUE) as Float
                rippleCircle3.alpha = animator.getAnimatedValue(CIRCLE_3_ALPHA_VALUE) as Float

                rippleCircle4.radius = animator.getAnimatedValue(CIRCLE_4_RADIUS_VALUE) as Float
                rippleCircle4.alpha = animator.getAnimatedValue(CIRCLE_4_ALPHA_VALUE) as Float

                rippleCircle1.invalidate()
                rippleCircle2.invalidate()
                rippleCircle3.invalidate()
                rippleCircle4.invalidate()
            }
        }
    }

    private fun startTikTokAnimation(purpleCircle: CircleView, fuchsiaCircle: CircleView) {

        val purpleCircleRadius = PropertyValuesHolder.ofFloat(
            RADIUS_VALUE_TIKTOK,
            purpleCircle.radius,
            purpleCircle.radius + 50f,
            purpleCircle.radius,
            purpleCircle.radius,
            purpleCircle.radius
        )
        val purpleCircleX = PropertyValuesHolder.ofFloat(
            X_VALUE_TIKTOK, purpleCircle.x, purpleCircle.x + 500f, purpleCircle.x
        )
        val purpleCircleZ = PropertyValuesHolder.ofFloat(
            Z_VALUE_TIKTOK, 0f, 40f, 0f, 0f, 0f
        )

        val fuchsiaCircleRadius = PropertyValuesHolder.ofFloat(
            RADIUS_VALUE_TIKTOK,
            fuchsiaCircle.radius,
            fuchsiaCircle.radius - 50f,
            fuchsiaCircle.radius,
            fuchsiaCircle.radius + 50f,
            fuchsiaCircle.radius
        )
        val fuchsiaCircleAlpha = PropertyValuesHolder.ofFloat(
            ALPHA_VALUE_TIKTOK,
            fuchsiaCircle.alpha,
            fuchsiaCircle.alpha - 0.95f,
            fuchsiaCircle.alpha + 0.95f,
            fuchsiaCircle.alpha,
            fuchsiaCircle.alpha
        )
        val fuchsiaCircleX = PropertyValuesHolder.ofFloat(
            X_VALUE_TIKTOK, 500f, 0f, 500f
        )
        val fuchsiaCircleZ = PropertyValuesHolder.ofFloat(
            Z_VALUE_TIKTOK, 0f, -40f, 0f, 40f, 0f
        )

        val purpleCircleValueAnimator =
            makePurpleCircleValueAnimator(purpleCircleX, purpleCircleRadius, purpleCircleZ)

        val fuchsiaCircleValueAnimator = makeFuchsiaCircleValueAnimator(
            fuchsiaCircleRadius,
            fuchsiaCircleAlpha,
            fuchsiaCircleX,
            fuchsiaCircleZ
        )

        purpleCircleValueAnimator.start()
        fuchsiaCircleValueAnimator.start()
    }

    private fun makePurpleCircleValueAnimator(
        firstCircleX: PropertyValuesHolder,
        firstCircleRadius: PropertyValuesHolder,
        firstCircleZ: PropertyValuesHolder
    ): ValueAnimator {
        return ValueAnimator.ofPropertyValuesHolder(
            firstCircleX,
            firstCircleRadius,
            firstCircleZ,
        ).apply {
            duration = DURATION_TIKTOK
            interpolator = LinearInterpolator()
            repeatCount = TIKTOK_REPEAT_COUNT
            repeatMode = ValueAnimator.RESTART
            startDelay = DELAY

            addUpdateListener { animator ->
                purpleCircle.radius = animator.getAnimatedValue(RADIUS_VALUE_TIKTOK) as Float
                purpleCircle.x = animator.getAnimatedValue(X_VALUE_TIKTOK) as Float
                purpleCircle.z = animator.getAnimatedValue(Z_VALUE_TIKTOK) as Float

                purpleCircle.invalidate()
            }
        }
    }

    private fun makeFuchsiaCircleValueAnimator(
        secondCircleRadius: PropertyValuesHolder,
        secondCircleAlpha: PropertyValuesHolder,
        secondCircleX: PropertyValuesHolder,
        secondCircleZ: PropertyValuesHolder
    ): ValueAnimator {
        return ValueAnimator.ofPropertyValuesHolder(
            secondCircleRadius, secondCircleAlpha, secondCircleX, secondCircleZ
        ).apply {
            duration = DURATION_TIKTOK
            interpolator = LinearInterpolator()
            repeatCount = TIKTOK_REPEAT_COUNT
            repeatMode = ValueAnimator.RESTART
            startDelay = DELAY

            addUpdateListener { animator ->
                fuchsiaCircle.alpha = animator.getAnimatedValue(ALPHA_VALUE_TIKTOK) as Float
                fuchsiaCircle.radius = animator.getAnimatedValue(RADIUS_VALUE_TIKTOK) as Float
                fuchsiaCircle.x = animator.getAnimatedValue(X_VALUE_TIKTOK) as Float
                fuchsiaCircle.z = animator.getAnimatedValue(Z_VALUE_TIKTOK) as Float

                fuchsiaCircle.invalidate()
            }
        }
    }

    companion object {

        private const val DURATION_TIKTOK = 3000L
        private const val DELAY = 500L
        private const val TIKTOK_REPEAT_COUNT = 3

        private const val RADIUS_VALUE_TIKTOK = "RADIUS_VALUE"
        private const val X_VALUE_TIKTOK = "X_VALUE"
        private const val Z_VALUE_TIKTOK = "Z_VALUE"
        private const val ALPHA_VALUE_TIKTOK = "ALPHA_VALUE"

        private const val DURATION_RIPPLE = 500L
        private const val RIPPLE_REPEAT_COUNT = 20

        private const val CIRCLE_1_RADIUS_VALUE = "CIRCLE_1_RADIUS_VALUE"
        private const val CIRCLE_1_ALPHA_VALUE = "CIRCLE_1_ALPHA_VALUE"
        private const val CIRCLE_2_RADIUS_VALUE = "CIRCLE_2_RADIUS_VALUE"
        private const val CIRCLE_2_ALPHA_VALUE = "CIRCLE_2_ALPHA_VALUE"
        private const val CIRCLE_3_RADIUS_VALUE = "CIRCLE_3_RADIUS_VALUE"
        private const val CIRCLE_3_ALPHA_VALUE = "CIRCLE_3_ALPHA_VALUE"
        private const val CIRCLE_4_RADIUS_VALUE = "CIRCLE_4_RADIUS_VALUE"
        private const val CIRCLE_4_ALPHA_VALUE = "CIRCLE_4_ALPHA_VALUE"
    }
}