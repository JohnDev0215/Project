package org.chromium.chrome.browser.feed;

import android.app.Activity;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import defpackage.ii2;
import defpackage.n3;
import defpackage.nr2;
import defpackage.sf2;
import defpackage.uf2;
import defpackage.vl0;
import defpackage.yp;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;
import org.chromium.third_party.android.swiperefresh.CircleImageView;
import org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout;

/* JADX INFO: loaded from: classes.dex */
public class FeedSwipeRefreshLayout extends SwipeRefreshLayout implements sf2 {
    public final Activity I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public View f66J;
    public final int K;
    public final org.chromium.base.b<SwipeRefreshLayout.e> L;
    public float M;
    public boolean N;
    public uf2 O;
    public int P;

    public class a implements SwipeRefreshLayout.e {
        public final /* synthetic */ Activity f;

        public a(Activity activity) {
            this.f = activity;
        }

        @Override // org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout.e
        public final void c() {
            FeedSwipeRefreshLayout feedSwipeRefreshLayout = FeedSwipeRefreshLayout.this;
            AccessibilityManager accessibilityManager = (AccessibilityManager) feedSwipeRefreshLayout.getContext().getSystemService("accessibility");
            if (accessibilityManager != null && accessibilityManager.isEnabled()) {
                try {
                    accessibilityManager.interrupt();
                } catch (NullPointerException unused) {
                }
                feedSwipeRefreshLayout.announceForAccessibility(this.f.getResources().getString(R$string.accessibility_swipe_refresh));
            }
            n3.X0("MobilePullGestureReloadNTP");
        }
    }

    public FeedSwipeRefreshLayout(Activity activity) {
        super(activity);
        this.L = new org.chromium.base.b<>();
        this.I = activity;
        this.K = ViewConfiguration.get(activity).getScaledTouchSlop();
        setOnRefreshListener(new vl0(this));
    }

    public static FeedSwipeRefreshLayout j(Activity activity, int i) {
        FeedSwipeRefreshLayout feedSwipeRefreshLayout = new FeedSwipeRefreshLayout(activity);
        feedSwipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        feedSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(yp.c(activity, R$dimen.default_elevation_2));
        feedSwipeRefreshLayout.setColorSchemeColors(ii2.b(activity));
        feedSwipeRefreshLayout.setEnabled(false);
        int i2 = (int) (16.0f * activity.getResources().getDisplayMetrics().density);
        feedSwipeRefreshLayout.m = false;
        feedSwipeRefreshLayout.o.setVisibility(8);
        feedSwipeRefreshLayout.j = i2;
        feedSwipeRefreshLayout.s = i2;
        feedSwipeRefreshLayout.y = (int) (r1 * 80.0f);
        feedSwipeRefreshLayout.C = true;
        feedSwipeRefreshLayout.o.invalidate();
        feedSwipeRefreshLayout.addOnRefreshListener(feedSwipeRefreshLayout.new a(activity));
        return feedSwipeRefreshLayout;
    }

    @Override // defpackage.sf2
    public final void a(int i) {
        this.P = i;
    }

    public void addOnRefreshListener(SwipeRefreshLayout.e eVar) {
        this.L.a(eVar);
    }

    public final void k() {
        if (isEnabled()) {
            setEnabled(false);
            uf2 uf2Var = this.O;
            if (uf2Var != null) {
                nr2.this.H.c(this);
                this.O = null;
            }
        }
    }

    public final void l() {
        if (this.f66J == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (!(childAt instanceof CircleImageView)) {
                    this.f66J = childAt;
                    return;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x004f  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.isEnabled()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            uf2 r0 = r5.O
            if (r0 == 0) goto L11
            int r0 = r5.P
            if (r0 == 0) goto L20
            return r1
        L11:
            r5.l()
            android.view.View r0 = r5.f66J
            if (r0 == 0) goto L62
            r2 = -1
            boolean r0 = r0.canScrollVertically(r2)
            if (r0 == 0) goto L20
            goto L62
        L20:
            int r0 = r6.getAction()
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r0 == 0) goto L52
            r3 = 1
            if (r0 == r3) goto L4f
            r4 = 2
            if (r0 == r4) goto L32
            r6 = 3
            if (r0 == r6) goto L4f
            goto L5f
        L32:
            float r6 = r6.getY()
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 != 0) goto L3b
            return r1
        L3b:
            float r0 = r5.M
            float r6 = r6 - r0
            int r0 = r5.K
            float r0 = (float) r0
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 <= 0) goto L5f
            boolean r6 = r5.N
            if (r6 != 0) goto L5f
            r5.N = r3
            r5.h()
            goto L5f
        L4f:
            r5.N = r1
            goto L5f
        L52:
            r5.N = r1
            float r6 = r6.getY()
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 != 0) goto L5d
            return r1
        L5d:
            r5.M = r6
        L5f:
            boolean r5 = r5.N
            return r5
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        l();
        if (this.f66J == null) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        View view = this.f66J;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
    }

    @Override // org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        l();
        View view = this.f66J;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0015, code lost:
    
        if (r0 != 3) goto L17;
     */
    @Override // android.view.View
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.isEnabled()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            int r0 = r5.getAction()
            r2 = 1
            if (r0 == 0) goto L2f
            if (r0 == r2) goto L29
            r3 = 2
            if (r0 == r3) goto L18
            r5 = 3
            if (r0 == r5) goto L29
            goto L31
        L18:
            float r0 = r5.getY()
            float r1 = r4.M
            float r0 = r0 - r1
            r4.c(r0)
            float r5 = r5.getY()
            r4.M = r5
            goto L31
        L29:
            r4.d(r2)
            r4.N = r1
            return r1
        L2f:
            r4.N = r1
        L31:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 8) {
            e();
        }
    }

    public void removeOnRefreshListener(SwipeRefreshLayout.e eVar) {
        this.L.c(eVar);
    }
}
