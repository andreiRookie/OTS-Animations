package ru.otus.animations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var radius: Float = RADIUS_DEFAULT.toDp()

    private val paint: Paint = initPaintBrush()

    init {
        attrs?.let { initAttributes(attrs) }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            radius,
            paint
        )
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//
//        val desiredWidth = radius * 2 + paddingStart + paddingEnd
//        val desiredHeight = radius * 2 + paddingTop + paddingBottom
//
//        setMeasuredDimension(
//            resolveSize(desiredWidth.toInt(), widthMeasureSpec),
//            resolveSize(desiredHeight.toInt(), heightMeasureSpec)
//        )
//    }


    private fun initPaintBrush(): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            style = Paint.Style.FILL
        }
    }

    private fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView)

        try {
            paint.color = typedArray.getColor(R.styleable.CircleView_color, Color.GRAY )
            radius = typedArray.getDimension(R.styleable.CircleView_radius, RADIUS_DEFAULT.toDp())
        } finally {
            typedArray.recycle()
        }
    }

    private fun Int.toDp(): Float {
        return this * context.resources.displayMetrics.density
    }

    companion object {
        private const val RADIUS_DEFAULT = 56
    }
}