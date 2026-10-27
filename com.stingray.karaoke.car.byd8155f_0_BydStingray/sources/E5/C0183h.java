package E5;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: E5.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0183h extends M {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f1810k;
    public final /* synthetic */ Class l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Executable f1811m;

    public /* synthetic */ C0183h(Executable executable, Class cls, int i7) {
        this.f1810k = i7;
        this.f1811m = executable;
        this.l = cls;
    }

    @Override // E5.M
    public final Object f() {
        switch (this.f1810k) {
            case 0:
                return ((Constructor) this.f1811m).newInstance(null);
            default:
                return ((Method) this.f1811m).invoke(null, this.l, Object.class);
        }
    }

    public final String toString() {
        switch (this.f1810k) {
        }
        return this.l.getName();
    }
}
