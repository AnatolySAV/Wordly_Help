package com.example.wordly_help

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.EditText
import androidx.core.view.isVisible
//import com.example.wordly_help.bt as bt1

@SuppressLint("ResourceAsColor")
fun Zap_Bukv() {
    //
    // test1
    txt_select1!!.text = ""
    txt_select2!!.text = ""
//    var Count_Bukv_Lang =32
//    if (Lang=="Рус") {
//        Count_Bukv_Lang =32
//    }
//    else {
//        Count_Bukv_Lang = 26
//    }
    sost_bukv[31] = 2
    if (Lang == "Рус") {
        for (i in 0..10) {
            bt[i]!!.text = Bukv_qwerty_Rus1[i]
        }
        for (i in 0..10) {
            bt[i + 11]!!.text = Bukv_qwerty_Rus2[i]
        }
        for (i in 0..8) {
            bt[i + 22]!!.text = Bukv_qwerty_Rus3[i]
        }
    } else {
        for (i in 0..10) {
            bt[i]!!.text = Bukv_qwerty_Eng1[i]
        }
        for (i in 0..10) {
            bt[i + 11]!!.text = Bukv_qwerty_Eng2[i]
        }
        for (i in 0..8) {
            bt[i + 22]!!.text = Bukv_qwerty_Eng3[i]
        }
    }
    for (i in 1..31) {
//      if (i<=Count_Bukv_Lang) {
            sost_bukv[i - 1] = 0
//            if (Lang=="Рус") {
//                bt[i - 1]!!.text = Char(sdvig_rus + i - 1).toString()
//            }
//            else {
//                bt[i - 1]!!.text = Char(sdvig_eng + i - 1).toString()
//            }
        if (bt[i - 1]!!.text != "") {
            bt[i - 1]!!.backgroundTintList = ColorStateList.valueOf(/* color = */R.color.bukv_0)
            bt[i - 1]?.setFocusable(true)
            bt[i - 1]?.isVisible = true
        }
        else {
            sost_bukv[i - 1] = 0
            bt[i - 1]?.setFocusable(false)
            bt[i - 1]?.isVisible = false

        }

    }
    for (i in 0..7){
        ed_slovo[i]!!.setText("")
        if (i<=Count_Bukv-1) {
            ed_slovo[i]!!.backgroundTintList = ColorStateList.valueOf(/* color = */Color.YELLOW)
            ed_slovo[i]?.setEnabled(true)
            ed_slovo[i]?.isVisible = true

        }
        else {
            ed_slovo[i]?.setEnabled(false)
            ed_slovo[i]?.isVisible = false

        }

    }

}