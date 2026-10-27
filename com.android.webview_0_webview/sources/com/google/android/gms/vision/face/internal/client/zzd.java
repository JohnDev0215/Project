package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0842gk;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzd extends zza {
    public static final Parcelable.Creator CREATOR = new C0842gk();
    public int s;
    public int t;
    public int u;
    public boolean v;
    public boolean w;
    public float x;

    public zzd() {
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 2, this.s);
        AbstractC1116lf.l(parcel, 3, this.t);
        AbstractC1116lf.l(parcel, 4, this.u);
        AbstractC1116lf.f(parcel, 5, this.v);
        AbstractC1116lf.f(parcel, 6, this.w);
        AbstractC1116lf.a(parcel, 7, this.x);
        AbstractC1116lf.j(parcel, iN);
    }

    public zzd(int i, int i2, int i3, boolean z, boolean z2, float f) {
        this.s = i;
        this.t = i2;
        this.u = i3;
        this.v = z;
        this.w = z2;
        this.x = f;
    }
}
