package i.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DynamicInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f5740a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5741b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte f5742c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5743d = new String();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5744e = new String();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f5745f = new String();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5746g = new String();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f5747h = new String();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f5748i = new String();
    public String j = new String();
    public byte k = 0;
    public byte l = 0;
    public String m = new String();
    public byte n = 0;

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f5740a = hwBlob.getInt8(j + 0);
        this.f5741b = hwBlob.getInt32(j + 4);
        this.f5742c = hwBlob.getInt8(j + 8);
        long j2 = j + 16;
        this.f5743d = hwBlob.getString(j2);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        long j3 = j + 32;
        this.f5744e = hwBlob.getString(j3);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j3 + 0, false);
        long j4 = j + 48;
        this.f5745f = hwBlob.getString(j4);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j4 + 0, false);
        long j5 = j + 64;
        this.f5746g = hwBlob.getString(j5);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j5 + 0, false);
        long j6 = j + 80;
        this.f5747h = hwBlob.getString(j6);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j6 + 0, false);
        long j7 = j + 96;
        this.f5748i = hwBlob.getString(j7);
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
        if (obj == null || obj.getClass() != c.class) {
            return false;
        }
        c cVar = (c) obj;
        return this.f5740a == cVar.f5740a && this.f5741b == cVar.f5741b && this.f5742c == cVar.f5742c && HidlSupport.deepEquals(this.f5743d, cVar.f5743d) && HidlSupport.deepEquals(this.f5744e, cVar.f5744e) && HidlSupport.deepEquals(this.f5745f, cVar.f5745f) && HidlSupport.deepEquals(this.f5746g, cVar.f5746g) && HidlSupport.deepEquals(this.f5747h, cVar.f5747h) && HidlSupport.deepEquals(this.f5748i, cVar.f5748i) && HidlSupport.deepEquals(this.j, cVar.j) && this.k == cVar.k && this.l == cVar.l && HidlSupport.deepEquals(this.m, cVar.m) && this.n == cVar.n;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f5740a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f5741b))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f5742c))), Integer.valueOf(HidlSupport.deepHashCode(this.f5743d)), Integer.valueOf(HidlSupport.deepHashCode(this.f5744e)), Integer.valueOf(HidlSupport.deepHashCode(this.f5745f)), Integer.valueOf(HidlSupport.deepHashCode(this.f5746g)), Integer.valueOf(HidlSupport.deepHashCode(this.f5747h)), Integer.valueOf(HidlSupport.deepHashCode(this.f5748i)), Integer.valueOf(HidlSupport.deepHashCode(this.j)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.k))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.l))), Integer.valueOf(HidlSupport.deepHashCode(this.m)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.n))));
    }

    public final String toString() {
        return "{.state = " + f.a(this.f5740a) + ", .nettype = " + g.a(this.f5741b) + ", .signal_strength = " + ((int) this.f5742c) + ", .external_imsi = " + this.f5743d + ", .external_iccid = " + this.f5744e + ", .external_simnumber = " + this.f5745f + ", .imei = " + this.f5746g + ", .internal_imsi = " + this.f5747h + ", .internal_iccid = " + this.f5748i + ", .internal_simnumber = " + this.j + ", .apn2_status = " + b.a(this.k) + ", .updown_status = " + h.a(this.l) + ", .mcc_mcn = " + this.m + ", .real_apn2_status = " + b.a(this.n) + "}";
    }
}
