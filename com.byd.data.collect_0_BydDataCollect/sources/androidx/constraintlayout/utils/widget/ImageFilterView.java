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
import androidx.constraintlayout.widget.f;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f969d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f970e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f971f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f972g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f973h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Path f974i;
    ViewOutlineProvider j;
    RectF k;
    Drawable[] l;
    LayerDrawable m;

    class a extends ViewOutlineProvider {
        a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), (Math.min(r3, r4) * ImageFilterView.this.f972g) / 2.0f);
        }
    }

    class b extends ViewOutlineProvider {
        b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), ImageFilterView.this.f973h);
        }
    }

    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float[] f977a = new float[20];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ColorMatrix f978b = new ColorMatrix();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ColorMatrix f979c = new ColorMatrix();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f980d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f981e = 1.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        float f982f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        float f983g = 1.0f;

        c() {
        }

        private void a(float f2) {
            float[] fArr = this.f977a;
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

        private void b(float f2) {
            float f3 = 1.0f - f2;
            float f4 = 0.2999f * f3;
            float f5 = 0.587f * f3;
            float f6 = f3 * 0.114f;
            float[] fArr = this.f977a;
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

        private void d(float f2) {
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
            float[] fArr = this.f977a;
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

        void c(ImageView imageView) {
            boolean z;
            this.f978b.reset();
            float f2 = this.f981e;
            boolean z2 = true;
            if (f2 != 1.0f) {
                b(f2);
                this.f978b.set(this.f977a);
                z = true;
            } else {
                z = false;
            }
            float f3 = this.f982f;
            if (f3 != 1.0f) {
                this.f979c.setScale(f3, f3, f3, 1.0f);
                this.f978b.postConcat(this.f979c);
                z = true;
            }
            float f4 = this.f983g;
            if (f4 != 1.0f) {
                d(f4);
                this.f979c.set(this.f977a);
                this.f978b.postConcat(this.f979c);
                z = true;
            }
            float f5 = this.f980d;
            if (f5 != 1.0f) {
                a(f5);
                this.f979c.set(this.f977a);
                this.f978b.postConcat(this.f979c);
            } else {
                z2 = z;
            }
            if (z2) {
                imageView.setColorFilter(new ColorMatrixColorFilter(this.f978b));
            } else {
                imageView.clearColorFilter();
            }
        }
    }

    public ImageFilterView(Context context) {
        super(context);
        this.f969d = new c();
        this.f970e = true;
        this.f971f = 0.0f;
        this.f972g = 0.0f;
        this.f973h = Float.NaN;
        e(context, null);
    }

    private void e(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, f.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(f.ImageFilterView_altSrc);
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.ImageFilterView_crossfade) {
                    this.f971f = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == f.ImageFilterView_warmth) {
                    setWarmth(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == f.ImageFilterView_saturation) {
                    setSaturation(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == f.ImageFilterView_contrast) {
                    setContrast(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == f.ImageFilterView_round) {
                    setRound(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == f.ImageFilterView_roundPercent) {
                    setRoundPercent(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == f.ImageFilterView_overlay) {
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.f970e));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.l = drawableArr;
                drawableArr[0] = getDrawable();
                Drawable[] drawableArr2 = this.l;
                drawableArr2[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(drawableArr2);
                this.m = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.f971f * 255.0f));
                super.setImageDrawable(this.m);
            }
        }
    }

    private void setOverlay(boolean z) {
        this.f970e = z;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.f972g == 0.0f || this.f974i == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.f974i);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public float getBrightness() {
        return this.f969d.f980d;
    }

    public float getContrast() {
        return this.f969d.f982f;
    }

    public float getCrossfade() {
        return this.f971f;
    }

    public float getRound() {
        return this.f973h;
    }

    public float getRoundPercent() {
        return this.f972g;
    }

    public float getSaturation() {
        return this.f969d.f981e;
    }

    public float getWarmth() {
        return this.f969d.f983g;
    }

    public void setBrightness(float f2) {
        c cVar = this.f969d;
        cVar.f980d = f2;
        cVar.c(this);
    }

    public void setContrast(float f2) {
        c cVar = this.f969d;
        cVar.f982f = f2;
        cVar.c(this);
    }

    public void setCrossfade(float f2) {
        this.f971f = f2;
        if (this.l != null) {
            if (!this.f970e) {
                this.m.getDrawable(0).setAlpha((int) ((1.0f - this.f971f) * 255.0f));
            }
            this.m.getDrawable(1).setAlpha((int) (this.f971f * 255.0f));
            super.setImageDrawable(this.m);
        }
    }

    public void setRound(float f2) {
        if (Float.isNaN(f2)) {
            this.f973h = f2;
            float f3 = this.f972g;
            this.f972g = -1.0f;
            setRoundPercent(f3);
            return;
        }
        boolean z = this.f973h != f2;
        this.f973h = f2;
        if (f2 != 0.0f) {
            if (this.f974i == null) {
                this.f974i = new Path();
            }
            if (this.k == null) {
                this.k = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.j == null) {
                    b bVar = new b();
                    this.j = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.k.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f974i.reset();
            Path path = this.f974i;
            RectF rectF = this.k;
            float f4 = this.f973h;
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
        boolean z = this.f972g != f2;
        this.f972g = f2;
        if (f2 != 0.0f) {
            if (this.f974i == null) {
                this.f974i = new Path();
            }
            if (this.k == null) {
                this.k = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.j == null) {
                    a aVar = new a();
                    this.j = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.f972g) / 2.0f;
            this.k.set(0.0f, 0.0f, width, height);
            this.f974i.reset();
            this.f974i.addRoundRect(this.k, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f2) {
        c cVar = this.f969d;
        cVar.f981e = f2;
        cVar.c(this);
    }

    public void setWarmth(float f2) {
        c cVar = this.f969d;
        cVar.f983g = f2;
        cVar.c(this);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f969d = new c();
        this.f970e = true;
        this.f971f = 0.0f;
        this.f972g = 0.0f;
        this.f973h = Float.NaN;
        e(context, attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f969d = new c();
        this.f970e = true;
        this.f971f = 0.0f;
        this.f972g = 0.0f;
        this.f973h = Float.NaN;
        e(context, attributeSet);
    }
}
