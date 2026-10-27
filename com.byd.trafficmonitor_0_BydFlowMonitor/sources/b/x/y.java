package b.x;

import android.animation.LayoutTransition;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewGroupUtilsApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static LayoutTransition f3324a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f3325b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f3326c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f3327d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3328e;

    /* JADX INFO: compiled from: ViewGroupUtilsApi14.java */
    public static class a extends LayoutTransition {
        @Override // android.animation.LayoutTransition
        public boolean isChangingLayout() {
            return true;
        }
    }

    public static void a(LayoutTransition layoutTransition) {
        if (!f3328e) {
            try {
                Method declaredMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
                f3327d = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            }
            f3328e = true;
        }
        Method method = f3327d;
        if (method != null) {
            try {
                method.invoke(layoutTransition, new Object[0]);
            } catch (IllegalAccessException unused2) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            } catch (InvocationTargetException unused3) {
                Log.i("ViewGroupUtilsApi14", "Failed to invoke cancel method by reflection");
            }
        }
    }

    public static void b(ViewGroup viewGroup, boolean z) {
        boolean z2 = false;
        if (f3324a == null) {
            a aVar = new a();
            f3324a = aVar;
            aVar.setAnimator(2, null);
            f3324a.setAnimator(0, null);
            f3324a.setAnimator(1, null);
            f3324a.setAnimator(3, null);
            f3324a.setAnimator(4, null);
        }
        if (z) {
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null) {
                if (layoutTransition.isRunning()) {
                    a(layoutTransition);
                }
                if (layoutTransition != f3324a) {
                    viewGroup.setTag(j.transition_layout_save, layoutTransition);
                }
            }
            viewGroup.setLayoutTransition(f3324a);
            return;
        }
        viewGroup.setLayoutTransition(null);
        if (!f3326c) {
            try {
                Field declaredField = ViewGroup.class.getDeclaredField("mLayoutSuppressed");
                f3325b = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access mLayoutSuppressed field by reflection");
            }
            f3326c = true;
        }
        Field field = f3325b;
        if (field != null) {
            try {
                boolean z3 = field.getBoolean(viewGroup);
                if (z3) {
                    try {
                        f3325b.setBoolean(viewGroup, false);
                    } catch (IllegalAccessException unused2) {
                        z2 = z3;
                        Log.i("ViewGroupUtilsApi14", "Failed to get mLayoutSuppressed field by reflection");
                    }
                }
                z2 = z3;
            } catch (IllegalAccessException unused3) {
            }
        }
        if (z2) {
            viewGroup.requestLayout();
        }
        int i2 = j.transition_layout_save;
        LayoutTransition layoutTransition2 = (LayoutTransition) viewGroup.getTag(i2);
        if (layoutTransition2 != null) {
            viewGroup.setTag(i2, null);
            viewGroup.setLayoutTransition(layoutTransition2);
        }
    }
}
