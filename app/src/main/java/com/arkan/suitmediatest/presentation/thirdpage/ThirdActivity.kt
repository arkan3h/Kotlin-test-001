package com.arkan.suitmediatest.presentation.thirdpage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arkan.suitmediatest.R
import com.arkan.suitmediatest.data.model.User
import com.arkan.suitmediatest.databinding.ActivityThirdBinding
import com.arkan.suitmediatest.presentation.secondpage.SecondActivity
import com.arkan.suitmediatest.presentation.thirdpage.adapter.ListUserAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThirdActivity : AppCompatActivity() {
    private val binding: ActivityThirdBinding by lazy {
        ActivityThirdBinding.inflate(layoutInflater)
    }
    private val viewModel: ThirdViewModel by viewModel()
    private val userAdapter: ListUserAdapter by lazy {
        ListUserAdapter {
            onUserSelected(it)
        }
    }
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

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
        setClickListener()
        getUserData()
        bindUserList()
        refreshList()
    }

    private fun onUserSelected(user: User) {
        val selectedName = "${user.firstName} ${user.lastName}"
        val resultIntent =
            Intent().apply {
                putExtra(SecondActivity.EXTRA_SELECTED_NAME, selectedName)
            }
        setResult(Activity.RESULT_OK, resultIntent)
    }

    private fun setAppBar() {
        binding.layoutAppBar.tvAppbarTitle.text = getString(R.string.text_third_screen)
    }

    private fun setClickListener() {
        binding.layoutAppBar.ibBtnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getUserData() {
        lifecycleScope.launch {
            viewModel.getUserList().collectLatest {
                userAdapter.submitData(it)
            }
        }
    }

    private fun bindUserList() {
        binding.rvListUser.apply {
            adapter = userAdapter
        }
    }

    private fun refreshList() {
        swipeRefreshLayout = binding.swipeUser
        swipeRefreshLayout.setOnRefreshListener {
            getUserData()
            bindUserList()
            Handler(Looper.getMainLooper()).postDelayed({
                swipeRefreshLayout.isRefreshing = false
            }, 1000)
        }
    }
}
