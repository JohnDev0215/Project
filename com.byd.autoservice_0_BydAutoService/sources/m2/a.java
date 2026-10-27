package m2;

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
    public static final int[] f3836a = new int[3];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[] f3837b = {0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f3838c = new int[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float[] f3839d = {0.0f, 0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f3840e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f3841f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f3842g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3843h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3844i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3845j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Path f3846k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Paint f3847l;

    public a() {
        this(-16777216);
    }

    public void a(Canvas canvas, Matrix matrix, RectF rectF, int i3, float f3, float f4) {
        boolean z3 = f4 < 0.0f;
        Path path = this.f3846k;
        if (z3) {
            int[] iArr = f3838c;
            iArr[0] = 0;
            iArr[1] = this.f3845j;
            iArr[2] = this.f3844i;
            iArr[3] = this.f3843h;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f3, f4);
            path.close();
            float f5 = -i3;
            rectF.inset(f5, f5);
            int[] iArr2 = f3838c;
            iArr2[0] = 0;
            iArr2[1] = this.f3843h;
            iArr2[2] = this.f3844i;
            iArr2[3] = this.f3845j;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f6 = 1.0f - (i3 / fWidth);
        float[] fArr = f3839d;
        fArr[1] = f6;
        fArr[2] = ((1.0f - f6) / 2.0f) + f6;
        this.f3841f.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, f3838c, fArr, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        if (!z3) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, this.f3847l);
        }
        canvas.drawArc(rectF, f3, f4, true, this.f3841f);
        canvas.restore();
    }

    public void b(Canvas canvas, Matrix matrix, RectF rectF, int i3) {
        rectF.bottom += i3;
        rectF.offset(0.0f, -i3);
        int[] iArr = f3836a;
        iArr[0] = this.f3845j;
        iArr[1] = this.f3844i;
        iArr[2] = this.f3843h;
        Paint paint = this.f3842g;
        float f3 = rectF.left;
        paint.setShader(new LinearGradient(f3, rectF.top, f3, rectF.bottom, iArr, f3837b, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rectF, this.f3842g);
        canvas.restore();
    }

    public Paint c() {
        return this.f3840e;
    }

    public void d(int i3) {
        this.f3843h = d0.a.d(i3, 68);
        this.f3844i = d0.a.d(i3, 20);
        this.f3845j = d0.a.d(i3, 0);
        this.f3840e.setColor(this.f3843h);
    }

    public a(int i3) {
        this.f3846k = new Path();
        this.f3847l = new Paint();
        this.f3840e = new Paint();
        d(i3);
        this.f3847l.setColor(0);
        Paint paint = new Paint(4);
        this.f3841f = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f3842g = new Paint(paint);
    }
}
