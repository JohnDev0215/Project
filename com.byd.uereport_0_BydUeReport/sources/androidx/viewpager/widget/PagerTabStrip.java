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
    public final Rect A;
    public int B;
    public boolean C;
    public boolean D;
    public int E;
    public boolean F;
    public float G;
    public float H;
    public int I;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1838t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1839u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1840v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1841w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1842x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1843y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final Paint f1844z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PagerTabStrip.this.f1849f.setCurrentItem(r2.getCurrentItem() - 1);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.f1849f;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void d(int i8, float f8, boolean z7) {
        Rect rect = this.A;
        int height = getHeight();
        int left = this.f1851h.getLeft() - this.f1843y;
        int right = this.f1851h.getRight() + this.f1843y;
        int i9 = height - this.f1839u;
        rect.set(left, i9, right, height);
        super.d(i8, f8, z7);
        this.B = (int) (Math.abs(f8 - 0.5f) * 2.0f * 255.0f);
        rect.union(this.f1851h.getLeft() - this.f1843y, i9, this.f1851h.getRight() + this.f1843y, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.C;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.f1842x);
    }

    public int getTabIndicatorColor() {
        return this.f1838t;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.f1851h.getLeft() - this.f1843y;
        int right = this.f1851h.getRight() + this.f1843y;
        int i8 = height - this.f1839u;
        this.f1844z.setColor((this.B << 24) | (this.f1838t & 16777215));
        float f8 = height;
        canvas.drawRect(left, i8, right, f8, this.f1844z);
        if (this.C) {
            this.f1844z.setColor((-16777216) | (this.f1838t & 16777215));
            canvas.drawRect(getPaddingLeft(), height - this.E, getWidth() - getPaddingRight(), f8, this.f1844z);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.F) {
            return false;
        }
        float x7 = motionEvent.getX();
        float y7 = motionEvent.getY();
        if (action == 0) {
            this.G = x7;
            this.H = y7;
            this.F = false;
        } else if (action != 1) {
            if (action == 2 && (Math.abs(x7 - this.G) > this.I || Math.abs(y7 - this.H) > this.I)) {
                this.F = true;
            }
        } else if (x7 < this.f1851h.getLeft() - this.f1843y) {
            ViewPager viewPager = this.f1849f;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        } else if (x7 > this.f1851h.getRight() + this.f1843y) {
            ViewPager viewPager2 = this.f1849f;
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i8) {
        super.setBackgroundColor(i8);
        if (this.D) {
            return;
        }
        this.C = (i8 & (-16777216)) == 0;
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
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        if (this.D) {
            return;
        }
        this.C = i8 == 0;
    }

    public void setDrawFullUnderline(boolean z7) {
        this.C = z7;
        this.D = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i8, int i9, int i10, int i11) {
        int i12 = this.f1840v;
        if (i11 < i12) {
            i11 = i12;
        }
        super.setPadding(i8, i9, i10, i11);
    }

    public void setTabIndicatorColor(int i8) {
        this.f1838t = i8;
        this.f1844z.setColor(i8);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i8) {
        setTabIndicatorColor(e0.a.b(getContext(), i8));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i8) {
        int i9 = this.f1841w;
        if (i8 < i9) {
            i8 = i9;
        }
        super.setTextSpacing(i8);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.f1844z = paint;
        this.A = new Rect();
        this.B = 255;
        this.C = false;
        this.D = false;
        int i8 = this.f1862s;
        this.f1838t = i8;
        paint.setColor(i8);
        float f8 = context.getResources().getDisplayMetrics().density;
        this.f1839u = (int) ((3.0f * f8) + 0.5f);
        this.f1840v = (int) ((6.0f * f8) + 0.5f);
        this.f1841w = (int) (64.0f * f8);
        this.f1843y = (int) ((16.0f * f8) + 0.5f);
        this.E = (int) ((1.0f * f8) + 0.5f);
        this.f1842x = (int) ((f8 * 32.0f) + 0.5f);
        this.I = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.f1850g.setFocusable(true);
        this.f1850g.setOnClickListener(new a());
        this.f1852i.setFocusable(true);
        this.f1852i.setOnClickListener(new b());
        if (getBackground() == null) {
            this.C = true;
        }
    }
}
