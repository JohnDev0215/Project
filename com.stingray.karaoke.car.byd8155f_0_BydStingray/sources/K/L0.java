package K;

import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.util.SparseArray;
import android.view.View;
import e2.C1208p;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.C1489n1;
import java.lang.reflect.Array;
import k2.InterfaceC1606g;
import x.AbstractC2652s;
import x.C2618G;
import x.InterfaceC2612A;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class L0 implements V2.b, InterfaceC1606g, x.G0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3180m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3181n;
    public final Object o;

    public L0(View view) {
        this.l = 1;
        this.o = view;
    }

    @Override // x.F0
    public AbstractC2652s c(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return ((B8.t) this.o).c(j3, abstractC2652s, abstractC2652s2, abstractC2652s3);
    }

    @Override // V2.b
    public int d() {
        return this.f3180m;
    }

    @Override // V2.b
    public int e() {
        return this.f3181n;
    }

    @Override // V2.b
    public int f() {
        int i7 = this.f3180m;
        return i7 == -1 ? ((h2.t) this.o).z() : i7;
    }

    public byte g(int i7, int i10) {
        return ((byte[][]) this.o)[i10][i7];
    }

    public void h(int i7, int i10, int i11) {
        ((byte[][]) this.o)[i10][i7] = (byte) i11;
    }

    @Override // x.G0
    public int n() {
        return this.f3181n;
    }

    @Override // x.F0
    public AbstractC2652s o(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return ((B8.t) this.o).o(j3, abstractC2652s, abstractC2652s2, abstractC2652s3);
    }

    @Override // k2.InterfaceC1606g
    public k2.h q() {
        return new k2.q(this.f3180m, this.f3181n, (C1489n1) this.o);
    }

    @Override // x.G0
    public int r() {
        return this.f3180m;
    }

    public String toString() {
        switch (this.l) {
            case 6:
                int i7 = this.f3180m;
                int i10 = this.f3181n;
                StringBuilder sb = new StringBuilder((i7 * 2 * i10) + 2);
                for (int i11 = 0; i11 < i10; i11++) {
                    byte[] bArr = ((byte[][]) this.o)[i11];
                    for (int i12 = 0; i12 < i7; i12++) {
                        byte b10 = bArr[i12];
                        if (b10 == 0) {
                            sb.append(" 0");
                        } else if (b10 != 1) {
                            sb.append("  ");
                        } else {
                            sb.append(" 1");
                        }
                    }
                    sb.append('\n');
                }
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public L0(int i7) {
        this.l = i7;
        switch (i7) {
            case 5:
                this.o = new C1489n1(28);
                this.f3180m = BYDAutoEngineDevice.ENGINE_SPEED_MAX;
                this.f3181n = BYDAutoEngineDevice.ENGINE_SPEED_MAX;
                break;
            default:
                this.o = new L0[256];
                this.f3180m = 0;
                this.f3181n = 0;
                break;
        }
    }

    public L0(int i7, int i10, int i11) {
        this.l = i11;
        switch (i11) {
            case 6:
                this.o = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i10, i7);
                this.f3180m = i7;
                this.f3181n = i10;
                break;
            default:
                this.o = null;
                this.f3180m = i7;
                int i12 = i10 & 7;
                this.f3181n = i12 == 0 ? 8 : i12;
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public L0(int i7, int i10, InterfaceC2772a interfaceC2772a) {
        this.l = 0;
        this.f3180m = i7;
        this.f3181n = i10;
        this.o = (A7.o) interfaceC2772a;
    }

    public L0(int i7, int i10, InterfaceC2612A interfaceC2612A) {
        this.l = 7;
        this.f3180m = i7;
        this.f3181n = i10;
        this.o = new B8.t(new C2618G(i7, i10, interfaceC2612A));
    }

    public L0(int i7, int i10, SparseArray sparseArray) {
        this.l = 3;
        this.f3180m = i7;
        this.f3181n = i10;
        this.o = sparseArray;
    }

    public L0(i2.c cVar, C1208p c1208p) {
        this.l = 2;
        h2.t tVar = cVar.f15672n;
        this.o = tVar;
        tVar.H(12);
        int iZ = tVar.z();
        if ("audio/raw".equals(c1208p.f14316n)) {
            int iV = AbstractC1343A.v(c1208p.f14297E, c1208p.f14295C);
            if (iZ == 0 || iZ % iV != 0) {
                AbstractC1346c.z("BoxParsers", "Audio sample size mismatch. stsd sample size: " + iV + ", stsz sample size: " + iZ);
                iZ = iV;
            }
        }
        this.f3180m = iZ == 0 ? -1 : iZ;
        this.f3181n = tVar.z();
    }
}
