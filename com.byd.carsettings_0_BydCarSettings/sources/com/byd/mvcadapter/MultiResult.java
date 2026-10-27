package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class MultiResult implements Parcelable {
    public static final Parcelable.Creator<MultiResult> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ClassLoader f3680a = MultiResult.class.getClassLoader();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public HashMap<Integer, Object> f3681b;

    public static class a implements Parcelable.Creator<MultiResult> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MultiResult createFromParcel(Parcel parcel) {
            MultiResult multiResult = new MultiResult();
            multiResult.c(parcel);
            return multiResult;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MultiResult[] newArray(int i) {
            return new MultiResult[i];
        }
    }

    public MultiResult() {
        if (this.f3681b == null) {
            this.f3681b = new HashMap<>();
        }
    }

    public void a(int i, Object obj) {
        this.f3681b.put(Integer.valueOf(i), obj);
    }

    public HashMap<Integer, Object> b() {
        return this.f3681b;
    }

    public void c(Parcel parcel) {
        parcel.readMap(this.f3681b, f3680a);
    }

    public int d() {
        HashMap<Integer, Object> map = this.f3681b;
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.f3681b);
    }
}
