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
    public boolean A;
    public boolean B;
    public int C;
    public boolean D;
    public float E;
    public float F;
    public int G;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1631r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1632s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1633t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1634u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1635v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1636w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Paint f1637x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Rect f1638y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f1639z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PagerTabStrip.this.f1644d.setCurrentItem(r2.getCurrentItem() - 1);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.f1644d;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void d(int i3, float f3, boolean z3) {
        Rect rect = this.f1638y;
        int height = getHeight();
        int left = this.f1646f.getLeft() - this.f1636w;
        int right = this.f1646f.getRight() + this.f1636w;
        int i4 = height - this.f1632s;
        rect.set(left, i4, right, height);
        super.d(i3, f3, z3);
        this.f1639z = (int) (Math.abs(f3 - 0.5f) * 2.0f * 255.0f);
        rect.union(this.f1646f.getLeft() - this.f1636w, i4, this.f1646f.getRight() + this.f1636w, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.A;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.f1635v);
    }

    public int getTabIndicatorColor() {
        return this.f1631r;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.f1646f.getLeft() - this.f1636w;
        int right = this.f1646f.getRight() + this.f1636w;
        int i3 = height - this.f1632s;
        this.f1637x.setColor((this.f1639z << 24) | (this.f1631r & 16777215));
        float f3 = height;
        canvas.drawRect(left, i3, right, f3, this.f1637x);
        if (this.A) {
            this.f1637x.setColor((-16777216) | (this.f1631r & 16777215));
            canvas.drawRect(getPaddingLeft(), height - this.C, getWidth() - getPaddingRight(), f3, this.f1637x);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.D) {
            return false;
        }
        float x3 = motionEvent.getX();
        float y3 = motionEvent.getY();
        if (action == 0) {
            this.E = x3;
            this.F = y3;
            this.D = false;
        } else if (action != 1) {
            if (action == 2 && (Math.abs(x3 - this.E) > this.G || Math.abs(y3 - this.F) > this.G)) {
                this.D = true;
            }
        } else if (x3 < this.f1646f.getLeft() - this.f1636w) {
            ViewPager viewPager = this.f1644d;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        } else if (x3 > this.f1646f.getRight() + this.f1636w) {
            ViewPager viewPager2 = this.f1644d;
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i3) {
        super.setBackgroundColor(i3);
        if (this.B) {
            return;
        }
        this.A = (i3 & (-16777216)) == 0;
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
    public void setBackgroundResource(int i3) {
        super.setBackgroundResource(i3);
        if (this.B) {
            return;
        }
        this.A = i3 == 0;
    }

    public void setDrawFullUnderline(boolean z3) {
        this.A = z3;
        this.B = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i3, int i4, int i5, int i6) {
        int i7 = this.f1633t;
        if (i6 < i7) {
            i6 = i7;
        }
        super.setPadding(i3, i4, i5, i6);
    }

    public void setTabIndicatorColor(int i3) {
        this.f1631r = i3;
        this.f1637x.setColor(i3);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i3) {
        setTabIndicatorColor(b0.a.b(getContext(), i3));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i3) {
        int i4 = this.f1634u;
        if (i3 < i4) {
            i3 = i4;
        }
        super.setTextSpacing(i3);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.f1637x = paint;
        this.f1638y = new Rect();
        this.f1639z = 255;
        this.A = false;
        this.B = false;
        int i3 = this.f1657q;
        this.f1631r = i3;
        paint.setColor(i3);
        float f3 = context.getResources().getDisplayMetrics().density;
        this.f1632s = (int) ((3.0f * f3) + 0.5f);
        this.f1633t = (int) ((6.0f * f3) + 0.5f);
        this.f1634u = (int) (64.0f * f3);
        this.f1636w = (int) ((16.0f * f3) + 0.5f);
        this.C = (int) ((1.0f * f3) + 0.5f);
        this.f1635v = (int) ((f3 * 32.0f) + 0.5f);
        this.G = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.f1645e.setFocusable(true);
        this.f1645e.setOnClickListener(new a());
        this.f1647g.setFocusable(true);
        this.f1647g.setOnClickListener(new b());
        if (getBackground() == null) {
            this.A = true;
        }
    }
}
