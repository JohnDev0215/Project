package org.chromium.chrome.browser.toolbar.top;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import defpackage.n3;
import defpackage.w43;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.toolbar.a;
import org.chromium.ui.widget.ChromeImageButton;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class ToggleTabStackButton extends ChromeImageButton implements a.InterfaceC0115a, View.OnClickListener, View.OnLongClickListener {
    public w43 h;
    public org.chromium.chrome.browser.toolbar.a i;
    public View.OnClickListener j;
    public View.OnLongClickListener k;

    public ToggleTabStackButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.toolbar.a.InterfaceC0115a
    public final void c(int i, boolean z) {
        setEnabled(i >= 1);
        this.h.e(i, z);
    }

    public w43 getTabSwitcherButtonDrawable() {
        return w43.d(getContext(), 3);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.j == null || !isClickable()) {
            return;
        }
        this.j.onClick(this);
    }

    @Override // org.chromium.ui.widget.ChromeImageButton, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        w43 tabSwitcherButtonDrawable = getTabSwitcherButtonDrawable();
        this.h = tabSwitcherButtonDrawable;
        setImageDrawable(tabSwitcherButtonDrawable);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent traceEventH0 = TraceEvent.H0("ToggleTabStackButton.onLayout", null);
        try {
            super.onLayout(z, i, i2, i3, i4);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        if (this.k != null && isLongClickable()) {
            return this.k.onLongClick(view);
        }
        return Toast.f(getContext(), view, getResources().getString(R$string.open_tabs));
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        TraceEvent traceEventH0 = TraceEvent.H0("ToggleTabStackButton.onMeasure", null);
        try {
            super.onMeasure(i, i2);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public void setBrandedColorScheme(int i) {
        this.h.c(n3.y0(getContext()));
    }

    public void setOnTabSwitcherClickHandler(View.OnClickListener onClickListener) {
        this.j = onClickListener;
    }

    public void setOnTabSwitcherLongClickHandler(View.OnLongClickListener onLongClickListener) {
        this.k = onLongClickListener;
    }

    public void setTabCountProvider(org.chromium.chrome.browser.toolbar.a aVar) {
        this.i = aVar;
        aVar.a(this);
    }

    public ToggleTabStackButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
