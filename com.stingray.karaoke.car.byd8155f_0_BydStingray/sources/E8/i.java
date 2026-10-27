package E8;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public class i extends h implements m {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(l lVar, InterfaceC2772a interfaceC2772a) {
        super(lVar, interfaceC2772a);
        if (lVar != null) {
        } else {
            f(0);
            throw null;
        }
    }

    public static /* synthetic */ void f(int i7) {
        String str = i7 != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i7 != 2 ? 3 : 2];
        if (i7 == 1) {
            objArr[0] = "computable";
        } else if (i7 != 2) {
            objArr[0] = "storageManager";
        } else {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValue";
        }
        if (i7 != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValue";
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
        Object objA = super.a();
        if (objA != null) {
            return objA;
        }
        f(2);
        throw null;
    }
}
