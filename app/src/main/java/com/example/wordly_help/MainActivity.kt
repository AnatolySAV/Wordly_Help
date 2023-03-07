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
import androidx.core.view.isVisible
import com.example.wordly_help.R.drawable.text_style_no
import java.io.IOException
import kotlin.math.min

var ed_slovo = arrayOfNulls<Button>(8)
var bt = arrayOfNulls<Button>(31)
var sost_bukv = Array<Int>(32,{0})


// var sdvig_rus = 1072
// public var sdvig_eng = 97
var Count_Bukv = 5
var Lang = "Рус"
var Sost_Comm = false
var btn_select: Button? = null
var  tx_vsego : TextView? = null
var txt_select1: TextView? = null
var txt_select2: TextView? = null
var rg_lang: RadioGroup? = null
var rbt_ru: RadioButton? = null
var rbt_en: RadioButton? = null

public val Bukv_qwerty_Rus1 = arrayOf("й","ц","у","к","е","н","г", "ш", "щ", "з", "х")
public val Bukv_qwerty_Rus2 = arrayOf("ф", "ы","в","а","п","р","о","л", "д", "ж", "э")
public val Bukv_qwerty_Rus3 = arrayOf("я", "ч","с","м","и","т", "ь", "б", "ю")

public val Bukv_qwerty_Eng1 = arrayOf("q","w","e","r","t","y","u", "i", "o", "p", "")
public val Bukv_qwerty_Eng2 = arrayOf("a", "s","d","f","g","h","j","k", "l", "", "")
public val Bukv_qwerty_Eng3 = arrayOf("z", "x", "c", "v", "b","n","m","","")

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
        rg_lang = findViewById<View>(R.id.rg_lang) as RadioGroup
        rbt_ru = findViewById<View>(R.id.rbt_ru) as RadioButton
        rbt_en = findViewById<View>(R.id.rbt_en) as RadioButton

        bt[0] = findViewById(R.id.bt_01_01) as Button // буквы
        bt[1] = findViewById(R.id.bt_01_02) as Button // буквы
        bt[2] = findViewById(R.id.bt_01_03) as Button // буквы
        bt[3] = findViewById(R.id.bt_01_04) as Button // буквы
        bt[4] = findViewById(R.id.bt_01_05) as Button // буквы
        bt[5] = findViewById(R.id.bt_01_06) as Button // буквы
        bt[6] = findViewById(R.id.bt_01_07) as Button // буквы
        bt[7] = findViewById(R.id.bt_01_08) as Button // буквы
        bt[8] = findViewById(R.id.bt_01_09) as Button // буквы
        bt[9] = findViewById(R.id.bt_01_10) as Button // буквы
        bt[10] = findViewById(R.id.bt_01_11) as Button // буквы

        bt[11] = findViewById(R.id.bt_02_01) as Button // буквы
        bt[12] = findViewById(R.id.bt_02_02) as Button // буквы
        bt[13] = findViewById(R.id.bt_02_03) as Button // буквы
        bt[14] = findViewById(R.id.bt_02_04) as Button // буквы
        bt[15] = findViewById(R.id.bt_02_05) as Button // буквы
        bt[16] = findViewById(R.id.bt_02_06) as Button // буквы
        bt[17] = findViewById(R.id.bt_02_07) as Button // буквы
        bt[18] = findViewById(R.id.bt_02_08) as Button // буквы
        bt[19] = findViewById(R.id.bt_02_09) as Button // буквы
        bt[20] = findViewById(R.id.bt_02_10) as Button // буквы
        bt[21] = findViewById(R.id.bt_02_11) as Button // буквы

        bt[22] = findViewById(R.id.bt_03_01) as Button // буквы
        bt[23] = findViewById(R.id.bt_03_02) as Button // буквы
        bt[24] = findViewById(R.id.bt_03_03) as Button // буквы
        bt[25] = findViewById(R.id.bt_03_04) as Button // буквы
        bt[26] = findViewById(R.id.bt_03_05) as Button // буквы
        bt[27] = findViewById(R.id.bt_03_06) as Button // буквы
        bt[28] = findViewById(R.id.bt_03_07) as Button // буквы
        bt[29] = findViewById(R.id.bt_03_08) as Button // буквы
        bt[30] = findViewById(R.id.bt_03_09) as Button // буквы


        ed_slovo[0] = findViewById(R.id.ed_slovo1) as Button // буквы
        ed_slovo[1] = findViewById(R.id.ed_slovo2) as Button // буквы
        ed_slovo[2] = findViewById(R.id.ed_slovo3) as Button // буквы
        ed_slovo[3] = findViewById(R.id.ed_slovo4) as Button // буквы
        ed_slovo[4] = findViewById(R.id.ed_slovo5) as Button // буквы
        ed_slovo[5] = findViewById(R.id.ed_slovo6) as Button // буквы
        ed_slovo[6] = findViewById(R.id.ed_slovo7) as Button // буквы
        ed_slovo[7] = findViewById(R.id.ed_slovo8) as Button // буквы

