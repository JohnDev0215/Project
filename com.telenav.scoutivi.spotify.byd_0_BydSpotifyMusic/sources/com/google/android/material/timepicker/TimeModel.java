package com.google.android.material.timepicker;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class TimeModel implements Parcelable {
    public static final Parcelable.Creator<TimeModel> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.google.android.material.timepicker.a f4963e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final com.google.android.material.timepicker.a f4964g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final int f4965h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f4966i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    int f4967j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    int f4968k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f4969l;

    static class a implements Parcelable.Creator<TimeModel> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public TimeModel createFromParcel(Parcel parcel) {
            return new TimeModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public TimeModel[] newArray(int i7) {
            return new TimeModel[i7];
        }
    }

    public TimeModel() {
        this(0);
    }

    public static String b(Resources resources, CharSequence charSequence) {
        return c(resources, charSequence, "%02d");
    }

    public static String c(Resources resources, CharSequence charSequence, String str) {
        return String.format(resources.getConfiguration().locale, str, Integer.valueOf(Integer.parseInt(String.valueOf(charSequence))));
    }

    private static int h(int i7) {
        return i7 >= 12 ? 1 : 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeModel)) {
            return false;
        }
        TimeModel timeModel = (TimeModel) obj;
        return this.f4966i == timeModel.f4966i && this.f4967j == timeModel.f4967j && this.f4965h == timeModel.f4965h && this.f4968k == timeModel.f4968k;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4965h), Integer.valueOf(this.f4966i), Integer.valueOf(this.f4967j), Integer.valueOf(this.f4968k)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f4966i);
        parcel.writeInt(this.f4967j);
        parcel.writeInt(this.f4968k);
        parcel.writeInt(this.f4965h);
    }

    public TimeModel(int i7) {
        this(0, 0, 10, i7);
    }

    public TimeModel(int i7, int i8, int i9, int i10) {
        this.f4966i = i7;
        this.f4967j = i8;
        this.f4968k = i9;
        this.f4965h = i10;
        this.f4969l = h(i7);
        this.f4963e = new com.google.android.material.timepicker.a(59);
        this.f4964g = new com.google.android.material.timepicker.a(i10 == 1 ? 24 : 12);
    }

    protected TimeModel(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
    }
}
