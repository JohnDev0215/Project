package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: BasicSuggestionProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ce extends c {
    public final sj3 g;
    public final a h;

    /* JADX INFO: compiled from: BasicSuggestionProcessor.java */
    public interface a {
        boolean f(GURL gurl);
    }

    public ce(Context context, d dVar, e2 e2Var, sj3 sj3Var, mk0 mk0Var, a aVar) {
        super(context, dVar, e2Var, mk0Var);
        this.g = sj3Var;
        this.h = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.util.ArrayList] */
    @Override // org.chromium.chrome.browser.omnibox.suggestions.base.c, defpackage.dw2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(int r11, org.chromium.components.omnibox.AutocompleteMatch r12, org.chromium.ui.modelutil.PropertyModel r13) {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ce.c(int, org.chromium.components.omnibox.AutocompleteMatch, org.chromium.ui.modelutil.PropertyModel):void");
    }

    @Override // defpackage.dw2
    public final boolean d(AutocompleteMatch autocompleteMatch, int i) {
        return true;
    }

    @Override // defpackage.ee0
    public final int m() {
        return 0;
    }

    @Override // defpackage.ee0
    public final PropertyModel n() {
        return new PropertyModel(fw2.f);
    }
}
