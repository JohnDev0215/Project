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
import androidx.constraintlayout.widget.e;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterButton extends AppCompatImageButton {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ImageFilterView.c f1043d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f1044e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f1045f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f1046g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Path f1047h;
    ViewOutlineProvider i;
    RectF j;
    Drawable[] k;
    LayerDrawable l;
    private boolean m;

    class a extends ViewOutlineProvider {
        a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), (Math.min(r3, r4) * ImageFilterButton.this.f1045f) / 2.0f);
        }
    }

    class b extends ViewOutlineProvider {
        b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.f1046g);
        }
    }

    public ImageFilterButton(Context context) {
        super(context);
        this.f1043d = new ImageFilterView.c();
        this.f1044e = 0.0f;
        this.f1045f = 0.0f;
        this.f1046g = Float.NaN;
        this.m = true;
        c(context, null);
    }

    private void c(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.r3);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(e.s3);
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.u3) {
                    this.f1044e = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
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
                layerDrawable.getDrawable(1).setAlpha((int) (this.f1044e * 255.0f));
                super.setImageDrawable(this.l);
            }
        }
    }

    private void setOverlay(boolean z) {
        this.m = z;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.f1046g == 0.0f || this.f1047h == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.f1047h);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public float getContrast() {
        return this.f1043d.f1062f;
    }

    public float getCrossfade() {
        return this.f1044e;
    }

    public float getRound() {
        return this.f1046g;
    }

    public float getRoundPercent() {
        return this.f1045f;
    }

    public float getSaturation() {
        return this.f1043d.f1061e;
    }

    public float getWarmth() {
        return this.f1043d.f1063g;
    }

    public void setBrightness(float f2) {
        ImageFilterView.c cVar = this.f1043d;
        cVar.f1060d = f2;
        cVar.c(this);
    }

    public void setContrast(float f2) {
        ImageFilterView.c cVar = this.f1043d;
        cVar.f1062f = f2;
        cVar.c(this);
    }

    public void setCrossfade(float f2) {
        this.f1044e = f2;
        if (this.k != null) {
            if (!this.m) {
                this.l.getDrawable(0).setAlpha((int) ((1.0f - this.f1044e) * 255.0f));
            }
            this.l.getDrawable(1).setAlpha((int) (this.f1044e * 255.0f));
            super.setImageDrawable(this.l);
        }
    }

    public void setRound(float f2) {
        if (Float.isNaN(f2)) {
            this.f1046g = f2;
            float f3 = this.f1045f;
            this.f1045f = -1.0f;
            setRoundPercent(f3);
            return;
        }
        boolean z = this.f1046g != f2;
        this.f1046g = f2;
        if (f2 != 0.0f) {
            if (this.f1047h == null) {
                this.f1047h = new Path();
            }
            if (this.j == null) {
                this.j = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.i == null) {
                    b bVar = new b();
                    this.i = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.j.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f1047h.reset();
            Path path = this.f1047h;
            RectF rectF = this.j;
            float f4 = this.f1046g;
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
        boolean z = this.f1045f != f2;
        this.f1045f = f2;
        if (f2 != 0.0f) {
            if (this.f1047h == null) {
                this.f1047h = new Path();
            }
            if (this.j == null) {
                this.j = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.i == null) {
                    a aVar = new a();
                    this.i = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.f1045f) / 2.0f;
            this.j.set(0.0f, 0.0f, width, height);
            this.f1047h.reset();
            this.f1047h.addRoundRect(this.j, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f2) {
        ImageFilterView.c cVar = this.f1043d;
        cVar.f1061e = f2;
        cVar.c(this);
    }

    public void setWarmth(float f2) {
        ImageFilterView.c cVar = this.f1043d;
        cVar.f1063g = f2;
        cVar.c(this);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1043d = new ImageFilterView.c();
        this.f1044e = 0.0f;
        this.f1045f = 0.0f;
        this.f1046g = Float.NaN;
        this.m = true;
        c(context, attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1043d = new ImageFilterView.c();
        this.f1044e = 0.0f;
        this.f1045f = 0.0f;
        this.f1046g = Float.NaN;
        this.m = true;
        c(context, attributeSet);
    }
}
