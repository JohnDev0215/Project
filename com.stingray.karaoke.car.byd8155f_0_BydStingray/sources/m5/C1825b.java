package m5;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1825b extends j5.y {
    public static final C1824a c = new C1824a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f19192a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1843u f19193b;

    public C1825b(j5.m mVar, j5.y yVar, Class cls) {
        this.f19193b = new C1843u(mVar, yVar, cls);
        this.f19192a = cls;
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        c2297a.b();
        while (c2297a.v()) {
            arrayList.add(((j5.y) this.f19193b.c).a(c2297a));
        }
        c2297a.k();
        int size = arrayList.size();
        Class cls = this.f19192a;
        if (!cls.isPrimitive()) {
            return arrayList.toArray((Object[]) Array.newInstance((Class<?>) cls, size));
        }
        Object objNewInstance = Array.newInstance((Class<?>) cls, size);
        for (int i7 = 0; i7 < size; i7++) {
            Array.set(objNewInstance, i7, arrayList.get(i7));
        }
        return objNewInstance;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        if (obj == null) {
            c2298b.v();
            return;
        }
        c2298b.g();
        int length = Array.getLength(obj);
        for (int i7 = 0; i7 < length; i7++) {
            this.f19193b.b(c2298b, Array.get(obj, i7));
        }
        c2298b.k();
    }
}
