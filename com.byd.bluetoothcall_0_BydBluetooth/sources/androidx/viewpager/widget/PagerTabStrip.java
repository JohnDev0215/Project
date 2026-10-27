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
    private boolean A;
    private boolean B;
    private int C;
    private boolean D;
    private float E;
    private float F;
    private int G;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private final Paint x;
    private final Rect y;
    private int z;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PagerTabStrip.this.f1894d.setCurrentItem(r2.getCurrentItem() - 1);
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.f1894d;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    void d(int i, float f, boolean z) {
        Rect rect = this.y;
        int height = getHeight();
        int left = this.f.getLeft() - this.w;
        int right = this.f.getRight() + this.w;
        int i2 = height - this.s;
        rect.set(left, i2, right, height);
        super.d(i, f, z);
        this.z = (int) (Math.abs(f - 0.5f) * 2.0f * 255.0f);
        rect.union(this.f.getLeft() - this.w, i2, this.f.getRight() + this.w, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.A;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    int getMinHeight() {
        return Math.max(super.getMinHeight(), this.v);
    }

    public int getTabIndicatorColor() {
        return this.r;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.f.getLeft() - this.w;
        int right = this.f.getRight() + this.w;
        int i = height - this.s;
        this.x.setColor((this.z << 24) | (this.r & 16777215));
        float f = height;
        canvas.drawRect(left, i, right, f, this.x);
        if (this.A) {
            this.x.setColor((-16777216) | (this.r & 16777215));
            canvas.drawRect(getPaddingLeft(), height - this.C, getWidth() - getPaddingRight(), f, this.x);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.D) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.E = x;
            this.F = y;
            this.D = false;
        } else if (action != 1) {
            if (action == 2 && (Math.abs(x - this.E) > this.G || Math.abs(y - this.F) > this.G)) {
                this.D = true;
            }
        } else if (x < this.f.getLeft() - this.w) {
            ViewPager viewPager = this.f1894d;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        } else if (x > this.f.getRight() + this.w) {
            ViewPager viewPager2 = this.f1894d;
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        if (this.B) {
            return;
        }
        this.A = (i & (-16777216)) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.B) {
            return;
        }
        this.A = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.B) {
            return;
        }
        this.A = i == 0;
    }

    public void setDrawFullUnderline(boolean z) {
        this.A = z;
        this.B = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        int i5 = this.t;
        if (i4 < i5) {
            i4 = i5;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setTabIndicatorColor(int i) {
        this.r = i;
        this.x.setColor(i);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i) {
        setTabIndicatorColor(androidx.core.content.a.b(getContext(), i));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i) {
        int i2 = this.u;
        if (i < i2) {
            i = i2;
        }
        super.setTextSpacing(i);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.x = paint;
        this.y = new Rect();
        this.z = 255;
        this.A = false;
        this.B = false;
        int i = this.q;
        this.r = i;
        paint.setColor(i);
        float f = context.getResources().getDisplayMetrics().density;
        this.s = (int) ((3.0f * f) + 0.5f);
        this.t = (int) ((6.0f * f) + 0.5f);
        this.u = (int) (64.0f * f);
        this.w = (int) ((16.0f * f) + 0.5f);
        this.C = (int) ((1.0f * f) + 0.5f);
        this.v = (int) ((f * 32.0f) + 0.5f);
        this.G = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.f1895e.setFocusable(true);
        this.f1895e.setOnClickListener(new a());
        this.g.setFocusable(true);
        this.g.setOnClickListener(new b());
        if (getBackground() == null) {
            this.A = true;
        }
    }
}
