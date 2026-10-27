package T2;

import B2.C0073m;
import B2.L;
import B2.r;
import B2.s;
import B2.t;
import N1.C0420a;
import Y2.j;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.SparseArray;
import e2.M;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h2.p;
import i2.n;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class d implements r {

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public static final byte[] f8584e0 = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public static final byte[] f8585f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public static final byte[] f8586g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final byte[] f8587h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static final UUID f8588i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public static final Map f8589j0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f8590A;
    public long B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f8591C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public long f8592D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public p f8593E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public p f8594F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f8595G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f8596H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f8597I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public long f8598J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public long f8599K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f8600L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f8601M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int[] f8602N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public int f8603O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f8604P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public int f8605Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public int f8606R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f8607S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public long f8608T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public int f8609U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public int f8610V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public int f8611W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public boolean f8612X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public boolean f8613Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public boolean f8614Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f8615a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f8616a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f8617b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public byte f8618b0;
    public final SparseArray c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean f8619c0;
    public final boolean d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public t f8620d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f8621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j f8622f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h2.t f8623g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final h2.t f8624h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h2.t f8625i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final h2.t f8626j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final h2.t f8627k;
    public final h2.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final h2.t f8628m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final h2.t f8629n;
    public final h2.t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final h2.t f8630p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ByteBuffer f8631q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f8632r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f8633s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f8634t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f8635u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f8636v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public c f8637w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f8638x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f8639y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public long f8640z;

    static {
        int i7 = AbstractC1343A.f15195a;
        f8585f0 = "Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text".getBytes(StandardCharsets.UTF_8);
        f8586g0 = new byte[]{68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
        f8587h0 = new byte[]{87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
        f8588i0 = new UUID(72057594037932032L, -9223371306706625679L);
        HashMap map = new HashMap();
        AbstractC1347d.m(0, map, "htc_video_rotA-000", 90, "htc_video_rotA-090");
        AbstractC1347d.m(180, map, "htc_video_rotA-180", 270, "htc_video_rotA-270");
        f8589j0 = Collections.unmodifiableMap(map);
    }

    public d(j jVar, int i7) {
        b bVar = new b();
        this.f8633s = -1L;
        this.f8634t = -9223372036854775807L;
        this.f8635u = -9223372036854775807L;
        this.f8636v = -9223372036854775807L;
        this.B = -1L;
        this.f8591C = -1L;
        this.f8592D = -9223372036854775807L;
        this.f8615a = bVar;
        bVar.d = new C0420a(this);
        this.f8622f = jVar;
        this.d = (i7 & 1) == 0;
        this.f8621e = (i7 & 2) == 0;
        this.f8617b = new e();
        this.c = new SparseArray();
        this.f8625i = new h2.t(4);
        this.f8626j = new h2.t(ByteBuffer.allocate(4).putInt(-1).array());
        this.f8627k = new h2.t(4);
        this.f8623g = new h2.t(n.f15713a);
        this.f8624h = new h2.t(4);
        this.l = new h2.t();
        this.f8628m = new h2.t();
        this.f8629n = new h2.t(8);
        this.o = new h2.t();
        this.f8630p = new h2.t();
        this.f8602N = new int[1];
    }

    public static byte[] j(long j3, long j10, String str) {
        AbstractC1346c.c(j3 != -9223372036854775807L);
        int i7 = (int) (j3 / 3600000000L);
        long j11 = j3 - (((long) i7) * 3600000000L);
        int i10 = (int) (j11 / 60000000);
        long j12 = j11 - (((long) i10) * 60000000);
        int i11 = (int) (j12 / 1000000);
        String str2 = String.format(Locale.US, str, Integer.valueOf(i7), Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf((int) ((j12 - (((long) i11) * 1000000)) / j10)));
        int i12 = AbstractC1343A.f15195a;
        return str2.getBytes(StandardCharsets.UTF_8);
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(t tVar) {
        if (this.f8621e) {
            tVar = new P(tVar, this.f8622f);
        }
        this.f8620d0 = tVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:243:0x04cc A[PHI: r33
  0x04cc: PHI (r33v37 java.lang.String) = 
  (r33v3 java.lang.String)
  (r33v4 java.lang.String)
  (r33v5 java.lang.String)
  (r33v6 java.lang.String)
  (r33v7 java.lang.String)
  (r33v8 java.lang.String)
  (r33v9 java.lang.String)
  (r33v10 java.lang.String)
  (r33v11 java.lang.String)
  (r33v12 java.lang.String)
  (r33v13 java.lang.String)
  (r33v14 java.lang.String)
  (r33v15 java.lang.String)
  (r33v16 java.lang.String)
  (r33v17 java.lang.String)
  (r33v18 java.lang.String)
  (r33v19 java.lang.String)
  (r33v20 java.lang.String)
  (r33v21 java.lang.String)
  (r33v22 java.lang.String)
  (r33v23 java.lang.String)
  (r33v24 java.lang.String)
  (r33v25 java.lang.String)
  (r33v26 java.lang.String)
  (r33v27 java.lang.String)
  (r33v28 java.lang.String)
  (r33v29 java.lang.String)
  (r33v30 java.lang.String)
  (r33v31 java.lang.String)
  (r33v32 java.lang.String)
  (r33v33 java.lang.String)
  (r33v34 java.lang.String)
  (r33v38 java.lang.String)
 binds: [B:373:0x06b3, B:369:0x06a8, B:365:0x069d, B:361:0x0692, B:357:0x0687, B:353:0x067c, B:349:0x0671, B:345:0x0664, B:341:0x0654, B:337:0x0644, B:333:0x0634, B:329:0x0624, B:325:0x0614, B:321:0x0604, B:317:0x05f4, B:313:0x05e4, B:309:0x05d4, B:305:0x05c4, B:301:0x05b4, B:297:0x05a4, B:293:0x0594, B:289:0x0584, B:285:0x0574, B:281:0x0564, B:277:0x0554, B:273:0x0544, B:269:0x0534, B:265:0x0524, B:261:0x0514, B:257:0x0504, B:253:0x04f4, B:249:0x04e4, B:242:0x04ca] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0aea  */
    /* JADX WARN: Removed duplicated region for block: B:541:0x0b01  */
    /* JADX WARN: Removed duplicated region for block: B:542:0x0b03  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0b14  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x0b25  */
    /* JADX WARN: Removed duplicated region for block: B:637:0x0d0a  */
    /* JADX WARN: Type inference failed for: r0v102, types: [T2.c, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v109 */
    /* JADX WARN: Type inference failed for: r0v110, types: [java.lang.RuntimeException] */
    /* JADX WARN: Type inference failed for: r2v19, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r8v13, types: [B2.m] */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r45, B2.v r46) throws e2.M, java.io.EOFException, java.io.InterruptedIOException {
        /*
            Method dump skipped, instruction units count: 5276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T2.d.c(B2.s, B2.v):int");
    }

    public final void d(int i7) {
        if (this.f8593E == null || this.f8594F == null) {
            throw M.a(null, "Element " + i7 + " must be in a Cues");
        }
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        this.f8592D = -9223372036854775807L;
        this.f8597I = 0;
        b bVar = this.f8615a;
        bVar.f8534e = 0;
        bVar.f8533b.clear();
        e eVar = bVar.c;
        eVar.f8642b = 0;
        eVar.c = 0;
        e eVar2 = this.f8617b;
        eVar2.f8642b = 0;
        eVar2.c = 0;
        l();
        int i7 = 0;
        while (true) {
            SparseArray sparseArray = this.c;
            if (i7 >= sparseArray.size()) {
                return;
            }
            B2.M m10 = ((c) sparseArray.valueAt(i7)).f8556U;
            if (m10 != null) {
                m10.f520b = false;
                m10.c = 0;
            }
            i7++;
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) throws EOFException, InterruptedIOException {
        C1.j jVar = new C1.j(5, false);
        C0073m c0073m = (C0073m) sVar;
        long j3 = c0073m.f588n;
        long j10 = 1024;
        if (j3 != -1 && j3 <= 1024) {
            j10 = j3;
        }
        int i7 = (int) j10;
        h2.t tVar = (h2.t) jVar.f944m;
        c0073m.k(tVar.f15241a, 0, 4, false);
        jVar.l = 4;
        for (long jX = tVar.x(); jX != 440786851; jX = ((jX << 8) & (-256)) | ((long) (tVar.f15241a[0] & 255))) {
            int i10 = jVar.l + 1;
            jVar.l = i10;
            if (i10 == i7) {
                return false;
            }
            c0073m.k(tVar.f15241a, 0, 1, false);
        }
        long jO = jVar.o(c0073m);
        long j11 = jVar.l;
        if (jO == Long.MIN_VALUE) {
            return false;
        }
        if (j3 != -1 && j11 + jO >= j3) {
            return false;
        }
        while (true) {
            long j12 = jVar.l;
            long j13 = j11 + jO;
            if (j12 >= j13) {
                return j12 == j13;
            }
            if (jVar.o(c0073m) == Long.MIN_VALUE) {
                return false;
            }
            long jO2 = jVar.o(c0073m);
            if (jO2 < 0 || jO2 > 2147483647L) {
                return false;
            }
            if (jO2 != 0) {
                int i11 = (int) jO2;
                c0073m.e(i11, false);
                jVar.l += i11;
            }
        }
    }

    public final void h(int i7) {
        if (this.f8637w != null) {
            return;
        }
        throw M.a(null, "Element " + i7 + " must be in a TrackEntry");
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(T2.c r18, long r19, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T2.d.i(T2.c, long, int, int, int):void");
    }

    public final void k(C0073m c0073m, int i7) {
        h2.t tVar = this.f8625i;
        if (tVar.c >= i7) {
            return;
        }
        byte[] bArr = tVar.f15241a;
        if (bArr.length < i7) {
            tVar.b(Math.max(bArr.length * 2, i7));
        }
        byte[] bArr2 = tVar.f15241a;
        int i10 = tVar.c;
        c0073m.c(bArr2, i10, i7 - i10, false);
        tVar.G(i7);
    }

    public final void l() {
        this.f8609U = 0;
        this.f8610V = 0;
        this.f8611W = 0;
        this.f8612X = false;
        this.f8613Y = false;
        this.f8614Z = false;
        this.f8616a0 = 0;
        this.f8618b0 = (byte) 0;
        this.f8619c0 = false;
        this.l.E(0);
    }

    public final long m(long j3) throws M {
        long j10 = this.f8634t;
        if (j10 == -9223372036854775807L) {
            throw M.a(null, "Can't scale timecode prior to timecodeScale being set.");
        }
        int i7 = AbstractC1343A.f15195a;
        return AbstractC1343A.N(j3, j10, 1000L, RoundingMode.DOWN);
    }

    public final int n(C0073m c0073m, c cVar, int i7, boolean z6) throws M, EOFException, InterruptedIOException {
        int iC;
        int iC2;
        int i10;
        if ("S_TEXT/UTF8".equals(cVar.f8563b)) {
            o(c0073m, f8584e0, i7);
            int i11 = this.f8610V;
            l();
            return i11;
        }
        if ("S_TEXT/ASS".equals(cVar.f8563b)) {
            o(c0073m, f8586g0, i7);
            int i12 = this.f8610V;
            l();
            return i12;
        }
        if ("S_TEXT/WEBVTT".equals(cVar.f8563b)) {
            o(c0073m, f8587h0, i7);
            int i13 = this.f8610V;
            l();
            return i13;
        }
        L l = cVar.f8560Y;
        boolean z10 = this.f8612X;
        h2.t tVar = this.l;
        if (!z10) {
            boolean z11 = cVar.f8567h;
            h2.t tVar2 = this.f8625i;
            if (z11) {
                this.f8605Q &= -1073741825;
                if (!this.f8613Y) {
                    c0073m.c(tVar2.f15241a, 0, 1, false);
                    this.f8609U++;
                    byte b10 = tVar2.f15241a[0];
                    if ((b10 & 128) == 128) {
                        throw M.a(null, "Extension bit is set in signal byte");
                    }
                    this.f8618b0 = b10;
                    this.f8613Y = true;
                }
                byte b11 = this.f8618b0;
                if ((b11 & 1) == 1) {
                    boolean z12 = (b11 & 2) == 2;
                    this.f8605Q |= TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
                    if (!this.f8619c0) {
                        h2.t tVar3 = this.f8629n;
                        c0073m.c(tVar3.f15241a, 0, 8, false);
                        this.f8609U += 8;
                        this.f8619c0 = true;
                        tVar2.f15241a[0] = (byte) ((z12 ? 128 : 0) | 8);
                        tVar2.H(0);
                        l.a(tVar2, 1, 1);
                        this.f8610V++;
                        tVar3.H(0);
                        l.a(tVar3, 8, 1);
                        this.f8610V += 8;
                    }
                    if (z12) {
                        if (!this.f8614Z) {
                            c0073m.c(tVar2.f15241a, 0, 1, false);
                            this.f8609U++;
                            tVar2.H(0);
                            this.f8616a0 = tVar2.v();
                            this.f8614Z = true;
                        }
                        int i14 = this.f8616a0 * 4;
                        tVar2.E(i14);
                        c0073m.c(tVar2.f15241a, 0, i14, false);
                        this.f8609U += i14;
                        short s6 = (short) ((this.f8616a0 / 2) + 1);
                        int i15 = (s6 * 6) + 2;
                        ByteBuffer byteBuffer = this.f8631q;
                        if (byteBuffer == null || byteBuffer.capacity() < i15) {
                            this.f8631q = ByteBuffer.allocate(i15);
                        }
                        this.f8631q.position(0);
                        this.f8631q.putShort(s6);
                        int i16 = 0;
                        int i17 = 0;
                        while (true) {
                            i10 = this.f8616a0;
                            if (i16 >= i10) {
                                break;
                            }
                            int iZ = tVar2.z();
                            if (i16 % 2 == 0) {
                                this.f8631q.putShort((short) (iZ - i17));
                            } else {
                                this.f8631q.putInt(iZ - i17);
                            }
                            i16++;
                            i17 = iZ;
                        }
                        int i18 = (i7 - this.f8609U) - i17;
                        if (i10 % 2 == 1) {
                            this.f8631q.putInt(i18);
                        } else {
                            this.f8631q.putShort((short) i18);
                            this.f8631q.putInt(0);
                        }
                        byte[] bArrArray = this.f8631q.array();
                        h2.t tVar4 = this.o;
                        tVar4.F(i15, bArrArray);
                        l.a(tVar4, i15, 1);
                        this.f8610V += i15;
                    }
                }
            } else {
                byte[] bArr = cVar.f8568i;
                if (bArr != null) {
                    tVar.F(bArr.length, bArr);
                }
            }
            if ("A_OPUS".equals(cVar.f8563b) ? z6 : cVar.f8565f > 0) {
                this.f8605Q |= 268435456;
                this.f8630p.E(0);
                int i19 = (tVar.c + i7) - this.f8609U;
                tVar2.E(4);
                byte[] bArr2 = tVar2.f15241a;
                bArr2[0] = (byte) ((i19 >> 24) & 255);
                bArr2[1] = (byte) ((i19 >> 16) & 255);
                bArr2[2] = (byte) ((i19 >> 8) & 255);
                bArr2[3] = (byte) (i19 & 255);
                l.a(tVar2, 4, 2);
                this.f8610V += 4;
            }
            this.f8612X = true;
        }
        int i20 = i7 + tVar.c;
        if (!"V_MPEG4/ISO/AVC".equals(cVar.f8563b) && !"V_MPEGH/ISO/HEVC".equals(cVar.f8563b)) {
            if (cVar.f8556U != null) {
                AbstractC1346c.j(tVar.c == 0);
                cVar.f8556U.c(c0073m);
            }
            while (true) {
                int i21 = this.f8609U;
                if (i21 >= i20) {
                    break;
                }
                int i22 = i20 - i21;
                int iA = tVar.a();
                if (iA > 0) {
                    iC2 = Math.min(i22, iA);
                    l.a(tVar, iC2, 0);
                } else {
                    iC2 = l.c(c0073m, i22, false);
                }
                this.f8609U += iC2;
                this.f8610V += iC2;
            }
        } else {
            h2.t tVar5 = this.f8624h;
            byte[] bArr3 = tVar5.f15241a;
            bArr3[0] = 0;
            bArr3[1] = 0;
            bArr3[2] = 0;
            int i23 = cVar.f8561Z;
            int i24 = 4 - i23;
            while (this.f8609U < i20) {
                int i25 = this.f8611W;
                if (i25 == 0) {
                    int iMin = Math.min(i23, tVar.a());
                    c0073m.c(bArr3, i24 + iMin, i23 - iMin, false);
                    if (iMin > 0) {
                        tVar.f(i24, iMin, bArr3);
                    }
                    this.f8609U += i23;
                    tVar5.H(0);
                    this.f8611W = tVar5.z();
                    h2.t tVar6 = this.f8623g;
                    tVar6.H(0);
                    l.a(tVar6, 4, 0);
                    this.f8610V += 4;
                } else {
                    int iA2 = tVar.a();
                    if (iA2 > 0) {
                        iC = Math.min(i25, iA2);
                        l.a(tVar, iC, 0);
                    } else {
                        iC = l.c(c0073m, i25, false);
                    }
                    this.f8609U += iC;
                    this.f8610V += iC;
                    this.f8611W -= iC;
                }
            }
        }
        if ("A_VORBIS".equals(cVar.f8563b)) {
            h2.t tVar7 = this.f8626j;
            tVar7.H(0);
            l.a(tVar7, 4, 0);
            this.f8610V += 4;
        }
        int i26 = this.f8610V;
        l();
        return i26;
    }

    public final void o(C0073m c0073m, byte[] bArr, int i7) throws EOFException, InterruptedIOException {
        int length = bArr.length + i7;
        h2.t tVar = this.f8628m;
        byte[] bArr2 = tVar.f15241a;
        if (bArr2.length < length) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, length + i7);
            tVar.F(bArrCopyOf.length, bArrCopyOf);
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        c0073m.c(tVar.f15241a, bArr.length, i7, false);
        tVar.H(0);
        tVar.G(length);
    }
}
