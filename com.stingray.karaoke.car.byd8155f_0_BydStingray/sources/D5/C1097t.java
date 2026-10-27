package d5;

import a.AbstractC0904a;
import java.util.Map;

/* JADX INFO: renamed from: d5.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1097t extends AbstractC1091m {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13454m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1098u f13455n;

    public C1097t(C1098u c1098u, int i7) {
        this.f13455n = c1098u;
        Object obj = C1098u.f13456u;
        this.l = c1098u.j()[i7];
        this.f13454m = i7;
    }

    public final void a() {
        int i7 = this.f13454m;
        Object obj = this.l;
        C1098u c1098u = this.f13455n;
        if (i7 != -1 && i7 < c1098u.size()) {
            if (AbstractC0904a.A(obj, c1098u.j()[this.f13454m])) {
                return;
            }
        }
        Object obj2 = C1098u.f13456u;
        this.f13454m = c1098u.e(obj);
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.l;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        C1098u c1098u = this.f13455n;
        Map mapC = c1098u.c();
        if (mapC != null) {
            return mapC.get(this.l);
        }
        a();
        int i7 = this.f13454m;
        if (i7 == -1) {
            return null;
        }
        return c1098u.k()[i7];
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        C1098u c1098u = this.f13455n;
        Map mapC = c1098u.c();
        Object obj2 = this.l;
        if (mapC != null) {
            return mapC.put(obj2, obj);
        }
        a();
        int i7 = this.f13454m;
        if (i7 == -1) {
            c1098u.put(obj2, obj);
            return null;
        }
        Object obj3 = c1098u.k()[i7];
        c1098u.k()[this.f13454m] = obj;
        return obj3;
    }
}
