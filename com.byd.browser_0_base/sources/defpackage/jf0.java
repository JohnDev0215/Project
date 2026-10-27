package defpackage;

import android.content.Context;
import java.util.Arrays;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.base.d;
import org.chromium.chrome.browser.omnibox.suggestions.base.e;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: EditUrlSuggestionProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jf0 extends c {
    public final Context g;
    public final ga h;
    public final nw2<ck2> i;
    public final nw2<Tab> j;
    public boolean k;

    public jf0(Context context, d dVar, ga gaVar, mk0 mk0Var, nw2 nw2Var, yq2 yq2Var) {
        super(context, dVar, null, mk0Var);
        this.g = context;
        this.h = gaVar;
        this.j = nw2Var;
        this.i = yq2Var;
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.base.c, defpackage.dw2
    public final void c(int i, AutocompleteMatch autocompleteMatch, PropertyModel propertyModel) {
        super.c(i, autocompleteMatch, propertyModel);
        propertyModel.p(fw2.b, new ew2(this.j.get().getTitle()));
        propertyModel.p(fw2.c, new ew2(autocompleteMatch.d));
        int i2 = R$drawable.ic_globe_24dp;
        Context context = this.a;
        e.a aVarC = e.a.c(context, i2);
        aVarC.b = true;
        c.g(propertyModel, aVarC.a());
        e.a aVarC2 = e.a.c(context, R$drawable.ic_content_copy_black);
        aVarC2.d = true;
        aVarC2.b = true;
        e eVarA = aVarC2.a();
        int i3 = R$string.copy_link;
        z8 z8Var = new z8(this, 18, autocompleteMatch);
        Context context2 = this.g;
        e.a aVarC3 = e.a.c(context, R$drawable.bookmark_edit_active);
        aVarC3.d = true;
        aVarC3.b = true;
        propertyModel.p(org.chromium.chrome.browser.omnibox.suggestions.base.d.b, Arrays.asList(new d.a(context2, eVarA, i3, z8Var), new d.a(context2, aVarC3.a(), R$string.bookmark_item_edit, new a9(this, 14, autocompleteMatch))));
        this.d.b(autocompleteMatch.j, false, new i40(this, propertyModel));
    }

    @Override // defpackage.dw2
    public final boolean d(AutocompleteMatch autocompleteMatch, int i) {
        Tab tab;
        if (i != 0 || (tab = this.j.get()) == null || !tab.isInitialized() || tab.isNativePage() || hd2.z1(tab) || autocompleteMatch.a != 0 || !autocompleteMatch.j.equals(tab.getUrl())) {
            return false;
        }
        if (!this.k) {
            this.k = true;
            ((org.chromium.chrome.browser.omnibox.c) this.h).a("");
        }
        return true;
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.base.c
    public final void f(AutocompleteMatch autocompleteMatch, int i) {
        super.f(autocompleteMatch, i);
        n3.X0("Omnibox.EditUrlSuggestion.Tap");
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.base.c, defpackage.ee0
    public final void k(boolean z) {
        super.k(z);
        if (z) {
            return;
        }
        this.k = false;
    }

    @Override // defpackage.ee0
    public final int m() {
        return 1;
    }

    @Override // defpackage.ee0
    public final PropertyModel n() {
        return new PropertyModel(fw2.f);
    }
}
