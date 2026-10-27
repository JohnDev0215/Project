package org.chromium.chrome.browser.omnibox.suggestions;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.in3;
import defpackage.it3;
import defpackage.kg2;
import defpackage.ko0;
import defpackage.om3;
import defpackage.pr1;
import defpackage.rh3;
import defpackage.xa3;
import defpackage.zc0;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.omnibox.suggestions.e;
import org.chromium.chrome.browser.omnibox.suggestions.h;
import org.chromium.components.omnibox.AutocompleteMatch;

/* JADX INFO: loaded from: classes2.dex */
public class SuggestionLayout extends LinearLayout {
    public final Rect e;
    public e f;
    public e.a g;
    public int h;
    public h i;
    public LinearLayout j;
    public int k;
    public int l;
    public int m;
    public c n;

    public class b implements h.b {
        public final /* synthetic */ ArrayList a;

        public b(ArrayList arrayList) {
            this.a = arrayList;
        }
    }

    public interface c {
    }

    public SuggestionLayout(Context context) {
        super(context);
        this.e = new Rect();
        this.g = e.a.g;
        this.k = 0;
        b(context);
    }

    public final void a() {
        if (pr1.e(getContext())) {
            setTranslationX(this.g.a);
        } else {
            setPadding(this.g.e, getPaddingTop(), this.g.f, getPaddingBottom());
        }
    }

    public final void b(Context context) {
        ArrayList<AutocompleteMatch> arrayListA = kg2.a();
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.search_layout, (ViewGroup) this, true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        pr1.e(context);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.omnibox_suggestion_list_padding_bottom);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(this, 0, 0, 0, dimensionPixelOffset);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.lin_history);
        this.j = linearLayout;
        linearLayout.setVisibility(0);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.search_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        h hVar = new h(context, arrayListA);
        this.i = hVar;
        recyclerView.setAdapter(hVar);
        ((LinearLayout) viewInflate.findViewById(R$id.del_lin)).setOnClickListener(new a());
        this.i.j = new b(arrayListA);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f.onAttachedToWindow();
        this.k = 0;
        this.g = this.f.a(new ko0(24, this));
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
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

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int iHeight;
        TraceEvent traceEventH0 = TraceEvent.H0("OmniboxSuggestionsList.Measure", null);
        try {
            xa3 xa3Var = new xa3("Android.Omnibox.SuggestionList.MeasureTime2", 2);
            xa3Var.g = SystemClock.currentThreadTimeMillis();
            try {
                e.a aVarC = this.f.c();
                int i3 = aVarC.b;
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if (layoutParams != null && (layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i3;
                }
                boolean zA = pr1.a();
                if (zA) {
                    iHeight = aVarC.d;
                } else {
                    it3 it3VarD = this.f.d();
                    Rect rect = this.e;
                    it3VarD.a.getDecorView().getWindowVisibleDisplayFrame(rect);
                    iHeight = rect.height() - aVarC.b;
                }
                int i4 = aVarC.c;
                a();
                if (!zA) {
                    int i5 = this.k;
                    if ((i5 == 1 || i5 == 2) && iHeight < this.h && getMeasuredWidth() == i4) {
                        super.onMeasure(this.l, this.m);
                        if (this.k == 2) {
                            if (traceEventH0 != null) {
                                traceEventH0.close();
                                return;
                            }
                            return;
                        } else {
                            this.k = 2;
                            PostTask.b(rh3.d, new zc0(25, this), 300L);
                            xa3Var.close();
                            if (traceEventH0 != null) {
                                traceEventH0.close();
                                return;
                            }
                            return;
                        }
                    }
                    if (this.k == 2) {
                        this.k = 3;
                    }
                }
                if (iHeight != this.h) {
                    this.h = iHeight;
                }
                int i6 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
                this.l = View.MeasureSpec.makeMeasureSpec(i4, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                if (this.f.b()) {
                    i6 = Integer.MIN_VALUE;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iHeight, i6);
                this.m = iMakeMeasureSpec;
                super.onMeasure(this.l, iMakeMeasureSpec);
                if (this.k == 0) {
                    this.k = 1;
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

    public void setEmbedder(e eVar) {
        this.f = eVar;
        this.g = eVar.c();
    }

    public void setSearchClick(c cVar) {
        this.n = cVar;
    }

    public SuggestionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new Rect();
        this.g = e.a.g;
        this.k = 0;
        b(context);
    }

    public SuggestionLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new Rect();
        this.g = e.a.g;
        this.k = 0;
        b(context);
    }

    public ViewGroup getViewGroup() {
        return this;
    }

    public class a implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
        }
    }
}
