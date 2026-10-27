package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MultiResult implements Parcelable {
    public static final Parcelable.Creator<MultiResult> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static ClassLoader f2772d = MultiResult.class.getClassLoader();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashMap<Integer, Object> f2773e;

    public static class a implements Parcelable.Creator<MultiResult> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MultiResult createFromParcel(Parcel parcel) {
            MultiResult multiResult = new MultiResult();
            multiResult.h(parcel);
            return multiResult;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MultiResult[] newArray(int i8) {
            return new MultiResult[i8];
        }
    }

    public MultiResult() {
        if (this.f2773e == null) {
            this.f2773e = new HashMap<>();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void f(int i8, Object obj) {
        this.f2773e.put(Integer.valueOf(i8), obj);
    }

    public HashMap<Integer, Object> g() {
        return this.f2773e;
    }

    public void h(Parcel parcel) {
        parcel.readMap(this.f2773e, f2772d);
    }

    public int i() {
        HashMap<Integer, Object> map = this.f2773e;
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeMap(this.f2773e);
    }
}
