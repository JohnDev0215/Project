package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.location.LocationRequest;
import defpackage.AbstractC0893hf;
import defpackage.AbstractC1116lf;
import defpackage.C0206Li;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbh extends zza {
    public static final List B = Collections.emptyList();
    public static final Parcelable.Creator CREATOR = new C0206Li();
    public boolean A;
    public LocationRequest s;
    public List t;
    public String u;
    public boolean v;
    public boolean w;
    public boolean x;
    public String y;
    public boolean z;

    public zzbh(LocationRequest locationRequest, List list, String str, boolean z, boolean z2, boolean z3, String str2, boolean z4, boolean z5) {
        this.s = locationRequest;
        this.t = list;
        this.u = str;
        this.v = z;
        this.w = z2;
        this.x = z3;
        this.y = str2;
        this.z = z4;
        this.A = z5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzbh)) {
            return false;
        }
        zzbh zzbhVar = (zzbh) obj;
        return AbstractC0893hf.a(this.s, zzbhVar.s) && AbstractC0893hf.a(this.t, zzbhVar.t) && AbstractC0893hf.a(this.u, zzbhVar.u) && this.v == zzbhVar.v && this.w == zzbhVar.w && this.x == zzbhVar.x && AbstractC0893hf.a(this.y, zzbhVar.y) && this.z == zzbhVar.z && this.A == zzbhVar.A;
    }

    public final int hashCode() {
        return this.s.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.s);
        if (this.u != null) {
            sb.append(" tag=");
            sb.append(this.u);
        }
        if (this.y != null) {
            sb.append(" moduleId=");
            sb.append(this.y);
        }
        sb.append(" hideAppOps=");
        sb.append(this.v);
        sb.append(" clients=");
        sb.append(this.t);
        sb.append(" forceCoarseLocation=");
        sb.append(this.w);
        if (this.x) {
            sb.append(" exemptFromBackgroundThrottle");
        }
        if (this.z) {
            sb.append(" locationSettingsIgnored");
        }
        if (this.A) {
            sb.append(" inaccurateLocationsDelayed");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.d(parcel, 1, this.s, i, false);
        AbstractC1116lf.m(parcel, 5, this.t, false);
        AbstractC1116lf.e(parcel, 6, this.u, false);
        AbstractC1116lf.f(parcel, 7, this.v);
        AbstractC1116lf.f(parcel, 8, this.w);
        AbstractC1116lf.f(parcel, 9, this.x);
        AbstractC1116lf.e(parcel, 10, this.y, false);
        AbstractC1116lf.f(parcel, 11, this.z);
        AbstractC1116lf.f(parcel, 12, this.A);
        AbstractC1116lf.j(parcel, iN);
    }
}
