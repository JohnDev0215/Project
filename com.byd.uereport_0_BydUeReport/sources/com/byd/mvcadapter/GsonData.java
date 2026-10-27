package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GsonData implements Parcelable {
    public static final Parcelable.Creator<GsonData> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2769d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2770e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<String> f2771f;

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
        public GsonData[] newArray(int i8) {
            return new GsonData[i8];
        }
    }

    public GsonData(String str, String str2, ArrayList<String> arrayList) {
        this.f2769d = str;
        this.f2770e = str2;
        this.f2771f = arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<String> f() {
        return this.f2771f;
    }

    public String g() {
        return this.f2769d;
    }

    public String h() {
        return this.f2770e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeString(this.f2769d);
        parcel.writeString(this.f2770e);
        parcel.writeStringList(this.f2771f);
    }
}
