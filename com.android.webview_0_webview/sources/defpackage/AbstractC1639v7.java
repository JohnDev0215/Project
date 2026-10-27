package defpackage;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: renamed from: v7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1639v7 {
    public abstract void a(View view);

    public float b(View view) {
        Float f = (Float) view.getTag(AbstractC0371Um.m3);
        return f != null ? view.getAlpha() / f.floatValue() : view.getAlpha();
    }

    public abstract void c(View view);

    public void d(View view, int i, int i2, int i3, int i4) {
        view.setLeft(i);
        view.setTop(i2);
        view.setRight(i3);
        view.setBottom(i4);
    }

    public abstract void e(View view, float f);

    public abstract void f(View view, Matrix matrix);

    public abstract void g(View view, Matrix matrix);
}
