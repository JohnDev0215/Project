package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class S7 extends Drawable.ConstantState {
    public int a;
    public R7 b;
    public ColorStateList c;
    public PorterDuff.Mode d;
    public boolean e;
    public Bitmap f;
    public ColorStateList g;
    public PorterDuff.Mode h;
    public int i;
    public boolean j;
    public boolean k;
    public Paint l;

    public S7(S7 s7) {
        this.c = null;
        this.d = U7.B;
        if (s7 != null) {
            this.a = s7.a;
            R7 r7 = new R7(s7.b);
            this.b = r7;
            if (s7.b.e != null) {
                r7.e = new Paint(s7.b.e);
            }
            if (s7.b.d != null) {
                this.b.d = new Paint(s7.b.d);
            }
            this.c = s7.c;
            this.d = s7.d;
            this.e = s7.e;
        }
    }

    public boolean a() {
        R7 r7 = this.b;
        if (r7.o == null) {
            r7.o = Boolean.valueOf(r7.h.a());
        }
        return r7.o.booleanValue();
    }

    public void b(int i, int i2) {
        this.f.eraseColor(0);
        Canvas canvas = new Canvas(this.f);
        R7 r7 = this.b;
        r7.a(r7.h, R7.q, canvas, i, i2, null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.a;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        return new U7(this);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        return new U7(this);
    }

    public S7() {
        this.c = null;
        this.d = U7.B;
        this.b = new R7();
    }
}
