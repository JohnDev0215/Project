package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C1121lk;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class LandmarkParcel extends zza {
    public static final Parcelable.Creator CREATOR = new C1121lk();
    public final int s;
    public final float t;
    public final float u;
    public final int v;

    public LandmarkParcel(int i, float f, float f2, int i2) {
        this.s = i;
        this.t = f;
        this.u = f2;
        this.v = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.a(parcel, 2, this.t);
        AbstractC1116lf.a(parcel, 3, this.u);
        AbstractC1116lf.l(parcel, 4, this.v);
        AbstractC1116lf.j(parcel, iN);
    }
}
