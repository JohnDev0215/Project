package d3;

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

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int[] f5653i = new int[3];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final float[] f5654j = {0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final int[] f5655k = new int[4];

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final float[] f5656l = {0.0f, 0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Paint f5657a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f5658b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Paint f5659c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f5660d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f5661e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f5662f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Path f5663g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Paint f5664h;

    public a() {
        this(-16777216);
    }

    public void a(Canvas canvas, Matrix matrix, RectF rectF, int i7, float f7, float f8) {
        boolean z6 = f8 < 0.0f;
        Path path = this.f5663g;
        if (z6) {
            int[] iArr = f5655k;
            iArr[0] = 0;
            iArr[1] = this.f5662f;
            iArr[2] = this.f5661e;
            iArr[3] = this.f5660d;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f7, f8);
            path.close();
            float f9 = -i7;
            rectF.inset(f9, f9);
            int[] iArr2 = f5655k;
            iArr2[0] = 0;
            iArr2[1] = this.f5660d;
            iArr2[2] = this.f5661e;
            iArr2[3] = this.f5662f;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f10 = 1.0f - (i7 / fWidth);
        float[] fArr = f5656l;
        fArr[1] = f10;
        fArr[2] = ((1.0f - f10) / 2.0f) + f10;
        this.f5658b.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, f5655k, fArr, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.scale(1.0f, rectF.height() / rectF.width());
        if (!z6) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, this.f5664h);
        }
        canvas.drawArc(rectF, f7, f8, true, this.f5658b);
        canvas.restore();
    }

    public void b(Canvas canvas, Matrix matrix, RectF rectF, int i7) {
        rectF.bottom += i7;
        rectF.offset(0.0f, -i7);
        int[] iArr = f5653i;
        iArr[0] = this.f5662f;
        iArr[1] = this.f5661e;
        iArr[2] = this.f5660d;
        Paint paint = this.f5659c;
        float f7 = rectF.left;
        paint.setShader(new LinearGradient(f7, rectF.top, f7, rectF.bottom, iArr, f5654j, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rectF, this.f5659c);
        canvas.restore();
    }

    public Paint c() {
        return this.f5657a;
    }

    public void d(int i7) {
        this.f5660d = z.a.d(i7, 68);
        this.f5661e = z.a.d(i7, 20);
        this.f5662f = z.a.d(i7, 0);
        this.f5657a.setColor(this.f5660d);
    }

    public a(int i7) {
        this.f5663g = new Path();
        this.f5664h = new Paint();
        this.f5657a = new Paint();
        d(i7);
        this.f5664h.setColor(0);
        Paint paint = new Paint(4);
        this.f5658b = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f5659c = new Paint(paint);
    }
}
