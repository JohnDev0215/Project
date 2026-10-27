package d6;

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
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f4461a = new int[3];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[] f4462b = {0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f4463c = new int[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float[] f4464d = {0.0f, 0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f4465e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f4466f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f4467g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4468h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4469i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4470j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Path f4471k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Paint f4472l;

    public a() {
        this(-16777216);
    }

    public void a(Canvas canvas, Matrix matrix, RectF rectF, int i8, float f8, float f9) {
        boolean z7 = f9 < 0.0f;
        Path path = this.f4471k;
        if (z7) {
            int[] iArr = f4463c;
            iArr[0] = 0;
            iArr[1] = this.f4470j;
            iArr[2] = this.f4469i;
            iArr[3] = this.f4468h;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f8, f9);
            path.close();
            float f10 = -i8;
            rectF.inset(f10, f10);
            int[] iArr2 = f4463c;
            iArr2[0] = 0;
            iArr2[1] = this.f4468h;
            iArr2[2] = this.f4469i;
            iArr2[3] = this.f4470j;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f11 = 1.0f - (i8 / fWidth);
        float[] fArr = f4464d;
        fArr[1] = f11;
        fArr[2] = ((1.0f - f11) / 2.0f) + f11;
        this.f4466f.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, f4463c, fArr, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        if (!z7) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, this.f4472l);
        }
        canvas.drawArc(rectF, f8, f9, true, this.f4466f);
        canvas.restore();
    }

    public void b(Canvas canvas, Matrix matrix, RectF rectF, int i8) {
        rectF.bottom += i8;
        rectF.offset(0.0f, -i8);
        int[] iArr = f4461a;
        iArr[0] = this.f4470j;
        iArr[1] = this.f4469i;
        iArr[2] = this.f4468h;
        Paint paint = this.f4467g;
        float f8 = rectF.left;
        paint.setShader(new LinearGradient(f8, rectF.top, f8, rectF.bottom, iArr, f4462b, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rectF, this.f4467g);
        canvas.restore();
    }

    public Paint c() {
        return this.f4465e;
    }

    public void d(int i8) {
        this.f4468h = g0.a.d(i8, 68);
        this.f4469i = g0.a.d(i8, 20);
        this.f4470j = g0.a.d(i8, 0);
        this.f4465e.setColor(this.f4468h);
    }

    public a(int i8) {
        this.f4471k = new Path();
        this.f4472l = new Paint();
        this.f4465e = new Paint();
        d(i8);
        this.f4472l.setColor(0);
        Paint paint = new Paint(4);
        this.f4466f = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f4467g = new Paint(paint);
    }
}
