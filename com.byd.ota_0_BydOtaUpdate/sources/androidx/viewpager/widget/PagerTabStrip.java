package androidx.viewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class PagerTabStrip extends PagerTitleStrip {
    public int P3;
    public int Q3;
    public int R3;
    public int S3;
    public int T3;
    public int U3;
    public final Paint V3;
    public final Rect W3;
    public int X3;
    public boolean Y3;
    public boolean Z3;
    public int a4;
    public boolean b4;
    public float c4;
    public float d4;
    public int e4;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PagerTabStrip.this.s.setCurrentItem(r2.getCurrentItem() - 1);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.s;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void d(int i2, float f2, boolean z) {
        Rect rect = this.W3;
        int height = getHeight();
        int left = this.y.getLeft() - this.U3;
        int right = this.y.getRight() + this.U3;
        int i3 = height - this.Q3;
        rect.set(left, i3, right, height);
        super.d(i2, f2, z);
        this.X3 = (int) (Math.abs(f2 - 0.5f) * 2.0f * 255.0f);
        rect.union(this.y.getLeft() - this.U3, i3, this.y.getRight() + this.U3, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.Y3;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.T3);
    }

    public int getTabIndicatorColor() {
        return this.P3;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.y.getLeft() - this.U3;
        int right = this.y.getRight() + this.U3;
        int i2 = height - this.Q3;
        this.V3.setColor((this.X3 << 24) | (this.P3 & 16777215));
        float f2 = height;
        canvas.drawRect(left, i2, right, f2, this.V3);
        if (this.Y3) {
            this.V3.setColor((-16777216) | (this.P3 & 16777215));
            canvas.drawRect(getPaddingLeft(), height - this.a4, getWidth() - getPaddingRight(), f2, this.V3);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.b4) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.c4 = x;
            this.d4 = y;
            this.b4 = false;
        } else if (action != 1) {
            if (action == 2 && (Math.abs(x - this.c4) > this.e4 || Math.abs(y - this.d4) > this.e4)) {
                this.b4 = true;
            }
        } else if (x < this.y.getLeft() - this.U3) {
            ViewPager viewPager = this.s;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        } else if (x > this.y.getRight() + this.U3) {
            ViewPager viewPager2 = this.s;
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        super.setBackgroundColor(i2);
        if (this.Z3) {
            return;
        }
        this.Y3 = (i2 & (-16777216)) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.Z3) {
            return;
        }
        this.Y3 = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        if (this.Z3) {
            return;
        }
        this.Y3 = i2 == 0;
    }

    public void setDrawFullUnderline(boolean z) {
        this.Y3 = z;
        this.Z3 = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        int i6 = this.R3;
        if (i5 < i6) {
            i5 = i6;
        }
        super.setPadding(i2, i3, i4, i5);
    }

    public void setTabIndicatorColor(int i2) {
        this.P3 = i2;
        this.V3.setColor(i2);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i2) {
        setTabIndicatorColor(b.f.f.a.a(getContext(), i2));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i2) {
        int i3 = this.S3;
        if (i2 < i3) {
            i2 = i3;
        }
        super.setTextSpacing(i2);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.V3 = paint;
        this.W3 = new Rect();
        this.X3 = 255;
        this.Y3 = false;
        this.Z3 = false;
        int i2 = this.O3;
        this.P3 = i2;
        paint.setColor(i2);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.Q3 = (int) ((3.0f * f2) + 0.5f);
        this.R3 = (int) ((6.0f * f2) + 0.5f);
        this.S3 = (int) (64.0f * f2);
        this.U3 = (int) ((16.0f * f2) + 0.5f);
        this.a4 = (int) ((1.0f * f2) + 0.5f);
        this.T3 = (int) ((f2 * 32.0f) + 0.5f);
        this.e4 = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.x.setFocusable(true);
        this.x.setOnClickListener(new a());
        this.E3.setFocusable(true);
        this.E3.setOnClickListener(new b());
        if (getBackground() == null) {
            this.Y3 = true;
        }
    }
}
