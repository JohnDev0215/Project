package a.l.d;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

/* JADX INFO: loaded from: classes.dex */
public class j extends AnimationSet implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewGroup f1002a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f1003b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1004c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1005d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1006e;

    public j(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.f1006e = true;
        this.f1002a = viewGroup;
        this.f1003b = view;
        addAnimation(animation);
        this.f1002a.post(this);
    }

    @Override // android.view.animation.AnimationSet, android.view.animation.Animation
    public boolean getTransformation(long j, Transformation transformation) {
        this.f1006e = true;
        if (this.f1004c) {
            return !this.f1005d;
        }
        if (!super.getTransformation(j, transformation)) {
            this.f1004c = true;
            a.h.k.l.a(this.f1002a, this);
        }
        return true;
    }

    @Override // android.view.animation.Animation
    public boolean getTransformation(long j, Transformation transformation, float f2) {
        this.f1006e = true;
        if (this.f1004c) {
            return !this.f1005d;
        }
        if (!super.getTransformation(j, transformation, f2)) {
            this.f1004c = true;
            a.h.k.l.a(this.f1002a, this);
        }
        return true;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1004c || !this.f1006e) {
            this.f1002a.endViewTransition(this.f1003b);
            this.f1005d = true;
        } else {
            this.f1006e = false;
            this.f1002a.post(this);
        }
    }
}
