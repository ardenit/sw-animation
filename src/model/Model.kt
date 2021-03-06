package model

import java.io.File
import java.util.*
import javax.swing.JOptionPane

object Model {

    /**
     * Текущая анимация
     */
    var animation : Animation = Animation()

    /**
     * Файл с текущей анимацией
     */
    var animationFile : File? = null

    /**
     * Текущая директория с анимацией
     */
    var animationDirectory : File = File("./animations")

    var settingsFile: File = File("./settings.txt")

    init {
        if (settingsFile.exists()) {
            animationDirectory = File(Scanner(settingsFile).nextLine())
        }
    }


    /**
     * Сериализует текущую анимацию и сохраняет ее в файл
     */
    fun serialize() {
        if (animationFile != null) {
            if (!animationFile!!.parentFile.parentFile.exists()) {
                animationFile!!.parentFile.parentFile.mkdir()
            }
            if (!animationFile!!.parentFile.exists()) {
                animationFile!!.parentFile.mkdir()
            }
            if (!animationFile!!.exists()) {
                animationFile!!.createNewFile()
            }
            animation.serialize(animationFile!!)
        }
    }


    /**
     * Создает отраженную копию всех кадров для соотвествующего moveDirection-а
     */
    fun mirrorAnimation() {
        val mirroredMD = animation.curMoveDirection.mirrored()
        if (mirroredMD == animation.curMoveDirection) {
            JOptionPane.showMessageDialog(null, "This move direction can't be mirrored")
        }
        else {
            val mirroredFrames = animation.data[mirroredMD]!![animation.curWeaponType]!!
            mirroredFrames.clear()
            val curFrames = animation.data[animation.curMoveDirection]!![animation.curWeaponType]!!
            for (frame in curFrames) {
                mirroredFrames.add(Frame(frame))
            }
            for (mFrame in mirroredFrames) {
                mFrame.mirror(animation.curMoveDirection)
            }

        }
    }
}