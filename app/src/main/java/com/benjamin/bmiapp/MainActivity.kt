package com.benjamin.bmiapp

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        btn_calculate.setOnClickListener {
            val weight = text_weight.text.toString().replace(",", ".")
            val height = text_height.text.toString()
            calculateBMI(weight, height)
            hideKeyboard(btn_calculate)
        }

    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun calculateBMI(weight: String, height: String) {
        var weight = weight.toFloatOrNull()
        var height = height.toFloatOrNull()

        if (weight != null && height != null) {
            height /= 100;

            var bmi = weight/ (height * height)

            if (bmi.isNaN()) bmi = 0F
            if (bmi.isInfinite()) bmi = 0F

            if (bmi != 0F) {
                var idealWeight1 = 18.5 * (height * height)
                var idealWeight2 = 24.9 * (height * height)

                text_ideal_weight_result.text =
                    "%.0f".format(idealWeight1) + " kg - " + "%.0f".format(idealWeight2) + " kg"
            }

            text_result.text = "%.2f".format(bmi)

            when (bmi) {
                in 0.0..18.4 -> text_bmi_category.text = getString(R.string.underweight)
                in 18.5..24.9 -> text_bmi_category.text = getString(R.string.normal)
                in 25.0..29.9 -> text_bmi_category.text = getString(R.string.overweight)
                in 30.0..30.4 -> text_bmi_category.text = getString(R.string.obesity_class_one)
                in 35.0..39.9 -> text_bmi_category.text = getString(R.string.obesity_class_two)
                else -> text_bmi_category.text = getString(R.string.obesity_class_three)
            }
        }

    }

}