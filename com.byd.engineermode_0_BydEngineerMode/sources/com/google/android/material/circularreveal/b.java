package com.google.android.material.circularreveal;

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
import com.google.android.material.circularreveal.c;

/* JADX INFO: compiled from: CircularRevealHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f2666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f2667b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final View f2668c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Path f2669d;
    private final Paint e;
    private final Paint f;
    private c.e g;
    private Drawable h;
    private boolean i;
    private boolean j;

    /* JADX INFO: compiled from: CircularRevealHelper.java */
    public interface a {
        void a(Canvas canvas);

        boolean d();
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            f2666a = 2;
        } else if (i >= 18) {
            f2666a = 1;
        } else {
            f2666a = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f2667b = aVar;
        View view = (View) aVar;
        this.f2668c = view;
        view.setWillNotDraw(false);
        this.f2669d = new Path();
        this.e = new Paint(7);
        Paint paint = new Paint(1);
        this.f = paint;
        paint.setColor(0);
    }

    private void d(Canvas canvas) {
        if (o()) {
            Rect bounds = this.h.getBounds();
            float fWidth = this.g.f2674a - (bounds.width() / 2.0f);
            float fHeight = this.g.f2675b - (bounds.height() / 2.0f);
            canvas.translate(fWidth, fHeight);
            this.h.draw(canvas);
            canvas.translate(-fWidth, -fHeight);
        }
    }

    private float g(c.e eVar) {
        return c.b.a.a.s.a.b(eVar.f2674a, eVar.f2675b, 0.0f, 0.0f, this.f2668c.getWidth(), this.f2668c.getHeight());
    }

    private void i() {
        if (f2666a == 1) {
            this.f2669d.rewind();
            c.e eVar = this.g;
            if (eVar != null) {
                this.f2669d.addCircle(eVar.f2674a, eVar.f2675b, eVar.f2676c, Path.Direction.CW);
            }
        }
        this.f2668c.invalidate();
    }

    private boolean n() {
        c.e eVar = this.g;
        boolean z = eVar == null || eVar.a();
        return f2666a == 0 ? !z && this.j : !z;
    }

    private boolean o() {
        return (this.i || this.h == null || this.g == null) ? false : true;
    }

    private boolean p() {
        return (this.i || Color.alpha(this.f.getColor()) == 0) ? false : true;
    }

    public void a() {
        if (f2666a == 0) {
            this.i = true;
            this.j = false;
            this.f2668c.buildDrawingCache();
            Bitmap drawingCache = this.f2668c.getDrawingCache();
            if (drawingCache == null && this.f2668c.getWidth() != 0 && this.f2668c.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.f2668c.getWidth(), this.f2668c.getHeight(), Bitmap.Config.ARGB_8888);
                this.f2668c.draw(new Canvas(drawingCache));
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
        if (f2666a == 0) {
            this.j = false;
            this.f2668c.destroyDrawingCache();
            this.e.setShader(null);
            this.f2668c.invalidate();
        }
    }

    public void c(Canvas canvas) {
        if (n()) {
            int i = f2666a;
            if (i == 0) {
                c.e eVar = this.g;
                canvas.drawCircle(eVar.f2674a, eVar.f2675b, eVar.f2676c, this.e);
                if (p()) {
                    c.e eVar2 = this.g;
                    canvas.drawCircle(eVar2.f2674a, eVar2.f2675b, eVar2.f2676c, this.f);
                }
            } else if (i == 1) {
                int iSave = canvas.save();
                canvas.clipPath(this.f2669d);
                this.f2667b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f2668c.getWidth(), this.f2668c.getHeight(), this.f);
                }
                canvas.restoreToCount(iSave);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("Unsupported strategy " + i);
                }
                this.f2667b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f2668c.getWidth(), this.f2668c.getHeight(), this.f);
                }
            }
        } else {
            this.f2667b.a(canvas);
            if (p()) {
                canvas.drawRect(0.0f, 0.0f, this.f2668c.getWidth(), this.f2668c.getHeight(), this.f);
            }
        }
        d(canvas);
    }

    public Drawable e() {
        return this.h;
    }

    public int f() {
        return this.f.getColor();
    }

    public c.e h() {
        c.e eVar = this.g;
        if (eVar == null) {
            return null;
        }
        c.e eVar2 = new c.e(eVar);
        if (eVar2.a()) {
            eVar2.f2676c = g(eVar2);
        }
        return eVar2;
    }

    public boolean j() {
        return this.f2667b.d() && !n();
    }

    public void k(Drawable drawable) {
        this.h = drawable;
        this.f2668c.invalidate();
    }

    public void l(int i) {
        this.f.setColor(i);
        this.f2668c.invalidate();
    }

    public void m(c.e eVar) {
        if (eVar == null) {
            this.g = null;
        } else {
            c.e eVar2 = this.g;
            if (eVar2 == null) {
                this.g = new c.e(eVar);
            } else {
                eVar2.c(eVar);
            }
            if (c.b.a.a.s.a.c(eVar.f2676c, g(eVar), 1.0E-4f)) {
                this.g.f2676c = Float.MAX_VALUE;
            }
        }
        i();
    }
}
