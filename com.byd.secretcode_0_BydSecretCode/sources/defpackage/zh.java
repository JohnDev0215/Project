package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import defpackage.ai;

/* JADX INFO: compiled from: CircularRevealHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class zh {
    public static final int a;
    public final a b;
    public final View c;
    public final Path d;
    public final Paint e;
    public final Paint f;
    public ai.e g;
    public Drawable h;
    public boolean i;
    public boolean j;

    /* JADX INFO: compiled from: CircularRevealHelper.java */
    public interface a {
        void a(Canvas canvas);

        boolean d();
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            a = 2;
        } else if (i >= 18) {
            a = 1;
        } else {
            a = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public zh(a aVar) {
        this.b = aVar;
        View view = (View) aVar;
        this.c = view;
        view.setWillNotDraw(false);
        this.d = new Path();
        this.e = new Paint(7);
        Paint paint = new Paint(1);
        this.f = paint;
        paint.setColor(0);
    }

    public void a() {
        if (a == 0) {
            this.i = true;
            this.j = false;
            this.c.buildDrawingCache();
            Bitmap drawingCache = this.c.getDrawingCache();
            if (drawingCache == null && this.c.getWidth() != 0 && this.c.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.c.getWidth(), this.c.getHeight(), Bitmap.Config.ARGB_8888);
                this.c.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.e;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.i = false;
            this.j = true;
        }
    }

    public void b() {
        if (a == 0) {
            this.j = false;
            this.c.destroyDrawingCache();
            this.e.setShader(null);
            this.c.invalidate();
        }
    }

    public void c(Canvas canvas) {
        if (n()) {
            int i = a;
            if (i == 0) {
                ai.e eVar = this.g;
                canvas.drawCircle(eVar.a, eVar.b, eVar.c, this.e);
                if (p()) {
                    ai.e eVar2 = this.g;
                    canvas.drawCircle(eVar2.a, eVar2.b, eVar2.c, this.f);
                }
            } else if (i == 1) {
                int iSave = canvas.save();
                canvas.clipPath(this.d);
                this.b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.c.getWidth(), this.c.getHeight(), this.f);
                }
                canvas.restoreToCount(iSave);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("Unsupported strategy " + i);
                }
                this.b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.c.getWidth(), this.c.getHeight(), this.f);
                }
            }
        } else {
            this.b.a(canvas);
            if (p()) {
                canvas.drawRect(0.0f, 0.0f, this.c.getWidth(), this.c.getHeight(), this.f);
            }
        }
        d(canvas);
    }

    public final void d(Canvas canvas) {
        if (o()) {
            Rect bounds = this.h.getBounds();
            float fWidth = this.g.a - (bounds.width() / 2.0f);
            float fHeight = this.g.b - (bounds.height() / 2.0f);
            canvas.translate(fWidth, fHeight);
            this.h.draw(canvas);
            canvas.translate(-fWidth, -fHeight);
        }
    }

    public Drawable e() {
        return this.h;
    }

    public int f() {
        return this.f.getColor();
    }

    public final float g(ai.e eVar) {
        return pj.b(eVar.a, eVar.b, 0.0f, 0.0f, this.c.getWidth(), this.c.getHeight());
    }

    public ai.e h() {
        ai.e eVar = this.g;
        if (eVar == null) {
            return null;
        }
        ai.e eVar2 = new ai.e(eVar);
        if (eVar2.a()) {
            eVar2.c = g(eVar2);
        }
        return eVar2;
    }

    public final void i() {
        if (a == 1) {
            this.d.rewind();
            ai.e eVar = this.g;
            if (eVar != null) {
                this.d.addCircle(eVar.a, eVar.b, eVar.c, Path.Direction.CW);
            }
        }
        this.c.invalidate();
    }

    public boolean j() {
        return this.b.d() && !n();
    }

    public void k(Drawable drawable) {
        this.h = drawable;
        this.c.invalidate();
    }

    public void l(int i) {
        this.f.setColor(i);
        this.c.invalidate();
    }

    public void m(ai.e eVar) {
        if (eVar == null) {
            this.g = null;
        } else {
            ai.e eVar2 = this.g;
            if (eVar2 == null) {
                this.g = new ai.e(eVar);
            } else {
                eVar2.c(eVar);
            }
            if (pj.c(eVar.c, g(eVar), 1.0E-4f)) {
                this.g.c = Float.MAX_VALUE;
            }
        }
        i();
    }

    public final boolean n() {
        ai.e eVar = this.g;
        boolean z = eVar == null || eVar.a();
        return a == 0 ? !z && this.j : !z;
    }

    public final boolean o() {
        return (this.i || this.h == null || this.g == null) ? false : true;
    }

    public final boolean p() {
        return (this.i || Color.alpha(this.f.getColor()) == 0) ? false : true;
    }
}
