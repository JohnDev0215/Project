package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class GsonData implements Parcelable {
    public static final Parcelable.Creator<GsonData> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f626b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f627c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<String> f628d;

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
        public GsonData[] newArray(int i) {
            return new GsonData[i];
        }
    }

    public GsonData(String str, String str2, ArrayList<String> arrayList) {
        this.f626b = str;
        this.f627c = str2;
        this.f628d = arrayList;
    }

    public ArrayList<String> a() {
        return this.f628d;
    }

    public String b() {
        return this.f626b;
    }

    public String c() {
        return this.f627c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f626b);
        parcel.writeString(this.f627c);
        parcel.writeStringList(this.f628d);
    }
}
