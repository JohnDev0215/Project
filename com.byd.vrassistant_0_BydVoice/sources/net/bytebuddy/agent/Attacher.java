package net.bytebuddy.agent;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes3.dex */
public class Attacher {
    private static final String ATTACH_METHOD_NAME = "attach";
    private static final String DETACH_METHOD_NAME = "detach";
    private static final String LOAD_AGENT_METHOD_NAME = "loadAgent";
    private static final Object STATIC_MEMBER = null;

    public static void main(String[] strArr) {
        String string;
        try {
            if (strArr.length < 3 || strArr[3].isEmpty()) {
                string = null;
            } else {
                StringBuilder sb = new StringBuilder(strArr[3].substring(1));
                for (int i = 4; i < strArr.length; i++) {
                    sb.append(' ').append(strArr[i]);
                }
                string = sb.toString();
            }
            install(Class.forName(strArr[0]), strArr[1], strArr[2], string);
        } catch (Exception unused) {
            System.exit(1);
        }
    }

    protected static void install(Class<?> cls, String str, String str2, String str3) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object objInvoke = cls.getMethod(ATTACH_METHOD_NAME, String.class).invoke(STATIC_MEMBER, str);
        try {
            cls.getMethod(LOAD_AGENT_METHOD_NAME, String.class, String.class).invoke(objInvoke, str2, str3);
            cls.getMethod(DETACH_METHOD_NAME, new Class[0]).invoke(objInvoke, new Object[0]);
        } catch (Throwable th) {
            cls.getMethod(DETACH_METHOD_NAME, new Class[0]).invoke(objInvoke, new Object[0]);
            throw th;
        }
    }

    private Attacher() {
        throw new UnsupportedOperationException();
    }
}
