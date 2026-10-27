package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import p.a23;
import p.g13;
import p.l03;
import p.l13;

/* JADX INFO: loaded from: classes.dex */
public final class zzbn {
    public final String a;
    public final int b;
    public final int c;
    public final long d;
    public final long e;
    public final int f;
    public final int g;
    public final String h;
    public final String i;

    public zzbn(String str, int i, int i2, long j, long j2, int i3, int i4, String str2, String str3) {
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = j;
        this.e = j2;
        this.f = i3;
        this.g = i4;
        if (str2 == null) {
            throw new NullPointerException("Null availableVersionTag");
        }
        this.h = str2;
        if (str3 == null) {
            throw new NullPointerException("Null installedVersionTag");
        }
        this.i = str3;
    }

    public static zzbn a(Bundle bundle, String str, g13 g13Var, a23 a23Var, l03 l03Var) {
        double dDoubleValue;
        int i;
        int i2;
        int iE = l03Var.e(bundle.getInt(l13.u("status", str)));
        int i3 = bundle.getInt(l13.u("error_code", str));
        long j = bundle.getLong(l13.u("bytes_downloaded", str));
        long j2 = bundle.getLong(l13.u("total_bytes_to_download", str));
        synchronized (g13Var) {
            Double d = (Double) g13Var.a.get(str);
            dDoubleValue = d == null ? 0.0d : d.doubleValue();
        }
        long j3 = bundle.getLong(l13.u("pack_version", str));
        long j4 = bundle.getLong(l13.u("pack_base_version", str));
        int i4 = 1;
        if (iE == 4) {
            if (j4 != 0 && j4 != j3) {
                i4 = 2;
            }
            i = i4;
            i2 = 4;
        } else {
            i = 1;
            i2 = iE;
        }
        return new zzbn(str, i2, i3, j, j2, (int) Math.rint(dDoubleValue * 100.0d), i, bundle.getString(l13.u("pack_version_tag", str), String.valueOf(bundle.getInt("app_version_code"))), a23Var.a(str));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbn) {
            zzbn zzbnVar = (zzbn) obj;
            if (this.a.equals(zzbnVar.a) && this.b == zzbnVar.b && this.c == zzbnVar.c && this.d == zzbnVar.d && this.e == zzbnVar.e && this.f == zzbnVar.f && this.g == zzbnVar.g && this.h.equals(zzbnVar.h) && this.i.equals(zzbnVar.i)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c) * 1000003;
        long j = this.d;
        int i = (iHashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.e;
        return ((((((((i ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.f) * 1000003) ^ this.g) * 1000003) ^ this.h.hashCode()) * 1000003) ^ this.i.hashCode();
    }

    public final String toString() {
        String str = this.a;
        int length = str.length() + 261;
        String str2 = this.h;
        int length2 = str2.length() + length;
        String str3 = this.i;
        StringBuilder sb = new StringBuilder(str3.length() + length2);
        sb.append("AssetPackState{name=");
        sb.append(str);
        sb.append(", status=");
        sb.append(this.b);
        sb.append(", errorCode=");
        sb.append(this.c);
        sb.append(", bytesDownloaded=");
        sb.append(this.d);
        sb.append(", totalBytesToDownload=");
        sb.append(this.e);
        sb.append(", transferProgressPercentage=");
        sb.append(this.f);
        sb.append(", updateAvailability=");
        sb.append(this.g);
        sb.append(", availableVersionTag=");
        sb.append(str2);
        sb.append(", installedVersionTag=");
        sb.append(str3);
        sb.append("}");
        return sb.toString();
    }
}
