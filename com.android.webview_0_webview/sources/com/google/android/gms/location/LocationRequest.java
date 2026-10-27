package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0457Zi;
import defpackage.Z7;
import java.util.Arrays;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class LocationRequest extends zza implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new C0457Zi();
    public int s;
    public long t;
    public long u;
    public boolean v;
    public long w;
    public int x;
    public float y;
    public long z;

    public LocationRequest() {
        this.s = 102;
        this.t = 3600000L;
        this.u = 600000L;
        this.v = false;
        this.w = Long.MAX_VALUE;
        this.x = Integer.MAX_VALUE;
        this.y = 0.0f;
        this.z = 0L;
    }

    public final LocationRequest b(long j) {
        if (j >= 0) {
            this.t = j;
            if (!this.v) {
                this.u = (long) (j / 6.0d);
            }
            return this;
        }
        StringBuilder sb = new StringBuilder(38);
        sb.append("invalid interval: ");
        sb.append(j);
        throw new IllegalArgumentException(sb.toString());
    }

    public final LocationRequest e(int i) {
        if (i == 100 || i == 102 || i == 104 || i == 105) {
            this.s = i;
            return this;
        }
        StringBuilder sb = new StringBuilder(28);
        sb.append("invalid quality: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        if (this.s == locationRequest.s) {
            long j = this.t;
            if (j == locationRequest.t && this.u == locationRequest.u && this.v == locationRequest.v && this.w == locationRequest.w && this.x == locationRequest.x && this.y == locationRequest.y) {
                long j2 = this.z;
                if (j2 >= j) {
                    j = j2;
                }
                long j3 = locationRequest.z;
                long j4 = locationRequest.t;
                if (j3 < j4) {
                    j3 = j4;
                }
                if (j == j3) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.s), Long.valueOf(this.t), Float.valueOf(this.y), Long.valueOf(this.z)});
    }

    public final String toString() {
        StringBuilder sbF = Z7.f("Request[");
        int i = this.s;
        sbF.append(i != 100 ? i != 102 ? i != 104 ? i != 105 ? "???" : "PRIORITY_NO_POWER" : "PRIORITY_LOW_POWER" : "PRIORITY_BALANCED_POWER_ACCURACY" : "PRIORITY_HIGH_ACCURACY");
        if (this.s != 105) {
            sbF.append(" requested=");
            sbF.append(this.t);
            sbF.append("ms");
        }
        sbF.append(" fastest=");
        sbF.append(this.u);
        sbF.append("ms");
        if (this.z > this.t) {
            sbF.append(" maxWait=");
            sbF.append(this.z);
            sbF.append("ms");
        }
        if (this.y > 0.0f) {
            sbF.append(" smallestDisplacement=");
            sbF.append(this.y);
            sbF.append("m");
        }
        long j = this.w;
        if (j != Long.MAX_VALUE) {
            long jElapsedRealtime = j - SystemClock.elapsedRealtime();
            sbF.append(" expireIn=");
            sbF.append(jElapsedRealtime);
            sbF.append("ms");
        }
        if (this.x != Integer.MAX_VALUE) {
            sbF.append(" num=");
            sbF.append(this.x);
        }
        sbF.append(']');
        return sbF.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.b(parcel, 2, this.t);
        AbstractC1116lf.b(parcel, 3, this.u);
        AbstractC1116lf.f(parcel, 4, this.v);
        AbstractC1116lf.b(parcel, 5, this.w);
        AbstractC1116lf.l(parcel, 6, this.x);
        AbstractC1116lf.a(parcel, 7, this.y);
        AbstractC1116lf.b(parcel, 8, this.z);
        AbstractC1116lf.j(parcel, iN);
    }

    public LocationRequest(int i, long j, long j2, boolean z, long j3, int i2, float f, long j4) {
        this.s = i;
        this.t = j;
        this.u = j2;
        this.v = z;
        this.w = j3;
        this.x = i2;
        this.y = f;
        this.z = j4;
    }
}
