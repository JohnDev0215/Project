package m3;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f18969A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f18970C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f18971D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public StaticLayout f18972E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public StaticLayout f18973F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f18974G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f18975H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f18976I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public Rect f18977J;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f18978a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f18979b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f18980e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextPaint f18981f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f18982g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f18983h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f18984i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Layout.Alignment f18985j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Bitmap f18986k;
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f18987m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f18988n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f18989p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f18990q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f18991r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f18992s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f18993t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f18994u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f18995v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f18996w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f18997x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f18998y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f18999z;

    public G(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{R.attr.lineSpacingExtra, R.attr.lineSpacingMultiplier}, 0, 0);
        this.f18980e = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.d = typedArrayObtainStyledAttributes.getFloat(1, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        float fRound = Math.round((context.getResources().getDisplayMetrics().densityDpi * 2.0f) / 160.0f);
        this.f18978a = fRound;
        this.f18979b = fRound;
        this.c = fRound;
        TextPaint textPaint = new TextPaint();
        this.f18981f = textPaint;
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        Paint paint = new Paint();
        this.f18982g = paint;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.f18983h = paint2;
        paint2.setAntiAlias(true);
        paint2.setFilterBitmap(true);
    }

    public final void a(Canvas canvas, boolean z6) {
        if (!z6) {
            this.f18977J.getClass();
            this.f18986k.getClass();
            canvas.drawBitmap(this.f18986k, (Rect) null, this.f18977J, this.f18983h);
            return;
        }
        StaticLayout staticLayout = this.f18972E;
        StaticLayout staticLayout2 = this.f18973F;
        if (staticLayout == null || staticLayout2 == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(this.f18974G, this.f18975H);
        if (Color.alpha(this.f18994u) > 0) {
            Paint paint = this.f18982g;
            paint.setColor(this.f18994u);
            canvas.drawRect(-this.f18976I, 0.0f, staticLayout.getWidth() + this.f18976I, staticLayout.getHeight(), paint);
        }
        int i7 = this.f18996w;
        TextPaint textPaint = this.f18981f;
        if (i7 == 1) {
            textPaint.setStrokeJoin(Paint.Join.ROUND);
            textPaint.setStrokeWidth(this.f18978a);
            textPaint.setColor(this.f18995v);
            textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            staticLayout2.draw(canvas);
        } else {
            float f10 = this.f18979b;
            if (i7 == 2) {
                float f11 = this.c;
                textPaint.setShadowLayer(f10, f11, f11, this.f18995v);
            } else if (i7 == 3 || i7 == 4) {
                boolean z10 = i7 == 3;
                int i10 = z10 ? -1 : this.f18995v;
                int i11 = z10 ? this.f18995v : -1;
                float f12 = f10 / 2.0f;
                textPaint.setColor(this.f18992s);
                textPaint.setStyle(Paint.Style.FILL);
                float f13 = -f12;
                textPaint.setShadowLayer(f10, f13, f13, i10);
                staticLayout2.draw(canvas);
                textPaint.setShadowLayer(f10, f12, f12, i11);
            }
        }
        textPaint.setColor(this.f18992s);
        textPaint.setStyle(Paint.Style.FILL);
        staticLayout.draw(canvas);
        textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        canvas.restoreToCount(iSave);
    }
}
