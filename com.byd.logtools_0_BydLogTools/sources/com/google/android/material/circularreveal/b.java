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
    public static final int f3495a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f3496b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final View f3497c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Path f3498d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Paint f3499e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Paint f3500f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private c.e f3501g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Drawable f3502h;
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
            f3495a = 2;
        } else if (i >= 18) {
            f3495a = 1;
        } else {
            f3495a = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f3496b = aVar;
        View view = (View) aVar;
        this.f3497c = view;
        view.setWillNotDraw(false);
        this.f3498d = new Path();
        this.f3499e = new Paint(7);
        Paint paint = new Paint(1);
        this.f3500f = paint;
        paint.setColor(0);
    }

    private void d(Canvas canvas) {
        if (o()) {
            Rect bounds = this.f3502h.getBounds();
            float fWidth = this.f3501g.f3507a - (bounds.width() / 2.0f);
            float fHeight = this.f3501g.f3508b - (bounds.height() / 2.0f);
            canvas.translate(fWidth, fHeight);
            this.f3502h.draw(canvas);
            canvas.translate(-fWidth, -fHeight);
        }
    }

    private float g(c.e eVar) {
        return d.b.a.a.s.a.b(eVar.f3507a, eVar.f3508b, 0.0f, 0.0f, this.f3497c.getWidth(), this.f3497c.getHeight());
    }

    private void i() {
        if (f3495a == 1) {
            this.f3498d.rewind();
            c.e eVar = this.f3501g;
            if (eVar != null) {
                this.f3498d.addCircle(eVar.f3507a, eVar.f3508b, eVar.f3509c, Path.Direction.CW);
            }
        }
        this.f3497c.invalidate();
    }

    private boolean n() {
        c.e eVar = this.f3501g;
        boolean z = eVar == null || eVar.a();
        return f3495a == 0 ? !z && this.j : !z;
    }

    private boolean o() {
        return (this.i || this.f3502h == null || this.f3501g == null) ? false : true;
    }

    private boolean p() {
        return (this.i || Color.alpha(this.f3500f.getColor()) == 0) ? false : true;
    }

    public void a() {
        if (f3495a == 0) {
            this.i = true;
            this.j = false;
            this.f3497c.buildDrawingCache();
            Bitmap drawingCache = this.f3497c.getDrawingCache();
            if (drawingCache == null && this.f3497c.getWidth() != 0 && this.f3497c.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.f3497c.getWidth(), this.f3497c.getHeight(), Bitmap.Config.ARGB_8888);
                this.f3497c.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.f3499e;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.i = false;
            this.j = true;
        }
    }

    public void b() {
        if (f3495a == 0) {
            this.j = false;
            this.f3497c.destroyDrawingCache();
            this.f3499e.setShader(null);
            this.f3497c.invalidate();
        }
    }

    public void c(Canvas canvas) {
        if (n()) {
            int i = f3495a;
            if (i == 0) {
                c.e eVar = this.f3501g;
                canvas.drawCircle(eVar.f3507a, eVar.f3508b, eVar.f3509c, this.f3499e);
                if (p()) {
                    c.e eVar2 = this.f3501g;
                    canvas.drawCircle(eVar2.f3507a, eVar2.f3508b, eVar2.f3509c, this.f3500f);
                }
            } else if (i == 1) {
                int iSave = canvas.save();
                canvas.clipPath(this.f3498d);
                this.f3496b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f3497c.getWidth(), this.f3497c.getHeight(), this.f3500f);
                }
                canvas.restoreToCount(iSave);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("Unsupported strategy " + i);
                }
                this.f3496b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f3497c.getWidth(), this.f3497c.getHeight(), this.f3500f);
                }
            }
        } else {
            this.f3496b.a(canvas);
            if (p()) {
                canvas.drawRect(0.0f, 0.0f, this.f3497c.getWidth(), this.f3497c.getHeight(), this.f3500f);
            }
        }
        d(canvas);
    }

    public Drawable e() {
        return this.f3502h;
    }

    public int f() {
        return this.f3500f.getColor();
    }

    public c.e h() {
        c.e eVar = this.f3501g;
        if (eVar == null) {
            return null;
        }
        c.e eVar2 = new c.e(eVar);
        if (eVar2.a()) {
            eVar2.f3509c = g(eVar2);
        }
        return eVar2;
    }

    public boolean j() {
        return this.f3496b.d() && !n();
    }

    public void k(Drawable drawable) {
        this.f3502h = drawable;
        this.f3497c.invalidate();
    }

    public void l(int i) {
        this.f3500f.setColor(i);
        this.f3497c.invalidate();
    }

    public void m(c.e eVar) {
        if (eVar == null) {
            this.f3501g = null;
        } else {
            c.e eVar2 = this.f3501g;
            if (eVar2 == null) {
                this.f3501g = new c.e(eVar);
            } else {
                eVar2.c(eVar);
            }
            if (d.b.a.a.s.a.c(eVar.f3509c, g(eVar), 1.0E-4f)) {
                this.f3501g.f3509c = Float.MAX_VALUE;
            }
        }
        i();
    }
}
