package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.n.c;
import b.n.e;
import b.n.g;

/* JADX INFO: loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1010a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f1011b;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1012a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f1012a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1012a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1012a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1012a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1012a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1012a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1012a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public FullLifecycleObserverAdapter(c cVar, e eVar) {
        this.f1010a = cVar;
        this.f1011b = eVar;
    }

    @Override // b.n.e
    public void d(g gVar, Lifecycle.Event event) {
        switch (a.f1012a[event.ordinal()]) {
            case 1:
                this.f1010a.c(gVar);
                break;
            case 2:
                this.f1010a.g(gVar);
                break;
            case 3:
                this.f1010a.a(gVar);
                break;
            case 4:
                this.f1010a.e(gVar);
                break;
            case 5:
                this.f1010a.f(gVar);
                break;
            case 6:
                this.f1010a.b(gVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        e eVar = this.f1011b;
        if (eVar != null) {
            eVar.d(gVar, event);
        }
    }
}
