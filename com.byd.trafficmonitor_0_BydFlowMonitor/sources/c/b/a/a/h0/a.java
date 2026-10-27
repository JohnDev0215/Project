package c.b.a.a.h0;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;

/* JADX INFO: compiled from: ShadowRenderer.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f3843a = new int[3];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[] f3844b = {0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f3845c = new int[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float[] f3846d = {0.0f, 0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f3847e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f3848f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f3849g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3850h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3851i;
    public int j;
    public final Path k;
    public Paint l;

    public a() {
        this(-16777216);
    }

    public void a(Canvas canvas, Matrix matrix, RectF rectF, int i2, float f2, float f3) {
        boolean z = f3 < 0.0f;
        Path path = this.k;
        if (z) {
            int[] iArr = f3845c;
            iArr[0] = 0;
            iArr[1] = this.j;
            iArr[2] = this.f3851i;
            iArr[3] = this.f3850h;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f2, f3);
            path.close();
            float f4 = -i2;
            rectF.inset(f4, f4);
            int[] iArr2 = f3845c;
            iArr2[0] = 0;
            iArr2[1] = this.f3850h;
            iArr2[2] = this.f3851i;
            iArr2[3] = this.j;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f5 = 1.0f - (i2 / fWidth);
        float[] fArr = f3846d;
        fArr[1] = f5;
        fArr[2] = ((1.0f - f5) / 2.0f) + f5;
        this.f3848f.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, f3845c, fArr, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        if (!z) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, this.l);
        }
        canvas.drawArc(rectF, f2, f3, true, this.f3848f);
        canvas.restore();
    }

    public void b(Canvas canvas, Matrix matrix, RectF rectF, int i2) {
        rectF.bottom += i2;
        rectF.offset(0.0f, -i2);
        int[] iArr = f3843a;
        iArr[0] = this.j;
        iArr[1] = this.f3851i;
        iArr[2] = this.f3850h;
        Paint paint = this.f3849g;
        float f2 = rectF.left;
        paint.setShader(new LinearGradient(f2, rectF.top, f2, rectF.bottom, iArr, f3844b, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rectF, this.f3849g);
        canvas.restore();
    }

    public Paint c() {
        return this.f3847e;
    }

    public void d(int i2) {
        this.f3850h = b.h.f.a.d(i2, 68);
        this.f3851i = b.h.f.a.d(i2, 20);
        this.j = b.h.f.a.d(i2, 0);
        this.f3847e.setColor(this.f3850h);
    }

    public a(int i2) {
        this.k = new Path();
        this.l = new Paint();
        this.f3847e = new Paint();
        d(i2);
        this.l.setColor(0);
        Paint paint = new Paint(4);
        this.f3848f = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f3849g = new Paint(paint);
    }
}
