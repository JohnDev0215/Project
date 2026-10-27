package L4;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import h6.n;
import java.util.ArrayList;
import u.C2458F;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2458F f4009a = new C2458F(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2458F f4010b = new C2458F(0);

    public static b a(Context context, int i7) {
        try {
            Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, i7);
            if (animatorLoadAnimator instanceof AnimatorSet) {
                return b(((AnimatorSet) animatorLoadAnimator).getChildAnimations());
            }
            if (animatorLoadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(animatorLoadAnimator);
            return b(arrayList);
        } catch (Exception e10) {
            n.u("MotionSpec", "Can't load animation resource ID #0x" + Integer.toHexString(i7), e10);
            return null;
        }
    }

    public static b b(ArrayList arrayList) {
        b bVar = new b();
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            Animator animator = (Animator) arrayList.get(i7);
            if (!(animator instanceof ObjectAnimator)) {
                throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
            }
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            bVar.f4010b.put(objectAnimator.getPropertyName(), objectAnimator.getValues());
            String propertyName = objectAnimator.getPropertyName();
            long startDelay = objectAnimator.getStartDelay();
            long duration = objectAnimator.getDuration();
            TimeInterpolator interpolator = objectAnimator.getInterpolator();
            if ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) {
                interpolator = a.f4008b;
            } else if (interpolator instanceof AccelerateInterpolator) {
                interpolator = a.c;
            } else if (interpolator instanceof DecelerateInterpolator) {
                interpolator = a.d;
            }
            c cVar = new c();
            cVar.d = 0;
            cVar.f4013e = 1;
            cVar.f4011a = startDelay;
            cVar.f4012b = duration;
            cVar.c = interpolator;
            cVar.d = objectAnimator.getRepeatCount();
            cVar.f4013e = objectAnimator.getRepeatMode();
            bVar.f4009a.put(propertyName, cVar);
        }
        return bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            return this.f4009a.equals(((b) obj).f4009a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f4009a.hashCode();
    }

    public final String toString() {
        return "\n" + b.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.f4009a + "}\n";
    }
}
