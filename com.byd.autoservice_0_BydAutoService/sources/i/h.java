package i;

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
    public static Field f3154a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3155b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Class<?> f3156c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f3157d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Field f3158e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f3159f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Field f3160g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f3161h;

    public static void a(Resources resources) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 28) {
            return;
        }
        if (i3 >= 24) {
            d(resources);
        } else if (i3 >= 23) {
            c(resources);
        } else if (i3 >= 21) {
            b(resources);
        }
    }

    public static void b(Resources resources) {
        if (!f3155b) {
            try {
                Field declaredField = Resources.class.getDeclaredField("mDrawableCache");
                f3154a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e3) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e3);
            }
            f3155b = true;
        }
        Field field = f3154a;
        if (field != null) {
            Map map = null;
            try {
                map = (Map) field.get(resources);
            } catch (IllegalAccessException e4) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e4);
            }
            if (map != null) {
                map.clear();
            }
        }
    }

    public static void c(Resources resources) {
        if (!f3155b) {
            try {
                Field declaredField = Resources.class.getDeclaredField("mDrawableCache");
                f3154a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e3) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e3);
            }
            f3155b = true;
        }
        Object obj = null;
        Field field = f3154a;
        if (field != null) {
            try {
                obj = field.get(resources);
            } catch (IllegalAccessException e4) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e4);
            }
        }
        if (obj == null) {
            return;
        }
        e(obj);
    }

    public static void d(Resources resources) {
        Object obj;
        if (!f3161h) {
            try {
                Field declaredField = Resources.class.getDeclaredField("mResourcesImpl");
                f3160g = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e3) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", e3);
            }
            f3161h = true;
        }
        Field field = f3160g;
        if (field == null) {
            return;
        }
        Object obj2 = null;
        try {
            obj = field.get(resources);
        } catch (IllegalAccessException e4) {
            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", e4);
            obj = null;
        }
        if (obj == null) {
            return;
        }
        if (!f3155b) {
            try {
                Field declaredField2 = obj.getClass().getDeclaredField("mDrawableCache");
                f3154a = declaredField2;
                declaredField2.setAccessible(true);
            } catch (NoSuchFieldException e5) {
                Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", e5);
            }
            f3155b = true;
        }
        Field field2 = f3154a;
        if (field2 != null) {
            try {
                obj2 = field2.get(obj);
            } catch (IllegalAccessException e6) {
                Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", e6);
            }
        }
        if (obj2 != null) {
            e(obj2);
        }
    }

    public static void e(Object obj) {
        if (!f3157d) {
            try {
                f3156c = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e3) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", e3);
            }
            f3157d = true;
        }
        Class<?> cls = f3156c;
        if (cls == null) {
            return;
        }
        if (!f3159f) {
            try {
                Field declaredField = cls.getDeclaredField("mUnthemedEntries");
                f3158e = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e4) {
                Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e4);
            }
            f3159f = true;
        }
        Field field = f3158e;
        if (field == null) {
            return;
        }
        LongSparseArray longSparseArray = null;
        try {
            longSparseArray = (LongSparseArray) field.get(obj);
        } catch (IllegalAccessException e5) {
            Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e5);
        }
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
    }
}
