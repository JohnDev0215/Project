package defpackage;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DynamicInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class tm {
    public byte a = 0;
    public int b = 0;
    public byte c = 0;
    public String d = new String();
    public String e = new String();
    public String f = new String();
    public String g = new String();
    public String h = new String();
    public String i = new String();
    public String j = new String();
    public byte k = 0;
    public byte l = 0;
    public String m = new String();
    public byte n = 0;

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.a = hwBlob.getInt8(j + 0);
        this.b = hwBlob.getInt32(j + 4);
        this.c = hwBlob.getInt8(j + 8);
        long j2 = j + 16;
        this.d = hwBlob.getString(j2);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        long j3 = j + 32;
        this.e = hwBlob.getString(j3);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j3 + 0, false);
        long j4 = j + 48;
        this.f = hwBlob.getString(j4);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j4 + 0, false);
        long j5 = j + 64;
        this.g = hwBlob.getString(j5);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j5 + 0, false);
        long j6 = j + 80;
        this.h = hwBlob.getString(j6);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j6 + 0, false);
        long j7 = j + 96;
        this.i = hwBlob.getString(j7);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j7 + 0, false);
        long j8 = j + 112;
        this.j = hwBlob.getString(j8);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j8 + 0, false);
        this.k = hwBlob.getInt8(j + 128);
        this.l = hwBlob.getInt8(j + 129);
        long j9 = j + 136;
        this.m = hwBlob.getString(j9);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j9 + 0, false);
        this.n = hwBlob.getInt8(j + 152);
    }

    public final void b(HwParcel hwParcel) {
        a(hwParcel, hwParcel.readBuffer(160L), 0L);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != tm.class) {
            return false;
        }
        tm tmVar = (tm) obj;
        return this.a == tmVar.a && this.b == tmVar.b && this.c == tmVar.c && HidlSupport.deepEquals(this.d, tmVar.d) && HidlSupport.deepEquals(this.e, tmVar.e) && HidlSupport.deepEquals(this.f, tmVar.f) && HidlSupport.deepEquals(this.g, tmVar.g) && HidlSupport.deepEquals(this.h, tmVar.h) && HidlSupport.deepEquals(this.i, tmVar.i) && HidlSupport.deepEquals(this.j, tmVar.j) && this.k == tmVar.k && this.l == tmVar.l && HidlSupport.deepEquals(this.m, tmVar.m) && this.n == tmVar.n;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.b))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.c))), Integer.valueOf(HidlSupport.deepHashCode(this.d)), Integer.valueOf(HidlSupport.deepHashCode(this.e)), Integer.valueOf(HidlSupport.deepHashCode(this.f)), Integer.valueOf(HidlSupport.deepHashCode(this.g)), Integer.valueOf(HidlSupport.deepHashCode(this.h)), Integer.valueOf(HidlSupport.deepHashCode(this.i)), Integer.valueOf(HidlSupport.deepHashCode(this.j)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.k))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.l))), Integer.valueOf(HidlSupport.deepHashCode(this.m)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.n))));
    }

    public final String toString() {
        return "{.state = " + wm.a(this.a) + ", .nettype = " + xm.a(this.b) + ", .signal_strength = " + ((int) this.c) + ", .external_imsi = " + this.d + ", .external_iccid = " + this.e + ", .external_simnumber = " + this.f + ", .imei = " + this.g + ", .internal_imsi = " + this.h + ", .internal_iccid = " + this.i + ", .internal_simnumber = " + this.j + ", .apn2_status = " + sm.a(this.k) + ", .updown_status = " + ym.a(this.l) + ", .mcc_mcn = " + this.m + ", .real_apn2_status = " + sm.a(this.n) + "}";
    }
}
