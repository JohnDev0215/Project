package defpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.view.View;
import android.view.Window;
import com.google.android.material.R$attr;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DynamicColors.java */
/* JADX INFO: loaded from: classes.dex */
public final class bf0 {
    public static final int[] a = {R$attr.dynamicColorThemeOverlay};
    public static final Map<String, c> b;
    public static final Map<String, c> c;

    /* JADX INFO: compiled from: DynamicColors.java */
    public class a implements c {
        @Override // bf0.c
        public final boolean a() {
            return true;
        }
    }

    /* JADX INFO: compiled from: DynamicColors.java */
    public class b implements c {
        public Long a;

        @Override // bf0.c
        public final boolean a() {
            if (this.a == null) {
                try {
                    Method declaredMethod = Build.class.getDeclaredMethod("getLong", String.class);
                    declaredMethod.setAccessible(true);
                    this.a = Long.valueOf(((Long) declaredMethod.invoke(null, "ro.build.version.oneui")).longValue());
                } catch (Exception unused) {
                    this.a = -1L;
                }
            }
            return this.a.longValue() >= 40100;
        }
    }

    /* JADX INFO: compiled from: DynamicColors.java */
    public interface c {
        boolean a();
    }

    static {
        a aVar = new a();
        b bVar = new b();
        HashMap map = new HashMap();
        map.put("google", aVar);
        map.put("hmd global", aVar);
        map.put("infinix", aVar);
        map.put("infinix mobility limited", aVar);
        map.put("itel", aVar);
        map.put("kyocera", aVar);
        map.put("lenovo", aVar);
        map.put("lge", aVar);
        map.put("motorola", aVar);
        map.put("nothing", aVar);
        map.put("oneplus", aVar);
        map.put("oppo", aVar);
        map.put("realme", aVar);
        map.put("robolectric", aVar);
        map.put("samsung", bVar);
        map.put("sharp", aVar);
        map.put("sony", aVar);
        map.put("tcl", aVar);
        map.put("tecno", aVar);
        map.put("tecno mobile limited", aVar);
        map.put("vivo", aVar);
        map.put("xiaomi", aVar);
        b = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        map2.put("asus", aVar);
        map2.put("jio", aVar);
        c = Collections.unmodifiableMap(map2);
    }

    public static void a(Activity activity) {
        View viewPeekDecorView;
        Context context;
        if (b()) {
            TypedArray typedArrayObtainStyledAttributes = activity.obtainStyledAttributes(a);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            typedArrayObtainStyledAttributes.recycle();
            if (resourceId != 0) {
                activity.getTheme().applyStyle(resourceId, true);
                Window window = activity.getWindow();
                Resources.Theme theme = (window == null || (viewPeekDecorView = window.peekDecorView()) == null || (context = viewPeekDecorView.getContext()) == null) ? null : context.getTheme();
                if (theme != null) {
                    theme.applyStyle(resourceId, true);
                }
            }
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean b() {
        if (Build.VERSION.SDK_INT < 31) {
            return false;
        }
        if (yj.a()) {
            return true;
        }
        c cVar = b.get(Build.MANUFACTURER.toLowerCase());
        if (cVar == null) {
            cVar = c.get(Build.BRAND.toLowerCase());
        }
        return cVar != null && cVar.a();
    }
}
