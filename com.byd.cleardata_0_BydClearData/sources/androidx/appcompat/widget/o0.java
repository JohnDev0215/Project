package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
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
import androidx.appcompat.widget.h0;

/* JADX INFO: loaded from: classes.dex */
public class o0 extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Interpolator f990b = new DecelerateInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Runnable f991c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f992d;
    h0 e;
    private Spinner f;
    private boolean g;
    int h;
    int i;
    private int j;
    private int k;

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f993b;

        a(View view) {
            this.f993b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            o0.this.smoothScrollTo(this.f993b.getLeft() - ((o0.this.getWidth() - this.f993b.getWidth()) / 2), 0);
            o0.this.f991c = null;
        }
    }

    private class b extends BaseAdapter {
        b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return o0.this.e.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return ((d) o0.this.e.getChildAt(i)).b();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return o0.this.c((a.c) getItem(i), true);
            }
            ((d) view).a((a.c) getItem(i));
            return view;
        }
    }

    private class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((d) view).b().e();
            int childCount = o0.this.e.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = o0.this.e.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }

    private class d extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int[] f997b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private a.c f998c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private TextView f999d;
        private ImageView e;
        private View f;

        /* JADX WARN: Illegal instructions before constructor call */
        public d(Context context, a.c cVar, boolean z) {
            int i = a.a.a.f3d;
            super(context, null, i);
            int[] iArr = {R.attr.background};
            this.f997b = iArr;
            this.f998c = cVar;
            v0 v0VarV = v0.v(context, null, iArr, i, 0);
            if (v0VarV.s(0)) {
                setBackgroundDrawable(v0VarV.g(0));
            }
            v0VarV.w();
            if (z) {
                setGravity(8388627);
            }
            c();
        }

        public void a(a.c cVar) {
            this.f998c = cVar;
            c();
        }

        public a.c b() {
            return this.f998c;
        }

        public void c() {
            a.c cVar = this.f998c;
            View viewB = cVar.b();
            if (viewB != null) {
                ViewParent parent = viewB.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewB);
                    }
                    addView(viewB);
                }
                this.f = viewB;
                TextView textView = this.f999d;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.e;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.e.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f;
            if (view != null) {
                removeView(view);
                this.f = null;
            }
            Drawable drawableC = cVar.c();
            CharSequence charSequenceD = cVar.d();
            if (drawableC != null) {
                if (this.e == null) {
                    n nVar = new n(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    nVar.setLayoutParams(layoutParams);
                    addView(nVar, 0);
                    this.e = nVar;
                }
                this.e.setImageDrawable(drawableC);
                this.e.setVisibility(0);
            } else {
                ImageView imageView2 = this.e;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.e.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(charSequenceD);
            if (z) {
                if (this.f999d == null) {
                    y yVar = new y(getContext(), null, a.a.a.e);
                    yVar.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    yVar.setLayoutParams(layoutParams2);
                    addView(yVar);
                    this.f999d = yVar;
                }
                this.f999d.setText(charSequenceD);
                this.f999d.setVisibility(0);
            } else {
                TextView textView2 = this.f999d;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f999d.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.e;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.a());
            }
            x0.a(this, z ? null : cVar.a());
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
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (o0.this.h > 0) {
                int measuredWidth = getMeasuredWidth();
                int i3 = o0.this.h;
                if (measuredWidth > i3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
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

    private Spinner b() {
        v vVar = new v(getContext(), null, a.a.a.h);
        vVar.setLayoutParams(new h0.a(-2, -1));
        vVar.setOnItemSelectedListener(this);
        return vVar;
    }

    private boolean d() {
        Spinner spinner = this.f;
        return spinner != null && spinner.getParent() == this;
    }

    private void e() {
        if (d()) {
            return;
        }
        if (this.f == null) {
            this.f = b();
        }
        removeView(this.e);
        addView(this.f, new ViewGroup.LayoutParams(-2, -1));
        if (this.f.getAdapter() == null) {
            this.f.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f991c;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f991c = null;
        }
        this.f.setSelection(this.k);
    }

    private boolean f() {
        if (!d()) {
            return false;
        }
        removeView(this.f);
        addView(this.e, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f.getSelectedItemPosition());
        return false;
    }

    public void a(int i) {
        View childAt = this.e.getChildAt(i);
        Runnable runnable = this.f991c;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.f991c = aVar;
        post(aVar);
    }

    d c(a.c cVar, boolean z) {
        d dVar = new d(getContext(), cVar, z);
        if (z) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.j));
        } else {
            dVar.setFocusable(true);
            if (this.f992d == null) {
                this.f992d = new c();
            }
            dVar.setOnClickListener(this.f992d);
        }
        return dVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f991c;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a.a.n.a aVarB = a.a.n.a.b(getContext());
        setContentHeight(aVarB.f());
        this.i = aVarB.e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f991c;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ((d) view).b().e();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            int r8 = android.view.View.MeasureSpec.getMode(r7)
            r0 = 1
            r1 = 0
            r2 = 1073741824(0x40000000, float:2.0)
            if (r8 != r2) goto Lc
            r3 = r0
            goto Ld
        Lc:
            r3 = r1
        Ld:
            r6.setFillViewport(r3)
            androidx.appcompat.widget.h0 r4 = r6.e
            int r4 = r4.getChildCount()
            if (r4 <= r0) goto L3e
            if (r8 == r2) goto L1e
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r8 != r5) goto L3e
        L1e:
            r8 = 2
            if (r4 <= r8) goto L2e
            int r8 = android.view.View.MeasureSpec.getSize(r7)
            float r8 = (float) r8
            r4 = 1053609165(0x3ecccccd, float:0.4)
            float r8 = r8 * r4
            int r8 = (int) r8
            r6.h = r8
            goto L35
        L2e:
            int r4 = android.view.View.MeasureSpec.getSize(r7)
            int r4 = r4 / r8
            r6.h = r4
        L35:
            int r8 = r6.h
            int r4 = r6.i
            int r8 = java.lang.Math.min(r8, r4)
            goto L3f
        L3e:
            r8 = -1
        L3f:
            r6.h = r8
            int r8 = r6.j
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r2)
            if (r3 != 0) goto L4e
            boolean r2 = r6.g
            if (r2 == 0) goto L4e
            goto L4f
        L4e:
            r0 = r1
        L4f:
            if (r0 == 0) goto L66
            androidx.appcompat.widget.h0 r0 = r6.e
            r0.measure(r1, r8)
            androidx.appcompat.widget.h0 r0 = r6.e
            int r0 = r0.getMeasuredWidth()
            int r1 = android.view.View.MeasureSpec.getSize(r7)
            if (r0 <= r1) goto L66
            r6.e()
            goto L69
        L66:
            r6.f()
        L69:
            int r0 = r6.getMeasuredWidth()
            super.onMeasure(r7, r8)
            int r7 = r6.getMeasuredWidth()
            if (r3 == 0) goto L7d
            if (r0 == r7) goto L7d
            int r7 = r6.k
            r6.setTabSelected(r7)
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.o0.onMeasure(int, int):void");
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.g = z;
    }

    public void setContentHeight(int i) {
        this.j = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.k = i;
        int childCount = this.e.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.e.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                a(i);
            }
            i2++;
        }
        Spinner spinner = this.f;
        if (spinner == null || i < 0) {
            return;
        }
        spinner.setSelection(i);
    }
}
