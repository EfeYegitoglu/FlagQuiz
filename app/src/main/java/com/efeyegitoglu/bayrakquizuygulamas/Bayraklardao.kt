package com.efeyegitoglu.bayrakquizuygulamas

class Bayraklardao {

    fun rasgele5BayrakGetir(vt:Veritabani):ArrayList<Bayraklar>{

        val bayraklarList=ArrayList<Bayraklar>()
        val db=vt.writableDatabase
        val cursor=db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)

        while (cursor.moveToNext()){
            val bayrak=Bayraklar(cursor.getInt(cursor.getColumnIndex("bayrak_id"))
                , cursor.getString(cursor.getColumnIndex("bayrak_ad"))
                , cursor.getString(cursor.getColumnIndex("bayrak_resim")))

            bayraklarList.add(bayrak)
        }
        return bayraklarList
    }

    fun rasgele3YanlisBayrakGetir(vt:Veritabani,bayrak_id:Int):ArrayList<Bayraklar>{

        val bayraklarList=ArrayList<Bayraklar>()
        val db=vt.writableDatabase
        val cursor=db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != $bayrak_id ORDER BY RANDOM() LIMIT 3",null)

        while (cursor.moveToNext()){
            val bayrak=Bayraklar(cursor.getInt(cursor.getColumnIndex("bayrak_id"))
                , cursor.getString(cursor.getColumnIndex("bayrak_ad"))
                , cursor.getString(cursor.getColumnIndex("bayrak_resim")))

            bayraklarList.add(bayrak)
        }
        return bayraklarList
    }
}