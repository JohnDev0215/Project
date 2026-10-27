package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0729ej;
import defpackage.InterfaceC0217Mc;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzb extends zza implements InterfaceC0217Mc {
    public static final Parcelable.Creator CREATOR = new C0729ej();
    public final int s;
    public int t;
    public Intent u;

    public zzb(int i, int i2, Intent intent) {
        this.s = i;
        this.t = i2;
        this.u = intent;
    }

    @Override // defpackage.InterfaceC0217Mc
    public final Status a() {
        return this.t == 0 ? Status.w : Status.z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.l(parcel, 2, this.t);
        AbstractC1116lf.d(parcel, 3, this.u, i, false);
        AbstractC1116lf.j(parcel, iN);
    }
}
