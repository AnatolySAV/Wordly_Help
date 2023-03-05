package com.example.wordly_help

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatToggleButton
import com.example.wordly_help.R.drawable.text_style_no
import java.io.IOException

public var ed_slovo = arrayOfNulls<Button>(8)
public var bt = arrayOfNulls<Button>(32)
public var sost_bukv = Array<Int>(33,{0})


public var sdvig_rus = 1072
public var sdvig_eng = 97
public var Count_Bukv = 5
public var Lang = "Рус"
public var Sost_Comm = false
var btn_select: Button? = null
var  tx_vsego : TextView? = null
var txt_select1: TextView? = null
var txt_select2: TextView? = null


class MainActivity : AppCompatActivity() {
    // Объявим переменные компонентов
//    var btn_select: Button? = null
//    var  tx_vsego : TextView? = null
//    var txt_select1: TextView? = null
//    var txt_select2: TextView? = null

    // Переменная для работы с БД
    private var mDBHelper: DatabaseHelper? = null
    private var mDb: SQLiteDatabase? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// Формирование (копирование) БД в рабочую папку на смартфоне

        mDBHelper = DatabaseHelper(this)
        try {
            mDBHelper!!.updateDataBase()
        } catch (mIOException: IOException) {
            throw Error("UnableToUpdateDatabase")
        }
        mDb = try {
//            mDBHelper!!.writableDatabase
            mDBHelper!!.readableDatabase
        } catch (mSQLException: SQLException) {
            throw mSQLException
        }

        // Найдем компоненты в XML разметке
        txt_select1 = findViewById<TextView>(R.id.txt_select1) as TextView
        tx_vsego = findViewById<TextView>(R.id.tx_vsego) as TextView
        txt_select2 = findViewById<View>(R.id.txt_select2) as TextView
        btn_select = findViewById<View>(R.id.btn_select) as Button

        bt[0] = findViewById(R.id.bt_01_01) as Button // буквы
        bt[1] = findViewById(R.id.bt_01_02) as Button // буквы
        bt[2] = findViewById(R.id.bt_01_03) as Button // буквы
        bt[3] = findViewById(R.id.bt_01_04) as Button // буквы
        bt[4] = findViewById(R.id.bt_01_05) as Button // буквы
        bt[5] = findViewById(R.id.bt_01_06) as Button // буквы
        bt[6] = findViewById(R.id.bt_01_07) as Button // буквы
        bt[7] = findViewById(R.id.bt_01_08) as Button // буквы

        bt[8] = findViewById(R.id.bt_02_01) as Button // буквы
        bt[9] = findViewById(R.id.bt_02_02) as Button // буквы
        bt[10] = findViewById(R.id.bt_02_03) as Button // буквы
        bt[11] = findViewById(R.id.bt_02_04) as Button // буквы
        bt[12] = findViewById(R.id.bt_02_05) as Button // буквы
        bt[13] = findViewById(R.id.bt_02_06) as Button // буквы
        bt[14] = findViewById(R.id.bt_02_07) as Button // буквы
        bt[15] = findViewById(R.id.bt_02_08) as Button // буквы

        bt[16] = findViewById(R.id.bt_03_01) as Button // буквы
        bt[17] = findViewById(R.id.bt_03_02) as Button // буквы
        bt[18] = findViewById(R.id.bt_03_03) as Button // буквы
        bt[19] = findViewById(R.id.bt_03_04) as Button // буквы
        bt[20] = findViewById(R.id.bt_03_05) as Button // буквы
        bt[21] = findViewById(R.id.bt_03_06) as Button // буквы
        bt[22] = findViewById(R.id.bt_03_07) as Button // буквы
        bt[23] = findViewById(R.id.bt_03_08) as Button // буквы

        bt[24] = findViewById(R.id.bt_04_01) as Button // буквы
        bt[25] = findViewById(R.id.bt_04_02) as Button // буквы
        bt[26] = findViewById(R.id.bt_04_03) as Button // буквы
        bt[27] = findViewById(R.id.bt_04_04) as Button // буквы
        bt[28] = findViewById(R.id.bt_04_05) as Button // буквы
        bt[29] = findViewById(R.id.bt_04_06) as Button // буквы
        bt[30] = findViewById(R.id.bt_04_07) as Button // буквы
        bt[31] = findViewById(R.id.bt_04_08) as Button // буквы

        ed_slovo[0] = findViewById(R.id.ed_slovo1) as Button // буквы
        ed_slovo[1] = findViewById(R.id.ed_slovo2) as Button // буквы
        ed_slovo[2] = findViewById(R.id.ed_slovo3) as Button // буквы
        ed_slovo[3] = findViewById(R.id.ed_slovo4) as Button // буквы
        ed_slovo[4] = findViewById(R.id.ed_slovo5) as Button // буквы
        ed_slovo[5] = findViewById(R.id.ed_slovo6) as Button // буквы
        ed_slovo[6] = findViewById(R.id.ed_slovo7) as Button // буквы
        ed_slovo[7] = findViewById(R.id.ed_slovo8) as Button // буквы

