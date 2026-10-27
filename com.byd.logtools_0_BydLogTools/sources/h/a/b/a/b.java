package h.a.b.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DynamicInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f4974a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4975b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte f4976c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f4977d = new String();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4978e = new String();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f4979f = new String();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f4980g = new String();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f4981h = new String();
    public String i = new String();
    public String j = new String();
    public byte k = 0;
    public byte l = 0;
    public String m = new String();
    public byte n = 0;

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f4974a = hwBlob.getInt8(j + 0);
        this.f4975b = hwBlob.getInt32(j + 4);
        this.f4976c = hwBlob.getInt8(j + 8);
        long j2 = j + 16;
        this.f4977d = hwBlob.getString(j2);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        long j3 = j + 32;
        this.f4978e = hwBlob.getString(j3);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j3 + 0, false);
        long j4 = j + 48;
        this.f4979f = hwBlob.getString(j4);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j4 + 0, false);
        long j5 = j + 64;
        this.f4980g = hwBlob.getString(j5);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j5 + 0, false);
        long j6 = j + 80;
        this.f4981h = hwBlob.getString(j6);
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
        return this.f4974a == bVar.f4974a && this.f4975b == bVar.f4975b && this.f4976c == bVar.f4976c && HidlSupport.deepEquals(this.f4977d, bVar.f4977d) && HidlSupport.deepEquals(this.f4978e, bVar.f4978e) && HidlSupport.deepEquals(this.f4979f, bVar.f4979f) && HidlSupport.deepEquals(this.f4980g, bVar.f4980g) && HidlSupport.deepEquals(this.f4981h, bVar.f4981h) && HidlSupport.deepEquals(this.i, bVar.i) && HidlSupport.deepEquals(this.j, bVar.j) && this.k == bVar.k && this.l == bVar.l && HidlSupport.deepEquals(this.m, bVar.m) && this.n == bVar.n;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f4974a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f4975b))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f4976c))), Integer.valueOf(HidlSupport.deepHashCode(this.f4977d)), Integer.valueOf(HidlSupport.deepHashCode(this.f4978e)), Integer.valueOf(HidlSupport.deepHashCode(this.f4979f)), Integer.valueOf(HidlSupport.deepHashCode(this.f4980g)), Integer.valueOf(HidlSupport.deepHashCode(this.f4981h)), Integer.valueOf(HidlSupport.deepHashCode(this.i)), Integer.valueOf(HidlSupport.deepHashCode(this.j)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.k))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.l))), Integer.valueOf(HidlSupport.deepHashCode(this.m)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.n))));
    }

    public final String toString() {
        return "{.state = " + e.a(this.f4974a) + ", .nettype = " + f.a(this.f4975b) + ", .signal_strength = " + ((int) this.f4976c) + ", .external_imsi = " + this.f4977d + ", .external_iccid = " + this.f4978e + ", .external_simnumber = " + this.f4979f + ", .imei = " + this.f4980g + ", .internal_imsi = " + this.f4981h + ", .internal_iccid = " + this.i + ", .internal_simnumber = " + this.j + ", .apn2_status = " + a.a(this.k) + ", .updown_status = " + g.a(this.l) + ", .mcc_mcn = " + this.m + ", .real_apn2_status = " + a.a(this.n) + "}";
    }
}
