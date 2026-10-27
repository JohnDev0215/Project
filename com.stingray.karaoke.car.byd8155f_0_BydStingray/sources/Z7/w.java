package Z7;

import o8.C2057b;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public abstract class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2058c f10961a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2057b f10962b;

    static {
        C2058c c2058c = new C2058c("kotlin.jvm.JvmField");
        f10961a = c2058c;
        C2057b.j(c2058c);
        C2057b.j(new C2058c("kotlin.reflect.jvm.internal.ReflectionFactoryImpl"));
        f10962b = C2057b.e("kotlin/jvm/internal/RepeatableContainer", false);
    }

    public static final String a(String str) {
        A7.m.f(str, "propertyName");
        if (c(str)) {
            return str;
        }
        return "get" + io.sentry.config.a.M(str);
    }

    public static final String b(String str) {
        String strM;
        StringBuilder sb = new StringBuilder("set");
        if (c(str)) {
            strM = str.substring(2);
            A7.m.e(strM, "this as java.lang.String).substring(startIndex)");
        } else {
            strM = io.sentry.config.a.M(str);
        }
        sb.append(strM);
        return sb.toString();
    }

    public static final boolean c(String str) {
        A7.m.f(str, "name");
        if (!Q8.o.z0(str, "is", false) || str.length() == 2) {
            return false;
        }
        char cCharAt = str.charAt(2);
        return A7.m.g(97, cCharAt) > 0 || A7.m.g(cCharAt, 122) > 0;
    }
}
