package defpackage;

import android.os.Build;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class JM {
    public static Object a(Class cls, InvocationHandler invocationHandler) {
        if (invocationHandler == null) {
            return null;
        }
        return cls.cast(Proxy.newProxyInstance(JM.class.getClassLoader(), new Class[]{cls}, invocationHandler));
    }

    public static boolean b(String[] strArr, String str) {
        List listAsList = Arrays.asList(strArr);
        if (listAsList.contains(str)) {
            return true;
        }
        if ("eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE)) {
            if (listAsList.contains(str + ":dev")) {
                return true;
            }
        }
        return false;
    }

    public static InvocationHandler c(Object obj) {
        if (obj == null) {
            return null;
        }
        return new IM(obj);
    }

    public static Object d(InvocationHandler invocationHandler) {
        if (invocationHandler == null) {
            return null;
        }
        return ((IM) invocationHandler).a;
    }
}
