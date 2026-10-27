package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: compiled from: AnnotationSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile Class f348a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f349b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile Method f350c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile Method f351d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static d f352e = new d();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        JSONException jSONException;
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        if (interfaces.length == 1 && interfaces[0].isAnnotation()) {
            Class<?> cls = interfaces[0];
            if (f348a == null && !f349b) {
                try {
                    f348a = Class.forName("sun.reflect.annotation.AnnotationType");
                } finally {
                }
            }
            if (f348a == null) {
                throw new JSONException("not support Type Annotation.");
            }
            if (f350c == null && !f349b) {
                try {
                    f350c = f348a.getMethod("getInstance", Class.class);
                } finally {
                }
            }
            if (f351d == null && !f349b) {
                try {
                    f351d = f348a.getMethod("members", new Class[0]);
                } finally {
                }
            }
            if (f350c == null || f349b) {
                throw new JSONException("not support Type Annotation.");
            }
            try {
                Object[] objArr = {cls};
                Object objInvoke = null;
                try {
                    Map map = (Map) f351d.invoke(f350c.invoke(null, objArr), new Object[0]);
                    JSONObject jSONObject = new JSONObject(map.size());
                    for (Map.Entry entry : map.entrySet()) {
                        try {
                            objInvoke = ((Method) entry.getValue()).invoke(obj, new Object[0]);
                        } catch (IllegalAccessException | InvocationTargetException unused) {
                        }
                        jSONObject.put((String) entry.getKey(), b.a.a.a.toJSON(objInvoke));
                    }
                    i0Var.H(jSONObject);
                } finally {
                }
            } finally {
            }
        }
    }
}
