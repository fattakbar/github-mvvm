package com.yogiw.githubmvvm.data

import android.os.Parcel
import android.os.Parcelable

class MainData (
        var name: String? = null,
        var location: String? = null,
        var email: String? = null,
        var company: String? = null,
        var avatarUrl: String? =null,
        var followers: Int? = null,
        var following: Int? = null,
        var publicRepos: Int? = null
// Setelah membuat properti + implements parcalable trus alt + enter buat implement kode dibawah
) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeString(email)
        parcel.writeString(company)
        parcel.writeString(avatarUrl)
        parcel.writeValue(followers)
        parcel.writeValue(following)
        parcel.writeValue(publicRepos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainData> {
        override fun createFromParcel(parcel: Parcel): MainData {
            return MainData(parcel)
        }

        override fun newArray(size: Int): Array<MainData?> {
            return arrayOfNulls(size)
        }
    }

}