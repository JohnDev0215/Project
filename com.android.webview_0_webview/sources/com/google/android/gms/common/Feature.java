package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC0893hf;
import defpackage.AbstractC1116lf;
import defpackage.C0328Sf;
import defpackage.C0837gf;
import java.util.Arrays;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Feature extends zza {
    public static final Parcelable.Creator CREATOR = new C0328Sf();
    public final String s;

    @Deprecated
    public final int t;
    public final long u;

    public Feature(String str, long j) {
        this.s = str;
        this.u = j;
        this.t = -1;
    }

    public long b() {
        long j = this.u;
        return j == -1 ? this.t : j;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            String str = this.s;
            if (((str != null && str.equals(feature.s)) || (this.s == null && feature.s == null)) && b() == feature.b()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.s, Long.valueOf(b())});
    }

    public String toString() {
        C0837gf c0837gfB = AbstractC0893hf.b(this);
        c0837gfB.a("name", this.s);
        c0837gfB.a("version", Long.valueOf(b()));
        return c0837gfB.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.e(parcel, 1, this.s, false);
        AbstractC1116lf.l(parcel, 2, this.t);
        AbstractC1116lf.b(parcel, 3, b());
        AbstractC1116lf.j(parcel, iN);
    }

    public Feature(String str, int i, long j) {
        this.s = str;
        this.t = i;
        this.u = j;
    }
}
