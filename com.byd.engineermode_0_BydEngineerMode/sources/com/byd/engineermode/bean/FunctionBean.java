package com.byd.engineermode.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class FunctionBean implements Parcelable {
    public static final Parcelable.Creator<FunctionBean> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2285b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2286c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private State f2287d;

    static class a implements Parcelable.Creator<FunctionBean> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FunctionBean createFromParcel(Parcel parcel) {
            return new FunctionBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FunctionBean[] newArray(int i) {
            return new FunctionBean[i];
        }
    }

    public FunctionBean(String str) {
        this.f2287d = State.UNPERFORMED;
        this.f2286c = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long f() {
        return this.f2285b;
    }

    public String g() {
        return this.f2286c;
    }

    public State h() {
        return this.f2287d;
    }

    public void i(long j) {
        this.f2285b = j;
    }

    public void j(State state) {
        this.f2287d = state;
    }

    public String toString() {
        return "FunctionBean{id=" + this.f2285b + ", name='" + this.f2286c + "', state=" + this.f2287d + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f2285b);
        parcel.writeString(this.f2286c);
        State state = this.f2287d;
        parcel.writeInt(state == null ? -1 : state.ordinal());
    }

    protected FunctionBean(Parcel parcel) {
        this.f2287d = State.UNPERFORMED;
        this.f2285b = parcel.readLong();
        this.f2286c = parcel.readString();
        int i = parcel.readInt();
        this.f2287d = i == -1 ? null : State.values()[i];
    }
}
