package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import defpackage.ac;
import defpackage.cc;
import defpackage.ec;

/* JADX INFO: loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements cc {
    public final ac a;
    public final cc b;

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public FullLifecycleObserverAdapter(ac acVar, cc ccVar) {
        this.a = acVar;
        this.b = ccVar;
    }

    @Override // defpackage.cc
    public void d(ec ecVar, Lifecycle.Event event) {
        switch (a.a[event.ordinal()]) {
            case 1:
                this.a.c(ecVar);
                break;
            case 2:
                this.a.g(ecVar);
                break;
            case 3:
                this.a.a(ecVar);
                break;
            case 4:
                this.a.e(ecVar);
                break;
            case 5:
                this.a.f(ecVar);
                break;
            case 6:
                this.a.b(ecVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        cc ccVar = this.b;
        if (ccVar != null) {
            ccVar.d(ecVar, event);
        }
    }
}
