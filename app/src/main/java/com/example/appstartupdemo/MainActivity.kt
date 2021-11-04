package com.example.appstartupdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import android.view.View
import android.view.animation.AnimationUtils


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashWasDisplayed = savedInstanceState != null
        if (!splashWasDisplayed) {
            val splashScreen = installSplashScreen()
            splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->

                splashScreenViewProvider.iconView.animate().apply {
                    duration = splashScreenDuration.toLong()
                    scaleX(0f)
                    scaleY(0f)
                    alpha(0f)
                    withEndAction {
                        // Remove splash screen and display main content with animation
                        splashScreenViewProvider.remove()
                        setContentView(R.layout.activity_main)
                        this@MainActivity.findViewById<View>(R.id.container).startAnimation(
                            AnimationUtils.loadAnimation(
                                applicationContext,
                                R.anim.slide_down
                            )
                        )
                    }
                    start()
                }
            }
        } else {
            setTheme(R.style.SplashTheme)

            setContentView(R.layout.activity_main)
            this@MainActivity.findViewById<View>(android.R.id.content).rootView.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.slide_down
                )
            )
        }
    }

    companion object {
        // allow us to show the splash screen for longer period than 1000ms
        const val splashScreenDuration = 1001
    }


}