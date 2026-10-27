package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractBinderC0349Ti;
import defpackage.AbstractBinderC0439Yi;
import defpackage.AbstractC1116lf;
import defpackage.C0026Bi;
import defpackage.C0223Mi;
import defpackage.InterfaceC0008Ai;
import defpackage.InterfaceC0367Ui;
import defpackage.InterfaceC0385Vi;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbj extends zza {
    public static final Parcelable.Creator CREATOR = new C0223Mi();
    public int s;
    public zzbh t;
    public InterfaceC0385Vi u;
    public PendingIntent v;
    public InterfaceC0367Ui w;
    public InterfaceC0008Ai x;

    public zzbj(int i, zzbh zzbhVar, IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2, IBinder iBinder3) {
        this.s = i;
        this.t = zzbhVar;
        InterfaceC0008Ai c0026Bi = null;
        this.u = iBinder == null ? null : AbstractBinderC0439Yi.v(iBinder);
        this.v = pendingIntent;
        this.w = iBinder2 == null ? null : AbstractBinderC0349Ti.v(iBinder2);
        if (iBinder3 != null) {
            IInterface iInterfaceQueryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            c0026Bi = iInterfaceQueryLocalInterface instanceof InterfaceC0008Ai ? (InterfaceC0008Ai) iInterfaceQueryLocalInterface : new C0026Bi(iBinder3);
        }
        this.x = c0026Bi;
    }

    public static zzbj b(InterfaceC0385Vi interfaceC0385Vi, InterfaceC0008Ai interfaceC0008Ai) {
        return new zzbj(2, null, interfaceC0385Vi.asBinder(), null, null, interfaceC0008Ai != null ? interfaceC0008Ai.asBinder() : null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.d(parcel, 2, this.t, i, false);
        InterfaceC0385Vi interfaceC0385Vi = this.u;
        AbstractC1116lf.c(parcel, 3, interfaceC0385Vi == null ? null : interfaceC0385Vi.asBinder());
        AbstractC1116lf.d(parcel, 4, this.v, i, false);
        InterfaceC0367Ui interfaceC0367Ui = this.w;
        AbstractC1116lf.c(parcel, 5, interfaceC0367Ui == null ? null : interfaceC0367Ui.asBinder());
        InterfaceC0008Ai interfaceC0008Ai = this.x;
        AbstractC1116lf.c(parcel, 6, interfaceC0008Ai != null ? interfaceC0008Ai.asBinder() : null);
        AbstractC1116lf.j(parcel, iN);
    }
}
