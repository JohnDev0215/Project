package m5;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import o5.AbstractC2047c;
import r5.C2298b;

/* JADX INFO: renamed from: m5.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1837n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f19233a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Field f19234b;
    public final String c;
    public final /* synthetic */ boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Method f19235e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ j5.y f19236f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ j5.y f19237g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ boolean f19238h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f19239i;

    public C1837n(String str, Field field, boolean z6, Method method, j5.y yVar, j5.y yVar2, boolean z10, boolean z11) {
        this.d = z6;
        this.f19235e = method;
        this.f19236f = yVar;
        this.f19237g = yVar2;
        this.f19238h = z10;
        this.f19239i = z11;
        this.f19233a = str;
        this.f19234b = field;
        this.c = field.getName();
    }

    public final void a(C2298b c2298b, Object obj) throws IllegalAccessException {
        Object objInvoke;
        Field field = this.f19234b;
        boolean z6 = this.d;
        Method method = this.f19235e;
        if (z6) {
            if (method == null) {
                C1841s.b(obj, field);
            } else {
                C1841s.b(obj, method);
            }
        }
        if (method != null) {
            try {
                objInvoke = method.invoke(obj, null);
            } catch (InvocationTargetException e10) {
                throw new j5.p(android.hardware.bydauto.ac.tsimpl.a.i("Accessor ", AbstractC2047c.d(method, false), " threw exception"), e10.getCause());
            }
        } else {
            objInvoke = field.get(obj);
        }
        if (objInvoke == obj) {
            return;
        }
        c2298b.o(this.f19233a);
        this.f19236f.b(c2298b, objInvoke);
    }
}
