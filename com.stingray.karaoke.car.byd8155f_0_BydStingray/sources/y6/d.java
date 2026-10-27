package y6;

import A7.m;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import r.C2218F;

/* JADX INFO: loaded from: classes.dex */
public final class d extends C2218F {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public w6.e f24140A;
    public final Rect B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Bitmap f24141C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public Canvas f24142D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public Bitmap f24143E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Canvas f24144F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f24145G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f24146H;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f24147r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f24148s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f24149t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f24150u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Point f24151v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f24152w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f24153x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f24154y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f24155z;

    public d(Context context) {
        super(context, null);
        this.f24151v = new Point();
        this.f24153x = "";
        this.B = new Rect();
    }

    private final float getTextEnd() {
        return getTextStart() + this.f24154y;
    }

    private final float getTextStart() {
        return (getWidth() - this.f24154y) * 0.5f;
    }

    public final Point getFirstLetterPosition() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        Point point = this.f24151v;
        point.x = (int) (this.f24148s ? getTextEnd() : getTextStart());
        point.y = iArr[1];
        return point;
    }

    public final String getLineText() {
        return this.f24153x;
    }

    public final float h(Paint paint) {
        paint.getTextBounds(getLineText(), 0, getLineText().length(), this.B);
        return ((r3.height() / 2.0f) + (getHeight() / 2.0f)) - r3.bottom;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        m.f(canvas, "canvas");
        if (getLineText().length() > 0) {
            if (this.f24155z) {
                this.f24155z = false;
                w6.e eVar = this.f24140A;
                if (eVar == null) {
                    m.j("lyricPaint");
                    throw null;
                }
                this.f24154y = eVar.f23342a.measureText(getLineText());
            }
            canvas.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), this.f24152w);
            float textStart = (this.f24154y * this.f24147r) + getTextStart();
            boolean z6 = this.f24149t;
            Rect rect = this.B;
            if (z6) {
                if (!this.f24145G) {
                    String lineText = getLineText();
                    w6.e eVar2 = this.f24140A;
                    if (eVar2 == null) {
                        m.j("lyricPaint");
                        throw null;
                    }
                    if (this.f24143E == null) {
                        this.f24143E = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                        Bitmap bitmap = this.f24143E;
                        m.c(bitmap);
                        this.f24144F = new Canvas(bitmap);
                    }
                    Canvas canvas2 = this.f24144F;
                    if (canvas2 != null) {
                        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
                        int length = lineText.length();
                        Paint paint = eVar2.f23343b;
                        paint.getTextBounds(lineText, 0, length, rect);
                        canvas2.drawText(lineText, getTextStart(), h(paint), paint);
                    }
                    this.f24145G = true;
                }
                canvas.save();
                if (this.f24148s) {
                    canvas.clipRect(0.0f, 0.0f, getWidth() - textStart, getHeight());
                } else {
                    canvas.clipRect(textStart, 0.0f, getWidth(), getHeight());
                }
                Bitmap bitmap2 = this.f24143E;
                if (bitmap2 != null) {
                    canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
                }
                canvas.restore();
            }
            if (this.f24150u) {
                if (!this.f24146H) {
                    String lineText2 = getLineText();
                    w6.e eVar3 = this.f24140A;
                    if (eVar3 == null) {
                        m.j("lyricPaint");
                        throw null;
                    }
                    if (this.f24141C == null) {
                        this.f24141C = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                        Bitmap bitmap3 = this.f24141C;
                        m.c(bitmap3);
                        this.f24142D = new Canvas(bitmap3);
                    }
                    Canvas canvas3 = this.f24142D;
                    if (canvas3 != null) {
                        canvas3.drawColor(0, PorterDuff.Mode.CLEAR);
                        int length2 = lineText2.length();
                        Paint paint2 = eVar3.f23342a;
                        paint2.getTextBounds(lineText2, 0, length2, rect);
                        canvas3.drawText(lineText2, getTextStart(), h(paint2), paint2);
                    }
                    this.f24146H = true;
                }
                if (this.f24148s) {
                    canvas.clipRect(getWidth() - textStart, 0.0f, getWidth(), getHeight());
                } else {
                    canvas.clipRect(0.0f, 0.0f, textStart, getHeight());
                }
                Bitmap bitmap4 = this.f24141C;
                if (bitmap4 != null) {
                    canvas.drawBitmap(bitmap4, 0.0f, 0.0f, (Paint) null);
                }
            }
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i10, int i11, int i12) {
        super.onSizeChanged(i7, i10, i11, i12);
        Bitmap bitmap = this.f24141C;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.f24141C = null;
        this.f24142D = null;
        this.f24146H = false;
        Bitmap bitmap2 = this.f24143E;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.f24143E = null;
        this.f24144F = null;
        this.f24145G = false;
    }

    public final void setColors(w6.e eVar) {
        m.f(eVar, "paints");
        this.f24155z = true;
        this.f24140A = eVar;
    }

    public final void setLineText(String str) {
        m.f(str, "value");
        this.f24153x = str;
        setText(str);
        this.f24147r = 0.0f;
        this.f24155z = true;
        this.f24145G = false;
        this.f24146H = false;
        D1.b.c().getClass();
        this.f24148s = D1.f.c.o(str.length(), str);
    }
}
