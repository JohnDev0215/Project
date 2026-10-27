package b.d.a.a.l;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a.e.h<String, h> f1979a = new a.e.h<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a.e.h<String, PropertyValuesHolder[]> f1980b = new a.e.h<>();

    public static g a(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
            return null;
        }
        return b(context, resourceId);
    }

    public static g b(Context context, int i) {
        try {
            Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, i);
            if (animatorLoadAnimator instanceof AnimatorSet) {
                return c(((AnimatorSet) animatorLoadAnimator).getChildAnimations());
            }
            if (animatorLoadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(animatorLoadAnimator);
            return c(arrayList);
        } catch (Exception e2) {
            StringBuilder sbD = b.a.a.a.a.d("Can't load animation resource ID #0x");
            sbD.append(Integer.toHexString(i));
            Log.w("MotionSpec", sbD.toString(), e2);
            return null;
        }
    }

    public static g c(List<Animator> list) {
        g gVar = new g();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Animator animator = list.get(i);
            if (!(animator instanceof ObjectAnimator)) {
                throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
            }
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            gVar.f1980b.put(objectAnimator.getPropertyName(), objectAnimator.getValues());
            String propertyName = objectAnimator.getPropertyName();
            long startDelay = objectAnimator.getStartDelay();
            long duration = objectAnimator.getDuration();
            TimeInterpolator interpolator = objectAnimator.getInterpolator();
            if ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) {
                interpolator = a.f1967b;
            } else if (interpolator instanceof AccelerateInterpolator) {
                interpolator = a.f1968c;
            } else if (interpolator instanceof DecelerateInterpolator) {
                interpolator = a.f1969d;
            }
            h hVar = new h(startDelay, duration, interpolator);
            hVar.f1984d = objectAnimator.getRepeatCount();
            hVar.f1985e = objectAnimator.getRepeatMode();
            gVar.f1979a.put(propertyName, hVar);
        }
        return gVar;
    }

    public h d(String str) {
        if (this.f1979a.getOrDefault(str, null) != null) {
            return this.f1979a.getOrDefault(str, null);
        }
        throw new IllegalArgumentException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g) {
            return this.f1979a.equals(((g) obj).f1979a);
        }
        return false;
    }

    public int hashCode() {
        return this.f1979a.hashCode();
    }

    public String toString() {
        return '\n' + g.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.f1979a + "}\n";
    }
}
