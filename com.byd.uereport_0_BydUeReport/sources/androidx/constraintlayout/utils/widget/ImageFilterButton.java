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

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ImageFilterView.c f1001f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f1002g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f1003h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1004i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Path f1005j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ViewOutlineProvider f1006k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public RectF f1007l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Drawable[] f1008m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public LayerDrawable f1009n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1010o;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), (Math.min(r3, r4) * ImageFilterButton.this.f1003h) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.f1004i);
        }
    }

    public ImageFilterButton(Context context) {
        super(context);
        this.f1001f = new ImageFilterView.c();
        this.f1002g = 0.0f;
        this.f1003h = 0.0f;
        this.f1004i = Float.NaN;
        this.f1010o = true;
        c(context, null);
    }

    private void setOverlay(boolean z7) {
        this.f1010o = z7;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ImageFilterView_altSrc);
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.ImageFilterView_crossfade) {
                    this.f1002g = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
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
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.f1010o));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.f1008m = drawableArr;
                drawableArr[0] = getDrawable();
                Drawable[] drawableArr2 = this.f1008m;
                drawableArr2[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(drawableArr2);
                this.f1009n = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.f1002g * 255.0f));
                super.setImageDrawable(this.f1009n);
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z7;
        if (Build.VERSION.SDK_INT >= 21 || this.f1004i == 0.0f || this.f1005j == null) {
            z7 = false;
        } else {
            z7 = true;
            canvas.save();
            canvas.clipPath(this.f1005j);
        }
        super.draw(canvas);
        if (z7) {
            canvas.restore();
        }
    }

    public float getContrast() {
        return this.f1001f.f1030f;
    }

    public float getCrossfade() {
        return this.f1002g;
    }

    public float getRound() {
        return this.f1004i;
    }

    public float getRoundPercent() {
        return this.f1003h;
    }

    public float getSaturation() {
        return this.f1001f.f1029e;
    }

    public float getWarmth() {
        return this.f1001f.f1031g;
    }

    public void setBrightness(float f8) {
        ImageFilterView.c cVar = this.f1001f;
        cVar.f1028d = f8;
        cVar.c(this);
    }

    public void setContrast(float f8) {
        ImageFilterView.c cVar = this.f1001f;
        cVar.f1030f = f8;
        cVar.c(this);
    }

    public void setCrossfade(float f8) {
        this.f1002g = f8;
        if (this.f1008m != null) {
            if (!this.f1010o) {
                this.f1009n.getDrawable(0).setAlpha((int) ((1.0f - this.f1002g) * 255.0f));
            }
            this.f1009n.getDrawable(1).setAlpha((int) (this.f1002g * 255.0f));
            super.setImageDrawable(this.f1009n);
        }
    }

    public void setRound(float f8) {
        if (Float.isNaN(f8)) {
            this.f1004i = f8;
            float f9 = this.f1003h;
            this.f1003h = -1.0f;
            setRoundPercent(f9);
            return;
        }
        boolean z7 = this.f1004i != f8;
        this.f1004i = f8;
        if (f8 != 0.0f) {
            if (this.f1005j == null) {
                this.f1005j = new Path();
            }
            if (this.f1007l == null) {
                this.f1007l = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f1006k == null) {
                    b bVar = new b();
                    this.f1006k = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.f1007l.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f1005j.reset();
            Path path = this.f1005j;
            RectF rectF = this.f1007l;
            float f10 = this.f1004i;
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
        boolean z7 = this.f1003h != f8;
        this.f1003h = f8;
        if (f8 != 0.0f) {
            if (this.f1005j == null) {
                this.f1005j = new Path();
            }
            if (this.f1007l == null) {
                this.f1007l = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f1006k == null) {
                    a aVar = new a();
                    this.f1006k = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.f1003h) / 2.0f;
            this.f1007l.set(0.0f, 0.0f, width, height);
            this.f1005j.reset();
            this.f1005j.addRoundRect(this.f1007l, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z7 || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f8) {
        ImageFilterView.c cVar = this.f1001f;
        cVar.f1029e = f8;
        cVar.c(this);
    }

    public void setWarmth(float f8) {
        ImageFilterView.c cVar = this.f1001f;
        cVar.f1031g = f8;
        cVar.c(this);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1001f = new ImageFilterView.c();
        this.f1002g = 0.0f;
        this.f1003h = 0.0f;
        this.f1004i = Float.NaN;
        this.f1010o = true;
        c(context, attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1001f = new ImageFilterView.c();
        this.f1002g = 0.0f;
        this.f1003h = 0.0f;
        this.f1004i = Float.NaN;
        this.f1010o = true;
        c(context, attributeSet);
    }
}
