import os
if os.name=='nt':
  from colorama import init, Fore
  from colorama import Back
  from colorama import Style

  init(autoreset=True)


import sqlite3 as sq
from tkinter import *
from tkinter.ttk import Combobox
from tkinter import messagebox
import tkinter.font as font

sdvig_rus = 1072 
#1488
#1072
sdvig_eng = 97
lang_rus = "Русский"
lang_eng = "Английский"

count_B = 5
ss=str(count_B)

def cls():
    os.system('cls' if os.name=='nt' else 'clear')

# now, to clear the screen
#cls()
  
# Основная программа

window = Tk()
window.title("Подбор слов")
window.geometry('360x340')

Kn_bukv=[]
Sost_bukv=[]

def def_clear(e):
  global slovo, count_B, ss
  
  cls()
  count_B = int(Bukv_E.get())
#  lang_tek = Lang_E.get()
  
  ss=str(count_B)
  list=['']
  if Lang_E.get() == lang_eng:
    for i in range(0,26):
      list.append(chr(sdvig_eng+i))
  else:
    for i in range(0,32):
      list.append(chr(sdvig_rus+i))
  
  for i in range(0,len(slovo)):
    slovo[i][1]['values']=list

    if i>=count_B:
      slovo[i][1].grid_remove()
#      slovo[i][1]['state']='disabled'
    else:
      slovo[i][1]['state']='normal'
      slovo[i][1].grid()
    slovo[i][1].current(0)
  for i in range(0,len(Sost_bukv)):
    
    Kn_bukv[i]['bg']='grey80'
    Kn_bukv[i]['state']='normal'
    if Lang_E.get() == lang_eng:
      Kn_bukv[i]['text'] = chr(sdvig_eng+i)
      if i>=26: 
        Kn_bukv[i]['state']='disabled'
#        Kn_bukv[i]['text'] = '█'
      
        Kn_bukv[i]['text'] = '☺'
      
    else:
      Kn_bukv[i]['text'] = chr(sdvig_rus+i)
    Sost_bukv[i]=0
  
 
  
def def_proba(e):
  cls()
  #ss=chr(count_B+48)
  Chk=chk_state.get()
  if Lang_E.get() == lang_eng:
    con = sq.connect("vordly_en_ru.db") 
  else:
    con = sq.connect("vordly.db") 
  cur = con.cursor()
  if Chk==False and Lang_E.get() == lang_rus:
    str = "SELECT word FROM dict WHERE length(word)==" + ss
  else:
    str = "SELECT * FROM dict WHERE length(word)==" + ss
 
  for i in range(0,len(Sost_bukv)):
    if Sost_bukv[i]==2:
      bukva=Kn_bukv[i]['text']
      if bukva == 'е':
        str = str + " AND ((word LIKE '%"+bukva+"%') OR (word LIKE '%"+'ё'+"%'))"
      else:
        str = str + " AND word LIKE '%"+bukva+"%'"
                #print(str) 
    elif Sost_bukv[i]==1:
      bukva=Kn_bukv[i]['text']
      str = str + " AND NOT(word LIKE '%"+bukva+"%')"
      if bukva == 'е':
        str = str + " AND NOT(word LIKE '%"+'ё'+"%')"
                #print(str) 
        # print(str)    
  for i in range(0,len(slovo)):
    if slovo[i][1].get() != '':
      bukva=slovo[i][1].get()
      if bukva == 'е':
        str = str + " AND ((word LIKE '" + '_'*i+bukva+'_'*(count_B-i-1)+"') OR (word LIKE '" + '_'*i+'ё'+'_'*(count_B-i-1)+"'))"
      else:
        str = str + " AND word LIKE '" + '_'*i+bukva+'_'*(count_B-i-1)+"'"
              #print(str) 
 
  str = str+" LIMIT 30"
        # print(str)
  cur.execute(str)
  for result in cur:
    if Chk==False and Lang_E.get() == lang_rus: # Отключен показ комментариев
      print(result[0]) 
    else:
      if os.name=='nt':
        print(Fore.WHITE+Back.GREEN+Style.BRIGHT+result[0],Style.RESET_ALL, result[1]) 
      else:
        print("\033[3m\033[32m\033[40m{}\033[0m".format(result[0]), result[1])
    
  con.close
  
