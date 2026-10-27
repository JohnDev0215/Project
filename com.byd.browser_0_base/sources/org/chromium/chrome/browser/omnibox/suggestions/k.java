package org.chromium.chrome.browser.omnibox.suggestions;

import defpackage.tr1;
import org.chromium.chrome.browser.omnibox.suggestions.l;
import org.chromium.ui.modelutil.d;

/* JADX INFO: compiled from: SuggestionListViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k implements d.a<Void> {
    public final /* synthetic */ l.a e;

    public k(l.a aVar) {
        this.e = aVar;
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void i(int i, int i2, Object obj) {
        tr1 tr1Var = this.e.b.T0;
        if (tr1Var == null) {
            return;
        }
        tr1Var.I(-1);
    }
}
