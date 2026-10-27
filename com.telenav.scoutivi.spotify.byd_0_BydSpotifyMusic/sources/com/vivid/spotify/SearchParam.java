package com.vivid.spotify;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class SearchParam implements Parcelable {
    public static final Parcelable.Creator<SearchParam> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5190e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f5191g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f5192h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private String f5193i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private String f5194j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private String f5195k;

    class a implements Parcelable.Creator<SearchParam> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SearchParam createFromParcel(Parcel parcel) {
            return new SearchParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public SearchParam[] newArray(int i7) {
            return new SearchParam[i7];
        }
    }

    private SearchParam() {
    }

    public String b() {
        return this.f5193i;
    }

    public String c() {
        return this.f5192h;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String h() {
        return this.f5194j;
    }

    public String j() {
        return this.f5195k;
    }

    public String k() {
        return this.f5190e;
    }

    public String m() {
        return this.f5191g;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f5190e);
        parcel.writeString(this.f5191g);
        parcel.writeString(this.f5192h);
        parcel.writeString(this.f5193i);
        parcel.writeString(this.f5194j);
        parcel.writeString(this.f5195k);
    }

    protected SearchParam(Parcel parcel) {
        this.f5190e = parcel.readString();
        this.f5191g = parcel.readString();
        this.f5192h = parcel.readString();
        this.f5193i = parcel.readString();
        this.f5194j = parcel.readString();
        this.f5195k = parcel.readString();
    }
}