//        findViewById<TextView>(R.id.textView5).backgroundTintList =
//            ColorStateList.valueOf(/* color = */Color.RED)
//        findViewById<TextView>(R.id.textView4).backgroundTintList =
//            ColorStateList.valueOf(/* color = */Color.GREEN)
        Zap_Bukv()
    }

    @SuppressLint("SuspiciousIndentation")
    fun Click_Select(view: View) {
        var product1 = ""
        var product2 = ""
        var Counter = 0
        var SQL_str = ""

        if (Sost_Comm != true) {
            SQL_str += ("SELECT *  FROM EngRusPereseh ")
        }
        else {
            SQL_str += ("SELECT *  FROM rus_full ")
        }

        if (Lang=="Рус") {
            SQL_str += "WHERE length(rus)=="
        }
        else {
            SQL_str += "WHERE length(eng)=="
        }
        SQL_str += Count_Bukv.toString()
        var  NNN = 0
        var  Bukva = ""
//        if (Lang=="Рус") NNN = 32 else NNN =26
        for (i in 1..31) {
            if (sost_bukv[i-1]==2) {
                Bukva = bt[i-1]?.text.toString()
                if (Lang=="Рус") {
                    if ((Bukva=="е") or  (Bukva=="ь")) {
                        if (Bukva == "е") {
                            SQL_str +=(" AND ((rus LIKE '%"+Bukva+"%') OR (rus LIKE '%"+'ё'+"%'))")
                        }
                        if (Bukva == "ь") {
                            SQL_str +=(" AND ((rus LIKE '%"+Bukva+"%') OR (rus LIKE '%"+'ъ'+"%'))")
                        }
                    }
                    else
                        SQL_str += (" AND (rus LIKE '%" + Bukva + "%')")
                }
                else {
                    SQL_str += (" AND (eng LIKE '%"+Bukva+"%')")

                }
            }
            else if (sost_bukv[i-1]==1) {
                Bukva = bt[i-1]?.text.toString()
                if (Lang=="Рус") {
                    SQL_str += (" AND NOT(rus LIKE '%"+Bukva+"%')")
                    if (Bukva=="е") {
                        SQL_str += (" AND NOT(rus LIKE '%" + "ё" + "%')")
                    }
                    if (Bukva=="ь") {
                        SQL_str += (" AND NOT(rus LIKE '%" + "ъ" + "%')")
                    }

                }
                else {
                    SQL_str += (" AND NOT(eng LIKE '%"+Bukva+"%')")

                }
            }
        }
        for (i in 0..Count_Bukv-1){
            if (ed_slovo[i]!!.text.toString() !="") {
                Bukva = ed_slovo[i]?.text.toString().lowercase()
                if (ed_slovo[i]!!.backgroundTintList == ColorStateList.valueOf(Color.GREEN))
                {
                    if (Lang == "Рус") {
                        SQL_str += ((" AND (rus LIKE '" + "_".repeat(i) + Bukva + "_".repeat(
                            Count_Bukv - i - 1
                        ) + "')"))

                    } else {
                        SQL_str += ((" AND (eng LIKE '" + "_".repeat(i) + Bukva + "_".repeat(
                            Count_Bukv - i - 1
                        ) + "')"))

                    }
                }
                else {
                    if (Lang == "Рус") {
                        SQL_str += ((" AND NOT(rus LIKE '" + "_".repeat(i) + Bukva + "_".repeat(
                            Count_Bukv - i - 1
                        ) + "')"))

                    } else {
                        SQL_str += ((" AND NOT(eng LIKE '" + "_".repeat(i) + Bukva + "_".repeat(
                            Count_Bukv - i - 1
                        ) + "')"))

                    }

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
            while ((cursor?.isAfterLast == false) and (Counter<=14)){
                if (Lang=="Рус")
                    if (Sost_Comm!=true) {
                        product1 = product1 + cursor?.getString(2) + "\n"
                        product2 = product2 + cursor?.getString(1) + "\n"
                    }
                    else {
                        val len_subs = min(25,cursor?.getString(1).toString().length)
                        val Str_sub = (cursor?.getString(1))!!.substring(1,len_subs)+"\n"
                        product1 = product1 + cursor?.getString(0) + "\n"
                        product2 = product2 + Str_sub
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
                bt[numb]?.setTextColor(ColorStateList.valueOf(/* color = */Color.WHITE))
            }
            1 -> {
                sost_bukv[numb] = 2
                bt[numb]?.backgroundTintList = ColorStateList.valueOf(/* color = */Color.GREEN)
                bt[numb]?.setTextColor(ColorStateList.valueOf(/* color = */Color.BLACK))
            }
           2 -> {
                sost_bukv[numb] = 0
                bt[numb]?.backgroundTintList = ColorStateList.valueOf(/* color = */R.color.bukv_0)
                bt[numb]?.setTextColor(ColorStateList.valueOf(/* color = */Color.WHITE))
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
        if (Sost_Comm == true)  rg_lang!!.isVisible  = false
        else rg_lang!!.isVisible  = true

    }
    fun Click_Clear(view: View) {
        Zap_Bukv()
        if (rbt_ru!!.isChecked) Lang = rbt_ru!!.text as String
        else Lang = rbt_en!!.text as String
    }

    fun Click_bukv_Slovo(view: View) {
        var PosNumb = 0
        var Fix_Numb = 0
        if ((view as Button).text.toString()=="") {
// Кнопка пустая ищем любую зеленую или красную
            for (i in 0..31) {
                if ((sost_bukv[i] == 2) or (sost_bukv[i] == 1)) {
                    if (i!=31) {
                        view.text = bt[i]!!.text.toString()
                        Fix_Numb = i
                    }

                    else view.text = ""
                    break
                }
            }
        }
        else {
            for (i in 0..31) {
                if (((sost_bukv[i] == 2) or (sost_bukv[i] == 1)) and (view.text == bt[i]!!.text.toString())) {
                    PosNumb = i+1
                    break
                }
            }
            for (i in PosNumb..31) {
                if (((sost_bukv[i] == 2) or (sost_bukv[i] == 1))) {
                    if (i!=31) {
                        view.text = bt[i]!!.text.toString()
                        Fix_Numb = i
                    }
                    else view.text = ""
                    break
                }
            }
        }
        if (view.text.toString()=="") {
            view!!.backgroundTintList = ColorStateList.valueOf(/* color = */Color.YELLOW)
        }
        else {
            if  (sost_bukv[Fix_Numb]==2) (view as Button)!!.backgroundTintList = ColorStateList.valueOf(/* color = */Color.GREEN)
            else if (sost_bukv[Fix_Numb]==1) (view as Button)!!.backgroundTintList = ColorStateList.valueOf(/* color = */Color.RED)

        }
//        Если текст "" - кнопку делаем желтой

    }

}