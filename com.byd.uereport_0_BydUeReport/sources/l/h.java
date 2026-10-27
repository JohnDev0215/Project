package l;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

/* JADX INFO: compiled from: ResourcesFlusher.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Field f5948a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5949b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Class<?> f5950c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5951d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Field f5952e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f5953f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Field f5954g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f5955h;

    public static void a(Resources resources) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 28) {
            return;
        }
        if (i8 >= 24) {
            d(resources);
        } else if (i8 >= 23) {
            c(resources);
        } else if (i8 >= 21) {
            b(resources);
        }
    }

    public static void b(Resources resources) {
        if (!f5949b) {
            try {
                Field declaredField = Resources.class.getDeclaredField("mDrawableCache");
                f5948a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e8) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e8);
            }
            f5949b = true;
        }
        Field field = f5948a;
        if (field != null) {
            Map map = null;
            try {
                map = (Map) field.get(resources);
            } catch (IllegalAccessException e9) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e9);
            }
            if (map != null) {
                map.clear();
            }
        }
    }

    public static void c(Resources resources) {
        if (!f5949b) {
            try {
                Field declaredField = Resources.class.getDeclaredField("mDrawableCache");
                f5948a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e8) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e8);
            }
            f5949b = true;
        }
        Object obj = null;
        Field field = f5948a;
        if (field != null) {
            try {
                obj = field.get(resources);
            } catch (IllegalAccessException e9) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e9);
            }
        }
        if (obj == null) {
            return;
        }
        e(obj);
    }

    public static void d(Resources resources) {
        Object obj;
        if (!f5955h) {
            try {
                Field declaredField = Resources.class.getDeclaredField("mResourcesImpl");
                f5954g = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e8) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", e8);
            }
            f5955h = true;
        }
        Field field = f5954g;
        if (field == null) {
            return;
        }
        Object obj2 = null;
        try {
            obj = field.get(resources);
        } catch (IllegalAccessException e9) {
            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", e9);
            obj = null;
        }
        if (obj == null) {
            return;
        }
        if (!f5949b) {
            try {
                Field declaredField2 = obj.getClass().getDeclaredField("mDrawableCache");
                f5948a = declaredField2;
                declaredField2.setAccessible(true);
            } catch (NoSuchFieldException e10) {
                Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", e10);
            }
            f5949b = true;
        }
        Field field2 = f5948a;
        if (field2 != null) {
            try {
                obj2 = field2.get(obj);
            } catch (IllegalAccessException e11) {
                Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", e11);
            }
        }
        if (obj2 != null) {
            e(obj2);
        }
    }

    public static void e(Object obj) {
        if (!f5951d) {
            try {
                f5950c = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e8) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", e8);
            }
            f5951d = true;
        }
        Class<?> cls = f5950c;
        if (cls == null) {
            return;
        }
        if (!f5953f) {
            try {
                Field declaredField = cls.getDeclaredField("mUnthemedEntries");
                f5952e = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e9) {
                Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e9);
            }
            f5953f = true;
        }
        Field field = f5952e;
        if (field == null) {
            return;
        }
        LongSparseArray longSparseArray = null;
        try {
            longSparseArray = (LongSparseArray) field.get(obj);
        } catch (IllegalAccessException e10) {
            Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e10);
        }
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
    }
}
