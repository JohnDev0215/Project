package defpackage;

import android.animation.TimeInterpolator;
import android.graphics.drawable.AnimationDrawable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class B implements TimeInterpolator {
    public int[] a;
    public int b;
    public int c;

    public B(AnimationDrawable animationDrawable, boolean z) {
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
    public float getInterpolation(float f) {
        int i = (int) ((f * this.c) + 0.5f);
        int i2 = this.b;
        int[] iArr = this.a;
        int i3 = 0;
        while (i3 < i2 && i >= iArr[i3]) {
            i -= iArr[i3];
            i3++;
        }
        return (i3 / i2) + (i3 < i2 ? i / this.c : 0.0f);
    }
}
