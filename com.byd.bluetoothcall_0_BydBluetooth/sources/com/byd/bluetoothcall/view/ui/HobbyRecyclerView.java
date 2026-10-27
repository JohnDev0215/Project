package com.byd.bluetoothcall.view.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class HobbyRecyclerView extends RecyclerView {
    private transient RectF F0;
    private transient Paint G0;
    private transient int H0;
    private transient float I0;
    private transient float J0;
    private transient float K0;
    private transient int L0;
    private transient float M0;
    private transient float N0;
    private transient float O0;
    transient float P0;
    private transient int Q0;
    private transient boolean R0;
    transient float S0;
    transient float T0;
    private transient RecyclerView.t U0;

    class a extends RecyclerView.t {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            super.a(recyclerView, i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            super.b(recyclerView, i, i2);
            HobbyRecyclerView hobbyRecyclerView = HobbyRecyclerView.this;
            HobbyRecyclerView.A1(hobbyRecyclerView, i2 * hobbyRecyclerView.P0);
        }
    }

    public HobbyRecyclerView(Context context) {
        super(context);
        this.H0 = 2;
        this.L0 = 10;
        this.M0 = 0.0f;
        this.R0 = false;
        this.S0 = 0.0f;
        this.T0 = 0.0f;
        this.U0 = new a();
    }

    static /* synthetic */ float A1(HobbyRecyclerView hobbyRecyclerView, float f) {
        float f2 = hobbyRecyclerView.M0 + f;
        hobbyRecyclerView.M0 = f2;
        return f2;
    }

    private void B1(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        RectF rectF = this.F0;
        int i = this.Q0;
        float f = i + this.K0;
        float f2 = this.M0;
        rectF.set(f, f2, i + this.J0, this.I0 + f2);
        if (this.R0) {
            this.G0.setColor(Color.parseColor("#2386BF"));
        } else {
            this.G0.setColor(Color.parseColor("#2EB3FF"));
        }
        canvas.drawRect(this.F0, this.G0);
        Log.i("tag", "yScrollOffset ------- " + this.M0);
        Log.i("tag", "scrollBarHeight ------- " + this.I0);
        Log.i("tag", "yScrollOffset ------- " + this.M0);
    }

    private void C1(Context context, AttributeSet attributeSet) {
        this.F0 = new RectF();
        Paint paint = new Paint(1);
        this.G0 = paint;
        paint.setStyle(Paint.Style.FILL);
        l(this.U0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        B1(canvas);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.O0 = getMeasuredHeight();
        float height = getChildAt(2).getHeight() * getAdapter().c();
        this.N0 = height;
        this.P0 = 0.0f;
        if (height != 0.0f) {
            this.P0 = this.O0 / height;
        }
        this.I0 = this.P0 * this.O0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.H0 == 1) {
            super.onMeasure(i, i2);
            return;
        }
        int size = View.MeasureSpec.getSize(i);
        float f = size / this.L0;
        this.J0 = f;
        float f2 = f / 10.0f;
        this.K0 = f2;
        this.Q0 = (int) (size - f);
        this.J0 = f - (f2 * 2.0f);
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            measureChild(getChildAt(i3), this.Q0, i2);
            getChildAt(i3).getLayoutParams().width = this.Q0;
        }
        setMeasuredDimension(size, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.S0 = motionEvent.getX();
            float y = motionEvent.getY();
            this.T0 = y;
            float f = this.S0;
            RectF rectF = this.F0;
            if (f >= rectF.left && f <= rectF.right && y >= rectF.top && y <= rectF.bottom) {
                this.R0 = true;
                invalidate();
            }
        } else if (action == 1) {
            this.R0 = false;
            invalidate();
        } else if (action == 2) {
            float f2 = this.S0;
            RectF rectF2 = this.F0;
            if (f2 >= rectF2.left && f2 <= rectF2.right) {
                float f3 = this.T0;
                if (f3 >= rectF2.top && f3 <= rectF2.bottom) {
                    scrollBy(0, (int) (((motionEvent.getY() - this.T0) / this.O0) * this.N0));
                    this.T0 = motionEvent.getY();
                }
            }
        }
        float f4 = this.S0;
        if (f4 >= this.Q0 && f4 <= getMeasuredWidth()) {
            float f5 = this.T0;
            if (f5 >= 0.0f && f5 <= getMeasuredHeight()) {
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public HobbyRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.H0 = 2;
        this.L0 = 10;
        this.M0 = 0.0f;
        this.R0 = false;
        this.S0 = 0.0f;
        this.T0 = 0.0f;
        this.U0 = new a();
        C1(context, attributeSet);
    }

    public HobbyRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.H0 = 2;
        this.L0 = 10;
        this.M0 = 0.0f;
        this.R0 = false;
        this.S0 = 0.0f;
        this.T0 = 0.0f;
        this.U0 = new a();
        C1(context, attributeSet);
    }
}
