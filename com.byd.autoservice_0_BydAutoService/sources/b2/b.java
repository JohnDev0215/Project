package b2;

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
import b2.c;

/* JADX INFO: compiled from: CircularRevealHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f1761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1762b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f1763c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Path f1764d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f1765e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f1766f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.e f1767g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f1768h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1769i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1770j;

    /* JADX INFO: compiled from: CircularRevealHelper.java */
    public interface a {
        void a(Canvas canvas);

        boolean d();
    }

    static {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21) {
            f1761a = 2;
        } else if (i3 >= 18) {
            f1761a = 1;
        } else {
            f1761a = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f1762b = aVar;
        View view = (View) aVar;
        this.f1763c = view;
        view.setWillNotDraw(false);
        this.f1764d = new Path();
        this.f1765e = new Paint(7);
        Paint paint = new Paint(1);
        this.f1766f = paint;
        paint.setColor(0);
    }

    public void a() {
        if (f1761a == 0) {
            this.f1769i = true;
            this.f1770j = false;
            this.f1763c.buildDrawingCache();
            Bitmap drawingCache = this.f1763c.getDrawingCache();
            if (drawingCache == null && this.f1763c.getWidth() != 0 && this.f1763c.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.f1763c.getWidth(), this.f1763c.getHeight(), Bitmap.Config.ARGB_8888);
                this.f1763c.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.f1765e;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.f1769i = false;
            this.f1770j = true;
        }
    }

    public void b() {
        if (f1761a == 0) {
            this.f1770j = false;
            this.f1763c.destroyDrawingCache();
            this.f1765e.setShader(null);
            this.f1763c.invalidate();
        }
    }

    public void c(Canvas canvas) {
        if (n()) {
            int i3 = f1761a;
            if (i3 == 0) {
                c.e eVar = this.f1767g;
                canvas.drawCircle(eVar.f1775a, eVar.f1776b, eVar.f1777c, this.f1765e);
                if (p()) {
                    c.e eVar2 = this.f1767g;
                    canvas.drawCircle(eVar2.f1775a, eVar2.f1776b, eVar2.f1777c, this.f1766f);
                }
            } else if (i3 == 1) {
                int iSave = canvas.save();
                canvas.clipPath(this.f1764d);
                this.f1762b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f1763c.getWidth(), this.f1763c.getHeight(), this.f1766f);
                }
                canvas.restoreToCount(iSave);
            } else {
                if (i3 != 2) {
                    throw new IllegalStateException("Unsupported strategy " + i3);
                }
                this.f1762b.a(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f1763c.getWidth(), this.f1763c.getHeight(), this.f1766f);
                }
            }
        } else {
            this.f1762b.a(canvas);
            if (p()) {
                canvas.drawRect(0.0f, 0.0f, this.f1763c.getWidth(), this.f1763c.getHeight(), this.f1766f);
            }
        }
        d(canvas);
    }

    public final void d(Canvas canvas) {
        if (o()) {
            Rect bounds = this.f1768h.getBounds();
            float fWidth = this.f1767g.f1775a - (bounds.width() / 2.0f);
            float fHeight = this.f1767g.f1776b - (bounds.height() / 2.0f);
            canvas.translate(fWidth, fHeight);
            this.f1768h.draw(canvas);
            canvas.translate(-fWidth, -fHeight);
        }
    }

    public Drawable e() {
        return this.f1768h;
    }

    public int f() {
        return this.f1766f.getColor();
    }

    public final float g(c.e eVar) {
        return j2.a.b(eVar.f1775a, eVar.f1776b, 0.0f, 0.0f, this.f1763c.getWidth(), this.f1763c.getHeight());
    }

    public c.e h() {
        c.e eVar = this.f1767g;
        if (eVar == null) {
            return null;
        }
        c.e eVar2 = new c.e(eVar);
        if (eVar2.a()) {
            eVar2.f1777c = g(eVar2);
        }
        return eVar2;
    }

    public final void i() {
        if (f1761a == 1) {
            this.f1764d.rewind();
            c.e eVar = this.f1767g;
            if (eVar != null) {
                this.f1764d.addCircle(eVar.f1775a, eVar.f1776b, eVar.f1777c, Path.Direction.CW);
            }
        }
        this.f1763c.invalidate();
    }

    public boolean j() {
        return this.f1762b.d() && !n();
    }

    public void k(Drawable drawable) {
        this.f1768h = drawable;
        this.f1763c.invalidate();
    }

    public void l(int i3) {
        this.f1766f.setColor(i3);
        this.f1763c.invalidate();
    }

    public void m(c.e eVar) {
        if (eVar == null) {
            this.f1767g = null;
        } else {
            c.e eVar2 = this.f1767g;
            if (eVar2 == null) {
                this.f1767g = new c.e(eVar);
            } else {
                eVar2.c(eVar);
            }
            if (j2.a.c(eVar.f1777c, g(eVar), 1.0E-4f)) {
                this.f1767g.f1777c = Float.MAX_VALUE;
            }
        }
        i();
    }

    public final boolean n() {
        c.e eVar = this.f1767g;
        boolean z3 = eVar == null || eVar.a();
        return f1761a == 0 ? !z3 && this.f1770j : !z3;
    }

    public final boolean o() {
        return (this.f1769i || this.f1768h == null || this.f1767g == null) ? false : true;
    }

    public final boolean p() {
        return (this.f1769i || Color.alpha(this.f1766f.getColor()) == 0) ? false : true;
    }
}
