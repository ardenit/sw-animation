package controller

import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.io.File
import java.io.IOException

import javax.imageio.ImageIO
import javax.swing.*

/**
 * Дополнительное окно, позволяющее работать с размерами слоя
 */
object SlidersWindow : JFrame() {

    private val panel = JPanel().apply {
        layout = null
    }

    /**
     * Слайдер, отвечающий за размер слоя
     */
    internal var sizeSlider : JSlider = UIFactory.createSlider(panel, sizeSliderListener)
    /**
     * Слайдер, отвечающий за ширину слоя
     */
    internal var widthSlider : JSlider = UIFactory.createSlider(panel, widthSliderListener)
    /**
     * Слайдер, отвечающий за высоту слоя
     */
    internal var heightSlider : JSlider = UIFactory.createSlider(panel, heightSliderListener)

    private val sizeLabel : JLabel = UIFactory.createLabel("size", panel)

    private val widthLabel : JLabel = UIFactory.createLabel("width", panel)

    private val heightLabel : JLabel = UIFactory.createLabel("height", panel)


    init {
        isUndecorated = true
        setSize(242, 102)
        title = "Layer size"
        isAlwaysOnTop = true
        defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
        contentPane.add(panel)

        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(evt: ComponentEvent?) {
                sizeSlider.setLocation(0, 4)
                widthSlider.setLocation(0, 4 + sizeSlider.height)
                heightSlider.setLocation(0, 4 + sizeSlider.height + widthSlider.height)
                sizeLabel.setLocation(sizeSlider.x + sizeSlider.width, sizeSlider.y)
                widthLabel.setLocation(widthSlider.x + widthSlider.width, widthSlider.y)
                heightLabel.setLocation(heightSlider.x + heightSlider.width, heightSlider.y)
            }
        })
        isVisible = false

    }

    /**
     * Перегрузка метода setEnabled класса JFrame
     * Не изменяет активность самого окна, но изменяет активность всех элементов внутри окна
     */
    override fun setEnabled(b: Boolean) {
        sizeSlider.isEnabled = b
        widthSlider.isEnabled = b
        heightSlider.isEnabled = b
    }
}
