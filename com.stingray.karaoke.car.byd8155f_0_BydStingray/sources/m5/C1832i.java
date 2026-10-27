package m5;

import j5.C1553k;
import java.util.concurrent.ConcurrentHashMap;
import k5.InterfaceC1687a;
import l5.AbstractC1776d;
import l5.C1778f;
import q5.C2185a;

/* JADX INFO: renamed from: m5.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1832i implements j5.z {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1831h f19224n;
    public final C1778f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ConcurrentHashMap f19225m = new ConcurrentHashMap();

    static {
        int i7 = 0;
        f19224n = new C1831h(i7);
        new C1831h(i7);
    }

    public C1832i(C1778f c1778f) {
        this.l = c1778f;
    }

    @Override // j5.z
    public final j5.y a(j5.m mVar, C2185a c2185a) {
        InterfaceC1687a interfaceC1687a = (InterfaceC1687a) c2185a.f21253a.getAnnotation(InterfaceC1687a.class);
        if (interfaceC1687a == null) {
            return null;
        }
        return b(this.l, mVar, c2185a, interfaceC1687a, true);
    }

    public final j5.y b(C1778f c1778f, j5.m mVar, C2185a c2185a, InterfaceC1687a interfaceC1687a, boolean z6) {
        j5.y yVarA;
        j5.z zVar;
        Object objH = c1778f.h(new C2185a(interfaceC1687a.value())).H();
        boolean zNullSafe = interfaceC1687a.nullSafe();
        if (objH instanceof j5.y) {
            yVarA = (j5.y) objH;
        } else {
            if (!(objH instanceof j5.z)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objH.getClass().getName() + " as a @JsonAdapter for " + AbstractC1776d.k(c2185a.f21254b) + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            j5.z zVar2 = (j5.z) objH;
            if (z6 && (zVar = (j5.z) this.f19225m.putIfAbsent(c2185a.f21253a, zVar2)) != null) {
                zVar2 = zVar;
            }
            yVarA = zVar2.a(mVar, c2185a);
        }
        return (yVarA == null || !zNullSafe) ? yVarA : new C1553k(yVarA, 2);
    }
}
