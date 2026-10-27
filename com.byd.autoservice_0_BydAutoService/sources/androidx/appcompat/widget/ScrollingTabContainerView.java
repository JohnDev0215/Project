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
import i.a;
import o.e0;
import o.g0;

/* JADX INFO: loaded from: classes.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Interpolator f564b = new DecelerateInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f565c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f566d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LinearLayoutCompat f567e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Spinner f568f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f569g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f570h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f571i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f572j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f573k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ViewPropertyAnimator f574l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e f575m;

    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f576b;

        public a(View view) {
            this.f576b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScrollingTabContainerView.this.smoothScrollTo(this.f576b.getLeft() - ((ScrollingTabContainerView.this.getWidth() - this.f576b.getWidth()) / 2), 0);
            ScrollingTabContainerView.this.f565c = null;
        }
    }

    public class b extends BaseAdapter {
        public b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ScrollingTabContainerView.this.f567e.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i3) {
            return ((d) ScrollingTabContainerView.this.f567e.getChildAt(i3)).b();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i3) {
            return i3;
        }

        @Override // android.widget.Adapter
        public View getView(int i3, View view, ViewGroup viewGroup) {
            if (view == null) {
                return ScrollingTabContainerView.this.d((a.c) getItem(i3), true);
            }
            ((d) view).a((a.c) getItem(i3));
            return view;
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((d) view).b().e();
            int childCount = ScrollingTabContainerView.this.f567e.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = ScrollingTabContainerView.this.f567e.getChildAt(i3);
                childAt.setSelected(childAt == view);
            }
        }
    }

    public class d extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int[] f580b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public a.c f581c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f582d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ImageView f583e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f584f;

        /* JADX WARN: Illegal instructions before constructor call */
        public d(Context context, a.c cVar, boolean z3) {
            int i3 = R$attr.actionBarTabStyle;
            super(context, null, i3);
            int[] iArr = {R.attr.background};
            this.f580b = iArr;
            this.f581c = cVar;
            e0 e0VarV = e0.v(context, null, iArr, i3, 0);
            if (e0VarV.s(0)) {
                setBackgroundDrawable(e0VarV.g(0));
            }
            e0VarV.w();
            if (z3) {
                setGravity(8388627);
            }
            c();
        }

        public void a(a.c cVar) {
            this.f581c = cVar;
            c();
        }

        public a.c b() {
            return this.f581c;
        }

        public void c() {
            a.c cVar = this.f581c;
            View viewB = cVar.b();
            if (viewB != null) {
                ViewParent parent = viewB.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewB);
                    }
                    addView(viewB);
                }
                this.f584f = viewB;
                TextView textView = this.f582d;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f583e;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f583e.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f584f;
            if (view != null) {
                removeView(view);
                this.f584f = null;
            }
            Drawable drawableC = cVar.c();
            CharSequence charSequenceD = cVar.d();
            if (drawableC != null) {
                if (this.f583e == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.f583e = appCompatImageView;
                }
                this.f583e.setImageDrawable(drawableC);
                this.f583e.setVisibility(0);
            } else {
                ImageView imageView2 = this.f583e;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f583e.setImageDrawable(null);
                }
            }
            boolean z3 = !TextUtils.isEmpty(charSequenceD);
            if (z3) {
                if (this.f582d == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, R$attr.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.f582d = appCompatTextView;
                }
                this.f582d.setText(charSequenceD);
                this.f582d.setVisibility(0);
            } else {
                TextView textView2 = this.f582d;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f582d.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f583e;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.a());
            }
            g0.a(this, z3 ? null : cVar.a());
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
        public void onMeasure(int i3, int i4) {
            super.onMeasure(i3, i4);
            if (ScrollingTabContainerView.this.f570h > 0) {
                int measuredWidth = getMeasuredWidth();
                int i5 = ScrollingTabContainerView.this.f570h;
                if (measuredWidth > i5) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i5, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i4);
                }
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z3) {
            boolean z4 = isSelected() != z3;
            super.setSelected(z3);
            if (z4 && z3) {
                sendAccessibilityEvent(4);
            }
        }
    }

    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f586a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f587b;

        public e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f586a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f586a) {
                return;
            }
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            scrollingTabContainerView.f574l = null;
            scrollingTabContainerView.setVisibility(this.f587b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.f586a = false;
        }
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        this.f575m = new e();
        setHorizontalScrollBarEnabled(false);
        m.a aVarB = m.a.b(context);
        setContentHeight(aVarB.f());
        this.f571i = aVarB.e();
        LinearLayoutCompat linearLayoutCompatC = c();
        this.f567e = linearLayoutCompatC;
        addView(linearLayoutCompatC, new ViewGroup.LayoutParams(-2, -1));
    }

    public void a(int i3) {
        View childAt = this.f567e.getChildAt(i3);
        Runnable runnable = this.f565c;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.f565c = aVar;
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

    public d d(a.c cVar, boolean z3) {
        d dVar = new d(getContext(), cVar, z3);
        if (z3) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.f572j));
        } else {
            dVar.setFocusable(true);
            if (this.f566d == null) {
                this.f566d = new c();
            }
            dVar.setOnClickListener(this.f566d);
        }
        return dVar;
    }

    public final boolean e() {
        Spinner spinner = this.f568f;
        return spinner != null && spinner.getParent() == this;
    }

    public final void f() {
        if (e()) {
            return;
        }
        if (this.f568f == null) {
            this.f568f = b();
        }
        removeView(this.f567e);
        addView(this.f568f, new ViewGroup.LayoutParams(-2, -1));
        if (this.f568f.getAdapter() == null) {
            this.f568f.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f565c;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f565c = null;
        }
        this.f568f.setSelection(this.f573k);
    }

    public final boolean g() {
        if (!e()) {
            return false;
        }
        removeView(this.f568f);
        addView(this.f567e, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f568f.getSelectedItemPosition());
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f565c;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m.a aVarB = m.a.b(getContext());
        setContentHeight(aVarB.f());
        this.f571i = aVarB.e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f565c;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j3) {
        ((d) view).b().e();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        boolean z3 = mode == 1073741824;
        setFillViewport(z3);
        int childCount = this.f567e.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f570h = -1;
        } else {
            if (childCount > 2) {
                this.f570h = (int) (View.MeasureSpec.getSize(i3) * 0.4f);
            } else {
                this.f570h = View.MeasureSpec.getSize(i3) / 2;
            }
            this.f570h = Math.min(this.f570h, this.f571i);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f572j, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (!z3 && this.f569g) {
            this.f567e.measure(0, iMakeMeasureSpec);
            if (this.f567e.getMeasuredWidth() > View.MeasureSpec.getSize(i3)) {
                f();
            } else {
                g();
            }
        } else {
            g();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i3, iMakeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z3 || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.f573k);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z3) {
        this.f569g = z3;
    }

    public void setContentHeight(int i3) {
        this.f572j = i3;
        requestLayout();
    }

    public void setTabSelected(int i3) {
        this.f573k = i3;
        int childCount = this.f567e.getChildCount();
        int i4 = 0;
        while (i4 < childCount) {
            View childAt = this.f567e.getChildAt(i4);
            boolean z3 = i4 == i3;
            childAt.setSelected(z3);
            if (z3) {
                a(i3);
            }
            i4++;
        }
        Spinner spinner = this.f568f;
        if (spinner == null || i3 < 0) {
            return;
        }
        spinner.setSelection(i3);
    }
}
