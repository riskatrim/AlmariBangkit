package com.example.almaritest

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TBL_USER = "tbl_user"
        private const val TBL_WARDROBE = "tbl_wardrobe"
        private const val ID = "id"
        private const val UNAME = "uname"
        private const val EMAIL = "email"
        private const val PW = "pw"
        private const val CLOTHES = "clothes"
        private const val LABEL = "label"
        private const val COLOR = "color"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblUser =
            ("CREATE TABLE $TBL_USER($ID INTEGER PRIMARY KEY,$UNAME TEXT,$PW TEXT,$EMAIL TEXT)")
        val createTblWardrobe =
            ("CREATE TABLE $TBL_WARDROBE($ID INTEGER PRIMARY KEY,$UNAME TEXT,$LABEL TEXT,$COLOR TEXT,$CLOTHES IMAGE)")
        db?.execSQL(createTblUser)
        db?.execSQL(createTblWardrobe)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        db.execSQL("DROP TABLE IF EXISTS $TBL_WARDROBE")
        onCreate(db)
    }

    fun insertUser(std: UserModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(UNAME, std.uname)
        contentValues.put(EMAIL, std.email)
        contentValues.put(PW, std.pw)

        val success = db.insert(TBL_USER, null, contentValues)
        db.close()
        return success
    }
    fun insertWardrobe(std: UserModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(UNAME, std.uname)
        contentValues.put(LABEL, std.label)
        contentValues.put(COLOR, std.color)
        contentValues.put(CLOTHES, std.clothes)

        val success = db.insert(TBL_WARDROBE, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllClothes(): ArrayList<UserModel> {
        val stdList: ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT CLOTHES FROM $TBL_WARDROBE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var uname: String
        var label: String
        var color: String
        var clothes: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                uname = cursor.getString(cursor.getColumnIndex("name"))
                label = cursor.getString(cursor.getColumnIndex("label"))
                color = cursor.getString(cursor.getColumnIndex("color"))
                clothes = cursor.getInt(cursor.getColumnIndex("clothes"))

                val std = UserModel(
                    id = id,
                    uname = uname,
                    label = label,
                    color = color,
                    clothes = clothes
                )
                stdList.add(std)
            } while (cursor.moveToNext())
        }
        return stdList
    }
}