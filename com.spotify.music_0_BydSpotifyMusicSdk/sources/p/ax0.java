package p;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

/* JADX INFO: loaded from: classes.dex */
public final class ax0 extends AnimationSet implements Runnable {
    public final ViewGroup c;
    public final View e;
    public boolean k;
    public boolean l;
    public boolean m;

    public ax0(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.m = true;
        this.c = viewGroup;
        this.e = view;
        addAnimation(animation);
        viewGroup.post(this);
    }

    @Override // android.view.animation.AnimationSet, android.view.animation.Animation
    public final boolean getTransformation(long j, Transformation transformation) {
        this.m = true;
        if (this.k) {
            return !this.l;
        }
        if (!super.getTransformation(j, transformation)) {
            this.k = true;
            kq1.a(this.c, this);
        }
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = this.k;
        ViewGroup viewGroup = this.c;
        if (z || !this.m) {
            viewGroup.endViewTransition(this.e);
            this.l = true;
        } else {
            this.m = false;
            viewGroup.post(this);
        }
    }

    @Override // android.view.animation.Animation
    public final boolean getTransformation(long j, Transformation transformation, float f) {
        this.m = true;
        if (this.k) {
            return !this.l;
        }
        if (!super.getTransformation(j, transformation, f)) {
            this.k = true;
            kq1.a(this.c, this);
        }
        return true;
    }
}
