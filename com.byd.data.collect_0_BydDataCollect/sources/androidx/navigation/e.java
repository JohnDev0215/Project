package androidx.navigation;

import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.d;
import androidx.savedstate.SavedStateRegistry;
import java.util.UUID;

/* JADX INFO: compiled from: NavBackStackEntry.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements androidx.lifecycle.g, androidx.lifecycle.s, androidx.savedstate.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f1658b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final i f1659c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Bundle f1660d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final androidx.lifecycle.h f1661e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final androidx.savedstate.a f1662f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final UUID f1663g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private d.b f1664h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private d.b f1665i;
    private f j;

    /* JADX INFO: compiled from: NavBackStackEntry.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1666a;

        static {
            int[] iArr = new int[d.a.values().length];
            f1666a = iArr;
            try {
                iArr[d.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1666a[d.a.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1666a[d.a.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1666a[d.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1666a[d.a.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1666a[d.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1666a[d.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    e(Context context, i iVar, Bundle bundle, androidx.lifecycle.g gVar, f fVar) {
        this(context, iVar, bundle, gVar, fVar, UUID.randomUUID(), null);
    }

    private static d.b g(d.a aVar) {
        switch (a.f1666a[aVar.ordinal()]) {
            case 1:
            case 2:
                return d.b.CREATED;
            case 3:
            case 4:
                return d.b.STARTED;
            case 5:
                return d.b.RESUMED;
            case 6:
                return d.b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    @Override // androidx.lifecycle.g
    public androidx.lifecycle.d a() {
        return this.f1661e;
    }

    public Bundle b() {
        return this.f1660d;
    }

    public i c() {
        return this.f1659c;
    }

    @Override // androidx.savedstate.b
    public SavedStateRegistry e() {
        return this.f1662f.b();
    }

    d.b f() {
        return this.f1665i;
    }

    void h(d.a aVar) {
        this.f1664h = g(aVar);
        m();
    }

    void i(Bundle bundle) {
        this.f1660d = bundle;
    }

    @Override // androidx.lifecycle.s
    public androidx.lifecycle.r j() {
        f fVar = this.j;
        if (fVar != null) {
            return fVar.g(this.f1663g);
        }
        throw new IllegalStateException("You must call setViewModelStore() on your NavHostController before accessing the ViewModelStore of a navigation graph.");
    }

    void k(Bundle bundle) {
        this.f1662f.d(bundle);
    }

    void l(d.b bVar) {
        this.f1665i = bVar;
        m();
    }

    void m() {
        if (this.f1664h.ordinal() < this.f1665i.ordinal()) {
            this.f1661e.p(this.f1664h);
        } else {
            this.f1661e.p(this.f1665i);
        }
    }

    e(Context context, i iVar, Bundle bundle, androidx.lifecycle.g gVar, f fVar, UUID uuid, Bundle bundle2) {
        this.f1661e = new androidx.lifecycle.h(this);
        androidx.savedstate.a aVarA = androidx.savedstate.a.a(this);
        this.f1662f = aVarA;
        this.f1664h = d.b.CREATED;
        this.f1665i = d.b.RESUMED;
        this.f1658b = context;
        this.f1663g = uuid;
        this.f1659c = iVar;
        this.f1660d = bundle;
        this.j = fVar;
        aVarA.c(bundle2);
        if (gVar != null) {
            this.f1664h = gVar.a().b();
        }
    }
}
