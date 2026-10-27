package com.google.android.gms.vision.text.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.AbstractC1116lf;
import defpackage.C1396qk;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zza extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator CREATOR = new C1396qk();
    public final int s;
    public final int t;
    public final int u;
    public final int v;
    public final float w;

    public zza(int i, int i2, int i3, int i4, float f) {
        this.s = i;
        this.t = i2;
        this.u = i3;
        this.v = i4;
        this.w = f;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 2, this.s);
        AbstractC1116lf.l(parcel, 3, this.t);
        AbstractC1116lf.l(parcel, 4, this.u);
        AbstractC1116lf.l(parcel, 5, this.v);
        AbstractC1116lf.a(parcel, 6, this.w);
        AbstractC1116lf.j(parcel, iN);
    }
}
