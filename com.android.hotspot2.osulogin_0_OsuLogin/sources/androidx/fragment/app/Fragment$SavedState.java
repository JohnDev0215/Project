package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage, ParcelClassLoader"})
public class Fragment$SavedState implements Parcelable {
    public static final Parcelable.Creator<Fragment$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<Fragment$SavedState>() { // from class: androidx.fragment.app.Fragment$SavedState.1
        @Override // android.os.Parcelable.Creator
        public Fragment$SavedState createFromParcel(Parcel parcel) {
            return new Fragment$SavedState(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public Fragment$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new Fragment$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public Fragment$SavedState[] newArray(int i) {
            return new Fragment$SavedState[i];
        }
    };
    final Bundle mState;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    Fragment$SavedState(Parcel parcel, ClassLoader classLoader) {
        Bundle bundle = parcel.readBundle();
        this.mState = bundle;
        if (classLoader == null || bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mState);
    }
}
