package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.content.Context;
import android.text.style.StyleSpan;
import defpackage.cw2;
import defpackage.dw2;
import defpackage.e2;
import defpackage.ew2;
import defpackage.h2;
import defpackage.kg2;
import defpackage.l91;
import defpackage.mk0;
import defpackage.oj2;
import defpackage.q8;
import defpackage.vd;
import java.util.Arrays;
import java.util.List;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.omnibox.suggestions.base.d;
import org.chromium.chrome.browser.omnibox.suggestions.base.e;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BaseSuggestionViewProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c implements dw2 {
    public final Context a;
    public final cw2 b;
    public final h2 c;
    public final mk0 d;
    public final int e;
    public final int f;

    public c(Context context, org.chromium.chrome.browser.omnibox.suggestions.d dVar, e2 e2Var, mk0 mk0Var) {
        this.a = context;
        this.b = dVar;
        context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_favicon_size);
        this.e = context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_decoration_image_size);
        this.f = context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_content_height);
        this.d = mk0Var;
        if (e2Var != null) {
            this.c = new h2(context, dVar, e2Var);
        } else {
            this.c = null;
        }
    }

    public static boolean e(ew2 ew2Var, List list) {
        int i = 0;
        if (list == null) {
            return false;
        }
        boolean z = false;
        while (i < list.size()) {
            AutocompleteMatch.a aVar = (AutocompleteMatch.a) list.get(i);
            if ((aVar.b & 2) == 2) {
                ew2Var.setSpan(new StyleSpan(1), Math.min(aVar.a, ew2Var.length()), Math.min(i == list.size() - 1 ? ew2Var.length() : ((AutocompleteMatch.a) list.get(i + 1)).a, ew2Var.length()), 33);
                z = true;
            }
            i++;
        }
        return z;
    }

    public static void g(PropertyModel propertyModel, e eVar) {
        propertyModel.p(d.a, eVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c8 A[ADDED_TO_REGION, REMOVE] */
    @Override // defpackage.dw2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(final int r17, final org.chromium.components.omnibox.AutocompleteMatch r18, org.chromium.ui.modelutil.PropertyModel r19) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.suggestions.base.c.c(int, org.chromium.components.omnibox.AutocompleteMatch, org.chromium.ui.modelutil.PropertyModel):void");
    }

    public void f(AutocompleteMatch autocompleteMatch, int i) {
        kg2.b(autocompleteMatch);
        ((org.chromium.chrome.browser.omnibox.suggestions.d) this.b).h(i, autocompleteMatch, autocompleteMatch.j);
    }

    public final void h(int i, AutocompleteMatch autocompleteMatch, PropertyModel propertyModel) {
        int i2;
        String string;
        Runnable l91Var;
        boolean z = autocompleteMatch.u;
        Context context = this.a;
        if (z) {
            i2 = R$drawable.switch_to_tab;
            string = context.getResources().getString(R$string.accessibility_omnibox_switch_to_tab);
            l91Var = new vd(this, autocompleteMatch, i);
        } else {
            i2 = R$drawable.btn_suggestion_refine;
            string = context.getResources().getString(R$string.accessibility_omnibox_btn_refine, autocompleteMatch.i);
            l91Var = new l91(this, 22, autocompleteMatch);
        }
        e.a aVarC = e.a.c(context, i2);
        aVarC.d = true;
        aVarC.b = true;
        propertyModel.p(d.b, Arrays.asList(new d.a(aVarC.a(), string, (String) null, l91Var)));
    }

    @Override // defpackage.ee0
    public void k(boolean z) {
        h2 h2Var = this.c;
        if (h2Var == null || z) {
            return;
        }
        q8 q8Var = h2Var.d;
        q8Var.getClass();
        q8.a aVar = new q8.a();
        while (aVar.hasNext()) {
            oj2.U(((Integer) aVar.next()).intValue(), 41, "Omnibox.PedalShown");
        }
        int i = h2Var.e;
        if (i != -1) {
            oj2.U(i, 30, "Omnibox.ResumeJourneyShown");
        }
        h2Var.e = -1;
        q8Var.clear();
    }

    @Override // defpackage.ee0
    public final int o() {
        return this.f;
    }
}
