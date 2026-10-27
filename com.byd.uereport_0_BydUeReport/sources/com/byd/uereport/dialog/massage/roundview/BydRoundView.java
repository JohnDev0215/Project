package com.byd.uereport.dialog.massage.roundview;

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
import com.byd.uereport.R;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/* JADX INFO: loaded from: classes2.dex */
public class BydRoundView extends View {
    public int A;
    public int B;
    public int C;
    public float D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public BlockingQueue<String> L;
    public Handler M;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3336d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3337e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f3338f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3339g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Paint f3340h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f3341i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Paint f3342j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3343k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3344l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3345m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3346n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3347o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Paint f3348p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f3349q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f3350r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3351s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3352t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f3353u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f3354v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f3355w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3356x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Paint f3357y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f3358z;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            BydRoundView.this.C = -1;
            try {
                Thread.sleep(100L);
                BydRoundView.this.invalidate();
            } catch (InterruptedException e8) {
                e8.printStackTrace();
            }
        }
    }

    public BydRoundView(Context context) {
        this(context, null);
    }

    public final void b(Canvas canvas) {
        int i8 = 0;
        while (true) {
            int[] iArr = this.f3341i;
            if (i8 >= iArr.length) {
                return;
            }
            if (i8 == this.C) {
                f(canvas, iArr[i8]);
            }
            i8++;
        }
    }

    public final void c(Canvas canvas) {
        int i8 = this.J;
        int width = (getWidth() - (((i8 * 2) * this.f3350r) + (i8 > 1 ? (i8 - 1) * this.f3351s : 0))) / 2;
        for (int i9 = 1; i9 <= this.J; i9++) {
            this.f3348p.setColor(this.B);
            if (i9 <= this.E) {
                this.f3348p.setColor(this.A);
            } else {
                this.f3348p.setColor(this.B);
            }
            int i10 = this.f3350r;
            float f8 = width + i10 + (((i10 * 2) + this.f3351s) * (i9 - 1));
            int height = getHeight() - this.f3349q;
            canvas.drawCircle(f8, height - r4, this.f3350r, this.f3348p);
        }
    }

    public final void d(Canvas canvas) {
        int i8 = this.F;
        int width = (this.f3343k + (getWidth() / this.f3346n)) - 8;
        int height = getHeight();
        int i9 = this.f3345m;
        g(canvas, i8, width, (height - i9) / 2, this.f3344l, i9);
        int i10 = this.G;
        int width2 = (((getWidth() - (getWidth() / this.f3346n)) - this.f3343k) - this.f3344l) + 5;
        int height2 = getHeight();
        int i11 = this.f3345m;
        g(canvas, i10, width2, (height2 - i11) / 2, this.f3344l, i11);
    }

    public final void drawBackground(Canvas canvas) {
        f(canvas, this.f3339g);
    }

    public final void e(Canvas canvas) {
        String string = getContext().getString(R.string.vehiclesettings_carseat_massage_level);
        Rect rect = new Rect();
        this.f3348p.setTextSize(this.f3354v);
        this.f3348p.setColor(this.f3356x);
        this.f3348p.getTextBounds(string, 0, string.length(), rect);
        canvas.drawText(string, ((this.f3343k + this.f3344l) + (getWidth() / this.f3346n)) - 8, (getHeight() + rect.height()) / 2, this.f3348p);
        canvas.drawText(string, ((((getWidth() - (getWidth() / this.f3346n)) - this.f3343k) - this.f3344l) - rect.width()) + 5, (getHeight() + rect.height()) / 2, this.f3348p);
    }

    public final void f(Canvas canvas, int i8) {
        g(canvas, i8, 0, 0, getWidth(), getHeight());
    }

    public final void g(Canvas canvas, int i8, int i9, int i10, int i11, int i12) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.f3338f.getResources(), i8), i11, i12, true);
        canvas.drawBitmap(bitmapCreateScaledBitmap, i9, i10, this.f3340h);
        bitmapCreateScaledBitmap.recycle();
    }

    public final void h(Canvas canvas) {
        Rect rect = new Rect();
        this.f3348p.setTextSize(this.f3355w);
        this.f3348p.setColor(this.f3358z);
        Paint paint = this.f3348p;
        String str = this.f3353u;
        paint.getTextBounds(str, 0, str.length(), rect);
        canvas.drawText(this.f3353u, (getWidth() - rect.width()) / 2, ((getHeight() - this.f3349q) - (this.f3350r * 2)) - this.f3352t, this.f3348p);
    }

    public final void i(Canvas canvas) {
        g(canvas, this.H, (getWidth() - this.f3344l) / 2, (getHeight() / this.f3346n) + this.f3347o, this.f3344l, this.f3345m);
        g(canvas, this.I, (getWidth() - this.f3344l) / 2, ((getHeight() - this.f3345m) - (getHeight() / this.f3346n)) - this.f3347o, this.f3344l, this.f3345m);
    }

    public final void j(Canvas canvas) {
        String string = getContext().getString(R.string.vehiclesettings_carseat_massage_mode);
        Rect rect = new Rect();
        this.f3348p.setTextSize(this.f3354v);
        this.f3348p.setColor(this.f3356x);
        this.f3348p.getTextBounds(string, 0, string.length(), rect);
        canvas.drawText(string, (getWidth() - rect.width()) / 2, (getHeight() / this.f3346n) + this.f3347o + this.f3345m + rect.height(), this.f3348p);
        canvas.drawText(string, (getWidth() - rect.width()) / 2, ((getHeight() - this.f3345m) - (getHeight() / this.f3346n)) - this.f3347o, this.f3348p);
    }

    public final void k(Context context, AttributeSet attributeSet, int i8) {
        this.f3338f = context;
        l();
        this.f3353u = getContext().getString(R.string.vehiclesettings_carseat_massage_pluse);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
        this.f3336d = iApplyDimension;
        this.f3337e = iApplyDimension;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BydRoundView);
        this.f3339g = typedArrayObtainStyledAttributes.getResourceId(0, R.drawable.img_bg_circle);
        TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(typedArrayObtainStyledAttributes.getResourceId(3, R.array.arcarray));
        this.f3341i = new int[typedArrayObtainTypedArray.length()];
        for (int i9 = 0; i9 < typedArrayObtainTypedArray.length(); i9++) {
            this.f3341i[i9] = typedArrayObtainTypedArray.getResourceId(i9, 0);
        }
        typedArrayObtainTypedArray.recycle();
        this.F = typedArrayObtainStyledAttributes.getResourceId(11, R.drawable.icon_public_add);
        this.G = typedArrayObtainStyledAttributes.getResourceId(12, R.drawable.icon_public_subtaract);
        this.H = typedArrayObtainStyledAttributes.getResourceId(13, R.drawable.icon_public_uparrow);
        this.I = typedArrayObtainStyledAttributes.getResourceId(8, R.drawable.icon_public_downarrow);
        this.f3343k = (int) typedArrayObtainStyledAttributes.getDimension(9, 0.0f);
        this.f3347o = (int) typedArrayObtainStyledAttributes.getDimension(14, 0.0f);
        this.f3344l = (int) typedArrayObtainStyledAttributes.getDimension(15, 0.0f);
        this.f3345m = (int) typedArrayObtainStyledAttributes.getDimension(10, 0.0f);
        this.f3349q = (int) typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        this.f3350r = (int) typedArrayObtainStyledAttributes.getDimension(5, 0.0f);
        this.f3351s = (int) typedArrayObtainStyledAttributes.getDimension(6, 0.0f);
        this.f3352t = (int) typedArrayObtainStyledAttributes.getDimension(7, 0.0f);
        this.f3354v = (int) typedArrayObtainStyledAttributes.getDimension(18, 0.0f);
        this.f3355w = (int) typedArrayObtainStyledAttributes.getDimension(2, 0.0f);
        this.f3356x = typedArrayObtainStyledAttributes.getColor(17, -1);
        this.f3358z = typedArrayObtainStyledAttributes.getColor(1, -1);
        this.A = typedArrayObtainStyledAttributes.getColor(19, -1);
        this.B = typedArrayObtainStyledAttributes.getColor(20, -1);
        this.J = typedArrayObtainStyledAttributes.getInt(16, 3);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void l() {
        Paint paint = new Paint();
        this.f3340h = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.f3342j = paint2;
        paint2.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.f3348p = paint3;
        paint3.setAntiAlias(true);
        this.f3348p.setTextSize(getContext().getResources().getDimension(R.dimen.sp_14));
        this.f3348p.setColor(this.f3356x);
        this.f3357y = new Paint();
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
    public void onMeasure(int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        int i10 = this.f3336d;
        if (size <= i10) {
            size = i10;
        }
        int mode2 = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i8);
        int i11 = this.f3337e;
        if (size2 <= i11) {
            size2 = i11;
        }
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(size, mode), View.MeasureSpec.makeMeasureSpec(size2, mode2));
    }

    public void setLevel(int i8) {
        this.E = i8;
        invalidate();
    }

    public void setLevelCount(int i8) {
        this.J = i8;
        invalidate();
    }

    public void setMode(int i8) {
        this.K = i8;
        setModeText(i8);
        invalidate();
    }

    public void setModeText(int i8) {
        if (i8 == 2) {
            this.f3353u = getContext().getString(R.string.vehiclesettings_carseat_massage_wave);
            return;
        }
        if (i8 == 3) {
            this.f3353u = getContext().getString(R.string.vehiclesettings_carseat_massage_pluse);
            return;
        }
        if (i8 == 4) {
            this.f3353u = getContext().getString(R.string.vehiclesettings_carseat_massage_stretch);
        } else if (i8 == 5) {
            this.f3353u = getContext().getString(R.string.vehiclesettings_seat_massage_mode_slack);
        } else {
            if (i8 != 6) {
                return;
            }
            this.f3353u = getContext().getString(R.string.vehiclesettings_seat_massage_mode_relieve);
        }
    }

    public BydRoundView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydRoundView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3346n = 20;
        this.C = -2;
        this.D = 45.0f;
        this.L = new SynchronousQueue();
        this.M = new a(Looper.myLooper());
        k(context, attributeSet, i8);
    }
}
