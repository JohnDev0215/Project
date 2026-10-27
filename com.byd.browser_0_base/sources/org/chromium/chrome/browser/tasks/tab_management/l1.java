package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.w33;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.m1;

/* JADX INFO: compiled from: TabSuggestionMessageService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l1 implements MessageCardView.a {
    public final /* synthetic */ m1.a a;

    public l1(m1.a aVar) {
        this.a = aVar;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.a
    public final void a(int i) {
        m1.a aVar = this.a;
        m1.this.getClass();
        aVar.b.onResult(new w33(aVar.a, 0, null));
    }
}
