package com.benjamin.imcapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        btn_calculate.setOnClickListener {
            val weight = text_weight.text.toString()
            val height = text_height.text.toString().replace(",", ".")
            calculateBMI(weight, height)
        }

    }

    private fun calculateBMI(weight: String, height: String) {
        val weight = weight.toFloatOrNull()
        val height = height.toFloatOrNull()

        if (weight != null && height != null) {

            var imc = weight / (height + height)

            text_result.visibility = View.VISIBLE
            text_bmi_category.visibility = View.VISIBLE

            if (imc.isNaN()) imc = 0F

            text_result.text = "%.2f".format(imc)

            when (imc) {
                in 0.0..18.4 -> text_bmi_category.text = "Magreza"
                in 18.5..24.9 -> text_bmi_category.text = "Saudável"
                in 25.0..29.9 -> text_bmi_category.text = "Sobrepreso"
                else -> text_bmi_category.text = "Obeso"
            }
        }

    }

}