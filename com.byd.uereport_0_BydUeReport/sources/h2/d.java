package h2;

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
    public static volatile Class f5201a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f5202b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile Method f5203c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile Method f5204d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static d f5205e = new d();

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        JSONException jSONException;
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        if (interfaces.length == 1 && interfaces[0].isAnnotation()) {
            Class<?> cls = interfaces[0];
            if (f5201a == null && !f5202b) {
                try {
                    f5201a = Class.forName("sun.reflect.annotation.AnnotationType");
                } finally {
                }
            }
            if (f5201a == null) {
                throw new JSONException("not support Type Annotation.");
            }
            if (f5203c == null && !f5202b) {
                try {
                    f5203c = f5201a.getMethod("getInstance", Class.class);
                } finally {
                }
            }
            if (f5204d == null && !f5202b) {
                try {
                    f5204d = f5201a.getMethod("members", new Class[0]);
                } finally {
                }
            }
            if (f5203c == null || f5202b) {
                throw new JSONException("not support Type Annotation.");
            }
            try {
                Object[] objArr = {cls};
                Object objInvoke = null;
                try {
                    Map map = (Map) f5204d.invoke(f5203c.invoke(null, objArr), new Object[0]);
                    JSONObject jSONObject = new JSONObject(map.size());
                    for (Map.Entry entry : map.entrySet()) {
                        try {
                            objInvoke = ((Method) entry.getValue()).invoke(obj, new Object[0]);
                        } catch (IllegalAccessException | InvocationTargetException unused) {
                        }
                        jSONObject.put((String) entry.getKey(), c2.a.toJSON(objInvoke));
                    }
                    i0Var.H(jSONObject);
                } finally {
                }
            } finally {
            }
        }
    }
}
