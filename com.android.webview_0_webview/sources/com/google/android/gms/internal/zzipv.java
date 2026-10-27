package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0437Yg;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzipv extends zza {
    public static final Parcelable.Creator CREATOR = new C0437Yg();
    public int s;

    public zzipv() {
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 2, this.s);
        AbstractC1116lf.j(parcel, iN);
    }

    public zzipv(int i) {
        this.s = i;
    }
}
