package org.chromium.chrome.browser.omnibox.suggestions;

import defpackage.tr1;
import org.chromium.chrome.browser.omnibox.suggestions.l;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.d;

/* JADX INFO: compiled from: SuggestionListViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j implements d.a<Void> {
    public final /* synthetic */ l.a e;
    public final /* synthetic */ PropertyModel f;

    public j(l.a aVar, PropertyModel propertyModel) {
        this.e = aVar;
        this.f = propertyModel;
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void b(int i, int i2) {
        l.a(this.e, this.f);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void i(int i, int i2, Object obj) {
        tr1 tr1Var = this.e.b.T0;
        if (tr1Var == null) {
            return;
        }
        tr1Var.I(-1);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void j(int i, int i2) {
        l.a(this.e, this.f);
    }
}
