package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GsonData implements Parcelable {
    public static final Parcelable.Creator<GsonData> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4354b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4355c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<String> f4356d;

    public static class a implements Parcelable.Creator<GsonData> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public GsonData createFromParcel(Parcel parcel) {
            String string = parcel.readString();
            String string2 = parcel.readString();
            ArrayList arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            return new GsonData(string, string2, arrayList);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public GsonData[] newArray(int i2) {
            return new GsonData[i2];
        }
    }

    public GsonData(String str, String str2, ArrayList<String> arrayList) {
        this.f4354b = str;
        this.f4355c = str2;
        this.f4356d = arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<String> j() {
        return this.f4356d;
    }

    public String k() {
        return this.f4354b;
    }

    public String l() {
        return this.f4355c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f4354b);
        parcel.writeString(this.f4355c);
        parcel.writeStringList(this.f4356d);
    }
}
