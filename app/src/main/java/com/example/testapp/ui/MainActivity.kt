package com.example.testapp.ui

import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.ext.setTextFieldData
import com.example.testapp.model.response.StyleString
import com.example.testapp.model.response.TextFieldData
import com.example.testapp.state.CatState
import com.example.testapp.viewModel.CatFactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CatFactViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.background)
        binding.videoView.setVideoURI(uri)

        binding.videoView.layoutParams.height = getDeviceHeight()
        binding.videoView.setOnPreparedListener { mp -> mp.isLooping = true }

        binding.rightLogo.setOnClickListener {
            if(!binding.expandCl.isVisible) {
                binding.rightLogo.setImageResource(R.drawable.cross)
            } else {
                binding.rightLogo.setImageResource(R.drawable.three_lines)
            }
            binding.expandCl.isVisible = !binding.expandCl.isVisible
        }
        binding.title.setTextFieldData(
            TextFieldData(
                text = "NX Wave. The next-\ngen credit card for\nthose who have\nrewards.",
                size = 24,
                substringStyles = listOf(
                    StyleString(substring = "NX Wave.", relativeSize = 1.3)
                )
            )
        )
        binding.belowVideoTitle.setTextFieldData(
            TextFieldData(
                text = "Earn 1% assured cashback on your spends. Get 5X more value than cashback at the Uni Store. Enjoy round the clock Whatsapp support. And it's lifetime free; no joining fee, no annual charges.",
                size = 24,
                substringStyles = listOf(
                    StyleString(substring = "on your spends. Get", textColor = "#999999"),
                    StyleString(substring = "at the Uni Store. Enjoy", textColor = "#999999"),
                    StyleString(substring = "round the clock", textColor = "#999999"),
                    StyleString(substring = "no joining fee, no annual charges.", textColor = "#999999"),
                )
            )
        )
        binding.belowVideoTitle2.setTextFieldData(
            TextFieldData(
                text = "Earn 1% assured cashback on your spends. Get 5X more value than cashback at the Uni Store. Enjoy roun",
                size = 24,
                substringStyles = listOf(
                    StyleString(substring = "on your spends. Get", textColor = "#999999"),
                    StyleString(substring = "at the Uni Store. Enjoy", textColor = "#999999"),
                    StyleString(substring = "round the clock", textColor = "#999999"),
                    StyleString(substring = "no joining fee, no annual charges.", textColor = "#999999"),
                )
            )
        )
        binding.belowVideoTitle3.setTextFieldData(
            TextFieldData(
                text = "Earn 1% assured cashback on your spends. Get 5X mo",
                size = 24,
                substringStyles = listOf(
                    StyleString(substring = "on your spends. Get", textColor = "#999999"),
                    StyleString(substring = "at the Uni Store. Enjoy", textColor = "#999999"),
                    StyleString(substring = "round the clock", textColor = "#999999"),
                    StyleString(substring = "no joining fee, no annual charges.", textColor = "#999999"),
                )
            )
        )
        binding.belowVideoTitle4.setTextFieldData(
            TextFieldData(
                text = "Earn 1% assured cashback on your spends. Get 5X more value than cashback at the",
                size = 24,
                substringStyles = listOf(
                    StyleString(substring = "on your spends. Get", textColor = "#999999"),
                    StyleString(substring = "at the Uni Store. Enjoy", textColor = "#999999"),
                    StyleString(substring = "round the clock", textColor = "#999999"),
                    StyleString(substring = "no joining fee, no annual charges.", textColor = "#999999"),
                )
            )
        )
        binding.belowVideoTitle5.setTextFieldData(
            TextFieldData(
                text = "Lifetime free.\nNo joining fee.\nNo annual charges.",
                size = 18,
                substringStyles = listOf(
                    StyleString(substring = "free", textColor = "#008080")
                )
            )
        )
        binding.blackTitle1.setTextFieldData(
            TextFieldData(
                text = "We’ve all heard of instant groceries, now say hello to\ninstant credit.\n\n0% hassle, 100% paperless. Get your Uni Card instantly.",
                size = 18,
                substringStyles = listOf(
                    StyleString(substring = "0% hassle, 100% paperless. Get your Uni Card instantly.", textColor = "#ffffff", relativeSize = 0.5),
                    StyleString(substring = "instant credit.", textColor = "#FAFA33", relativeSize = 0.5)
                )
            )
        )
        binding.button.setOnClickListener {  }
        binding.blackTitle2.setTextFieldData(
            TextFieldData(
                text = "We’ve all heard of instant groceries, now say hello to\ninstant credit.\n\n0% hassle, 100% paperless. Get your Uni Card instantly.",
                size = 18,
                substringStyles = listOf(
                    StyleString(substring = "0% hassle, 100% paperless. Get your Uni Card instantly.", textColor = "#ffffff", relativeSize = 0.5),
                    StyleString(substring = "instant credit.", textColor = "#FAFA33", relativeSize = 0.5)
                )
            )
        )
        binding.blackTitle3.setTextFieldData(
            TextFieldData(
                text = "We’ve all heard of instant groceries, now say hello to\ninstant credit.\n\n0% hassle, 100% paperless. Get your Uni Card instantly.",
                size = 18,
                substringStyles = listOf(
                    StyleString(substring = "0% hassle, 100% paperless. Get your Uni Card instantly.", textColor = "#ffffff", relativeSize = 0.5),
                    StyleString(substring = "instant credit.", textColor = "#FAFA33", relativeSize = 0.5)
                )
            )
        )
        binding.blackTitle4.setTextFieldData(
            TextFieldData(
                text = "We’ve all heard of instant groceries, now say hello to\ninstant credit.\n\n0% hassle, 100% paperless. Get your Uni Card instantly.",
                size = 18,
                substringStyles = listOf(
                    StyleString(substring = "0% hassle, 100% paperless. Get your Uni Card instantly.", textColor = "#ffffff", relativeSize = 0.5),
                    StyleString(substring = "instant credit.", textColor = "#FAFA33", relativeSize = 0.5)
                )
            )
        )

        initObservers()
        viewModel.getCatFact()
    }

    private fun getDeviceHeight(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onResume() {
        super.onResume()
        binding.videoView.start()
    }

    override fun onStop() {
        super.onStop()
        binding.videoView.stopPlayback()
    }
    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.catStateFlow.collect { catState ->
                when(catState) {
                    is CatState.Failure -> {
                        Toast.makeText(this@MainActivity, catState.failureMessage, Toast.LENGTH_SHORT).show()
                    }
                    is CatState.Loading -> {
                        //binding.textView.text = getString(R.string.loading)
                    }
                    is CatState.Success -> {
                        //binding.textView.text = catState.data?.fact
                    }
                    else -> {}
                }
            }
        }
    }
}