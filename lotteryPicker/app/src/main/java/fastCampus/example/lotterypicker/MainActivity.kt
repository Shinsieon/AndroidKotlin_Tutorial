package fastCampus.example.lotterypicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat

class MainActivity : AppCompatActivity() {

    private val clearButton : Button by lazy{
        findViewById<Button>(R.id.clearButton)
    }
    private val addButton : Button by lazy{
        findViewById<Button>(R.id.addButton)
    }
    private val runButton : Button by lazy{
        findViewById<Button>(R.id.runButton)
    }
    private val numberPicker : NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker)
    }
    private val numberTextViewList : List<TextView> by lazy{
        listOf<TextView>(
            findViewById<TextView>(R.id.textView1),
            findViewById<TextView>(R.id.textView2),
            findViewById<TextView>(R.id.textView3),
            findViewById<TextView>(R.id.textView4),
            findViewById<TextView>(R.id.textView5),
            findViewById<TextView>(R.id.textView6)

        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()

    }

    private var didRun = false

    private val picNumberSet = hashSetOf<Int>()

    private fun initRunButton(){
        runButton.setOnClickListener{
            val list = getRandomNumber()
            didRun = true
            list.forEachIndexed{ index, number ->
                val textView = numberTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true;
                textView.setBackgroundColor()
            }
        }
    }
    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for (i in 1..45) {
                if(!picNumberSet.contains(i)){
                    this.add(i)
                }
            }
        }
        numberList.shuffle()
        val newList = picNumberSet.toList() + numberList.subList(0, 6-picNumberSet.size)

        return newList.sorted()
    }
    private fun initClearButton(){
        clearButton.setOnClickListener{
            picNumberSet.clear()
            numberTextViewList.forEach{
                it.isVisible = false
            }
            didRun = false
        }
    }

    private fun initAddButton(){
        addButton.setOnClickListener{
            if(didRun){
                Toast.makeText(this, "초기화 후에 실행해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(picNumberSet.size>=5){
                Toast.makeText(this, "번호는 5개까지만", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(picNumberSet.contains(numberPicker.value)){
                Toast.makeText(this, "이미 선택한 번호입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val textView = numberTextViewList[picNumberSet.size]
            textView.isVisible= true
            textView.text = numberPicker.value.toString()
            picNumberSet.add(numberPicker.value)
            textView.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
        }
    }
}