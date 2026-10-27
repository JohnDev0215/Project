package org.chromium.chrome.browser.toolbar.optional_button;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: OptionalButtonView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ OptionalButtonView f;

    public c(OptionalButtonView optionalButtonView, boolean z) {
        this.f = optionalButtonView;
        this.e = z;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        OptionalButtonView optionalButtonView = this.f;
        ViewGroup viewGroup = optionalButtonView.l;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.g.c(viewGroup)) {
            optionalButtonView.f(this.e);
            optionalButtonView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }
}
