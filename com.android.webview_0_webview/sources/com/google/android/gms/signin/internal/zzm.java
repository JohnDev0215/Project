package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzam;
import defpackage.AbstractC1116lf;
import defpackage.C1175mj;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzm extends zza {
    public static final Parcelable.Creator CREATOR = new C1175mj();
    public final int s;
    public final ConnectionResult t;
    public final zzam u;

    public zzm(int i, ConnectionResult connectionResult, zzam zzamVar) {
        this.s = i;
        this.t = connectionResult;
        this.u = zzamVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.d(parcel, 2, this.t, i, false);
        AbstractC1116lf.d(parcel, 3, this.u, i, false);
        AbstractC1116lf.j(parcel, iN);
    }

    public zzm() {
        ConnectionResult connectionResult = new ConnectionResult(8, null);
        this.s = 1;
        this.t = connectionResult;
        this.u = null;
    }
}
