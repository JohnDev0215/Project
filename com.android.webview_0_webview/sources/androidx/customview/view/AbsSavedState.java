package androidx.customview.view;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.A3;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbsSavedState implements Parcelable {
    public final Parcelable s;
    public static final AbsSavedState t = new AbsSavedState() { // from class: androidx.customview.view.AbsSavedState.1
    };
    public static final Parcelable.Creator CREATOR = new A3();

    public AbsSavedState(AnonymousClass1 anonymousClass1) {
        this.s = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.s, i);
    }

    public AbsSavedState(Parcelable parcelable) {
        if (parcelable != null) {
            this.s = parcelable == t ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    public AbsSavedState(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.s = parcelable == null ? t : parcelable;
    }
}
