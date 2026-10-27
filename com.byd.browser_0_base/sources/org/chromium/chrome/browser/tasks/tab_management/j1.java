package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import defpackage.v33;
import defpackage.w33;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.z0;

/* JADX INFO: compiled from: TabSuggestionMessageService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j1 extends z0.e {
    public final /* synthetic */ Callback c;
    public final /* synthetic */ v33 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j1(Context context, z0.d dVar, Callback callback, v33 v33Var) {
        super(context, dVar);
        this.c = callback;
        this.d = v33Var;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.z0.e
    public final void a() {
        super.a();
        this.c.onResult(new w33(this.d, 1, null));
    }
}
