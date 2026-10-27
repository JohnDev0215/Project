package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f922d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f923e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f924f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f925g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f926h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Path f927i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ViewOutlineProvider f928j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public RectF f929k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable[] f930l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public LayerDrawable f931m;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), (Math.min(r3, r4) * ImageFilterView.this.f925g) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), ImageFilterView.this.f926h);
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float[] f934a = new float[20];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ColorMatrix f935b = new ColorMatrix();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorMatrix f936c = new ColorMatrix();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f937d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f938e = 1.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f939f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f940g = 1.0f;

        public final void a(float f3) {
            float[] fArr = this.f934a;
            fArr[0] = f3;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = f3;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = f3;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        public final void b(float f3) {
            float f4 = 1.0f - f3;
            float f5 = 0.2999f * f4;
            float f6 = 0.587f * f4;
            float f7 = f4 * 0.114f;
            float[] fArr = this.f934a;
            fArr[0] = f5 + f3;
            fArr[1] = f6;
            fArr[2] = f7;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = f5;
            fArr[6] = f6 + f3;
            fArr[7] = f7;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = f5;
            fArr[11] = f6;
            fArr[12] = f7 + f3;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        public void c(ImageView imageView) {
            boolean z3;
            this.f935b.reset();
            float f3 = this.f938e;
            boolean z4 = true;
            if (f3 != 1.0f) {
                b(f3);
                this.f935b.set(this.f934a);
                z3 = true;
            } else {
                z3 = false;
            }
            float f4 = this.f939f;
            if (f4 != 1.0f) {
                this.f936c.setScale(f4, f4, f4, 1.0f);
                this.f935b.postConcat(this.f936c);
                z3 = true;
            }
            float f5 = this.f940g;
            if (f5 != 1.0f) {
                d(f5);
                this.f936c.set(this.f934a);
                this.f935b.postConcat(this.f936c);
                z3 = true;
            }
            float f6 = this.f937d;
            if (f6 != 1.0f) {
                a(f6);
                this.f936c.set(this.f934a);
                this.f935b.postConcat(this.f936c);
            } else {
                z4 = z3;
            }
            if (z4) {
                imageView.setColorFilter(new ColorMatrixColorFilter(this.f935b));
            } else {
                imageView.clearColorFilter();
            }
        }

        public final void d(float f3) {
            float fLog;
            float fPow;
            if (f3 <= 0.0f) {
                f3 = 0.01f;
            }
            float f4 = (5000.0f / f3) / 100.0f;
            if (f4 > 66.0f) {
                double d3 = f4 - 60.0f;
                fPow = ((float) Math.pow(d3, -0.13320475816726685d)) * 329.69873f;
                fLog = ((float) Math.pow(d3, 0.07551484555006027d)) * 288.12216f;
            } else {
                fLog = (((float) Math.log(f4)) * 99.4708f) - 161.11957f;
                fPow = 255.0f;
            }
            float fLog2 = f4 < 66.0f ? f4 > 19.0f ? (((float) Math.log(f4 - 10.0f)) * 138.51773f) - 305.0448f : 0.0f : 255.0f;
            float fMin = Math.min(255.0f, Math.max(fPow, 0.0f));
            float fMin2 = Math.min(255.0f, Math.max(fLog, 0.0f));
            float fMin3 = Math.min(255.0f, Math.max(fLog2, 0.0f));
            float fLog3 = (((float) Math.log(50.0f)) * 99.4708f) - 161.11957f;
            float fLog4 = (((float) Math.log(40.0f)) * 138.51773f) - 305.0448f;
            float fMin4 = Math.min(255.0f, Math.max(255.0f, 0.0f));
            float fMin5 = Math.min(255.0f, Math.max(fLog3, 0.0f));
            float fMin6 = fMin3 / Math.min(255.0f, Math.max(fLog4, 0.0f));
            float[] fArr = this.f934a;
            fArr[0] = fMin / fMin4;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = fMin2 / fMin5;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = fMin6;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }
    }

    public ImageFilterView(Context context) {
        super(context);
        this.f922d = new c();
        this.f923e = true;
        this.f924f = 0.0f;
        this.f925g = 0.0f;
        this.f926h = Float.NaN;
        e(context, null);
    }

    private void setOverlay(boolean z3) {
        this.f923e = z3;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z3;
        if (Build.VERSION.SDK_INT >= 21 || this.f925g == 0.0f || this.f927i == null) {
            z3 = false;
        } else {
            z3 = true;
            canvas.save();
            canvas.clipPath(this.f927i);
        }
        super.draw(canvas);
        if (z3) {
            canvas.restore();
        }
    }

    public final void e(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ImageFilterView_altSrc);
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.ImageFilterView_crossfade) {
                    this.f924f = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R$styleable.ImageFilterView_warmth) {
                    setWarmth(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_saturation) {
                    setSaturation(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_contrast) {
                    setContrast(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_round) {
                    setRound(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_roundPercent) {
                    setRoundPercent(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_overlay) {
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.f923e));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.f930l = drawableArr;
                drawableArr[0] = getDrawable();
                Drawable[] drawableArr2 = this.f930l;
                drawableArr2[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(drawableArr2);
                this.f931m = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.f924f * 255.0f));
                super.setImageDrawable(this.f931m);
            }
        }
    }

    public float getBrightness() {
        return this.f922d.f937d;
    }

    public float getContrast() {
        return this.f922d.f939f;
    }

    public float getCrossfade() {
        return this.f924f;
    }

    public float getRound() {
        return this.f926h;
    }

    public float getRoundPercent() {
        return this.f925g;
    }

    public float getSaturation() {
        return this.f922d.f938e;
    }

    public float getWarmth() {
        return this.f922d.f940g;
    }

    public void setBrightness(float f3) {
        c cVar = this.f922d;
        cVar.f937d = f3;
        cVar.c(this);
    }

    public void setContrast(float f3) {
        c cVar = this.f922d;
        cVar.f939f = f3;
        cVar.c(this);
    }

    public void setCrossfade(float f3) {
        this.f924f = f3;
        if (this.f930l != null) {
            if (!this.f923e) {
                this.f931m.getDrawable(0).setAlpha((int) ((1.0f - this.f924f) * 255.0f));
            }
            this.f931m.getDrawable(1).setAlpha((int) (this.f924f * 255.0f));
            super.setImageDrawable(this.f931m);
        }
    }

    public void setRound(float f3) {
        if (Float.isNaN(f3)) {
            this.f926h = f3;
            float f4 = this.f925g;
            this.f925g = -1.0f;
            setRoundPercent(f4);
            return;
        }
        boolean z3 = this.f926h != f3;
        this.f926h = f3;
        if (f3 != 0.0f) {
            if (this.f927i == null) {
                this.f927i = new Path();
            }
            if (this.f929k == null) {
                this.f929k = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f928j == null) {
                    b bVar = new b();
                    this.f928j = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.f929k.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f927i.reset();
            Path path = this.f927i;
            RectF rectF = this.f929k;
            float f5 = this.f926h;
            path.addRoundRect(rectF, f5, f5, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z3 || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setRoundPercent(float f3) {
        boolean z3 = this.f925g != f3;
        this.f925g = f3;
        if (f3 != 0.0f) {
            if (this.f927i == null) {
                this.f927i = new Path();
            }
            if (this.f929k == null) {
                this.f929k = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f928j == null) {
                    a aVar = new a();
                    this.f928j = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.f925g) / 2.0f;
            this.f929k.set(0.0f, 0.0f, width, height);
            this.f927i.reset();
            this.f927i.addRoundRect(this.f929k, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z3 || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f3) {
        c cVar = this.f922d;
        cVar.f938e = f3;
        cVar.c(this);
    }

    public void setWarmth(float f3) {
        c cVar = this.f922d;
        cVar.f940g = f3;
        cVar.c(this);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f922d = new c();
        this.f923e = true;
        this.f924f = 0.0f;
        this.f925g = 0.0f;
        this.f926h = Float.NaN;
        e(context, attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f922d = new c();
        this.f923e = true;
        this.f924f = 0.0f;
        this.f925g = 0.0f;
        this.f926h = Float.NaN;
        e(context, attributeSet);
    }
}
