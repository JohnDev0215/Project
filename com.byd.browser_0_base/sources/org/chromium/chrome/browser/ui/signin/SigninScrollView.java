package org.chromium.chrome.browser.ui.signin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import defpackage.qm2;
import defpackage.rm2;
import org.chromium.chrome.browser.ui.signin.SigninScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class SigninScrollView extends ScrollView {
    public static final /* synthetic */ int h = 0;
    public final qm2 e;
    public final rm2 f;
    public Runnable g;

    /* JADX WARN: Type inference failed for: r1v1, types: [qm2] */
    /* JADX WARN: Type inference failed for: r1v2, types: [rm2] */
    public SigninScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: qm2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                int i = SigninScrollView.h;
                this.e.a();
            }
        };
        this.f = new ViewTreeObserver.OnScrollChangedListener() { // from class: rm2
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                int i = SigninScrollView.h;
                this.a.a();
            }
        };
    }

    public final void a() {
        if (this.g == null) {
            return;
        }
        if (getChildCount() == 0) {
            this.g.run();
            return;
        }
        if (getScrollY() + getHeight() < getChildAt(getChildCount() - 1).getBottom()) {
            return;
        }
        this.g.run();
    }

    @Override // android.widget.ScrollView, android.view.View
    public float getTopFadingEdgeStrength() {
        return 0.0f;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        if (this.g != null) {
            this.g = null;
            getViewTreeObserver().removeOnGlobalLayoutListener(this.e);
            getViewTreeObserver().removeOnScrollChangedListener(this.f);
        }
        super.onDetachedFromWindow();
    }

    public void setScrolledToBottomObserver(Runnable runnable) {
        Runnable runnable2 = this.g;
        rm2 rm2Var = this.f;
        qm2 qm2Var = this.e;
        if (runnable2 != null) {
            this.g = null;
            getViewTreeObserver().removeOnGlobalLayoutListener(qm2Var);
            getViewTreeObserver().removeOnScrollChangedListener(rm2Var);
        }
        if (runnable == null) {
            return;
        }
        this.g = runnable;
        getViewTreeObserver().addOnGlobalLayoutListener(qm2Var);
        getViewTreeObserver().addOnScrollChangedListener(rm2Var);
    }
}
