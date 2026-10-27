package org.chromium.weblayer_private.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.C0943iS;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NavigateParams implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0943iS();
    public boolean s;

    public NavigateParams(Parcel parcel, C0943iS c0943iS) {
        this.s = false;
        this.s = parcel.readInt() == 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.s ? 1 : 0);
    }
}
