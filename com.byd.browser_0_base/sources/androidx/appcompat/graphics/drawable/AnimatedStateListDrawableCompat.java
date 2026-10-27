package androidx.appcompat.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.graphics.drawable.DrawableContainerCompat;
import androidx.appcompat.graphics.drawable.StateListDrawableCompat;
import defpackage.a8;
import defpackage.nq2;
import defpackage.oq2;
import defpackage.p5;
import defpackage.sa1;

/* JADX INFO: loaded from: classes.dex */
public class AnimatedStateListDrawableCompat extends StateListDrawableCompat {
    public AnimatedStateListState t;
    public Transition u;
    public int v;
    public int w;
    public boolean x;

    public static class AnimatableTransition extends Transition {
        public final Animatable a;

        public AnimatableTransition(Animatable animatable) {
            super(0);
            this.a = animatable;
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public final void c() {
            this.a.start();
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public final void d() {
            this.a.stop();
        }
    }

    public static class AnimatedStateListState extends StateListDrawableCompat.StateListState {

        /* JADX INFO: renamed from: J, reason: collision with root package name */
        public sa1<Long> f2J;
        public nq2<Integer> K;

        public AnimatedStateListState(AnimatedStateListState animatedStateListState, AnimatedStateListDrawableCompat animatedStateListDrawableCompat, Resources resources) {
            super(animatedStateListState, animatedStateListDrawableCompat, resources);
            if (animatedStateListState != null) {
                this.f2J = animatedStateListState.f2J;
                this.K = animatedStateListState.K;
            } else {
                this.f2J = new sa1<>();
                this.K = new nq2<>();
            }
        }

        @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat.StateListState, androidx.appcompat.graphics.drawable.DrawableContainerCompat.DrawableContainerState
        public final void d() {
            this.f2J = this.f2J.clone();
            this.K = this.K.clone();
        }

        public final int f(int i) {
            Object obj;
            if (i < 0) {
                return 0;
            }
            nq2<Integer> nq2Var = this.K;
            Object obj2 = 0;
            nq2Var.getClass();
            Object obj3 = oq2.a;
            int iJ = a8.j(nq2Var.g, i, nq2Var.e);
            if (iJ >= 0 && (obj = nq2Var.f[iJ]) != oq2.a) {
                obj2 = obj;
            }
            return ((Integer) obj2).intValue();
        }

        @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat.StateListState, android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            return new AnimatedStateListDrawableCompat(this, null);
        }

        @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat.StateListState, android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources) {
            return new AnimatedStateListDrawableCompat(this, resources);
        }
    }

    public static class AnimatedVectorDrawableTransition extends Transition {
        public final p5 a;

        public AnimatedVectorDrawableTransition(p5 p5Var) {
            super(0);
            this.a = p5Var;
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public final void c() {
            this.a.start();
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public final void d() {
            this.a.stop();
        }
    }

    public static class AnimationDrawableTransition extends Transition {
        public final ObjectAnimator a;
        public final boolean b;

        public AnimationDrawableTransition(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super(0);
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            FrameInterpolator frameInterpolator = new FrameInterpolator(animationDrawable, z);
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i, i2);
            objectAnimatorOfInt.setAutoCancel(true);
            objectAnimatorOfInt.setDuration(frameInterpolator.c);
            objectAnimatorOfInt.setInterpolator(frameInterpolator);
            this.b = z2;
            this.a = objectAnimatorOfInt;
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public final boolean a() {
            return this.b;
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public final void b() {
            this.a.reverse();
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public final void c() {
            this.a.start();
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public final void d() {
            this.a.cancel();
        }
    }

    public static class FrameInterpolator implements TimeInterpolator {
        public int[] a;
        public int b;
        public int c;

        public FrameInterpolator(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.b = numberOfFrames;
            int[] iArr = this.a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.a = new int[numberOfFrames];
            }
            int[] iArr2 = this.a;
            int i = 0;
            for (int i2 = 0; i2 < numberOfFrames; i2++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
                iArr2[i2] = duration;
                i += duration;
            }
            this.c = i;
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            int i = (int) ((f * this.c) + 0.5f);
            int i2 = this.b;
            int[] iArr = this.a;
            int i3 = 0;
            while (i3 < i2) {
                int i4 = iArr[i3];
                if (i < i4) {
                    break;
                }
                i -= i4;
                i3++;
            }
            return (i3 / i2) + (i3 < i2 ? i / this.c : 0.0f);
        }
    }

    public AnimatedStateListDrawableCompat() {
        this(null, null);
    }

    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat, androidx.appcompat.graphics.drawable.DrawableContainerCompat
    public final DrawableContainerCompat.DrawableContainerState b() {
        return new AnimatedStateListState(this.t, this, null);
    }

    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat, androidx.appcompat.graphics.drawable.DrawableContainerCompat
    public final void e(DrawableContainerCompat.DrawableContainerState drawableContainerState) {
        super.e(drawableContainerState);
        if (drawableContainerState instanceof AnimatedStateListState) {
            this.t = (AnimatedStateListState) drawableContainerState;
        }
    }

    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    /* JADX INFO: renamed from: f */
    public final StateListDrawableCompat.StateListState b() {
        return new AnimatedStateListState(this.t, this, null);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        super.jumpToCurrentState();
        Transition transition = this.u;
        if (transition != null) {
            transition.d();
            this.u = null;
            d(this.v);
            this.v = -1;
            this.w = -1;
        }
    }

    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat, androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.x) {
            super.mutate();
            this.t.d();
            this.x = true;
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e8  */
    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat, androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onStateChange(int[] r15) {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.onStateChange(int[]):boolean");
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Transition transition = this.u;
        if (transition != null && (visible || z2)) {
            if (z) {
                transition.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    public AnimatedStateListDrawableCompat(AnimatedStateListState animatedStateListState, Resources resources) {
        super(0);
        this.v = -1;
        this.w = -1;
        e(new AnimatedStateListState(animatedStateListState, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    public static abstract class Transition {
        private Transition() {
        }

        public /* synthetic */ Transition(int i) {
            this();
        }

        public boolean a() {
            return false;
        }

        public abstract void c();

        public abstract void d();

        public void b() {
        }
    }
}
