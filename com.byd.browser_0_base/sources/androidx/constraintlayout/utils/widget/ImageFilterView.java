package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {
    public final c h;
    public boolean i;
    public Drawable j;
    public Drawable k;
    public float l;
    public float m;
    public float n;
    public Path o;
    public ViewOutlineProvider p;
    public RectF q;
    public final Drawable[] r;
    public LayerDrawable s;
    public float t;
    public float u;
    public float v;
    public float w;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            ImageFilterView imageFilterView = ImageFilterView.this;
            outline.setRoundRect(0, 0, imageFilterView.getWidth(), imageFilterView.getHeight(), (Math.min(r3, r4) * imageFilterView.m) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            ImageFilterView imageFilterView = ImageFilterView.this;
            outline.setRoundRect(0, 0, imageFilterView.getWidth(), imageFilterView.getHeight(), imageFilterView.n);
        }
    }

    public static class c {
        public final float[] a = new float[20];
        public final ColorMatrix b = new ColorMatrix();
        public final ColorMatrix c = new ColorMatrix();
        public float d = 1.0f;
        public float e = 1.0f;
        public float f = 1.0f;
        public float g = 1.0f;

        public final void a(ImageView imageView) {
            boolean z;
            float fLog;
            float fPow;
            float f;
            float fLog2;
            ColorMatrix colorMatrix = this.b;
            colorMatrix.reset();
            float f2 = this.e;
            float f3 = 1.0f;
            float[] fArr = this.a;
            boolean z2 = true;
            if (f2 != 1.0f) {
                float f4 = 1.0f - f2;
                float f5 = 0.2999f * f4;
                float f6 = 0.587f * f4;
                float f7 = f4 * 0.114f;
                fArr[0] = f5 + f2;
                fArr[1] = f6;
                fArr[2] = f7;
                fArr[3] = 0.0f;
                fArr[4] = 0.0f;
                fArr[5] = f5;
                fArr[6] = f6 + f2;
                fArr[7] = f7;
                fArr[8] = 0.0f;
                fArr[9] = 0.0f;
                fArr[10] = f5;
                fArr[11] = f6;
                fArr[12] = f7 + f2;
                fArr[13] = 0.0f;
                fArr[14] = 0.0f;
                fArr[15] = 0.0f;
                fArr[16] = 0.0f;
                fArr[17] = 0.0f;
                fArr[18] = 1.0f;
                fArr[19] = 0.0f;
                colorMatrix.set(fArr);
                z = true;
            } else {
                z = false;
            }
            float f8 = this.f;
            ColorMatrix colorMatrix2 = this.c;
            if (f8 != 1.0f) {
                colorMatrix2.setScale(f8, f8, f8, 1.0f);
                colorMatrix.postConcat(colorMatrix2);
                z = true;
            }
            float f9 = this.g;
            if (f9 != 1.0f) {
                if (f9 <= 0.0f) {
                    f9 = 0.01f;
                }
                float f10 = (5000.0f / f9) / 100.0f;
                if (f10 > 66.0f) {
                    double d = f10 - 60.0f;
                    fPow = ((float) Math.pow(d, -0.13320475816726685d)) * 329.69873f;
                    fLog = ((float) Math.pow(d, 0.07551485300064087d)) * 288.12216f;
                } else {
                    fLog = (((float) Math.log(f10)) * 99.4708f) - 161.11957f;
                    fPow = 255.0f;
                }
                if (f10 >= 66.0f) {
                    f = fLog;
                    fLog2 = 255.0f;
                } else if (f10 > 19.0f) {
                    f = fLog;
                    fLog2 = (((float) Math.log(f10 - 10.0f)) * 138.51773f) - 305.0448f;
                } else {
                    f = fLog;
                    fLog2 = 0.0f;
                }
                float fMin = Math.min(255.0f, Math.max(fPow, 0.0f));
                float fMin2 = Math.min(255.0f, Math.max(f, 0.0f));
                float fMin3 = Math.min(255.0f, Math.max(fLog2, 0.0f));
                float fLog3 = (((float) Math.log(50.0f)) * 99.4708f) - 161.11957f;
                float fLog4 = (((float) Math.log(40.0f)) * 138.51773f) - 305.0448f;
                float fMin4 = Math.min(255.0f, Math.max(255.0f, 0.0f));
                float fMin5 = Math.min(255.0f, Math.max(fLog3, 0.0f));
                float fMin6 = fMin3 / Math.min(255.0f, Math.max(fLog4, 0.0f));
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
                f3 = 1.0f;
                fArr[18] = 1.0f;
                fArr[19] = 0.0f;
                colorMatrix2.set(fArr);
                colorMatrix.postConcat(colorMatrix2);
                z = true;
            }
            float f11 = this.d;
            if (f11 != f3) {
                fArr[0] = f11;
                fArr[1] = 0.0f;
                fArr[2] = 0.0f;
                fArr[3] = 0.0f;
                fArr[4] = 0.0f;
                fArr[5] = 0.0f;
                fArr[6] = f11;
                fArr[7] = 0.0f;
                fArr[8] = 0.0f;
                fArr[9] = 0.0f;
                fArr[10] = 0.0f;
                fArr[11] = 0.0f;
                fArr[12] = f11;
                fArr[13] = 0.0f;
                fArr[14] = 0.0f;
                fArr[15] = 0.0f;
                fArr[16] = 0.0f;
                fArr[17] = 0.0f;
                fArr[18] = 1.0f;
                fArr[19] = 0.0f;
                colorMatrix2.set(fArr);
                colorMatrix.postConcat(colorMatrix2);
            } else {
                z2 = z;
            }
            if (z2) {
                imageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            } else {
                imageView.clearColorFilter();
            }
        }
    }

    public ImageFilterView(Context context) {
        super(context);
        this.h = new c();
        this.i = true;
        this.j = null;
        this.k = null;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = Float.NaN;
        this.r = new Drawable[2];
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = Float.NaN;
    }

    private void setOverlay(boolean z) {
        this.i = z;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            this.j = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ImageFilterView_altSrc);
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ImageFilterView_crossfade) {
                    this.l = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R$styleable.ImageFilterView_warmth) {
                    setWarmth(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_saturation) {
                    setSaturation(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_contrast) {
                    setContrast(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_brightness) {
                    setBrightness(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_round) {
                    setRound(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_roundPercent) {
                    setRoundPercent(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_overlay) {
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.i));
                } else if (index == R$styleable.ImageFilterView_imagePanX) {
                    setImagePanX(typedArrayObtainStyledAttributes.getFloat(index, this.t));
                } else if (index == R$styleable.ImageFilterView_imagePanY) {
                    setImagePanY(typedArrayObtainStyledAttributes.getFloat(index, this.u));
                } else if (index == R$styleable.ImageFilterView_imageRotate) {
                    setImageRotate(typedArrayObtainStyledAttributes.getFloat(index, this.w));
                } else if (index == R$styleable.ImageFilterView_imageZoom) {
                    setImageZoom(typedArrayObtainStyledAttributes.getFloat(index, this.v));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            Drawable drawable = getDrawable();
            this.k = drawable;
            Drawable drawable2 = this.j;
            Drawable[] drawableArr = this.r;
            if (drawable2 == null || drawable == null) {
                Drawable drawable3 = getDrawable();
                this.k = drawable3;
                if (drawable3 != null) {
                    Drawable drawableMutate = drawable3.mutate();
                    this.k = drawableMutate;
                    drawableArr[0] = drawableMutate;
                    return;
                }
                return;
            }
            Drawable drawableMutate2 = getDrawable().mutate();
            this.k = drawableMutate2;
            drawableArr[0] = drawableMutate2;
            drawableArr[1] = this.j.mutate();
            LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
            this.s = layerDrawable;
            layerDrawable.getDrawable(1).setAlpha((int) (this.l * 255.0f));
            if (!this.i) {
                this.s.getDrawable(0).setAlpha((int) ((1.0f - this.l) * 255.0f));
            }
            super.setImageDrawable(this.s);
        }
    }

    public final void d() {
        if (Float.isNaN(this.t) && Float.isNaN(this.u) && Float.isNaN(this.v) && Float.isNaN(this.w)) {
            return;
        }
        float f = Float.isNaN(this.t) ? 0.0f : this.t;
        float f2 = Float.isNaN(this.u) ? 0.0f : this.u;
        float f3 = Float.isNaN(this.v) ? 1.0f : this.v;
        float f4 = Float.isNaN(this.w) ? 0.0f : this.w;
        Matrix matrix = new Matrix();
        matrix.reset();
        float intrinsicWidth = getDrawable().getIntrinsicWidth();
        float intrinsicHeight = getDrawable().getIntrinsicHeight();
        float width = getWidth();
        float height = getHeight();
        float f5 = f3 * (intrinsicWidth * height < intrinsicHeight * width ? width / intrinsicWidth : height / intrinsicHeight);
        matrix.postScale(f5, f5);
        float f6 = intrinsicWidth * f5;
        float f7 = f5 * intrinsicHeight;
        matrix.postTranslate(((((width - f6) * f) + width) - f6) * 0.5f, ((((height - f7) * f2) + height) - f7) * 0.5f);
        matrix.postRotate(f4, width / 2.0f, height / 2.0f);
        setImageMatrix(matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public final void f() {
        if (Float.isNaN(this.t) && Float.isNaN(this.u) && Float.isNaN(this.v) && Float.isNaN(this.w)) {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            d();
        }
    }

    public float getBrightness() {
        return this.h.d;
    }

    public float getContrast() {
        return this.h.f;
    }

    public float getCrossfade() {
        return this.l;
    }

    public float getImagePanX() {
        return this.t;
    }

    public float getImagePanY() {
        return this.u;
    }

    public float getImageRotate() {
        return this.w;
    }

    public float getImageZoom() {
        return this.v;
    }

    public float getRound() {
        return this.n;
    }

    public float getRoundPercent() {
        return this.m;
    }

    public float getSaturation() {
        return this.h.e;
    }

    public float getWarmth() {
        return this.h.g;
    }

    @Override // android.view.View
    public final void layout(int i, int i2, int i3, int i4) {
        super.layout(i, i2, i3, i4);
        d();
    }

    public void setAltImageDrawable(Drawable drawable) {
        Drawable drawableMutate = drawable.mutate();
        this.j = drawableMutate;
        Drawable drawable2 = this.k;
        Drawable[] drawableArr = this.r;
        drawableArr[0] = drawable2;
        drawableArr[1] = drawableMutate;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.s = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.l);
    }

    public void setAltImageResource(int i) {
        Drawable drawableA = AppCompatResources.a(getContext(), i);
        this.j = drawableA;
        setAltImageDrawable(drawableA);
    }

    public void setBrightness(float f) {
        c cVar = this.h;
        cVar.d = f;
        cVar.a(this);
    }

    public void setContrast(float f) {
        c cVar = this.h;
        cVar.f = f;
        cVar.a(this);
    }

    public void setCrossfade(float f) {
        this.l = f;
        if (this.r != null) {
            if (!this.i) {
                this.s.getDrawable(0).setAlpha((int) ((1.0f - this.l) * 255.0f));
            }
            this.s.getDrawable(1).setAlpha((int) (this.l * 255.0f));
            super.setImageDrawable(this.s);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.j == null || drawable == null) {
            super.setImageDrawable(drawable);
            return;
        }
        Drawable drawableMutate = drawable.mutate();
        this.k = drawableMutate;
        Drawable[] drawableArr = this.r;
        drawableArr[0] = drawableMutate;
        drawableArr[1] = this.j;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.s = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.l);
    }

    public void setImagePanX(float f) {
        this.t = f;
        f();
    }

    public void setImagePanY(float f) {
        this.u = f;
        f();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        if (this.j == null) {
            super.setImageResource(i);
            return;
        }
        Drawable drawableMutate = AppCompatResources.a(getContext(), i).mutate();
        this.k = drawableMutate;
        Drawable[] drawableArr = this.r;
        drawableArr[0] = drawableMutate;
        drawableArr[1] = this.j;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.s = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.l);
    }

    public void setImageRotate(float f) {
        this.w = f;
        f();
    }

    public void setImageZoom(float f) {
        this.v = f;
        f();
    }

    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.n = f;
            float f2 = this.m;
            this.m = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.n != f;
        this.n = f;
        if (f != 0.0f) {
            if (this.o == null) {
                this.o = new Path();
            }
            if (this.q == null) {
                this.q = new RectF();
            }
            if (this.p == null) {
                b bVar = new b();
                this.p = bVar;
                setOutlineProvider(bVar);
            }
            setClipToOutline(true);
            this.q.set(0.0f, 0.0f, getWidth(), getHeight());
            this.o.reset();
            Path path = this.o;
            RectF rectF = this.q;
            float f3 = this.n;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f) {
        boolean z = this.m != f;
        this.m = f;
        if (f != 0.0f) {
            if (this.o == null) {
                this.o = new Path();
            }
            if (this.q == null) {
                this.q = new RectF();
            }
            if (this.p == null) {
                a aVar = new a();
                this.p = aVar;
                setOutlineProvider(aVar);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.m) / 2.0f;
            this.q.set(0.0f, 0.0f, width, height);
            this.o.reset();
            this.o.addRoundRect(this.q, fMin, fMin, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setSaturation(float f) {
        c cVar = this.h;
        cVar.e = f;
        cVar.a(this);
    }

    public void setWarmth(float f) {
        c cVar = this.h;
        cVar.g = f;
        cVar.a(this);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new c();
        this.i = true;
        this.j = null;
        this.k = null;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = Float.NaN;
        this.r = new Drawable[2];
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = Float.NaN;
        c(context, attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new c();
        this.i = true;
        this.j = null;
        this.k = null;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = Float.NaN;
        this.r = new Drawable[2];
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = Float.NaN;
        c(context, attributeSet);
    }
}
