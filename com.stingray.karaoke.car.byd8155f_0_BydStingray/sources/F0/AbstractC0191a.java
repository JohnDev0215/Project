package F0;

import A.C0024l;
import T.AbstractC0764s;
import T.C0761q;
import android.content.Context;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.node.Owner;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: F0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0191a extends ViewGroup {
    public WeakReference l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public IBinder f2168m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public u1 f2169n;
    public AbstractC0764s o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C0024l f2170p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2171q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2172r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f2173s;

    public /* synthetic */ AbstractC0191a(Context context) {
        this(context, null, 0);
    }

    private static /* synthetic */ void getDisposeViewCompositionStrategy$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    private final void setParentContext(AbstractC0764s abstractC0764s) {
        if (this.o != abstractC0764s) {
            this.o = abstractC0764s;
            if (abstractC0764s != null) {
                this.l = null;
            }
            u1 u1Var = this.f2169n;
            if (u1Var != null) {
                u1Var.a();
                this.f2169n = null;
                if (isAttachedToWindow()) {
                    d();
                }
            }
        }
    }

    private final void setPreviousAttachedWindowToken(IBinder iBinder) {
        if (this.f2168m != iBinder) {
            this.f2168m = iBinder;
            this.l = null;
        }
    }

    public abstract void a(int i7, C0761q c0761q);

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        b();
        super.addView(view);
    }

    @Override // android.view.ViewGroup
    public final boolean addViewInLayout(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        b();
        return super.addViewInLayout(view, i7, layoutParams);
    }

    public final void b() {
        if (this.f2172r) {
            return;
        }
        throw new UnsupportedOperationException("Cannot add views to " + getClass().getSimpleName() + "; only Compose content is supported");
    }

    public final void c() {
        u1 u1Var = this.f2169n;
        if (u1Var != null) {
            u1Var.a();
        }
        this.f2169n = null;
        requestLayout();
    }

    public final void d() {
        if (this.f2169n == null) {
            try {
                this.f2172r = true;
                this.f2169n = w1.a(this, g(), new b0.b(-656146368, new A.G0(this, 5), true));
            } finally {
                this.f2172r = false;
            }
        }
    }

    public void e(boolean z6, int i7, int i10, int i11, int i12) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.layout(getPaddingLeft(), getPaddingTop(), (i11 - i7) - getPaddingRight(), (i12 - i10) - getPaddingBottom());
        }
    }

    public void f(int i7, int i10) {
        View childAt = getChildAt(0);
        if (childAt == null) {
            super.onMeasure(i7, i10);
            return;
        }
        childAt.measure(View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i7) - getPaddingLeft()) - getPaddingRight()), View.MeasureSpec.getMode(i7)), View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i10) - getPaddingTop()) - getPaddingBottom()), View.MeasureSpec.getMode(i10)));
        setMeasuredDimension(getPaddingRight() + getPaddingLeft() + childAt.getMeasuredWidth(), getPaddingBottom() + getPaddingTop() + childAt.getMeasuredHeight());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0074  */
    /* JADX WARN: Type inference failed for: r1v0, types: [T.s] */
    /* JADX WARN: Type inference failed for: r1v1, types: [T.s] */
    /* JADX WARN: Type inference failed for: r1v15, types: [T.t0] */
    /* JADX WARN: Type inference failed for: r1v2, types: [T.s] */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T.AbstractC0764s g() {
        /*
            Method dump skipped, instruction units count: 453
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.AbstractC0191a.g():T.s");
    }

    public final boolean getHasComposition() {
        return this.f2169n != null;
    }

    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return true;
    }

    public final boolean getShowLayoutBounds() {
        return this.f2171q;
    }

    @Override // android.view.ViewGroup
    public final boolean isTransitionGroup() {
        return !this.f2173s || super.isTransitionGroup();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setPreviousAttachedWindowToken(getWindowToken());
        if (getShouldCreateCompositionOnAttachedToWindow()) {
            d();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        e(z6, i7, i10, i11, i12);
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        d();
        f(i7, i10);
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        childAt.setLayoutDirection(i7);
    }

    public final void setParentCompositionContext(AbstractC0764s abstractC0764s) {
        setParentContext(abstractC0764s);
    }

    public final void setShowLayoutBounds(boolean z6) {
        this.f2171q = z6;
        KeyEvent.Callback childAt = getChildAt(0);
        if (childAt != null) {
            ((Owner) childAt).setShowLayoutBounds(z6);
        }
    }

    @Override // android.view.ViewGroup
    public void setTransitionGroup(boolean z6) {
        super.setTransitionGroup(z6);
        this.f2173s = true;
    }

    public final void setViewCompositionStrategy(V0 v02) {
        C0024l c0024l = this.f2170p;
        if (c0024l != null) {
            c0024l.a();
        }
        ((T) v02).getClass();
        C c = new C(this, 1);
        addOnAttachStateChangeListener(c);
        U0 u02 = new U0(this);
        C7.a.L(this).f3048a.add(u02);
        this.f2170p = new C0024l(this, c, u02, 5);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public AbstractC0191a(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        setClipChildren(false);
        setClipToPadding(false);
        C c = new C(this, 1);
        addOnAttachStateChangeListener(c);
        U0 u02 = new U0(this);
        C7.a.L(this).f3048a.add(u02);
        this.f2170p = new C0024l(this, c, u02, 5);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7) {
        b();
        super.addView(view, i7);
    }

    @Override // android.view.ViewGroup
    public final boolean addViewInLayout(View view, int i7, ViewGroup.LayoutParams layoutParams, boolean z6) {
        b();
        return super.addViewInLayout(view, i7, layoutParams, z6);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, int i10) {
        b();
        super.addView(view, i7, i10);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        b();
        super.addView(view, layoutParams);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        b();
        super.addView(view, i7, layoutParams);
    }
}
