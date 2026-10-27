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
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.LinearLayoutCompat;
import l.a;
import r.e0;
import r.g0;

/* JADX INFO: loaded from: classes.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Interpolator f663d = new DecelerateInterpolator();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f664e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f665f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public LinearLayoutCompat f666g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Spinner f667h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f668i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f669j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f670k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f671l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f672m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ViewPropertyAnimator f673n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final e f674o;

    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f675d;

        public a(View view) {
            this.f675d = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScrollingTabContainerView.this.smoothScrollTo(this.f675d.getLeft() - ((ScrollingTabContainerView.this.getWidth() - this.f675d.getWidth()) / 2), 0);
            ScrollingTabContainerView.this.f664e = null;
        }
    }

    public class b extends BaseAdapter {
        public b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ScrollingTabContainerView.this.f666g.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i8) {
            return ((d) ScrollingTabContainerView.this.f666g.getChildAt(i8)).b();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i8) {
            return i8;
        }

        @Override // android.widget.Adapter
        public View getView(int i8, View view, ViewGroup viewGroup) {
            if (view == null) {
                return ScrollingTabContainerView.this.d((a.c) getItem(i8), true);
            }
            ((d) view).a((a.c) getItem(i8));
            return view;
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((d) view).b().e();
            int childCount = ScrollingTabContainerView.this.f666g.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = ScrollingTabContainerView.this.f666g.getChildAt(i8);
                childAt.setSelected(childAt == view);
            }
        }
    }

    public class d extends LinearLayout {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int[] f679d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public a.c f680e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public TextView f681f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ImageView f682g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public View f683h;

        /* JADX WARN: Illegal instructions before constructor call */
        public d(Context context, a.c cVar, boolean z7) {
            int i8 = R$attr.actionBarTabStyle;
            super(context, null, i8);
            int[] iArr = {R.attr.background};
            this.f679d = iArr;
            this.f680e = cVar;
            e0 e0VarV = e0.v(context, null, iArr, i8, 0);
            if (e0VarV.s(0)) {
                setBackgroundDrawable(e0VarV.g(0));
            }
            e0VarV.w();
            if (z7) {
                setGravity(8388627);
            }
            c();
        }

        public void a(a.c cVar) {
            this.f680e = cVar;
            c();
        }

        public a.c b() {
            return this.f680e;
        }

        public void c() {
            a.c cVar = this.f680e;
            View viewB = cVar.b();
            if (viewB != null) {
                ViewParent parent = viewB.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewB);
                    }
                    addView(viewB);
                }
                this.f683h = viewB;
                TextView textView = this.f681f;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f682g;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f682g.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f683h;
            if (view != null) {
                removeView(view);
                this.f683h = null;
            }
            Drawable drawableC = cVar.c();
            CharSequence charSequenceD = cVar.d();
            if (drawableC != null) {
                if (this.f682g == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.f682g = appCompatImageView;
                }
                this.f682g.setImageDrawable(drawableC);
                this.f682g.setVisibility(0);
            } else {
                ImageView imageView2 = this.f682g;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f682g.setImageDrawable(null);
                }
            }
            boolean z7 = !TextUtils.isEmpty(charSequenceD);
            if (z7) {
                if (this.f681f == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, R$attr.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.f681f = appCompatTextView;
                }
                this.f681f.setText(charSequenceD);
                this.f681f.setVisibility(0);
            } else {
                TextView textView2 = this.f681f;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f681f.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f682g;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.a());
            }
            g0.a(this, z7 ? null : cVar.a());
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
        public void onMeasure(int i8, int i9) {
            super.onMeasure(i8, i9);
            if (ScrollingTabContainerView.this.f669j > 0) {
                int measuredWidth = getMeasuredWidth();
                int i10 = ScrollingTabContainerView.this.f669j;
                if (measuredWidth > i10) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i9);
                }
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z7) {
            boolean z8 = isSelected() != z7;
            super.setSelected(z7);
            if (z8 && z7) {
                sendAccessibilityEvent(4);
            }
        }
    }

    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f685d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f686e;

        public e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f685d = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f685d) {
                return;
            }
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            scrollingTabContainerView.f673n = null;
            scrollingTabContainerView.setVisibility(this.f686e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.f685d = false;
        }
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        this.f674o = new e();
        setHorizontalScrollBarEnabled(false);
        p.a aVarB = p.a.b(context);
        setContentHeight(aVarB.f());
        this.f670k = aVarB.e();
        LinearLayoutCompat linearLayoutCompatC = c();
        this.f666g = linearLayoutCompatC;
        addView(linearLayoutCompatC, new ViewGroup.LayoutParams(-2, -1));
    }

    public void a(int i8) {
        View childAt = this.f666g.getChildAt(i8);
        Runnable runnable = this.f664e;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.f664e = aVar;
        post(aVar);
    }

    public final Spinner b() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, R$attr.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    public final LinearLayoutCompat c() {
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, R$attr.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        return linearLayoutCompat;
    }

    public d d(a.c cVar, boolean z7) {
        d dVar = new d(getContext(), cVar, z7);
        if (z7) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.f671l));
        } else {
            dVar.setFocusable(true);
            if (this.f665f == null) {
                this.f665f = new c();
            }
            dVar.setOnClickListener(this.f665f);
        }
        return dVar;
    }

    public final boolean e() {
        Spinner spinner = this.f667h;
        return spinner != null && spinner.getParent() == this;
    }

    public final void f() {
        if (e()) {
            return;
        }
        if (this.f667h == null) {
            this.f667h = b();
        }
        removeView(this.f666g);
        addView(this.f667h, new ViewGroup.LayoutParams(-2, -1));
        if (this.f667h.getAdapter() == null) {
            this.f667h.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f664e;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f664e = null;
        }
        this.f667h.setSelection(this.f672m);
    }

    public final boolean g() {
        if (!e()) {
            return false;
        }
        removeView(this.f667h);
        addView(this.f666g, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f667h.getSelectedItemPosition());
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f664e;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        p.a aVarB = p.a.b(getContext());
        setContentHeight(aVarB.f());
        this.f670k = aVarB.e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f664e;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i8, long j8) {
        ((d) view).b().e();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i8);
        boolean z7 = mode == 1073741824;
        setFillViewport(z7);
        int childCount = this.f666g.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f669j = -1;
        } else {
            if (childCount > 2) {
                this.f669j = (int) (View.MeasureSpec.getSize(i8) * 0.4f);
            } else {
                this.f669j = View.MeasureSpec.getSize(i8) / 2;
            }
            this.f669j = Math.min(this.f669j, this.f670k);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f671l, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (!z7 && this.f668i) {
            this.f666g.measure(0, iMakeMeasureSpec);
            if (this.f666g.getMeasuredWidth() > View.MeasureSpec.getSize(i8)) {
                f();
            } else {
                g();
            }
        } else {
            g();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i8, iMakeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z7 || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.f672m);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z7) {
        this.f668i = z7;
    }

    public void setContentHeight(int i8) {
        this.f671l = i8;
        requestLayout();
    }

    public void setTabSelected(int i8) {
        this.f672m = i8;
        int childCount = this.f666g.getChildCount();
        int i9 = 0;
        while (i9 < childCount) {
            View childAt = this.f666g.getChildAt(i9);
            boolean z7 = i9 == i8;
            childAt.setSelected(z7);
            if (z7) {
                a(i8);
            }
            i9++;
        }
        Spinner spinner = this.f667h;
        if (spinner == null || i8 < 0) {
            return;
        }
        spinner.setSelection(i8);
    }
}
