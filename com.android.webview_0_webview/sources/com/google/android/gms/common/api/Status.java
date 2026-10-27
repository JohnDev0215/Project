package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC0056Dc;
import defpackage.AbstractC0893hf;
import defpackage.AbstractC1116lf;
import defpackage.C0112Ge;
import defpackage.C0837gf;
import defpackage.InterfaceC0217Mc;
import java.util.Arrays;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Status extends zza implements InterfaceC0217Mc, ReflectedParcelable {
    public final int s;
    public final int t;
    public final String u;
    public final PendingIntent v;
    public static final Status w = new Status(0);
    public static final Status x = new Status(8);
    public static final Status y = new Status(15);
    public static final Status z = new Status(16);
    public static final Parcelable.Creator CREATOR = new C0112Ge();

    public Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.s = i;
        this.t = i2;
        this.u = str;
        this.v = pendingIntent;
    }

    @Override // defpackage.InterfaceC0217Mc
    public final Status a() {
        return this;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.s == status.s && this.t == status.t && AbstractC0893hf.a(this.u, status.u) && AbstractC0893hf.a(this.v, status.v);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.s), Integer.valueOf(this.t), this.u, this.v});
    }

    public final String toString() {
        C0837gf c0837gfB = AbstractC0893hf.b(this);
        String strA = this.u;
        if (strA == null) {
            strA = AbstractC0056Dc.a(this.t);
        }
        c0837gfB.a("statusCode", strA);
        c0837gfB.a("resolution", this.v);
        return c0837gfB.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.t);
        AbstractC1116lf.e(parcel, 2, this.u, false);
        AbstractC1116lf.d(parcel, 3, this.v, i, false);
        AbstractC1116lf.l(parcel, 1000, this.s);
        AbstractC1116lf.j(parcel, iN);
    }

    public Status(int i) {
        this(1, i, null, null);
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }
}
