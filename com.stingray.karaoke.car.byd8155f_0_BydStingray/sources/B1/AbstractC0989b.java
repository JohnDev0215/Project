package b1;

import u.AbstractC2473m;
import u.C2459G;
import v.AbstractC2530a;

/* JADX INFO: renamed from: b1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0989b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[] f12198a = {8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile C2459G f12199b = new C2459G(0);
    public static final Object[] c;

    static {
        Object[] objArr = new Object[0];
        c = objArr;
        synchronized (objArr) {
            f12199b.e((int) 115.0f, new C0990c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{9.2f, 11.5f, 13.8f, 16.4f, 19.8f, 21.8f, 25.2f, 30.0f, 100.0f}));
            f12199b.e((int) 130.0f, new C0990c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{10.4f, 13.0f, 15.6f, 18.8f, 21.6f, 23.6f, 26.4f, 30.0f, 100.0f}));
            f12199b.e((int) 150.0f, new C0990c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{12.0f, 15.0f, 18.0f, 22.0f, 24.0f, 26.0f, 28.0f, 30.0f, 100.0f}));
            f12199b.e((int) 180.0f, new C0990c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{14.4f, 18.0f, 21.6f, 24.4f, 27.6f, 30.8f, 32.8f, 34.8f, 100.0f}));
            f12199b.e((int) 200.0f, new C0990c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{16.0f, 20.0f, 24.0f, 26.0f, 30.0f, 34.0f, 36.0f, 38.0f, 100.0f}));
        }
        if ((f12199b.d(0) / 100.0f) - 0.01f <= 1.03f) {
            throw new IllegalStateException("You should only apply non-linear scaling to font scales > 1");
        }
    }

    public static InterfaceC0988a a(float f10) {
        float fD;
        InterfaceC0988a c0990c;
        if (f10 < 1.03f) {
            return null;
        }
        int i7 = (int) (f10 * 100.0f);
        InterfaceC0988a interfaceC0988a = (InterfaceC0988a) f12199b.c(i7);
        if (interfaceC0988a != null) {
            return interfaceC0988a;
        }
        C2459G c2459g = f12199b;
        if (c2459g.l) {
            AbstractC2473m.a(c2459g);
        }
        int iA = AbstractC2530a.a(c2459g.o, i7, c2459g.f22588m);
        if (iA >= 0) {
            return (InterfaceC0988a) f12199b.g(iA);
        }
        int i10 = -(iA + 1);
        int i11 = i10 - 1;
        if (i10 >= f12199b.f()) {
            C0990c c0990c2 = new C0990c(new float[]{1.0f}, new float[]{f10});
            b(f10, c0990c2);
            return c0990c2;
        }
        float[] fArr = f12198a;
        if (i11 < 0) {
            c0990c = new C0990c(fArr, fArr);
            fD = 1.0f;
        } else {
            fD = f12199b.d(i11) / 100.0f;
            c0990c = (InterfaceC0988a) f12199b.g(i11);
        }
        float fD2 = f12199b.d(i10) / 100.0f;
        float fMax = (Math.max(0.0f, Math.min(1.0f, fD == fD2 ? 0.0f : (f10 - fD) / (fD2 - fD))) * 1.0f) + 0.0f;
        InterfaceC0988a interfaceC0988a2 = (InterfaceC0988a) f12199b.g(i10);
        float[] fArr2 = new float[9];
        for (int i12 = 0; i12 < 9; i12++) {
            float f11 = fArr[i12];
            float fB = c0990c.b(f11);
            fArr2[i12] = ((interfaceC0988a2.b(f11) - fB) * fMax) + fB;
        }
        C0990c c0990c3 = new C0990c(fArr, fArr2);
        b(f10, c0990c3);
        return c0990c3;
    }

    public static void b(float f10, C0990c c0990c) {
        synchronized (c) {
            C2459G c2459gClone = f12199b.clone();
            c2459gClone.e((int) (f10 * 100.0f), c0990c);
            f12199b = c2459gClone;
        }
    }
}
