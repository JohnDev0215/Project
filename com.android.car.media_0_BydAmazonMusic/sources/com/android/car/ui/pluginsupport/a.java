package com.android.car.ui.pluginsupport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import dalvik.system.PathClassLoader;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: AdapterClassLoader.java */
/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(api = 28)
public final class a extends PathClassLoader {
    public static final Pattern b;
    public static final Pattern c;

    @Nullable
    public final ClassLoader a;

    static {
        StringBuilder sbK = spi.c.k("^com\\.android\\.car\\.ui\\..*AdapterV[0-9]+(\\$.*)?$|Lambda|^");
        sbK.append(Pattern.quote(OemApiUtil.class.getName()));
        sbK.append("$");
        b = Pattern.compile(sbK.toString());
        c = Pattern.compile("^com\\.android\\.car\\.ui\\.plugin\\.(oemapis\\..*|PluginVersionProviderImpl)$");
    }

    public a(String str, @Nullable ClassLoader classLoader, @Nullable ClassLoader classLoader2) {
        this(str, null, classLoader, classLoader2);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    @Override // java.lang.ClassLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Class<?> loadClass(java.lang.String r3, boolean r4) throws java.lang.ClassNotFoundException {
        /*
            r2 = this;
            java.lang.Class r4 = r2.findLoadedClass(r3)
            if (r4 == 0) goto L7
            return r4
        L7:
            java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch: java.lang.ClassNotFoundException -> L12
            java.lang.Class r2 = r4.loadClass(r3)     // Catch: java.lang.ClassNotFoundException -> L12
            return r2
        L12:
            r4 = 0
            if (r3 == 0) goto L27
            java.util.regex.Pattern r0 = com.android.car.ui.pluginsupport.a.b
            java.util.regex.Matcher r0 = r0.matcher(r3)
            boolean r0 = r0.find()
            if (r0 == 0) goto L27
            java.lang.Class r2 = r2.findClass(r3)     // Catch: java.lang.ClassNotFoundException -> L26
            return r2
        L26:
            r4 = move-exception
        L27:
            java.lang.ClassLoader r0 = r2.a
            if (r0 == 0) goto L44
            java.util.regex.Pattern r0 = com.android.car.ui.pluginsupport.a.c
            if (r3 != 0) goto L32
            java.lang.String r1 = ""
            goto L33
        L32:
            r1 = r3
        L33:
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r0 = r0.matches()
            if (r0 == 0) goto L44
            java.lang.ClassLoader r0 = r2.a     // Catch: java.lang.ClassNotFoundException -> L44
            java.lang.Class r2 = r0.loadClass(r3)     // Catch: java.lang.ClassNotFoundException -> L44
            return r2
        L44:
            java.lang.ClassLoader r2 = r2.getParent()     // Catch: java.lang.ClassNotFoundException -> L4d
            java.lang.Class r2 = r2.loadClass(r3)     // Catch: java.lang.ClassNotFoundException -> L4d
            return r2
        L4d:
            r2 = move-exception
            if (r4 != 0) goto L51
            throw r2
        L51:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.ui.pluginsupport.a.loadClass(java.lang.String, boolean):java.lang.Class");
    }

    public a(String str, String str2, @Nullable ClassLoader classLoader, @Nullable ClassLoader classLoader2) {
        this(str, str2, classLoader, classLoader2, true);
    }

    public a(@NonNull String str, @Nullable String str2, @Nullable ClassLoader classLoader, @Nullable ClassLoader classLoader2, boolean z) {
        super(str, str2, classLoader);
        this.a = classLoader2;
    }
}
