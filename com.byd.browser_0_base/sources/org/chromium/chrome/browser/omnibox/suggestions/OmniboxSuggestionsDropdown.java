package org.chromium.chrome.browser.omnibox.suggestions;

import android.R;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.b50;
import defpackage.bq;
import defpackage.in3;
import defpackage.it3;
import defpackage.ko0;
import defpackage.n3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.pr1;
import defpackage.rh3;
import defpackage.tr1;
import defpackage.xa3;
import defpackage.yp;
import defpackage.zc0;
import java.util.WeakHashMap;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.omnibox.suggestions.e;

/* JADX INFO: loaded from: classes2.dex */
public class OmniboxSuggestionsDropdown extends RecyclerView {
    public static final /* synthetic */ int g1 = 0;
    public final int P0;
    public final int Q0;
    public final Rect R0;
    public final c S0;
    public tr1 T0;
    public e U0;
    public a V0;
    public Callback<Integer> W0;
    public Runnable X0;
    public Runnable Y0;
    public e.a Z0;
    public int a1;
    public int b1;
    public ko0 c1;
    public int d1;
    public int e1;
    public int f1;

    public interface a {
        void b(long j, boolean z);
    }

    public class b extends RecyclerView.r {
        public b() {
            d(0, 20);
            d(1, 1);
            d(2, 1);
            if (pr1.c()) {
                d(3, 8);
            } else {
                d(3, 5);
            }
            d(4, 15);
            d(5, 1);
            d(6, 4);
            d(7, 1);
            d(8, 3);
            d(9, 1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public final RecyclerView.a0 b(int i) {
            RecyclerView.a0 a0VarB = super.b(i);
            if (a0VarB == null) {
                oj2.U(i, 10, "Android.Omnibox.SuggestionView.CreatedType");
            } else {
                oj2.U(i, 10, "Android.Omnibox.SuggestionView.ReusedType");
            }
            return a0VarB;
        }
    }

    public class c extends LinearLayoutManager {
        public boolean E;

        public c(Context context) {
            super(context);
            this.E = true;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
        public final int w0(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
            int iW0 = super.w0(i, sVar, wVar);
            if (iW0 < i) {
                return iW0;
            }
            boolean z = iW0 > i;
            if (this.E == z) {
                return iW0;
            }
            this.E = z;
            OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = OmniboxSuggestionsDropdown.this;
            if (!z) {
                Runnable runnable = omniboxSuggestionsDropdown.X0;
                if (runnable != null) {
                    runnable.run();
                }
                return 0;
            }
            Runnable runnable2 = omniboxSuggestionsDropdown.Y0;
            if (runnable2 == null) {
                return iW0;
            }
            runnable2.run();
            return iW0;
        }
    }

    public OmniboxSuggestionsDropdown(Context context) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.R0 = new Rect();
        this.Z0 = e.a.g;
        this.d1 = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setRecycledViewPool(new b());
        setItemAnimator(null);
        c cVar = new c(context);
        this.S0 = cVar;
        setLayoutManager(cVar);
        boolean zE = pr1.e(context);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.omnibox_suggestion_list_padding_bottom);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(this, 0, 0, 0, dimensionPixelOffset);
        this.P0 = zE ? yp.c(context, R$dimen.omnibox_suggestion_dropdown_bg_elevation) : yp.a(context, false);
        this.Q0 = zE ? context.getColor(bq.d.a() ? R$color.default_bg_color_dark_elev_1_gm3_baseline : R$color.omnibox_dropdown_bg_incognito) : yp.a(context, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        a aVar;
        int actionMasked = motionEvent.getActionMasked();
        if ((actionMasked == 1 || actionMasked == 0) && (aVar = this.V0) != null) {
            aVar.b(motionEvent.getEventTime(), actionMasked == 1);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getDropdownItemViewCountForTest() {
        tr1 tr1Var = this.T0;
        if (tr1Var == null) {
            return 0;
        }
        return tr1Var.k();
    }

    public int getIncognitoBgColor() {
        return this.Q0;
    }

    public c getLayoutScrollListener() {
        return this.S0;
    }

    public int getStandardBgColor() {
        return this.P0;
    }

    public final void o0() {
        if (pr1.e(getContext())) {
            setTranslationX(this.Z0.a);
        } else {
            setPadding(this.Z0.e, getPaddingTop(), this.Z0.f, getPaddingBottom());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        tr1 tr1Var;
        super.onAttachedToWindow();
        this.U0.onAttachedToWindow();
        this.d1 = 0;
        ko0 ko0Var = new ko0(23, this);
        this.c1 = ko0Var;
        this.Z0 = this.U0.a(ko0Var);
        if (!pr1.c() || (tr1Var = this.T0) == null) {
            return;
        }
        tr1Var.I(-1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.U0.onDetachedFromWindow();
        this.Z0 = e.a.g;
        getRecycledViewPool().a();
        tr1 tr1Var = this.T0;
        int i = tr1Var.m;
        if (i > 0) {
            int i2 = tr1Var.l;
            oj2.Q(i2, "Android.Omnibox.SuggestionView.SessionViewsCreated");
            oj2.Q(((i - i2) * 100) / i, "Android.Omnibox.SuggestionView.SessionViewsReused");
        }
        tr1Var.l = 0;
        tr1Var.m = 0;
        this.U0.e(this.c1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getToolType(0) == 3 && (actionMasked == 11 || actionMasked == 12)) {
            return true;
        }
        super.onGenericMotionEvent(motionEvent);
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        int i2;
        if (!isShown()) {
            return false;
        }
        tr1 tr1Var = this.T0;
        View view = null;
        if (tr1Var.k != null && (i2 = tr1Var.j) >= 0 && i2 < tr1Var.k()) {
            View viewU = tr1Var.k.u(tr1Var.j);
            if (viewU != null) {
                view = viewU;
            } else {
                tr1Var.j = -1;
            }
        }
        if (view != null && view.onKeyDown(i, keyEvent)) {
            return true;
        }
        int i3 = this.T0.j;
        return n3.J0(keyEvent) ? this.T0.I(i3 + 1) : n3.M0(keyEvent) ? this.T0.I(i3 - 1) : (!n3.I0(keyEvent) || view == null) ? super.onKeyDown(i, keyEvent) : view.performClick();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent traceEventH0 = TraceEvent.H0("OmniboxSuggestionsList.Layout", null);
        try {
            xa3 xa3Var = new xa3("Android.Omnibox.SuggestionList.LayoutTime2", 2);
            xa3Var.g = SystemClock.currentThreadTimeMillis();
            try {
                super.onLayout(z, i, i2, i3, i4);
                xa3Var.close();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } catch (Throwable th) {
                try {
                    xa3Var.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th2;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public final void onMeasure(int i, int i2) {
        int iHeight;
        TraceEvent traceEventH0 = TraceEvent.H0("OmniboxSuggestionsList.Measure", null);
        try {
            xa3 xa3Var = new xa3("Android.Omnibox.SuggestionList.MeasureTime2", 2);
            xa3Var.g = SystemClock.currentThreadTimeMillis();
            try {
                e.a aVarC = this.U0.c();
                int i3 = aVarC.b;
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if (layoutParams != null && (layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i3;
                }
                boolean zA = pr1.a();
                if (zA) {
                    iHeight = aVarC.d;
                } else {
                    it3 it3VarD = this.U0.d();
                    Rect rect = this.R0;
                    it3VarD.a.getDecorView().getWindowVisibleDisplayFrame(rect);
                    iHeight = rect.height() - aVarC.b;
                }
                int i4 = aVarC.c;
                o0();
                if (!zA) {
                    int i5 = this.d1;
                    if ((i5 == 1 || i5 == 2) && iHeight < this.a1 && getMeasuredWidth() == i4) {
                        super.onMeasure(this.e1, this.f1);
                        if (this.d1 == 2) {
                            if (traceEventH0 != null) {
                                traceEventH0.close();
                                return;
                            }
                            return;
                        } else {
                            this.d1 = 2;
                            PostTask.b(rh3.d, new zc0(24, this), 300L);
                            xa3Var.close();
                            if (traceEventH0 != null) {
                                traceEventH0.close();
                                return;
                            }
                            return;
                        }
                    }
                    if (this.d1 == 2) {
                        this.d1 = 3;
                    }
                }
                if (iHeight != this.a1) {
                    this.a1 = iHeight;
                    if (this.W0 != null) {
                        PostTask.c(rh3.a, new b50(iHeight, 5, this));
                    }
                }
                int i6 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
                this.e1 = View.MeasureSpec.makeMeasureSpec(i4, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                if (this.U0.b()) {
                    i6 = Integer.MIN_VALUE;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iHeight, i6);
                this.f1 = iMakeMeasureSpec;
                super.onMeasure(this.e1, iMakeMeasureSpec);
                if (this.d1 == 0) {
                    this.d1 = 1;
                }
                xa3Var.close();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } finally {
                try {
                    xa3Var.close();
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.e eVar) {
        tr1 tr1Var = (tr1) eVar;
        this.T0 = tr1Var;
        super.setAdapter(tr1Var);
    }

    public void setEmbedder(e eVar) {
        this.U0 = eVar;
        this.Z0 = eVar.c();
    }

    public void setGestureObserver(a aVar) {
        this.V0 = aVar;
    }

    public void setHeightChangeListener(Callback<Integer> callback) {
        this.W0 = callback;
    }

    public void setSuggestionDropdownOverscrolledToTopListener(Runnable runnable) {
        this.Y0 = runnable;
    }

    public void setSuggestionDropdownScrollListener(Runnable runnable) {
        this.X0 = runnable;
    }

    public ViewGroup getViewGroup() {
        return this;
    }
}
