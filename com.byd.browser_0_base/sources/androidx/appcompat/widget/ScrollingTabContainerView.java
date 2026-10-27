package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.widget.LinearLayoutCompat;

/* JADX INFO: loaded from: classes.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    public Runnable e;
    public final LinearLayoutCompat f;
    public AppCompatSpinner g;
    public boolean h;
    public int i;
    public int j;
    public int k;
    public int l;

    public class TabAdapter extends BaseAdapter {
        public TabAdapter() {
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return ScrollingTabContainerView.this.f.getChildCount();
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            return ((TabView) ScrollingTabContainerView.this.f.getChildAt(i)).e;
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            if (view != null) {
                TabView tabView = (TabView) view;
                tabView.e = (ActionBar.Tab) getItem(i);
                tabView.a();
                return view;
            }
            ActionBar.Tab tab = (ActionBar.Tab) getItem(i);
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            scrollingTabContainerView.getClass();
            TabView tabView2 = scrollingTabContainerView.new TabView(scrollingTabContainerView.getContext(), tab);
            tabView2.setBackgroundDrawable(null);
            tabView2.setLayoutParams(new AbsListView.LayoutParams(-1, scrollingTabContainerView.k));
            return tabView2;
        }
    }

    public class TabClickListener implements View.OnClickListener {
        public TabClickListener() {
            throw null;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ((TabView) view).e.e();
            throw null;
        }
    }

    public class TabView extends LinearLayout {
        public ActionBar.Tab e;
        public AppCompatTextView f;
        public View g;

        /* JADX WARN: Illegal instructions before constructor call */
        public TabView(Context context, ActionBar.Tab tab) {
            int i = R.attr.actionBarTabStyle;
            super(context, null, i);
            int[] iArr = {android.R.attr.background};
            this.e = tab;
            TintTypedArray tintTypedArray = new TintTypedArray(context, context.obtainStyledAttributes(null, iArr, i, 0));
            if (tintTypedArray.l(0)) {
                setBackgroundDrawable(tintTypedArray.e(0));
            }
            tintTypedArray.n();
            setGravity(8388627);
            a();
        }

        public final void a() {
            ActionBar.Tab tab = this.e;
            tab.b();
            View view = this.g;
            if (view != null) {
                removeView(view);
                this.g = null;
            }
            tab.c();
            tab.d();
            boolean z = !TextUtils.isEmpty(null);
            if (z) {
                if (this.f == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, R.attr.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams);
                    addView(appCompatTextView);
                    this.f = appCompatTextView;
                }
                this.f.setText((CharSequence) null);
                this.f.setVisibility(0);
            } else {
                AppCompatTextView appCompatTextView2 = this.f;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setVisibility(8);
                    this.f.setText((CharSequence) null);
                }
            }
            if (!z) {
                tab.a();
            }
            setTooltipText(null);
        }

        @Override // android.view.View
        public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.view.View
        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public final void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            if (scrollingTabContainerView.i > 0) {
                int measuredWidth = getMeasuredWidth();
                int i3 = scrollingTabContainerView.i;
                if (measuredWidth > i3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i2);
                }
            }
        }

        @Override // android.view.View
        public final void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }
    }

    public class VisibilityAnimListener extends AnimatorListenerAdapter {
        public boolean e = false;

        public VisibilityAnimListener() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.e = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.e) {
                return;
            }
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            scrollingTabContainerView.getClass();
            scrollingTabContainerView.setVisibility(0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.e = false;
        }
    }

    static {
        new DecelerateInterpolator();
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        new VisibilityAnimListener();
        setHorizontalScrollBarEnabled(false);
        setContentHeight(new ActionBarPolicy(context).b());
        this.j = context.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, R.attr.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        this.f = linearLayoutCompat;
        addView(linearLayoutCompat, new ViewGroup.LayoutParams(-2, -1));
    }

    public final void a() {
        AppCompatSpinner appCompatSpinner = this.g;
        if (appCompatSpinner != null && appCompatSpinner.getParent() == this) {
            removeView(this.g);
            addView(this.f, new ViewGroup.LayoutParams(-2, -1));
            setTabSelected(this.g.getSelectedItemPosition());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.e;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Context context = getContext();
        setContentHeight(new ActionBarPolicy(context).b());
        this.j = context.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.e;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ((TabView) view).e.e();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        LinearLayoutCompat linearLayoutCompat = this.f;
        int childCount = linearLayoutCompat.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.i = -1;
        } else {
            if (childCount > 2) {
                this.i = (int) (View.MeasureSpec.getSize(i) * 0.4f);
            } else {
                this.i = View.MeasureSpec.getSize(i) / 2;
            }
            this.i = Math.min(this.i, this.j);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.k, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (!z && this.h) {
            linearLayoutCompat.measure(0, iMakeMeasureSpec);
            if (linearLayoutCompat.getMeasuredWidth() > View.MeasureSpec.getSize(i)) {
                AppCompatSpinner appCompatSpinner = this.g;
                if (!(appCompatSpinner != null && appCompatSpinner.getParent() == this)) {
                    if (this.g == null) {
                        AppCompatSpinner appCompatSpinner2 = new AppCompatSpinner(getContext(), (AttributeSet) null, R.attr.actionDropDownStyle);
                        appCompatSpinner2.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
                        appCompatSpinner2.setOnItemSelectedListener(this);
                        this.g = appCompatSpinner2;
                    }
                    removeView(linearLayoutCompat);
                    addView(this.g, new ViewGroup.LayoutParams(-2, -1));
                    if (this.g.getAdapter() == null) {
                        this.g.setAdapter((SpinnerAdapter) new TabAdapter());
                    }
                    Runnable runnable = this.e;
                    if (runnable != null) {
                        removeCallbacks(runnable);
                        this.e = null;
                    }
                    this.g.setSelection(this.l);
                }
            } else {
                a();
            }
        } else {
            a();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, iMakeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.l);
    }

    public void setAllowCollapse(boolean z) {
        this.h = z;
    }

    public void setContentHeight(int i) {
        this.k = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.l = i;
        LinearLayoutCompat linearLayoutCompat = this.f;
        int childCount = linearLayoutCompat.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = linearLayoutCompat.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                final View childAt2 = linearLayoutCompat.getChildAt(i);
                Runnable runnable = this.e;
                if (runnable != null) {
                    removeCallbacks(runnable);
                }
                Runnable runnable2 = new Runnable() { // from class: androidx.appcompat.widget.ScrollingTabContainerView.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        View view = childAt2;
                        int left = view.getLeft();
                        ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
                        scrollingTabContainerView.smoothScrollTo(left - ((scrollingTabContainerView.getWidth() - view.getWidth()) / 2), 0);
                        scrollingTabContainerView.e = null;
                    }
                };
                this.e = runnable2;
                post(runnable2);
            }
            i2++;
        }
        AppCompatSpinner appCompatSpinner = this.g;
        if (appCompatSpinner == null || i < 0) {
            return;
        }
        appCompatSpinner.setSelection(i);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }
}
