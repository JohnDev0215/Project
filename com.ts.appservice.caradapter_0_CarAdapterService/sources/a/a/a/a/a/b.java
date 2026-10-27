package a.a.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: VehiclePropValue.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f1a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2b = 0;
    public int c = 0;
    public int d = 0;
    public a e = new a();

    /* JADX INFO: compiled from: VehiclePropValue.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ArrayList<Integer> f3a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<Float> f4b = new ArrayList<>();
        public ArrayList<Long> c = new ArrayList<>();
        public ArrayList<Byte> d = new ArrayList<>();
        public String e = new String();

        public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
            long j2 = j + 0;
            int int32 = hwBlob.getInt32(j2 + 8);
            HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 4, hwBlob.handle(), j2 + 0, true);
            this.f3a.clear();
            for (int i = 0; i < int32; i++) {
                this.f3a.add(Integer.valueOf(embeddedBuffer.getInt32(i * 4)));
            }
            long j3 = j + 16;
            int int322 = hwBlob.getInt32(j3 + 8);
            HwBlob embeddedBuffer2 = hwParcel.readEmbeddedBuffer(int322 * 4, hwBlob.handle(), j3 + 0, true);
            this.f4b.clear();
            for (int i2 = 0; i2 < int322; i2++) {
                this.f4b.add(Float.valueOf(embeddedBuffer2.getFloat(i2 * 4)));
            }
            long j4 = j + 32;
            int int323 = hwBlob.getInt32(j4 + 8);
            HwBlob embeddedBuffer3 = hwParcel.readEmbeddedBuffer(int323 * 8, hwBlob.handle(), j4 + 0, true);
            this.c.clear();
            for (int i3 = 0; i3 < int323; i3++) {
                this.c.add(Long.valueOf(embeddedBuffer3.getInt64(i3 * 8)));
            }
            long j5 = j + 48;
            int int324 = hwBlob.getInt32(8 + j5);
            HwBlob embeddedBuffer4 = hwParcel.readEmbeddedBuffer(int324 * 1, hwBlob.handle(), j5 + 0, true);
            this.d.clear();
            for (int i4 = 0; i4 < int324; i4++) {
                this.d.add(Byte.valueOf(embeddedBuffer4.getInt8(i4 * 1)));
            }
            long j6 = j + 64;
            this.e = hwBlob.getString(j6);
            hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j6 + 0, false);
        }

        public final void b(HwBlob hwBlob, long j) {
            int size = this.f3a.size();
            long j2 = j + 0;
            hwBlob.putInt32(j2 + 8, size);
            hwBlob.putBool(j2 + 12, false);
            HwBlob hwBlob2 = new HwBlob(size * 4);
            for (int i = 0; i < size; i++) {
                hwBlob2.putInt32(i * 4, this.f3a.get(i).intValue());
            }
            hwBlob.putBlob(j2 + 0, hwBlob2);
            int size2 = this.f4b.size();
            long j3 = j + 16;
            hwBlob.putInt32(j3 + 8, size2);
            hwBlob.putBool(j3 + 12, false);
            HwBlob hwBlob3 = new HwBlob(size2 * 4);
            for (int i2 = 0; i2 < size2; i2++) {
                hwBlob3.putFloat(i2 * 4, this.f4b.get(i2).floatValue());
            }
            hwBlob.putBlob(j3 + 0, hwBlob3);
            int size3 = this.c.size();
            long j4 = j + 32;
            hwBlob.putInt32(j4 + 8, size3);
            hwBlob.putBool(j4 + 12, false);
            HwBlob hwBlob4 = new HwBlob(size3 * 8);
            for (int i3 = 0; i3 < size3; i3++) {
                hwBlob4.putInt64(i3 * 8, this.c.get(i3).longValue());
            }
            hwBlob.putBlob(j4 + 0, hwBlob4);
            int size4 = this.d.size();
            long j5 = j + 48;
            hwBlob.putInt32(8 + j5, size4);
            hwBlob.putBool(j5 + 12, false);
            HwBlob hwBlob5 = new HwBlob(size4 * 1);
            for (int i4 = 0; i4 < size4; i4++) {
                hwBlob5.putInt8(i4 * 1, this.d.get(i4).byteValue());
            }
            hwBlob.putBlob(j5 + 0, hwBlob5);
            hwBlob.putString(j + 64, this.e);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != a.class) {
                return false;
            }
            a aVar = (a) obj;
            return HidlSupport.deepEquals(this.f3a, aVar.f3a) && HidlSupport.deepEquals(this.f4b, aVar.f4b) && HidlSupport.deepEquals(this.c, aVar.c) && HidlSupport.deepEquals(this.d, aVar.d) && HidlSupport.deepEquals(this.e, aVar.e);
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.f3a)), Integer.valueOf(HidlSupport.deepHashCode(this.f4b)), Integer.valueOf(HidlSupport.deepHashCode(this.c)), Integer.valueOf(HidlSupport.deepHashCode(this.d)), Integer.valueOf(HidlSupport.deepHashCode(this.e)));
        }

        public final String toString() {
            return "{.int32Values = " + this.f3a + ", .floatValues = " + this.f4b + ", .int64Values = " + this.c + ", .bytes = " + this.d + ", .stringValue = " + this.e + "}";
        }
    }

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f1a = hwBlob.getInt64(0 + j);
        this.f2b = hwBlob.getInt32(8 + j);
        this.c = hwBlob.getInt32(12 + j);
        this.d = hwBlob.getInt32(16 + j);
        this.e.a(hwParcel, hwBlob, j + 24);
    }

    public final void b(HwParcel hwParcel) {
        a(hwParcel, hwParcel.readBuffer(104L), 0L);
    }

    public final void c(HwBlob hwBlob, long j) {
        hwBlob.putInt64(0 + j, this.f1a);
        hwBlob.putInt32(8 + j, this.f2b);
        hwBlob.putInt32(12 + j, this.c);
        hwBlob.putInt32(16 + j, this.d);
        this.e.b(hwBlob, j + 24);
    }

    public final void d(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(104);
        c(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != b.class) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1a == bVar.f1a && this.f2b == bVar.f2b && this.c == bVar.c && this.d == bVar.d && HidlSupport.deepEquals(this.e, bVar.e);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.f1a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f2b))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.c))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.d))), Integer.valueOf(HidlSupport.deepHashCode(this.e)));
    }

    public final String toString() {
        return "{.timestamp = " + this.f1a + ", .areaId = " + this.f2b + ", .prop = " + this.c + ", .status = " + c.a(this.d) + ", .value = " + this.e + "}";
    }
}
