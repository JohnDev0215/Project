package com.google.android.gms.vision.text.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.AbstractC1116lf;
import defpackage.C1890zk;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzp extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator CREATOR = new C1890zk();
    public final zzi[] s;
    public final zza t;
    public final zza u;
    public final String v;
    public final float w;
    public final String x;
    public final boolean y;

    public zzp(zzi[] zziVarArr, zza zzaVar, zza zzaVar2, String str, float f, String str2, boolean z) {
        this.s = zziVarArr;
        this.t = zzaVar;
        this.u = zzaVar2;
        this.v = str;
        this.w = f;
        this.x = str2;
        this.y = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.g(parcel, 2, this.s, i);
        AbstractC1116lf.d(parcel, 3, this.t, i, false);
        AbstractC1116lf.d(parcel, 4, this.u, i, false);
        AbstractC1116lf.e(parcel, 5, this.v, false);
        AbstractC1116lf.a(parcel, 6, this.w);
        AbstractC1116lf.e(parcel, 7, this.x, false);
        AbstractC1116lf.f(parcel, 8, this.y);
        AbstractC1116lf.j(parcel, iN);
    }
}
