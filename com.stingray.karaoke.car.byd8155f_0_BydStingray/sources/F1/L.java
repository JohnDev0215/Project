package F1;

import android.graphics.Insets;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class L extends N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowInsets.Builder f2309a;

    public L() {
        this.f2309a = new WindowInsets.Builder();
    }

    @Override // F1.N
    public V b() {
        a();
        V vC = V.c(this.f2309a.build(), null);
        vC.f2321a.l(null);
        return vC;
    }

    @Override // F1.N
    public void c(y1.b bVar) {
        this.f2309a.setSystemWindowInsets(Insets.of(bVar.f24113a, bVar.f24114b, bVar.c, bVar.d));
    }

    public L(V v10) {
        WindowInsets.Builder builder;
        super(v10);
        WindowInsets windowInsetsB = v10.b();
        if (windowInsetsB != null) {
            builder = new WindowInsets.Builder(windowInsetsB);
        } else {
            builder = new WindowInsets.Builder();
        }
        this.f2309a = builder;
    }
}
