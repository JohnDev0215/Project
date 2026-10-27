package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class PoiType implements Parcelable {
    public static final Parcelable.Creator<PoiType> CREATOR = new Parcelable.Creator<PoiType>() { // from class: com.neusoft.na.ocservice.PoiType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiType createFromParcel(Parcel parcel) {
            return new PoiType(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiType[] newArray(int i) {
            return new PoiType[i];
        }
    };
    private String typeCode;
    private String typeName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public void setTypeCode(String str) {
        if (str != null) {
            this.typeCode = str;
        }
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        if (str != null) {
            this.typeName = str;
        }
    }

    public PoiType(String str, String str2) {
        this.typeCode = str;
        this.typeName = str2;
    }

    public PoiType() {
        this.typeCode = "";
        this.typeName = "";
    }

    protected PoiType(Parcel parcel) {
        this.typeCode = "";
        this.typeName = "";
        this.typeCode = parcel.readString();
        this.typeName = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.typeCode);
        parcel.writeString(this.typeName);
    }
}
