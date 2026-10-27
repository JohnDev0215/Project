package androidx.fragment.app;

import android.R;
import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import defpackage.os1;

/* JADX INFO: compiled from: FragmentAnim.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static int a(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.style.Animation.Activity, new int[]{i});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    public static class a {
        public final Animation a;
        public final Animator b;

        public a(Animation animation) {
            this.a = animation;
            this.b = null;
        }

        public a(Animator animator) {
            this.a = null;
            this.b = animator;
        }
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    public static class b extends AnimationSet implements Runnable {
        public final ViewGroup e;
        public final View f;
        public boolean g;
        public boolean h;
        public boolean i;

        public b(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.i = true;
            this.e = viewGroup;
            this.f = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public final boolean getTransformation(long j, Transformation transformation) {
            this.i = true;
            if (this.g) {
                return !this.h;
            }
            if (!super.getTransformation(j, transformation)) {
                this.g = true;
                os1.a(this.e, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean z = this.g;
            ViewGroup viewGroup = this.e;
            if (z || !this.i) {
                viewGroup.endViewTransition(this.f);
                this.h = true;
            } else {
                this.i = false;
                viewGroup.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public final boolean getTransformation(long j, Transformation transformation, float f) {
            this.i = true;
            if (this.g) {
                return !this.h;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.g = true;
                os1.a(this.e, this);
            }
            return true;
        }
    }
}
