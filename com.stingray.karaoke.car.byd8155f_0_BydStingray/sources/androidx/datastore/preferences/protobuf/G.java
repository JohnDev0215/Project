package androidx.datastore.preferences.protobuf;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r f11792b = new r(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f11793a;

    public G(C0948k c0948k) {
        AbstractC0959w.a(c0948k, "output");
        this.f11793a = c0948k;
        c0948k.c = this;
    }

    public void a(int i7, boolean z6) throws IOException {
        ((C0948k) this.f11793a).M(i7, z6);
    }

    public void b(int i7, C0944g c0944g) throws IOException {
        ((C0948k) this.f11793a).N(i7, c0944g);
    }

    public void c(int i7, double d) throws IOException {
        C0948k c0948k = (C0948k) this.f11793a;
        c0948k.getClass();
        c0948k.R(Double.doubleToRawLongBits(d), i7);
    }

    public void d(int i7, int i10) throws IOException {
        ((C0948k) this.f11793a).T(i7, i10);
    }

    public void e(int i7, int i10) throws IOException {
        ((C0948k) this.f11793a).P(i7, i10);
    }

    public void f(long j3, int i7) throws IOException {
        ((C0948k) this.f11793a).R(j3, i7);
    }

    public void g(float f10, int i7) throws IOException {
        C0948k c0948k = (C0948k) this.f11793a;
        c0948k.getClass();
        c0948k.P(i7, Float.floatToRawIntBits(f10));
    }

    public void h(int i7, Object obj, Y y10) throws IOException {
        C0948k c0948k = (C0948k) this.f11793a;
        c0948k.X(i7, 3);
        y10.b((AbstractC0937a) obj, c0948k.c);
        c0948k.X(i7, 4);
    }

    public void i(int i7, int i10) throws IOException {
        ((C0948k) this.f11793a).T(i7, i10);
    }

    public void j(long j3, int i7) throws IOException {
        ((C0948k) this.f11793a).a0(j3, i7);
    }

    public void k(int i7, Object obj, Y y10) throws IOException {
        C0948k c0948k = (C0948k) this.f11793a;
        AbstractC0937a abstractC0937a = (AbstractC0937a) obj;
        c0948k.X(i7, 2);
        c0948k.Z(abstractC0937a.a(y10));
        y10.b(abstractC0937a, c0948k.c);
    }

    public void l(int i7, int i10) throws IOException {
        ((C0948k) this.f11793a).P(i7, i10);
    }

    public void m(long j3, int i7) throws IOException {
        ((C0948k) this.f11793a).R(j3, i7);
    }

    public void n(int i7, int i10) throws IOException {
        C0948k c0948k = (C0948k) this.f11793a;
        c0948k.Y(i7, (i10 >> 31) ^ (i10 << 1));
    }

    public void o(long j3, int i7) throws IOException {
        C0948k c0948k = (C0948k) this.f11793a;
        c0948k.a0((j3 >> 63) ^ (j3 << 1), i7);
    }

    public void p(int i7, int i10) throws IOException {
        ((C0948k) this.f11793a).Y(i7, i10);
    }

    public void q(long j3, int i7) throws IOException {
        ((C0948k) this.f11793a).a0(j3, i7);
    }

    public G() {
        M m10;
        try {
            m10 = (M) Class.forName("androidx.datastore.preferences.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
        } catch (Exception unused) {
            m10 = f11792b;
        }
        M[] mArr = {r.f11881b, m10};
        F f10 = new F();
        f10.f11791a = mArr;
        Charset charset = AbstractC0959w.f11890a;
        this.f11793a = f10;
    }
}
