package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
class FullLifecycleObserverAdapter implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1083a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f1084b;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1085a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f1085a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1085a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1085a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1085a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1085a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1085a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1085a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    FullLifecycleObserverAdapter(b bVar, d dVar) {
        this.f1083a = bVar;
        this.f1084b = dVar;
    }

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        switch (a.f1085a[event.ordinal()]) {
            case 1:
                this.f1083a.c(fVar);
                break;
            case 2:
                this.f1083a.g(fVar);
                break;
            case 3:
                this.f1083a.a(fVar);
                break;
            case 4:
                this.f1083a.e(fVar);
                break;
            case 5:
                this.f1083a.f(fVar);
                break;
            case 6:
                this.f1083a.b(fVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        d dVar = this.f1084b;
        if (dVar != null) {
            dVar.d(fVar, event);
        }
    }
}
