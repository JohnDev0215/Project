package org.apache.commons.compress.harmony.archive.internal.nls;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes4.dex */
public class Messages {
    private static ResourceBundle bundle;

    public static String getString(String str) {
        ResourceBundle resourceBundle = bundle;
        if (resourceBundle == null) {
            return str;
        }
        try {
            return resourceBundle.getString(str);
        } catch (MissingResourceException unused) {
            return "Missing message: " + str;
        }
    }

    public static String getString(String str, Object obj) {
        return getString(str, new Object[]{obj});
    }

    public static String getString(String str, int i) {
        return getString(str, new Object[]{Integer.toString(i)});
    }

    public static String getString(String str, char c) {
        return getString(str, new Object[]{String.valueOf(c)});
    }

    public static String getString(String str, Object obj, Object obj2) {
        return getString(str, new Object[]{obj, obj2});
    }

    public static String getString(String str, Object[] objArr) {
        ResourceBundle resourceBundle = bundle;
        if (resourceBundle != null) {
            try {
                str = resourceBundle.getString(str);
            } catch (MissingResourceException unused) {
            }
        }
        return format(str, objArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String format(java.lang.String r9, java.lang.Object[] r10) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r9.length()
            int r2 = r10.length
            int r2 = r2 * 20
            int r1 = r1 + r2
            r0.<init>(r1)
            int r1 = r10.length
            java.lang.String[] r2 = new java.lang.String[r1]
            r3 = 0
            r4 = r3
        L12:
            int r5 = r10.length
            if (r4 >= r5) goto L27
            r5 = r10[r4]
            if (r5 != 0) goto L1e
            java.lang.String r5 = "<null>"
            r2[r4] = r5
            goto L24
        L1e:
            java.lang.String r5 = r5.toString()
            r2[r4] = r5
        L24:
            int r4 = r4 + 1
            goto L12
        L27:
            r10 = 123(0x7b, float:1.72E-43)
        L29:
            int r4 = r9.indexOf(r10, r3)
            if (r4 < 0) goto L9a
            if (r4 == 0) goto L4c
            int r5 = r4 + (-1)
            char r6 = r9.charAt(r5)
            r7 = 92
            if (r6 != r7) goto L4c
            r6 = 1
            if (r4 == r6) goto L45
            java.lang.String r3 = r9.substring(r3, r5)
            r0.append(r3)
        L45:
            r0.append(r10)
            int r4 = r4 + 1
        L4a:
            r3 = r4
            goto L29
        L4c:
            int r5 = r9.length()
            int r5 = r5 + (-3)
            if (r4 <= r5) goto L60
            java.lang.String r3 = r9.substring(r3)
            r0.append(r3)
            int r3 = r9.length()
            goto L29
        L60:
            int r5 = r4 + 1
            char r6 = r9.charAt(r5)
            r7 = 10
            int r6 = java.lang.Character.digit(r6, r7)
            byte r6 = (byte) r6
            if (r6 < 0) goto L91
            int r7 = r4 + 2
            char r7 = r9.charAt(r7)
            r8 = 125(0x7d, float:1.75E-43)
            if (r7 == r8) goto L7a
            goto L91
        L7a:
            java.lang.String r3 = r9.substring(r3, r4)
            r0.append(r3)
            if (r6 < r1) goto L89
            java.lang.String r3 = "<missing argument>"
            r0.append(r3)
            goto L8e
        L89:
            r3 = r2[r6]
            r0.append(r3)
        L8e:
            int r4 = r4 + 3
            goto L4a
        L91:
            java.lang.String r3 = r9.substring(r3, r5)
            r0.append(r3)
            r3 = r5
            goto L29
        L9a:
            int r10 = r9.length()
            if (r3 >= r10) goto La7
            java.lang.String r9 = r9.substring(r3)
            r0.append(r9)
        La7:
            java.lang.String r9 = r0.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.archive.internal.nls.Messages.format(java.lang.String, java.lang.Object[]):java.lang.String");
    }

    public static ResourceBundle setLocale(final Locale locale, final String str) {
        final ClassLoader classLoader = null;
        try {
            return (ResourceBundle) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.compress.harmony.archive.internal.nls.Messages$$ExternalSyntheticLambda0
                @Override // java.security.PrivilegedAction
                public final Object run() {
                    return Messages.lambda$setLocale$0(str, locale, classLoader);
                }
            });
        } catch (MissingResourceException unused) {
            return null;
        }
    }

    static /* synthetic */ Object lambda$setLocale$0(String str, Locale locale, ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return ResourceBundle.getBundle(str, locale, classLoader);
    }

    static {
        try {
            bundle = setLocale(Locale.getDefault(), "org.apache.commons.compress.harmony.archive.internal.nls.messages");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
