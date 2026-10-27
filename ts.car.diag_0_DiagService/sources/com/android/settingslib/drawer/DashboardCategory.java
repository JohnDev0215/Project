package com.android.settingslib.drawer;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class DashboardCategory implements Parcelable {
    public static final Parcelable.Creator<DashboardCategory> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1350c;
    private List<Tile> d = new ArrayList();

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Parcelable.Creator<DashboardCategory> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DashboardCategory createFromParcel(Parcel parcel) {
            return new DashboardCategory(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public DashboardCategory[] newArray(int i) {
            return new DashboardCategory[i];
        }
    }

    DashboardCategory(Parcel parcel) {
        this.f1350c = parcel.readString();
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            this.d.add(Tile.CREATOR.createFromParcel(parcel));
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1350c);
        int size = this.d.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.d.get(i2).writeToParcel(parcel, i);
        }
    }
}
