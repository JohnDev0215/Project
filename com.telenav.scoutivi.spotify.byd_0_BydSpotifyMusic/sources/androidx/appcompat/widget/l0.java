package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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

/* JADX INFO: compiled from: ScrollingTabContainerView.java */
/* JADX INFO: loaded from: classes.dex */
public class l0 extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final Interpolator f1114o = new DecelerateInterpolator();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Runnable f1115e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private c f1116g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    LinearLayoutCompat f1117h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Spinner f1118i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f1119j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    int f1120k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f1121l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f1122m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f1123n;

    /* JADX INFO: compiled from: ScrollingTabContainerView.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f1124e;

        a(View view) {
            this.f1124e = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            l0.this.smoothScrollTo(this.f1124e.getLeft() - ((l0.this.getWidth() - this.f1124e.getWidth()) / 2), 0);
            l0.this.f1115e = null;
        }
    }

    /* JADX INFO: compiled from: ScrollingTabContainerView.java */
    private class b extends BaseAdapter {
        b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return l0.this.f1117h.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i7) {
            return ((d) l0.this.f1117h.getChildAt(i7)).b();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i7) {
            return i7;
        }

        @Override // android.widget.Adapter
        public View getView(int i7, View view, ViewGroup viewGroup) {
            if (view == null) {
                return l0.this.c((a.c) getItem(i7), true);
            }
            ((d) view).a((a.c) getItem(i7));
            return view;
        }
    }

    /* JADX INFO: compiled from: ScrollingTabContainerView.java */
    private class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((d) view).b().e();
            int childCount = l0.this.f1117h.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = l0.this.f1117h.getChildAt(i7);
                childAt.setSelected(childAt == view);
            }
        }
    }

    /* JADX INFO: compiled from: ScrollingTabContainerView.java */
    private class d extends LinearLayout {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int[] f1128e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private a.c f1129g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private TextView f1130h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private ImageView f1131i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private View f1132j;

        /* JADX WARN: Illegal instructions before constructor call */
        public d(Context context, a.c cVar, boolean z6) {
            int i7 = e.a.actionBarTabStyle;
            super(context, null, i7);
            int[] iArr = {R.attr.background};
            this.f1128e = iArr;
            this.f1129g = cVar;
            s0 s0VarV = s0.v(context, null, iArr, i7, 0);
            if (s0VarV.s(0)) {
                setBackgroundDrawable(s0VarV.g(0));
            }
            s0VarV.w();
            if (z6) {
                setGravity(8388627);
            }
            c();
        }

        public void a(a.c cVar) {
            this.f1129g = cVar;
            c();
        }

        public a.c b() {
            return this.f1129g;
        }

        public void c() {
            a.c cVar = this.f1129g;
            View viewB = cVar.b();
            if (viewB != null) {
                ViewParent parent = viewB.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewB);
                    }
                    addView(viewB);
                }
                this.f1132j = viewB;
                TextView textView = this.f1130h;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f1131i;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f1131i.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f1132j;
            if (view != null) {
                removeView(view);
                this.f1132j = null;
            }
            Drawable drawableC = cVar.c();
            CharSequence charSequenceD = cVar.d();
            if (drawableC != null) {
                if (this.f1131i == null) {
                    m mVar = new m(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    mVar.setLayoutParams(layoutParams);
                    addView(mVar, 0);
                    this.f1131i = mVar;
                }
                this.f1131i.setImageDrawable(drawableC);
                this.f1131i.setVisibility(0);
            } else {
                ImageView imageView2 = this.f1131i;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f1131i.setImageDrawable(null);
                }
            }
            boolean z6 = !TextUtils.isEmpty(charSequenceD);
            if (z6) {
                if (this.f1130h == null) {
                    w wVar = new w(getContext(), null, e.a.actionBarTabTextStyle);
                    wVar.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    wVar.setLayoutParams(layoutParams2);
                    addView(wVar);
                    this.f1130h = wVar;
                }
                this.f1130h.setText(charSequenceD);
                this.f1130h.setVisibility(0);
            } else {
                TextView textView2 = this.f1130h;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f1130h.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f1131i;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.a());
            }
            u0.a(this, z6 ? null : cVar.a());
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
        public void onMeasure(int i7, int i8) {
            super.onMeasure(i7, i8);
            if (l0.this.f1120k > 0) {
                int measuredWidth = getMeasuredWidth();
                int i9 = l0.this.f1120k;
                if (measuredWidth > i9) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i9, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i8);
                }
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z6) {
            boolean z7 = isSelected() != z6;
            super.setSelected(z6);
            if (z7 && z6) {
                sendAccessibilityEvent(4);
            }
        }
    }

    private Spinner b() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, e.a.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    private boolean d() {
        Spinner spinner = this.f1118i;
        return spinner != null && spinner.getParent() == this;
    }

    private void e() {
        if (d()) {
            return;
        }
        if (this.f1118i == null) {
            this.f1118i = b();
        }
        removeView(this.f1117h);
        addView(this.f1118i, new ViewGroup.LayoutParams(-2, -1));
        if (this.f1118i.getAdapter() == null) {
            this.f1118i.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f1115e;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f1115e = null;
        }
        this.f1118i.setSelection(this.f1123n);
    }

    private boolean f() {
        if (!d()) {
            return false;
        }
        removeView(this.f1118i);
        addView(this.f1117h, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f1118i.getSelectedItemPosition());
        return false;
    }

    public void a(int i7) {
        View childAt = this.f1117h.getChildAt(i7);
        Runnable runnable = this.f1115e;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.f1115e = aVar;
        post(aVar);
    }

    d c(a.c cVar, boolean z6) {
        d dVar = new d(getContext(), cVar, z6);
        if (z6) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.f1122m));
        } else {
            dVar.setFocusable(true);
            if (this.f1116g == null) {
                this.f1116g = new c();
            }
            dVar.setOnClickListener(this.f1116g);
        }
        return dVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f1115e;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        i.a aVarB = i.a.b(getContext());
        setContentHeight(aVarB.f());
        this.f1121l = aVarB.e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f1115e;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i7, long j7) {
        ((d) view).b().e();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        boolean z6 = mode == 1073741824;
        setFillViewport(z6);
        int childCount = this.f1117h.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f1120k = -1;
        } else {
            if (childCount > 2) {
                this.f1120k = (int) (View.MeasureSpec.getSize(i7) * 0.4f);
            } else {
                this.f1120k = View.MeasureSpec.getSize(i7) / 2;
            }
            this.f1120k = Math.min(this.f1120k, this.f1121l);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f1122m, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (!z6 && this.f1119j) {
            this.f1117h.measure(0, iMakeMeasureSpec);
            if (this.f1117h.getMeasuredWidth() > View.MeasureSpec.getSize(i7)) {
                e();
            } else {
                f();
            }
        } else {
            f();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i7, iMakeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z6 || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.f1123n);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z6) {
        this.f1119j = z6;
    }

    public void setContentHeight(int i7) {
        this.f1122m = i7;
        requestLayout();
    }

    public void setTabSelected(int i7) {
        this.f1123n = i7;
        int childCount = this.f1117h.getChildCount();
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = this.f1117h.getChildAt(i8);
            boolean z6 = i8 == i7;
            childAt.setSelected(z6);
            if (z6) {
                a(i7);
            }
            i8++;
        }
        Spinner spinner = this.f1118i;
        if (spinner == null || i7 < 0) {
            return;
        }
        spinner.setSelection(i7);
    }
}
