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

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Interpolator f241c = new DecelerateInterpolator();
    public boolean E3;
    public int F3;
    public int G3;
    public int H3;
    public int I3;
    public ViewPropertyAnimator J3;
    public final e K3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f242d;
    public c s;
    public LinearLayoutCompat x;
    public Spinner y;

    public class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f243c;

        public a(View view) {
            this.f243c = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScrollingTabContainerView.this.smoothScrollTo(this.f243c.getLeft() - ((ScrollingTabContainerView.this.getWidth() - this.f243c.getWidth()) / 2), 0);
            ScrollingTabContainerView.this.f242d = null;
        }
    }

    public class b extends BaseAdapter {
        public b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ScrollingTabContainerView.this.x.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return ((d) ScrollingTabContainerView.this.x.getChildAt(i2)).b();
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
            int childCount = ScrollingTabContainerView.this.x.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = ScrollingTabContainerView.this.x.getChildAt(i2);
                childAt.setSelected(childAt == view);
            }
        }
    }

    public class d extends LinearLayout {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[] f247c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public a.c f248d;
        public TextView s;
        public ImageView x;
        public View y;

        /* JADX WARN: Illegal instructions before constructor call */
        public d(Context context, a.c cVar, boolean z) {
            int i2 = b.b.a.f708e;
            super(context, null, i2);
            int[] iArr = {R.attr.background};
            this.f247c = iArr;
            this.f248d = cVar;
            e0 e0VarU = e0.u(context, null, iArr, i2, 0);
            if (e0VarU.r(0)) {
                setBackgroundDrawable(e0VarU.f(0));
            }
            e0VarU.v();
            if (z) {
                setGravity(8388627);
            }
            c();
        }

        public void a(a.c cVar) {
            this.f248d = cVar;
            c();
        }

        public a.c b() {
            return this.f248d;
        }

        public void c() {
            a.c cVar = this.f248d;
            View viewB = cVar.b();
            if (viewB != null) {
                ViewParent parent = viewB.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewB);
                    }
                    addView(viewB);
                }
                this.y = viewB;
                TextView textView = this.s;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.x;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.x.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.y;
            if (view != null) {
                removeView(view);
                this.y = null;
            }
            Drawable drawableC = cVar.c();
            CharSequence charSequenceD = cVar.d();
            if (drawableC != null) {
                if (this.x == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.x = appCompatImageView;
                }
                this.x.setImageDrawable(drawableC);
                this.x.setVisibility(0);
            } else {
                ImageView imageView2 = this.x;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.x.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(charSequenceD);
            if (z) {
                if (this.s == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, b.b.a.f709f);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.s = appCompatTextView;
                }
                this.s.setText(charSequenceD);
                this.s.setVisibility(0);
            } else {
                TextView textView2 = this.s;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.s.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.x;
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
            if (ScrollingTabContainerView.this.F3 > 0) {
                int measuredWidth = getMeasuredWidth();
                int i4 = ScrollingTabContainerView.this.F3;
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
        public boolean f249a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f250b;

        public e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f249a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f249a) {
                return;
            }
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            scrollingTabContainerView.J3 = null;
            scrollingTabContainerView.setVisibility(this.f250b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.f249a = false;
        }
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        this.K3 = new e();
        setHorizontalScrollBarEnabled(false);
        b.b.p.a aVarB = b.b.p.a.b(context);
        setContentHeight(aVarB.f());
        this.G3 = aVarB.e();
        LinearLayoutCompat linearLayoutCompatC = c();
        this.x = linearLayoutCompatC;
        addView(linearLayoutCompatC, new ViewGroup.LayoutParams(-2, -1));
    }

    public void a(int i2) {
        View childAt = this.x.getChildAt(i2);
        Runnable runnable = this.f242d;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.f242d = aVar;
        post(aVar);
    }

    public final Spinner b() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, b.b.a.f712i);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    public final LinearLayoutCompat c() {
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, b.b.a.f707d);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        return linearLayoutCompat;
    }

    public d d(a.c cVar, boolean z) {
        d dVar = new d(getContext(), cVar, z);
        if (z) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.H3));
        } else {
            dVar.setFocusable(true);
            if (this.s == null) {
                this.s = new c();
            }
            dVar.setOnClickListener(this.s);
        }
        return dVar;
    }

    public final boolean e() {
        Spinner spinner = this.y;
        return spinner != null && spinner.getParent() == this;
    }

    public final void f() {
        if (e()) {
            return;
        }
        if (this.y == null) {
            this.y = b();
        }
        removeView(this.x);
        addView(this.y, new ViewGroup.LayoutParams(-2, -1));
        if (this.y.getAdapter() == null) {
            this.y.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f242d;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f242d = null;
        }
        this.y.setSelection(this.I3);
    }

    public final boolean g() {
        if (!e()) {
            return false;
        }
        removeView(this.y);
        addView(this.x, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.y.getSelectedItemPosition());
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f242d;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b.b.p.a aVarB = b.b.p.a.b(getContext());
        setContentHeight(aVarB.f());
        this.G3 = aVarB.e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f242d;
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
        int childCount = this.x.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.F3 = -1;
        } else {
            if (childCount > 2) {
                this.F3 = (int) (View.MeasureSpec.getSize(i2) * 0.4f);
            } else {
                this.F3 = View.MeasureSpec.getSize(i2) / 2;
            }
            this.F3 = Math.min(this.F3, this.G3);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.H3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (!z && this.E3) {
            this.x.measure(0, iMakeMeasureSpec);
            if (this.x.getMeasuredWidth() > View.MeasureSpec.getSize(i2)) {
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
        setTabSelected(this.I3);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.E3 = z;
    }

    public void setContentHeight(int i2) {
        this.H3 = i2;
        requestLayout();
    }

    public void setTabSelected(int i2) {
        this.I3 = i2;
        int childCount = this.x.getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = this.x.getChildAt(i3);
            boolean z = i3 == i2;
            childAt.setSelected(z);
            if (z) {
                a(i2);
            }
            i3++;
        }
        Spinner spinner = this.y;
        if (spinner == null || i2 < 0) {
            return;
        }
        spinner.setSelection(i2);
    }
}
