package defpackage;

import android.content.Context;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.base.e;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TailSuggestionProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o63 extends c {
    public final boolean g;
    public f4 h;

    public o63(Context context, d dVar, e2 e2Var) {
        super(context, dVar, e2Var, null);
        this.g = DeviceFormFactor.a(context);
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.base.c, defpackage.dw2
    public final void c(int i, AutocompleteMatch autocompleteMatch, PropertyModel propertyModel) {
        super.c(i, autocompleteMatch, propertyModel);
        propertyModel.p(p63.c, this.h);
        propertyModel.p(p63.b, autocompleteMatch.i);
        ew2 ew2Var = new ew2(autocompleteMatch.d);
        c.e(ew2Var, autocompleteMatch.e);
        propertyModel.p(p63.a, ew2Var);
        e.a aVarC = e.a.c(this.a, R$drawable.ic_suggestion_magnifier);
        aVarC.b = true;
        c.g(propertyModel, aVarC.a());
        h(i, autocompleteMatch, propertyModel);
    }

    @Override // defpackage.dw2
    public final boolean d(AutocompleteMatch autocompleteMatch, int i) {
        return this.g && autocompleteMatch.a == 10;
    }

    @Override // defpackage.ee0
    public final void l() {
        this.h = new f4();
    }

    @Override // defpackage.ee0
    public final int m() {
        return 4;
    }

    @Override // defpackage.ee0
    public final PropertyModel n() {
        return new PropertyModel(p63.e);
    }
}
