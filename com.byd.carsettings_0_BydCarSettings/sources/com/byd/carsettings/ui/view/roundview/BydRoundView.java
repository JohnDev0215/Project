package com.byd.carsettings.ui.view.roundview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.carsettings.R;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/* JADX INFO: loaded from: classes2.dex */
public class BydRoundView extends View {
    public float A;
    public int B;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public BlockingQueue<String> J;
    public Handler K;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3313a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3314b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f3315c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3316d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f3317e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f3318f;
    public Paint g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public Paint m;
    public int n;
    public int o;
    public int p;
    public int q;
    public String r;
    public int s;
    public int t;
    public int u;
    public Paint v;
    public int w;
    public int x;
    public int y;
    public int z;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            BydRoundView.this.z = -1;
            try {
                Thread.sleep(100L);
                BydRoundView.this.invalidate();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    public BydRoundView(Context context) {
        this(context, null);
    }

    public final void b(Canvas canvas) {
        int i = 0;
        while (true) {
            int[] iArr = this.f3318f;
            if (i >= iArr.length) {
                return;
            }
            if (i == this.z) {
                f(canvas, iArr[i]);
            }
            i++;
        }
    }

    public final void c(Canvas canvas) {
        int i = this.H;
        int width = (getWidth() - (((i * 2) * this.o) + (i > 1 ? (i - 1) * this.p : 0))) / 2;
        for (int i2 = 0; i2 < this.H; i2++) {
            this.m.setColor(this.y);
            if (i2 <= this.B) {
                this.m.setColor(this.x);
            }
            int i3 = this.o;
            float f2 = width + i3 + (((i3 * 2) + this.p) * i2);
            int height = getHeight() - this.n;
            canvas.drawCircle(f2, height - r4, this.o, this.m);
        }
    }

    public final void d(Canvas canvas) {
        int i = this.D;
        int width = this.h + (getWidth() / this.k);
        int height = getHeight();
        int i2 = this.j;
        g(canvas, i, width, (height - i2) / 2, this.i, i2);
        int i3 = this.E;
        int width2 = ((getWidth() - (getWidth() / this.k)) - this.h) - this.i;
        int height2 = getHeight();
        int i4 = this.j;
        g(canvas, i3, width2, (height2 - i4) / 2, this.i, i4);
    }

    public final void drawBackground(Canvas canvas) {
        f(canvas, this.f3316d);
    }

    public final void e(Canvas canvas) {
        Rect rect = new Rect();
        this.m.setTextSize(this.s);
        this.m.setColor(this.u);
        this.m.getTextBounds("强度", 0, 2, rect);
        canvas.drawText("强度", this.h + this.i + (getWidth() / this.k), (getHeight() + rect.height()) / 2, this.m);
        canvas.drawText("强度", (((getWidth() - (getWidth() / this.k)) - this.h) - this.i) - rect.width(), (getHeight() + rect.height()) / 2, this.m);
    }

    public final void f(Canvas canvas, int i) {
        g(canvas, i, 0, 0, getWidth(), getHeight());
    }

    public final void g(Canvas canvas, int i, int i2, int i3, int i4, int i5) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.f3315c.getResources(), i), i4, i5, true);
        canvas.drawBitmap(bitmapCreateScaledBitmap, i2, i3, this.f3317e);
        bitmapCreateScaledBitmap.recycle();
    }

    public final void h(Canvas canvas) {
        Rect rect = new Rect();
        this.m.setTextSize(this.t);
        this.m.setColor(this.w);
        Paint paint = this.m;
        String str = this.r;
        paint.getTextBounds(str, 0, str.length(), rect);
        canvas.drawText(this.r, (getWidth() - rect.width()) / 2, ((getHeight() - this.n) - (this.o * 2)) - this.q, this.m);
    }

    public final void i(Canvas canvas) {
        g(canvas, this.F, (getWidth() - this.i) / 2, (getHeight() / this.k) + this.l, this.i, this.j);
        g(canvas, this.G, (getWidth() - this.i) / 2, ((getHeight() - this.j) - (getHeight() / this.k)) - this.l, this.i, this.j);
    }

    public final void j(Canvas canvas) {
        Rect rect = new Rect();
        this.m.setTextSize(this.s);
        this.m.setColor(this.u);
        this.m.getTextBounds("模式", 0, 2, rect);
        canvas.drawText("模式", (getWidth() - rect.width()) / 2, (getHeight() / this.k) + this.l + this.j + rect.height(), this.m);
        canvas.drawText("模式", (getWidth() - rect.width()) / 2, ((getHeight() - this.j) - (getHeight() / this.k)) - this.l, this.m);
    }

    public final void k(Context context, @Nullable AttributeSet attributeSet, int i) {
        this.f3315c = context;
        l();
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
        this.f3313a = iApplyDimension;
        this.f3314b = iApplyDimension;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BydRoundView);
        this.f3316d = typedArrayObtainStyledAttributes.getResourceId(0, R.drawable.img_bg_circle);
        TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(typedArrayObtainStyledAttributes.getResourceId(3, R.array.arcarray));
        this.f3318f = new int[typedArrayObtainTypedArray.length()];
        for (int i2 = 0; i2 < typedArrayObtainTypedArray.length(); i2++) {
            this.f3318f[i2] = typedArrayObtainTypedArray.getResourceId(i2, 0);
        }
        typedArrayObtainTypedArray.recycle();
        this.D = typedArrayObtainStyledAttributes.getResourceId(11, R.drawable.icon_public_add);
        this.E = typedArrayObtainStyledAttributes.getResourceId(12, R.drawable.icon_public_subtaract);
        this.F = typedArrayObtainStyledAttributes.getResourceId(13, R.drawable.icon_public_uparrow);
        this.G = typedArrayObtainStyledAttributes.getResourceId(8, R.drawable.icon_public_downarrow);
        this.h = (int) typedArrayObtainStyledAttributes.getDimension(9, 0.0f);
        this.l = (int) typedArrayObtainStyledAttributes.getDimension(14, 0.0f);
        this.i = (int) typedArrayObtainStyledAttributes.getDimension(15, 0.0f);
        this.j = (int) typedArrayObtainStyledAttributes.getDimension(10, 0.0f);
        this.n = (int) typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        this.o = (int) typedArrayObtainStyledAttributes.getDimension(5, 0.0f);
        this.p = (int) typedArrayObtainStyledAttributes.getDimension(6, 0.0f);
        this.q = (int) typedArrayObtainStyledAttributes.getDimension(7, 0.0f);
        this.s = (int) typedArrayObtainStyledAttributes.getDimension(18, 0.0f);
        this.t = (int) typedArrayObtainStyledAttributes.getDimension(2, 0.0f);
        this.u = typedArrayObtainStyledAttributes.getColor(17, -1);
        this.w = typedArrayObtainStyledAttributes.getColor(1, -1);
        this.x = typedArrayObtainStyledAttributes.getColor(19, -1);
        this.y = typedArrayObtainStyledAttributes.getColor(20, -1);
        this.H = typedArrayObtainStyledAttributes.getInt(16, 3);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void l() {
        Paint paint = new Paint();
        this.f3317e = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.g = paint2;
        paint2.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.m = paint3;
        paint3.setColor(this.u);
        this.v = new Paint();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        drawBackground(canvas);
        b(canvas);
        d(canvas);
        i(canvas);
        c(canvas);
        h(canvas);
        e(canvas);
        j(canvas);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int i3 = this.f3313a;
        if (size <= i3) {
            size = i3;
        }
        int mode2 = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i);
        int i4 = this.f3314b;
        if (size2 <= i4) {
            size2 = i4;
        }
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(size, mode), View.MeasureSpec.makeMeasureSpec(size2, mode2));
    }

    public void setLevel(int i) {
        this.B = i;
        invalidate();
    }

    public void setLevelCount(int i) {
        this.H = i;
        invalidate();
    }

    public void setMode(int i) {
        this.I = i;
        invalidate();
    }

    public void setModeAndLevel(int i, int i2) {
        this.I = i;
        this.B = i2;
        invalidate();
    }

    public void setModeText(int i) {
        if (i == 2) {
            this.r = "波浪";
            return;
        }
        if (i == 3) {
            this.r = "脉冲";
            return;
        }
        if (i == 4) {
            this.r = "伸展";
        } else if (i == 5) {
            this.r = "腰部";
        } else {
            if (i != 6) {
                return;
            }
            this.r = "肩膀";
        }
    }

    public BydRoundView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydRoundView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = 20;
        this.r = "脉冲";
        this.z = -2;
        this.A = 45.0f;
        this.J = new SynchronousQueue();
        this.K = new a(Looper.myLooper());
        k(context, attributeSet, i);
    }
}
