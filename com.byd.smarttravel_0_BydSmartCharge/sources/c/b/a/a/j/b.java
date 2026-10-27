package c.b.a.a.j;

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
import c.b.a.a.j.c;

/* JADX INFO: compiled from: CircularRevealHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f1697a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1698b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f1699c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Path f1700d;
    public final Paint e;
    public final Paint f;
    public c.e g;
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
            f1697a = 2;
        } else if (i >= 18) {
            f1697a = 1;
        } else {
            f1697a = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f1698b = aVar;
        View view = (View) aVar;
        this.f1699c = view;
        view.setWillNotDraw(false);
        this.f1700d = new Path();
        this.e = new Paint(7);
        Paint paint = new Paint(1);
        this.f = paint;
        paint.setColor(0);
    }

    public void a() {
        if (f1697a == 0) {
            this.i = true;
            this.j = false;
            this.f1699c.buildDrawingCache();
            Bitmap drawingCache = this.f1699c.getDrawingCache();
            if (drawingCache == null && this.f1699c.getWidth() != 0 && this.f1699c.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.f1699c.getWidth(), this.f1699c.getHeight(), Bitmap.Config.ARGB_8888);
                this.f1699c.draw(new Canvas(drawingCache));
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
        if (f1697a == 0) {
            this.j = false;
            this.f1699c.destroyDrawingCache();
            this.e.setShader(null);
            this.f1699c.invalidate();
        }
    }

    public void c(Canvas canvas) {
        if (n()) {
            int i = f1697a;
            if (i == 0) {
                c.e eVar = this.g;
                canvas.drawCircle(eVar.f1705a, eVar.f1706b, eVar.f1707c, this.e);
                if (p()) {
                    c.e eVar2 = this.g;
                    canvas.drawCircle(eVar2.f1705a, eVar2.f1706b, eVar2.f1707c, this.f);
                }
            } else if (i == 1) {
                int iSave = canvas.save();
                canvas.clipPath(this.f1700d);
                this.f1698b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f1699c.getWidth(), this.f1699c.getHeight(), this.f);
                }
                canvas.restoreToCount(iSave);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("Unsupported strategy " + i);
                }
                this.f1698b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f1699c.getWidth(), this.f1699c.getHeight(), this.f);
                }
            }
        } else {
            this.f1698b.a(canvas);
            if (p()) {
                canvas.drawRect(0.0f, 0.0f, this.f1699c.getWidth(), this.f1699c.getHeight(), this.f);
            }
        }
        d(canvas);
    }

    public final void d(Canvas canvas) {
        if (o()) {
            Rect bounds = this.h.getBounds();
            float fWidth = this.g.f1705a - (bounds.width() / 2.0f);
            float fHeight = this.g.f1706b - (bounds.height() / 2.0f);
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

    public final float g(c.e eVar) {
        return c.b.a.a.r.a.b(eVar.f1705a, eVar.f1706b, 0.0f, 0.0f, this.f1699c.getWidth(), this.f1699c.getHeight());
    }

    public c.e h() {
        c.e eVar = this.g;
        if (eVar == null) {
            return null;
        }
        c.e eVar2 = new c.e(eVar);
        if (eVar2.a()) {
            eVar2.f1707c = g(eVar2);
        }
        return eVar2;
    }

    public final void i() {
        if (f1697a == 1) {
            this.f1700d.rewind();
            c.e eVar = this.g;
            if (eVar != null) {
                this.f1700d.addCircle(eVar.f1705a, eVar.f1706b, eVar.f1707c, Path.Direction.CW);
            }
        }
        this.f1699c.invalidate();
    }

    public boolean j() {
        return this.f1698b.d() && !n();
    }

    public void k(Drawable drawable) {
        this.h = drawable;
        this.f1699c.invalidate();
    }

    public void l(int i) {
        this.f.setColor(i);
        this.f1699c.invalidate();
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
            if (c.b.a.a.r.a.c(eVar.f1707c, g(eVar), 1.0E-4f)) {
                this.g.f1707c = Float.MAX_VALUE;
            }
        }
        i();
    }

    public final boolean n() {
        c.e eVar = this.g;
        boolean z = eVar == null || eVar.a();
        return f1697a == 0 ? !z && this.j : !z;
    }

    public final boolean o() {
        return (this.i || this.h == null || this.g == null) ? false : true;
    }

    public final boolean p() {
        return (this.i || Color.alpha(this.f.getColor()) == 0) ? false : true;
    }
}
