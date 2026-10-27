package androidx.lifecycle;

import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
class FullLifecycleObserverAdapter implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1568a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f1569b;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1570a;

        static {
            int[] iArr = new int[d.a.values().length];
            f1570a = iArr;
            try {
                iArr[d.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1570a[d.a.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1570a[d.a.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1570a[d.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1570a[d.a.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1570a[d.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1570a[d.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    FullLifecycleObserverAdapter(b bVar, e eVar) {
        this.f1568a = bVar;
        this.f1569b = eVar;
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, d.a aVar) {
        switch (a.f1570a[aVar.ordinal()]) {
            case 1:
                this.f1568a.c(gVar);
                break;
            case 2:
                this.f1568a.g(gVar);
                break;
            case 3:
                this.f1568a.a(gVar);
                break;
            case 4:
                this.f1568a.e(gVar);
                break;
            case 5:
                this.f1568a.f(gVar);
                break;
            case 6:
                this.f1568a.b(gVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        e eVar = this.f1569b;
        if (eVar != null) {
            eVar.d(gVar, aVar);
        }
    }
}
