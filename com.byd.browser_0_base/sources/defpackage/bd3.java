package defpackage;

import android.view.View;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* JADX INFO: compiled from: ToolbarTablet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bd3 extends s41 {
    public final /* synthetic */ ToolbarTablet e;

    public bd3(ToolbarTablet toolbarTablet) {
        this.e = toolbarTablet;
    }

    @Override // defpackage.s41
    public final View a() {
        boolean zA = bq.f0.a();
        ToolbarTablet toolbarTablet = this.e;
        return zA ? toolbarTablet.findViewById(R$id.refresh_button) : toolbarTablet.findViewById(R$id.menu_button);
    }

    @Override // defpackage.s41
    public final View b() {
        boolean zA = bq.f0.a();
        ToolbarTablet toolbarTablet = this.e;
        return zA ? toolbarTablet.findViewById(R$id.url_bar) : toolbarTablet.C.isFocusable() ? toolbarTablet.C : toolbarTablet.D.isFocusable() ? toolbarTablet.D : toolbarTablet.findViewById(R$id.refresh_button);
    }
}
