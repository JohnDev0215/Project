package defpackage;

import android.view.View;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* JADX INFO: compiled from: ToolbarTablet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ed3 extends s41 {
    public final /* synthetic */ ToolbarTablet e;

    public ed3(ToolbarTablet toolbarTablet) {
        this.e = toolbarTablet;
    }

    @Override // defpackage.s41
    public final View a() {
        ToolbarTablet toolbarTablet = this.e;
        return toolbarTablet.D.isFocusable() ? toolbarTablet.D : toolbarTablet.C.isFocusable() ? toolbarTablet.C : (bq.f0.a() || toolbarTablet.B.getVisibility() != 0) ? toolbarTablet.findViewById(R$id.menu_button) : toolbarTablet.findViewById(R$id.home_button);
    }

    @Override // defpackage.s41
    public final View b() {
        boolean zA = bq.f0.a();
        ToolbarTablet toolbarTablet = this.e;
        return (zA && toolbarTablet.B.getVisibility() == 0) ? toolbarTablet.findViewById(R$id.home_button) : toolbarTablet.findViewById(R$id.url_bar);
    }
}
