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
import androidx.appcompat.widget.LinearLayoutCompat;
import b.b.k.a;
import b.b.q.e0;
import b.b.q.g0;

/* JADX INFO: loaded from: classes.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Interpolator f487b = new DecelerateInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f488c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f489d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LinearLayoutCompat f490e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Spinner f491f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f492g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f493h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f494i;
    public int j;
    public int k;
    public ViewPropertyAnimator l;
    public final e m;

    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f495b;

        public a(View view) {
            this.f495b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScrollingTabContainerView.this.smoothScrollTo(this.f495b.getLeft() - ((ScrollingTabContainerView.this.getWidth() - this.f495b.getWidth()) / 2), 0);
            ScrollingTabContainerView.this.f488c = null;
        }
    }

    public class b extends BaseAdapter {
        public b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ScrollingTabContainerView.this.f490e.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return ((d) ScrollingTabContainerView.this.f490e.getChildAt(i2)).b();
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

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((d) view).b().e();
            int childCount = ScrollingTabContainerView.this.f490e.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = ScrollingTabContainerView.this.f490e.getChildAt(i2);
                childAt.setSelected(childAt == view);
            }
        }
    }

    public class d extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int[] f499b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public a.c f500c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f501d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ImageView f502e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f503f;

        /* JADX WARN: Illegal instructions before constructor call */
        public d(Context context, a.c cVar, boolean z) {
            int i2 = b.b.a.actionBarTabStyle;
            super(context, null, i2);
            int[] iArr = {R.attr.background};
            this.f499b = iArr;
            this.f500c = cVar;
            e0 e0VarV = e0.v(context, null, iArr, i2, 0);
            if (e0VarV.s(0)) {
                setBackgroundDrawable(e0VarV.g(0));
            }
            e0VarV.w();
            if (z) {
                setGravity(8388627);
            }
            c();
        }

        public void a(a.c cVar) {
            this.f500c = cVar;
            c();
        }

        public a.c b() {
            return this.f500c;
        }

        public void c() {
            a.c cVar = this.f500c;
            View viewB = cVar.b();
            if (viewB != null) {
                ViewParent parent = viewB.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewB);
                    }
                    addView(viewB);
                }
                this.f503f = viewB;
                TextView textView = this.f501d;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f502e;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f502e.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f503f;
            if (view != null) {
                removeView(view);
                this.f503f = null;
            }
            Drawable drawableC = cVar.c();
            CharSequence charSequenceD = cVar.d();
            if (drawableC != null) {
                if (this.f502e == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.f502e = appCompatImageView;
                }
                this.f502e.setImageDrawable(drawableC);
                this.f502e.setVisibility(0);
            } else {
                ImageView imageView2 = this.f502e;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f502e.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(charSequenceD);
            if (z) {
                if (this.f501d == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, b.b.a.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.f501d = appCompatTextView;
                }
                this.f501d.setText(charSequenceD);
                this.f501d.setVisibility(0);
            } else {
                TextView textView2 = this.f501d;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f501d.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f502e;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.a());
            }
            g0.a(this, z ? null : cVar.a());
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
            if (ScrollingTabContainerView.this.f493h > 0) {
                int measuredWidth = getMeasuredWidth();
                int i4 = ScrollingTabContainerView.this.f493h;
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

    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f505a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f506b;

        public e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f505a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f505a) {
                return;
            }
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            scrollingTabContainerView.l = null;
            scrollingTabContainerView.setVisibility(this.f506b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.f505a = false;
        }
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        this.m = new e();
        setHorizontalScrollBarEnabled(false);
        b.b.p.a aVarB = b.b.p.a.b(context);
        setContentHeight(aVarB.f());
        this.f494i = aVarB.e();
        LinearLayoutCompat linearLayoutCompatC = c();
        this.f490e = linearLayoutCompatC;
        addView(linearLayoutCompatC, new ViewGroup.LayoutParams(-2, -1));
    }

    public void a(int i2) {
        View childAt = this.f490e.getChildAt(i2);
        Runnable runnable = this.f488c;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.f488c = aVar;
        post(aVar);
    }

    public final Spinner b() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, b.b.a.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    public final LinearLayoutCompat c() {
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, b.b.a.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        return linearLayoutCompat;
    }

    public d d(a.c cVar, boolean z) {
        d dVar = new d(getContext(), cVar, z);
        if (z) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.j));
        } else {
            dVar.setFocusable(true);
            if (this.f489d == null) {
                this.f489d = new c();
            }
            dVar.setOnClickListener(this.f489d);
        }
        return dVar;
    }

    public final boolean e() {
        Spinner spinner = this.f491f;
        return spinner != null && spinner.getParent() == this;
    }

    public final void f() {
        if (e()) {
            return;
        }
        if (this.f491f == null) {
            this.f491f = b();
        }
        removeView(this.f490e);
        addView(this.f491f, new ViewGroup.LayoutParams(-2, -1));
        if (this.f491f.getAdapter() == null) {
            this.f491f.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f488c;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f488c = null;
        }
        this.f491f.setSelection(this.k);
    }

    public final boolean g() {
        if (!e()) {
            return false;
        }
        removeView(this.f491f);
        addView(this.f490e, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f491f.getSelectedItemPosition());
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f488c;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b.b.p.a aVarB = b.b.p.a.b(getContext());
        setContentHeight(aVarB.f());
        this.f494i = aVarB.e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f488c;
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
        int childCount = this.f490e.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f493h = -1;
        } else {
            if (childCount > 2) {
                this.f493h = (int) (View.MeasureSpec.getSize(i2) * 0.4f);
            } else {
                this.f493h = View.MeasureSpec.getSize(i2) / 2;
            }
            this.f493h = Math.min(this.f493h, this.f494i);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.j, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (!z && this.f492g) {
            this.f490e.measure(0, iMakeMeasureSpec);
            if (this.f490e.getMeasuredWidth() > View.MeasureSpec.getSize(i2)) {
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
        this.f492g = z;
    }

    public void setContentHeight(int i2) {
        this.j = i2;
        requestLayout();
    }

    public void setTabSelected(int i2) {
        this.k = i2;
        int childCount = this.f490e.getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = this.f490e.getChildAt(i3);
            boolean z = i3 == i2;
            childAt.setSelected(z);
            if (z) {
                a(i2);
            }
            i3++;
        }
        Spinner spinner = this.f491f;
        if (spinner == null || i2 < 0) {
            return;
        }
        spinner.setSelection(i2);
    }
}
