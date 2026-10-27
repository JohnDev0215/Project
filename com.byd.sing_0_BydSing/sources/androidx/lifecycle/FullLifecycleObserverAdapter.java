package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
class FullLifecycleObserverAdapter implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1609a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f1610b;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1611a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f1611a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1611a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1611a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1611a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1611a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1611a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1611a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    FullLifecycleObserverAdapter(b bVar, d dVar) {
        this.f1609a = bVar;
        this.f1610b = dVar;
    }

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        switch (a.f1611a[event.ordinal()]) {
            case 1:
                this.f1609a.c(fVar);
                break;
            case 2:
                this.f1609a.f(fVar);
                break;
            case 3:
                this.f1609a.a(fVar);
                break;
            case 4:
                this.f1609a.e(fVar);
                break;
            case 5:
                this.f1609a.g(fVar);
                break;
            case 6:
                this.f1609a.b(fVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        d dVar = this.f1610b;
        if (dVar != null) {
            dVar.d(fVar, event);
        }
    }
}
