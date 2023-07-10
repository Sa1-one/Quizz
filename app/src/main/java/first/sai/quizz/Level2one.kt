package first.sai.quizz

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import java.util.Random

class Level2one : AppCompatActivity() {

    private var count: Int = 0
    val random = Random()
    var randomIndex = 0





    private lateinit var textQuestion: TextView
    private lateinit var textLeft: TextView
    private lateinit var textRight: TextView
    private lateinit var textLeftBot: TextView
    private lateinit var textRightBot: TextView

//    var tag = "MyTag"
//    var number = randomIndextextLeft


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.universal)

        val level1 = findViewById<TextView>(R.id.textLvl)
        level1.setText(R.string.level1)

        val btnBack = findViewById<Button>(R.id.button_back)
        btnBack.setOnClickListener {
            try {
                val intent = Intent(this, GameLevels::class.java)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                try {
                    val intent = Intent(this@Level2one, GameLevels::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)

        textQuestion = findViewById(R.id.textQuestion)
        textLeft = findViewById(R.id.textLeft)
        textRight = findViewById(R.id.textRight)
        textLeftBot = findViewById(R.id.textLeftBot)
        textRightBot = findViewById(R.id.textRightBot)


        // Генерируем и отображаем новый вопрос и ответы
        generateQuestion()


        // Назначаем обработчики нажатия на кнопки ответов

        val dialog = Dialog(this)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.previewdialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)

        val btnclose = dialog.findViewById<TextView>(R.id.btnclose)
        btnclose.setOnClickListener {
            try {
                val intent = Intent(this, GameLevels::class.java)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            dialog.dismiss()

        }

        val btncontinuedialog = dialog.findViewById<Button>(R.id.btncontinuedialog)
        btncontinuedialog.setOnClickListener {
            try {
                dialog.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        dialog.show()
    }

    fun onAnswerTextViewClick(
        textView: TextView,
        textView1: TextView,
        indexQ: Int,
        indAn: Int
    ) {

//        var tag = "MyTag3"
//        var number = randomIndextextLeft
//        Log.d(tag, "The number is: $number")

        /*count++*/
        /*if (count <= 20) {*/

            if (indexQ == indAn) {
//                textView.setBackgroundColor(Color.RED)
//                textView1.setBackgroundColor(Color.RED)
                textView.setBackgroundColor(Color.GREEN)
                Log.d("Answer", "Правильный ответ!")

                // Создаем Handler
                val handler = Handler()
                handler.postDelayed({
                    // Возвращаем изначальный цвет фона
                    textView.setBackgroundColor(Color.TRANSPARENT)
//                    textView1.setBackgroundColor(Color.TRANSPARENT)
                }, 1000)
//                updatePointsColor()

            } else {
                textView.setBackgroundColor(Color.RED)
                Log.d("Answer", "Неправильный ответ!")
                // Создаем Handler
                val handler = Handler()
                handler.postDelayed({
                    // Возвращаем изначальный цвет фона
                    textView.setBackgroundColor(Color.TRANSPARENT)
//                    textView1.setBackgroundColor(Color.TRANSPARENT)
                }, 1000)
//                updatePointsColor()
            }

            // Обновляем цвета точек
//            updatePointsColor()
        /*}*/
// Генерируем и отображаем новый вопрос и ответы

//        tag = "MyTag4"
//        number = randomIndextextLeft
//        Log.d(tag, "The number is: $number")

        generateQuestion()
//        tag = "MyTag5"
//        number = randomIndextextLeft
//        Log.d(tag, "The number is: $number")
    }
    fun generateQuestion() {
        // Устанавливаем текст вопроса
        val questionsArray = resources.getStringArray(R.array.question_array)
        randomIndex = random.nextInt(questionsArray.size)
        textQuestion.text = questionsArray[randomIndex]

        val answerArray = resources.getStringArray(R.array.answer_array)

        val randomIndexCorrectAnswer = random.nextInt(answerArray.size)
        val correctAnswer = answerArray[randomIndexCorrectAnswer]

        var randomIndextextLeft: Int
        do {
            randomIndextextLeft = random.nextInt(answerArray.size)
        } while (randomIndextextLeft == randomIndexCorrectAnswer)
//        tag = "MyTag1"
//        number = randomIndextextLeft
//        Log.d(tag, "The number is: $number")
        var randomIndextextRight: Int
        do {
            randomIndextextRight = random.nextInt(answerArray.size)
        } while (randomIndextextRight == randomIndexCorrectAnswer || randomIndextextRight == randomIndextextLeft)

        var randomIndextextLeftBot: Int
        do {
            randomIndextextLeftBot = random.nextInt(answerArray.size)
        } while (randomIndextextLeftBot == randomIndexCorrectAnswer || randomIndextextLeftBot == randomIndextextLeft || randomIndextextLeftBot == randomIndextextRight)

        var randomIndextextRightBot: Int
        do {
            randomIndextextRightBot = random.nextInt(answerArray.size)
        } while (randomIndextextRightBot == randomIndexCorrectAnswer || randomIndextextRightBot == randomIndextextLeft || randomIndextextRightBot == randomIndextextRight || randomIndextextRightBot == randomIndextextLeftBot)



        textLeft.text = answerArray[randomIndextextLeft]
        textRight.text = answerArray[randomIndextextRight]
        textLeftBot.text = answerArray[randomIndextextLeftBot]
        textRightBot.text = answerArray[randomIndextextRightBot]

// Установка случайного совпадающего значения у одного из ответов
        var rndNum: Int = 4
        val randomMatchingIndex = random.nextInt(4)
        if (randomIndex == randomIndextextLeft) {
            rndNum = 0
        } else if (randomIndex == randomIndextextRight) {
            rndNum = 1
        } else if (randomIndex == randomIndextextLeftBot) {
            rndNum = 2
        } else if (randomIndex == randomIndextextRightBot) {
            rndNum = 3
        } else {
            when (randomMatchingIndex) {
                0 -> textLeft.text = answerArray[randomIndex]
                1 -> textRight.text = answerArray[randomIndex]
                2 -> textLeftBot.text = answerArray[randomIndex]
                3 -> textRightBot.text = answerArray[randomIndex]
            }
        }

        when (rndNum) {
            0 -> textLeft.text = answerArray[randomIndex]
            1 -> textRight.text = answerArray[randomIndex]
            2 -> textLeftBot.text = answerArray[randomIndex]
            3 -> textRightBot.text = answerArray[randomIndex]
        }

        // Находим представления в макете по их идентификаторам
        val point1 = findViewById<TextView>(R.id.point1)


        var tag = "MyTag2"
        var number = randomIndextextLeft
        Log.d(tag, "The number is: $number")

        textLeft.setOnClickListener {
            onAnswerTextViewClick(
                textLeft,
                point1,
                randomIndex,
                randomIndextextLeft,
            )
        }
        textRight.setOnClickListener {
            onAnswerTextViewClick(
                textRight,
                point1,
                randomIndex,
                randomIndextextRight,
            )
        }
        textLeftBot.setOnClickListener {
            onAnswerTextViewClick(
                textLeftBot,
                point1,
                randomIndex,
                randomIndextextLeftBot,
            )
        }
        textRightBot.setOnClickListener {
            onAnswerTextViewClick(
                textRightBot,
                point1,
                randomIndex,
                randomIndextextRightBot
            )
        }
    }

//    private fun updatePointsColor() {
//        val pointsArray = arrayOf(
//            findViewById<TextView>(R.id.point1),
//            findViewById<TextView>(R.id.point2),
//            findViewById<TextView>(R.id.point3),
//            // и так далее до point20
//        )
//
//        for (i in pointsArray.indices) {
//            if (i == randomIndex) {
//                pointsArray[i].setBackgroundResource(R.drawable.btn_start)
//            } else {
//                pointsArray[i].setBackgroundResource(R.drawable.btn_start)
//            }
//        }
//    }

}