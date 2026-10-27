package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.C1086l4;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1086l4();
    public FragmentState[] s;
    public int[] t;
    public BackStackState[] u;
    public int v;
    public int w;

    public FragmentManagerState() {
        this.v = -1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.s, i);
        parcel.writeIntArray(this.t);
        parcel.writeTypedArray(this.u, i);
        parcel.writeInt(this.v);
        parcel.writeInt(this.w);
    }

    public FragmentManagerState(Parcel parcel) {
        this.v = -1;
        this.s = (FragmentState[]) parcel.createTypedArray(FragmentState.CREATOR);
        this.t = parcel.createIntArray();
        this.u = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.v = parcel.readInt();
        this.w = parcel.readInt();
    }
}
