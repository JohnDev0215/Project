package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.spotify.music.R;
import java.util.WeakHashMap;
import p.c4;
import p.ev2;
import p.g4;
import p.it2;
import p.l4;
import p.qj1;
import p.si1;
import p.t3;
import p.tu2;
import p.v0;
import p.v4;
import p.yz1;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends ViewGroup {
    public boolean A;
    public final int B;
    public final v0 c;
    public final Context e;
    public ActionMenuView k;
    public g4 l;
    public int m;
    public tu2 n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f7p;
    public CharSequence q;
    public CharSequence r;
    public View s;
    public View t;
    public View u;
    public LinearLayout v;
    public TextView w;
    public TextView x;
    public final int y;
    public final int z;

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.actionModeStyle);
        this.c = new v0(this);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.e = context;
        } else {
            this.e = new ContextThemeWrapper(context, typedValue.resourceId);
        }
        v4 v4Var = new v4(context, context.obtainStyledAttributes(attributeSet, yz1.d, R.attr.actionModeStyle, 0));
        Drawable drawableT = v4Var.t(0);
        WeakHashMap weakHashMap = zt2.a;
        it2.q(this, drawableT);
        this.y = v4Var.y(5, 0);
        this.z = v4Var.y(4, 0);
        this.m = ((TypedArray) v4Var.k).getLayoutDimension(3, 0);
        this.B = v4Var.y(2, R.layout.abc_action_mode_close_item_material);
        v4Var.L();
    }

    public static int f(View view, int i, int i2) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, i - view.getMeasuredWidth());
    }

    public static int j(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public final void c(l4 l4Var) {
        View view = this.s;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.B, (ViewGroup) this, false);
            this.s = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.s);
        }
        View viewFindViewById = this.s.findViewById(R.id.action_mode_close_button);
        this.t = viewFindViewById;
        viewFindViewById.setOnClickListener(new t3(this, l4Var));
        si1 si1VarC = l4Var.c();
        g4 g4Var = this.l;
        if (g4Var != null) {
            g4Var.c();
            c4 c4Var = g4Var.B;
            if (c4Var != null && c4Var.b()) {
                c4Var.j.dismiss();
            }
        }
        g4 g4Var2 = new g4(getContext());
        this.l = g4Var2;
        g4Var2.t = true;
        g4Var2.u = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        si1VarC.b(this.l, this.e);
        g4 g4Var3 = this.l;
        qj1 qj1Var = g4Var3.f122p;
        if (qj1Var == null) {
            qj1 qj1Var2 = (qj1) g4Var3.l.inflate(g4Var3.n, (ViewGroup) this, false);
            g4Var3.f122p = qj1Var2;
            qj1Var2.b(g4Var3.k);
            g4Var3.d();
        }
        qj1 qj1Var3 = g4Var3.f122p;
        if (qj1Var != qj1Var3) {
            ((ActionMenuView) qj1Var3).setPresenter(g4Var3);
        }
        ActionMenuView actionMenuView = (ActionMenuView) qj1Var3;
        this.k = actionMenuView;
        WeakHashMap weakHashMap = zt2.a;
        it2.q(actionMenuView, null);
        addView(this.k, layoutParams);
    }

    public final void d() {
        if (this.v == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.v = linearLayout;
            this.w = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.x = (TextView) this.v.findViewById(R.id.action_bar_subtitle);
            int i = this.y;
            if (i != 0) {
                this.w.setTextAppearance(getContext(), i);
            }
            int i2 = this.z;
            if (i2 != 0) {
                this.x.setTextAppearance(getContext(), i2);
            }
        }
        this.w.setText(this.q);
        this.x.setText(this.r);
        boolean z = !TextUtils.isEmpty(this.q);
        boolean z2 = !TextUtils.isEmpty(this.r);
        this.x.setVisibility(z2 ? 0 : 8);
        this.v.setVisibility((z || z2) ? 0 : 8);
        if (this.v.getParent() == null) {
            addView(this.v);
        }
    }

    public final void e() {
        removeAllViews();
        this.u = null;
        this.k = null;
        this.l = null;
        View view = this.t;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, yz1.a, R.attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(13, 0));
        typedArrayObtainStyledAttributes.recycle();
        g4 g4Var = this.l;
        if (g4Var != null) {
            Configuration configuration2 = g4Var.e.getResources().getConfiguration();
            int i = configuration2.screenWidthDp;
            int i2 = configuration2.screenHeightDp;
            g4Var.x = (configuration2.smallestScreenWidthDp > 600 || i > 600 || (i > 960 && i2 > 720) || (i > 720 && i2 > 960)) ? 5 : (i >= 500 || (i > 640 && i2 > 480) || (i > 480 && i2 > 640)) ? 4 : i >= 360 ? 3 : 2;
            si1 si1Var = g4Var.k;
            if (si1Var != null) {
                si1Var.p(true);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public int getAnimatedVisibility() {
        return this.n != null ? this.c.b : getVisibility();
    }

    public int getContentHeight() {
        return this.m;
    }

    public CharSequence getSubtitle() {
        return this.r;
    }

    public CharSequence getTitle() {
        return this.q;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f7p = false;
        }
        if (!this.f7p) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f7p = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f7p = false;
        }
        return true;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.o = false;
        }
        if (!this.o) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.o = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.o = false;
        }
        return true;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void setVisibility(int i) {
        if (i != getVisibility()) {
            tu2 tu2Var = this.n;
            if (tu2Var != null) {
                tu2Var.b();
            }
            super.setVisibility(i);
        }
    }

    public final tu2 l(int i, long j) {
        tu2 tu2Var = this.n;
        if (tu2Var != null) {
            tu2Var.b();
        }
        v0 v0Var = this.c;
        if (i != 0) {
            tu2 tu2VarA = zt2.a(this);
            tu2VarA.a(0.0f);
            tu2VarA.c(j);
            v0Var.c.n = tu2VarA;
            v0Var.b = i;
            tu2VarA.d(v0Var);
            return tu2VarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        tu2 tu2VarA2 = zt2.a(this);
        tu2VarA2.a(1.0f);
        tu2VarA2.c(j);
        v0Var.c.n = tu2VarA2;
        v0Var.b = i;
        tu2VarA2.d(v0Var);
        return tu2VarA2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        g4 g4Var = this.l;
        if (g4Var != null) {
            g4Var.c();
            c4 c4Var = this.l.B;
            if (c4Var == null || !c4Var.b()) {
                return;
            }
            c4Var.j.dismiss();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean zA = ev2.a(this);
        int paddingRight = zA ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.s;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.s.getLayoutParams();
            int i5 = zA ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = zA ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i7 = zA ? paddingRight - i5 : paddingRight + i5;
            int iJ = j(this.s, i7, paddingTop, paddingTop2, zA) + i7;
            paddingRight = zA ? iJ - i6 : iJ + i6;
        }
        LinearLayout linearLayout = this.v;
        if (linearLayout != null && this.u == null && linearLayout.getVisibility() != 8) {
            paddingRight += j(this.v, paddingRight, paddingTop, paddingTop2, zA);
        }
        View view2 = this.u;
        if (view2 != null) {
            j(view2, paddingRight, paddingTop, paddingTop2, zA);
        }
        int paddingLeft = zA ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.k;
        if (actionMenuView != null) {
            j(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zA);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        }
        if (View.MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = this.m;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i2);
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingBottom;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.s;
        if (view != null) {
            int iF = f(view, paddingLeft, iMakeMeasureSpec);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.s.getLayoutParams();
            paddingLeft = iF - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.k;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = f(this.k, paddingLeft, iMakeMeasureSpec);
        }
        LinearLayout linearLayout = this.v;
        if (linearLayout != null && this.u == null) {
            if (this.A) {
                this.v.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.v.getMeasuredWidth();
                boolean z = measuredWidth <= paddingLeft;
                if (z) {
                    paddingLeft -= measuredWidth;
                }
                this.v.setVisibility(z ? 0 : 8);
            } else {
                paddingLeft = f(linearLayout, paddingLeft, iMakeMeasureSpec);
            }
        }
        View view2 = this.u;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i3 = layoutParams.width;
            int i4 = i3 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i3 >= 0) {
                paddingLeft = Math.min(i3, paddingLeft);
            }
            int i5 = layoutParams.height;
            int i6 = i5 == -2 ? Integer.MIN_VALUE : 1073741824;
            if (i5 >= 0) {
                iMin = Math.min(i5, iMin);
            }
            this.u.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i4), View.MeasureSpec.makeMeasureSpec(iMin, i6));
        }
        if (this.m > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            int measuredHeight = getChildAt(i8).getMeasuredHeight() + paddingBottom;
            if (measuredHeight > i7) {
                i7 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i7);
    }

    public void setContentHeight(int i) {
        this.m = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.u;
        if (view2 != null) {
            removeView(view2);
        }
        this.u = view;
        if (view != null && (linearLayout = this.v) != null) {
            removeView(linearLayout);
            this.v = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.r = charSequence;
        d();
    }

    public void setTitle(CharSequence charSequence) {
        this.q = charSequence;
        d();
        zt2.l(this, charSequence);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.A) {
            requestLayout();
        }
        this.A = z;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
