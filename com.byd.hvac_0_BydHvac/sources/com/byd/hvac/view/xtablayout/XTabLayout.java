package com.byd.hvac.view.xtablayout;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.util.Pools;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import b.a.a.i.o.d;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class XTabLayout extends HorizontalScrollView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pools.Pool<g> f1099a = new Pools.SynchronizedPool(16);
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public d F;
    public List<d> G;
    public b.a.a.i.o.d H;
    public ViewPager I;
    public PagerAdapter J;
    public DataSetObserver K;
    public TabLayoutOnPageChangeListener L;
    public final Pools.Pool<h> M;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1100b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1101c;
    public final ArrayList<g> d;
    public g e;
    public final f f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public ColorStateList l;
    public float m;
    public boolean n;
    public float o;
    public boolean p;
    public float q;
    public final int r;
    public final int s;
    public int t;
    public final int u;
    public final int v;
    public int w;
    public final int x;
    public int y;
    public int z;

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<XTabLayout> f1102a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1103b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1104c;

        public TabLayoutOnPageChangeListener(XTabLayout xTabLayout) {
            this.f1102a = new WeakReference<>(xTabLayout);
        }

        public final void a() {
            this.f1104c = 0;
            this.f1103b = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.f1103b = this.f1104c;
            this.f1104c = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            XTabLayout xTabLayout = this.f1102a.get();
            if (xTabLayout != null) {
                int i3 = this.f1104c;
                xTabLayout.Z(i, f, i3 != 2 || this.f1103b == 1, (i3 == 2 && this.f1103b == 0) ? false : true);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            XTabLayout xTabLayout = this.f1102a.get();
            if (xTabLayout == null || xTabLayout.getSelectedTabPosition() == i) {
                return;
            }
            int i2 = this.f1104c;
            xTabLayout.X(xTabLayout.R(i), i2 == 0 || (i2 == 2 && this.f1103b == 0));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (XTabLayout.this.B > 0) {
                LinearLayout linearLayout = (LinearLayout) XTabLayout.this.getChildAt(0);
                linearLayout.setShowDividers(2);
                b.a.a.i.o.b bVar = new b.a.a.i.o.b(XTabLayout.this.getContext());
                bVar.c(XTabLayout.this.B, XTabLayout.this.C);
                bVar.b(XTabLayout.this.D);
                bVar.d(XTabLayout.this.E);
                linearLayout.setDividerDrawable(bVar);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f1106a;

        public b(h hVar) {
            this.f1106a = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            int width = this.f1106a.getWidth();
            String strD = this.f1106a.d();
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            Paint paint = new Paint();
            paint.setTextSize(XTabLayout.this.o);
            Rect rect = new Rect();
            paint.getTextBounds(strD, 0, strD.length(), rect);
            if (width - rect.width() < XTabLayout.this.Q(20)) {
                int iWidth = rect.width() + XTabLayout.this.Q(20);
                ViewGroup.LayoutParams layoutParams = this.f1106a.getLayoutParams();
                layoutParams.width = iWidth;
                this.f1106a.setLayoutParams(layoutParams);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class c implements d.e {
        public c() {
        }

        @Override // b.a.a.i.o.d.e
        public void a(b.a.a.i.o.d dVar) {
            XTabLayout.this.scrollTo(dVar.c(), 0);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface d {
        void a(g gVar);

        void b(g gVar);

        void c(g gVar);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class e extends DataSetObserver {
        public e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            XTabLayout.this.T();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            XTabLayout.this.T();
        }

        public /* synthetic */ e(XTabLayout xTabLayout, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class f extends LinearLayout {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1110a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1111b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1112c;
        public int d;
        public final Paint e;
        public int f;
        public float g;
        public int h;
        public int i;
        public b.a.a.i.o.d j;

        /* JADX INFO: compiled from: caradapter-bug */
        public class a implements d.e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f1113a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ int f1114b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ int f1115c;
            public final /* synthetic */ int d;

            public a(int i, int i2, int i3, int i4) {
                this.f1113a = i;
                this.f1114b = i2;
                this.f1115c = i3;
                this.d = i4;
            }

            @Override // b.a.a.i.o.d.e
            public void a(b.a.a.i.o.d dVar) {
                float fB = dVar.b();
                f.this.g(b.a.a.i.o.a.a(this.f1113a, this.f1114b, fB), b.a.a.i.o.a.a(this.f1115c, this.d, fB));
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        public class b extends d.C0007d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f1116a;

            public b(int i) {
                this.f1116a = i;
            }

            @Override // b.a.a.i.o.d.c
            public void a(b.a.a.i.o.d dVar) {
                f.this.f = this.f1116a;
                f.this.g = 0.0f;
            }
        }

        public f(Context context) {
            super(context);
            this.f = -1;
            this.h = -1;
            this.i = -1;
            setWillNotDraw(false);
            this.e = new Paint();
        }

        public void d(int i, int i2) {
            int i3;
            int i4;
            b.a.a.i.o.d dVar = this.j;
            if (dVar != null && dVar.e()) {
                this.j.a();
            }
            boolean z = ViewCompat.getLayoutDirection(this) == 1;
            View childAt = getChildAt(i);
            if (childAt == null) {
                n();
                return;
            }
            int left = childAt.getLeft();
            int right = childAt.getRight();
            if (Math.abs(i - this.f) <= 1) {
                i3 = this.h;
                i4 = this.i;
            } else {
                int iQ = XTabLayout.this.Q(24);
                i3 = (i >= this.f ? !z : z) ? left - iQ : iQ + right;
                i4 = i3;
            }
            if (i3 == left && i4 == right) {
                return;
            }
            b.a.a.i.o.d dVarA = b.a.a.i.o.g.a();
            this.j = dVarA;
            dVarA.i(b.a.a.i.o.a.f930b);
            dVarA.f(i2);
            dVarA.g(0.0f, 1.0f);
            dVarA.k(new a(i3, left, i4, right));
            dVarA.j(new b(i));
            dVarA.l();
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
            int i = this.h;
            if (i < 0 || this.i <= i) {
                return;
            }
            if (this.f1111b == 0 || XTabLayout.this.f1101c) {
                int i2 = this.i - this.h;
                if (i2 > XTabLayout.this.e.l()) {
                    this.h += (i2 - XTabLayout.this.e.l()) / 2;
                    this.i -= (i2 - XTabLayout.this.e.l()) / 2;
                }
            } else {
                int i3 = this.i;
                int i4 = this.h;
                int i5 = i3 - i4;
                int i6 = this.f1111b;
                if (i5 > i6) {
                    this.h = i4 + ((i5 - i6) / 2);
                    this.i = i3 - ((i5 - i6) / 2);
                }
            }
            RectF rectF = new RectF(this.h, getHeight() - this.f1110a, this.i, getHeight());
            int i7 = this.f1112c;
            canvas.drawRoundRect(rectF, i7 > 0 ? XTabLayout.this.Q(i7) : 0, this.d > 0 ? XTabLayout.this.Q(r3) : 0, this.e);
        }

        public boolean e() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        public float f() {
            return this.f + this.g;
        }

        public final void g(int i, int i2) {
            int i3 = i + XTabLayout.this.g;
            int i4 = i2 - XTabLayout.this.i;
            if (i3 == this.h && i4 == this.i) {
                return;
            }
            this.h = i3;
            this.i = i4;
            ViewCompat.postInvalidateOnAnimation(this);
        }

        public void h(int i, float f) {
            b.a.a.i.o.d dVar = this.j;
            if (dVar != null && dVar.e()) {
                this.j.a();
            }
            this.f = i;
            this.g = f;
            n();
        }

        public void i(int i) {
            if (this.e.getColor() != i) {
                this.e.setColor(i);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        public void j(int i) {
            if (this.f1110a != i) {
                this.f1110a = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        public void k(int i) {
            if (this.f1112c != i) {
                this.f1112c = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        public void l(int i) {
            if (this.d != i) {
                this.d = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        public void m(int i) {
            if (this.f1111b != i) {
                this.f1111b = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        public final void n() {
            int right;
            int left;
            int i;
            View childAt = getChildAt(this.f);
            if (childAt == null || childAt.getWidth() <= 0) {
                right = -1;
                left = -1;
            } else {
                left = childAt.getLeft();
                right = childAt.getRight();
                int i2 = 0;
                if (this.f1111b == 0 && !XTabLayout.this.f1101c) {
                    this.f1111b = R.attr.maxWidth;
                }
                int i3 = this.f1111b;
                if (i3 != 0 && (i = this.i - this.h) > i3) {
                    i2 = (i - i3) / 2;
                    left += i2;
                    right -= i2;
                }
                if (this.g > 0.0f && this.f < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.f + 1);
                    int left2 = childAt2.getLeft() + i2;
                    int right2 = childAt2.getRight() - i2;
                    float f = this.g;
                    left = (int) ((left2 * f) + ((1.0f - f) * left));
                    right = (int) ((right2 * f) + ((1.0f - f) * right));
                }
            }
            g(left, right);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            b.a.a.i.o.d dVar = this.j;
            if (dVar == null || !dVar.e()) {
                n();
                return;
            }
            this.j.a();
            d(this.f, Math.round((1.0f - this.j.b()) * this.j.d()));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) != 1073741824) {
                return;
            }
            boolean z = true;
            if (XTabLayout.this.A == 1 && XTabLayout.this.z == 1) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = getChildAt(i3);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (XTabLayout.this.Q(16) * 2)) {
                    boolean z2 = false;
                    for (int i4 = 0; i4 < childCount; i4++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i4).getLayoutParams();
                        if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                            layoutParams.width = iMax;
                            layoutParams.weight = 0.0f;
                            z2 = true;
                        }
                    }
                    z = z2;
                } else {
                    XTabLayout.this.z = 0;
                    XTabLayout.this.c0(false);
                }
                if (z) {
                    super.onMeasure(i, i2);
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f1118a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Drawable f1119b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f1120c;
        public CharSequence d;
        public int e;
        public View f;
        public XTabLayout g;
        public h h;

        public /* synthetic */ g(a aVar) {
            this();
        }

        @Nullable
        public CharSequence g() {
            return this.d;
        }

        @Nullable
        public View h() {
            return this.f;
        }

        @Nullable
        public Drawable i() {
            return this.f1119b;
        }

        public int j() {
            return this.e;
        }

        @Nullable
        public CharSequence k() {
            return this.f1120c;
        }

        public int l() {
            return this.h.e();
        }

        public final void m() {
            this.g = null;
            this.h = null;
            this.f1118a = null;
            this.f1119b = null;
            this.f1120c = null;
            this.d = null;
            this.e = -1;
            this.f = null;
        }

        public void n() {
            XTabLayout xTabLayout = this.g;
            if (xTabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            xTabLayout.W(this);
        }

        @NonNull
        public g o(@LayoutRes int i) {
            return p(LayoutInflater.from(this.h.getContext()).inflate(i, (ViewGroup) this.h, false));
        }

        @NonNull
        public g p(@Nullable View view) {
            this.f = view;
            t();
            return this;
        }

        @NonNull
        public g q(@Nullable Drawable drawable) {
            this.f1119b = drawable;
            t();
            return this;
        }

        public void r(int i) {
            this.e = i;
        }

        @NonNull
        public g s(@Nullable CharSequence charSequence) {
            this.f1120c = charSequence;
            t();
            return this;
        }

        public final void t() {
            h hVar = this.h;
            if (hVar != null) {
                hVar.h();
            }
        }

        public g() {
            this.e = -1;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class h extends LinearLayout implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public g f1121a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f1122b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ImageView f1123c;
        public View d;
        public TextView e;
        public ImageView f;
        public int g;

        public h(Context context) {
            super(context);
            this.g = 2;
            ViewCompat.setPaddingRelative(this, XTabLayout.this.g, XTabLayout.this.h, XTabLayout.this.i, XTabLayout.this.j);
            setGravity(17);
            setOrientation(1);
            setClickable(true);
        }

        public final float c(Layout layout, int i, float f) {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }

        public String d() {
            return this.f1122b.getText().toString();
        }

        public int e() {
            if (TextUtils.isEmpty(this.f1122b.getText().toString())) {
                return 0;
            }
            Rect rect = new Rect();
            String string = this.f1122b.getText().toString();
            this.f1122b.getPaint().getTextBounds(string, 0, string.length(), rect);
            return rect.width();
        }

        public final void f() {
            g(null);
            setSelected(false);
        }

        public final void g(@Nullable g gVar) {
            if (gVar != this.f1121a) {
                this.f1121a = gVar;
                h();
            }
        }

        public final void h() {
            g gVar = this.f1121a;
            View viewH = gVar != null ? gVar.h() : null;
            if (viewH != null) {
                ViewParent parent = viewH.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewH);
                    }
                    addView(viewH);
                }
                this.d = viewH;
                TextView textView = this.f1122b;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f1123c;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f1123c.setImageDrawable(null);
                }
                TextView textView2 = (TextView) viewH.findViewById(R.id.text1);
                this.e = textView2;
                if (textView2 != null) {
                    this.g = TextViewCompat.getMaxLines(textView2);
                }
                this.f = (ImageView) viewH.findViewById(R.id.icon);
            } else {
                View view = this.d;
                if (view != null) {
                    removeView(view);
                    this.d = null;
                }
                this.e = null;
                this.f = null;
            }
            if (this.d != null) {
                TextView textView3 = this.e;
                if (textView3 == null && this.f == null) {
                    return;
                }
                i(textView3, this.f);
                return;
            }
            if (this.f1123c == null) {
                ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(com.byd.hvac.R.layout.design_layout_tab_icon, (ViewGroup) this, false);
                addView(imageView2, 0);
                this.f1123c = imageView2;
            }
            if (this.f1122b == null) {
                TextView textView4 = (TextView) LayoutInflater.from(getContext()).inflate(com.byd.hvac.R.layout.design_layout_tab_text, (ViewGroup) this, false);
                addView(textView4);
                this.f1122b = textView4;
                this.g = TextViewCompat.getMaxLines(textView4);
            }
            this.f1122b.setTextAppearance(getContext(), XTabLayout.this.k);
            if (XTabLayout.this.l != null) {
                this.f1122b.setTextColor(XTabLayout.this.l);
                if (!this.f1122b.isSelected() || XTabLayout.this.o == 0.0f) {
                    this.f1122b.setTextSize(0, XTabLayout.this.m);
                } else {
                    this.f1122b.setTextSize(0, XTabLayout.this.o);
                }
            }
            i(this.f1122b, this.f1123c);
        }

        public final void i(@Nullable TextView textView, @Nullable ImageView imageView) {
            g gVar = this.f1121a;
            Drawable drawableI = gVar != null ? gVar.i() : null;
            g gVar2 = this.f1121a;
            CharSequence charSequenceK = gVar2 != null ? gVar2.k() : null;
            g gVar3 = this.f1121a;
            CharSequence charSequenceG = gVar3 != null ? gVar3.g() : null;
            if (imageView != null) {
                if (drawableI != null) {
                    imageView.setImageDrawable(drawableI);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
                imageView.setContentDescription(charSequenceG);
            }
            boolean z = !TextUtils.isEmpty(charSequenceK);
            if (textView != null) {
                if (z) {
                    textView.setAllCaps(XTabLayout.this.f1100b);
                    textView.setText(charSequenceK);
                    textView.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
                textView.setContentDescription(charSequenceG);
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int iQ = (z && imageView.getVisibility() == 0) ? XTabLayout.this.Q(8) : 0;
                if (iQ != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iQ;
                    imageView.requestLayout();
                }
            }
            if (!z && !TextUtils.isEmpty(charSequenceG)) {
                setOnLongClickListener(this);
            } else {
                setOnLongClickListener(null);
                setLongClickable(false);
            }
        }

        @Override // android.view.View
        @TargetApi(14)
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(ActionBar.Tab.class.getName());
        }

        @Override // android.view.View
        @TargetApi(14)
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(ActionBar.Tab.class.getName());
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int i = context.getResources().getDisplayMetrics().widthPixels;
            Toast toastMakeText = Toast.makeText(context, this.f1121a.g(), 0);
            toastMakeText.setGravity(49, (iArr[0] + (width / 2)) - (i / 2), height);
            toastMakeText.show();
            return true;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int tabMaxWidth = XTabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = View.MeasureSpec.makeMeasureSpec(XTabLayout.this.t, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.f1122b != null) {
                getResources();
                float f = XTabLayout.this.m;
                int i3 = this.g;
                ImageView imageView = this.f1123c;
                boolean z = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f1122b;
                    if (textView != null && textView.getLineCount() > 1) {
                        f = XTabLayout.this.q;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.f1122b.getTextSize();
                int lineCount = this.f1122b.getLineCount();
                int maxLines = TextViewCompat.getMaxLines(this.f1122b);
                if (f != textSize || (maxLines >= 0 && i3 != maxLines)) {
                    if (XTabLayout.this.A == 1 && f > textSize && lineCount == 1 && ((layout = this.f1122b.getLayout()) == null || c(layout, 0, f) > layout.getWidth())) {
                        z = false;
                    }
                    if (z) {
                        if (!this.f1122b.isSelected() || XTabLayout.this.o == 0.0f) {
                            this.f1122b.setTextSize(0, XTabLayout.this.m);
                        } else {
                            this.f1122b.setTextSize(0, XTabLayout.this.o);
                        }
                        this.f1122b.setMaxLines(i3);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            g gVar = this.f1121a;
            if (gVar == null) {
                return zPerformClick;
            }
            gVar.n();
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (!z) {
                if (XTabLayout.this.r != 0) {
                    setBackgroundColor(XTabLayout.this.r);
                }
                this.f1122b.setTextSize(0, XTabLayout.this.m);
                if (XTabLayout.this.n) {
                    this.f1122b.setTypeface(Typeface.defaultFromStyle(1));
                } else {
                    this.f1122b.setTypeface(Typeface.defaultFromStyle(0));
                }
            }
            if (z2 && z) {
                if (XTabLayout.this.s != 0) {
                    setBackgroundColor(XTabLayout.this.s);
                }
                sendAccessibilityEvent(4);
                TextView textView = this.f1122b;
                if (textView != null) {
                    textView.setSelected(true);
                    if (XTabLayout.this.o != 0.0f) {
                        this.f1122b.setTextSize(0, XTabLayout.this.o);
                        if (XTabLayout.this.p) {
                            this.f1122b.setTypeface(Typeface.defaultFromStyle(1));
                        } else {
                            this.f1122b.setTypeface(Typeface.defaultFromStyle(0));
                        }
                    }
                }
                ImageView imageView = this.f1123c;
                if (imageView != null) {
                    imageView.setSelected(true);
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class i implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ViewPager f1124a;

        public i(ViewPager viewPager) {
            this.f1124a = viewPager;
        }

        @Override // com.byd.hvac.view.xtablayout.XTabLayout.d
        public void a(g gVar) {
        }

        @Override // com.byd.hvac.view.xtablayout.XTabLayout.d
        public void b(g gVar) {
            this.f1124a.setCurrentItem(gVar.j());
        }

        @Override // com.byd.hvac.view.xtablayout.XTabLayout.d
        public void c(g gVar) {
        }
    }

    public XTabLayout(Context context) {
        this(context, null);
    }

    public static ColorStateList N(int i2, int i3) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i3, i2});
    }

    private int getDefaultHeight() {
        int size = this.d.size();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                g gVar = this.d.get(i2);
                if (gVar != null && gVar.i() != null && !TextUtils.isEmpty(gVar.k())) {
                    z = true;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        return z ? 72 : 48;
    }

    private float getScrollPosition() {
        return this.f.f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTabMaxWidth() {
        return this.t;
    }

    private int getTabMinWidth() {
        if (this.J != null && this.w != 0) {
            WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
            return (this.J.getCount() == 1 || this.w == 1) ? windowManager.getDefaultDisplay().getWidth() : this.J.getCount() < this.w ? windowManager.getDefaultDisplay().getWidth() / this.J.getCount() : windowManager.getDefaultDisplay().getWidth() / this.w;
        }
        if (this.w != 0) {
            return ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getWidth() / this.w;
        }
        int i2 = this.u;
        if (i2 != -1) {
            return i2;
        }
        if (this.A == 0) {
            return this.x;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.f.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void setSelectedTabView(int i2) {
        int childCount = this.f.getChildCount();
        if (i2 >= childCount || this.f.getChildAt(i2).isSelected()) {
            return;
        }
        int i3 = 0;
        while (i3 < childCount) {
            this.f.getChildAt(i3).setSelected(i3 == i2);
            i3++;
        }
    }

    public final void D() {
        post(new a());
    }

    public void E(@NonNull g gVar) {
        F(gVar, this.d.isEmpty());
    }

    public void F(@NonNull g gVar, boolean z) {
        if (gVar.g != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        H(gVar, z);
        M(gVar, this.d.size());
        if (z) {
            gVar.n();
        }
    }

    public final void G(@NonNull TabItem tabItem) {
        g gVarS = S();
        CharSequence charSequence = tabItem.f1096a;
        if (charSequence != null) {
            gVarS.s(charSequence);
        }
        Drawable drawable = tabItem.f1097b;
        if (drawable != null) {
            gVarS.q(drawable);
        }
        int i2 = tabItem.f1098c;
        if (i2 != 0) {
            gVarS.o(i2);
        }
        E(gVarS);
    }

    public final void H(g gVar, boolean z) {
        h hVar = gVar.h;
        if (this.o != 0.0f) {
            hVar.post(new b(hVar));
        }
        this.f.addView(hVar, O());
        if (z) {
            hVar.setSelected(true);
        }
    }

    public final void I(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        G((TabItem) view);
    }

    public final void J(int i2) {
        if (i2 == -1) {
            return;
        }
        if (getWindowToken() == null || !ViewCompat.isLaidOut(this) || this.f.e()) {
            setScrollPosition(i2, 0.0f, true);
            return;
        }
        int scrollX = getScrollX();
        int iL = L(i2, 0.0f);
        if (scrollX != iL) {
            if (this.H == null) {
                b.a.a.i.o.d dVarA = b.a.a.i.o.g.a();
                this.H = dVarA;
                dVarA.i(b.a.a.i.o.a.f930b);
                this.H.f(300);
                this.H.k(new c());
            }
            this.H.h(scrollX, iL);
            this.H.l();
        }
        this.f.d(i2, 300);
    }

    public final void K() {
        ViewCompat.setPaddingRelative(this.f, this.A == 0 ? Math.max(0, this.y - this.g) : 0, 0, 0, 0);
        int i2 = this.A;
        if (i2 == 0) {
            this.f.setGravity(GravityCompat.START);
        } else if (i2 == 1) {
            this.f.setGravity(1);
        }
        c0(true);
    }

    public final int L(int i2, float f2) {
        if (this.A != 0) {
            return 0;
        }
        View childAt = this.f.getChildAt(i2);
        int i3 = i2 + 1;
        return ((childAt.getLeft() + ((int) ((((childAt != null ? childAt.getWidth() : 0) + ((i3 < this.f.getChildCount() ? this.f.getChildAt(i3) : null) != null ? r4.getWidth() : 0)) * f2) * 0.5f))) + (childAt.getWidth() / 2)) - (getWidth() / 2);
    }

    public final void M(g gVar, int i2) {
        gVar.r(i2);
        this.d.add(i2, gVar);
        int size = this.d.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                return;
            } else {
                this.d.get(i2).r(i2);
            }
        }
    }

    public final LinearLayout.LayoutParams O() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        b0(layoutParams);
        return layoutParams;
    }

    public final h P(@NonNull g gVar) {
        Pools.Pool<h> pool = this.M;
        h hVarAcquire = pool != null ? pool.acquire() : null;
        if (hVarAcquire == null) {
            hVarAcquire = new h(getContext());
        }
        hVarAcquire.g(gVar);
        hVarAcquire.setFocusable(true);
        hVarAcquire.setMinimumWidth(getTabMinWidth());
        return hVarAcquire;
    }

    public final int Q(int i2) {
        return Math.round(getResources().getDisplayMetrics().density * i2);
    }

    @Nullable
    public g R(int i2) {
        return this.d.get(i2);
    }

    @NonNull
    public g S() {
        g gVarAcquire = f1099a.acquire();
        if (gVarAcquire == null) {
            gVarAcquire = new g(null);
        }
        gVarAcquire.g = this;
        gVarAcquire.h = P(gVarAcquire);
        return gVarAcquire;
    }

    public final void T() {
        int currentItem;
        U();
        PagerAdapter pagerAdapter = this.J;
        if (pagerAdapter == null) {
            U();
            return;
        }
        int count = pagerAdapter.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            F(S().s(this.J.getPageTitle(i2)), false);
        }
        ViewPager viewPager = this.I;
        if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
            return;
        }
        W(R(currentItem));
    }

    public void U() {
        for (int childCount = this.f.getChildCount() - 1; childCount >= 0; childCount--) {
            V(childCount);
        }
        Iterator<g> it = this.d.iterator();
        while (it.hasNext()) {
            g next = it.next();
            it.remove();
            next.m();
            f1099a.release(next);
        }
        this.e = null;
    }

    public final void V(int i2) {
        h hVar = (h) this.f.getChildAt(i2);
        this.f.removeViewAt(i2);
        if (hVar != null) {
            hVar.f();
            this.M.release(hVar);
        }
        requestLayout();
    }

    public void W(g gVar) {
        X(gVar, true);
    }

    public void X(g gVar, boolean z) {
        d dVar;
        d dVar2;
        g gVar2 = this.e;
        if (gVar2 == gVar) {
            if (gVar2 != null) {
                d dVar3 = this.F;
                if (dVar3 != null) {
                    dVar3.c(gVar2);
                }
                Iterator<d> it = this.G.iterator();
                while (it.hasNext()) {
                    it.next().c(this.e);
                }
                J(gVar.j());
                return;
            }
            return;
        }
        if (z) {
            int iJ = gVar != null ? gVar.j() : -1;
            if (iJ != -1) {
                setSelectedTabView(iJ);
            }
            g gVar3 = this.e;
            if ((gVar3 == null || gVar3.j() == -1) && iJ != -1) {
                setScrollPosition(iJ, 0.0f, true);
            } else {
                J(iJ);
            }
        }
        g gVar4 = this.e;
        if (gVar4 != null && (dVar2 = this.F) != null) {
            dVar2.a(gVar4);
        }
        Iterator<d> it2 = this.G.iterator();
        while (it2.hasNext()) {
            it2.next().a(this.e);
        }
        this.e = gVar;
        if (gVar != null && (dVar = this.F) != null) {
            dVar.b(gVar);
        }
        Iterator<d> it3 = this.G.iterator();
        while (it3.hasNext()) {
            it3.next().b(this.e);
        }
    }

    public final void Y(@Nullable PagerAdapter pagerAdapter, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter2 = this.J;
        if (pagerAdapter2 != null && (dataSetObserver = this.K) != null) {
            pagerAdapter2.unregisterDataSetObserver(dataSetObserver);
        }
        this.J = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.K == null) {
                this.K = new e(this, null);
            }
            pagerAdapter.registerDataSetObserver(this.K);
        }
        T();
    }

    public final void Z(int i2, float f2, boolean z, boolean z2) {
        int iRound = Math.round(i2 + f2);
        if (iRound < 0 || iRound >= this.f.getChildCount()) {
            return;
        }
        if (z2) {
            this.f.h(i2, f2);
        }
        b.a.a.i.o.d dVar = this.H;
        if (dVar != null && dVar.e()) {
            this.H.a();
        }
        scrollTo(L(i2, f2), 0);
        if (z) {
            setSelectedTabView(iRound);
        }
    }

    public final void a0() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.d.get(i2).t();
        }
    }

    public void addOnTabSelectedListener(d dVar) {
        this.G.add(dVar);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        I(view);
    }

    public final void b0(LinearLayout.LayoutParams layoutParams) {
        if (this.A == 1 && this.z == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    public final void c0(boolean z) {
        for (int i2 = 0; i2 < this.f.getChildCount(); i2++) {
            View childAt = this.f.getChildAt(i2);
            childAt.setMinimumWidth(getTabMinWidth());
            b0((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    public int getSelectedTabPosition() {
        g gVar = this.e;
        if (gVar != null) {
            return gVar.j();
        }
        return -1;
    }

    public int getTabCount() {
        return this.d.size();
    }

    public int getTabGravity() {
        return this.z;
    }

    public int getTabMode() {
        return this.A;
    }

    @Nullable
    public ColorStateList getTabTextColors() {
        return this.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00c4  */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r7, int r8) {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.hvac.view.xtablayout.XTabLayout.onMeasure(int, int):void");
    }

    public void setAllCaps(boolean z) {
        this.f1100b = z;
    }

    public void setDividerColor(int i2) {
        this.D = i2;
        D();
    }

    public void setDividerGravity(int i2) {
        this.E = i2;
        D();
    }

    public void setDividerSize(int i2, int i3) {
        this.B = i2;
        this.C = i3;
        D();
    }

    public void setOnTabSelectedListener(d dVar) {
        this.F = dVar;
    }

    public void setScrollPosition(int i2, float f2, boolean z) {
        Z(i2, f2, z, true);
    }

    public void setSelectedTabIndicatorColor(@ColorInt int i2) {
        this.f.i(i2);
    }

    public void setSelectedTabIndicatorHeight(int i2) {
        this.f.j(i2);
    }

    public void setTabGravity(int i2) {
        if (this.z != i2) {
            this.z = i2;
            K();
        }
    }

    public void setTabMode(int i2) {
        if (i2 != this.A) {
            this.A = i2;
            K();
        }
    }

    public void setTabTextColors(@Nullable ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            a0();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(@Nullable PagerAdapter pagerAdapter) {
        Y(pagerAdapter, false);
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;
        ViewPager viewPager2 = this.I;
        if (viewPager2 != null && (tabLayoutOnPageChangeListener = this.L) != null) {
            viewPager2.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
        }
        if (viewPager == null) {
            this.I = null;
            setOnTabSelectedListener(null);
            Y(null, true);
            return;
        }
        PagerAdapter adapter = viewPager.getAdapter();
        if (adapter == null) {
            throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
        }
        this.I = viewPager;
        if (this.L == null) {
            this.L = new TabLayoutOnPageChangeListener(this);
        }
        this.L.a();
        viewPager.addOnPageChangeListener(this.L);
        setOnTabSelectedListener(new i(viewPager));
        Y(adapter, true);
    }

    public void setxTabDisplayNum(int i2) {
        this.w = i2;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public XTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2) {
        I(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    public XTabLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1100b = false;
        this.f1101c = false;
        this.d = new ArrayList<>();
        this.m = 0.0f;
        this.o = 0.0f;
        this.t = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.G = new ArrayList();
        this.M = new Pools.SimplePool(12);
        b.a.a.i.o.c.a(context);
        setHorizontalScrollBarEnabled(false);
        f fVar = new f(context);
        this.f = fVar;
        super.addView(fVar, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.byd.hvac.R.styleable.XTabLayout, i2, 2131821141);
        fVar.j(typedArrayObtainStyledAttributes.getDimensionPixelSize(10, Q(2)));
        fVar.m(typedArrayObtainStyledAttributes.getDimensionPixelSize(13, 0));
        fVar.k(typedArrayObtainStyledAttributes.getDimensionPixelSize(11, 0));
        fVar.l(typedArrayObtainStyledAttributes.getDimensionPixelSize(12, 0));
        fVar.i(typedArrayObtainStyledAttributes.getColor(9, 0));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(17, 0);
        this.j = dimensionPixelSize;
        this.i = dimensionPixelSize;
        this.h = dimensionPixelSize;
        this.g = dimensionPixelSize;
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(20, dimensionPixelSize);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(21, this.h);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(19, this.i);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(18, this.j);
        this.f1100b = typedArrayObtainStyledAttributes.getBoolean(25, false);
        this.k = typedArrayObtainStyledAttributes.getResourceId(26, 2131820913);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(30, 0);
        this.n = typedArrayObtainStyledAttributes.getBoolean(27, false);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(24, 0);
        this.p = typedArrayObtainStyledAttributes.getBoolean(29, false);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(this.k, com.byd.hvac.R.styleable.TextAppearance);
        try {
            if (this.m == 0.0f) {
                this.m = typedArrayObtainStyledAttributes2.getDimensionPixelSize(0, 0);
            }
            this.l = typedArrayObtainStyledAttributes2.getColorStateList(3);
            typedArrayObtainStyledAttributes2.recycle();
            if (typedArrayObtainStyledAttributes.hasValue(28)) {
                this.l = typedArrayObtainStyledAttributes.getColorStateList(28);
            }
            if (typedArrayObtainStyledAttributes.hasValue(23)) {
                this.l = N(this.l.getDefaultColor(), typedArrayObtainStyledAttributes.getColor(23, 0));
            }
            this.w = typedArrayObtainStyledAttributes.getInt(2, 0);
            this.u = typedArrayObtainStyledAttributes.getDimensionPixelSize(15, -1);
            this.v = typedArrayObtainStyledAttributes.getDimensionPixelSize(14, -1);
            this.r = typedArrayObtainStyledAttributes.getColor(0, 0);
            this.s = typedArrayObtainStyledAttributes.getColor(22, 0);
            this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
            this.A = typedArrayObtainStyledAttributes.getInt(16, 1);
            this.z = typedArrayObtainStyledAttributes.getInt(8, 0);
            this.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 0);
            this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
            this.D = typedArrayObtainStyledAttributes.getColor(3, ViewCompat.MEASURED_STATE_MASK);
            this.E = typedArrayObtainStyledAttributes.getInteger(4, 1);
            this.f1101c = typedArrayObtainStyledAttributes.getBoolean(7, false);
            typedArrayObtainStyledAttributes.recycle();
            Resources resources = getResources();
            this.q = resources.getDimensionPixelSize(com.byd.hvac.R.dimen.design_tab_text_size_2line);
            this.x = resources.getDimensionPixelSize(com.byd.hvac.R.dimen.design_tab_scrollable_min_width);
            K();
            D();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes2.recycle();
            throw th;
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        I(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        I(view);
    }

    public void setTabTextColors(int i2, int i3) {
        setTabTextColors(N(i2, i3));
    }
}
