package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C1888zi;
import defpackage.InterfaceC0217Mc;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzae extends zza implements InterfaceC0217Mc {
    public static final Parcelable.Creator CREATOR = new C1888zi();
    public final Status s;

    public zzae(Status status) {
        this.s = status;
    }

    @Override // defpackage.InterfaceC0217Mc
    public final Status a() {
        return this.s;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.d(parcel, 1, this.s, i, false);
        AbstractC1116lf.j(parcel, iN);
    }
}
