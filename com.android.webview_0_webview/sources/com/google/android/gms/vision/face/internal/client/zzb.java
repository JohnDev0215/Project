package com.google.android.gms.vision.face.internal.client;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0619ck;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzb extends zza {
    public static final Parcelable.Creator CREATOR = new C0619ck();
    public final PointF[] s;
    public final int t;

    public zzb(PointF[] pointFArr, int i) {
        this.s = pointFArr;
        this.t = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.g(parcel, 2, this.s, i);
        AbstractC1116lf.l(parcel, 3, this.t);
        AbstractC1116lf.j(parcel, iN);
    }
}
