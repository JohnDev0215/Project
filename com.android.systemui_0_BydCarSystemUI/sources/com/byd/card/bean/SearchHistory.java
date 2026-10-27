package com.byd.card.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class SearchHistory implements Parcelable {
    public static final Parcelable.Creator<SearchHistory> CREATOR = new Parcelable.Creator<SearchHistory>() { // from class: com.byd.card.bean.SearchHistory.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchHistory createFromParcel(Parcel in) {
            return new SearchHistory(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchHistory[] newArray(int size) {
            return new SearchHistory[size];
        }
    };
    public int id;
    public String text;
    public long time;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SearchHistory(String text, long time) {
        this.text = text;
        this.time = time;
    }

    protected SearchHistory(Parcel in) {
        this.text = in.readString();
        this.time = in.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.text);
        parcel.writeLong(this.time);
    }
}
