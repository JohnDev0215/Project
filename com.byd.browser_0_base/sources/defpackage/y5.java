package defpackage;

import J.N;
import android.content.Context;
import java.util.HashMap;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AnswerSuggestionProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class y5 extends c {
    public final HashMap g;
    public final sj3 h;
    public final nw2<ax0> i;
    public boolean j;

    public y5(Context context, d dVar, e2 e2Var, sj3 sj3Var, yo yoVar) {
        super(context, dVar, e2Var, null);
        this.g = new HashMap();
        this.h = sj3Var;
        this.i = yoVar;
    }

    @Override // defpackage.ee0
    public final void a() {
        sk skVar = bq.a;
        this.j = N.M09VlOh_("SuggestionAnswersColorReverse");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00fe  */
    @Override // org.chromium.chrome.browser.omnibox.suggestions.base.c, defpackage.dw2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(int r17, org.chromium.components.omnibox.AutocompleteMatch r18, org.chromium.ui.modelutil.PropertyModel r19) {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.y5.c(int, org.chromium.components.omnibox.AutocompleteMatch, org.chromium.ui.modelutil.PropertyModel):void");
    }

    @Override // defpackage.dw2
    public final boolean d(AutocompleteMatch autocompleteMatch, int i) {
        return (autocompleteMatch.h != null) || autocompleteMatch.a == 18;
    }

    @Override // defpackage.ee0
    public final int m() {
        return 2;
    }

    @Override // defpackage.ee0
    public final PropertyModel n() {
        return new PropertyModel(z5.h);
    }
}
