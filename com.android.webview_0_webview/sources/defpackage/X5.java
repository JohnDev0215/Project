package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class X5 extends ViewGroup.MarginLayoutParams {
    public final Rect a;
    public boolean b;
    public boolean c;

    public X5(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Rect();
        this.b = true;
        this.c = false;
    }

    public X5(int i, int i2) {
        super(i, i2);
        this.a = new Rect();
        this.b = true;
        this.c = false;
    }

    public X5(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.a = new Rect();
        this.b = true;
        this.c = false;
    }

    public X5(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.a = new Rect();
        this.b = true;
        this.c = false;
    }

    public X5(X5 x5) {
        super((ViewGroup.LayoutParams) x5);
        this.a = new Rect();
        this.b = true;
        this.c = false;
    }
}
