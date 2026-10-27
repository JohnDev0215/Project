package defpackage;

import androidx.lifecycle.b;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/* JADX INFO: compiled from: Lifecycling.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d71 {
    public static final HashMap a = new HashMap();
    public static final HashMap b = new HashMap();

    public static b a(Constructor constructor, Object obj) {
        try {
            Object objNewInstance = constructor.newInstance(obj);
            i21.d(objNewInstance, "{\n            constructo…tance(`object`)\n        }");
            return (b) objNewInstance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static final String b(String str) {
        StringBuilder sb = new StringBuilder();
        int iW1 = tu2.w1(str, ".", 0, false);
        if (iW1 >= 0) {
            int length = (str.length() - 1) + 1;
            if (length < 0) {
                throw new OutOfMemoryError();
            }
            StringBuilder sb2 = new StringBuilder(length);
            int i = 0;
            do {
                sb2.append((CharSequence) str, i, iW1);
                sb2.append("_");
                i = iW1 + 1;
                if (iW1 >= str.length()) {
                    break;
                }
                iW1 = tu2.w1(str, ".", i, false);
            } while (iW1 > 0);
            sb2.append((CharSequence) str, i, str.length());
            str = sb2.toString();
            i21.d(str, "stringBuilder.append(this, i, length).toString()");
        }
        return gq2.i(sb, str, "_LifecycleAdapter");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x014e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int c(java.lang.Class r12) {
        /*
            Method dump skipped, instruction units count: 357
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d71.c(java.lang.Class):int");
    }
}
