package nl.dionsegijn.konfettidemo.configurations.selection_views

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.SeekBar
import kotlinx.android.synthetic.main.view_section_seekbar_selection.view.*
import nl.dionsegijn.konfettidemo.R
import nl.dionsegijn.konfettidemo.configurations.Configurations
import nl.dionsegijn.konfettidemo.configurations.settings.Configuration
import nl.dionsegijn.konfettidemo.interfaces.UpdateConfiguration

/**
 * Created by dionsegijn on 5/21/17.
 */
class SeekbarSelectionView(context: Context?,
                           configuration: Configurations,
                           title: String,
                           max: Int) : LinearLayout(context), UpdateConfiguration {

    init {
        inflate(context, R.layout.view_section_seekbar_selection, this)
        orientation = VERTICAL
        gravity = Gravity.CENTER

        viewSeekbar.max = max

        val startValue = configuration.active.timeToLive.toInt()
        viewSeekbar.progress = startValue
        viewSeekbarTitle.text = "$title ($startValue)"
        
        viewSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewSeekbarTitle.text = "$title ($progress)"
                configuration.active.timeToLive = progress.toLong()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

    }

    override fun onUpdateConfiguration(configuration: Configuration) {
        viewSeekbar.progress = configuration.timeToLive.toInt()
    }

}
