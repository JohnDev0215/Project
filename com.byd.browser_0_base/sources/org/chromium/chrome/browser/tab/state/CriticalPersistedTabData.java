package org.chromium.chrome.browser.tab.state;

import J.N;
import android.util.Log;
import defpackage.l02;
import defpackage.m02;
import defpackage.mk3;
import defpackage.nn0;
import defpackage.o02;
import defpackage.oj2;
import defpackage.on2;
import defpackage.ui2;
import defpackage.vi2;
import defpackage.y10;
import defpackage.z10;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class CriticalPersistedTabData extends l02 {
    public static final a A = new a();
    public String n;
    public GURL o;
    public int p;
    public int q;
    public long r;
    public on2 s;
    public int t;
    public String u;
    public int v;
    public Integer w;
    public final org.chromium.base.b<z10> x;
    public int y;
    public boolean z;

    public class a implements o02<ui2> {
        public final ui2 a(ByteBuffer byteBuffer) {
            ui2 ui2Var;
            int i;
            if (byteBuffer == null || byteBuffer.limit() == 0) {
                return null;
            }
            try {
                y10 y10VarC = y10.c(byteBuffer);
                ByteBuffer byteBufferE = y10VarC.e();
                on2 on2Var = new on2(byteBufferE == null ? ByteBuffer.allocateDirect(0) : byteBufferE.slice());
                int i2 = 2;
                on2Var.a = 2;
                int iA = y10VarC.a(4);
                int i3 = iA != 0 ? y10VarC.b.getInt(iA + y10VarC.a) : 0;
                int iA2 = y10VarC.a(6);
                int i4 = iA2 != 0 ? y10VarC.b.getInt(iA2 + y10VarC.a) : 0;
                int iA3 = y10VarC.a(8);
                long j = iA3 != 0 ? y10VarC.b.getLong(iA3 + y10VarC.a) : 0L;
                String strD = " ".equals(y10VarC.d()) ? null : y10VarC.d();
                int iA4 = y10VarC.a(12);
                int i5 = iA4 != 0 ? y10VarC.b.getInt(iA4 + y10VarC.a) : 0;
                int iA5 = y10VarC.a(16);
                int i6 = iA5 != 0 ? y10VarC.b.getInt(iA5 + y10VarC.a) : 0;
                int iA6 = y10VarC.a(18);
                Integer numN = CriticalPersistedTabData.n(iA6 != 0 ? y10VarC.b.getInt(iA6 + y10VarC.a) : 0);
                int iA7 = y10VarC.a(20);
                int i7 = iA7 != 0 ? y10VarC.b.getInt(iA7 + y10VarC.a) : 0;
                if (i7 == 0) {
                    i = 0;
                    ui2Var = new ui2(i3, i4, j, on2Var, strD, i5, i6, numN, i);
                } else {
                    if (i7 == 1) {
                        i2 = 1;
                    } else if (i7 != 2) {
                        i2 = 3;
                        if (i7 != 3) {
                            if (i7 != 4) {
                                i = 0;
                                ui2Var = new ui2(i3, i4, j, on2Var, strD, i5, i6, numN, i);
                            } else {
                                i2 = 4;
                            }
                        }
                    }
                    i = i2;
                    ui2Var = new ui2(i3, i4, j, on2Var, strD, i5, i6, numN, i);
                }
            } catch (Exception e) {
                Log.e("cr_CriticalPTD", "Failed to deserialize CriticalPersistedTabDataFlatBuffer. Details: " + e.getMessage());
                ui2Var = null;
            }
            oj2.O("Tabs.PersistedTabData.Critical.Map.Success", ui2Var != null);
            return ui2Var;
        }
    }

    public class b implements vi2<ByteBuffer> {
        public ByteBuffer e;
        public String f;
        public int g;
        public int h;
        public long i;
        public int j;
        public int k;
        public int l;
        public int m;
        public boolean n;

        public b() {
        }

        @Override // defpackage.vi2
        public final ByteBuffer get() {
            ByteBuffer byteBufferAsReadOnlyBuffer = null;
            TraceEvent traceEventH0 = TraceEvent.H0("CriticalPersistedTabData.Serialize", null);
            try {
                ByteBuffer byteBuffer = this.e;
                if (byteBuffer != null) {
                    byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
                }
                if (byteBufferAsReadOnlyBuffer != null) {
                    byteBufferAsReadOnlyBuffer.rewind();
                }
                nn0 nn0Var = new nn0();
                if (byteBufferAsReadOnlyBuffer == null) {
                    byteBufferAsReadOnlyBuffer = ByteBuffer.allocate(0).put(new byte[0]);
                }
                int iRemaining = byteBufferAsReadOnlyBuffer.remaining();
                if (nn0Var.f) {
                    throw new AssertionError("FlatBuffers: object serialization must not be nested.");
                }
                nn0Var.j = iRemaining;
                int i = iRemaining * 1;
                nn0Var.e(4, i);
                nn0Var.e(1, i);
                nn0Var.f = true;
                ByteBuffer byteBuffer2 = nn0Var.a;
                int i2 = nn0Var.b - iRemaining;
                nn0Var.b = i2;
                nn0Var.a.put(byteBufferAsReadOnlyBuffer);
                if (!nn0Var.f) {
                    throw new AssertionError("FlatBuffers: endVector called without startVector");
                }
                nn0Var.f = false;
                int i3 = nn0Var.j;
                ByteBuffer byteBuffer3 = nn0Var.a;
                int i4 = nn0Var.b - 4;
                nn0Var.b = i4;
                byteBuffer3.putInt(i4, i3);
                int iD = nn0Var.d();
                String str = this.f;
                if (str == null) {
                    str = " ";
                }
                int iC = nn0Var.c(str);
                if (nn0Var.f) {
                    throw new AssertionError("FlatBuffers: object serialization must not be nested.");
                }
                int[] iArr = nn0Var.d;
                if (iArr == null || iArr.length < 9) {
                    nn0Var.d = new int[9];
                }
                nn0Var.e = 9;
                Arrays.fill(nn0Var.d, 0, 9, 0);
                nn0Var.f = true;
                nn0Var.g = nn0Var.d();
                nn0Var.a(0, this.g);
                nn0Var.a(1, this.h);
                long j = this.i;
                if (j != 0) {
                    nn0Var.e(8, 0);
                    ByteBuffer byteBuffer4 = nn0Var.a;
                    int i5 = nn0Var.b - 8;
                    nn0Var.b = i5;
                    byteBuffer4.putLong(i5, j);
                    nn0Var.d[2] = nn0Var.d();
                }
                if (iD != 0) {
                    nn0Var.e(4, 0);
                    int iD2 = (nn0Var.d() - iD) + 4;
                    ByteBuffer byteBuffer5 = nn0Var.a;
                    int i6 = nn0Var.b - 4;
                    nn0Var.b = i6;
                    byteBuffer5.putInt(i6, iD2);
                    nn0Var.d[3] = nn0Var.d();
                }
                nn0Var.a(4, this.j);
                if (iC != 0) {
                    nn0Var.e(4, 0);
                    int iD3 = (nn0Var.d() - iC) + 4;
                    ByteBuffer byteBuffer6 = nn0Var.a;
                    int i7 = nn0Var.b - 4;
                    nn0Var.b = i7;
                    byteBuffer6.putInt(i7, iD3);
                    nn0Var.d[5] = nn0Var.d();
                }
                nn0Var.a(6, this.k);
                nn0Var.a(7, this.l);
                nn0Var.a(8, this.m);
                int iB = y10.b(nn0Var);
                nn0Var.e(nn0Var.c, 4);
                nn0Var.e(4, 0);
                int iD4 = (nn0Var.d() - iB) + 4;
                ByteBuffer byteBuffer7 = nn0Var.a;
                int i8 = nn0Var.b - 4;
                nn0Var.b = i8;
                byteBuffer7.putInt(i8, iD4);
                ByteBuffer byteBuffer8 = nn0Var.a;
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return byteBuffer8;
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:57:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x00d8  */
        @Override // defpackage.vi2
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void m() {
            /*
                Method dump skipped, instruction units count: 274
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.b.m():void");
        }
    }

    public CriticalPersistedTabData(Tab tab, String str, String str2, int i, int i2, long j, on2 on2Var, int i3, String str3, int i4, Integer num, int i5) {
        super(tab, m02.a(CriticalPersistedTabData.class, tab.isIncognito()).f(), m02.a(CriticalPersistedTabData.class, tab.isIncognito()).e);
        this.x = new org.chromium.base.b<>();
        this.o = (str == null || str.isEmpty()) ? GURL.emptyGURL() : new GURL(str);
        this.n = str2;
        this.p = i;
        this.q = i2;
        this.r = j;
        this.s = on2Var;
        this.t = i3;
        this.u = str3;
        this.v = i4;
        this.w = num;
        this.y = i5;
    }

    @CalledByNative
    public static int getUserAgent(Tab tab) {
        return m(tab).y;
    }

    public static CriticalPersistedTabData m(Tab tab) {
        l02 l02VarD = l02.d(tab, CriticalPersistedTabData.class);
        if (l02VarD == null) {
            mk3 mk3VarB = tab.B();
            CriticalPersistedTabData criticalPersistedTabData = new CriticalPersistedTabData(tab, "", "", -1, tab.getId(), -1L, null, -1, "", 0, null, 0);
            criticalPersistedTabData.l();
            l02VarD = (l02) mk3VarB.d(CriticalPersistedTabData.class, criticalPersistedTabData);
        }
        return (CriticalPersistedTabData) l02VarD;
    }

    public static Integer n(int i) {
        switch (i) {
            case -2:
                return 20;
            case -1:
                return null;
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            case 11:
                return 11;
            case 12:
                return 12;
            case 13:
                return 13;
            case 14:
                return 14;
            case 15:
                return 15;
            case 16:
                return 16;
            case 17:
                return 17;
            case 18:
                return 18;
            case 19:
                return 19;
            default:
                return null;
        }
    }

    @Override // defpackage.l02
    public final void a() {
        super.a();
    }

    @Override // defpackage.l02
    public final boolean b(ByteBuffer byteBuffer) {
        GURL gurl;
        String strD = null;
        TraceEvent traceEventH0 = TraceEvent.H0("CriticalPersistedTabData.Deserialize", null);
        try {
            y10 y10VarC = y10.c(byteBuffer);
            int i = 4;
            int iA = y10VarC.a(4);
            int i2 = 0;
            this.p = iA != 0 ? y10VarC.b.getInt(iA + y10VarC.a) : 0;
            int iA2 = y10VarC.a(6);
            this.q = iA2 != 0 ? y10VarC.b.getInt(iA2 + y10VarC.a) : 0;
            int iA3 = y10VarC.a(8);
            this.r = iA3 != 0 ? y10VarC.b.getLong(iA3 + y10VarC.a) : 0L;
            ByteBuffer byteBufferE = y10VarC.e();
            ByteBuffer byteBufferAllocateDirect = byteBufferE == null ? ByteBuffer.allocateDirect(0) : byteBufferE.slice();
            on2 on2Var = new on2(byteBufferAllocateDirect);
            this.s = on2Var;
            on2Var.a = 2;
            if (N.MNZ4eg9q(byteBufferAllocateDirect, 2) == null) {
                gurl = GURL.emptyGURL();
            } else {
                on2 on2Var2 = this.s;
                gurl = new GURL(N.MNZ4eg9q((ByteBuffer) on2Var2.b, on2Var2.a));
            }
            this.o = gurl;
            on2 on2Var3 = this.s;
            this.n = N.MZZlQD12((ByteBuffer) on2Var3.b, on2Var3.a);
            int iA4 = y10VarC.a(12);
            this.t = iA4 != 0 ? y10VarC.b.getInt(iA4 + y10VarC.a) : 0;
            if (!" ".equals(y10VarC.d())) {
                strD = y10VarC.d();
            }
            this.u = strD;
            int iA5 = y10VarC.a(16);
            this.v = iA5 != 0 ? y10VarC.b.getInt(iA5 + y10VarC.a) : 0;
            int iA6 = y10VarC.a(18);
            this.w = n(iA6 != 0 ? y10VarC.b.getInt(iA6 + y10VarC.a) : 0);
            int iA7 = y10VarC.a(20);
            int i3 = iA7 != 0 ? y10VarC.b.getInt(iA7 + y10VarC.a) : 0;
            if (i3 != 0) {
                if (i3 == 1) {
                    i = 1;
                } else if (i3 == 2) {
                    i = 2;
                } else if (i3 == 3) {
                    i = 3;
                } else if (i3 != 4) {
                }
                i2 = i;
            }
            this.y = i2;
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return true;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.l02, defpackage.lk3
    public final void destroy() {
        this.x.clear();
    }

    @Override // defpackage.l02
    public final vi2<ByteBuffer> g() {
        return new b();
    }

    @Override // defpackage.l02
    public final String i() {
        return "Critical";
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003e  */
    @Override // defpackage.l02
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l() {
        /*
            r5 = this;
            boolean r0 = r5.z
            org.chromium.chrome.browser.tab.Tab r1 = r5.e
            r2 = 0
            if (r0 != 0) goto L8
            goto L3e
        L8:
            org.chromium.url.GURL r0 = r5.o
            if (r0 == 0) goto L3e
            boolean r0 = r0.k()
            if (r0 == 0) goto L13
            goto L3e
        L13:
            org.chromium.url.GURL r0 = r5.o
            boolean r0 = defpackage.bk3.l(r0)
            if (r0 == 0) goto L28
            boolean r0 = r1.g()
            if (r0 != 0) goto L28
            boolean r0 = r1.i()
            if (r0 != 0) goto L28
            goto L3e
        L28:
            org.chromium.url.GURL r0 = r5.o
            r3 = 1
            if (r0 == 0) goto L3b
            java.lang.String r0 = r0.i()
            java.lang.String r4 = "content"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L3b
            r0 = r3
            goto L3c
        L3b:
            r0 = r2
        L3c:
            if (r0 == 0) goto L3f
        L3e:
            r3 = r2
        L3f:
            if (r3 == 0) goto L63
            x10 r0 = new x10
            r0.<init>(r2, r5)
            aq1<java.lang.Boolean> r2 = r5.i
            if (r2 == 0) goto L63
            E r2 = r2.f
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L63
            int r1 = r1.getId()
            k02 r2 = r5.f()
            q02 r3 = r5.f
            java.lang.String r5 = r5.g
            r3.g(r1, r5, r2, r0)
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.l():void");
    }

    public final void o(int i) {
        if (this.q == i) {
            return;
        }
        Tab tab = this.e;
        if (tab.isDestroyed()) {
            return;
        }
        this.q = i;
        Iterator<z10> it = this.x.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                l();
                return;
            }
            ((z10) aVar.next()).H(i, tab);
        }
    }

    public final void p(long j) {
        if (this.r == j) {
            return;
        }
        this.r = j;
        Iterator<z10> it = this.x.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                l();
                return;
            }
            ((z10) aVar.next()).u1(this.e, j);
        }
    }

    public final void q(GURL gurl) {
        if (gurl == null && this.o == null) {
            return;
        }
        if (gurl == null || !gurl.equals(this.o)) {
            this.o = gurl;
            l();
        }
    }

    public final void r(int i) {
        if (this.y == i) {
            return;
        }
        this.y = i;
        l();
    }

    public final void s(on2 on2Var) {
        if (on2Var == null && this.s == null) {
            return;
        }
        if (on2Var == null || !on2Var.equals(this.s)) {
            this.s = on2Var;
            l();
        }
    }
}
