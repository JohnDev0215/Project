package T1;

import android.graphics.Rect;
import android.view.View;
import s3.AbstractC2367F;
import s3.C2401u;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f8493b;
    public final Object c;

    public f(AbstractC2367F abstractC2367F) {
        this.f8492a = Integer.MIN_VALUE;
        this.c = new Rect();
        this.f8493b = abstractC2367F;
    }

    public static f a(AbstractC2367F abstractC2367F, int i7) {
        if (i7 == 0) {
            return new C2401u(abstractC2367F, 0);
        }
        if (i7 == 1) {
            return new C2401u(abstractC2367F, 1);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int b(View view);

    public abstract int c(View view);

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f();

    public abstract int g();

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public int m() {
        if (Integer.MIN_VALUE == this.f8492a) {
            return 0;
        }
        return l() - this.f8492a;
    }

    public abstract int n(View view);

    public abstract int o(View view);

    public abstract void p(int i7);

    public f(h hVar) {
        this.f8492a = 0;
        this.c = new c();
        this.f8493b = hVar;
    }
}
