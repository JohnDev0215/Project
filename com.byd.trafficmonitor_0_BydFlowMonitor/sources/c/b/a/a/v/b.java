package c.b.a.a.v;

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
import c.b.a.a.v.c;

/* JADX INFO: compiled from: CircularRevealHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f4084a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f4085b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f4086c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Path f4087d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f4088e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f4089f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.e f4090g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f4091h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4092i;
    public boolean j;

    /* JADX INFO: compiled from: CircularRevealHelper.java */
    public interface a {
        void a(Canvas canvas);

        boolean d();
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f4084a = 2;
        } else if (i2 >= 18) {
            f4084a = 1;
        } else {
            f4084a = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f4085b = aVar;
        View view = (View) aVar;
        this.f4086c = view;
        view.setWillNotDraw(false);
        this.f4087d = new Path();
        this.f4088e = new Paint(7);
        Paint paint = new Paint(1);
        this.f4089f = paint;
        paint.setColor(0);
    }

    public void a() {
        if (f4084a == 0) {
            this.f4092i = true;
            this.j = false;
            this.f4086c.buildDrawingCache();
            Bitmap drawingCache = this.f4086c.getDrawingCache();
            if (drawingCache == null && this.f4086c.getWidth() != 0 && this.f4086c.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.f4086c.getWidth(), this.f4086c.getHeight(), Bitmap.Config.ARGB_8888);
                this.f4086c.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.f4088e;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.f4092i = false;
            this.j = true;
        }
    }

    public void b() {
        if (f4084a == 0) {
            this.j = false;
            this.f4086c.destroyDrawingCache();
            this.f4088e.setShader(null);
            this.f4086c.invalidate();
        }
    }

    public void c(Canvas canvas) {
        if (n()) {
            int i2 = f4084a;
            if (i2 == 0) {
                c.e eVar = this.f4090g;
                canvas.drawCircle(eVar.f4097a, eVar.f4098b, eVar.f4099c, this.f4088e);
                if (p()) {
                    c.e eVar2 = this.f4090g;
                    canvas.drawCircle(eVar2.f4097a, eVar2.f4098b, eVar2.f4099c, this.f4089f);
                }
            } else if (i2 == 1) {
                int iSave = canvas.save();
                canvas.clipPath(this.f4087d);
                this.f4085b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f4086c.getWidth(), this.f4086c.getHeight(), this.f4089f);
                }
                canvas.restoreToCount(iSave);
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("Unsupported strategy " + i2);
                }
                this.f4085b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f4086c.getWidth(), this.f4086c.getHeight(), this.f4089f);
                }
            }
        } else {
            this.f4085b.a(canvas);
            if (p()) {
                canvas.drawRect(0.0f, 0.0f, this.f4086c.getWidth(), this.f4086c.getHeight(), this.f4089f);
            }
        }
        d(canvas);
    }

    public final void d(Canvas canvas) {
        if (o()) {
            Rect bounds = this.f4091h.getBounds();
            float fWidth = this.f4090g.f4097a - (bounds.width() / 2.0f);
            float fHeight = this.f4090g.f4098b - (bounds.height() / 2.0f);
            canvas.translate(fWidth, fHeight);
            this.f4091h.draw(canvas);
            canvas.translate(-fWidth, -fHeight);
        }
    }

    public Drawable e() {
        return this.f4091h;
    }

    public int f() {
        return this.f4089f.getColor();
    }

    public final float g(c.e eVar) {
        return c.b.a.a.e0.a.b(eVar.f4097a, eVar.f4098b, 0.0f, 0.0f, this.f4086c.getWidth(), this.f4086c.getHeight());
    }

    public c.e h() {
        c.e eVar = this.f4090g;
        if (eVar == null) {
            return null;
        }
        c.e eVar2 = new c.e(eVar);
        if (eVar2.a()) {
            eVar2.f4099c = g(eVar2);
        }
        return eVar2;
    }

    public final void i() {
        if (f4084a == 1) {
            this.f4087d.rewind();
            c.e eVar = this.f4090g;
            if (eVar != null) {
                this.f4087d.addCircle(eVar.f4097a, eVar.f4098b, eVar.f4099c, Path.Direction.CW);
            }
        }
        this.f4086c.invalidate();
    }

    public boolean j() {
        return this.f4085b.d() && !n();
    }

    public void k(Drawable drawable) {
        this.f4091h = drawable;
        this.f4086c.invalidate();
    }

    public void l(int i2) {
        this.f4089f.setColor(i2);
        this.f4086c.invalidate();
    }

    public void m(c.e eVar) {
        if (eVar == null) {
            this.f4090g = null;
        } else {
            c.e eVar2 = this.f4090g;
            if (eVar2 == null) {
                this.f4090g = new c.e(eVar);
            } else {
                eVar2.c(eVar);
            }
            if (c.b.a.a.e0.a.c(eVar.f4099c, g(eVar), 1.0E-4f)) {
                this.f4090g.f4099c = Float.MAX_VALUE;
            }
        }
        i();
    }

    public final boolean n() {
        c.e eVar = this.f4090g;
        boolean z = eVar == null || eVar.a();
        return f4084a == 0 ? !z && this.j : !z;
    }

    public final boolean o() {
        return (this.f4092i || this.f4091h == null || this.f4090g == null) ? false : true;
    }

    public final boolean p() {
        return (this.f4092i || Color.alpha(this.f4089f.getColor()) == 0) ? false : true;
    }
}
