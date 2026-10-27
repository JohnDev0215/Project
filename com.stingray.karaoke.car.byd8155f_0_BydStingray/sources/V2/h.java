package V2;

import B2.L;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import h2.AbstractC1343A;
import h2.t;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L f9341a;
    public s d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f9343e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9344f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9345g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f9346h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9347i;
    public boolean l;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f9342b = new r();
    public final t c = new t();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final t f9348j = new t(1);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final t f9349k = new t();

    public h(L l, s sVar, e eVar) {
        this.f9341a = l;
        this.d = sVar;
        this.f9343e = eVar;
        this.d = sVar;
        this.f9343e = eVar;
        l.d(sVar.f9445a.f9424g);
        e();
    }

    public final int a() {
        int i7 = !this.l ? this.d.f9449g[this.f9344f] : this.f9342b.f9439j[this.f9344f] ? 1 : 0;
        return b() != null ? i7 | TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED : i7;
    }

    public final q b() {
        if (!this.l) {
            return null;
        }
        r rVar = this.f9342b;
        e eVar = rVar.f9432a;
        int i7 = AbstractC1343A.f15195a;
        int i10 = eVar.f9337a;
        q qVar = rVar.f9441m;
        if (qVar == null) {
            qVar = this.d.f9445a.l[i10];
        }
        if (qVar == null || !qVar.f9429a) {
            return null;
        }
        return qVar;
    }

    public final boolean c() {
        this.f9344f++;
        if (!this.l) {
            return false;
        }
        int i7 = this.f9345g + 1;
        this.f9345g = i7;
        int[] iArr = this.f9342b.f9436g;
        int i10 = this.f9346h;
        if (i7 != iArr[i10]) {
            return true;
        }
        this.f9346h = i10 + 1;
        this.f9345g = 0;
        return false;
    }

    public final int d(int i7, int i10) {
        t tVar;
        q qVarB = b();
        if (qVarB == null) {
            return 0;
        }
        r rVar = this.f9342b;
        int length = qVarB.d;
        if (length != 0) {
            tVar = rVar.f9442n;
        } else {
            int i11 = AbstractC1343A.f15195a;
            byte[] bArr = qVarB.f9431e;
            int length2 = bArr.length;
            t tVar2 = this.f9349k;
            tVar2.F(length2, bArr);
            length = bArr.length;
            tVar = tVar2;
        }
        boolean z6 = rVar.f9440k && rVar.l[this.f9344f];
        boolean z10 = z6 || i10 != 0;
        t tVar3 = this.f9348j;
        tVar3.f15241a[0] = (byte) ((z10 ? 128 : 0) | length);
        tVar3.H(0);
        L l = this.f9341a;
        l.a(tVar3, 1, 1);
        l.a(tVar, length, 1);
        if (!z10) {
            return length + 1;
        }
        t tVar4 = this.c;
        if (!z6) {
            tVar4.E(8);
            byte[] bArr2 = tVar4.f15241a;
            bArr2[0] = 0;
            bArr2[1] = 1;
            bArr2[2] = (byte) 0;
            bArr2[3] = (byte) (i10 & 255);
            bArr2[4] = (byte) ((i7 >> 24) & 255);
            bArr2[5] = (byte) ((i7 >> 16) & 255);
            bArr2[6] = (byte) ((i7 >> 8) & 255);
            bArr2[7] = (byte) (i7 & 255);
            l.a(tVar4, 8, 1);
            return length + 9;
        }
        t tVar5 = rVar.f9442n;
        int iB = tVar5.B();
        tVar5.I(-2);
        int i12 = (iB * 6) + 2;
        if (i10 != 0) {
            tVar4.E(i12);
            byte[] bArr3 = tVar4.f15241a;
            tVar5.f(0, i12, bArr3);
            int i13 = (((bArr3[2] & 255) << 8) | (bArr3[3] & 255)) + i10;
            bArr3[2] = (byte) ((i13 >> 8) & 255);
            bArr3[3] = (byte) (i13 & 255);
        } else {
            tVar4 = tVar5;
        }
        l.a(tVar4, i12, 1);
        return length + 1 + i12;
    }

    public final void e() {
        r rVar = this.f9342b;
        rVar.d = 0;
        rVar.f9443p = 0L;
        rVar.f9444q = false;
        rVar.f9440k = false;
        rVar.o = false;
        rVar.f9441m = null;
        this.f9344f = 0;
        this.f9346h = 0;
        this.f9345g = 0;
        this.f9347i = 0;
        this.l = false;
    }
}
