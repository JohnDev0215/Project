package com.android.launcher3;

import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.util.FloatProperty;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAnimUtils {
    public static final int ALL_APPS_TRANSITION_MS = 320;
    public static final float MIN_PROGRESS_TO_ALL_APPS = 0.5f;
    public static final int OVERVIEW_TRANSITION_MS = 250;
    public static final int SPRING_LOADED_EXIT_DELAY = 500;
    public static final int SPRING_LOADED_TRANSITION_MS = 150;
    public static final FloatProperty<View> VIEW_TRANSLATE_X;
    public static final FloatProperty<View> VIEW_TRANSLATE_Y;
    public static final Property<Drawable, Integer> DRAWABLE_ALPHA = new Property<Drawable, Integer>(Integer.TYPE, "drawableAlpha") { // from class: com.android.launcher3.LauncherAnimUtils.1
        @Override // android.util.Property
        public Integer get(Drawable drawable) {
            return Integer.valueOf(drawable.getAlpha());
        }

        @Override // android.util.Property
        public void set(Drawable drawable, Integer alpha) {
            drawable.setAlpha(alpha.intValue());
        }
    };
    public static final FloatProperty<View> SCALE_PROPERTY = new FloatProperty<View>(BatteryManager.EXTRA_SCALE) { // from class: com.android.launcher3.LauncherAnimUtils.2
        @Override // android.util.Property
        public Float get(View view) {
            return Float.valueOf(view.getScaleX());
        }

        @Override // android.util.FloatProperty
        public void setValue(View view, float scale) {
            view.setScaleX(scale);
            view.setScaleY(scale);
        }
    };
    public static final Property<ViewGroup.LayoutParams, Integer> LAYOUT_WIDTH = new Property<ViewGroup.LayoutParams, Integer>(Integer.TYPE, "width") { // from class: com.android.launcher3.LauncherAnimUtils.3
        @Override // android.util.Property
        public Integer get(ViewGroup.LayoutParams lp) {
            return Integer.valueOf(lp.width);
        }

        @Override // android.util.Property
        public void set(ViewGroup.LayoutParams lp, Integer width) {
            lp.width = width.intValue();
        }
    };
    public static final Property<ViewGroup.LayoutParams, Integer> LAYOUT_HEIGHT = new Property<ViewGroup.LayoutParams, Integer>(Integer.TYPE, "height") { // from class: com.android.launcher3.LauncherAnimUtils.4
        @Override // android.util.Property
        public Integer get(ViewGroup.LayoutParams lp) {
            return Integer.valueOf(lp.height);
        }

        @Override // android.util.Property
        public void set(ViewGroup.LayoutParams lp, Integer height) {
            lp.height = height.intValue();
        }
    };

    static {
        VIEW_TRANSLATE_X = View.TRANSLATION_X instanceof FloatProperty ? (FloatProperty) View.TRANSLATION_X : new FloatProperty<View>("translateX") { // from class: com.android.launcher3.LauncherAnimUtils.5
            @Override // android.util.FloatProperty
            public void setValue(View view, float v) {
                view.setTranslationX(v);
            }

            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(view.getTranslationX());
            }
        };
        VIEW_TRANSLATE_Y = View.TRANSLATION_Y instanceof FloatProperty ? (FloatProperty) View.TRANSLATION_Y : new FloatProperty<View>("translateY") { // from class: com.android.launcher3.LauncherAnimUtils.6
            @Override // android.util.FloatProperty
            public void setValue(View view, float v) {
                view.setTranslationY(v);
            }

            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(view.getTranslationY());
            }
        };
    }

    public static int blockedFlingDurationFactor(float velocity) {
        return (int) Utilities.boundToRange(Math.abs(velocity) / 2.0f, 2.0f, 6.0f);
    }
}
