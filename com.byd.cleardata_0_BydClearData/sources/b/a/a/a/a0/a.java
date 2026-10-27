package b.a.a.a.a0;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1638a = new int[3];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final float[] f1639b = {0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f1640c = new int[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final float[] f1641d = {0.0f, 0.0f, 0.5f, 1.0f};
    private final Paint e;
    private final Paint f;
    private final Paint g;
    private int h;
    private int i;
    private int j;
    private final Path k;
    private Paint l;

    public a() {
        this(-16777216);
    }

    public a(int i) {
        this.k = new Path();
        this.l = new Paint();
        this.e = new Paint();
        d(i);
        this.l.setColor(0);
        Paint paint = new Paint(4);
        this.f = paint;
        paint.setStyle(Paint.Style.FILL);
        this.g = new Paint(paint);
    }

    public void a(Canvas canvas, Matrix matrix, RectF rectF, int i, float f, float f2) {
        boolean z = f2 < 0.0f;
        Path path = this.k;
        if (z) {
            int[] iArr = f1640c;
            iArr[0] = 0;
            iArr[1] = this.j;
            iArr[2] = this.i;
            iArr[3] = this.h;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f, f2);
            path.close();
            float f3 = -i;
            rectF.inset(f3, f3);
            int[] iArr2 = f1640c;
            iArr2[0] = 0;
            iArr2[1] = this.h;
            iArr2[2] = this.i;
            iArr2[3] = this.j;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f4 = 1.0f - (i / fWidth);
        float[] fArr = f1641d;
        fArr[1] = f4;
        fArr[2] = ((1.0f - f4) / 2.0f) + f4;
        this.f.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, f1640c, fArr, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        if (!z) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, this.l);
        }
        canvas.drawArc(rectF, f, f2, true, this.f);
        canvas.restore();
    }

    public void b(Canvas canvas, Matrix matrix, RectF rectF, int i) {
        rectF.bottom += i;
        rectF.offset(0.0f, -i);
        int[] iArr = f1638a;
        iArr[0] = this.j;
        iArr[1] = this.i;
        iArr[2] = this.h;
        Paint paint = this.g;
        float f = rectF.left;
        paint.setShader(new LinearGradient(f, rectF.top, f, rectF.bottom, iArr, f1639b, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rectF, this.g);
        canvas.restore();
    }

    public Paint c() {
        return this.e;
    }

    public void d(int i) {
        this.h = a.g.d.a.d(i, 68);
        this.i = a.g.d.a.d(i, 20);
        this.j = a.g.d.a.d(i, 0);
        this.e.setColor(this.h);
    }
}
