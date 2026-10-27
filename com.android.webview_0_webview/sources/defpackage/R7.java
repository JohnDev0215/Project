package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Shader;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class R7 {
    public static final Matrix q = new Matrix();
    public final Path a;
    public final Path b;
    public final Matrix c;
    public Paint d;
    public Paint e;
    public PathMeasure f;
    public int g;
    public final O7 h;
    public float i;
    public float j;
    public float k;
    public float l;
    public int m;
    public String n;
    public Boolean o;
    public final C0971j1 p;

    public R7() {
        this.c = new Matrix();
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.m = 255;
        this.n = null;
        this.o = null;
        this.p = new C0971j1();
        this.h = new O7();
        this.a = new Path();
        this.b = new Path();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v20 */
    public final void a(O7 o7, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
        R7 r7;
        R7 r72 = this;
        o7.a.set(matrix);
        o7.a.preConcat(o7.j);
        canvas.save();
        ?? r11 = 0;
        int i3 = 0;
        while (i3 < o7.b.size()) {
            P7 p7 = (P7) o7.b.get(i3);
            if (p7 instanceof O7) {
                a((O7) p7, o7.a, canvas, i, i2, colorFilter);
            } else {
                if (p7 instanceof Q7) {
                    Q7 q7 = (Q7) p7;
                    float f = i / r72.k;
                    float f2 = i2 / r72.l;
                    float fMin = Math.min(f, f2);
                    Matrix matrix2 = o7.a;
                    r72.c.set(matrix2);
                    r72.c.postScale(f, f2);
                    float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
                    matrix2.mapVectors(fArr);
                    float fHypot = (float) Math.hypot(fArr[r11], fArr[1]);
                    float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
                    float f3 = (fArr[r11] * fArr[3]) - (fArr[1] * fArr[2]);
                    float fMax = Math.max(fHypot, fHypot2);
                    float fAbs = fMax > 0.0f ? Math.abs(f3) / fMax : 0.0f;
                    if (fAbs == 0.0f) {
                        r7 = this;
                    } else {
                        r7 = this;
                        Path path = r7.a;
                        Objects.requireNonNull(q7);
                        path.reset();
                        C1305p2[] c1305p2Arr = q7.a;
                        if (c1305p2Arr != null) {
                            C1305p2.b(c1305p2Arr, path);
                        }
                        Path path2 = r7.a;
                        r7.b.reset();
                        if (q7.c()) {
                            r7.b.addPath(path2, r7.c);
                            canvas.clipPath(r7.b);
                        } else {
                            N7 n7 = (N7) q7;
                            if (n7.k != 0.0f || n7.l != 1.0f) {
                                float f4 = n7.k;
                                float f5 = n7.m;
                                float f6 = (f4 + f5) % 1.0f;
                                float f7 = (n7.l + f5) % 1.0f;
                                if (r7.f == null) {
                                    r7.f = new PathMeasure();
                                }
                                r7.f.setPath(r7.a, r11);
                                float length = r7.f.getLength();
                                float f8 = f6 * length;
                                float f9 = f7 * length;
                                path2.reset();
                                if (f8 > f9) {
                                    r7.f.getSegment(f8, length, path2, true);
                                    r7.f.getSegment(0.0f, f9, path2, true);
                                } else {
                                    r7.f.getSegment(f8, f9, path2, true);
                                }
                                path2.rLineTo(0.0f, 0.0f);
                            }
                            r7.b.addPath(path2, r7.c);
                            C0638d2 c0638d2 = n7.g;
                            if (c0638d2.b() || c0638d2.c != 0) {
                                C0638d2 c0638d22 = n7.g;
                                if (r7.e == null) {
                                    Paint paint = new Paint(1);
                                    r7.e = paint;
                                    paint.setStyle(Paint.Style.FILL);
                                }
                                Paint paint2 = r7.e;
                                if (c0638d22.b()) {
                                    Shader shader = c0638d22.a;
                                    shader.setLocalMatrix(r7.c);
                                    paint2.setShader(shader);
                                    paint2.setAlpha(Math.round(n7.j * 255.0f));
                                } else {
                                    paint2.setColor(U7.a(c0638d22.c, n7.j));
                                }
                                paint2.setColorFilter(colorFilter);
                                r7.b.setFillType(n7.i == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                                canvas.drawPath(r7.b, paint2);
                            }
                            C0638d2 c0638d23 = n7.e;
                            if (c0638d23.b() || c0638d23.c != 0) {
                                C0638d2 c0638d24 = n7.e;
                                if (r7.d == null) {
                                    Paint paint3 = new Paint(1);
                                    r7.d = paint3;
                                    paint3.setStyle(Paint.Style.STROKE);
                                }
                                Paint paint4 = r7.d;
                                Paint.Join join = n7.o;
                                if (join != null) {
                                    paint4.setStrokeJoin(join);
                                }
                                Paint.Cap cap = n7.n;
                                if (cap != null) {
                                    paint4.setStrokeCap(cap);
                                }
                                paint4.setStrokeMiter(n7.p);
                                if (c0638d24.b()) {
                                    Shader shader2 = c0638d24.a;
                                    shader2.setLocalMatrix(r7.c);
                                    paint4.setShader(shader2);
                                    paint4.setAlpha(Math.round(n7.h * 255.0f));
                                } else {
                                    paint4.setColor(U7.a(c0638d24.c, n7.h));
                                }
                                paint4.setColorFilter(colorFilter);
                                paint4.setStrokeWidth(n7.f * fAbs * fMin);
                                canvas.drawPath(r7.b, paint4);
                            }
                        }
                    }
                }
                i3++;
                r72 = r7;
                r11 = 0;
            }
            r7 = r72;
            i3++;
            r72 = r7;
            r11 = 0;
        }
        canvas.restore();
    }

    public float getAlpha() {
        return getRootAlpha() / 255.0f;
    }

    public int getRootAlpha() {
        return this.m;
    }

    public void setAlpha(float f) {
        setRootAlpha((int) (f * 255.0f));
    }

    public void setRootAlpha(int i) {
        this.m = i;
    }

    public R7(R7 r7) {
        this.c = new Matrix();
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.m = 255;
        this.n = null;
        this.o = null;
        C0971j1 c0971j1 = new C0971j1();
        this.p = c0971j1;
        this.h = new O7(r7.h, c0971j1);
        this.a = new Path(r7.a);
        this.b = new Path(r7.b);
        this.i = r7.i;
        this.j = r7.j;
        this.k = r7.k;
        this.l = r7.l;
        this.g = r7.g;
        this.m = r7.m;
        this.n = r7.n;
        String str = r7.n;
        if (str != null) {
            this.p.put(str, this);
        }
        this.o = r7.o;
    }
}
