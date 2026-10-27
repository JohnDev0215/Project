package a.b.o;

import a.h.k.s;
import a.h.k.t;
import a.h.k.u;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Interpolator f124c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public t f125d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f126e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f123b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u f127f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<s> f122a = new ArrayList<>();

    public class a extends u {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f128a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f129b = 0;

        public a() {
        }

        @Override // a.h.k.t
        public void a(View view) {
            int i = this.f129b + 1;
            this.f129b = i;
            if (i == g.this.f122a.size()) {
                t tVar = g.this.f125d;
                if (tVar != null) {
                    tVar.a(null);
                }
                this.f129b = 0;
                this.f128a = false;
                g.this.f126e = false;
            }
        }

        @Override // a.h.k.u, a.h.k.t
        public void b(View view) {
            if (this.f128a) {
                return;
            }
            this.f128a = true;
            t tVar = g.this.f125d;
            if (tVar != null) {
                tVar.b(null);
            }
        }
    }

    public void a() {
        if (this.f126e) {
            Iterator<s> it = this.f122a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f126e = false;
        }
    }

    public void b() {
        View view;
        if (this.f126e) {
            return;
        }
        for (s sVar : this.f122a) {
            long j = this.f123b;
            if (j >= 0) {
                sVar.c(j);
            }
            Interpolator interpolator = this.f124c;
            if (interpolator != null && (view = sVar.f835a.get()) != null) {
                view.animate().setInterpolator(interpolator);
            }
            if (this.f125d != null) {
                sVar.d(this.f127f);
            }
            View view2 = sVar.f835a.get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.f126e = true;
    }
}
