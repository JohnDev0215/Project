package E8;

import A.C0050y0;
import A.F;
import A.I0;
import F8.C0271f;

/* JADX INFO: loaded from: classes.dex */
public final class d extends h implements m {
    public volatile io.sentry.internal.debugmeta.c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ F f1850p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(l lVar, I0 i02, F f10) {
        super(lVar, i02);
        this.f1850p = f10;
        if (lVar == null) {
            j(0);
            throw null;
        }
        this.o = null;
    }

    public static /* synthetic */ void f(int i7) {
        String str = i7 != 2 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[i7 != 2 ? 2 : 3];
        if (i7 != 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5";
        } else {
            objArr[0] = "value";
        }
        if (i7 != 2) {
            objArr[1] = "recursionDetected";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5";
        }
        if (i7 == 2) {
            objArr[2] = "doPostCompute";
        }
        String str2 = String.format(str, objArr);
        if (i7 == 2) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static /* synthetic */ void j(int i7) {
        String str = i7 != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i7 != 2 ? 3 : 2];
        if (i7 == 1) {
            objArr[0] = "computable";
        } else if (i7 != 2) {
            objArr[0] = "storageManager";
        } else {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute";
        }
        if (i7 != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute";
        } else {
            objArr[1] = "invoke";
        }
        if (i7 != 2) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 == 2) {
            throw new IllegalStateException(str2);
        }
    }

    @Override // E8.h, z7.InterfaceC2772a
    public final Object a() throws Throwable {
        Object objA;
        io.sentry.internal.debugmeta.c cVar = this.o;
        if (cVar == null || ((Thread) cVar.f16570n) != Thread.currentThread()) {
            objA = super.a();
        } else {
            if (((Thread) cVar.f16570n) != Thread.currentThread()) {
                throw new IllegalStateException("No value in this thread (hasValue should be checked before)");
            }
            objA = cVar.f16569m;
        }
        if (objA != null) {
            return objA;
        }
        j(2);
        throw null;
    }

    @Override // E8.h
    public final void h(Object obj) {
        this.o = new io.sentry.internal.debugmeta.c(obj);
        try {
            if (obj != null) {
                this.f1850p.b(obj);
            } else {
                f(2);
                throw null;
            }
        } finally {
            this.o = null;
        }
    }

    @Override // E8.h
    public final C0050y0 i(boolean z6) {
        return new C0050y0(2, C0271f.f2464n.b(Boolean.valueOf(z6)), false);
    }
}
