package E8;

import A.C0050y0;
import java.util.concurrent.ConcurrentHashMap;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public class j implements InterfaceC2782k {
    public final l l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ConcurrentHashMap f1855m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final InterfaceC2782k f1856n;

    public j(l lVar, ConcurrentHashMap concurrentHashMap, InterfaceC2782k interfaceC2782k) {
        if (lVar == null) {
            f(0);
            throw null;
        }
        this.l = lVar;
        this.f1855m = concurrentHashMap;
        this.f1856n = interfaceC2782k;
    }

    public static /* synthetic */ void f(int i7) {
        String str = (i7 == 3 || i7 == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 3 || i7 == 4) ? 2 : 3];
        if (i7 == 1) {
            objArr[0] = "map";
        } else if (i7 == 2) {
            objArr[0] = "compute";
        } else if (i7 == 3 || i7 == 4) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
        } else {
            objArr[0] = "storageManager";
        }
        if (i7 == 3) {
            objArr[1] = "recursionDetected";
        } else if (i7 != 4) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
        } else {
            objArr[1] = "raceCondition";
        }
        if (i7 != 3 && i7 != 4) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 != 3 && i7 != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // z7.InterfaceC2782k
    public Object b(Object obj) throws Throwable {
        ConcurrentHashMap concurrentHashMap = this.f1855m;
        Object obj2 = concurrentHashMap.get(obj);
        k kVar = k.f1857m;
        Object obj3 = O8.l.f5197a;
        if (obj2 != null && obj2 != kVar) {
            O8.l.j(obj2);
            if (obj2 == obj3) {
                return null;
            }
            return obj2;
        }
        l lVar = this.l;
        n nVar = lVar.f1860a;
        n nVar2 = lVar.f1860a;
        nVar.lock();
        try {
            Object obj4 = concurrentHashMap.get(obj);
            k kVar2 = k.f1858n;
            if (obj4 == kVar) {
                C0050y0 c0050y0D = lVar.d(obj, "");
                if (c0050y0D == null) {
                    f(3);
                    throw null;
                }
                if (!c0050y0D.f300m) {
                    Object obj5 = c0050y0D.f301n;
                    nVar2.unlock();
                    return obj5;
                }
                obj4 = kVar2;
            }
            if (obj4 == kVar2) {
                C0050y0 c0050y0D2 = lVar.d(obj, "");
                if (c0050y0D2 == null) {
                    f(3);
                    throw null;
                }
                if (!c0050y0D2.f300m) {
                    Object obj6 = c0050y0D2.f301n;
                    nVar2.unlock();
                    return obj6;
                }
            }
            if (obj4 != null) {
                O8.l.j(obj4);
                assertionErrorH = obj4 != obj3 ? obj4 : null;
                nVar2.unlock();
                return assertionErrorH;
            }
            try {
                concurrentHashMap.put(obj, kVar);
                Object objB = this.f1856n.b(obj);
                if (objB != null) {
                    obj3 = objB;
                }
                Object objPut = concurrentHashMap.put(obj, obj3);
                if (objPut == kVar) {
                    nVar2.unlock();
                    return objB;
                }
                assertionErrorH = h(obj, objPut);
                throw assertionErrorH;
            } catch (Throwable th) {
                if (O8.l.h(th)) {
                    concurrentHashMap.remove(obj);
                    throw th;
                }
                a aVar = lVar.f1861b;
                if (th == assertionErrorH) {
                    aVar.getClass();
                    throw th;
                }
                Object objPut2 = concurrentHashMap.put(obj, new O8.k(th));
                if (objPut2 != kVar) {
                    throw h(obj, objPut2);
                }
                aVar.getClass();
                throw th;
            }
        } catch (Throwable th2) {
            nVar2.unlock();
            throw th2;
        }
    }

    public final AssertionError h(Object obj, Object obj2) {
        AssertionError assertionError = new AssertionError("Race condition detected on input " + obj + ". Old value is " + obj2 + " under " + this.l);
        l.e(assertionError);
        return assertionError;
    }
}
