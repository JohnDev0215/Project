package y0;

import android.animation.LayoutTransition;
import android.util.Log;
import android.view.ViewGroup;
import androidx.transition.R$id;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewGroupUtilsApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static LayoutTransition f5989a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f5990b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f5991c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f5992d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5993e;

    /* JADX INFO: compiled from: ViewGroupUtilsApi14.java */
    public static class a extends LayoutTransition {
        @Override // android.animation.LayoutTransition
        public boolean isChangingLayout() {
            return true;
        }
    }

    public static void a(LayoutTransition layoutTransition) {
        if (!f5993e) {
            try {
                Method declaredMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
                f5992d = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            }
            f5993e = true;
        }
        Method method = f5992d;
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

    public static void b(ViewGroup viewGroup, boolean z3) {
        boolean z4 = false;
        if (f5989a == null) {
            a aVar = new a();
            f5989a = aVar;
            aVar.setAnimator(2, null);
            f5989a.setAnimator(0, null);
            f5989a.setAnimator(1, null);
            f5989a.setAnimator(3, null);
            f5989a.setAnimator(4, null);
        }
        if (z3) {
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null) {
                if (layoutTransition.isRunning()) {
                    a(layoutTransition);
                }
                if (layoutTransition != f5989a) {
                    viewGroup.setTag(R$id.transition_layout_save, layoutTransition);
                }
            }
            viewGroup.setLayoutTransition(f5989a);
            return;
        }
        viewGroup.setLayoutTransition(null);
        if (!f5991c) {
            try {
                Field declaredField = ViewGroup.class.getDeclaredField("mLayoutSuppressed");
                f5990b = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access mLayoutSuppressed field by reflection");
            }
            f5991c = true;
        }
        Field field = f5990b;
        if (field != null) {
            try {
                boolean z5 = field.getBoolean(viewGroup);
                if (z5) {
                    try {
                        f5990b.setBoolean(viewGroup, false);
                    } catch (IllegalAccessException unused2) {
                        z4 = z5;
                        Log.i("ViewGroupUtilsApi14", "Failed to get mLayoutSuppressed field by reflection");
                    }
                }
                z4 = z5;
            } catch (IllegalAccessException unused3) {
            }
        }
        if (z4) {
            viewGroup.requestLayout();
        }
        int i3 = R$id.transition_layout_save;
        LayoutTransition layoutTransition2 = (LayoutTransition) viewGroup.getTag(i3);
        if (layoutTransition2 != null) {
            viewGroup.setTag(i3, null);
            viewGroup.setLayoutTransition(layoutTransition2);
        }
    }
}
