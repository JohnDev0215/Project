package androidx.lifecycle;

import androidx.lifecycle.d;
import defpackage.b71;
import defpackage.i21;
import defpackage.t70;

/* JADX INFO: compiled from: DefaultLifecycleObserverAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DefaultLifecycleObserverAdapter implements f {
    public final t70 e;
    public final f f;

    /* JADX INFO: compiled from: DefaultLifecycleObserverAdapter.kt */
    public /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[d.a.values().length];
            try {
                iArr[d.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[d.a.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[d.a.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[d.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[d.a.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[d.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[d.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            a = iArr;
        }
    }

    public DefaultLifecycleObserverAdapter(t70 t70Var, f fVar) {
        i21.e(t70Var, "defaultLifecycleObserver");
        this.e = t70Var;
        this.f = fVar;
    }

    @Override // androidx.lifecycle.f
    public final void a(b71 b71Var, d.a aVar) {
        int i = a.a[aVar.ordinal()];
        t70 t70Var = this.e;
        switch (i) {
            case 1:
                t70Var.getClass();
                break;
            case 2:
                t70Var.getClass();
                break;
            case 3:
                t70Var.g(b71Var);
                break;
            case 4:
                t70Var.getClass();
                break;
            case 5:
                t70Var.getClass();
                break;
            case 6:
                t70Var.f(b71Var);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        f fVar = this.f;
        if (fVar != null) {
            fVar.a(b71Var, aVar);
        }
    }
}
