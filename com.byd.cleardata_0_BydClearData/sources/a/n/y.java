package a.n;

import android.animation.LayoutTransition;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static LayoutTransition f599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Field f600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f601c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Method f602d;
    private static boolean e;

    static class a extends LayoutTransition {
        a() {
        }

        @Override // android.animation.LayoutTransition
        public boolean isChangingLayout() {
            return true;
        }
    }

    private static void a(LayoutTransition layoutTransition) {
        if (!e) {
            try {
                Method declaredMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
                f602d = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            }
            e = true;
        }
        Method method = f602d;
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

    static void b(ViewGroup viewGroup, boolean z) {
        boolean z2 = false;
        if (f599a == null) {
            a aVar = new a();
            f599a = aVar;
            aVar.setAnimator(2, null);
            f599a.setAnimator(0, null);
            f599a.setAnimator(1, null);
            f599a.setAnimator(3, null);
            f599a.setAnimator(4, null);
        }
        if (z) {
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null) {
                if (layoutTransition.isRunning()) {
                    a(layoutTransition);
                }
                if (layoutTransition != f599a) {
                    viewGroup.setTag(j.f549d, layoutTransition);
                }
            }
            viewGroup.setLayoutTransition(f599a);
            return;
        }
        viewGroup.setLayoutTransition(null);
        if (!f601c) {
            try {
                Field declaredField = ViewGroup.class.getDeclaredField("mLayoutSuppressed");
                f600b = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access mLayoutSuppressed field by reflection");
            }
            f601c = true;
        }
        Field field = f600b;
        if (field != null) {
            try {
                boolean z3 = field.getBoolean(viewGroup);
                if (z3) {
                    try {
                        f600b.setBoolean(viewGroup, false);
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
        int i = j.f549d;
        LayoutTransition layoutTransition2 = (LayoutTransition) viewGroup.getTag(i);
        if (layoutTransition2 != null) {
            viewGroup.setTag(i, null);
            viewGroup.setLayoutTransition(layoutTransition2);
        }
    }
}
