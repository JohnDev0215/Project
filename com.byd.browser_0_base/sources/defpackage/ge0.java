package defpackage;

import defpackage.ce;
import java.util.ArrayList;
import org.chromium.chrome.browser.omnibox.suggestions.dividerline.DividerLineProcessor;
import org.chromium.chrome.browser.omnibox.suggestions.header.HeaderProcessor;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.omnibox.AutocompleteMatch;

/* JADX INFO: compiled from: DropdownItemViewInfoListBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ge0 {
    public final nw2<Tab> b;
    public final e2 c;
    public DividerLineProcessor d;
    public HeaderProcessor e;
    public nw2<ck2> f;
    public ax0 g;
    public mk0 h;
    public LargeIconBridge i;
    public final ce.a j;
    public final ArrayList a = new ArrayList();
    public int k = -1;

    public ge0(nw2 nw2Var, ce.a aVar, f2 f2Var) {
        this.b = nw2Var;
        this.j = aVar;
        this.c = f2Var;
    }

    public final dw2 a(AutocompleteMatch autocompleteMatch, int i) {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i2 >= arrayList.size()) {
                return null;
            }
            dw2 dw2Var = (dw2) arrayList.get(i2);
            if (dw2Var.d(autocompleteMatch, i)) {
                return dw2Var;
            }
            i2++;
        }
    }

    public final void b(dw2 dw2Var) {
        this.a.add(dw2Var);
    }
}
