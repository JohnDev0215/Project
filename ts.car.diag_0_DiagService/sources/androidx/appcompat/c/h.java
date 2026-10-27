package androidx.appcompat.c;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.annotation.RestrictTo;
import androidx.core.view.v;
import androidx.core.view.w;
import androidx.core.view.x;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f184c;
    w d;
    private boolean e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f183b = -1;
    private final x f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<v> f182a = new ArrayList<>();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f185a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f186b = 0;

        a() {
        }

        @Override // androidx.core.view.w
        public void a(View view) {
            int i = this.f186b + 1;
            this.f186b = i;
            if (i == h.this.f182a.size()) {
                w wVar = h.this.d;
                if (wVar != null) {
                    wVar.a(null);
                }
                d();
            }
        }

        @Override // androidx.core.view.x, androidx.core.view.w
        public void b(View view) {
            if (this.f185a) {
                return;
            }
            this.f185a = true;
            w wVar = h.this.d;
            if (wVar != null) {
                wVar.b(null);
            }
        }

        void d() {
            this.f186b = 0;
            this.f185a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.e) {
            Iterator<v> it = this.f182a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.e = false;
        }
    }

    void b() {
        this.e = false;
    }

    public h c(v vVar) {
        if (!this.e) {
            this.f182a.add(vVar);
        }
        return this;
    }

    public h d(v vVar, v vVar2) {
        this.f182a.add(vVar);
        vVar2.h(vVar.c());
        this.f182a.add(vVar2);
        return this;
    }

    public h e(long j) {
        if (!this.e) {
            this.f183b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.e) {
            this.f184c = interpolator;
        }
        return this;
    }

    public h g(w wVar) {
        if (!this.e) {
            this.d = wVar;
        }
        return this;
    }

    public void h() {
        if (this.e) {
            return;
        }
        for (v vVar : this.f182a) {
            long j = this.f183b;
            if (j >= 0) {
                vVar.d(j);
            }
            Interpolator interpolator = this.f184c;
            if (interpolator != null) {
                vVar.e(interpolator);
            }
            if (this.d != null) {
                vVar.f(this.f);
            }
            vVar.j();
        }
        this.e = true;
    }
}
