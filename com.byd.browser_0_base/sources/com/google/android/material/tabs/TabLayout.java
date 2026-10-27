package com.google.android.material.tabs;

import android.R;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.bz;
import defpackage.dc2;
import defpackage.in3;
import defpackage.jg0;
import defpackage.kv1;
import defpackage.oj2;
import defpackage.om3;
import defpackage.r83;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.t22;
import defpackage.u22;
import defpackage.u5;
import defpackage.uc1;
import defpackage.uj0;
import defpackage.yn3;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.d
public class TabLayout extends HorizontalScrollView {
    public static final int V = R$style.Widget_Design_TabLayout;
    public static final u22 W = new u22(16);
    public int A;
    public final int B;
    public int C;
    public int D;
    public boolean E;
    public boolean F;
    public int G;
    public int H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public com.google.android.material.tabs.a f35J;
    public c K;
    public final ArrayList<c> L;
    public i M;
    public ValueAnimator N;
    public ViewPager O;
    public kv1 P;
    public e Q;
    public h R;
    public b S;
    public boolean T;
    public final t22 U;
    public final ArrayList<g> e;
    public g f;
    public final f g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public ColorStateList m;
    public ColorStateList n;
    public ColorStateList o;
    public Drawable p;
    public int q;
    public final PorterDuff.Mode r;
    public final float s;
    public final float t;
    public final int u;
    public int v;
    public final int w;
    public final int x;
    public final int y;
    public final int z;

    public final class TabView extends LinearLayout {
        public static final /* synthetic */ int p = 0;
        public g e;
        public TextView f;
        public ImageView g;
        public View h;
        public com.google.android.material.badge.a i;
        public View j;
        public TextView k;
        public ImageView l;
        public Drawable m;
        public int n;

        public TabView(Context context) {
            super(context);
            this.n = 2;
            e(context);
            int i = TabLayout.this.h;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.e.i(this, i, TabLayout.this.i, TabLayout.this.j, TabLayout.this.k);
            setGravity(17);
            setOrientation(!TabLayout.this.E ? 1 : 0);
            setClickable(true);
            om3.k.a(this, PointerIcon.getSystemIcon(getContext(), 1002));
        }

        private com.google.android.material.badge.a getBadge() {
            return this.i;
        }

