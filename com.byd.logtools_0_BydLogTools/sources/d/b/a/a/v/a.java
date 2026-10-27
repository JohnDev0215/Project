package d.b.a.a.v;

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
    private static final int[] f4818a = new int[3];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final float[] f4819b = {0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f4820c = new int[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final float[] f4821d = {0.0f, 0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Paint f4822e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Paint f4823f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Paint f4824g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4825h;
    private int i;
    private int j;
    private final Path k;
    private Paint l;

    public a() {
        this(-16777216);
    }

    public void a(Canvas canvas, Matrix matrix, RectF rectF, int i, float f2, float f3) {
        boolean z = f3 < 0.0f;
        Path path = this.k;
        if (z) {
            int[] iArr = f4820c;
            iArr[0] = 0;
            iArr[1] = this.j;
            iArr[2] = this.i;
            iArr[3] = this.f4825h;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f2, f3);
            path.close();
            float f4 = -i;
            rectF.inset(f4, f4);
            int[] iArr2 = f4820c;
            iArr2[0] = 0;
            iArr2[1] = this.f4825h;
            iArr2[2] = this.i;
            iArr2[3] = this.j;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f5 = 1.0f - (i / fWidth);
        float[] fArr = f4821d;
        fArr[1] = f5;
        fArr[2] = ((1.0f - f5) / 2.0f) + f5;
        this.f4823f.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, f4820c, fArr, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        if (!z) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, this.l);
        }
        canvas.drawArc(rectF, f2, f3, true, this.f4823f);
        canvas.restore();
    }

    public void b(Canvas canvas, Matrix matrix, RectF rectF, int i) {
        rectF.bottom += i;
        rectF.offset(0.0f, -i);
        int[] iArr = f4818a;
        iArr[0] = this.j;
        iArr[1] = this.i;
        iArr[2] = this.f4825h;
        Paint paint = this.f4824g;
        float f2 = rectF.left;
        paint.setShader(new LinearGradient(f2, rectF.top, f2, rectF.bottom, iArr, f4819b, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rectF, this.f4824g);
        canvas.restore();
    }

    public Paint c() {
        return this.f4822e;
    }

    public void d(int i) {
        this.f4825h = b.g.d.a.d(i, 68);
        this.i = b.g.d.a.d(i, 20);
        this.j = b.g.d.a.d(i, 0);
        this.f4822e.setColor(this.f4825h);
    }

    public a(int i) {
        this.k = new Path();
        this.l = new Paint();
        this.f4822e = new Paint();
        d(i);
        this.l.setColor(0);
        Paint paint = new Paint(4);
        this.f4823f = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f4824g = new Paint(paint);
    }
}
