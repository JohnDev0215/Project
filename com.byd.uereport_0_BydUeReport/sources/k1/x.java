package k1;

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
    public static LayoutTransition f5831a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f5832b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f5833c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f5834d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5835e;

    /* JADX INFO: compiled from: ViewGroupUtilsApi14.java */
    public static class a extends LayoutTransition {
        @Override // android.animation.LayoutTransition
        public boolean isChangingLayout() {
            return true;
        }
    }

    public static void a(LayoutTransition layoutTransition) {
        if (!f5835e) {
            try {
                Method declaredMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
                f5834d = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            }
            f5835e = true;
        }
        Method method = f5834d;
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

    public static void b(ViewGroup viewGroup, boolean z7) {
        boolean z8 = false;
        if (f5831a == null) {
            a aVar = new a();
            f5831a = aVar;
            aVar.setAnimator(2, null);
            f5831a.setAnimator(0, null);
            f5831a.setAnimator(1, null);
            f5831a.setAnimator(3, null);
            f5831a.setAnimator(4, null);
        }
        if (z7) {
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null) {
                if (layoutTransition.isRunning()) {
                    a(layoutTransition);
                }
                if (layoutTransition != f5831a) {
                    viewGroup.setTag(R$id.transition_layout_save, layoutTransition);
                }
            }
            viewGroup.setLayoutTransition(f5831a);
            return;
        }
        viewGroup.setLayoutTransition(null);
        if (!f5833c) {
            try {
                Field declaredField = ViewGroup.class.getDeclaredField("mLayoutSuppressed");
                f5832b = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access mLayoutSuppressed field by reflection");
            }
            f5833c = true;
        }
        Field field = f5832b;
        if (field != null) {
            try {
                boolean z9 = field.getBoolean(viewGroup);
                if (z9) {
                    try {
                        f5832b.setBoolean(viewGroup, false);
                    } catch (IllegalAccessException unused2) {
                        z8 = z9;
                        Log.i("ViewGroupUtilsApi14", "Failed to get mLayoutSuppressed field by reflection");
                    }
                }
                z8 = z9;
            } catch (IllegalAccessException unused3) {
            }
        }
        if (z8) {
            viewGroup.requestLayout();
        }
        int i8 = R$id.transition_layout_save;
        LayoutTransition layoutTransition2 = (LayoutTransition) viewGroup.getTag(i8);
        if (layoutTransition2 != null) {
            viewGroup.setTag(i8, null);
            viewGroup.setLayoutTransition(layoutTransition2);
        }
    }
}
