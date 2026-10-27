package g2;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: EnumCreatorDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f5030a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class f5031b;

    public g(Method method) {
        this.f5030a = method;
        this.f5031b = method.getParameterTypes()[0];
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        try {
            return (T) this.f5030a.invoke(null, aVar.d0(this.f5031b));
        } catch (IllegalAccessException e8) {
            throw new JSONException("parse enum error", e8);
        } catch (InvocationTargetException e9) {
            throw new JSONException("parse enum error", e9);
        }
    }

    @Override // g2.t
    public int e() {
        return 0;
    }
}
