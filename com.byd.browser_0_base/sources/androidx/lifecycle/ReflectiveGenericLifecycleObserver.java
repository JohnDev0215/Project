package androidx.lifecycle;

import androidx.lifecycle.a;
import androidx.lifecycle.d;
import defpackage.b71;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class ReflectiveGenericLifecycleObserver implements f {
    public final Object e;
    public final a.C0010a f;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.e = obj;
        this.f = a.c.b(obj.getClass());
    }

    @Override // androidx.lifecycle.f
    public final void a(b71 b71Var, d.a aVar) {
        HashMap map = this.f.a;
        List list = (List) map.get(aVar);
        Object obj = this.e;
        a.C0010a.a(list, b71Var, aVar, obj);
        a.C0010a.a((List) map.get(d.a.ON_ANY), b71Var, aVar, obj);
    }
}
