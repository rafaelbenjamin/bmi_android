package com.benjamin.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            val height = text_height.text.toString().replace(",", ".")
            calculateBMI(weight, height)
        }

    }

    private fun calculateBMI(weight: String, height: String) {
        val weight = weight.toFloatOrNull()
        val height = height.toFloatOrNull()

        if (weight != null && height != null) {

            var bmi = weight / (height * height)

            text_result.visibility = View.VISIBLE
            text_bmi_category.visibility = View.VISIBLE
            text_bmi_unit.visibility = View.VISIBLE

            if (bmi.isNaN()) bmi = 0F
            if (bmi.isInfinite()) bmi = 0F

            if (bmi != 0F ) {
                var idealWeight1 = 18.5 * (height * height)
                var idealWeight2 = 24.9 * (height * height)

                text_bmi_you_are.visibility = View.VISIBLE
                text_bmi_your_ideal_weight_is.visibility = View.VISIBLE
                text_ideal_weight_result.visibility = View.VISIBLE
                text_ideal_weight_result.text = "%.0f".format(idealWeight1) + " kg a " + "%.0f".format(idealWeight2) + " kg"
            }

            text_result.text = "%.2f".format(bmi)

            when (bmi) {
                in 0.0..18.4 -> text_bmi_category.text = "Magreza"
                in 18.5..24.9 -> text_bmi_category.text = "Saudável"
                in 25.0..29.9 -> text_bmi_category.text = "Sobrepreso"
                else -> text_bmi_category.text = "Obeso"
            }
        }

    }

}