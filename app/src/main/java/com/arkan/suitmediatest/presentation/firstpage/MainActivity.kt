package com.arkan.suitmediatest.presentation.firstpage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.arkan.suitmediatest.databinding.ActivityMainBinding
import com.arkan.suitmediatest.presentation.secondpage.SecondActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setClickListener()
        palindromeObserver()
    }

    private fun setClickListener() {
        binding.btnNext.setOnClickListener {
            navigateToSecondPage()
        }
        binding.btnPalindrome.setOnClickListener {
            checkPalindrome()
        }
    }

    private fun navigateToSecondPage() {
        val name = binding.tiEtName.text.toString()
        SecondActivity.startActivity(this, name)
    }

    private fun palindromeObserver() {
        viewModel.isPalindrome.observe(this) { isPalindrome ->
            val message =
                if (isPalindrome) {
                    "isPalindrome"
                } else {
                    "not palindrome"
                }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPalindrome() {
        val textPalindrome = binding.tiEtPalindrome.text.toString()
        viewModel.checkPalindrome(textPalindrome)
    }
}