def selected(e):
  global btn, Sost_bukv
  w = e.widget
  if w.get() != '':
    if Lang_E.get()==lang_eng:  
      ind=ord(w.get())-sdvig_eng
    else:
      ind=ord(w.get())-sdvig_rus
  
    Kn_bukv[ind]['bg']='green'
    Sost_bukv[ind]=2
  

def callback(e):
  w = e.widget
  if Lang_E.get()==lang_eng:  
    ind=ord(w['text'])-sdvig_eng
  else:
    ind=ord(w['text'])-sdvig_rus
  if Lang_E.get()==lang_eng and ind>=26: return
  
  if Sost_bukv[ind]==0:
    w['bg']='red'
    Sost_bukv[ind]=1
  elif Sost_bukv[ind]==1:
    w['bg']='green'
    Sost_bukv[ind]=2
  elif Sost_bukv[ind]==2:
    w['bg']='grey80'
    Sost_bukv[ind]=0

list=['']
for i1 in range(0,4):
  for i2 in range(0,8):
    btn = Button(window,text=chr(sdvig_eng+i1*8+i2), bg='grey80')
#text=chr(sdvig_rus+i1*8+i2),bg='grey80')
    btn['font'] = font.Font(family= "Courier New", size=14)
    btn.bind('<Button-1>', callback)
#    btn.place(x=20*(i2+1), y=20*(i1+1), height=20, width=20)
    btn.grid(column=i2+1, row=i1+1, padx =0, pady=0, ipadx =0, ipady=0)
    if i1*8+i2 >= 26: #Количество букв в англ алфавите
      btn['state']='disabled'
      btn['text'] = '☻' #☻█

    Kn_bukv.append(btn)
      
    Sost_bukv.append(0)
    if i1*8+i2 < 26: list.append(chr(sdvig_eng+i1*8+i2))
#    list.append(chr(sdvig_rus+i1*8+i2))
comm1 = Label(window, text='НЕТ', bg="red")
comm2 = Label(window, text='ЕСТЬ', bg="green")
comm1.grid(column=1, row=5, columnspan=1)
comm2.grid(column=1, row=6, columnspan=1)
chk_state = BooleanVar()  
chk_state.set(False)  # задайте проверку состояния чекбокса  
chk_comm = Checkbutton(window, text='Показ коммента', var=chk_state)  
chk_comm.grid(column=3, row=5, columnspan=4)

Lang_L = Label(window, text='Язык:', fg="red")
Lang_L.grid(column=3, row=0, columnspan=1)
Lang_E = Combobox(window, width=10, height=3)
Lang_E['values'] = (lang_rus, lang_eng)
Lang_E.current(1)
Lang_E.grid(column=4, row=0, columnspan=3)

Bukv_L = Label(window, text='Букв:', fg="red")
Bukv_L.grid(column=8, row=0, columnspan=1)

Bukv_E = Combobox(window, width=1, height=3)
Bukv_E['values'] = (5, 6, 7)
Bukv_E.current(0)
Bukv_E.grid(column=9, row=0)


slovo=[]
for i in range(0,7):
  
  slovo.append([i, Combobox(window, width=2, height=10)])
  slovo[i][1]['values'] = list
  slovo[i][1]['font'] = font.Font(family= "Courier New", size=14)
  slovo[i][1].bind("<<ComboboxSelected>>", selected)
  slovo[i][1].current(0)
  slovo[i][1].grid(column=i+1, row=8)
  if i>=count_B:
    slovo[i][1].grid_remove()
    slovo[i][1]['state']='disabled'
  

proba = Button(window, text='проба',bg='grey80')
proba.bind('<Button-1>', def_proba)
#proba['font'] = font.Font(size=20)
proba.grid(column=1, row=9, columnspan=4)

clear = Button(window, text='очистка',bg='grey80')
clear.bind('<Button-1>', def_clear)
clear.grid(column=5, row=9, columnspan=4)

#print(os.name)

window.mainloop()  