        private com.google.android.material.badge.a getOrCreateBadge() {
            if (this.i == null) {
                this.i = new com.google.android.material.badge.a(getContext(), null);
            }
            b();
            com.google.android.material.badge.a aVar = this.i;
            if (aVar != null) {
                return aVar;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        public final void a() {
            if (this.i != null) {
                setClipChildren(true);
                setClipToPadding(true);
                ViewGroup viewGroup = (ViewGroup) getParent();
                if (viewGroup != null) {
                    viewGroup.setClipChildren(true);
                    viewGroup.setClipToPadding(true);
                }
                View view = this.h;
                if (view != null) {
                    com.google.android.material.badge.a aVar = this.i;
                    if (aVar != null) {
                        if (aVar.d() != null) {
                            aVar.d().setForeground(null);
                        } else {
                            view.getOverlay().remove(aVar);
                        }
                    }
                    this.h = null;
                }
            }
        }

        public final void b() {
            g gVar;
            g gVar2;
            if (this.i != null) {
                if (this.j != null) {
                    a();
                    return;
                }
                ImageView imageView = this.g;
                if (imageView != null && (gVar2 = this.e) != null && gVar2.b != null) {
                    if (this.h == imageView) {
                        c(imageView);
                        return;
                    }
                    a();
                    ImageView imageView2 = this.g;
                    if ((this.i != null) && imageView2 != null) {
                        setClipChildren(false);
                        setClipToPadding(false);
                        ViewGroup viewGroup = (ViewGroup) getParent();
                        if (viewGroup != null) {
                            viewGroup.setClipChildren(false);
                            viewGroup.setClipToPadding(false);
                        }
                        com.google.android.material.badge.a aVar = this.i;
                        Rect rect = new Rect();
                        imageView2.getDrawingRect(rect);
                        aVar.setBounds(rect);
                        aVar.g(imageView2, null);
                        if (aVar.d() != null) {
                            aVar.d().setForeground(aVar);
                        } else {
                            imageView2.getOverlay().add(aVar);
                        }
                        this.h = imageView2;
                        return;
                    }
                    return;
                }
                TextView textView = this.f;
                if (textView == null || (gVar = this.e) == null || gVar.g != 1) {
                    a();
                    return;
                }
                if (this.h == textView) {
                    c(textView);
                    return;
                }
                a();
                TextView textView2 = this.f;
                if ((this.i != null) && textView2 != null) {
                    setClipChildren(false);
                    setClipToPadding(false);
                    ViewGroup viewGroup2 = (ViewGroup) getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.setClipChildren(false);
                        viewGroup2.setClipToPadding(false);
                    }
                    com.google.android.material.badge.a aVar2 = this.i;
                    Rect rect2 = new Rect();
                    textView2.getDrawingRect(rect2);
                    aVar2.setBounds(rect2);
                    aVar2.g(textView2, null);
                    if (aVar2.d() != null) {
                        aVar2.d().setForeground(aVar2);
                    } else {
                        textView2.getOverlay().add(aVar2);
                    }
                    this.h = textView2;
                }
            }
        }

        public final void c(View view) {
            com.google.android.material.badge.a aVar = this.i;
            if ((aVar != null) && view == this.h) {
                Rect rect = new Rect();
                view.getDrawingRect(rect);
                aVar.setBounds(rect);
                aVar.g(view, null);
            }
        }

        public final void d() {
            g gVar = this.e;
            View view = gVar != null ? gVar.f : null;
            if (view != null) {
                ViewParent parent = view.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(view);
                    }
                    addView(view);
                }
                this.j = view;
                TextView textView = this.f;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.g;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.g.setImageDrawable(null);
                }
                TextView textView2 = (TextView) view.findViewById(R.id.text1);
                this.k = textView2;
                if (textView2 != null) {
                    this.n = textView2.getMaxLines();
                }
                this.l = (ImageView) view.findViewById(R.id.icon);
            } else {
                View view2 = this.j;
                if (view2 != null) {
                    removeView(view2);
                    this.j = null;
                }
                this.k = null;
                this.l = null;
            }
            boolean z = false;
            if (this.j == null) {
                if (this.g == null) {
                    ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_icon, (ViewGroup) this, false);
                    this.g = imageView2;
                    addView(imageView2, 0);
                }
                if (this.f == null) {
                    TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_text, (ViewGroup) this, false);
                    this.f = textView3;
                    addView(textView3);
                    this.n = this.f.getMaxLines();
                }
                TextView textView4 = this.f;
                TabLayout tabLayout = TabLayout.this;
                textView4.setTextAppearance(tabLayout.l);
                ColorStateList colorStateList = tabLayout.m;
                if (colorStateList != null) {
                    this.f.setTextColor(colorStateList);
                }
                f(this.f, this.g);
                b();
                ImageView imageView3 = this.g;
                if (imageView3 != null) {
                    imageView3.addOnLayoutChangeListener(new com.google.android.material.tabs.d(this, imageView3));
                }
                TextView textView5 = this.f;
                if (textView5 != null) {
                    textView5.addOnLayoutChangeListener(new com.google.android.material.tabs.d(this, textView5));
                }
            } else {
                TextView textView6 = this.k;
                if (textView6 != null || this.l != null) {
                    f(textView6, this.l);
                }
            }
            if (gVar != null && !TextUtils.isEmpty(gVar.d)) {
                setContentDescription(gVar.d);
            }
            if (gVar != null && gVar.a()) {
                z = true;
            }
            setSelected(z);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.m;
            boolean state = false;
            if (drawable != null && drawable.isStateful()) {
                state = false | this.m.setState(drawableState);
            }
            if (state) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public final void e(Context context) {
            TabLayout tabLayout = TabLayout.this;
            int i = tabLayout.u;
            if (i != 0) {
                Drawable drawableA = AppCompatResources.a(context, i);
                this.m = drawableA;
                if (drawableA != null && drawableA.isStateful()) {
                    this.m.setState(getDrawableState());
                }
            } else {
                this.m = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            Drawable rippleDrawable = gradientDrawable;
            if (tabLayout.o != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateListA = dc2.a(tabLayout.o);
                boolean z = tabLayout.I;
                GradientDrawable gradientDrawable3 = gradientDrawable;
                if (z) {
                    gradientDrawable3 = null;
                }
                rippleDrawable = new RippleDrawable(colorStateListA, gradientDrawable3, z ? null : gradientDrawable2);
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(this, rippleDrawable);
            tabLayout.invalidate();
        }

        public final void f(TextView textView, ImageView imageView) {
            Drawable drawable;
            g gVar = this.e;
            Drawable drawableMutate = (gVar == null || (drawable = gVar.b) == null) ? null : drawable.mutate();
            TabLayout tabLayout = TabLayout.this;
            if (drawableMutate != null) {
                drawableMutate.setTintList(tabLayout.n);
                PorterDuff.Mode mode = tabLayout.r;
                if (mode != null) {
                    drawableMutate.setTintMode(mode);
                }
            }
            g gVar2 = this.e;
            CharSequence charSequence = gVar2 != null ? gVar2.c : null;
            if (imageView != null) {
                if (drawableMutate != null) {
                    imageView.setImageDrawable(drawableMutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(charSequence);
            if (textView != null) {
                if (z) {
                    textView.setText(charSequence);
                    if (this.e.g == 1) {
                        textView.setVisibility(0);
                    } else {
                        textView.setVisibility(8);
                    }
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int iB = (z && imageView.getVisibility() == 0) ? (int) yn3.b(getContext(), 8) : 0;
                if (tabLayout.E) {
                    if (iB != marginLayoutParams.getMarginEnd()) {
                        marginLayoutParams.setMarginEnd(iB);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iB != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iB;
                    marginLayoutParams.setMarginEnd(0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            g gVar3 = this.e;
            CharSequence charSequence2 = gVar3 != null ? gVar3.d : null;
            if (!z) {
                charSequence = charSequence2;
            }
            setTooltipText(charSequence);
        }

        public int getContentHeight() {
            View[] viewArr = {this.f, this.g, this.j};
            int iMax = 0;
            int iMin = 0;
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                View view = viewArr[i];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z ? Math.min(iMin, view.getTop()) : view.getTop();
                    iMax = z ? Math.max(iMax, view.getBottom()) : view.getBottom();
                    z = true;
                }
            }
            return iMax - iMin;
        }

        public int getContentWidth() {
            View[] viewArr = {this.f, this.g, this.j};
            int iMax = 0;
            int iMin = 0;
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                View view = viewArr[i];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z ? Math.max(iMax, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return iMax - iMin;
        }

        public g getTab() {
            return this.e;
        }

        @Override // android.view.View
        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            com.google.android.material.badge.a aVar = this.i;
            if (aVar != null && aVar.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.i.c()));
            }
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(0, 1, this.e.e, 1, false, isSelected()));
            if (isSelected()) {
                accessibilityNodeInfoCompat.o(false);
                accessibilityNodeInfoCompat.k(AccessibilityNodeInfoCompat.a.g);
            }
            accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(R$string.item_view_role_description));
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x008d  */
        @Override // android.widget.LinearLayout, android.view.View
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onMeasure(int r8, int r9) {
            /*
                r7 = this;
                int r0 = android.view.View.MeasureSpec.getSize(r8)
                int r1 = android.view.View.MeasureSpec.getMode(r8)
                com.google.android.material.tabs.TabLayout r2 = com.google.android.material.tabs.TabLayout.this
                int r3 = r2.getTabMaxWidth()
                if (r3 <= 0) goto L1c
                if (r1 == 0) goto L14
                if (r0 <= r3) goto L1c
            L14:
                int r8 = r2.v
                r0 = -2147483648(0xffffffff80000000, float:-0.0)
                int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r0)
            L1c:
                super.onMeasure(r8, r9)
                android.widget.TextView r0 = r7.f
                if (r0 == 0) goto L9d
                float r0 = r2.s
                int r1 = r7.n
                android.widget.ImageView r3 = r7.g
                r4 = 1
                if (r3 == 0) goto L34
                int r3 = r3.getVisibility()
                if (r3 != 0) goto L34
                r1 = r4
                goto L40
            L34:
                android.widget.TextView r3 = r7.f
                if (r3 == 0) goto L40
                int r3 = r3.getLineCount()
                if (r3 <= r4) goto L40
                float r0 = r2.t
            L40:
                android.widget.TextView r3 = r7.f
                float r3 = r3.getTextSize()
                android.widget.TextView r5 = r7.f
                int r5 = r5.getLineCount()
                android.widget.TextView r6 = r7.f
                int r6 = r6.getMaxLines()
                int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
                if (r3 != 0) goto L5a
                if (r6 < 0) goto L9d
                if (r1 == r6) goto L9d
            L5a:
                int r2 = r2.D
                r6 = 0
                if (r2 != r4) goto L8e
                if (r3 <= 0) goto L8e
                if (r5 != r4) goto L8e
                android.widget.TextView r2 = r7.f
                android.text.Layout r2 = r2.getLayout()
                if (r2 == 0) goto L8d
                float r3 = r2.getLineWidth(r6)
                android.text.TextPaint r2 = r2.getPaint()
                float r2 = r2.getTextSize()
                float r2 = r0 / r2
                float r2 = r2 * r3
                int r3 = r7.getMeasuredWidth()
                int r5 = r7.getPaddingLeft()
                int r3 = r3 - r5
                int r5 = r7.getPaddingRight()
                int r3 = r3 - r5
                float r3 = (float) r3
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L8e
            L8d:
                r4 = r6
            L8e:
                if (r4 == 0) goto L9d
                android.widget.TextView r2 = r7.f
                r2.setTextSize(r6, r0)
                android.widget.TextView r0 = r7.f
                r0.setMaxLines(r1)
                super.onMeasure(r8, r9)
            L9d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.TabView.onMeasure(int, int):void");
        }

        @Override // android.view.View
        public final boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.e == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.e.b();
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            if (isSelected() != z) {
            }
            super.setSelected(z);
            TextView textView = this.f;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.g;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.j;
            if (view != null) {
                view.setSelected(z);
            }
        }

        public void setTab(g gVar) {
            if (gVar != this.e) {
                this.e = gVar;
                d();
            }
        }
    }

    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            TabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    public class b implements ViewPager.g {
        public boolean e;

        public b() {
        }

        @Override // androidx.viewpager.widget.ViewPager.g
        public final void a(ViewPager viewPager, kv1 kv1Var, kv1 kv1Var2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.O == viewPager) {
                tabLayout.q(kv1Var2, this.e);
            }
        }
    }

    @Deprecated
    public interface c<T extends g> {
        void a(T t);

        void h(T t);

        void l(T t);
    }

    public interface d extends c<g> {
    }

    public class e extends DataSetObserver {
        public e() {
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            TabLayout.this.m();
        }

        @Override // android.database.DataSetObserver
        public final void onInvalidated() {
            TabLayout.this.m();
        }
    }

    public class f extends LinearLayout {
        public static final /* synthetic */ int i = 0;
        public ValueAnimator e;
        public int f;
        public float g;

        public f(Context context) {
            super(context);
            this.f = -1;
            setWillNotDraw(false);
        }

        public final void a() {
            View childAt = getChildAt(this.f);
            TabLayout tabLayout = TabLayout.this;
            com.google.android.material.tabs.a aVar = tabLayout.f35J;
            Drawable drawable = tabLayout.p;
            aVar.getClass();
            RectF rectFA = com.google.android.material.tabs.a.a(tabLayout, childAt);
            drawable.setBounds((int) rectFA.left, drawable.getBounds().top, (int) rectFA.right, drawable.getBounds().bottom);
        }

        public final void b(int i2) {
            TabLayout tabLayout = TabLayout.this;
            Rect bounds = tabLayout.p.getBounds();
            tabLayout.p.setBounds(bounds.left, 0, bounds.right, i2);
            requestLayout();
        }

        public final void c(View view, View view2, float f) {
            if (view != null && view.getWidth() > 0) {
                TabLayout tabLayout = TabLayout.this;
                tabLayout.f35J.b(tabLayout, view, view2, f, tabLayout.p);
            } else {
                TabLayout tabLayout2 = TabLayout.this;
                Drawable drawable = tabLayout2.p;
                drawable.setBounds(-1, drawable.getBounds().top, -1, tabLayout2.p.getBounds().bottom);
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(this);
        }

        public final void d(int i2, int i3, boolean z) {
            View childAt = getChildAt(this.f);
            View childAt2 = getChildAt(i2);
            if (childAt2 == null) {
                a();
                return;
            }
            com.google.android.material.tabs.b bVar = new com.google.android.material.tabs.b(this, childAt, childAt2);
            if (!z) {
                this.e.removeAllUpdateListeners();
                this.e.addUpdateListener(bVar);
                return;
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            this.e = valueAnimator;
            valueAnimator.setInterpolator(u5.b);
            valueAnimator.setDuration(i3);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(bVar);
            valueAnimator.addListener(new com.google.android.material.tabs.c(this, i2));
            valueAnimator.start();
        }

        @Override // android.view.View
        public final void draw(Canvas canvas) {
            int height;
            TabLayout tabLayout = TabLayout.this;
            int iHeight = tabLayout.p.getBounds().height();
            if (iHeight < 0) {
                iHeight = tabLayout.p.getIntrinsicHeight();
            }
            int i2 = tabLayout.C;
            if (i2 == 0) {
                height = getHeight() - iHeight;
                iHeight = getHeight();
            } else if (i2 != 1) {
                height = 0;
                if (i2 != 2) {
                    iHeight = i2 != 3 ? 0 : getHeight();
                }
            } else {
                height = (getHeight() - iHeight) / 2;
                iHeight = (getHeight() + iHeight) / 2;
            }
            if (tabLayout.p.getBounds().width() > 0) {
                Rect bounds = tabLayout.p.getBounds();
                tabLayout.p.setBounds(bounds.left, height, bounds.right, iHeight);
                Drawable drawable = tabLayout.p;
                int i3 = tabLayout.q;
                if (i3 != 0) {
                    drawable.setTint(i3);
                } else {
                    drawable.setTintList(null);
                }
                drawable.draw(canvas);
            }
            super.draw(canvas);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public final void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
            ValueAnimator valueAnimator = this.e;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                a();
            } else {
                d(this.f, -1, false);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public final void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            if (View.MeasureSpec.getMode(i2) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z = true;
            if (tabLayout.A == 1 || tabLayout.D == 2) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i4 = 0; i4 < childCount; i4++) {
                    View childAt = getChildAt(i4);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (((int) yn3.b(getContext(), 16)) * 2)) {
                    boolean z2 = false;
                    for (int i5 = 0; i5 < childCount; i5++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i5).getLayoutParams();
                        if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                            layoutParams.width = iMax;
                            layoutParams.weight = 0.0f;
                            z2 = true;
                        }
                    }
                    z = z2;
                } else {
                    tabLayout.A = 0;
                    tabLayout.u(false);
                }
                if (z) {
                    super.onMeasure(i2, i3);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public final void onRtlPropertiesChanged(int i2) {
            super.onRtlPropertiesChanged(i2);
        }
    }

    public static class g {
        public Object a;
        public Drawable b;
        public CharSequence c;
        public CharSequence d;
        public View f;
        public TabLayout h;
        public TabView i;
        public int e = -1;
        public final int g = 1;
        public int j = -1;

        public final boolean a() {
            TabLayout tabLayout = this.h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            int selectedTabPosition = tabLayout.getSelectedTabPosition();
            return selectedTabPosition != -1 && selectedTabPosition == this.e;
        }

        public final void b() {
            TabLayout tabLayout = this.h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.p(this, true);
        }

        public final void c(int i) {
            this.f = LayoutInflater.from(this.i.getContext()).inflate(i, (ViewGroup) this.i, false);
            e();
        }

        public final void d(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(charSequence)) {
                this.i.setContentDescription(charSequence);
            }
            this.c = charSequence;
            e();
        }

        public final void e() {
            TabView tabView = this.i;
            if (tabView != null) {
                tabView.d();
            }
        }
    }

    public static class h implements ViewPager.h {
        public final WeakReference<TabLayout> e;
        public int f;
        public int g;

        public h(TabLayout tabLayout) {
            this.e = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.h
        public final void b(int i) {
            this.f = this.g;
            this.g = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.h
        public void c(int i) {
            TabLayout tabLayout = this.e.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i || i >= tabLayout.getTabCount()) {
                return;
            }
            int i2 = this.g;
            tabLayout.p(tabLayout.k(i), i2 == 0 || (i2 == 2 && this.f == 0));
        }

        @Override // androidx.viewpager.widget.ViewPager.h
        public final void d(int i, float f) {
            TabLayout tabLayout = this.e.get();
            if (tabLayout != null) {
                int i2 = this.g;
                tabLayout.r(i2 != 2 || this.f == 1, i, (i2 == 2 && this.f == 0) ? false : true, f);
            }
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private void g(int i2) {
        boolean z;
        if (i2 == -1) {
            return;
        }
        if (getWindowToken() != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.c(this)) {
                f fVar = this.g;
                int childCount = fVar.getChildCount();
                int i3 = 0;
                while (true) {
                    if (i3 >= childCount) {
                        z = false;
                        break;
                    } else {
                        if (fVar.getChildAt(i3).getWidth() <= 0) {
                            z = true;
                            break;
                        }
                        i3++;
                    }
                }
                if (!z) {
                    int scrollX = getScrollX();
                    int i4 = i(0.0f, i2);
                    if (scrollX != i4) {
                        j();
                        this.N.setIntValues(scrollX, i4);
                        this.N.start();
                    }
                    ValueAnimator valueAnimator = fVar.e;
                    if (valueAnimator != null && valueAnimator.isRunning()) {
                        fVar.e.cancel();
                    }
                    fVar.d(i2, this.B, true);
                    return;
                }
            }
        }
        r(true, i2, true, 0.0f);
    }

    private int getDefaultHeight() {
        ArrayList<g> arrayList = this.e;
        int size = arrayList.size();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                g gVar = arrayList.get(i2);
                if (gVar != null && gVar.b != null && !TextUtils.isEmpty(gVar.c)) {
                    z = true;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        return (!z || this.E) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i2 = this.w;
        if (i2 != -1) {
            return i2;
        }
        int i3 = this.D;
        if (i3 == 0 || i3 == 2) {
            return this.y;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.g.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void setSelectedTabView(int i2) {
        f fVar = this.g;
        int childCount = fVar.getChildCount();
        if (i2 < childCount) {
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = fVar.getChildAt(i3);
                boolean z = true;
                childAt.setSelected(i3 == i2);
                if (i3 != i2) {
                    z = false;
                }
                childAt.setActivated(z);
                i3++;
            }
        }
    }

    public final void a(g gVar) {
        d(gVar, this.e.isEmpty());
    }

    public void addOnTabSelectedListener(d dVar) {
        addOnTabSelectedListener((c) dVar);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final void addView(View view) {
        f(view);
    }

    public void b(g gVar, int i2, boolean z) {
        if (gVar.h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        gVar.e = i2;
        ArrayList<g> arrayList = this.e;
        arrayList.add(i2, gVar);
        int size = arrayList.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                break;
            } else {
                arrayList.get(i2).e = i2;
            }
        }
        TabView tabView = gVar.i;
        tabView.setSelected(false);
        tabView.setActivated(false);
        int i3 = gVar.e;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        if (this.D == 1 && this.A == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
        this.g.addView(tabView, i3, layoutParams);
        if (z) {
            gVar.b();
        }
    }

    public void d(g gVar, boolean z) {
        b(gVar, this.e.size(), z);
    }

    public final void f(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        TabItem tabItem = (TabItem) view;
        g gVarL = l();
        CharSequence charSequence = tabItem.e;
        if (charSequence != null) {
            gVarL.d(charSequence);
        }
        Drawable drawable = tabItem.f;
        if (drawable != null) {
            gVarL.b = drawable;
            TabLayout tabLayout = gVarL.h;
            if (tabLayout.A == 1 || tabLayout.D == 2) {
                tabLayout.u(true);
            }
            gVarL.e();
        }
        int i2 = tabItem.g;
        if (i2 != 0) {
            gVarL.c(i2);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            gVarL.d = tabItem.getContentDescription();
            gVarL.e();
        }
        a(gVarL);
    }

    public int getSelectedTabPosition() {
        g gVar = this.f;
        if (gVar != null) {
            return gVar.e;
        }
        return -1;
    }

    public int getTabCount() {
        return this.e.size();
    }

    public int getTabGravity() {
        return this.A;
    }

    public ColorStateList getTabIconTint() {
        return this.n;
    }

    public int getTabIndicatorAnimationMode() {
        return this.H;
    }

    public int getTabIndicatorGravity() {
        return this.C;
    }

    public int getTabMaxWidth() {
        return this.v;
    }

    public int getTabMode() {
        return this.D;
    }

    public ColorStateList getTabRippleColor() {
        return this.o;
    }

    public Drawable getTabSelectedIndicator() {
        return this.p;
    }

    public ColorStateList getTabTextColors() {
        return this.m;
    }

    public final void h() {
        int i2 = this.D;
        int iMax = (i2 == 0 || i2 == 2) ? Math.max(0, this.z - this.h) : 0;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        f fVar = this.g;
        om3.e.i(fVar, iMax, 0, 0, 0);
        int i3 = this.D;
        if (i3 == 0) {
            int i4 = this.A;
            if (i4 == 0) {
                Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
            } else if (i4 == 1) {
                fVar.setGravity(1);
            } else if (i4 == 2) {
            }
            fVar.setGravity(8388611);
        } else if (i3 == 1 || i3 == 2) {
            if (this.A == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            fVar.setGravity(1);
        }
        u(true);
    }

    public final int i(float f2, int i2) {
        f fVar;
        View childAt;
        int i3 = this.D;
        if ((i3 != 0 && i3 != 2) || (childAt = (fVar = this.g).getChildAt(i2)) == null) {
            return 0;
        }
        int i4 = i2 + 1;
        View childAt2 = i4 < fVar.getChildCount() ? fVar.getChildAt(i4) : null;
        int width = childAt.getWidth();
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = ((width / 2) + childAt.getLeft()) - (getWidth() / 2);
        int i5 = (int) ((width + width2) * 0.5f * f2);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return om3.e.c(this) == 0 ? left + i5 : left - i5;
    }

    public final void j() {
        if (this.N == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.N = valueAnimator;
            valueAnimator.setInterpolator(u5.b);
            this.N.setDuration(this.B);
            this.N.addUpdateListener(new a());
        }
    }

    public final g k(int i2) {
        if (i2 < 0 || i2 >= getTabCount()) {
            return null;
        }
        return this.e.get(i2);
    }

    public final g l() {
        g gVar = (g) W.a();
        if (gVar == null) {
            gVar = new g();
        }
        gVar.h = this;
        t22 t22Var = this.U;
        TabView tabView = t22Var != null ? (TabView) t22Var.a() : null;
        if (tabView == null) {
            tabView = new TabView(getContext());
        }
        tabView.setTab(gVar);
        tabView.setFocusable(true);
        tabView.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(gVar.d)) {
            tabView.setContentDescription(gVar.c);
        } else {
            tabView.setContentDescription(gVar.d);
        }
        gVar.i = tabView;
        int i2 = gVar.j;
        if (i2 != -1) {
            tabView.setId(i2);
        }
        return gVar;
    }

    public final void m() {
        int currentItem;
        n();
        kv1 kv1Var = this.P;
        if (kv1Var != null) {
            int iB = kv1Var.b();
            for (int i2 = 0; i2 < iB; i2++) {
                g gVarL = l();
                gVarL.d(this.P.c(i2));
                d(gVarL, false);
            }
            ViewPager viewPager = this.O;
            if (viewPager == null || iB <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            p(k(currentItem), true);
        }
    }

    public final void n() {
        for (int childCount = this.g.getChildCount() - 1; childCount >= 0; childCount--) {
            o(childCount);
        }
        Iterator<g> it = this.e.iterator();
        while (it.hasNext()) {
            g next = it.next();
            it.remove();
            next.h = null;
            next.i = null;
            next.a = null;
            next.b = null;
            next.j = -1;
            next.c = null;
            next.d = null;
            next.e = -1;
            next.f = null;
            W.b(next);
        }
        this.f = null;
    }

    public final void o(int i2) {
        f fVar = this.g;
        TabView tabView = (TabView) fVar.getChildAt(i2);
        fVar.removeViewAt(i2);
        if (tabView != null) {
            tabView.setTab(null);
            tabView.setSelected(false);
            this.U.b(tabView);
        }
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oj2.d0(this);
        if (this.O == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                t((ViewPager) parent, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.T) {
            setupWithViewPager(null);
            this.T = false;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        TabView tabView;
        Drawable drawable;
        int i2 = 0;
        while (true) {
            f fVar = this.g;
            if (i2 >= fVar.getChildCount()) {
                super.onDraw(canvas);
                return;
            }
            View childAt = fVar.getChildAt(i2);
            if ((childAt instanceof TabView) && (drawable = (tabView = (TabView) childAt).m) != null) {
                drawable.setBounds(tabView.getLeft(), tabView.getTop(), tabView.getRight(), tabView.getBottom());
                tabView.m.draw(canvas);
            }
            i2++;
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        new AccessibilityNodeInfoCompat(accessibilityNodeInfo).p(AccessibilityNodeInfoCompat.c.a(1, getTabCount(), 1));
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (getTabMode() == 0 || getTabMode() == 2) && super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0082  */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = defpackage.yn3.b(r0, r1)
            int r0 = java.lang.Math.round(r0)
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L2e
            if (r1 == 0) goto L1f
            goto L41
        L1f:
            int r8 = r6.getPaddingTop()
            int r8 = r8 + r0
            int r0 = r6.getPaddingBottom()
            int r0 = r0 + r8
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            goto L41
        L2e:
            int r1 = r6.getChildCount()
            if (r1 != r5) goto L41
            int r1 = android.view.View.MeasureSpec.getSize(r8)
            if (r1 < r0) goto L41
            android.view.View r1 = r6.getChildAt(r4)
            r1.setMinimumHeight(r0)
        L41:
            int r0 = android.view.View.MeasureSpec.getSize(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            if (r1 == 0) goto L5f
            int r1 = r6.x
            if (r1 <= 0) goto L50
            goto L5d
        L50:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = defpackage.yn3.b(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5d:
            r6.v = r1
        L5f:
            super.onMeasure(r7, r8)
            int r7 = r6.getChildCount()
            if (r7 != r5) goto Lad
            android.view.View r7 = r6.getChildAt(r4)
            int r0 = r6.D
            if (r0 == 0) goto L82
            if (r0 == r5) goto L76
            r1 = 2
            if (r0 == r1) goto L82
            goto L8d
        L76:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 == r1) goto L8d
        L80:
            r4 = r5
            goto L8d
        L82:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 >= r1) goto L8d
            goto L80
        L8d:
            if (r4 == 0) goto Lad
            int r0 = r6.getPaddingTop()
            int r1 = r6.getPaddingBottom()
            int r1 = r1 + r0
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            int r0 = r0.height
            int r8 = android.view.ViewGroup.getChildMeasureSpec(r8, r1, r0)
            int r6 = r6.getMeasuredWidth()
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r3)
            r7.measure(r6, r8)
        Lad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 8) {
            if (!(getTabMode() == 0 || getTabMode() == 2)) {
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void p(g gVar, boolean z) {
        g gVar2 = this.f;
        ArrayList<c> arrayList = this.L;
        if (gVar2 == gVar) {
            if (gVar2 != null) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    arrayList.get(size).a(gVar);
                }
                g(gVar.e);
                return;
            }
            return;
        }
        int i2 = gVar != null ? gVar.e : -1;
        if (z) {
            if ((gVar2 == null || gVar2.e == -1) && i2 != -1) {
                r(true, i2, true, 0.0f);
            } else {
                g(i2);
            }
            if (i2 != -1) {
                setSelectedTabView(i2);
            }
        }
        this.f = gVar;
        if (gVar2 != null) {
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                arrayList.get(size2).l(gVar2);
            }
        }
        if (gVar != null) {
            for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                arrayList.get(size3).h(gVar);
            }
        }
    }

    public final void q(kv1 kv1Var, boolean z) {
        e eVar;
        kv1 kv1Var2 = this.P;
        if (kv1Var2 != null && (eVar = this.Q) != null) {
            kv1Var2.a.unregisterObserver(eVar);
        }
        this.P = kv1Var;
        if (z && kv1Var != null) {
            if (this.Q == null) {
                this.Q = new e();
            }
            kv1Var.a.registerObserver(this.Q);
        }
        m();
    }

    public final void r(boolean z, int i2, boolean z2, float f2) {
        int iRound = Math.round(i2 + f2);
        if (iRound >= 0) {
            f fVar = this.g;
            if (iRound >= fVar.getChildCount()) {
                return;
            }
            if (z2) {
                ValueAnimator valueAnimator = fVar.e;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    fVar.e.cancel();
                }
                fVar.f = i2;
                fVar.g = f2;
                fVar.c(fVar.getChildAt(i2), fVar.getChildAt(fVar.f + 1), fVar.g);
            }
            ValueAnimator valueAnimator2 = this.N;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.N.cancel();
            }
            scrollTo(i2 < 0 ? 0 : i(f2, i2), 0);
            if (z) {
                setSelectedTabView(iRound);
            }
        }
    }

    public void removeOnTabSelectedListener(d dVar) {
        removeOnTabSelectedListener((c) dVar);
    }

    public final void s(int i2, int i3) {
        setTabTextColors(new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i3, i2}));
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        oj2.c0(this, f2);
    }

    public void setInlineLabel(boolean z) {
        if (this.E == z) {
            return;
        }
        this.E = z;
        int i2 = 0;
        while (true) {
            f fVar = this.g;
            if (i2 >= fVar.getChildCount()) {
                h();
                return;
            }
            View childAt = fVar.getChildAt(i2);
            if (childAt instanceof TabView) {
                TabView tabView = (TabView) childAt;
                tabView.setOrientation(!TabLayout.this.E ? 1 : 0);
                TextView textView = tabView.k;
                if (textView == null && tabView.l == null) {
                    tabView.f(tabView.f, tabView.g);
                } else {
                    tabView.f(textView, tabView.l);
                }
            }
            i2++;
        }
    }

    public void setInlineLabelResource(int i2) {
        setInlineLabel(getResources().getBoolean(i2));
    }

    @Deprecated
    public void setOnTabSelectedListener(d dVar) {
        setOnTabSelectedListener((c) dVar);
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        j();
        this.N.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.p != drawable) {
            if (drawable == null) {
                drawable = new GradientDrawable();
            }
            this.p = drawable;
            int intrinsicHeight = this.G;
            if (intrinsicHeight == -1) {
                intrinsicHeight = drawable.getIntrinsicHeight();
            }
            this.g.b(intrinsicHeight);
        }
    }

    public void setSelectedTabIndicatorColor(int i2) {
        this.q = i2;
        u(false);
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.C != i2) {
            this.C = i2;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(this.g);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.G = i2;
        this.g.b(i2);
    }

    public void setTabGravity(int i2) {
        if (this.A != i2) {
            this.A = i2;
            h();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            ArrayList<g> arrayList = this.e;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.get(i2).e();
            }
        }
    }

    public void setTabIconTintResource(int i2) {
        setTabIconTint(bz.b(getContext(), i2));
    }

    public void setTabIndicatorAnimationMode(int i2) {
        this.H = i2;
        if (i2 == 0) {
            this.f35J = new com.google.android.material.tabs.a();
            return;
        }
        if (i2 == 1) {
            this.f35J = new jg0();
        } else {
            if (i2 == 2) {
                this.f35J = new uj0();
                return;
            }
            throw new IllegalArgumentException(i2 + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.F = z;
        int i2 = f.i;
        f fVar = this.g;
        fVar.a();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.j(fVar);
    }

    public void setTabMode(int i2) {
        if (i2 != this.D) {
            this.D = i2;
            h();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.o == colorStateList) {
            return;
        }
        this.o = colorStateList;
        int i2 = 0;
        while (true) {
            f fVar = this.g;
            if (i2 >= fVar.getChildCount()) {
                return;
            }
            View childAt = fVar.getChildAt(i2);
            if (childAt instanceof TabView) {
                Context context = getContext();
                int i3 = TabView.p;
                ((TabView) childAt).e(context);
            }
            i2++;
        }
    }

    public void setTabRippleColorResource(int i2) {
        setTabRippleColor(bz.b(getContext(), i2));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            ArrayList<g> arrayList = this.e;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.get(i2).e();
            }
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(kv1 kv1Var) {
        q(kv1Var, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.I == z) {
            return;
        }
        this.I = z;
        int i2 = 0;
        while (true) {
            f fVar = this.g;
            if (i2 >= fVar.getChildCount()) {
                return;
            }
            View childAt = fVar.getChildAt(i2);
            if (childAt instanceof TabView) {
                Context context = getContext();
                int i3 = TabView.p;
                ((TabView) childAt).e(context);
            }
            i2++;
        }
    }

    public void setUnboundedRippleResource(int i2) {
        setUnboundedRipple(getResources().getBoolean(i2));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        t(viewPager, false);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public final void t(ViewPager viewPager, boolean z) {
        ViewPager viewPager2 = this.O;
        if (viewPager2 != null) {
            h hVar = this.R;
            if (hVar != null) {
                viewPager2.removeOnPageChangeListener(hVar);
            }
            b bVar = this.S;
            if (bVar != null) {
                this.O.removeOnAdapterChangeListener(bVar);
            }
        }
        c cVar = this.M;
        if (cVar != null) {
            removeOnTabSelectedListener(cVar);
            this.M = null;
        }
        if (viewPager != null) {
            this.O = viewPager;
            if (this.R == null) {
                this.R = new h(this);
            }
            h hVar2 = this.R;
            hVar2.g = 0;
            hVar2.f = 0;
            viewPager.addOnPageChangeListener(hVar2);
            i iVar = new i(viewPager);
            this.M = iVar;
            addOnTabSelectedListener((c) iVar);
            kv1 adapter = viewPager.getAdapter();
            if (adapter != null) {
                q(adapter, true);
            }
            if (this.S == null) {
                this.S = new b();
            }
            b bVar2 = this.S;
            bVar2.e = true;
            viewPager.addOnAdapterChangeListener(bVar2);
            r(true, viewPager.getCurrentItem(), true, 0.0f);
        } else {
            this.O = null;
            q(null, false);
        }
        this.T = z;
    }

    public final void u(boolean z) {
        int i2 = 0;
        while (true) {
            f fVar = this.g;
            if (i2 >= fVar.getChildCount()) {
                return;
            }
            View childAt = fVar.getChildAt(i2);
            childAt.setMinimumWidth(getTabMinWidth());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (this.D == 1 && this.A == 0) {
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            } else {
                layoutParams.width = -2;
                layoutParams.weight = 0.0f;
            }
            if (z) {
                childAt.requestLayout();
            }
            i2++;
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.tabStyle);
    }

    @Deprecated
    public void addOnTabSelectedListener(c cVar) {
        ArrayList<c> arrayList = this.L;
        if (arrayList.contains(cVar)) {
            return;
        }
        arrayList.add(cVar);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final void addView(View view, int i2) {
        f(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void removeOnTabSelectedListener(c cVar) {
        this.L.remove(cVar);
    }

    @Deprecated
    public void setOnTabSelectedListener(c cVar) {
        c cVar2 = this.K;
        if (cVar2 != null) {
            removeOnTabSelectedListener(cVar2);
        }
        this.K = cVar;
        if (cVar != null) {
            addOnTabSelectedListener(cVar);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TabLayout(Context context, AttributeSet attributeSet, int i2) {
        int i3 = V;
        super(uc1.a(context, attributeSet, i2, i3), attributeSet, i2);
        this.e = new ArrayList<>();
        this.p = new GradientDrawable();
        this.q = 0;
        this.v = Integer.MAX_VALUE;
        this.G = -1;
        this.L = new ArrayList<>();
        this.U = new t22(12, 1);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        f fVar = new f(context2);
        this.g = fVar;
        super.addView(fVar, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = R$styleable.TabLayout;
        int i4 = R$styleable.TabLayout_tabTextAppearance;
        TypedArray typedArrayD = r83.d(context2, attributeSet, iArr, i2, i3, i4);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            sc1 sc1Var = new sc1();
            sc1Var.n(ColorStateList.valueOf(colorDrawable.getColor()));
            sc1Var.k(context2);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            sc1Var.m(om3.i.e(this));
            om3.d.n(this, sc1Var);
        }
        setSelectedTabIndicator(rc1.d(context2, typedArrayD, R$styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorColor(typedArrayD.getColor(R$styleable.TabLayout_tabIndicatorColor, 0));
        fVar.b(typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabIndicatorHeight, -1));
        setSelectedTabIndicatorGravity(typedArrayD.getInt(R$styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorAnimationMode(typedArrayD.getInt(R$styleable.TabLayout_tabIndicatorAnimationMode, 0));
        setTabIndicatorFullWidth(typedArrayD.getBoolean(R$styleable.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPadding, 0);
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        this.i = dimensionPixelSize;
        this.h = dimensionPixelSize;
        this.h = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.i = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingTop, dimensionPixelSize);
        this.j = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingEnd, dimensionPixelSize);
        this.k = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingBottom, dimensionPixelSize);
        int resourceId = typedArrayD.getResourceId(i4, R$style.TextAppearance_Design_Tab);
        this.l = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, androidx.appcompat.R.styleable.TextAppearance);
        try {
            this.s = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R.styleable.TextAppearance_android_textSize, 0);
            this.m = rc1.a(context2, typedArrayObtainStyledAttributes, androidx.appcompat.R.styleable.TextAppearance_android_textColor);
            typedArrayObtainStyledAttributes.recycle();
            int i5 = R$styleable.TabLayout_tabTextColor;
            if (typedArrayD.hasValue(i5)) {
                this.m = rc1.a(context2, typedArrayD, i5);
            }
            int i6 = R$styleable.TabLayout_tabSelectedTextColor;
            if (typedArrayD.hasValue(i6)) {
                this.m = new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{typedArrayD.getColor(i6, 0), this.m.getDefaultColor()});
            }
            this.n = rc1.a(context2, typedArrayD, R$styleable.TabLayout_tabIconTint);
            this.r = yn3.e(typedArrayD.getInt(R$styleable.TabLayout_tabIconTintMode, -1), null);
            this.o = rc1.a(context2, typedArrayD, R$styleable.TabLayout_tabRippleColor);
            this.B = typedArrayD.getInt(R$styleable.TabLayout_tabIndicatorAnimationDuration, 300);
            this.w = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabMinWidth, -1);
            this.x = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabMaxWidth, -1);
            this.u = typedArrayD.getResourceId(R$styleable.TabLayout_tabBackground, 0);
            this.z = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabContentStart, 0);
            this.D = typedArrayD.getInt(R$styleable.TabLayout_tabMode, 1);
            this.A = typedArrayD.getInt(R$styleable.TabLayout_tabGravity, 0);
            this.E = typedArrayD.getBoolean(R$styleable.TabLayout_tabInlineLabel, false);
            this.I = typedArrayD.getBoolean(R$styleable.TabLayout_tabUnboundedRipple, false);
            typedArrayD.recycle();
            Resources resources = getResources();
            this.t = resources.getDimensionPixelSize(R$dimen.design_tab_text_size_2line);
            this.y = resources.getDimensionPixelSize(R$dimen.design_tab_scrollable_min_width);
            h();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        f(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        f(view);
    }

    public void setSelectedTabIndicator(int i2) {
        if (i2 != 0) {
            setSelectedTabIndicator(AppCompatResources.a(getContext(), i2));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    public static class i implements d {
        public final ViewPager e;

        public i(ViewPager viewPager) {
            this.e = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void h(g gVar) {
            this.e.setCurrentItem(gVar.e);
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void a(g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void l(g gVar) {
        }
    }
}
