package com.google.android.material.navigation;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.internal.ParcelableSparseArray;

/* JADX INFO: loaded from: classes.dex */
class NavigationBarPresenter$SavedState implements Parcelable {
    public static final Parcelable.Creator<NavigationBarPresenter$SavedState> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f4674e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ParcelableSparseArray f4675g;

    static class a implements Parcelable.Creator<NavigationBarPresenter$SavedState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NavigationBarPresenter$SavedState createFromParcel(Parcel parcel) {
            return new NavigationBarPresenter$SavedState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public NavigationBarPresenter$SavedState[] newArray(int i7) {
            return new NavigationBarPresenter$SavedState[i7];
        }
    }

    NavigationBarPresenter$SavedState() {
    }

    NavigationBarPresenter$SavedState(Parcel parcel) {
        this.f4674e = parcel.readInt();
        this.f4675g = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f4674e);
        parcel.writeParcelable(this.f4675g, 0);
    }
}
