package K7;

import java.util.Arrays;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: K7.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0382f extends v0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f3785m;

    public C0382f(Class cls) {
        A7.m.f(cls, "jClass");
        Object[] declaredMethods = cls.getDeclaredMethods();
        A7.m.e(declaredMethods, "jClass.declaredMethods");
        E5.y yVar = new E5.y(1);
        if (declaredMethods.length != 0) {
            declaredMethods = Arrays.copyOf(declaredMethods, declaredMethods.length);
            A7.m.e(declaredMethods, "copyOf(...)");
            if (declaredMethods.length > 1) {
                Arrays.sort(declaredMethods, yVar);
            }
        }
        this.f3785m = AbstractC1996k.x(declaredMethods);
    }

    @Override // K7.v0
    public final String i() {
        return AbstractC1998m.f0(this.f3785m, "", "<init>(", ")V", C0377b.f3767s, 24);
    }
}
