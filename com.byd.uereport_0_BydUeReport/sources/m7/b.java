package m7;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DynamicInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f6433a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6434b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte f6435c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f6436d = new String();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f6437e = new String();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f6438f = new String();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f6439g = new String();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f6440h = new String();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f6441i = new String();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f6442j = new String();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public byte f6443k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public byte f6444l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f6445m = new String();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public byte f6446n = 0;

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j8) {
        this.f6433a = hwBlob.getInt8(j8 + 0);
        this.f6434b = hwBlob.getInt32(j8 + 4);
        this.f6435c = hwBlob.getInt8(j8 + 8);
        long j9 = j8 + 16;
        this.f6436d = hwBlob.getString(j9);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j9 + 0, false);
        long j10 = j8 + 32;
        this.f6437e = hwBlob.getString(j10);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j10 + 0, false);
        long j11 = j8 + 48;
        this.f6438f = hwBlob.getString(j11);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j11 + 0, false);
        long j12 = j8 + 64;
        this.f6439g = hwBlob.getString(j12);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j12 + 0, false);
        long j13 = j8 + 80;
        this.f6440h = hwBlob.getString(j13);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j13 + 0, false);
        long j14 = j8 + 96;
        this.f6441i = hwBlob.getString(j14);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j14 + 0, false);
        long j15 = j8 + 112;
        this.f6442j = hwBlob.getString(j15);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j15 + 0, false);
        this.f6443k = hwBlob.getInt8(j8 + 128);
        this.f6444l = hwBlob.getInt8(j8 + 129);
        long j16 = j8 + 136;
        this.f6445m = hwBlob.getString(j16);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j16 + 0, false);
        this.f6446n = hwBlob.getInt8(j8 + 152);
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
        return this.f6433a == bVar.f6433a && this.f6434b == bVar.f6434b && this.f6435c == bVar.f6435c && HidlSupport.deepEquals(this.f6436d, bVar.f6436d) && HidlSupport.deepEquals(this.f6437e, bVar.f6437e) && HidlSupport.deepEquals(this.f6438f, bVar.f6438f) && HidlSupport.deepEquals(this.f6439g, bVar.f6439g) && HidlSupport.deepEquals(this.f6440h, bVar.f6440h) && HidlSupport.deepEquals(this.f6441i, bVar.f6441i) && HidlSupport.deepEquals(this.f6442j, bVar.f6442j) && this.f6443k == bVar.f6443k && this.f6444l == bVar.f6444l && HidlSupport.deepEquals(this.f6445m, bVar.f6445m) && this.f6446n == bVar.f6446n;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f6433a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f6434b))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f6435c))), Integer.valueOf(HidlSupport.deepHashCode(this.f6436d)), Integer.valueOf(HidlSupport.deepHashCode(this.f6437e)), Integer.valueOf(HidlSupport.deepHashCode(this.f6438f)), Integer.valueOf(HidlSupport.deepHashCode(this.f6439g)), Integer.valueOf(HidlSupport.deepHashCode(this.f6440h)), Integer.valueOf(HidlSupport.deepHashCode(this.f6441i)), Integer.valueOf(HidlSupport.deepHashCode(this.f6442j)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f6443k))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f6444l))), Integer.valueOf(HidlSupport.deepHashCode(this.f6445m)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f6446n))));
    }

    public final String toString() {
        return "{.state = " + e.a(this.f6433a) + ", .nettype = " + f.a(this.f6434b) + ", .signal_strength = " + ((int) this.f6435c) + ", .external_imsi = " + this.f6436d + ", .external_iccid = " + this.f6437e + ", .external_simnumber = " + this.f6438f + ", .imei = " + this.f6439g + ", .internal_imsi = " + this.f6440h + ", .internal_iccid = " + this.f6441i + ", .internal_simnumber = " + this.f6442j + ", .apn2_status = " + a.a(this.f6443k) + ", .updown_status = " + g.a(this.f6444l) + ", .mcc_mcn = " + this.f6445m + ", .real_apn2_status = " + a.a(this.f6446n) + "}";
    }
}
