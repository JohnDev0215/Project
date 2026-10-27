package androidx.navigation;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.a0;
import androidx.lifecycle.b0;
import androidx.lifecycle.d0;
import androidx.lifecycle.e0;
import androidx.lifecycle.i;
import androidx.lifecycle.y;
import androidx.lifecycle.z;
import androidx.savedstate.SavedStateRegistry;
import java.util.UUID;

/* JADX INFO: compiled from: NavBackStackEntry.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements androidx.lifecycle.o, e0, androidx.lifecycle.h, androidx.savedstate.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Context f2645e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final j f2646g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Bundle f2647h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final androidx.lifecycle.p f2648i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final androidx.savedstate.b f2649j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final UUID f2650k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private i.c f2651l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private i.c f2652m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private f f2653n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private b0.b f2654o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private y f2655p;

    /* JADX INFO: compiled from: NavBackStackEntry.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2656a;

        static {
            int[] iArr = new int[i.b.values().length];
            f2656a = iArr;
            try {
                iArr[i.b.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2656a[i.b.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2656a[i.b.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2656a[i.b.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2656a[i.b.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2656a[i.b.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2656a[i.b.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: NavBackStackEntry.java */
    private static class b extends androidx.lifecycle.a {
        b(androidx.savedstate.c cVar, Bundle bundle) {
            super(cVar, bundle);
        }

        @Override // androidx.lifecycle.a
        protected <T extends a0> T d(String str, Class<T> cls, y yVar) {
            return new c(yVar);
        }
    }

    /* JADX INFO: compiled from: NavBackStackEntry.java */
    private static class c extends a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private y f2657a;

        c(y yVar) {
            this.f2657a = yVar;
        }

        public y h() {
            return this.f2657a;
        }
    }

    e(Context context, j jVar, Bundle bundle, androidx.lifecycle.o oVar, f fVar) {
        this(context, jVar, bundle, oVar, fVar, UUID.randomUUID(), null);
    }

    private static i.c h(i.b bVar) {
        switch (a.f2656a[bVar.ordinal()]) {
            case 1:
            case 2:
                return i.c.CREATED;
            case 3:
            case 4:
                return i.c.STARTED;
            case 5:
                return i.c.RESUMED;
            case 6:
                return i.c.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + bVar);
        }
    }

    @Override // androidx.lifecycle.o
    public androidx.lifecycle.i a() {
        return this.f2648i;
    }

    public Bundle b() {
        return this.f2647h;
    }

    @Override // androidx.savedstate.c
    public SavedStateRegistry d() {
        return this.f2649j.b();
    }

    public j e() {
        return this.f2646g;
    }

    i.c f() {
        return this.f2652m;
    }

    public y g() {
        if (this.f2655p == null) {
            this.f2655p = ((c) new b0(this, new b(this, null)).a(c.class)).h();
        }
        return this.f2655p;
    }

    void i(i.b bVar) {
        this.f2651l = h(bVar);
        o();
    }

    void j(Bundle bundle) {
        this.f2647h = bundle;
    }

    @Override // androidx.lifecycle.e0
    public d0 k() {
        f fVar = this.f2653n;
        if (fVar != null) {
            return fVar.j(this.f2650k);
        }
        throw new IllegalStateException("You must call setViewModelStore() on your NavHostController before accessing the ViewModelStore of a navigation graph.");
    }

    void l(Bundle bundle) {
        this.f2649j.d(bundle);
    }

    @Override // androidx.lifecycle.h
    public b0.b m() {
        if (this.f2654o == null) {
            this.f2654o = new z((Application) this.f2645e.getApplicationContext(), this, this.f2647h);
        }
        return this.f2654o;
    }

    void n(i.c cVar) {
        this.f2652m = cVar;
        o();
    }

    void o() {
        if (this.f2651l.ordinal() < this.f2652m.ordinal()) {
            this.f2648i.o(this.f2651l);
        } else {
            this.f2648i.o(this.f2652m);
        }
    }

    e(Context context, j jVar, Bundle bundle, androidx.lifecycle.o oVar, f fVar, UUID uuid, Bundle bundle2) {
        this.f2648i = new androidx.lifecycle.p(this);
        androidx.savedstate.b bVarA = androidx.savedstate.b.a(this);
        this.f2649j = bVarA;
        this.f2651l = i.c.CREATED;
        this.f2652m = i.c.RESUMED;
        this.f2645e = context;
        this.f2650k = uuid;
        this.f2646g = jVar;
        this.f2647h = bundle;
        this.f2653n = fVar;
        bVarA.c(bundle2);
        if (oVar != null) {
            this.f2651l = oVar.a().b();
        }
    }
}
