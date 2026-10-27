package spi;

import java.io.IOException;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import spi.re;

/* JADX INFO: loaded from: classes2.dex */
public final class ue extends ts {
    public final /* synthetic */ re.e e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ jp g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ue(String str, boolean z, String str2, boolean z2, re.e eVar, boolean z3, jp jpVar) {
        super(str2, z2);
        this.e = eVar;
        this.f = z3;
        this.g = jpVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v23, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, spi.cf>] */
    /* JADX WARN: Type inference failed for: r3v1, types: [T, spi.jp] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    @Override // spi.ts
    public final long a() throws Throwable {
        df dfVar;
        re reVar;
        re reVar2;
        ?? r3;
        T t;
        re.e eVar = this.e;
        boolean z = this.f;
        jp settings = this.g;
        Objects.requireNonNull(eVar);
        Intrinsics.checkNotNullParameter(settings, "settings");
        Ref.LongRef longRef = new Ref.LongRef();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        df dfVar2 = eVar.b.y;
        synchronized (dfVar2) {
            try {
                reVar = eVar.b;
            } catch (Throwable th) {
                th = th;
                dfVar = dfVar2;
            }
            try {
                synchronized (reVar) {
                    try {
                        jp jpVar = eVar.b.s;
                        if (z) {
                            r3 = settings;
                        } else {
                            jp jpVar2 = new jp();
                            jpVar2.b(jpVar);
                            jpVar2.b(settings);
                            Unit unit = Unit.INSTANCE;
                            r3 = jpVar2;
                        }
                        objectRef2.element = r3;
                        long jA = ((long) r3.a()) - ((long) jpVar.a());
                        longRef.element = jA;
                        if (jA == 0 || eVar.b.c.isEmpty()) {
                            t = 0;
                        } else {
                            Object[] array = eVar.b.c.values().toArray(new cf[0]);
                            if (array == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            t = (cf[]) array;
                        }
                        objectRef.element = t;
                        re reVar3 = eVar.b;
                        jp jpVar3 = (jp) objectRef2.element;
                        Objects.requireNonNull(reVar3);
                        Intrinsics.checkNotNullParameter(jpVar3, "<set-?>");
                        reVar3.s = jpVar3;
                        vs vsVar = eVar.b.k;
                        String str = eVar.b.d + " onSettings";
                        reVar2 = reVar;
                        dfVar = dfVar2;
                        try {
                            vsVar.c(new se(str, true, str, true, eVar, objectRef2, z, settings, longRef, objectRef), 0L);
                            Unit unit2 = Unit.INSTANCE;
                            try {
                                eVar.b.y.e((jp) objectRef2.element);
                            } catch (IOException e) {
                                re.e(eVar.b, e);
                            }
                            Unit unit3 = Unit.INSTANCE;
                            cf[] cfVarArr = (cf[]) objectRef.element;
                            if (cfVarArr == null) {
                                return -1L;
                            }
                            Intrinsics.checkNotNull(cfVarArr);
                            for (cf cfVar : cfVarArr) {
                                synchronized (cfVar) {
                                    long j = longRef.element;
                                    cfVar.d += j;
                                    if (j > 0) {
                                        cfVar.notifyAll();
                                    }
                                    Unit unit4 = Unit.INSTANCE;
                                }
                            }
                            return -1L;
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        reVar2 = reVar;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                throw th;
            }
        }
    }
}
