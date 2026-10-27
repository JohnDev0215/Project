package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0135Hj;
import defpackage.C0153Ij;
import defpackage.C0783fh;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zziqe extends zza {
    public static final Parcelable.Creator CREATOR = new C0783fh();
    public int s;
    public int t;
    public int u;
    public long v;
    public int w;

    public zziqe() {
    }

    public static zziqe b(C0153Ij c0153Ij) {
        zziqe zziqeVar = new zziqe();
        C0135Hj c0135Hj = c0153Ij.a;
        zziqeVar.s = c0135Hj.a;
        zziqeVar.t = c0135Hj.b;
        zziqeVar.w = 0;
        Objects.requireNonNull(c0135Hj);
        zziqeVar.u = 0;
        Objects.requireNonNull(c0153Ij.a);
        zziqeVar.v = 0L;
        return zziqeVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 2, this.s);
        AbstractC1116lf.l(parcel, 3, this.t);
        AbstractC1116lf.l(parcel, 4, this.u);
        AbstractC1116lf.b(parcel, 5, this.v);
        AbstractC1116lf.l(parcel, 6, this.w);
        AbstractC1116lf.j(parcel, iN);
    }

    public zziqe(int i, int i2, int i3, long j, int i4) {
        this.s = i;
        this.t = i2;
        this.u = i3;
        this.v = j;
        this.w = i4;
    }
}
