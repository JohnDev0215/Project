package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C1064kj;
import defpackage.InterfaceC0217Mc;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzg extends zza implements InterfaceC0217Mc {
    public static final Parcelable.Creator CREATOR = new C1064kj();
    public final List s;
    public final String t;

    public zzg(List list, String str) {
        this.s = list;
        this.t = str;
    }

    @Override // defpackage.InterfaceC0217Mc
    public final Status a() {
        return this.t != null ? Status.w : Status.z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        List<String> list = this.s;
        if (list != null) {
            int i2 = AbstractC1116lf.i(parcel, 1);
            parcel.writeStringList(list);
            AbstractC1116lf.j(parcel, i2);
        }
        AbstractC1116lf.e(parcel, 2, this.t, false);
        AbstractC1116lf.j(parcel, iN);
    }
}
