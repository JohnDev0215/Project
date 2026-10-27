package com.amazon.music.station.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class RefinementItem implements Parcelable {
    public static final Parcelable.Creator<RefinementItem> CREATOR = new Parcelable.Creator<RefinementItem>() { // from class: com.amazon.music.station.model.RefinementItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RefinementItem createFromParcel(Parcel parcel) {
            return new RefinementItem(parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RefinementItem[] newArray(int i) {
            return new RefinementItem[i];
        }
    };
    private final String artworkUrl;
    private final String id;
    private final String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RefinementItem(String str, String str2, String str3) {
        this.title = str;
        this.id = str2;
        this.artworkUrl = str3;
    }

    public String getTitle() {
        return this.title;
    }

    public String getId() {
        return this.id;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.id);
        parcel.writeString(this.artworkUrl);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RefinementItem.class != obj.getClass()) {
            return false;
        }
        RefinementItem refinementItem = (RefinementItem) obj;
        String str = this.artworkUrl;
        if (str == null ? refinementItem.artworkUrl != null : !str.equals(refinementItem.artworkUrl)) {
            return false;
        }
        String str2 = this.id;
        if (str2 == null ? refinementItem.id != null : !str2.equals(refinementItem.id)) {
            return false;
        }
        String str3 = this.title;
        String str4 = refinementItem.title;
        return str3 == null ? str4 == null : str3.equals(str4);
    }

    public int hashCode() {
        String str = this.title;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.id;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.artworkUrl;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "RefinementItem{title='" + this.title + "', id='" + this.id + "'}";
    }
}
