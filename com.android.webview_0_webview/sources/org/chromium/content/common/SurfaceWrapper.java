package org.chromium.content.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import defpackage.C1810yD;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SurfaceWrapper implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1810yD();
    public final Surface s;
    public final boolean t;

    public SurfaceWrapper(Surface surface, boolean z) {
        this.s = surface;
        this.t = z;
    }

    public static SurfaceWrapper create(Surface surface, boolean z) {
        return new SurfaceWrapper(surface, z);
    }

    public boolean canBeUsedWithSurfaceControl() {
        return this.t;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Surface getSurface() {
        return this.s;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.s.writeToParcel(parcel, 0);
        parcel.writeInt(this.t ? 1 : 0);
    }
}
