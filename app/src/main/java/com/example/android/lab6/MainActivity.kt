package com.example.android.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.lab6.databinding.ActivityMainBinding
import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.sqrt

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
            val y = Integer.parseInt(yEditText.text.toString())

            showFirstResult(y)
            showSecondResult(a, b, c, y)
            showThirdResult(a, c, y)
        }
    }

    private fun isEditTextsEmpty(): Boolean {
        binding.apply {
            return aEditText.text.toString().isEmpty() || bEditText.text.toString().isEmpty() ||
                    cEditText.text.toString().isEmpty() || yEditText.text.toString().isEmpty()
        }
    }

    private fun showFirstResult(y: Int) {
        binding.apply {
            when {
                y > 0 -> {
                    val x = y.toDouble().pow(0.25)
                    val roundX = x.toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                    firstResult.text = "x1 = ${roundX}, x2 = ${-roundX}"
                }

                y == 0 -> firstResult.text = "x = 0"

                y < 0 -> firstResult.text = "There are no real solutions"
            }
        }
    }

    private fun showSecondResult(a: Int, b: Int, c: Int, y: Int) {
        val disc = b*b - 4 * a * (c - y)
        binding.apply {
            when {
                disc > 0 -> {
                    val x1 = (-b + sqrt(disc.toDouble()) / 2 * a)
                        .toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                    val x2 = (-b - sqrt(disc.toDouble()) / 2 * a)
                        .toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                    secondResult.text = "x1 = $x1; x2 = $x2"
                }
                disc == 0 -> {
                    val x = (-b / 2 * a).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                    secondResult.text = "x = $x"
                }
                disc < 0 -> secondResult.text = "There are no real solutions"
            }
        }
    }

    private fun showThirdResult(a: Int, c: Int, y: Int) {
        val x = ((y - c) / a)
            .toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
        binding.thirdResult.text = "x = $x"
    }
}