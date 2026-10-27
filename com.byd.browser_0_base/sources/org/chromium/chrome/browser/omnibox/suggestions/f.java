package org.chromium.chrome.browser.omnibox.suggestions;

import android.view.View;
import defpackage.kg2;
import defpackage.xz;
import java.util.ArrayList;
import org.chromium.chrome.browser.omnibox.suggestions.SuggestionLayout;

/* JADX INFO: compiled from: SearchAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ h f;

    public f(h hVar, int i) {
        this.f = hVar;
        this.e = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        h hVar = this.f;
        ArrayList arrayList = hVar.i;
        int i = this.e;
        arrayList.remove(i);
        hVar.o();
        kg2.a.remove(i);
        kg2.c(xz.a.a.edit());
        SuggestionLayout.b bVar = (SuggestionLayout.b) hVar.j;
        if (bVar.a.size() == 0) {
            SuggestionLayout.this.j.setVisibility(8);
        }
    }
}
