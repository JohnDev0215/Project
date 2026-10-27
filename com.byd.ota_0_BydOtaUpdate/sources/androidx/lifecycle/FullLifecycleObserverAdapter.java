package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.l.b;
import b.l.d;
import b.l.f;

/* JADX INFO: loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f522b;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f523a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f523a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f523a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f523a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f523a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f523a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f523a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f523a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public FullLifecycleObserverAdapter(b bVar, d dVar) {
        this.f521a = bVar;
        this.f522b = dVar;
    }

    @Override // b.l.d
    public void d(f fVar, Lifecycle.Event event) {
        switch (a.f523a[event.ordinal()]) {
            case 1:
                this.f521a.c(fVar);
                break;
            case 2:
                this.f521a.g(fVar);
                break;
            case 3:
                this.f521a.a(fVar);
                break;
            case 4:
                this.f521a.e(fVar);
                break;
            case 5:
                this.f521a.f(fVar);
                break;
            case 6:
                this.f521a.b(fVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        d dVar = this.f522b;
        if (dVar != null) {
            dVar.d(fVar, event);
        }
    }
}
