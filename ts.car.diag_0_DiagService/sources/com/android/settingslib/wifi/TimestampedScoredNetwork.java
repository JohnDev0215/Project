package com.android.settingslib.wifi;

import android.net.ScoredNetwork;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
class TimestampedScoredNetwork implements Parcelable {
    public static final Parcelable.Creator<TimestampedScoredNetwork> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ScoredNetwork f1390c;
    private long d;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Parcelable.Creator<TimestampedScoredNetwork> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public TimestampedScoredNetwork createFromParcel(Parcel parcel) {
            return new TimestampedScoredNetwork(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public TimestampedScoredNetwork[] newArray(int i) {
            return new TimestampedScoredNetwork[i];
        }
    }

    TimestampedScoredNetwork(ScoredNetwork scoredNetwork, long j) {
        this.f1390c = scoredNetwork;
        this.d = j;
    }

    public ScoredNetwork a() {
        return this.f1390c;
    }

    public long b() {
        return this.d;
    }

    public void c(ScoredNetwork scoredNetwork, long j) {
        this.f1390c = scoredNetwork;
        this.d = j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f1390c, i);
        parcel.writeLong(this.d);
    }

    protected TimestampedScoredNetwork(Parcel parcel) {
        this.f1390c = parcel.readParcelable(ScoredNetwork.class.getClassLoader());
        this.d = parcel.readLong();
    }
}