        findViewById<TextView>(R.id.textView5).backgroundTintList =
            ColorStateList.valueOf(/* color = */Color.RED)
        findViewById<TextView>(R.id.textView4).backgroundTintList =
            ColorStateList.valueOf(/* color = */Color.GREEN)
        Zap_Bukv()
    }

    @SuppressLint("SuspiciousIndentation")
    fun Click_Select(view: View) {
        var product1 = ""
        var product2 = ""
        var Counter = 0


        var SQL_str = "SELECT *  FROM EngRusPereseh "
        if (Lang=="Рус") {
            SQL_str += "WHERE length(rus)=="
        }
        else {
            SQL_str += "WHERE length(eng)=="
        }
        SQL_str += Count_Bukv.toString()
        var  NNN = 0
        var  Bukva = ""
        if (Lang=="Рус") NNN = 32 else NNN =26
        for (i in 1..NNN) {
            if (sost_bukv[i-1]==2) {
                Bukva = bt[i-1]?.text.toString()
                if (Lang=="Рус") {
                    SQL_str += (" AND (rus LIKE '%"+Bukva+"%')")

                }
                else {
                    SQL_str += (" AND (eng LIKE '%"+Bukva+"%')")

                }
            }
            else if (sost_bukv[i-1]==1) {
                Bukva = bt[i-1]?.text.toString()
                if (Lang=="Рус") {
                    SQL_str += (" AND NOT(rus LIKE '%"+Bukva+"%')")

                }
                else {
                    SQL_str += (" AND NOT(eng LIKE '%"+Bukva+"%')")

                }
            }
        }
        for (i in 0..Count_Bukv-1){
            if (ed_slovo[i]!!.text.toString() !="") {
                Bukva = ed_slovo[i]?.text.toString().lowercase()
                if (Lang=="Рус") {
                    SQL_str += ((" AND (rus LIKE '" + "_".repeat(i) + Bukva + "_".repeat(Count_Bukv-i-1)+"')"))

                }
                else {
                    SQL_str += ((" AND (eng LIKE '" + "_".repeat(i) + Bukva + "_".repeat(Count_Bukv-i-1)+"')"))

                }

            }
        }
        if (Lang=="Рус") {
            SQL_str += " ORDER BY rus"
        }
        else {
            SQL_str += " ORDER BY eng"
        }

        val cursor = mDb?.rawQuery(SQL_str , null)
            cursor?.moveToFirst()
            while ((cursor?.isAfterLast == false) and (Counter<=11)){
                if (Lang=="Рус") {
                    product1 = product1 + cursor?.getString(2) + "\n"
                    product2 = product2 + cursor?.getString(1) + "\n"
                }
                else {
                    product1 = product1 + cursor?.getString(1) + "\n"
                    product2 = product2 + cursor?.getString(2) + "\n"
                }
                cursor?.moveToNext()
                Counter+=1
            }
        val SSS = "Всего: " + cursor!!.getCount().toString()
        tx_vsego?.text  = SSS

        cursor?.close()
        txt_select1!!.text = product1
        txt_select2!!.text = product2
//      Chr_code = Char(sdvig_rus).toString()
//        CHR_code = "а".toInt()
    }

    @SuppressLint("ResourceAsColor")
    fun Click_bukv(view: View) {
        var numb = 0
        for (i in 0..31){
            if ((view as Button).text.toString() == bt[i]?.text.toString()) {
                numb = i
                break
            }
        }
        val sost = sost_bukv[numb]
        when (sost) {
            0 -> {
                sost_bukv[numb] = 1
                bt[numb]?.backgroundTintList = ColorStateList.valueOf(/* color = */Color.RED)
            }
            1 -> {
                sost_bukv[numb] = 2
                bt[numb]?.backgroundTintList = ColorStateList.valueOf(/* color = */Color.GREEN)
            }
           2 -> {
                sost_bukv[numb] = 0
                bt[numb]?.backgroundTintList = ColorStateList.valueOf(/* color = */R.color.bukv_0)
            }
            else -> {}

        }

    }


    fun Click_Lang(view: View) {
        Lang = (view as RadioButton).text as String

    }
    fun Click_Count(view: View) {
        Count_Bukv = (view as RadioButton).text.toString().toInt()
    }
    fun Click_Comment(view: View) {
        Sost_Comm = (view as CheckBox).isChecked

    }
    fun Click_Clear(view: View) {
        Zap_Bukv()
    }

    fun Click_bukv_Slovo(view: View) {
        var PosNumb = 0
        if ((view as Button).text.toString()=="") {
// Кнопка пустая ищем любую зеленую
            for (i in 0..32) {
                if (sost_bukv[i] == 2) {
                    if (i!=32) (view as Button).text = bt[i]!!.text.toString()

                    else (view as Button).text = ""
                    break
                }
            }
        }
        else {
            for (i in 0..32) {
                if ((sost_bukv[i] == 2) and ((view as Button).text == bt[i]!!.text.toString())) {
                    PosNumb = i+1
                    break
                }
            }
            for (i in PosNumb..32) {
                if ((sost_bukv[i] == 2)) {
                    if (i!=32) (view as Button).text = bt[i]!!.text.toString()

                    else (view as Button).text = ""
                    break
                }
            }
        }
        if ((view as Button).text.toString()=="") {
            (view as Button)!!.backgroundTintList = ColorStateList.valueOf(/* color = */Color.YELLOW)

        }
        else {
            (view as Button)!!.backgroundTintList = ColorStateList.valueOf(/* color = */Color.GREEN)

        }
//        Если текст "" - кнопку делаем желтой

    }

}