package p;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBase;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class vs {
    public static final Iterator a = Collections.emptyIterator();

    public static String a(Object obj) {
        if (obj == null) {
            return "[null]";
        }
        return g(obj instanceof Class ? (Class) obj : obj.getClass());
    }

    public static String b(Throwable th) {
        return th instanceof JacksonException ? ((JacksonException) th).b() : (!(th instanceof InvocationTargetException) || th.getCause() == null) ? th.getMessage() : th.getCause().getMessage();
    }

    public static String c(Object obj) {
        return g(obj instanceof Class ? (Class) obj : obj.getClass());
    }

    public static String d(JavaType javaType) {
        if (javaType == null) {
            return "[null]";
        }
        StringBuilder sb = new StringBuilder(80);
        sb.append('`');
        sb.append(((TypeBase) javaType).l());
        sb.append('`');
        return sb.toString();
    }

    public static boolean e(Class cls, Object obj) {
        return obj != null && obj.getClass() == cls;
    }

    public static boolean f(Object obj) {
        return obj == null || obj.getClass().getAnnotation(n61.class) != null;
    }

    public static String g(Class cls) {
        if (cls == null) {
            return "[null]";
        }
        int i = 0;
        while (cls.isArray()) {
            i++;
            cls = cls.getComponentType();
        }
        String simpleName = cls.isPrimitive() ? cls.getSimpleName() : cls.getName();
        if (i > 0) {
            StringBuilder sb = new StringBuilder(simpleName);
            do {
                sb.append("[]");
                i--;
            } while (i > 0);
            simpleName = sb.toString();
        }
        if (simpleName == null) {
            return "[null]";
        }
        StringBuilder sb2 = new StringBuilder(simpleName.length() + 2);
        sb2.append('`');
        sb2.append(simpleName);
        sb2.append('`');
        return sb2.toString();
    }
}
