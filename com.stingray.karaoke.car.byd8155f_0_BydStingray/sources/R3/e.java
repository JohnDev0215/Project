package R3;

import android.view.animation.BaseInterpolator;
import b4.C0995a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    public final b c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f7388a = new ArrayList(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f7389b = false;
    public float d = 0.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f7390e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f7391f = -1.0f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f7392g = -1.0f;

    public e(List list) {
        b dVar;
        if (list.isEmpty()) {
            dVar = new Y4.e(8);
        } else {
            dVar = list.size() == 1 ? new d(list) : new c(list);
        }
        this.c = dVar;
    }

    public final void a(a aVar) {
        this.f7388a.add(aVar);
    }

    public final float b() {
        BaseInterpolator baseInterpolator;
        C0995a c0995aT = this.c.t();
        if (c0995aT == null || c0995aT.c() || (baseInterpolator = c0995aT.d) == null) {
            return 0.0f;
        }
        return baseInterpolator.getInterpolation(c());
    }

    public final float c() {
        if (this.f7389b) {
            return 0.0f;
        }
        C0995a c0995aT = this.c.t();
        if (c0995aT.c()) {
            return 0.0f;
        }
        return (this.d - c0995aT.b()) / (c0995aT.a() - c0995aT.b());
    }

    public Object d() {
        BaseInterpolator baseInterpolator;
        float fC = c();
        b bVar = this.c;
        if (bVar.q(fC) && !h()) {
            return this.f7390e;
        }
        C0995a c0995aT = bVar.t();
        BaseInterpolator baseInterpolator2 = c0995aT.f12213e;
        Object objE = (baseInterpolator2 == null || (baseInterpolator = c0995aT.f12214f) == null) ? e(c0995aT, b()) : f(c0995aT, fC, baseInterpolator2.getInterpolation(fC), baseInterpolator.getInterpolation(fC));
        this.f7390e = objE;
        return objE;
    }

    public abstract Object e(C0995a c0995a, float f10);

    public Object f(C0995a c0995a, float f10, float f11, float f12) {
        throw new UnsupportedOperationException("This animation does not support split dimensions!");
    }

    public void g(float f10) {
        b bVar = this.c;
        if (bVar.isEmpty()) {
            return;
        }
        if (this.f7391f == -1.0f) {
            this.f7391f = bVar.r();
        }
        float f11 = this.f7391f;
        if (f10 < f11) {
            if (f11 == -1.0f) {
                this.f7391f = bVar.r();
            }
            f10 = this.f7391f;
        } else {
            if (this.f7392g == -1.0f) {
                this.f7392g = bVar.p();
            }
            float f12 = this.f7392g;
            if (f10 > f12) {
                if (f12 == -1.0f) {
                    this.f7392g = bVar.p();
                }
                f10 = this.f7392g;
            }
        }
        if (f10 == this.d) {
            return;
        }
        this.d = f10;
        if (!bVar.x(f10)) {
            return;
        }
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f7388a;
            if (i7 >= arrayList.size()) {
                return;
            }
            ((a) arrayList.get(i7)).b();
            i7++;
        }
    }

    public boolean h() {
        return false;
    }
}
