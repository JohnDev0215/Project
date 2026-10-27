package androidx.lifecycle;

import java.util.HashMap;
import java.util.List;
import p.p91;
import p.s91;
import p.ws;
import p.ys;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class ReflectiveGenericLifecycleObserver implements p91 {
    public final Object c;
    public final ws e;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.c = obj;
        ys ysVar = ys.c;
        Class<?> cls = obj.getClass();
        ws wsVar = (ws) ysVar.a.get(cls);
        this.e = wsVar == null ? ysVar.a(cls, null) : wsVar;
    }

    @Override // p.p91
    public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        HashMap map = this.e.a;
        List list = (List) map.get(lifecycle$Event);
        Object obj = this.c;
        ws.a(list, s91Var, lifecycle$Event, obj);
        ws.a((List) map.get(Lifecycle$Event.ON_ANY), s91Var, lifecycle$Event, obj);
    }
}
