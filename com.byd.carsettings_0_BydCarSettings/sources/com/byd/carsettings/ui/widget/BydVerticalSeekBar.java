package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;
import com.byd.carsettings.R;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class BydVerticalSeekBar extends SeekBar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3376a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3377b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3378c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f3379d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SeekBar.OnSeekBarChangeListener f3380e;

    public class a implements SeekBar.OnSeekBarChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<BydVerticalSeekBar> f3381a;

        public a(BydVerticalSeekBar bydVerticalSeekBar) {
            this.f3381a = new WeakReference<>(bydVerticalSeekBar);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            WeakReference<BydVerticalSeekBar> weakReference = this.f3381a;
            if (weakReference == null || weakReference.get() == null || this.f3381a.get().f3380e == null) {
                return;
            }
            this.f3381a.get().f3380e.onProgressChanged(seekBar, i, z);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            WeakReference<BydVerticalSeekBar> weakReference = this.f3381a;
            if (weakReference == null || weakReference.get() == null || this.f3381a.get().f3380e == null) {
                return;
            }
            this.f3381a.get().f3380e.onStartTrackingTouch(seekBar);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            WeakReference<BydVerticalSeekBar> weakReference = this.f3381a;
            if (weakReference == null || weakReference.get() == null || this.f3381a.get().f3380e == null) {
                return;
            }
            this.f3381a.get().f3380e.onStopTrackingTouch(seekBar);
        }
    }

    public BydVerticalSeekBar(Context context) {
        super(context);
        this.f3376a = 0;
        this.f3377b = 0.0f;
        this.f3378c = 0.0f;
        this.f3379d = 0.0f;
        d(context);
    }

    public BitmapDrawable b(Context context, int i, float f2, float f3) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        float f4 = this.f3379d;
        int i2 = (int) (f2 * f4);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeResource, i2, (int) (f3 * f4), true);
        if (i2 != ((int) f2) || !bitmapDecodeResource.equals(bitmapCreateScaledBitmap)) {
            bitmapDecodeResource.recycle();
        }
        return new BitmapDrawable(bitmapCreateScaledBitmap);
    }

    public final void c(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.byd_seekbar);
        this.f3376a = typedArrayObtainStyledAttributes.getResourceId(0, this.f3376a);
        this.f3377b = typedArrayObtainStyledAttributes.getDimension(2, this.f3377b);
        this.f3378c = typedArrayObtainStyledAttributes.getDimension(1, this.f3378c);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void d(Context context) {
        setLayoutDirection(0);
        super.setOnSeekBarChangeListener(new a(this));
        this.f3379d = context.getResources().getDisplayMetrics().density;
        if (this.f3376a != 0) {
            setThumb(b(getContext(), this.f3376a, this.f3378c, this.f3377b));
        }
    }

    public final void e(MotionEvent motionEvent) {
        int max = getMax() - ((int) (((getMax() * motionEvent.getY()) / getHeight()) + 0.5f));
        b.b.m.c.a.a("BydVerticalSeekBar", "---scroll---i: " + max);
        setProgress(max);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        canvas.rotate(-90.0f);
        canvas.translate(-getHeight(), 0.0f);
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i2, i);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i2, i, i4, i3);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.f3380e;
            if (onSeekBarChangeListener != null) {
                onSeekBarChangeListener.onStartTrackingTouch(this);
            }
            e(motionEvent);
        } else if (action == 1) {
            e(motionEvent);
            SeekBar.OnSeekBarChangeListener onSeekBarChangeListener2 = this.f3380e;
            if (onSeekBarChangeListener2 != null) {
                onSeekBarChangeListener2.onStopTrackingTouch(this);
            }
        } else if (action == 2) {
            e(motionEvent);
        }
        return true;
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.f3380e = onSeekBarChangeListener;
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        super.setProgress(i);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }

    public BydVerticalSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3376a = 0;
        this.f3377b = 0.0f;
        this.f3378c = 0.0f;
        this.f3379d = 0.0f;
        c(context, attributeSet);
        d(context);
    }

    public BydVerticalSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3376a = 0;
        this.f3377b = 0.0f;
        this.f3378c = 0.0f;
        this.f3379d = 0.0f;
        c(context, attributeSet);
        d(context);
    }
}
