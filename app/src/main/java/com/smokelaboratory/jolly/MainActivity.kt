package com.smokelaboratory.jolly

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.smokelaboratory.jollify.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.view_single).jollify(rotate {
            end = 180f
            interpolator = AccelerateDecelerateInterpolator()
            repeat = repeat {
                repeatMode = JollyRepetition.RepeatMode.REVERSE
            }
        })

        findViewById<View>(R.id.view_together).jollify(sequence(together(
            scaleX {
                end = 1.2f
            }, scaleY {
                end = 1.2f
            }
        ) {
            duration = 2000
        }, translateX {
            end = -180f
            startDelay = 1000
        }, translateX {
            begin = -180f
            end = 180f
        })
        )

        findViewById<View>(R.id.view_sequence).jollify(together(scaleX {
            end = 1.2f
        }, scaleY {
            end = 1.2f
        }, fade {
            end = 1f
        }) {
            duration = 2000
            repeat = repeat {
                repeatMode = JollyRepetition.RepeatMode.RESTART
                repeatCount = 5
            }
        })
    }
}