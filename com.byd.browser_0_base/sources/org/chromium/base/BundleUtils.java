package org.chromium.base;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.ArrayMap;
import dalvik.system.BaseDexClassLoader;
import defpackage.fa1;
import defpackage.ku2;
import defpackage.oj2;
import defpackage.uv;
import defpackage.wt3;
import defpackage.xz;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class BundleUtils {
    public static final Object a = new Object();
    public static final ArrayMap<String, ClassLoader> b = new ArrayMap<>();
    public static final Map<String, ClassLoader> c = Collections.synchronizedMap(new ArrayMap());
    public static a d;
    public static ArrayList<String> e;

    public static class a extends ClassLoader {
        public a() {
            super(xz.a.getClassLoader());
            fa1.g("SplitCompatClassLoader", "Splits: %s", BundleUtils.e);
        }

        public static Class a(String str) {
            Iterator<ClassLoader> it = BundleUtils.c.values().iterator();
            while (it.hasNext()) {
                try {
                    return it.next().loadClass(str);
                } catch (ClassNotFoundException unused) {
                }
            }
            return null;
        }

        @Override // java.lang.ClassLoader
        public final Class<?> findClass(String str) throws ClassNotFoundException {
            Class<?> clsA = a(str);
            if (clsA != null) {
                return clsA;
            }
            if (BundleUtils.e != null && !str.startsWith("android.")) {
                for (String str2 : BundleUtils.e) {
                    Map<String, ClassLoader> map = BundleUtils.c;
                    if (!map.containsKey(str2)) {
                        map.put(str2, BundleUtils.b(str2));
                    }
                }
                BundleUtils.e = null;
                Class<?> clsA2 = a(str);
                if (clsA2 != null) {
                    return clsA2;
                }
            }
            fa1.l("SplitCompatClassLoader", "No class %s amongst %s", str, BundleUtils.c.keySet());
            throw new ClassNotFoundException(str);
        }
    }

    public static Context a(Context context, String str) {
        boolean z;
        boolean z2;
        ku2 ku2VarT;
        Context contextCreateContextForSplit;
        Context baseContext = context;
        while (true) {
            try {
                z = true;
                if (!(baseContext instanceof ContextWrapper)) {
                    z2 = false;
                    break;
                }
                if (baseContext instanceof Application) {
                    z2 = true;
                    break;
                }
                baseContext = ((ContextWrapper) baseContext).getBaseContext();
            } catch (PackageManager.NameNotFoundException e2) {
                throw new uv(e2);
            }
        }
        if (z2) {
            ku2VarT = ku2.t();
            try {
                contextCreateContextForSplit = context.createContextForSplit(str);
                ku2VarT.close();
            } finally {
            }
        } else {
            synchronized (a) {
                ku2VarT = ku2.t();
                try {
                    contextCreateContextForSplit = context.createContextForSplit(str);
                } finally {
                    try {
                        ku2VarT.close();
                    } catch (Throwable unused) {
                    }
                }
            }
        }
        contextCreateContextForSplit.getClassLoader().getParent();
        ArrayMap<String, ClassLoader> arrayMap = b;
        synchronized (arrayMap) {
            ClassLoader classLoader = arrayMap.get(str);
            if (classLoader == null) {
                arrayMap.put(str, contextCreateContextForSplit.getClassLoader());
            } else if (!classLoader.equals(contextCreateContextForSplit.getClassLoader())) {
                f(contextCreateContextForSplit, classLoader);
            }
            z = false;
        }
        oj2.O("Android.IsolatedSplits.ClassLoaderReplaced." + str, z);
        return contextCreateContextForSplit;
    }

    public static ClassLoader b(String str) {
        ClassLoader classLoader;
        ArrayMap<String, ClassLoader> arrayMap = b;
        synchronized (arrayMap) {
            classLoader = arrayMap.get(str);
        }
        if (classLoader == null) {
            a(xz.a, str);
            synchronized (arrayMap) {
                classLoader = arrayMap.get(str);
            }
        }
        return classLoader;
    }

    public static String c(String str, String str2) {
        int iBinarySearch;
        ApplicationInfo applicationInfo = xz.a.getApplicationInfo();
        String[] strArr = applicationInfo.splitNames;
        String str3 = (strArr != null && (iBinarySearch = Arrays.binarySearch(strArr, str2)) >= 0) ? applicationInfo.splitSourceDirs[iBinarySearch] : null;
        if (str3 == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo2 = xz.a.getApplicationInfo();
            return str3 + "!/lib/" + ((String) applicationInfo2.getClass().getField("primaryCpuAbi").get(applicationInfo2)) + "/" + System.mapLibraryName(str);
        } catch (ReflectiveOperationException e2) {
            throw new uv(e2);
        }
    }

    public static boolean d(String str) {
        int iBinarySearch;
        ApplicationInfo applicationInfo = xz.a.getApplicationInfo();
        String[] strArr = applicationInfo.splitNames;
        String str2 = null;
        if (strArr != null && (iBinarySearch = Arrays.binarySearch(strArr, str)) >= 0) {
            str2 = applicationInfo.splitSourceDirs[iBinarySearch];
        }
        return str2 != null;
    }

    public static Object e(Context context, String str) {
        Context context2 = xz.a;
        if (context2 != null) {
            boolean z = false;
            try {
                Class.forName(str, false, context2.getClassLoader());
                z = true;
            } catch (ClassNotFoundException unused) {
            }
            if (z) {
                context = context2;
            }
        }
        try {
            return context.getClassLoader().loadClass(str).newInstance();
        } catch (ReflectiveOperationException e2) {
            throw new uv(e2);
        }
    }

    public static void f(Context context, ClassLoader classLoader) {
        while (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        try {
            Field declaredField = context.getClass().getDeclaredField("mClassLoader");
            declaredField.setAccessible(true);
            declaredField.set(context, classLoader);
        } catch (ReflectiveOperationException e2) {
            throw new uv("Error setting ClassLoader.", e2);
        }
    }

    @CalledByNative
    public static String getNativeLibraryPath(String str, String str2) {
        ku2 ku2VarT = ku2.t();
        try {
            String strFindLibrary = ((BaseDexClassLoader) BundleUtils.class.getClassLoader()).findLibrary(str);
            if (strFindLibrary != null) {
                return strFindLibrary;
            }
            ClassLoader classLoader = xz.a.getClassLoader();
            if (classLoader instanceof BaseDexClassLoader) {
                strFindLibrary = ((BaseDexClassLoader) classLoader).findLibrary(str);
            } else if (classLoader instanceof wt3) {
                strFindLibrary = ((wt3) classLoader).findLibrary(str);
            }
            if (strFindLibrary != null) {
                ku2VarT.close();
                return strFindLibrary;
            }
            String strC = c(str, str2);
            ku2VarT.close();
            return strC;
        } finally {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
        }
    }

    @CalledByNative
    public static boolean isBundleForNative() {
        return false;
    }
}
