package b.a.a.j.j;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: EnumCreatorDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f276a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class f277b;

    public g(Method method) {
        this.f276a = method;
        this.f277b = method.getParameterTypes()[0];
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        try {
            return (T) this.f276a.invoke(null, aVar.N(this.f277b));
        } catch (IllegalAccessException e2) {
            throw new JSONException("parse enum error", e2);
        } catch (InvocationTargetException e3) {
            throw new JSONException("parse enum error", e3);
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 0;
    }
}
