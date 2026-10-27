package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0095Ff;
import defpackage.C1336pf;
import defpackage.InterfaceC0502af;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzam extends zza {
    public static final Parcelable.Creator CREATOR = new C1336pf();
    public final int s;
    public IBinder t;
    public ConnectionResult u;
    public boolean v;
    public boolean w;

    public zzam(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.s = i;
        this.t = iBinder;
        this.u = connectionResult;
        this.v = z;
        this.w = z2;
    }

    public final InterfaceC0502af b() {
        IBinder iBinder = this.t;
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        return iInterfaceQueryLocalInterface instanceof InterfaceC0502af ? (InterfaceC0502af) iInterfaceQueryLocalInterface : new C0095Ff(iBinder);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzam)) {
            return false;
        }
        zzam zzamVar = (zzam) obj;
        return this.u.equals(zzamVar.u) && b().equals(zzamVar.b());
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.c(parcel, 2, this.t);
        AbstractC1116lf.d(parcel, 3, this.u, i, false);
        AbstractC1116lf.f(parcel, 4, this.v);
        AbstractC1116lf.f(parcel, 5, this.w);
        AbstractC1116lf.j(parcel, iN);
    }
}
