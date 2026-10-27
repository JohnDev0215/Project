package defpackage;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class A extends C {
    public final ObjectAnimator a;
    public final boolean b;

    public A(AnimationDrawable animationDrawable, boolean z, boolean z2) {
        super(null);
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        int i = z ? numberOfFrames - 1 : 0;
        int i2 = z ? 0 : numberOfFrames - 1;
        B b = new B(animationDrawable, z);
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i, i2);
        objectAnimatorOfInt.setAutoCancel(true);
        objectAnimatorOfInt.setDuration(b.c);
        objectAnimatorOfInt.setInterpolator(b);
        this.b = z2;
        this.a = objectAnimatorOfInt;
    }

    @Override // defpackage.C
    public boolean a() {
        return this.b;
    }

    @Override // defpackage.C
    public void b() {
        this.a.reverse();
    }

    @Override // defpackage.C
    public void c() {
        this.a.start();
    }

    @Override // defpackage.C
    public void d() {
        this.a.cancel();
    }
}
