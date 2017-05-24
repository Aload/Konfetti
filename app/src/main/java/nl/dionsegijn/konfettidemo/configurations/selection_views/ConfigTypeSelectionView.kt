package nl.dionsegijn.konfettidemo.configurations.selection_views

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v7.widget.AppCompatButton
import android.view.Gravity
import android.widget.LinearLayout
import nl.dionsegijn.konfettidemo.R
import nl.dionsegijn.konfettidemo.configurations.Configurations
import nl.dionsegijn.konfettidemo.configurations.settings.Configuration
import nl.dionsegijn.konfettidemo.interfaces.OnConfigurationChangedListener
import nl.dionsegijn.konfettidemo.interfaces.UpdateConfiguration


/**
 * Created by dionsegijn on 5/21/17.
 */
class ConfigTypeSelectionView(context: Context?,
                              val onConfigurationChangedListener: OnConfigurationChangedListener,
                              val configs: Configurations) : LinearLayout(context), UpdateConfiguration {

    lateinit var selectedButton: AppCompatButton
    val selectedColor: Int = 0xffffce08.toInt()
    val defaultColor: Int = 0xfff1f1f1.toInt()

    init {
        inflate(context, R.layout.view_section_config_selection, this)
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        displayConfigOptions()
    }

    fun displayConfigOptions() {
        configs.configurations.forEach { config ->
            val button = AppCompatButton(context)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                button.elevation = 6f
            }

            val color: Int
            if (configs.active == config) {
                color = selectedColor
                selectedButton = button
            } else {
                color = defaultColor
            }
            setColorForButton(button, color)

            button.text = config.title
            button.setOnClickListener { v ->
                onConfigurationChangedListener.onConfigurationChanged(config)
                // Set previous selected button to default
                setColorForButton(selectedButton, defaultColor)
                // Set new selected button
                setColorForButton(v as AppCompatButton, selectedColor)
                selectedButton = v
            }

            addView(button)
        }
    }

    fun setColorForButton(button: AppCompatButton, @ColorInt color: Int) {
        val colorStateList = ColorStateList(arrayOf(IntArray(0)), intArrayOf(color))
        button.supportBackgroundTintList = colorStateList
    }

    override fun onUpdateConfiguration(configuration: Configuration) {
    }

}
