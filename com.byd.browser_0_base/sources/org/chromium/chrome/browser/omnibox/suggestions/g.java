package org.chromium.chrome.browser.omnibox.suggestions;

import android.view.View;
import java.util.ArrayList;
import org.chromium.chrome.browser.omnibox.suggestions.h;
import org.chromium.components.omnibox.AutocompleteMatch;

/* JADX INFO: compiled from: SearchAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ h f;

    public g(h hVar, int i) {
        this.f = hVar;
        this.e = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        h hVar = this.f;
        h.b bVar = hVar.j;
        ArrayList arrayList = hVar.i;
        int i = this.e;
        ((a) SuggestionLayout.this.n).a.e.h.h(0, (AutocompleteMatch) arrayList.get(i), ((AutocompleteMatch) arrayList.get(i)).j);
    }
}
