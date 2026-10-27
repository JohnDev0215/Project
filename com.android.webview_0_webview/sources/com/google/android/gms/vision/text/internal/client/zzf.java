package com.google.android.gms.vision.text.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.AbstractC1116lf;
import defpackage.C1615uk;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzf extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator CREATOR = new C1615uk();
    public final boolean A;
    public final int B;
    public final int C;
    public final zzp[] s;
    public final zza t;
    public final zza u;
    public final zza v;
    public final String w;
    public final float x;
    public final String y;
    public final int z;

    public zzf(zzp[] zzpVarArr, zza zzaVar, zza zzaVar2, zza zzaVar3, String str, float f, String str2, int i, boolean z, int i2, int i3) {
        this.s = zzpVarArr;
        this.t = zzaVar;
        this.u = zzaVar2;
        this.v = zzaVar3;
        this.w = str;
        this.x = f;
        this.y = str2;
        this.z = i;
        this.A = z;
        this.B = i2;
        this.C = i3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.g(parcel, 2, this.s, i);
        AbstractC1116lf.d(parcel, 3, this.t, i, false);
        AbstractC1116lf.d(parcel, 4, this.u, i, false);
        AbstractC1116lf.d(parcel, 5, this.v, i, false);
        AbstractC1116lf.e(parcel, 6, this.w, false);
        AbstractC1116lf.a(parcel, 7, this.x);
        AbstractC1116lf.e(parcel, 8, this.y, false);
        AbstractC1116lf.l(parcel, 9, this.z);
        AbstractC1116lf.f(parcel, 10, this.A);
        AbstractC1116lf.l(parcel, 11, this.B);
        AbstractC1116lf.l(parcel, 12, this.C);
        AbstractC1116lf.j(parcel, iN);
    }
}
