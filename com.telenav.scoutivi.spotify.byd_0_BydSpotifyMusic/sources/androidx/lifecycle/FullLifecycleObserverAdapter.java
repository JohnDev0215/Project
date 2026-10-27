package androidx.lifecycle;

import androidx.lifecycle.i;

/* JADX INFO: loaded from: classes.dex */
class FullLifecycleObserverAdapter implements m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final f f2494e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final m f2495g;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2496a;

        static {
            int[] iArr = new int[i.b.values().length];
            f2496a = iArr;
            try {
                iArr[i.b.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2496a[i.b.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2496a[i.b.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2496a[i.b.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2496a[i.b.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2496a[i.b.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2496a[i.b.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    FullLifecycleObserverAdapter(f fVar, m mVar) {
        this.f2494e = fVar;
        this.f2495g = mVar;
    }

    @Override // androidx.lifecycle.m
    public void d(o oVar, i.b bVar) {
        switch (a.f2496a[bVar.ordinal()]) {
            case 1:
                this.f2494e.c(oVar);
                break;
            case 2:
                this.f2494e.g(oVar);
                break;
            case 3:
                this.f2494e.a(oVar);
                break;
            case 4:
                this.f2494e.e(oVar);
                break;
            case 5:
                this.f2494e.f(oVar);
                break;
            case 6:
                this.f2494e.b(oVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        m mVar = this.f2495g;
        if (mVar != null) {
            mVar.d(oVar, bVar);
        }
    }
}
