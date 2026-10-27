package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import t0.b;
import t0.d;
import t0.f;

/* JADX INFO: loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1347a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f1348b;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1349a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f1349a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1349a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1349a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1349a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1349a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1349a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1349a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public FullLifecycleObserverAdapter(b bVar, d dVar) {
        this.f1347a = bVar;
        this.f1348b = dVar;
    }

    @Override // t0.d
    public void d(f fVar, Lifecycle.Event event) {
        switch (a.f1349a[event.ordinal()]) {
            case 1:
                this.f1347a.c(fVar);
                break;
            case 2:
                this.f1347a.g(fVar);
                break;
            case 3:
                this.f1347a.a(fVar);
                break;
            case 4:
                this.f1347a.e(fVar);
                break;
            case 5:
                this.f1347a.f(fVar);
                break;
            case 6:
                this.f1347a.b(fVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        d dVar = this.f1348b;
        if (dVar != null) {
            dVar.d(fVar, event);
        }
    }
}
