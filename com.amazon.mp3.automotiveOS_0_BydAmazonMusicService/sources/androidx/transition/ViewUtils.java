package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;
import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
class ViewUtils {
    private static final String TAG = "ViewUtils";
    private static final int VISIBILITY_MASK = 12;
    private static Field sViewFlagsField;
    private static boolean sViewFlagsFieldFetched;
    private static final ViewUtilsBase IMPL = new ViewUtilsApi22();
    static final Property<View, Float> TRANSITION_ALPHA = new Property<View, Float>(Float.class, "translationAlpha") { // from class: androidx.transition.ViewUtils.1
        @Override // android.util.Property
        public Float get(View view) {
            return Float.valueOf(ViewUtils.getTransitionAlpha(view));
        }

        @Override // android.util.Property
        public void set(View view, Float f) {
            ViewUtils.setTransitionAlpha(view, f.floatValue());
        }
    };
    static final Property<View, Rect> CLIP_BOUNDS = new Property<View, Rect>(Rect.class, "clipBounds") { // from class: androidx.transition.ViewUtils.2
        @Override // android.util.Property
        public Rect get(View view) {
            return ViewCompat.getClipBounds(view);
        }

        @Override // android.util.Property
        public void set(View view, Rect rect) {
            ViewCompat.setClipBounds(view, rect);
        }
    };

    static ViewOverlayImpl getOverlay(@NonNull View view) {
        return new ViewOverlayApi18(view);
    }

    static WindowIdImpl getWindowId(@NonNull View view) {
        return new WindowIdApi18(view);
    }

    static void setTransitionAlpha(@NonNull View view, float f) {
        IMPL.setTransitionAlpha(view, f);
    }

    static float getTransitionAlpha(@NonNull View view) {
        return IMPL.getTransitionAlpha(view);
    }

    static void saveNonTransitionAlpha(@NonNull View view) {
        IMPL.saveNonTransitionAlpha(view);
    }

    static void clearNonTransitionAlpha(@NonNull View view) {
        IMPL.clearNonTransitionAlpha(view);
    }

    static void setTransitionVisibility(@NonNull View view, int i) {
        fetchViewFlagsField();
        Field field = sViewFlagsField;
        if (field != null) {
            try {
                sViewFlagsField.setInt(view, i | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused) {
            }
        }
    }

    static void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
        IMPL.transformMatrixToGlobal(view, matrix);
    }

    static void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
        IMPL.transformMatrixToLocal(view, matrix);
    }

    static void setAnimationMatrix(@NonNull View view, @Nullable Matrix matrix) {
        IMPL.setAnimationMatrix(view, matrix);
    }

    static void setLeftTopRightBottom(@NonNull View view, int i, int i2, int i3, int i4) {
        IMPL.setLeftTopRightBottom(view, i, i2, i3, i4);
    }

    private static void fetchViewFlagsField() {
        if (sViewFlagsFieldFetched) {
            return;
        }
        try {
            Field declaredField = View.class.getDeclaredField("mViewFlags");
            sViewFlagsField = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException unused) {
            Log.i(TAG, "fetchViewFlagsField: ");
        }
        sViewFlagsFieldFetched = true;
    }

    private ViewUtils() {
    }
}
