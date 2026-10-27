package org.chromium.chrome.browser.ntp;

import android.view.View;
import defpackage.im;
import java.util.ArrayList;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: MyAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e implements View.OnLongClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ g f;

    public e(g gVar, int i) {
        this.f = gVar;
        this.e = i;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        g gVar = this.f;
        ArrayList arrayList = gVar.i;
        int i = this.e;
        if (((im) arrayList.get(i)).d.equals(gVar.h.getString(R$string.card_add))) {
            return true;
        }
        NewTabPageLayout newTabPageLayout = ((i) gVar.j).a;
        newTabPageLayout.I = i;
        newTabPageLayout.k(view, i);
        return true;
    }
}
