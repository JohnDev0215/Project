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
import b.e.c.e;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterButton extends AppCompatImageButton {
    public float E3;
    public Path F3;
    public ViewOutlineProvider G3;
    public RectF H3;
    public Drawable[] I3;
    public LayerDrawable J3;
    public boolean K3;
    public ImageFilterView.c s;
    public float x;
    public float y;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), (Math.min(r3, r4) * ImageFilterButton.this.y) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.E3);
        }
    }

    public ImageFilterButton(Context context) {
        super(context);
        this.s = new ImageFilterView.c();
        this.x = 0.0f;
        this.y = 0.0f;
        this.E3 = Float.NaN;
        this.K3 = true;
        c(context, null);
    }

    private void setOverlay(boolean z) {
        this.K3 = z;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.r3);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(e.s3);
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.u3) {
                    this.x = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == e.z3) {
                    setWarmth(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == e.y3) {
                    setSaturation(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == e.t3) {
                    setContrast(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == e.w3) {
                    setRound(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == e.x3) {
                    setRoundPercent(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == e.v3) {
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.K3));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.I3 = drawableArr;
                drawableArr[0] = getDrawable();
                Drawable[] drawableArr2 = this.I3;
                drawableArr2[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(drawableArr2);
                this.J3 = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.x * 255.0f));
                super.setImageDrawable(this.J3);
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.E3 == 0.0f || this.F3 == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.F3);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public float getContrast() {
        return this.s.f380f;
    }

    public float getCrossfade() {
        return this.x;
    }

    public float getRound() {
        return this.E3;
    }

    public float getRoundPercent() {
        return this.y;
    }

    public float getSaturation() {
        return this.s.f379e;
    }

    public float getWarmth() {
        return this.s.f381g;
    }

    public void setBrightness(float f2) {
        ImageFilterView.c cVar = this.s;
        cVar.f378d = f2;
        cVar.c(this);
    }

    public void setContrast(float f2) {
        ImageFilterView.c cVar = this.s;
        cVar.f380f = f2;
        cVar.c(this);
    }

    public void setCrossfade(float f2) {
        this.x = f2;
        if (this.I3 != null) {
            if (!this.K3) {
                this.J3.getDrawable(0).setAlpha((int) ((1.0f - this.x) * 255.0f));
            }
            this.J3.getDrawable(1).setAlpha((int) (this.x * 255.0f));
            super.setImageDrawable(this.J3);
        }
    }

    public void setRound(float f2) {
        if (Float.isNaN(f2)) {
            this.E3 = f2;
            float f3 = this.y;
            this.y = -1.0f;
            setRoundPercent(f3);
            return;
        }
        boolean z = this.E3 != f2;
        this.E3 = f2;
        if (f2 != 0.0f) {
            if (this.F3 == null) {
                this.F3 = new Path();
            }
            if (this.H3 == null) {
                this.H3 = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.G3 == null) {
                    b bVar = new b();
                    this.G3 = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.H3.set(0.0f, 0.0f, getWidth(), getHeight());
            this.F3.reset();
            Path path = this.F3;
            RectF rectF = this.H3;
            float f4 = this.E3;
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
        boolean z = this.y != f2;
        this.y = f2;
        if (f2 != 0.0f) {
            if (this.F3 == null) {
                this.F3 = new Path();
            }
            if (this.H3 == null) {
                this.H3 = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.G3 == null) {
                    a aVar = new a();
                    this.G3 = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.y) / 2.0f;
            this.H3.set(0.0f, 0.0f, width, height);
            this.F3.reset();
            this.F3.addRoundRect(this.H3, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f2) {
        ImageFilterView.c cVar = this.s;
        cVar.f379e = f2;
        cVar.c(this);
    }

    public void setWarmth(float f2) {
        ImageFilterView.c cVar = this.s;
        cVar.f381g = f2;
        cVar.c(this);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = new ImageFilterView.c();
        this.x = 0.0f;
        this.y = 0.0f;
        this.E3 = Float.NaN;
        this.K3 = true;
        c(context, attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.s = new ImageFilterView.c();
        this.x = 0.0f;
        this.y = 0.0f;
        this.E3 = Float.NaN;
        this.K3 = true;
        c(context, attributeSet);
    }
}
