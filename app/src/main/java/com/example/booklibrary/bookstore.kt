package com.example.booklibrary

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookstore")
class Book(@PrimaryKey @ColumnInfo(name = "book_id") val BookNo: Int,
                     @ColumnInfo(name = "book_name") val BookName: String?,
                     @ColumnInfo(name = "price") val BookPrice: Int?,
                     @ColumnInfo(name = "author_name") val AuthorName: String? )

class BookData(
    val bookId: Int,
    val bookName: String?,
    val price: Int,
    val author: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bookId)
        parcel.writeString(bookName)
        parcel.writeInt(price)
        parcel.writeString(author)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookData> {
        override fun createFromParcel(parcel: Parcel): BookData {
            return BookData(parcel)
        }

        override fun newArray(size: Int): Array<BookData?> {
            return arrayOfNulls(size)
        }
    }

}
