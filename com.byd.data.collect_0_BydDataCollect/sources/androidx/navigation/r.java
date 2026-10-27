package androidx.navigation;

import android.annotation.SuppressLint;
import androidx.navigation.q;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: NavigatorProvider.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"TypeParameterUnusedInFormals"})
public class r {

    /* JADX INFO: renamed from: a */
    private static final HashMap<Class<?>, String> f1742a = new HashMap<>();

    /* JADX INFO: renamed from: b */
    private final HashMap<String, q<? extends i>> f1743b = new HashMap<>();

    static String c(Class<? extends q> cls) {
        HashMap<Class<?>, String> map = f1742a;
        String strValue = map.get(cls);
        if (strValue == null) {
            q.b bVar = (q.b) cls.getAnnotation(q.b.class);
            strValue = bVar != null ? bVar.value() : null;
            if (!g(strValue)) {
                throw new IllegalArgumentException("No @Navigator.Name annotation found for " + cls.getSimpleName());
            }
            map.put(cls, strValue);
        }
        return strValue;
    }

    private static boolean g(String str) {
        return (str == null || str.isEmpty()) ? false : true;
    }

    public final q<? extends i> a(q<? extends i> qVar) {
        return b(c(qVar.getClass()), qVar);
    }

    public q<? extends i> b(String str, q<? extends i> qVar) {
        if (g(str)) {
            return this.f1743b.put(str, qVar);
        }
        throw new IllegalArgumentException("navigator name cannot be an empty string");
    }

    public final <T extends q<?>> T d(Class<T> cls) {
        return (T) e(c(cls));
    }

    public <T extends q<?>> T e(String str) {
        if (!g(str)) {
            throw new IllegalArgumentException("navigator name cannot be an empty string");
        }
        q<? extends i> qVar = this.f1743b.get(str);
        if (qVar != null) {
            return qVar;
        }
        throw new IllegalStateException("Could not find Navigator with name \"" + str + "\". You must call NavController.addNavigator() for each navigation type.");
    }

    Map<String, q<? extends i>> f() {
        return this.f1743b;
    }
}
