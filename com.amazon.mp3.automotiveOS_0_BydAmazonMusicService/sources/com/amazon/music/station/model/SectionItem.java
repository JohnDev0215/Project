package com.amazon.music.station.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public class SectionItem implements Parcelable {
    public static final Parcelable.Creator<SectionItem> CREATOR = new Parcelable.Creator<SectionItem>() { // from class: com.amazon.music.station.model.SectionItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SectionItem createFromParcel(Parcel parcel) {
            return new SectionItem(parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SectionItem[] newArray(int i) {
            return new SectionItem[i];
        }
    };
    private final String id;
    private final String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SectionItem(String str, String str2) {
        this.id = str;
        this.title = str2;
    }

    public String getId() {
        return this.id;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SectionItem sectionItem = (SectionItem) obj;
        String str = this.id;
        if (str == null ? sectionItem.id != null : !str.equals(sectionItem.id)) {
            return false;
        }
        String str2 = this.title;
        String str3 = sectionItem.title;
        return str2 == null ? str3 == null : str2.equals(str3);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.title;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }
}
