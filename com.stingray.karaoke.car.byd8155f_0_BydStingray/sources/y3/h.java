package Y3;

import android.graphics.PointF;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class h implements D {
    public static final h l = new h();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f10676m = io.sentry.internal.debugmeta.c.Q("t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of", "ps", "sz");

    @Override // Y3.D
    public final Object d(Z3.a aVar, float f10) {
        aVar.g();
        String strY = null;
        float fQ = 0.0f;
        float fQ2 = 0.0f;
        float fQ3 = 0.0f;
        float fQ4 = 0.0f;
        int iV = 0;
        int iA = 0;
        int iA2 = 0;
        boolean zO = true;
        int i7 = 3;
        String strY2 = null;
        PointF pointF = null;
        PointF pointF2 = null;
        while (aVar.m()) {
            switch (aVar.F(f10676m)) {
                case 0:
                    strY = aVar.y();
                    break;
                case 1:
                    strY2 = aVar.y();
                    break;
                case 2:
                    fQ = (float) aVar.q();
                    break;
                case 3:
                    int iV2 = aVar.v();
                    i7 = (iV2 <= 2 && iV2 >= 0) ? AbstractC2641j.d(3)[iV2] : 3;
                    break;
                case 4:
                    iV = aVar.v();
                    break;
                case 5:
                    fQ2 = (float) aVar.q();
                    break;
                case 6:
                    fQ3 = (float) aVar.q();
                    break;
                case 7:
                    iA = n.a(aVar);
                    break;
                case 8:
                    iA2 = n.a(aVar);
                    break;
                case 9:
                    fQ4 = (float) aVar.q();
                    break;
                case 10:
                    zO = aVar.o();
                    break;
                case 11:
                    aVar.b();
                    pointF = new PointF(((float) aVar.q()) * f10, ((float) aVar.q()) * f10);
                    aVar.i();
                    break;
                case 12:
                    aVar.b();
                    strY = strY;
                    pointF2 = new PointF(((float) aVar.q()) * f10, ((float) aVar.q()) * f10);
                    aVar.i();
                    break;
                default:
                    aVar.G();
                    aVar.I();
                    break;
            }
        }
        aVar.j();
        U3.b bVar = new U3.b();
        bVar.f8997a = strY;
        bVar.f8998b = strY2;
        bVar.c = fQ;
        bVar.d = i7;
        bVar.f8999e = iV;
        bVar.f9000f = fQ2;
        bVar.f9001g = fQ3;
        bVar.f9002h = iA;
        bVar.f9003i = iA2;
        bVar.f9004j = fQ4;
        bVar.f9005k = zO;
        bVar.l = pointF;
        bVar.f9006m = pointF2;
        return bVar;
    }
}
