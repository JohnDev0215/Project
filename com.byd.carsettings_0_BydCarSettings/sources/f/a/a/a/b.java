package f.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DynamicInfo.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f3949a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3950b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte f3951c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3952d = new String();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3953e = new String();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3954f = new String();
    public String g = new String();
    public String h = new String();
    public String i = new String();
    public String j = new String();
    public byte k = 0;
    public byte l = 0;
    public String m = new String();
    public byte n = 0;

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f3949a = hwBlob.getInt8(j + 0);
        this.f3950b = hwBlob.getInt32(j + 4);
        this.f3951c = hwBlob.getInt8(j + 8);
        long j2 = j + 16;
        this.f3952d = hwBlob.getString(j2);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        long j3 = j + 32;
        this.f3953e = hwBlob.getString(j3);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j3 + 0, false);
        long j4 = j + 48;
        this.f3954f = hwBlob.getString(j4);
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
        if (obj == null || obj.getClass() != b.class) {
            return false;
        }
        b bVar = (b) obj;
        return this.f3949a == bVar.f3949a && this.f3950b == bVar.f3950b && this.f3951c == bVar.f3951c && HidlSupport.deepEquals(this.f3952d, bVar.f3952d) && HidlSupport.deepEquals(this.f3953e, bVar.f3953e) && HidlSupport.deepEquals(this.f3954f, bVar.f3954f) && HidlSupport.deepEquals(this.g, bVar.g) && HidlSupport.deepEquals(this.h, bVar.h) && HidlSupport.deepEquals(this.i, bVar.i) && HidlSupport.deepEquals(this.j, bVar.j) && this.k == bVar.k && this.l == bVar.l && HidlSupport.deepEquals(this.m, bVar.m) && this.n == bVar.n;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f3949a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f3950b))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f3951c))), Integer.valueOf(HidlSupport.deepHashCode(this.f3952d)), Integer.valueOf(HidlSupport.deepHashCode(this.f3953e)), Integer.valueOf(HidlSupport.deepHashCode(this.f3954f)), Integer.valueOf(HidlSupport.deepHashCode(this.g)), Integer.valueOf(HidlSupport.deepHashCode(this.h)), Integer.valueOf(HidlSupport.deepHashCode(this.i)), Integer.valueOf(HidlSupport.deepHashCode(this.j)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.k))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.l))), Integer.valueOf(HidlSupport.deepHashCode(this.m)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.n))));
    }

    public final String toString() {
        return "{.state = " + e.a(this.f3949a) + ", .nettype = " + f.a(this.f3950b) + ", .signal_strength = " + ((int) this.f3951c) + ", .external_imsi = " + this.f3952d + ", .external_iccid = " + this.f3953e + ", .external_simnumber = " + this.f3954f + ", .imei = " + this.g + ", .internal_imsi = " + this.h + ", .internal_iccid = " + this.i + ", .internal_simnumber = " + this.j + ", .apn2_status = " + a.a(this.k) + ", .updown_status = " + g.a(this.l) + ", .mcc_mcn = " + this.m + ", .real_apn2_status = " + a.a(this.n) + "}";
    }
}
