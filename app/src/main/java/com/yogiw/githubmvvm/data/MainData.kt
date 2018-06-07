package com.yogiw.githubmvvm.data

import android.os.Parcel
import android.os.Parcelable

class MainData (
        var name: String? = "-",
        var location: String? = "-",
        var email: String? = "-",
        var company: String? = "-",
        var avatarUrl: String? ="-",
        var followers: String? = "0",
        var following: String? = "0",
        var publicRepos: String? = "0"
// Setelah membuat properti + implements parcalable trus alt + enter buat implement kode dibawah
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeString(email)
        parcel.writeString(company)
        parcel.writeString(avatarUrl)
        parcel.writeString(followers)
        parcel.writeString(following)
        parcel.writeString(publicRepos)
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
