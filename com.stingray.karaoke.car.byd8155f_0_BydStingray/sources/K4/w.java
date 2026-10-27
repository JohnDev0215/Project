package k4;

import java.util.ArrayList;
import java.util.HashSet;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final z f18217e = new z(10);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C1682A f18218f = new C1682A(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f18219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z f18220b;
    public final HashSet c;
    public final P d;

    public w(P p10) {
        z zVar = f18217e;
        this.f18219a = new ArrayList();
        this.c = new HashSet();
        this.d = p10;
        this.f18220b = zVar;
    }

    public final synchronized ArrayList a(Class cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (v vVar : this.f18219a) {
                if (!this.c.contains(vVar) && vVar.f18215a.isAssignableFrom(cls)) {
                    this.c.add(vVar);
                    arrayList.add(vVar.c.A(this));
                    this.c.remove(vVar);
                }
            }
        } finally {
        }
        return arrayList;
    }

    public final synchronized q b(Class cls, Class cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z6 = false;
            for (v vVar : this.f18219a) {
                if (this.c.contains(vVar)) {
                    z6 = true;
                } else if (vVar.f18215a.isAssignableFrom(cls) && vVar.f18216b.isAssignableFrom(cls2)) {
                    this.c.add(vVar);
                    arrayList.add(vVar.c.A(this));
                    this.c.remove(vVar);
                }
            }
            if (arrayList.size() > 1) {
                z zVar = this.f18220b;
                P p10 = this.d;
                zVar.getClass();
                return new C1684b(arrayList, 2, p10);
            }
            if (arrayList.size() == 1) {
                return (q) arrayList.get(0);
            }
            if (z6) {
                return f18218f;
            }
            throw new com.bumptech.glide.g("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        } catch (Throwable th) {
            this.c.clear();
            throw th;
        }
    }

    public final synchronized ArrayList c(Class cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (v vVar : this.f18219a) {
            if (!arrayList.contains(vVar.f18216b) && vVar.f18215a.isAssignableFrom(cls)) {
                arrayList.add(vVar.f18216b);
            }
        }
        return arrayList;
    }
}
