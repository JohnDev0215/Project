package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MultiResult implements Parcelable {
    public static final Parcelable.Creator<MultiResult> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ClassLoader f4357b = MultiResult.class.getClassLoader();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HashMap<Integer, Object> f4358c;

    public static class a implements Parcelable.Creator<MultiResult> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MultiResult createFromParcel(Parcel parcel) {
            MultiResult multiResult = new MultiResult();
            multiResult.l(parcel);
            return multiResult;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MultiResult[] newArray(int i2) {
            return new MultiResult[i2];
        }
    }

    public MultiResult() {
        if (this.f4358c == null) {
            this.f4358c = new HashMap<>();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void j(int i2, Object obj) {
        this.f4358c.put(Integer.valueOf(i2), obj);
    }

    public HashMap<Integer, Object> k() {
        return this.f4358c;
    }

    public void l(Parcel parcel) {
        parcel.readMap(this.f4358c, f4357b);
    }

    public int m() {
        HashMap<Integer, Object> map = this.f4358c;
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeMap(this.f4358c);
    }
}
