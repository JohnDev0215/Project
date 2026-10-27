package i.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DynamicInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f5527a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5528b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte f5529c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5530d = new String();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5531e = new String();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f5532f = new String();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5533g = new String();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f5534h = new String();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f5535i = new String();
    public String j = new String();
    public byte k = 0;
    public byte l = 0;
    public String m = new String();
    public byte n = 0;

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f5527a = hwBlob.getInt8(j + 0);
        this.f5528b = hwBlob.getInt32(j + 4);
        this.f5529c = hwBlob.getInt8(j + 8);
        long j2 = j + 16;
        this.f5530d = hwBlob.getString(j2);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        long j3 = j + 32;
        this.f5531e = hwBlob.getString(j3);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j3 + 0, false);
        long j4 = j + 48;
        this.f5532f = hwBlob.getString(j4);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j4 + 0, false);
        long j5 = j + 64;
        this.f5533g = hwBlob.getString(j5);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j5 + 0, false);
        long j6 = j + 80;
        this.f5534h = hwBlob.getString(j6);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j6 + 0, false);
        long j7 = j + 96;
        this.f5535i = hwBlob.getString(j7);
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
        return this.f5527a == bVar.f5527a && this.f5528b == bVar.f5528b && this.f5529c == bVar.f5529c && HidlSupport.deepEquals(this.f5530d, bVar.f5530d) && HidlSupport.deepEquals(this.f5531e, bVar.f5531e) && HidlSupport.deepEquals(this.f5532f, bVar.f5532f) && HidlSupport.deepEquals(this.f5533g, bVar.f5533g) && HidlSupport.deepEquals(this.f5534h, bVar.f5534h) && HidlSupport.deepEquals(this.f5535i, bVar.f5535i) && HidlSupport.deepEquals(this.j, bVar.j) && this.k == bVar.k && this.l == bVar.l && HidlSupport.deepEquals(this.m, bVar.m) && this.n == bVar.n;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f5527a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f5528b))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f5529c))), Integer.valueOf(HidlSupport.deepHashCode(this.f5530d)), Integer.valueOf(HidlSupport.deepHashCode(this.f5531e)), Integer.valueOf(HidlSupport.deepHashCode(this.f5532f)), Integer.valueOf(HidlSupport.deepHashCode(this.f5533g)), Integer.valueOf(HidlSupport.deepHashCode(this.f5534h)), Integer.valueOf(HidlSupport.deepHashCode(this.f5535i)), Integer.valueOf(HidlSupport.deepHashCode(this.j)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.k))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.l))), Integer.valueOf(HidlSupport.deepHashCode(this.m)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.n))));
    }

    public final String toString() {
        return "{.state = " + e.a(this.f5527a) + ", .nettype = " + f.a(this.f5528b) + ", .signal_strength = " + ((int) this.f5529c) + ", .external_imsi = " + this.f5530d + ", .external_iccid = " + this.f5531e + ", .external_simnumber = " + this.f5532f + ", .imei = " + this.f5533g + ", .internal_imsi = " + this.f5534h + ", .internal_iccid = " + this.f5535i + ", .internal_simnumber = " + this.j + ", .apn2_status = " + a.a(this.k) + ", .updown_status = " + g.a(this.l) + ", .mcc_mcn = " + this.m + ", .real_apn2_status = " + a.a(this.n) + "}";
    }
}
