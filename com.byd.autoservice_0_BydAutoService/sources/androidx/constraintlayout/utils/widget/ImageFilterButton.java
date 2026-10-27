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
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterButton extends AppCompatImageButton {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageFilterView.c f910d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f911e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f912f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f913g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Path f914h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ViewOutlineProvider f915i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public RectF f916j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable[] f917k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public LayerDrawable f918l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f919m;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), (Math.min(r3, r4) * ImageFilterButton.this.f912f) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.f913g);
        }
    }

    public ImageFilterButton(Context context) {
        super(context);
        this.f910d = new ImageFilterView.c();
        this.f911e = 0.0f;
        this.f912f = 0.0f;
        this.f913g = Float.NaN;
        this.f919m = true;
        c(context, null);
    }

    private void setOverlay(boolean z3) {
        this.f919m = z3;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ImageFilterView_altSrc);
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.ImageFilterView_crossfade) {
                    this.f911e = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
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
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.f919m));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.f917k = drawableArr;
                drawableArr[0] = getDrawable();
                Drawable[] drawableArr2 = this.f917k;
                drawableArr2[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(drawableArr2);
                this.f918l = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.f911e * 255.0f));
                super.setImageDrawable(this.f918l);
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z3;
        if (Build.VERSION.SDK_INT >= 21 || this.f913g == 0.0f || this.f914h == null) {
            z3 = false;
        } else {
            z3 = true;
            canvas.save();
            canvas.clipPath(this.f914h);
        }
        super.draw(canvas);
        if (z3) {
            canvas.restore();
        }
    }

    public float getContrast() {
        return this.f910d.f939f;
    }

    public float getCrossfade() {
        return this.f911e;
    }

    public float getRound() {
        return this.f913g;
    }

    public float getRoundPercent() {
        return this.f912f;
    }

    public float getSaturation() {
        return this.f910d.f938e;
    }

    public float getWarmth() {
        return this.f910d.f940g;
    }

    public void setBrightness(float f3) {
        ImageFilterView.c cVar = this.f910d;
        cVar.f937d = f3;
        cVar.c(this);
    }

    public void setContrast(float f3) {
        ImageFilterView.c cVar = this.f910d;
        cVar.f939f = f3;
        cVar.c(this);
    }

    public void setCrossfade(float f3) {
        this.f911e = f3;
        if (this.f917k != null) {
            if (!this.f919m) {
                this.f918l.getDrawable(0).setAlpha((int) ((1.0f - this.f911e) * 255.0f));
            }
            this.f918l.getDrawable(1).setAlpha((int) (this.f911e * 255.0f));
            super.setImageDrawable(this.f918l);
        }
    }

    public void setRound(float f3) {
        if (Float.isNaN(f3)) {
            this.f913g = f3;
            float f4 = this.f912f;
            this.f912f = -1.0f;
            setRoundPercent(f4);
            return;
        }
        boolean z3 = this.f913g != f3;
        this.f913g = f3;
        if (f3 != 0.0f) {
            if (this.f914h == null) {
                this.f914h = new Path();
            }
            if (this.f916j == null) {
                this.f916j = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f915i == null) {
                    b bVar = new b();
                    this.f915i = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.f916j.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f914h.reset();
            Path path = this.f914h;
            RectF rectF = this.f916j;
            float f5 = this.f913g;
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
        boolean z3 = this.f912f != f3;
        this.f912f = f3;
        if (f3 != 0.0f) {
            if (this.f914h == null) {
                this.f914h = new Path();
            }
            if (this.f916j == null) {
                this.f916j = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f915i == null) {
                    a aVar = new a();
                    this.f915i = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.f912f) / 2.0f;
            this.f916j.set(0.0f, 0.0f, width, height);
            this.f914h.reset();
            this.f914h.addRoundRect(this.f916j, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z3 || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f3) {
        ImageFilterView.c cVar = this.f910d;
        cVar.f938e = f3;
        cVar.c(this);
    }

    public void setWarmth(float f3) {
        ImageFilterView.c cVar = this.f910d;
        cVar.f940g = f3;
        cVar.c(this);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f910d = new ImageFilterView.c();
        this.f911e = 0.0f;
        this.f912f = 0.0f;
        this.f913g = Float.NaN;
        this.f919m = true;
        c(context, attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f910d = new ImageFilterView.c();
        this.f911e = 0.0f;
        this.f912f = 0.0f;
        this.f913g = Float.NaN;
        this.f919m = true;
        c(context, attributeSet);
    }
}
