package A7;

import H7.InterfaceC0317b;
import H7.InterfaceC0320e;
import java.io.Serializable;

/* JADX INFO: renamed from: A7.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0056d implements InterfaceC0317b, Serializable {
    public transient InterfaceC0317b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f455m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Class f456n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f457p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f458q;

    public AbstractC0056d(Object obj, Class cls, String str, String str2, boolean z6) {
        this.f455m = obj;
        this.f456n = cls;
        this.o = str;
        this.f457p = str2;
        this.f458q = z6;
    }

    public abstract InterfaceC0317b f();

    @Override // H7.InterfaceC0317b
    public String getName() {
        return this.o;
    }

    public InterfaceC0320e h() {
        Class cls = this.f456n;
        if (cls == null) {
            return null;
        }
        return this.f458q ? A.f443a.c(cls, "") : A.f443a.b(cls);
    }

    public String i() {
        return this.f457p;
    }
}
