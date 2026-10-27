package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import x0.b;
import x0.d;
import x0.f;

/* JADX INFO: loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f1434d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f1435e;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1436a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f1436a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1436a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1436a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1436a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1436a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1436a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1436a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public FullLifecycleObserverAdapter(b bVar, d dVar) {
        this.f1434d = bVar;
        this.f1435e = dVar;
    }

    @Override // x0.d
    public void onStateChanged(f fVar, Lifecycle.Event event) {
        switch (a.f1436a[event.ordinal()]) {
            case 1:
                this.f1434d.b(fVar);
                break;
            case 2:
                this.f1434d.onStart(fVar);
                break;
            case 3:
                this.f1434d.a(fVar);
                break;
            case 4:
                this.f1434d.c(fVar);
                break;
            case 5:
                this.f1434d.onStop(fVar);
                break;
            case 6:
                this.f1434d.onDestroy(fVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        d dVar = this.f1435e;
        if (dVar != null) {
            dVar.onStateChanged(fVar, event);
        }
    }
}
