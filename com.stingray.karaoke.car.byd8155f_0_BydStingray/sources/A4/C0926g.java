package a4;

import C1.j;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.os.Build;
import x.AbstractC2641j;

/* JADX INFO: renamed from: a4.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0926g {
    public static final Matrix B = new Matrix();

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public C0920a f11109A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Canvas f11110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j f11111b;
    public int c;
    public RectF d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RectF f11112e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Rect f11113f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public RectF f11114g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public RectF f11115h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Rect f11116i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public RectF f11117j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public P3.a f11118k;
    public Bitmap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Canvas f11119m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Rect f11120n;
    public P3.a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Matrix f11121p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float[] f11122q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Bitmap f11123r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Bitmap f11124s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Canvas f11125t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Canvas f11126u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public P3.a f11127v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public BlurMaskFilter f11128w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f11129x = 0.0f;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public RenderNode f11130y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public RenderNode f11131z;

    public static Bitmap a(RectF rectF, Bitmap.Config config) {
        return Bitmap.createBitmap((int) Math.ceil(((double) rectF.width()) * 1.05d), (int) Math.ceil(((double) rectF.height()) * 1.05d), config);
    }

    public static boolean d(Bitmap bitmap, RectF rectF) {
        return bitmap == null || rectF.width() >= ((float) bitmap.getWidth()) || rectF.height() >= ((float) bitmap.getHeight()) || rectF.width() < ((float) bitmap.getWidth()) * 0.75f || rectF.height() < ((float) bitmap.getHeight()) * 0.75f;
    }

    public final RectF b(RectF rectF, C0920a c0920a) {
        if (this.f11112e == null) {
            this.f11112e = new RectF();
        }
        if (this.f11114g == null) {
            this.f11114g = new RectF();
        }
        this.f11112e.set(rectF);
        this.f11112e.offsetTo(rectF.left + c0920a.f11091b, rectF.top + c0920a.c);
        RectF rectF2 = this.f11112e;
        float f10 = c0920a.f11090a;
        rectF2.inset(-f10, -f10);
        this.f11114g.set(rectF);
        this.f11112e.union(this.f11114g);
        return this.f11112e;
    }

    public final void c() {
        float f10;
        P3.a aVar;
        if (this.f11110a == null || this.f11111b == null || this.f11122q == null || this.d == null) {
            throw new IllegalStateException("OffscreenBitmap: finish() call without matching start()");
        }
        int iC = AbstractC2641j.c(this.c);
        if (iC == 0 || iC == 1) {
            this.f11110a.restore();
        } else {
            if (iC != 2) {
                if (iC == 3) {
                    if (this.f11130y == null) {
                        throw new IllegalStateException("RenderNode is not ready; should've been initialized at start() time");
                    }
                    int i7 = Build.VERSION.SDK_INT;
                    this.f11110a.save();
                    Canvas canvas = this.f11110a;
                    float[] fArr = this.f11122q;
                    canvas.scale(1.0f / fArr[0], 1.0f / fArr[4]);
                    this.f11130y.endRecording();
                    if (this.f11111b.m()) {
                        Canvas canvas2 = this.f11110a;
                        C0920a c0920a = (C0920a) this.f11111b.f944m;
                        if (this.f11130y == null || this.f11131z == null) {
                            throw new IllegalStateException("Cannot render to render node outside a start()/finish() block");
                        }
                        if (i7 < 31) {
                            throw new RuntimeException("RenderEffect is not supported on API level <31");
                        }
                        float[] fArr2 = this.f11122q;
                        float f11 = fArr2 != null ? fArr2[0] : 1.0f;
                        f10 = fArr2 != null ? fArr2[4] : 1.0f;
                        C0920a c0920a2 = this.f11109A;
                        if (c0920a2 == null || c0920a.f11090a != c0920a2.f11090a || c0920a.f11091b != c0920a2.f11091b || c0920a.c != c0920a2.c || c0920a.d != c0920a2.d) {
                            RenderEffect renderEffectCreateColorFilterEffect = RenderEffect.createColorFilterEffect(new PorterDuffColorFilter(c0920a.d, PorterDuff.Mode.SRC_IN));
                            float f12 = c0920a.f11090a;
                            if (f12 > 0.0f) {
                                float f13 = ((f11 + f10) * f12) / 2.0f;
                                renderEffectCreateColorFilterEffect = RenderEffect.createBlurEffect(f13, f13, renderEffectCreateColorFilterEffect, Shader.TileMode.CLAMP);
                            }
                            this.f11131z.setRenderEffect(renderEffectCreateColorFilterEffect);
                            this.f11109A = c0920a;
                        }
                        RectF rectFB = b(this.d, c0920a);
                        RectF rectF = new RectF(rectFB.left * f11, rectFB.top * f10, rectFB.right * f11, rectFB.bottom * f10);
                        this.f11131z.setPosition(0, 0, (int) rectF.width(), (int) rectF.height());
                        RecordingCanvas recordingCanvasBeginRecording = this.f11131z.beginRecording((int) rectF.width(), (int) rectF.height());
                        recordingCanvasBeginRecording.translate((c0920a.f11091b * f11) + (-rectF.left), (c0920a.c * f10) + (-rectF.top));
                        recordingCanvasBeginRecording.drawRenderNode(this.f11130y);
                        this.f11131z.endRecording();
                        canvas2.save();
                        canvas2.translate(rectF.left, rectF.top);
                        canvas2.drawRenderNode(this.f11131z);
                        canvas2.restore();
                    }
                    this.f11110a.drawRenderNode(this.f11130y);
                    this.f11110a.restore();
                }
            } else {
                if (this.l == null) {
                    throw new IllegalStateException("Bitmap is not ready; should've been initialized at start() time");
                }
                if (this.f11111b.m()) {
                    Canvas canvas3 = this.f11110a;
                    C0920a c0920a3 = (C0920a) this.f11111b.f944m;
                    RectF rectF2 = this.d;
                    if (rectF2 == null || this.l == null) {
                        throw new IllegalStateException("Cannot render to bitmap outside a start()/finish() block");
                    }
                    RectF rectFB2 = b(rectF2, c0920a3);
                    if (this.f11113f == null) {
                        this.f11113f = new Rect();
                    }
                    this.f11113f.set((int) Math.floor(rectFB2.left), (int) Math.floor(rectFB2.top), (int) Math.ceil(rectFB2.right), (int) Math.ceil(rectFB2.bottom));
                    float[] fArr3 = this.f11122q;
                    float f14 = fArr3 != null ? fArr3[0] : 1.0f;
                    f10 = fArr3 != null ? fArr3[4] : 1.0f;
                    if (this.f11115h == null) {
                        this.f11115h = new RectF();
                    }
                    this.f11115h.set(rectFB2.left * f14, rectFB2.top * f10, rectFB2.right * f14, rectFB2.bottom * f10);
                    if (this.f11116i == null) {
                        this.f11116i = new Rect();
                    }
                    this.f11116i.set(0, 0, Math.round(this.f11115h.width()), Math.round(this.f11115h.height()));
                    if (d(this.f11123r, this.f11115h)) {
                        Bitmap bitmap = this.f11123r;
                        if (bitmap != null) {
                            bitmap.recycle();
                        }
                        Bitmap bitmap2 = this.f11124s;
                        if (bitmap2 != null) {
                            bitmap2.recycle();
                        }
                        this.f11123r = a(this.f11115h, Bitmap.Config.ARGB_8888);
                        this.f11124s = a(this.f11115h, Bitmap.Config.ALPHA_8);
                        this.f11125t = new Canvas(this.f11123r);
                        this.f11126u = new Canvas(this.f11124s);
                    } else {
                        Canvas canvas4 = this.f11125t;
                        if (canvas4 == null || this.f11126u == null || (aVar = this.o) == null) {
                            throw new IllegalStateException("If needNewBitmap() returns true, we should have a canvas and bitmap ready");
                        }
                        canvas4.drawRect(this.f11116i, aVar);
                        this.f11126u.drawRect(this.f11116i, this.o);
                    }
                    if (this.f11124s == null) {
                        throw new IllegalStateException("Expected to have allocated a shadow mask bitmap");
                    }
                    if (this.f11127v == null) {
                        this.f11127v = new P3.a(1, 0);
                    }
                    RectF rectF3 = this.d;
                    this.f11126u.drawBitmap(this.l, Math.round((rectF3.left - rectFB2.left) * f14), Math.round((rectF3.top - rectFB2.top) * f10), (Paint) null);
                    if (this.f11128w == null || this.f11129x != c0920a3.f11090a) {
                        float f15 = ((f14 + f10) * c0920a3.f11090a) / 2.0f;
                        if (f15 > 0.0f) {
                            this.f11128w = new BlurMaskFilter(f15, BlurMaskFilter.Blur.NORMAL);
                        } else {
                            this.f11128w = null;
                        }
                        this.f11129x = c0920a3.f11090a;
                    }
                    this.f11127v.setColor(c0920a3.d);
                    if (c0920a3.f11090a > 0.0f) {
                        this.f11127v.setMaskFilter(this.f11128w);
                    } else {
                        this.f11127v.setMaskFilter(null);
                    }
                    this.f11127v.setFilterBitmap(true);
                    this.f11125t.drawBitmap(this.f11124s, Math.round(c0920a3.f11091b * f14), Math.round(c0920a3.c * f10), this.f11127v);
                    canvas3.drawBitmap(this.f11123r, this.f11116i, this.f11113f, this.f11118k);
                }
                if (this.f11120n == null) {
                    this.f11120n = new Rect();
                }
                this.f11120n.set(0, 0, (int) (this.d.width() * this.f11122q[0]), (int) (this.d.height() * this.f11122q[4]));
                this.f11110a.drawBitmap(this.l, this.f11120n, this.d, this.f11118k);
            }
        }
        this.f11110a = null;
    }

    public final Canvas e(Canvas canvas, RectF rectF, j jVar) {
        if (this.f11110a != null) {
            throw new IllegalStateException("Cannot nest start() calls on a single OffscreenBitmap - call finish() first");
        }
        if (this.f11122q == null) {
            this.f11122q = new float[9];
        }
        if (this.f11121p == null) {
            this.f11121p = new Matrix();
        }
        canvas.getMatrix(this.f11121p);
        this.f11121p.getValues(this.f11122q);
        float[] fArr = this.f11122q;
        float f10 = fArr[0];
        int i7 = 4;
        float f11 = fArr[4];
        if (this.f11117j == null) {
            this.f11117j = new RectF();
        }
        this.f11117j.set(rectF.left * f10, rectF.top * f11, rectF.right * f10, rectF.bottom * f11);
        this.f11110a = canvas;
        this.f11111b = jVar;
        if (jVar.l >= 255 && !jVar.m()) {
            i7 = 1;
        } else if (jVar.m()) {
            int i10 = Build.VERSION.SDK_INT;
            if (!canvas.isHardwareAccelerated() || i10 <= 31) {
                i7 = 3;
            }
        } else {
            i7 = 2;
        }
        this.c = i7;
        if (this.d == null) {
            this.d = new RectF();
        }
        this.d.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        if (this.f11118k == null) {
            this.f11118k = new P3.a();
        }
        this.f11118k.reset();
        int iC = AbstractC2641j.c(this.c);
        if (iC == 0) {
            canvas.save();
            return canvas;
        }
        if (iC == 1) {
            this.f11118k.setAlpha(jVar.l);
            this.f11118k.setColorFilter(null);
            P3.a aVar = this.f11118k;
            Matrix matrix = AbstractC0927h.f11132a;
            canvas.saveLayer(rectF, aVar);
            return canvas;
        }
        Matrix matrix2 = B;
        if (iC != 2) {
            if (iC != 3) {
                throw new RuntimeException("Invalid render strategy for OffscreenLayer");
            }
            if (this.f11130y == null) {
                this.f11130y = new RenderNode("OffscreenLayer.main");
            }
            if (jVar.m() && this.f11131z == null) {
                this.f11131z = new RenderNode("OffscreenLayer.shadow");
                this.f11109A = null;
            }
            this.f11130y.setAlpha(jVar.l / 255.0f);
            if (jVar.m()) {
                RenderNode renderNode = this.f11131z;
                if (renderNode == null) {
                    throw new IllegalStateException("Must initialize shadowRenderNode when we have shadow");
                }
                renderNode.setAlpha(jVar.l / 255.0f);
            }
            this.f11130y.setHasOverlappingRendering(true);
            RenderNode renderNode2 = this.f11130y;
            RectF rectF2 = this.f11117j;
            renderNode2.setPosition((int) rectF2.left, (int) rectF2.top, (int) rectF2.right, (int) rectF2.bottom);
            RecordingCanvas recordingCanvasBeginRecording = this.f11130y.beginRecording((int) this.f11117j.width(), (int) this.f11117j.height());
            recordingCanvasBeginRecording.setMatrix(matrix2);
            recordingCanvasBeginRecording.scale(f10, f11);
            recordingCanvasBeginRecording.translate(-rectF.left, -rectF.top);
            return recordingCanvasBeginRecording;
        }
        if (this.o == null) {
            P3.a aVar2 = new P3.a();
            this.o = aVar2;
            aVar2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        if (d(this.l, this.f11117j)) {
            Bitmap bitmap = this.l;
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.l = a(this.f11117j, Bitmap.Config.ARGB_8888);
            this.f11119m = new Canvas(this.l);
        } else {
            Canvas canvas2 = this.f11119m;
            if (canvas2 == null) {
                throw new IllegalStateException("If needNewBitmap() returns true, we should have a canvas ready");
            }
            canvas2.setMatrix(matrix2);
            this.f11119m.drawRect(-1.0f, -1.0f, this.f11117j.width() + 1.0f, this.f11117j.height() + 1.0f, this.o);
        }
        P3.a aVar3 = this.f11118k;
        int i11 = y1.c.f24115a;
        aVar3.setBlendMode(null);
        this.f11118k.setColorFilter(null);
        this.f11118k.setAlpha(jVar.l);
        Canvas canvas3 = this.f11119m;
        canvas3.scale(f10, f11);
        canvas3.translate(-rectF.left, -rectF.top);
        return canvas3;
    }
}
