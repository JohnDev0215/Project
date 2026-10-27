package s5;

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
import s5.c;

/* JADX INFO: compiled from: CircularRevealHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f7903a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f7904b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f7905c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Path f7906d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f7907e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f7908f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.e f7909g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f7910h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f7911i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f7912j;

    /* JADX INFO: compiled from: CircularRevealHelper.java */
    public interface a {
        void b(Canvas canvas);

        boolean d();
    }

    static {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 21) {
            f7903a = 2;
        } else if (i8 >= 18) {
            f7903a = 1;
        } else {
            f7903a = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f7904b = aVar;
        View view = (View) aVar;
        this.f7905c = view;
        view.setWillNotDraw(false);
        this.f7906d = new Path();
        this.f7907e = new Paint(7);
        Paint paint = new Paint(1);
        this.f7908f = paint;
        paint.setColor(0);
    }

    public void a() {
        if (f7903a == 0) {
            this.f7911i = true;
            this.f7912j = false;
            this.f7905c.buildDrawingCache();
            Bitmap drawingCache = this.f7905c.getDrawingCache();
            if (drawingCache == null && this.f7905c.getWidth() != 0 && this.f7905c.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.f7905c.getWidth(), this.f7905c.getHeight(), Bitmap.Config.ARGB_8888);
                this.f7905c.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.f7907e;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.f7911i = false;
            this.f7912j = true;
        }
    }

    public void b() {
        if (f7903a == 0) {
            this.f7912j = false;
            this.f7905c.destroyDrawingCache();
            this.f7907e.setShader(null);
            this.f7905c.invalidate();
        }
    }

    public void c(Canvas canvas) {
        if (n()) {
            int i8 = f7903a;
            if (i8 == 0) {
                c.e eVar = this.f7909g;
                canvas.drawCircle(eVar.f7917a, eVar.f7918b, eVar.f7919c, this.f7907e);
                if (p()) {
                    c.e eVar2 = this.f7909g;
                    canvas.drawCircle(eVar2.f7917a, eVar2.f7918b, eVar2.f7919c, this.f7908f);
                }
            } else if (i8 == 1) {
                int iSave = canvas.save();
                canvas.clipPath(this.f7906d);
                this.f7904b.b(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f7905c.getWidth(), this.f7905c.getHeight(), this.f7908f);
                }
                canvas.restoreToCount(iSave);
            } else {
                if (i8 != 2) {
                    throw new IllegalStateException("Unsupported strategy " + i8);
                }
                this.f7904b.b(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f7905c.getWidth(), this.f7905c.getHeight(), this.f7908f);
                }
            }
        } else {
            this.f7904b.b(canvas);
            if (p()) {
                canvas.drawRect(0.0f, 0.0f, this.f7905c.getWidth(), this.f7905c.getHeight(), this.f7908f);
            }
        }
        d(canvas);
    }

    public final void d(Canvas canvas) {
        if (o()) {
            Rect bounds = this.f7910h.getBounds();
            float fWidth = this.f7909g.f7917a - (bounds.width() / 2.0f);
            float fHeight = this.f7909g.f7918b - (bounds.height() / 2.0f);
            canvas.translate(fWidth, fHeight);
            this.f7910h.draw(canvas);
            canvas.translate(-fWidth, -fHeight);
        }
    }

    public Drawable e() {
        return this.f7910h;
    }

    public int f() {
        return this.f7908f.getColor();
    }

    public final float g(c.e eVar) {
        return a6.a.b(eVar.f7917a, eVar.f7918b, 0.0f, 0.0f, this.f7905c.getWidth(), this.f7905c.getHeight());
    }

    public c.e h() {
        c.e eVar = this.f7909g;
        if (eVar == null) {
            return null;
        }
        c.e eVar2 = new c.e(eVar);
        if (eVar2.a()) {
            eVar2.f7919c = g(eVar2);
        }
        return eVar2;
    }

    public final void i() {
        if (f7903a == 1) {
            this.f7906d.rewind();
            c.e eVar = this.f7909g;
            if (eVar != null) {
                this.f7906d.addCircle(eVar.f7917a, eVar.f7918b, eVar.f7919c, Path.Direction.CW);
            }
        }
        this.f7905c.invalidate();
    }

    public boolean j() {
        return this.f7904b.d() && !n();
    }

    public void k(Drawable drawable) {
        this.f7910h = drawable;
        this.f7905c.invalidate();
    }

    public void l(int i8) {
        this.f7908f.setColor(i8);
        this.f7905c.invalidate();
    }

    public void m(c.e eVar) {
        if (eVar == null) {
            this.f7909g = null;
        } else {
            c.e eVar2 = this.f7909g;
            if (eVar2 == null) {
                this.f7909g = new c.e(eVar);
            } else {
                eVar2.c(eVar);
            }
            if (a6.a.c(eVar.f7919c, g(eVar), 1.0E-4f)) {
                this.f7909g.f7919c = Float.MAX_VALUE;
            }
        }
        i();
    }

    public final boolean n() {
        c.e eVar = this.f7909g;
        boolean z7 = eVar == null || eVar.a();
        return f7903a == 0 ? !z7 && this.f7912j : !z7;
    }

    public final boolean o() {
        return (this.f7911i || this.f7910h == null || this.f7909g == null) ? false : true;
    }

    public final boolean p() {
        return (this.f7911i || Color.alpha(this.f7908f.getColor()) == 0) ? false : true;
    }
}
