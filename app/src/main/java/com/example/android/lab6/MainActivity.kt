package com.example.android.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.lab6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.showResultButton.setOnClickListener { onClick() }
    }

    private fun onClick() {
        if (isEditTextsEmpty()) {
            Toast.makeText(this, "Enter values in EditTexts!", Toast.LENGTH_SHORT).show()
            return
        }
        binding.apply {
            val a = Integer.parseInt(aEditText.text.toString())
            val b = Integer.parseInt(bEditText.text.toString())
            val c = Integer.parseInt(cEditText.text.toString())
            val d = Integer.parseInt(dEditText.text.toString())
            val x = Integer.parseInt(xEditText.text.toString())

            showFirstResult(a, b, c, d)
            showSecondResult(x)
            showThirdResult(a, b, c, x)
        }
    }

    private fun isEditTextsEmpty(): Boolean {
        binding.apply {
            return aEditText.text.toString().isEmpty() || bEditText.text.toString().isEmpty() ||
                    cEditText.text.toString().isEmpty() || dEditText.text.toString().isEmpty() ||
                    xEditText.text.toString().isEmpty()
        }
    }

    private fun showFirstResult(vararg numbers: Int) {
        numbers.sort()
        binding.firstResult.text = "y = ${numbers.last()}"
    }

    private fun  showSecondResult(x: Int) {
        binding.secondResult.text = "y = ${x*x*x*x}"
    }

    private fun showThirdResult(a: Int, b: Int, c: Int, x: Int) {
        binding.thirdResult.text = "y = ${a * x * x + b * x + c}"
    }
}