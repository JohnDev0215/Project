package com.byd.carsettings.ui.view.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class BydTextSeekBar extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SeekBar f3320a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f3321b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f3322c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3323d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3324e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3325f;
    public int g;
    public int h;
    public int i;
    public Drawable j;
    public Drawable k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public boolean t;
    public d u;

    public class a implements SeekBar.OnSeekBarChangeListener {
        public a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (BydTextSeekBar.this.u != null) {
                BydTextSeekBar.this.u.a(i);
            }
            BydTextSeekBar.this.i();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BydTextSeekBar.this.i();
        }
    }

    public class c implements View.OnTouchListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3328a;

        public c(boolean z) {
            this.f3328a = z;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return this.f3328a;
        }
    }

    public interface d {
        void a(int i);
    }

    public BydTextSeekBar(Context context) {
        this(context, null);
    }

    public final int c(Context context, float f2, int i) {
        return (int) TypedValue.applyDimension(i, f2, context.getResources().getDisplayMetrics());
    }

    public final void d(Context context) {
        this.n = c(context, 40.0f, 1);
        this.o = c(context, 100.0f, 1);
        this.p = c(context, 24.0f, 1);
        this.q = c(context, 24.0f, 1);
        this.r = c(context, 20.0f, 2);
    }

    public final void e() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f3320a.getLayoutParams();
        layoutParams.leftMargin = this.f3323d;
        layoutParams.rightMargin = this.f3324e;
        layoutParams.height = this.f3325f;
        this.f3320a.setLayoutParams(layoutParams);
    }

    public final void f() {
        this.f3320a.setOnSeekBarChangeListener(new a());
        this.f3320a.post(new b());
    }

    public final void g() {
        this.f3321b.setTextSize(this.g);
        this.f3321b.setTextColor(this.m);
        this.f3320a.setMax(this.h);
        this.f3320a.setMin(this.i);
        this.f3320a.setThumbOffset(this.l);
        Drawable drawable = this.j;
        if (drawable != null) {
            this.f3320a.setProgressDrawable(drawable);
        }
        Drawable drawable2 = this.k;
        if (drawable2 != null) {
            this.f3320a.setThumb(drawable2);
        }
        setSeekbarUnTouch(this.t);
    }

    public final void h(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.byd_num_text_seekbar, (ViewGroup) this, true);
        this.f3322c = viewInflate;
        this.f3320a = (SeekBar) viewInflate.findViewById(R.id.byd_seekbar);
        this.f3321b = (TextView) this.f3322c.findViewById(R.id.byd_text);
    }

    public final void i() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f3321b.getLayoutParams();
        this.f3321b.setText(String.valueOf(this.f3320a.getProgress()));
        Rect bounds = this.f3320a.getThumb().getBounds();
        layoutParams.leftMargin = ((bounds.left + (bounds.width() / 2)) - (this.f3321b.getWidth() / 2)) + this.f3320a.getLeft() + this.l;
        this.f3321b.setLayoutParams(layoutParams);
    }

    public void setProgress(int i) {
        this.f3320a.setProgress(i);
    }

    public void setProgressDrawable(Drawable drawable) {
        if (drawable != null) {
            this.f3320a.setProgressDrawable(drawable);
        }
    }

    public void setSeekbarUnTouch(boolean z) {
        this.f3320a.setOnTouchListener(new c(z));
    }

    public void setTextChangListener(d dVar) {
        this.u = dVar;
    }

    public void setTextColor(int i) {
        this.f3321b.setTextColor(i);
    }

    public void setTextSize(float f2) {
        this.f3321b.setTextSize(f2);
    }

    public void setThumb(Drawable drawable) {
        if (drawable != null) {
            this.f3320a.setThumb(drawable);
        }
    }

    public BydTextSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setTextSize(float f2, int i) {
        this.f3321b.setTextSize(i, f2);
    }

    public BydTextSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.s = Color.parseColor("#999999");
        d(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BydTextSeekBar);
        this.f3323d = (int) typedArrayObtainStyledAttributes.getDimension(8, this.p);
        this.f3324e = (int) typedArrayObtainStyledAttributes.getDimension(7, this.q);
        this.f3325f = (int) typedArrayObtainStyledAttributes.getDimension(10, this.n);
        this.g = (int) typedArrayObtainStyledAttributes.getDimension(0, this.r);
        this.h = typedArrayObtainStyledAttributes.getInt(2, 0);
        this.i = typedArrayObtainStyledAttributes.getInt(6, 0);
        this.j = typedArrayObtainStyledAttributes.getDrawable(3);
        this.k = typedArrayObtainStyledAttributes.getDrawable(4);
        this.l = typedArrayObtainStyledAttributes.getInt(5, 0);
        this.m = typedArrayObtainStyledAttributes.getInt(1, this.s);
        this.t = typedArrayObtainStyledAttributes.getBoolean(9, false);
        h(context);
        f();
        g();
        e();
        typedArrayObtainStyledAttributes.recycle();
    }
}
