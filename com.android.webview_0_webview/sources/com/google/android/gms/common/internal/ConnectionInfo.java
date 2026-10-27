package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C1500sf;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ConnectionInfo extends zza {
    public static final Parcelable.Creator CREATOR = new C1500sf();
    public Bundle s;
    public Feature[] t;
    public int u;

    public ConnectionInfo(Bundle bundle, Feature[] featureArr, int i) {
        this.s = bundle;
        this.t = featureArr;
        this.u = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        Bundle bundle = this.s;
        if (bundle != null) {
            int i2 = AbstractC1116lf.i(parcel, 1);
            parcel.writeBundle(bundle);
            AbstractC1116lf.j(parcel, i2);
        }
        AbstractC1116lf.g(parcel, 2, this.t, i);
        AbstractC1116lf.l(parcel, 3, this.u);
        AbstractC1116lf.j(parcel, iN);
    }
}
