package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import defpackage.ax0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.base.e;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: EntitySuggestionProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vh0 extends c {
    public final HashMap g;
    public final nw2<ax0> h;

    public vh0(Context context, d dVar, e2 e2Var, yo yoVar) {
        super(context, dVar, e2Var, null);
        this.g = new HashMap();
        this.h = yoVar;
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.base.c, defpackage.dw2
    public final void c(int i, AutocompleteMatch autocompleteMatch, PropertyModel propertyModel) {
        ax0 ax0Var;
        super.c(i, autocompleteMatch, propertyModel);
        e.a aVarC = e.a.c(this.a, R$drawable.ic_suggestion_magnifier);
        aVarC.b = true;
        c.g(propertyModel, aVarC.a());
        if (SysUtils.amountOfPhysicalMemoryKB() >= 1572864 || iv.e().g("disable-low-end-device-mode")) {
            String str = autocompleteMatch.l;
            if (!TextUtils.isEmpty(str)) {
                try {
                    c.g(propertyModel, new e(new ColorDrawable(Color.parseColor(str)), true, true, false));
                } catch (IllegalArgumentException unused) {
                }
            }
            Object obj = ThreadUtils.a;
            GURL gurl = autocompleteMatch.k;
            if (!gurl.k() && (ax0Var = this.h.get()) != null) {
                HashMap map = this.g;
                if (map.containsKey(gurl)) {
                    ((List) map.get(gurl)).add(propertyModel);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(propertyModel);
                    map.put(gurl, arrayList);
                    ax0Var.d(ax0.a.b(gurl.j(), "EntitySuggestions"), new gp(this, 16, gurl));
                }
            }
        }
        propertyModel.p(wh0.a, autocompleteMatch.d);
        propertyModel.p(wh0.b, autocompleteMatch.f);
        h(i, autocompleteMatch, propertyModel);
    }

    @Override // defpackage.dw2
    public final boolean d(AutocompleteMatch autocompleteMatch, int i) {
        return autocompleteMatch.a == 9;
    }

    @Override // defpackage.ee0
    public final int m() {
        return 3;
    }

    @Override // defpackage.ee0
    public final PropertyModel n() {
        return new PropertyModel(wh0.d);
    }
}
