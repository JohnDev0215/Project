package F1;

import android.os.Build;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class U {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final V f2318b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V f2319a;

    static {
        f2318b = (Build.VERSION.SDK_INT >= 30 ? new M() : new L()).b().f2321a.a().f2321a.b().f2321a.c();
    }

    public U(V v10) {
        this.f2319a = v10;
    }

    public V a() {
        return this.f2319a;
    }

    public V b() {
        return this.f2319a;
    }

    public V c() {
        return this.f2319a;
    }

    public void d(View view) {
    }

    public C0249h e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof U)) {
            return false;
        }
        U u4 = (U) obj;
        return k() == u4.k() && j() == u4.j() && Objects.equals(h(), u4.h()) && Objects.equals(g(), u4.g()) && Objects.equals(e(), u4.e());
    }

    public y1.b f() {
        return h();
    }

    public y1.b g() {
        return y1.b.f24112e;
    }

    public y1.b h() {
        return y1.b.f24112e;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(k()), Boolean.valueOf(j()), h(), g(), e());
    }

    public V i(int i7, int i10, int i11, int i12) {
        return f2318b;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return false;
    }

    public void l(y1.b[] bVarArr) {
    }

    public void m(V v10) {
    }
}
