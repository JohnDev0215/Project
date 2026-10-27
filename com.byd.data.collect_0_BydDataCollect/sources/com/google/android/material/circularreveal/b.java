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
    public static final int f3889a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f3890b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final View f3891c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Path f3892d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Paint f3893e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Paint f3894f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private c.e f3895g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Drawable f3896h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f3897i;
    private boolean j;

    /* JADX INFO: compiled from: CircularRevealHelper.java */
    public interface a {
        void a(Canvas canvas);

        boolean d();
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f3889a = 2;
        } else if (i2 >= 18) {
            f3889a = 1;
        } else {
            f3889a = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f3890b = aVar;
        View view = (View) aVar;
        this.f3891c = view;
        view.setWillNotDraw(false);
        this.f3892d = new Path();
        this.f3893e = new Paint(7);
        Paint paint = new Paint(1);
        this.f3894f = paint;
        paint.setColor(0);
    }

    private void d(Canvas canvas) {
        if (o()) {
            Rect bounds = this.f3896h.getBounds();
            float fWidth = this.f3895g.f3902a - (bounds.width() / 2.0f);
            float fHeight = this.f3895g.f3903b - (bounds.height() / 2.0f);
            canvas.translate(fWidth, fHeight);
            this.f3896h.draw(canvas);
            canvas.translate(-fWidth, -fHeight);
        }
    }

    private float g(c.e eVar) {
        return d.b.a.a.t.a.b(eVar.f3902a, eVar.f3903b, 0.0f, 0.0f, this.f3891c.getWidth(), this.f3891c.getHeight());
    }

    private void i() {
        if (f3889a == 1) {
            this.f3892d.rewind();
            c.e eVar = this.f3895g;
            if (eVar != null) {
                this.f3892d.addCircle(eVar.f3902a, eVar.f3903b, eVar.f3904c, Path.Direction.CW);
            }
        }
        this.f3891c.invalidate();
    }

    private boolean n() {
        c.e eVar = this.f3895g;
        boolean z = eVar == null || eVar.a();
        return f3889a == 0 ? !z && this.j : !z;
    }

    private boolean o() {
        return (this.f3897i || this.f3896h == null || this.f3895g == null) ? false : true;
    }

    private boolean p() {
        return (this.f3897i || Color.alpha(this.f3894f.getColor()) == 0) ? false : true;
    }

    public void a() {
        if (f3889a == 0) {
            this.f3897i = true;
            this.j = false;
            this.f3891c.buildDrawingCache();
            Bitmap drawingCache = this.f3891c.getDrawingCache();
            if (drawingCache == null && this.f3891c.getWidth() != 0 && this.f3891c.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.f3891c.getWidth(), this.f3891c.getHeight(), Bitmap.Config.ARGB_8888);
                this.f3891c.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.f3893e;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.f3897i = false;
            this.j = true;
        }
    }

    public void b() {
        if (f3889a == 0) {
            this.j = false;
            this.f3891c.destroyDrawingCache();
            this.f3893e.setShader(null);
            this.f3891c.invalidate();
        }
    }

    public void c(Canvas canvas) {
        if (n()) {
            int i2 = f3889a;
            if (i2 == 0) {
                c.e eVar = this.f3895g;
                canvas.drawCircle(eVar.f3902a, eVar.f3903b, eVar.f3904c, this.f3893e);
                if (p()) {
                    c.e eVar2 = this.f3895g;
                    canvas.drawCircle(eVar2.f3902a, eVar2.f3903b, eVar2.f3904c, this.f3894f);
                }
            } else if (i2 == 1) {
                int iSave = canvas.save();
                canvas.clipPath(this.f3892d);
                this.f3890b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f3891c.getWidth(), this.f3891c.getHeight(), this.f3894f);
                }
                canvas.restoreToCount(iSave);
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("Unsupported strategy " + i2);
                }
                this.f3890b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f3891c.getWidth(), this.f3891c.getHeight(), this.f3894f);
                }
            }
        } else {
            this.f3890b.a(canvas);
            if (p()) {
                canvas.drawRect(0.0f, 0.0f, this.f3891c.getWidth(), this.f3891c.getHeight(), this.f3894f);
            }
        }
        d(canvas);
    }

    public Drawable e() {
        return this.f3896h;
    }

    public int f() {
        return this.f3894f.getColor();
    }

    public c.e h() {
        c.e eVar = this.f3895g;
        if (eVar == null) {
            return null;
        }
        c.e eVar2 = new c.e(eVar);
        if (eVar2.a()) {
            eVar2.f3904c = g(eVar2);
        }
        return eVar2;
    }

    public boolean j() {
        return this.f3890b.d() && !n();
    }

    public void k(Drawable drawable) {
        this.f3896h = drawable;
        this.f3891c.invalidate();
    }

    public void l(int i2) {
        this.f3894f.setColor(i2);
        this.f3891c.invalidate();
    }

    public void m(c.e eVar) {
        if (eVar == null) {
            this.f3895g = null;
        } else {
            c.e eVar2 = this.f3895g;
            if (eVar2 == null) {
                this.f3895g = new c.e(eVar);
            } else {
                eVar2.c(eVar);
            }
            if (d.b.a.a.t.a.c(eVar.f3904c, g(eVar), 1.0E-4f)) {
                this.f3895g.f3904c = Float.MAX_VALUE;
            }
        }
        i();
    }
}
