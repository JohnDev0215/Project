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
import b.e.c.e;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {
    public float E3;
    public float F3;
    public Path G3;
    public ViewOutlineProvider H3;
    public RectF I3;
    public Drawable[] J3;
    public LayerDrawable K3;
    public c s;
    public boolean x;
    public float y;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), (Math.min(r3, r4) * ImageFilterView.this.E3) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), ImageFilterView.this.F3);
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float[] f375a = new float[20];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ColorMatrix f376b = new ColorMatrix();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorMatrix f377c = new ColorMatrix();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f378d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f379e = 1.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f380f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f381g = 1.0f;

        public final void a(float f2) {
            float[] fArr = this.f375a;
            fArr[0] = f2;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = f2;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = f2;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        public final void b(float f2) {
            float f3 = 1.0f - f2;
            float f4 = 0.2999f * f3;
            float f5 = 0.587f * f3;
            float f6 = f3 * 0.114f;
            float[] fArr = this.f375a;
            fArr[0] = f4 + f2;
            fArr[1] = f5;
            fArr[2] = f6;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = f4;
            fArr[6] = f5 + f2;
            fArr[7] = f6;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = f4;
            fArr[11] = f5;
            fArr[12] = f6 + f2;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        public void c(ImageView imageView) {
            boolean z;
            this.f376b.reset();
            float f2 = this.f379e;
            boolean z2 = true;
            if (f2 != 1.0f) {
                b(f2);
                this.f376b.set(this.f375a);
                z = true;
            } else {
                z = false;
            }
            float f3 = this.f380f;
            if (f3 != 1.0f) {
                this.f377c.setScale(f3, f3, f3, 1.0f);
                this.f376b.postConcat(this.f377c);
                z = true;
            }
            float f4 = this.f381g;
            if (f4 != 1.0f) {
                d(f4);
                this.f377c.set(this.f375a);
                this.f376b.postConcat(this.f377c);
                z = true;
            }
            float f5 = this.f378d;
            if (f5 != 1.0f) {
                a(f5);
                this.f377c.set(this.f375a);
                this.f376b.postConcat(this.f377c);
            } else {
                z2 = z;
            }
            if (z2) {
                imageView.setColorFilter(new ColorMatrixColorFilter(this.f376b));
            } else {
                imageView.clearColorFilter();
            }
        }

        public final void d(float f2) {
            float fLog;
            float fPow;
            if (f2 <= 0.0f) {
                f2 = 0.01f;
            }
            float f3 = (5000.0f / f2) / 100.0f;
            if (f3 > 66.0f) {
                double d2 = f3 - 60.0f;
                fPow = ((float) Math.pow(d2, -0.13320475816726685d)) * 329.69873f;
                fLog = ((float) Math.pow(d2, 0.07551484555006027d)) * 288.12216f;
            } else {
                fLog = (((float) Math.log(f3)) * 99.4708f) - 161.11957f;
                fPow = 255.0f;
            }
            float fLog2 = f3 < 66.0f ? f3 > 19.0f ? (((float) Math.log(f3 - 10.0f)) * 138.51773f) - 305.0448f : 0.0f : 255.0f;
            float fMin = Math.min(255.0f, Math.max(fPow, 0.0f));
            float fMin2 = Math.min(255.0f, Math.max(fLog, 0.0f));
            float fMin3 = Math.min(255.0f, Math.max(fLog2, 0.0f));
            float fLog3 = (((float) Math.log(50.0f)) * 99.4708f) - 161.11957f;
            float fLog4 = (((float) Math.log(40.0f)) * 138.51773f) - 305.0448f;
            float fMin4 = Math.min(255.0f, Math.max(255.0f, 0.0f));
            float fMin5 = Math.min(255.0f, Math.max(fLog3, 0.0f));
            float fMin6 = fMin3 / Math.min(255.0f, Math.max(fLog4, 0.0f));
            float[] fArr = this.f375a;
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
        this.s = new c();
        this.x = true;
        this.y = 0.0f;
        this.E3 = 0.0f;
        this.F3 = Float.NaN;
        e(context, null);
    }

    private void setOverlay(boolean z) {
        this.x = z;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.E3 == 0.0f || this.G3 == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.G3);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public final void e(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.r3);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(e.s3);
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.u3) {
                    this.y = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
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
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.x));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.J3 = drawableArr;
                drawableArr[0] = getDrawable();
                Drawable[] drawableArr2 = this.J3;
                drawableArr2[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(drawableArr2);
                this.K3 = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.y * 255.0f));
                super.setImageDrawable(this.K3);
            }
        }
    }

    public float getBrightness() {
        return this.s.f378d;
    }

    public float getContrast() {
        return this.s.f380f;
    }

    public float getCrossfade() {
        return this.y;
    }

    public float getRound() {
        return this.F3;
    }

    public float getRoundPercent() {
        return this.E3;
    }

    public float getSaturation() {
        return this.s.f379e;
    }

    public float getWarmth() {
        return this.s.f381g;
    }

    public void setBrightness(float f2) {
        c cVar = this.s;
        cVar.f378d = f2;
        cVar.c(this);
    }

    public void setContrast(float f2) {
        c cVar = this.s;
        cVar.f380f = f2;
        cVar.c(this);
    }

    public void setCrossfade(float f2) {
        this.y = f2;
        if (this.J3 != null) {
            if (!this.x) {
                this.K3.getDrawable(0).setAlpha((int) ((1.0f - this.y) * 255.0f));
            }
            this.K3.getDrawable(1).setAlpha((int) (this.y * 255.0f));
            super.setImageDrawable(this.K3);
        }
    }

    public void setRound(float f2) {
        if (Float.isNaN(f2)) {
            this.F3 = f2;
            float f3 = this.E3;
            this.E3 = -1.0f;
            setRoundPercent(f3);
            return;
        }
        boolean z = this.F3 != f2;
        this.F3 = f2;
        if (f2 != 0.0f) {
            if (this.G3 == null) {
                this.G3 = new Path();
            }
            if (this.I3 == null) {
                this.I3 = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.H3 == null) {
                    b bVar = new b();
                    this.H3 = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.I3.set(0.0f, 0.0f, getWidth(), getHeight());
            this.G3.reset();
            Path path = this.G3;
            RectF rectF = this.I3;
            float f4 = this.F3;
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
        boolean z = this.E3 != f2;
        this.E3 = f2;
        if (f2 != 0.0f) {
            if (this.G3 == null) {
                this.G3 = new Path();
            }
            if (this.I3 == null) {
                this.I3 = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.H3 == null) {
                    a aVar = new a();
                    this.H3 = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.E3) / 2.0f;
            this.I3.set(0.0f, 0.0f, width, height);
            this.G3.reset();
            this.G3.addRoundRect(this.I3, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f2) {
        c cVar = this.s;
        cVar.f379e = f2;
        cVar.c(this);
    }

    public void setWarmth(float f2) {
        c cVar = this.s;
        cVar.f381g = f2;
        cVar.c(this);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = new c();
        this.x = true;
        this.y = 0.0f;
        this.E3 = 0.0f;
        this.F3 = Float.NaN;
        e(context, attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.s = new c();
        this.x = true;
        this.y = 0.0f;
        this.E3 = 0.0f;
        this.F3 = Float.NaN;
        e(context, attributeSet);
    }
}
