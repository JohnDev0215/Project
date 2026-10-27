package E8;

import java.util.concurrent.ConcurrentHashMap;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class e extends j {
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(l lVar, ConcurrentHashMap concurrentHashMap, InterfaceC2782k interfaceC2782k, int i7) {
        super(lVar, concurrentHashMap, interfaceC2782k);
        this.o = i7;
    }

    public static /* synthetic */ void f(int i7) {
        String str = i7 != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i7 != 3 ? 3 : 2];
        if (i7 == 1) {
            objArr[0] = "map";
        } else if (i7 == 2) {
            objArr[0] = "computation";
        } else if (i7 != 3) {
            objArr[0] = "storageManager";
        } else {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction";
        }
        if (i7 != 3) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction";
        } else {
            objArr[1] = "computeIfAbsent";
        }
        if (i7 == 2) {
            objArr[2] = "computeIfAbsent";
        } else if (i7 != 3) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 == 3) {
            throw new IllegalStateException(str2);
        }
    }

    @Override // E8.j, z7.InterfaceC2782k
    public Object b(Object obj) throws Throwable {
        switch (this.o) {
            case 1:
                Object objB = super.b(obj);
                if (objB != null) {
                    return objB;
                }
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunctionToNotNull", "invoke"));
            default:
                return super.b(obj);
        }
    }
}
