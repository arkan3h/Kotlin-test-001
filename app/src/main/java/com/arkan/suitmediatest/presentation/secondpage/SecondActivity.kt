package com.arkan.suitmediatest.presentation.secondpage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arkan.suitmediatest.R
import com.arkan.suitmediatest.databinding.ActivitySecondBinding
import com.arkan.suitmediatest.presentation.thirdpage.ThirdActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SecondActivity : AppCompatActivity() {
    private val binding: ActivitySecondBinding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private val viewModel: SecondViewModel by viewModel {
        parametersOf(intent.extras)
    }
    private val userSelectLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            viewModel.handleActivityResult(result.resultCode, result.data)
            setName()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setAppBar()
        setName()
        setClickListener()
    }

    private fun setAppBar() {
        binding.layoutAppBar.tvAppbarTitle.text = getString(R.string.text_second_screen)
    }

    private fun setName() {
        if (viewModel.name != "") {
            binding.tvName.text = viewModel.name
        }
        if (viewModel.selectedName != null) {
            binding.tvSelectedUserName.text = viewModel.selectedName
        }
    }

    private fun setClickListener() {
        binding.layoutAppBar.ibBtnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.btnChooseUser.setOnClickListener {
            navigateToThirdScreen()
        }
    }

    private fun navigateToThirdScreen() {
        userSelectLauncher.launch(
            Intent(this, ThirdActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
        )
    }

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_SELECTED_NAME = "EXTRA_SELECTED_NAME"

        fun startActivity(
            context: Context,
            name: String?,
        ) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(EXTRA_NAME, name)
            context.startActivity(intent)
        }
    }
}
