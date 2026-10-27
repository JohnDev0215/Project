package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import android.content.Context;
import defpackage.b43;
import defpackage.dr;
import defpackage.v33;
import defpackage.w33;
import defpackage.y13;
import java.util.Iterator;
import java.util.LinkedList;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.g;
import org.chromium.chrome.browser.tasks.tab_management.z0;

/* JADX INFO: compiled from: TabSuggestionMessageService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m1 extends g implements b43 {
    public final Context g;
    public final y13 h;
    public final z0.d i;

    /* JADX INFO: compiled from: TabSuggestionMessageService.java */
    public class a implements g.a {
        public final v33 a;
        public final Callback<w33> b;

        public a(v33 v33Var, dr drVar) {
            this.a = v33Var;
            this.b = drVar;
        }
    }

    public m1(Activity activity, y13 y13Var, a1 a1Var) {
        super(1);
        this.g = activity;
        this.h = y13Var;
        this.i = a1Var;
    }

    @Override // defpackage.b43
    public final void a() {
        g();
    }

    @Override // defpackage.b43
    public final void b(LinkedList linkedList, dr drVar) {
        if (linkedList.size() == 0) {
            return;
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            e(new a((v33) it.next(), drVar));
        }
    }
}
