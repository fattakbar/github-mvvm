package com.yogiw.githubmvvm.data

import android.os.Parcel
import android.os.Parcelable

class RepoData(
        var repoName: String? = "-",
        var language: String? = "-",
        var repoDescription: String? = "-",
        var url : String? = "-"
) :Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(repoName)
        parcel.writeString(language)
        parcel.writeString(repoDescription)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepoData> {
        override fun createFromParcel(parcel: Parcel): RepoData {
            return RepoData(parcel)
        }

        override fun newArray(size: Int): Array<RepoData?> {
            return arrayOfNulls(size)
        }
    }
}