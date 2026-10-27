package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterButton extends AppCompatImageButton {
    public final ImageFilterView.c h;
    public float i;
    public float j;
    public float k;
    public Path l;
    public ViewOutlineProvider m;
    public RectF n;
    public final Drawable[] o;
    public LayerDrawable p;
    public boolean q;
    public Drawable r;
    public Drawable s;
    public float t;
    public float u;
    public float v;
    public float w;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            ImageFilterButton imageFilterButton = ImageFilterButton.this;
            outline.setRoundRect(0, 0, imageFilterButton.getWidth(), imageFilterButton.getHeight(), (Math.min(r3, r4) * imageFilterButton.j) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            ImageFilterButton imageFilterButton = ImageFilterButton.this;
            outline.setRoundRect(0, 0, imageFilterButton.getWidth(), imageFilterButton.getHeight(), imageFilterButton.k);
        }
    }

    public ImageFilterButton(Context context) {
        super(context);
        this.h = new ImageFilterView.c();
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = Float.NaN;
        this.o = new Drawable[2];
        this.q = true;
        this.r = null;
        this.s = null;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = Float.NaN;
        b(context, null);
    }

    private void setOverlay(boolean z) {
        this.q = z;
    }

    public final void b(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            this.r = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ImageFilterView_altSrc);
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ImageFilterView_crossfade) {
                    this.i = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
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
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.q));
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
            this.s = drawable;
            Drawable drawable2 = this.r;
            Drawable[] drawableArr = this.o;
            if (drawable2 == null || drawable == null) {
                Drawable drawable3 = getDrawable();
                this.s = drawable3;
                if (drawable3 != null) {
                    Drawable drawableMutate = drawable3.mutate();
                    this.s = drawableMutate;
                    drawableArr[0] = drawableMutate;
                    return;
                }
                return;
            }
            Drawable drawableMutate2 = getDrawable().mutate();
            this.s = drawableMutate2;
            drawableArr[0] = drawableMutate2;
            drawableArr[1] = this.r.mutate();
            LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
            this.p = layerDrawable;
            layerDrawable.getDrawable(1).setAlpha((int) (this.i * 255.0f));
            if (!this.q) {
                this.p.getDrawable(0).setAlpha((int) ((1.0f - this.i) * 255.0f));
            }
            super.setImageDrawable(this.p);
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

    public float getContrast() {
        return this.h.f;
    }

    public float getCrossfade() {
        return this.i;
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
        return this.k;
    }

    public float getRoundPercent() {
        return this.j;
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

    public void setAltImageResource(int i) {
        Drawable drawableMutate = AppCompatResources.a(getContext(), i).mutate();
        this.r = drawableMutate;
        Drawable drawable = this.s;
        Drawable[] drawableArr = this.o;
        drawableArr[0] = drawable;
        drawableArr[1] = drawableMutate;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.p = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.i);
    }

    public void setBrightness(float f) {
        ImageFilterView.c cVar = this.h;
        cVar.d = f;
        cVar.a(this);
    }

    public void setContrast(float f) {
        ImageFilterView.c cVar = this.h;
        cVar.f = f;
        cVar.a(this);
    }

    public void setCrossfade(float f) {
        this.i = f;
        if (this.o != null) {
            if (!this.q) {
                this.p.getDrawable(0).setAlpha((int) ((1.0f - this.i) * 255.0f));
            }
            this.p.getDrawable(1).setAlpha((int) (this.i * 255.0f));
            super.setImageDrawable(this.p);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.r == null || drawable == null) {
            super.setImageDrawable(drawable);
            return;
        }
        Drawable drawableMutate = drawable.mutate();
        this.s = drawableMutate;
        Drawable[] drawableArr = this.o;
        drawableArr[0] = drawableMutate;
        drawableArr[1] = this.r;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.p = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.i);
    }

    public void setImagePanX(float f) {
        this.t = f;
        f();
    }

    public void setImagePanY(float f) {
        this.u = f;
        f();
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView
    public void setImageResource(int i) {
        if (this.r == null) {
            super.setImageResource(i);
            return;
        }
        Drawable drawableMutate = AppCompatResources.a(getContext(), i).mutate();
        this.s = drawableMutate;
        Drawable[] drawableArr = this.o;
        drawableArr[0] = drawableMutate;
        drawableArr[1] = this.r;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.p = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.i);
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
            this.k = f;
            float f2 = this.j;
            this.j = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.k != f;
        this.k = f;
        if (f != 0.0f) {
            if (this.l == null) {
                this.l = new Path();
            }
            if (this.n == null) {
                this.n = new RectF();
            }
            if (this.m == null) {
                b bVar = new b();
                this.m = bVar;
                setOutlineProvider(bVar);
            }
            setClipToOutline(true);
            this.n.set(0.0f, 0.0f, getWidth(), getHeight());
            this.l.reset();
            Path path = this.l;
            RectF rectF = this.n;
            float f3 = this.k;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f) {
        boolean z = this.j != f;
        this.j = f;
        if (f != 0.0f) {
            if (this.l == null) {
                this.l = new Path();
            }
            if (this.n == null) {
                this.n = new RectF();
            }
            if (this.m == null) {
                a aVar = new a();
                this.m = aVar;
                setOutlineProvider(aVar);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.j) / 2.0f;
            this.n.set(0.0f, 0.0f, width, height);
            this.l.reset();
            this.l.addRoundRect(this.n, fMin, fMin, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setSaturation(float f) {
        ImageFilterView.c cVar = this.h;
        cVar.e = f;
        cVar.a(this);
    }

    public void setWarmth(float f) {
        ImageFilterView.c cVar = this.h;
        cVar.g = f;
        cVar.a(this);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new ImageFilterView.c();
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = Float.NaN;
        this.o = new Drawable[2];
        this.q = true;
        this.r = null;
        this.s = null;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = Float.NaN;
        b(context, attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new ImageFilterView.c();
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = Float.NaN;
        this.o = new Drawable[2];
        this.q = true;
        this.r = null;
        this.s = null;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = Float.NaN;
        b(context, attributeSet);
    }
}
