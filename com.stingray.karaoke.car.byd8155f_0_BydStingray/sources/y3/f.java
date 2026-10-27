package Y3;

import android.graphics.Color;
import android.graphics.PointF;
import b4.C0996b;

/* JADX INFO: loaded from: classes.dex */
public final class f implements D {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f f10670m = new f(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final f f10671n = new f(1);
    public static final f o = new f(2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final f f10672p = new f(3);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final f f10673q = new f(4);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final f f10674r = new f(5);
    public final /* synthetic */ int l;

    public /* synthetic */ f(int i7) {
        this.l = i7;
    }

    @Override // Y3.D
    public final Object d(Z3.a aVar, float f10) {
        switch (this.l) {
            case 0:
                boolean z6 = aVar.z() == 1;
                if (z6) {
                    aVar.b();
                }
                double dQ = aVar.q();
                double dQ2 = aVar.q();
                double dQ3 = aVar.q();
                double dQ4 = aVar.z() == 7 ? aVar.q() : 1.0d;
                if (z6) {
                    aVar.i();
                }
                if (dQ <= 1.0d && dQ2 <= 1.0d && dQ3 <= 1.0d) {
                    dQ *= 255.0d;
                    dQ2 *= 255.0d;
                    dQ3 *= 255.0d;
                    if (dQ4 <= 1.0d) {
                        dQ4 *= 255.0d;
                    }
                }
                return Integer.valueOf(Color.argb((int) dQ4, (int) dQ, (int) dQ2, (int) dQ3));
            case 1:
                return Float.valueOf(n.d(aVar) * f10);
            case 2:
                return Integer.valueOf(Math.round(n.d(aVar) * f10));
            case 3:
                return n.b(aVar, f10);
            case 4:
                int iZ = aVar.z();
                if (iZ != 1 && iZ != 3) {
                    if (iZ != 7) {
                        throw new IllegalArgumentException("Cannot convert json to point. Next token is ".concat(M5.b.v(iZ)));
                    }
                    PointF pointF = new PointF(((float) aVar.q()) * f10, ((float) aVar.q()) * f10);
                    while (aVar.m()) {
                        aVar.I();
                    }
                    return pointF;
                }
                return n.b(aVar, f10);
            default:
                boolean z10 = aVar.z() == 1;
                if (z10) {
                    aVar.b();
                }
                float fQ = (float) aVar.q();
                float fQ2 = (float) aVar.q();
                while (aVar.m()) {
                    aVar.I();
                }
                if (z10) {
                    aVar.i();
                }
                return new C0996b((fQ / 100.0f) * f10, (fQ2 / 100.0f) * f10);
        }
    }
}
