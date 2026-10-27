package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzak;
import defpackage.AbstractC1116lf;
import defpackage.C1120lj;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzk extends zza {
    public static final Parcelable.Creator CREATOR = new C1120lj();
    public final int s;
    public final zzak t;

    public zzk(int i, zzak zzakVar) {
        this.s = i;
        this.t = zzakVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.d(parcel, 2, this.t, i, false);
        AbstractC1116lf.j(parcel, iN);
    }

    public zzk(zzak zzakVar) {
        this.s = 1;
        this.t = zzakVar;
    }
}
