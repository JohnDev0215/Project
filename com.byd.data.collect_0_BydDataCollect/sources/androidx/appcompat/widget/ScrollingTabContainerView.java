package androidx.appcompat.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.a;
import androidx.appcompat.widget.LinearLayoutCompat;

/* JADX INFO: loaded from: classes.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: b */
    private static final Interpolator f490b = new DecelerateInterpolator();

    /* JADX INFO: renamed from: c */
    Runnable f491c;

    /* JADX INFO: renamed from: d */
    private c f492d;

    /* JADX INFO: renamed from: e */
    LinearLayoutCompat f493e;

    /* JADX INFO: renamed from: f */
    private Spinner f494f;

    /* JADX INFO: renamed from: g */
    private boolean f495g;

    /* JADX INFO: renamed from: h */
    int f496h;

    /* JADX INFO: renamed from: i */
    int f497i;
    private int j;
    private int k;
    protected ViewPropertyAnimator l;
    protected final e m;

    class a implements Runnable {

        /* JADX INFO: renamed from: b */
        final /* synthetic */ View f498b;

        a(View view) {
            this.f498b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScrollingTabContainerView.this.smoothScrollTo(this.f498b.getLeft() - ((ScrollingTabContainerView.this.getWidth() - this.f498b.getWidth()) / 2), 0);
            ScrollingTabContainerView.this.f491c = null;
        }
    }

    private class b extends BaseAdapter {
        b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ScrollingTabContainerView.this.f493e.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return ((d) ScrollingTabContainerView.this.f493e.getChildAt(i2)).b();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                return ScrollingTabContainerView.this.d((a.c) getItem(i2), true);
            }
            ((d) view).a((a.c) getItem(i2));
            return view;
        }
    }

    private class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((d) view).b().e();
            int childCount = ScrollingTabContainerView.this.f493e.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = ScrollingTabContainerView.this.f493e.getChildAt(i2);
                childAt.setSelected(childAt == view);
            }
        }
    }

    private class d extends LinearLayout {

        /* JADX INFO: renamed from: b */
        private final int[] f502b;

        /* JADX INFO: renamed from: c */
        private a.c f503c;

        /* JADX INFO: renamed from: d */
        private TextView f504d;

        /* JADX INFO: renamed from: e */
        private ImageView f505e;

        /* JADX INFO: renamed from: f */
        private View f506f;

        /* JADX WARN: Illegal instructions before constructor call */
        public d(Context context, a.c cVar, boolean z) {
            int i2 = b.a.a.actionBarTabStyle;
            super(context, null, i2);
            int[] iArr = {R.attr.background};
            this.f502b = iArr;
            this.f503c = cVar;
            f0 f0VarV = f0.v(context, null, iArr, i2, 0);
            if (f0VarV.s(0)) {
                setBackgroundDrawable(f0VarV.g(0));
            }
            f0VarV.w();
            if (z) {
                setGravity(8388627);
            }
            c();
        }

        public void a(a.c cVar) {
            this.f503c = cVar;
            c();
        }

        public a.c b() {
            return this.f503c;
        }

        public void c() {
            a.c cVar = this.f503c;
            View viewB = cVar.b();
            if (viewB != null) {
                ViewParent parent = viewB.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewB);
                    }
                    addView(viewB);
                }
                this.f506f = viewB;
                TextView textView = this.f504d;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f505e;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f505e.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f506f;
            if (view != null) {
                removeView(view);
                this.f506f = null;
            }
            Drawable drawableC = cVar.c();
            CharSequence charSequenceD = cVar.d();
            if (drawableC != null) {
                if (this.f505e == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.f505e = appCompatImageView;
                }
                this.f505e.setImageDrawable(drawableC);
                this.f505e.setVisibility(0);
            } else {
                ImageView imageView2 = this.f505e;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f505e.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(charSequenceD);
            if (z) {
                if (this.f504d == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, b.a.a.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.f504d = appCompatTextView;
                }
                this.f504d.setText(charSequenceD);
                this.f504d.setVisibility(0);
            } else {
                TextView textView2 = this.f504d;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f504d.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f505e;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.a());
            }
            h0.a(this, z ? null : cVar.a());
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            if (ScrollingTabContainerView.this.f496h > 0) {
                int measuredWidth = getMeasuredWidth();
                int i4 = ScrollingTabContainerView.this.f496h;
                if (measuredWidth > i4) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i4, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i3);
                }
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }
    }

    protected class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a */
        private boolean f508a = false;

        /* JADX INFO: renamed from: b */
        private int f509b;

        protected e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f508a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f508a) {
                return;
            }
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            scrollingTabContainerView.l = null;
            scrollingTabContainerView.setVisibility(this.f509b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.f508a = false;
        }
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        this.m = new e();
        setHorizontalScrollBarEnabled(false);
        b.a.o.a aVarB = b.a.o.a.b(context);
        setContentHeight(aVarB.f());
        this.f497i = aVarB.e();
        LinearLayoutCompat linearLayoutCompatC = c();
        this.f493e = linearLayoutCompatC;
        addView(linearLayoutCompatC, new ViewGroup.LayoutParams(-2, -1));
    }

    private Spinner b() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, b.a.a.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    private LinearLayoutCompat c() {
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, b.a.a.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        return linearLayoutCompat;
    }

    private boolean e() {
        Spinner spinner = this.f494f;
        return spinner != null && spinner.getParent() == this;
    }

    private void f() {
        if (e()) {
            return;
        }
        if (this.f494f == null) {
            this.f494f = b();
        }
        removeView(this.f493e);
        addView(this.f494f, new ViewGroup.LayoutParams(-2, -1));
        if (this.f494f.getAdapter() == null) {
            this.f494f.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f491c;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f491c = null;
        }
        this.f494f.setSelection(this.k);
    }

    private boolean g() {
        if (!e()) {
            return false;
        }
        removeView(this.f494f);
        addView(this.f493e, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f494f.getSelectedItemPosition());
        return false;
    }

    public void a(int i2) {
        View childAt = this.f493e.getChildAt(i2);
        Runnable runnable = this.f491c;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.f491c = aVar;
        post(aVar);
    }

    d d(a.c cVar, boolean z) {
        d dVar = new d(getContext(), cVar, z);
        if (z) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.j));
        } else {
            dVar.setFocusable(true);
            if (this.f492d == null) {
                this.f492d = new c();
            }
            dVar.setOnClickListener(this.f492d);
        }
        return dVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f491c;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b.a.o.a aVarB = b.a.o.a.b(getContext());
        setContentHeight(aVarB.f());
        this.f497i = aVarB.e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f491c;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
        ((d) view).b().e();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.f493e.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f496h = -1;
        } else {
            if (childCount > 2) {
                this.f496h = (int) (View.MeasureSpec.getSize(i2) * 0.4f);
            } else {
                this.f496h = View.MeasureSpec.getSize(i2) / 2;
            }
            this.f496h = Math.min(this.f496h, this.f497i);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.j, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (!z && this.f495g) {
            this.f493e.measure(0, iMakeMeasureSpec);
            if (this.f493e.getMeasuredWidth() > View.MeasureSpec.getSize(i2)) {
                f();
            } else {
                g();
            }
        } else {
            g();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i2, iMakeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.k);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.f495g = z;
    }

    public void setContentHeight(int i2) {
        this.j = i2;
        requestLayout();
    }

    public void setTabSelected(int i2) {
        this.k = i2;
        int childCount = this.f493e.getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = this.f493e.getChildAt(i3);
            boolean z = i3 == i2;
            childAt.setSelected(z);
            if (z) {
                a(i2);
            }
            i3++;
        }
        Spinner spinner = this.f494f;
        if (spinner == null || i2 < 0) {
            return;
        }
        spinner.setSelection(i2);
    }
}
