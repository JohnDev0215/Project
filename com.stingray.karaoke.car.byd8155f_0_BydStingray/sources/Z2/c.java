package Z2;

import T.C0746i0;
import g2.C1289b;
import h2.AbstractC1346c;
import h2.t;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class c extends h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f10828i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f10829j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f10830k;
    public List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f10833p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f10834q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f10835r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f10836s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f10837t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public byte f10838u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public byte f10839v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f10841x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f10842y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final int[] f10826z = {11, 1, 3, 12, 14, 5, 7, 9};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final int[] f10820A = {0, 4, 8, 12, 16, 20, 24, 28};
    public static final int[] B = {-1, -16711936, -16776961, -16711681, -65536, -256, -65281};

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final int[] f10821C = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final int[] f10822D = {174, 176, 189, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final int[] f10823E = {193, 201, 211, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, 192, 194, 199, 200, 202, 203, 235, 206, 207, 239, 212, 217, 249, 219, 171, 187};

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final int[] f10824F = {195, 227, 205, 204, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final boolean[] f10825G = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final t f10827h = new t();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f10831m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public b f10832n = new b(0, 4);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f10840w = 0;
    public final long l = 16000000;

    public c(String str, int i7) {
        this.f10828i = "application/x-mp4-cea-608".equals(str) ? 2 : 3;
        if (i7 == 1) {
            this.f10830k = 0;
            this.f10829j = 0;
        } else if (i7 == 2) {
            this.f10830k = 1;
            this.f10829j = 0;
        } else if (i7 == 3) {
            this.f10830k = 0;
            this.f10829j = 1;
        } else if (i7 != 4) {
            AbstractC1346c.z("Cea608Decoder", "Invalid channel. Defaulting to CC1.");
            this.f10830k = 0;
            this.f10829j = 0;
        } else {
            this.f10830k = 1;
            this.f10829j = 1;
        }
        m(0);
        l();
        this.f10841x = true;
        this.f10842y = -9223372036854775807L;
    }

    @Override // Z2.h, m2.d
    public final void a() {
    }

    @Override // Z2.h, m2.d
    public final void flush() {
        super.flush();
        this.o = null;
        this.f10833p = null;
        m(0);
        this.f10835r = 4;
        this.f10832n.f10819h = 4;
        l();
        this.f10836s = false;
        this.f10837t = false;
        this.f10838u = (byte) 0;
        this.f10839v = (byte) 0;
        this.f10840w = 0;
        this.f10841x = true;
        this.f10842y = -9223372036854775807L;
    }

    @Override // Z2.h
    public final C0746i0 g() {
        List list = this.o;
        this.f10833p = list;
        list.getClass();
        return new C0746i0(list);
    }

    /* JADX WARN: Removed duplicated region for block: B:179:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x007e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a6 A[FALL_THROUGH] */
    @Override // Z2.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h(Z2.g r15) {
        /*
            Method dump skipped, instruction units count: 682
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Z2.c.h(Z2.g):void");
    }

    @Override // Z2.h, m2.d
    /* JADX INFO: renamed from: i */
    public final Y2.c e() {
        Y2.c cVar;
        Y2.c cVarE = super.e();
        if (cVarE != null) {
            return cVarE;
        }
        long j3 = this.l;
        if (j3 == -9223372036854775807L) {
            return null;
        }
        long j10 = this.f10842y;
        if (j10 == -9223372036854775807L || this.f10882e - j10 < j3 || (cVar = (Y2.c) this.f10881b.pollFirst()) == null) {
            return null;
        }
        this.o = Collections.emptyList();
        this.f10842y = -9223372036854775807L;
        C0746i0 c0746i0G = g();
        long j11 = this.f10882e;
        cVar.f18960n = j11;
        cVar.f10633p = c0746i0G;
        cVar.f10634q = j11;
        return cVar;
    }

    @Override // Z2.h
    public final boolean j() {
        return this.o != this.f10833p;
    }

    public final ArrayList k() {
        ArrayList arrayList = this.f10831m;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        int iMin = 2;
        for (int i7 = 0; i7 < size; i7++) {
            C1289b c1289bC = ((b) arrayList.get(i7)).c(Integer.MIN_VALUE);
            arrayList2.add(c1289bC);
            if (c1289bC != null) {
                iMin = Math.min(iMin, c1289bC.f14793i);
            }
        }
        ArrayList arrayList3 = new ArrayList(size);
        for (int i10 = 0; i10 < size; i10++) {
            C1289b c1289bC2 = (C1289b) arrayList2.get(i10);
            if (c1289bC2 != null) {
                if (c1289bC2.f14793i != iMin) {
                    c1289bC2 = ((b) arrayList.get(i10)).c(iMin);
                    c1289bC2.getClass();
                }
                arrayList3.add(c1289bC2);
            }
        }
        return arrayList3;
    }

    public final void l() {
        b bVar = this.f10832n;
        bVar.f10818g = this.f10834q;
        bVar.f10814a.clear();
        bVar.f10815b.clear();
        bVar.c.setLength(0);
        bVar.d = 15;
        bVar.f10816e = 0;
        bVar.f10817f = 0;
        ArrayList arrayList = this.f10831m;
        arrayList.clear();
        arrayList.add(this.f10832n);
    }

    public final void m(int i7) {
        int i10 = this.f10834q;
        if (i10 == i7) {
            return;
        }
        this.f10834q = i7;
        if (i7 != 3) {
            l();
            if (i10 == 3 || i7 == 1 || i7 == 0) {
                this.o = Collections.emptyList();
                return;
            }
            return;
        }
        int i11 = 0;
        while (true) {
            ArrayList arrayList = this.f10831m;
            if (i11 >= arrayList.size()) {
                return;
            }
            ((b) arrayList.get(i11)).f10818g = i7;
            i11++;
        }
    }
}
