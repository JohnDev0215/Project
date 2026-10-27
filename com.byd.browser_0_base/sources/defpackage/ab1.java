package defpackage;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import org.chromium.content.browser.selection.a;

/* JADX INFO: compiled from: MagnifierAnimator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ab1 {
    public final bb1 a;
    public ValueAnimator b;
    public boolean c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;

    public ab1(a aVar) {
        this.a = aVar;
        a();
        this.h = -1.0f;
        this.i = -1.0f;
    }

    public final void a() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.b = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(100L);
        this.b.setInterpolator(new LinearInterpolator());
        this.b.addUpdateListener(new ut(4, this));
    }
}
