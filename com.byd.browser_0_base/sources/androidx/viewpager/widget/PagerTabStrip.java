package androidx.viewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;
import defpackage.bz;

/* JADX INFO: loaded from: classes.dex */
public class PagerTabStrip extends PagerTitleStrip {
    public final Paint A;
    public int B;
    public boolean C;
    public boolean D;
    public final int E;
    public boolean F;
    public float G;
    public float H;
    public final int I;
    public int u;
    public final int v;
    public final int w;
    public final int x;
    public final int y;
    public final int z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            PagerTabStrip.this.e.setCurrentItem(r0.getCurrentItem() - 1);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.e;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public final void c(float f, int i, boolean z) {
        super.c(f, i, z);
        this.B = (int) (Math.abs(f - 0.5f) * 2.0f * 255.0f);
        invalidate();
    }

    public boolean getDrawFullUnderline() {
        return this.C;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.y);
    }

    public int getTabIndicatorColor() {
        return this.u;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        TextView textView = this.g;
        int left = textView.getLeft();
        int i = this.z;
        int i2 = left - i;
        int right = textView.getRight() + i;
        int i3 = height - this.v;
        Paint paint = this.A;
        paint.setColor((this.B << 24) | (this.u & 16777215));
        float f = right;
        float f2 = height;
        canvas.drawRect(i2, i3, f, f2, paint);
        if (this.C) {
            paint.setColor((this.u & 16777215) | (-16777216));
            canvas.drawRect(getPaddingLeft(), height - this.E, getWidth() - getPaddingRight(), f2, paint);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.F) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.G = x;
            this.H = y;
            this.F = false;
        } else if (action == 1) {
            int left = this.g.getLeft();
            int i = this.z;
            if (x < left - i) {
                ViewPager viewPager = this.e;
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            } else if (x > r5.getRight() + i) {
                ViewPager viewPager2 = this.e;
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        } else if (action == 2) {
            float fAbs = Math.abs(x - this.G);
            int i2 = this.I;
            if (fAbs > i2 || Math.abs(y - this.H) > i2) {
                this.F = true;
            }
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        if (this.D) {
            return;
        }
        this.C = (i & (-16777216)) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.D) {
            return;
        }
        this.C = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.D) {
            return;
        }
        this.C = i == 0;
    }

    public void setDrawFullUnderline(boolean z) {
        this.C = z;
        this.D = true;
        invalidate();
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        int i5 = this.w;
        if (i4 < i5) {
            i4 = i5;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setTabIndicatorColor(int i) {
        this.u = i;
        this.A.setColor(i);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i) {
        Context context = getContext();
        Object obj = bz.a;
        setTabIndicatorColor(context.getColor(i));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i) {
        int i2 = this.x;
        if (i < i2) {
            i = i2;
        }
        super.setTextSpacing(i);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.A = paint;
        this.B = 255;
        this.C = false;
        this.D = false;
        int i = this.r;
        this.u = i;
        paint.setColor(i);
        float f = context.getResources().getDisplayMetrics().density;
        this.v = (int) ((3.0f * f) + 0.5f);
        this.w = (int) ((6.0f * f) + 0.5f);
        this.x = (int) (64.0f * f);
        this.z = (int) ((16.0f * f) + 0.5f);
        this.E = (int) ((1.0f * f) + 0.5f);
        this.y = (int) ((f * 32.0f) + 0.5f);
        this.I = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.f.setFocusable(true);
        this.f.setOnClickListener(new a());
        this.h.setFocusable(true);
        this.h.setOnClickListener(new b());
        if (getBackground() == null) {
            this.C = true;
        }
    }
}
