package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import b.f.c.e;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterButton extends AppCompatImageButton {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageFilterView.c f660d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f661e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f662f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f663g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Path f664h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ViewOutlineProvider f665i;
    public RectF j;
    public Drawable[] k;
    public LayerDrawable l;
    public boolean m;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), (Math.min(r3, r4) * ImageFilterButton.this.f662f) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.f663g);
        }
    }

    public ImageFilterButton(Context context) {
        super(context);
        this.f660d = new ImageFilterView.c();
        this.f661e = 0.0f;
        this.f662f = 0.0f;
        this.f663g = Float.NaN;
        this.m = true;
        c(context, null);
    }

    private void setOverlay(boolean z) {
        this.m = z;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(e.ImageFilterView_altSrc);
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.ImageFilterView_crossfade) {
                    this.f661e = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == e.ImageFilterView_warmth) {
                    setWarmth(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == e.ImageFilterView_saturation) {
                    setSaturation(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == e.ImageFilterView_contrast) {
                    setContrast(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == e.ImageFilterView_round) {
                    setRound(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == e.ImageFilterView_roundPercent) {
                    setRoundPercent(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == e.ImageFilterView_overlay) {
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.m));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.k = drawableArr;
                drawableArr[0] = getDrawable();
                Drawable[] drawableArr2 = this.k;
                drawableArr2[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(drawableArr2);
                this.l = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.f661e * 255.0f));
                super.setImageDrawable(this.l);
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.f663g == 0.0f || this.f664h == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.f664h);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public float getContrast() {
        return this.f660d.f681f;
    }

    public float getCrossfade() {
        return this.f661e;
    }

    public float getRound() {
        return this.f663g;
    }

    public float getRoundPercent() {
        return this.f662f;
    }

    public float getSaturation() {
        return this.f660d.f680e;
    }

    public float getWarmth() {
        return this.f660d.f682g;
    }

    public void setBrightness(float f2) {
        ImageFilterView.c cVar = this.f660d;
        cVar.f679d = f2;
        cVar.c(this);
    }

    public void setContrast(float f2) {
        ImageFilterView.c cVar = this.f660d;
        cVar.f681f = f2;
        cVar.c(this);
    }

    public void setCrossfade(float f2) {
        this.f661e = f2;
        if (this.k != null) {
            if (!this.m) {
                this.l.getDrawable(0).setAlpha((int) ((1.0f - this.f661e) * 255.0f));
            }
            this.l.getDrawable(1).setAlpha((int) (this.f661e * 255.0f));
            super.setImageDrawable(this.l);
        }
    }

    public void setRound(float f2) {
        if (Float.isNaN(f2)) {
            this.f663g = f2;
            float f3 = this.f662f;
            this.f662f = -1.0f;
            setRoundPercent(f3);
            return;
        }
        boolean z = this.f663g != f2;
        this.f663g = f2;
        if (f2 != 0.0f) {
            if (this.f664h == null) {
                this.f664h = new Path();
            }
            if (this.j == null) {
                this.j = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f665i == null) {
                    b bVar = new b();
                    this.f665i = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.j.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f664h.reset();
            Path path = this.f664h;
            RectF rectF = this.j;
            float f4 = this.f663g;
            path.addRoundRect(rectF, f4, f4, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setRoundPercent(float f2) {
        boolean z = this.f662f != f2;
        this.f662f = f2;
        if (f2 != 0.0f) {
            if (this.f664h == null) {
                this.f664h = new Path();
            }
            if (this.j == null) {
                this.j = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f665i == null) {
                    a aVar = new a();
                    this.f665i = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.f662f) / 2.0f;
            this.j.set(0.0f, 0.0f, width, height);
            this.f664h.reset();
            this.f664h.addRoundRect(this.j, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f2) {
        ImageFilterView.c cVar = this.f660d;
        cVar.f680e = f2;
        cVar.c(this);
    }

    public void setWarmth(float f2) {
        ImageFilterView.c cVar = this.f660d;
        cVar.f682g = f2;
        cVar.c(this);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f660d = new ImageFilterView.c();
        this.f661e = 0.0f;
        this.f662f = 0.0f;
        this.f663g = Float.NaN;
        this.m = true;
        c(context, attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f660d = new ImageFilterView.c();
        this.f661e = 0.0f;
        this.f662f = 0.0f;
        this.f663g = Float.NaN;
        this.m = true;
        c(context, attributeSet);
    }
}
