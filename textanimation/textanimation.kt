// MainActivity.kt
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        // Start text animations
        blinkAnimation()
        bounceAnimation()
        rotateAnimation()
        zoomInOutAnimation()
    }

    private fun blinkAnimation() {
        val blinkAnimator = ObjectAnimator.ofInt(textView, "visibility", View.VISIBLE, View.INVISIBLE)
        blinkAnimator.duration = 500
        blinkAnimator.repeatCount = ObjectAnimator.INFINITE
        blinkAnimator.repeatMode = ObjectAnimator.REVERSE
        blinkAnimator.start()
    }

    private fun bounceAnimation() {
        val bounceAnimator = ObjectAnimator.ofFloat(textView, "translationY", 0f, -50f, 0f)
        bounceAnimator.duration = 1000
        bounceAnimator.repeatCount = ObjectAnimator.INFINITE
        bounceAnimator.repeatMode = ObjectAnimator.REVERSE
        bounceAnimator.interpolator = BounceInterpolator()
        bounceAnimator.start()
    }

    private fun rotateAnimation() {
        val rotateAnimator = ObjectAnimator.ofFloat(textView, "rotation", 0f, 360f)
        rotateAnimator.duration = 2000
        rotateAnimator.repeatCount = ObjectAnimator.INFINITE
        rotateAnimator.interpolator = LinearInterpolator()
        rotateAnimator.start()
    }

    private fun zoomInOutAnimation() {
        val zoomInOutAnimator = ObjectAnimator.ofFloat(textView, "scaleX", 1f, 1.5f, 1f)
        zoomInOutAnimator.duration = 2000
        zoomInOutAnimator.repeatCount = ObjectAnimator.INFINITE
        zoomInOutAnimator.repeatMode = ObjectAnimator.REVERSE
        zoomInOutAnimator.interpolator = DecelerateInterpolator()
        zoomInOutAnimator.start()
    }
}

