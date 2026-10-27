package m5;

import q5.C2185a;

/* JADX INFO: loaded from: classes.dex */
public class P implements j5.z {
    @Override // j5.z
    public final j5.y a(j5.m mVar, C2185a c2185a) {
        Class superclass = c2185a.f21253a;
        if (!Enum.class.isAssignableFrom(superclass) || superclass == Enum.class) {
            return null;
        }
        if (!superclass.isEnum()) {
            superclass = superclass.getSuperclass();
        }
        return new C1843u(superclass);
    }
}
