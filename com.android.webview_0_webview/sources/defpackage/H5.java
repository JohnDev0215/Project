package defpackage;

import android.graphics.Rect;
import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class H5 {
    public final W5 a;
    public int b = Integer.MIN_VALUE;
    public final Rect c = new Rect();

    public H5(W5 w5, F5 f5) {
        this.a = w5;
    }

    public static H5 a(W5 w5, int i) {
        if (i == 0) {
            return new F5(w5);
        }
        if (i == 1) {
            return new G5(w5);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int b(View view);

    public abstract int c(View view);

    public abstract int d();

    public abstract int e();

    public abstract int f();
}
