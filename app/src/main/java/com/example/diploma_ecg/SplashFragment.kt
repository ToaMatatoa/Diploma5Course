package com.example.diploma_ecg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.splash_screen.*


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       // activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        splash_loading?.speed = 2.0F // How fast does the animation play
        splash_loading?.progress = 0.5F // Starts the animation from 50% of the beginning
        splash_loading?.addAnimatorUpdateListener {
        }
        splash_loading?.repeatMode =
            LottieDrawable.RESTART // Restarts the animation (you can choose to reverse it as well)
        splash_loading?.cancelAnimation() // Cancels the animation

        return inflater.inflate(R.layout.splash_screen, container, false)
    }
}