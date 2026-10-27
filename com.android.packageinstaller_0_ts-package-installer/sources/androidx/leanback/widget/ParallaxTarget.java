package androidx.leanback.widget;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Property;
import android.view.animation.LinearInterpolator;

/* JADX INFO: loaded from: classes.dex */
public abstract class ParallaxTarget {
    public void update(float fraction) {
    }

    public boolean isDirectMapping() {
        return false;
    }

    public void directUpdate(Number value) {
    }

    public static final class PropertyValuesHolderTarget extends ParallaxTarget {
        private static final long PSEUDO_DURATION = 1000000;
        private final ObjectAnimator mAnimator;

        public PropertyValuesHolderTarget(Object targetObject, PropertyValuesHolder values) {
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(targetObject, values);
            this.mAnimator = objectAnimatorOfPropertyValuesHolder;
            objectAnimatorOfPropertyValuesHolder.setInterpolator(new LinearInterpolator());
            this.mAnimator.setDuration(PSEUDO_DURATION);
        }

        @Override // androidx.leanback.widget.ParallaxTarget
        public void update(float fraction) {
            this.mAnimator.setCurrentPlayTime((long) (1000000.0f * fraction));
        }
    }

    public static final class DirectPropertyTarget<T, V extends Number> extends ParallaxTarget {
        Object mObject;
        Property<T, V> mProperty;

        public DirectPropertyTarget(Object targetObject, Property<T, V> property) {
            this.mObject = targetObject;
            this.mProperty = property;
        }

        @Override // androidx.leanback.widget.ParallaxTarget
        public boolean isDirectMapping() {
            return true;
        }

        @Override // androidx.leanback.widget.ParallaxTarget
        public void directUpdate(Number number) {
            this.mProperty.set((T) this.mObject, (V) number);
        }
    }
}
