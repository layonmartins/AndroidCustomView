package com.example.customuserview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.slider.Slider


class CustomSquareView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    var buttonTouched: (() -> Unit)? = null
    var sliderChanged: ((Int) -> Int)? = null
    var sliderValue: Int = 0
    var editText: EditText? = null
    var textView: TextView? = null
    var slider: Slider? = null
    var button: Button? = null


    init {
        val view = LayoutInflater.from(context).inflate(R.layout.square_component_view, this, false)
        val set = ConstraintSet()

        addView(view)
        set.clone(this)
        set.match(view, this)

        editText = findViewById(R.id.plain_text_input)
        textView = findViewById(R.id.text_view_result)
        slider = findViewById(R.id.powerSlider)
        button = findViewById(R.id.button_id)


        slider?.addOnChangeListener(Slider.OnChangeListener {slider, value, fromUser ->
            val result = (value * 100).toInt()
            Log.d("layonf", "Slider changed: $result")
            textView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, result.toFloat())
            sliderValue = result
        })

        button?.setOnClickListener {
            try {
                val number = editText?.text.toString().toInt()
                textView?.text = "The square of ${number} is ${number * number}"

            } catch (e: NumberFormatException) {
                //Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
                textView?.text = e.toString()
            } finally {
                buttonTouched?.invoke()
            }
        }
    }

    fun ConstraintSet.match(view: View, parentView: View) {
        this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
        this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
        this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
        this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
    }



}