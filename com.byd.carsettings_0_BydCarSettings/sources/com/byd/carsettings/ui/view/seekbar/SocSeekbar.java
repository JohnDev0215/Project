package com.byd.carsettings.ui.view.seekbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import b.b.a.h.b;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes2.dex */
public class SocSeekbar extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3330a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3331b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3332c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3333d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f3334e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f3335f;
    public int g;
    public int h;
    public int i;
    public float j;
    public float k;
    public float l;
    public int m;
    public int n;
    public Bitmap o;
    public boolean p;
    public float q;
    public float r;
    public Paint s;
    public LinearGradient t;
    public LinearGradient u;
    public LinearGradient v;
    public Paint w;
    public DecimalFormat x;
    public a y;

    public interface a {
        void a(float f2);

        void b(float f2);

        void c();

        void d(float f2);
    }

    public SocSeekbar(Context context) {
        super(context);
        this.m = 0;
        this.n = 0;
        this.p = false;
        this.q = 0.0f;
        this.r = 8.0f;
        a();
    }

    public final void a() {
        this.x = new DecimalFormat("#.#");
        Paint paint = new Paint();
        this.s = paint;
        paint.setAntiAlias(true);
        this.s.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.w = paint2;
        paint2.setAntiAlias(true);
        this.w.setStyle(Paint.Style.FILL);
        this.w.setColor(ContextCompat.getColor(getContext(), R.color.sublevelseekbar_circle));
        this.t = new LinearGradient(0.0f, this.i, 0.0f, this.f3332c, new int[]{ContextCompat.getColor(getContext(), b.a(getContext(), R.attr.thumb_socseekbar_unselect_color)), ContextCompat.getColor(getContext(), b.a(getContext(), R.attr.thumb_socseekbar_unselect_color))}, (float[]) null, Shader.TileMode.CLAMP);
        this.u = new LinearGradient(0.0f, this.i, 0.0f, this.f3332c, new int[]{ContextCompat.getColor(getContext(), b.a(getContext(), R.attr.thumb_socseekbar_select_color)), ContextCompat.getColor(getContext(), b.a(getContext(), R.attr.thumb_socseekbar_select_color))}, (float[]) null, Shader.TileMode.CLAMP);
        this.v = new LinearGradient(0.0f, this.i, 0.0f, this.f3332c, new int[]{ContextCompat.getColor(getContext(), b.a(getContext(), R.attr.thumb_socseekbar_bg_color)), ContextCompat.getColor(getContext(), b.a(getContext(), R.attr.thumb_socseekbar_bg_color))}, (float[]) null, Shader.TileMode.CLAMP);
        this.g = getResources().getDimensionPixelSize(R.dimen.dp_35);
        this.h = getResources().getDimensionPixelSize(R.dimen.dp_35);
        this.i = getResources().getDimensionPixelSize(R.dimen.dp_17);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_6);
        this.f3332c = dimensionPixelSize;
        this.f3334e = dimensionPixelSize / 2.0f;
        this.f3335f = getResources().getDimensionPixelSize(R.dimen.dp_10);
        b();
    }

    public final void b() {
        BydLogHelper.logE("[initProgress]----initRecycleBitmap");
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.seekbar_thumb_background_lock);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        this.o = bitmapCreateBitmap;
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
    }

    public final void c(MotionEvent motionEvent) {
        a aVar;
        try {
            float x = motionEvent.getX();
            float f2 = this.k;
            float f3 = this.l;
            float f4 = f2 + f3;
            int i = this.h;
            float f5 = this.j;
            int i2 = this.f3333d;
            float f6 = 0.0f;
            if (x >= i + (i2 * f5)) {
                f6 = x > ((float) i) + (((f2 + f5) + f3) * ((float) i2)) ? 1.0f : (((x - i) - (f5 * i2)) * 1.0f) / (i2 * f4);
            } else if (motionEvent.getAction() == 1 && (aVar = this.y) != null && this.j != 0.0f) {
                aVar.c();
            }
            int i3 = this.n;
            float fRound = (Math.round((f6 * f4) * this.n) * 1.0f) / i3;
            this.k = fRound;
            float f7 = f4 - fRound;
            this.l = f7;
            setProgress(0, this.j, fRound, f7, i3);
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(this.p);
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            BydLogHelper.logE("[initProgress]----onDetachedFromWindow  mRecycleBitmap.recycle");
            this.o.recycle();
            this.o = null;
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            int i = this.m;
            if (i == 1) {
                this.s.setShader(this.v);
                int i2 = this.h;
                float f2 = this.f3334e;
                canvas.drawRoundRect(i2 - f2, this.i, i2 + this.f3333d + f2, r1 + this.f3332c, f2, f2, this.s);
                if (this.o == null) {
                    b();
                }
                canvas.drawBitmap(this.o, this.h - (r0.getWidth() / 2.0f), (this.f3331b / 2.0f) - (this.o.getHeight() / 2.0f), (Paint) null);
                return;
            }
            if (i == 2) {
                this.s.setShader(this.j + this.k == 0.0f ? this.v : this.t);
                int i3 = this.h;
                float f3 = this.f3334e;
                canvas.drawRoundRect(i3 - f3, this.i, i3 + ((this.j + this.k) * this.f3333d) + f3, r1 + this.f3332c, f3, f3, this.s);
                this.s.setShader(this.l == 0.0f ? this.t : this.v);
                int i4 = this.h;
                float f4 = this.j + this.k;
                int i5 = this.f3333d;
                float f5 = this.f3334e;
                canvas.drawRoundRect((i4 + (f4 * i5)) - f5, this.i, i4 + i5 + f5, r1 + this.f3332c, f5, f5, this.s);
                if (this.o == null) {
                    b();
                }
                canvas.drawBitmap(this.o, (this.h + (this.f3333d * (this.j + this.k))) - (r0.getWidth() / 2.0f), (this.f3331b / 2.0f) - (this.o.getHeight() / 2.0f), (Paint) null);
                return;
            }
            this.s.setShader(this.j == 0.0f ? this.u : this.t);
            int i6 = this.h;
            float f6 = this.f3334e;
            canvas.drawRoundRect(i6 - f6, this.i, i6 + (this.j * this.f3333d) + f6, r1 + this.f3332c, f6, f6, this.s);
            this.s.setShader(this.l == 0.0f ? this.u : this.v);
            int i7 = this.h;
            float f7 = this.j + this.k;
            int i8 = this.f3333d;
            float f8 = this.f3334e;
            canvas.drawRoundRect((i7 + (f7 * i8)) - f8, this.i, i7 + i8 + f8, r1 + this.f3332c, f8, f8, this.s);
            this.s.setShader(this.u);
            int i9 = this.h;
            float f9 = this.j;
            int i10 = this.f3333d;
            canvas.drawRect(i9 + (i10 * f9), this.i, i9 + ((f9 + this.k) * i10), r1 + this.f3332c, this.s);
            if (this.o == null) {
                b();
            }
            canvas.drawBitmap(this.o, (this.h + (this.f3333d * (this.j + this.k))) - (r0.getWidth() / 2.0f), (this.f3331b / 2.0f) - (this.o.getHeight() / 2.0f), (Paint) null);
        } catch (Exception e2) {
            BydLogHelper.logE("[initProgress]----" + e2.getMessage());
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f3330a = View.MeasureSpec.getSize(i);
        this.f3331b = View.MeasureSpec.getSize(i2);
        this.f3333d = (this.f3330a - this.g) - this.h;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.m = 0;
            this.p = false;
            this.q = motionEvent.getX();
            c(motionEvent);
            a aVar = this.y;
            if (aVar != null) {
                aVar.b(this.j + this.k);
            }
            invalidate();
        } else if (action == 1) {
            if (this.p) {
                c(motionEvent);
                setPressed(false);
            } else {
                c(motionEvent);
            }
            a aVar2 = this.y;
            if (aVar2 != null) {
                aVar2.a(this.j + this.k);
            }
            invalidate();
        } else if (action == 2) {
            if (this.p) {
                c(motionEvent);
                invalidate();
            } else if (Math.abs(motionEvent.getX() - this.q) > this.r) {
                this.p = true;
            }
            a aVar3 = this.y;
            if (aVar3 != null) {
                aVar3.d(this.j + this.k);
            }
        } else if (action == 3) {
            if (this.p) {
                setPressed(false);
            }
            a aVar4 = this.y;
            if (aVar4 != null) {
                aVar4.a(this.j + this.k);
            }
            invalidate();
        }
        return true;
    }

    public void setOnSeekBarChangeListener(a aVar) {
        this.y = aVar;
    }

    public void setProgress(int i, float f2, float f3, float f4, int i2) {
        if (f2 + f3 + f4 != 1.0f) {
            return;
        }
        this.m = i;
        this.j = f2;
        this.k = f3;
        this.l = f4;
        this.n = i2;
    }

    public SocSeekbar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = 0;
        this.n = 0;
        this.p = false;
        this.q = 0.0f;
        this.r = 8.0f;
        a();
    }

    public SocSeekbar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = 0;
        this.n = 0;
        this.p = false;
        this.q = 0.0f;
        this.r = 8.0f;
        a();
    }
}
