package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

/* JADX INFO: renamed from: d4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0640d4 extends AnimationSet implements Runnable {
    public final ViewGroup s;
    public final View t;
    public boolean u;
    public boolean v;
    public boolean w;

    public RunnableC0640d4(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.w = true;
        this.s = viewGroup;
        this.t = view;
        addAnimation(animation);
        this.s.post(this);
    }

    @Override // android.view.animation.AnimationSet, android.view.animation.Animation
    public boolean getTransformation(long j, Transformation transformation) {
        this.w = true;
        if (this.u) {
            return !this.v;
        }
        if (!super.getTransformation(j, transformation)) {
            this.u = true;
            D4.a(this.s, this);
        }
        return true;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.u || !this.w) {
            this.s.endViewTransition(this.t);
            this.v = true;
        } else {
            this.w = false;
            this.s.post(this);
        }
    }

    @Override // android.view.animation.Animation
    public boolean getTransformation(long j, Transformation transformation, float f) {
        this.w = true;
        if (this.u) {
            return !this.v;
        }
        if (!super.getTransformation(j, transformation, f)) {
            this.u = true;
            D4.a(this.s, this);
        }
        return true;
    }
}
