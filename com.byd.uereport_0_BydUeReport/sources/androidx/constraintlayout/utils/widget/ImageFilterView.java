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

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f1013f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1014g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f1015h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1016i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f1017j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Path f1018k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ViewOutlineProvider f1019l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public RectF f1020m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable[] f1021n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public LayerDrawable f1022o;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), (Math.min(r3, r4) * ImageFilterView.this.f1016i) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), ImageFilterView.this.f1017j);
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float[] f1025a = new float[20];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ColorMatrix f1026b = new ColorMatrix();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorMatrix f1027c = new ColorMatrix();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1028d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1029e = 1.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f1030f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1031g = 1.0f;

        public final void a(float f8) {
            float[] fArr = this.f1025a;
            fArr[0] = f8;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = f8;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = f8;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        public final void b(float f8) {
            float f9 = 1.0f - f8;
            float f10 = 0.2999f * f9;
            float f11 = 0.587f * f9;
            float f12 = f9 * 0.114f;
            float[] fArr = this.f1025a;
            fArr[0] = f10 + f8;
            fArr[1] = f11;
            fArr[2] = f12;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = f10;
            fArr[6] = f11 + f8;
            fArr[7] = f12;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = f10;
            fArr[11] = f11;
            fArr[12] = f12 + f8;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        public void c(ImageView imageView) {
            boolean z7;
            this.f1026b.reset();
            float f8 = this.f1029e;
            boolean z8 = true;
            if (f8 != 1.0f) {
                b(f8);
                this.f1026b.set(this.f1025a);
                z7 = true;
            } else {
                z7 = false;
            }
            float f9 = this.f1030f;
            if (f9 != 1.0f) {
                this.f1027c.setScale(f9, f9, f9, 1.0f);
                this.f1026b.postConcat(this.f1027c);
                z7 = true;
            }
            float f10 = this.f1031g;
            if (f10 != 1.0f) {
                d(f10);
                this.f1027c.set(this.f1025a);
                this.f1026b.postConcat(this.f1027c);
                z7 = true;
            }
            float f11 = this.f1028d;
            if (f11 != 1.0f) {
                a(f11);
                this.f1027c.set(this.f1025a);
                this.f1026b.postConcat(this.f1027c);
            } else {
                z8 = z7;
            }
            if (z8) {
                imageView.setColorFilter(new ColorMatrixColorFilter(this.f1026b));
            } else {
                imageView.clearColorFilter();
            }
        }

        public final void d(float f8) {
            float fLog;
            float fPow;
            if (f8 <= 0.0f) {
                f8 = 0.01f;
            }
            float f9 = (5000.0f / f8) / 100.0f;
            if (f9 > 66.0f) {
                double d8 = f9 - 60.0f;
                fPow = ((float) Math.pow(d8, -0.13320475816726685d)) * 329.69873f;
                fLog = ((float) Math.pow(d8, 0.07551484555006027d)) * 288.12216f;
            } else {
                fLog = (((float) Math.log(f9)) * 99.4708f) - 161.11957f;
                fPow = 255.0f;
            }
            float fLog2 = f9 < 66.0f ? f9 > 19.0f ? (((float) Math.log(f9 - 10.0f)) * 138.51773f) - 305.0448f : 0.0f : 255.0f;
            float fMin = Math.min(255.0f, Math.max(fPow, 0.0f));
            float fMin2 = Math.min(255.0f, Math.max(fLog, 0.0f));
            float fMin3 = Math.min(255.0f, Math.max(fLog2, 0.0f));
            float fLog3 = (((float) Math.log(50.0f)) * 99.4708f) - 161.11957f;
            float fLog4 = (((float) Math.log(40.0f)) * 138.51773f) - 305.0448f;
            float fMin4 = Math.min(255.0f, Math.max(255.0f, 0.0f));
            float fMin5 = Math.min(255.0f, Math.max(fLog3, 0.0f));
            float fMin6 = fMin3 / Math.min(255.0f, Math.max(fLog4, 0.0f));
            float[] fArr = this.f1025a;
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
        this.f1013f = new c();
        this.f1014g = true;
        this.f1015h = 0.0f;
        this.f1016i = 0.0f;
        this.f1017j = Float.NaN;
        e(context, null);
    }

    private void setOverlay(boolean z7) {
        this.f1014g = z7;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z7;
        if (Build.VERSION.SDK_INT >= 21 || this.f1016i == 0.0f || this.f1018k == null) {
            z7 = false;
        } else {
            z7 = true;
            canvas.save();
            canvas.clipPath(this.f1018k);
        }
        super.draw(canvas);
        if (z7) {
            canvas.restore();
        }
    }

    public final void e(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ImageFilterView_altSrc);
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.ImageFilterView_crossfade) {
                    this.f1015h = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
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
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.f1014g));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.f1021n = drawableArr;
                drawableArr[0] = getDrawable();
                Drawable[] drawableArr2 = this.f1021n;
                drawableArr2[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(drawableArr2);
                this.f1022o = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.f1015h * 255.0f));
                super.setImageDrawable(this.f1022o);
            }
        }
    }

    public float getBrightness() {
        return this.f1013f.f1028d;
    }

    public float getContrast() {
        return this.f1013f.f1030f;
    }

    public float getCrossfade() {
        return this.f1015h;
    }

    public float getRound() {
        return this.f1017j;
    }

    public float getRoundPercent() {
        return this.f1016i;
    }

    public float getSaturation() {
        return this.f1013f.f1029e;
    }

    public float getWarmth() {
        return this.f1013f.f1031g;
    }

    public void setBrightness(float f8) {
        c cVar = this.f1013f;
        cVar.f1028d = f8;
        cVar.c(this);
    }

    public void setContrast(float f8) {
        c cVar = this.f1013f;
        cVar.f1030f = f8;
        cVar.c(this);
    }

    public void setCrossfade(float f8) {
        this.f1015h = f8;
        if (this.f1021n != null) {
            if (!this.f1014g) {
                this.f1022o.getDrawable(0).setAlpha((int) ((1.0f - this.f1015h) * 255.0f));
            }
            this.f1022o.getDrawable(1).setAlpha((int) (this.f1015h * 255.0f));
            super.setImageDrawable(this.f1022o);
        }
    }

    public void setRound(float f8) {
        if (Float.isNaN(f8)) {
            this.f1017j = f8;
            float f9 = this.f1016i;
            this.f1016i = -1.0f;
            setRoundPercent(f9);
            return;
        }
        boolean z7 = this.f1017j != f8;
        this.f1017j = f8;
        if (f8 != 0.0f) {
            if (this.f1018k == null) {
                this.f1018k = new Path();
            }
            if (this.f1020m == null) {
                this.f1020m = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f1019l == null) {
                    b bVar = new b();
                    this.f1019l = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.f1020m.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f1018k.reset();
            Path path = this.f1018k;
            RectF rectF = this.f1020m;
            float f10 = this.f1017j;
            path.addRoundRect(rectF, f10, f10, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z7 || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setRoundPercent(float f8) {
        boolean z7 = this.f1016i != f8;
        this.f1016i = f8;
        if (f8 != 0.0f) {
            if (this.f1018k == null) {
                this.f1018k = new Path();
            }
            if (this.f1020m == null) {
                this.f1020m = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f1019l == null) {
                    a aVar = new a();
                    this.f1019l = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.f1016i) / 2.0f;
            this.f1020m.set(0.0f, 0.0f, width, height);
            this.f1018k.reset();
            this.f1018k.addRoundRect(this.f1020m, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z7 || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f8) {
        c cVar = this.f1013f;
        cVar.f1029e = f8;
        cVar.c(this);
    }

    public void setWarmth(float f8) {
        c cVar = this.f1013f;
        cVar.f1031g = f8;
        cVar.c(this);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1013f = new c();
        this.f1014g = true;
        this.f1015h = 0.0f;
        this.f1016i = 0.0f;
        this.f1017j = Float.NaN;
        e(context, attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1013f = new c();
        this.f1014g = true;
        this.f1015h = 0.0f;
        this.f1016i = 0.0f;
        this.f1017j = Float.NaN;
        e(context, attributeSet);
    }
}
