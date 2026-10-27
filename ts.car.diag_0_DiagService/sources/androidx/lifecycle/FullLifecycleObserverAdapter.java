package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class FullLifecycleObserverAdapter implements d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b f749c;
    private final d d;

    /* JADX INFO: compiled from: caradapter-bug */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f750a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f750a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f750a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f750a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f750a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f750a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f750a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f750a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    FullLifecycleObserverAdapter(b bVar, d dVar) {
        this.f749c = bVar;
        this.d = dVar;
    }

    @Override // androidx.lifecycle.d
    public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
        switch (a.f750a[event.ordinal()]) {
            case 1:
                this.f749c.e(fVar);
                break;
            case 2:
                this.f749c.j(fVar);
                break;
            case 3:
                this.f749c.b(fVar);
                break;
            case 4:
                this.f749c.h(fVar);
                break;
            case 5:
                this.f749c.i(fVar);
                break;
            case 6:
                this.f749c.d(fVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        d dVar = this.d;
        if (dVar != null) {
            dVar.f(fVar, event);
        }
    }
}
