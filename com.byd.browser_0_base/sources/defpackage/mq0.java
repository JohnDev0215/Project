package defpackage;

import defpackage.z2;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: FullscreenHtmlApiHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class mq0 extends z2.b {
    public final /* synthetic */ qq0 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public mq0(qq0 qq0Var, z2 z2Var) {
        super(z2Var, false);
        this.h = qq0Var;
    }

    @Override // z2.b
    public final void y1(Tab tab) {
        qq0 qq0Var = this.h;
        qq0Var.t = tab;
        qq0Var.m(tab != null ? tab.d() : null);
        if (tab != null) {
            qq0Var.o(!qq0Var.g());
        }
    }
}
