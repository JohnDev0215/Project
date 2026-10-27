package defpackage;

import android.view.View;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* JADX INFO: compiled from: ToolbarTablet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cd3 extends s41 {
    public final /* synthetic */ ToolbarTablet e;

    public cd3(ToolbarTablet toolbarTablet) {
        this.e = toolbarTablet;
    }

    @Override // defpackage.s41
    public final View a() {
        boolean zA = bq.f0.a();
        ToolbarTablet toolbarTablet = this.e;
        return zA ? toolbarTablet.findViewById(R$id.menu_button) : toolbarTablet.B.getVisibility() == 0 ? toolbarTablet.findViewById(R$id.home_button) : toolbarTablet.findViewById(R$id.menu_button);
    }

    @Override // defpackage.s41
    public final View b() {
        ToolbarTablet toolbarTablet = this.e;
        return toolbarTablet.D.isFocusable() ? toolbarTablet.D : toolbarTablet.findViewById(R$id.refresh_button);
    }
}
