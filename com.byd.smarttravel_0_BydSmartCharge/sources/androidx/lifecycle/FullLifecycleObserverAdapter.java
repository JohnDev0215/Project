package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.m.b;
import b.m.d;
import b.m.f;

/* JADX INFO: loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f398a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f399b;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f400a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f400a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f400a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f400a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f400a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f400a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f400a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f400a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public FullLifecycleObserverAdapter(b bVar, d dVar) {
        this.f398a = bVar;
        this.f399b = dVar;
    }

    @Override // b.m.d
    public void d(f fVar, Lifecycle.Event event) {
        switch (a.f400a[event.ordinal()]) {
            case 1:
                this.f398a.c(fVar);
                break;
            case 2:
                this.f398a.g(fVar);
                break;
            case 3:
                this.f398a.a(fVar);
                break;
            case 4:
                this.f398a.e(fVar);
                break;
            case 5:
                this.f398a.f(fVar);
                break;
            case 6:
                this.f398a.b(fVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        d dVar = this.f399b;
        if (dVar != null) {
            dVar.d(fVar, event);
        }
    }
}
