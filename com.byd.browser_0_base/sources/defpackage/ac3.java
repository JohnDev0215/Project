package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.home.toolbar.DownloadHomeToolbar;
import org.chromium.components.browser_ui.widget.FadingShadowView;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;
import org.chromium.components.browser_ui.widget.selectable_list.a;

/* JADX INFO: compiled from: ToolbarCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class ac3 implements a.InterfaceC0128a<f81>, vb {
    public final c e;
    public final b f;
    public final ViewGroup g;
    public final DownloadHomeToolbar h;
    public final FadingShadowView i;
    public final aq1<Boolean> j;
    public boolean k;

    /* JADX INFO: compiled from: ToolbarCoordinator.java */
    public class a implements SelectableListToolbar.b {
        public a() {
        }

        @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.b
        public final void k() {
            ac3 ac3Var = ac3.this;
            ((g60) ac3Var.e).a(null);
            ac3Var.d();
        }

        @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.b
        public final void r(String str) {
            ((g60) ac3.this.e).a(str);
        }
    }

    /* JADX INFO: compiled from: ToolbarCoordinator.java */
    public interface b {
    }

    /* JADX INFO: compiled from: ToolbarCoordinator.java */
    public interface c {
    }

    public ac3(Context context, b bVar, g60 g60Var, org.chromium.components.browser_ui.widget.selectable_list.a aVar, boolean z, xe3 xe3Var) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.j = aq1Var;
        a aVar2 = new a();
        this.f = bVar;
        this.e = g60Var;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R$layout.download_home_toolbar, (ViewGroup) null);
        this.g = viewGroup;
        DownloadHomeToolbar downloadHomeToolbar = (DownloadHomeToolbar) viewGroup.findViewById(R$id.download_toolbar);
        this.h = downloadHomeToolbar;
        FadingShadowView fadingShadowView = (FadingShadowView) viewGroup.findViewById(R$id.shadow);
        this.i = fadingShadowView;
        downloadHomeToolbar.y(aVar, R$string.menu_downloads, R$id.normal_menu_group, R$id.selection_mode_menu_group, z);
        downloadHomeToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { // from class: zb3
            /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x0054  */
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final boolean onMenuItemClick(android.view.MenuItem r8) {
                /*
                    Method dump skipped, instruction units count: 206
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.zb3.onMenuItemClick(android.view.MenuItem):boolean");
            }
        });
        downloadHomeToolbar.z(aVar2, R$string.download_manager_search, R$id.search_menu_id);
        xe3Var.c(new xm1(xe3Var, 3, downloadHomeToolbar));
        fadingShadowView.a(context.getColor(R$color.toolbar_shadow_color));
        if (!z) {
            downloadHomeToolbar.getMenu().removeItem(R$id.close_menu_id);
        }
        aq1Var.r(Boolean.valueOf(downloadHomeToolbar.A()));
        downloadHomeToolbar.d0.p(new xr(17, aq1Var));
    }

    public final void d() {
        this.i.setVisibility(this.k || this.h.A() ? 0 : 8);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        d();
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.j;
    }

    @Override // defpackage.vb
    public final int o() {
        int i;
        DownloadHomeToolbar downloadHomeToolbar = this.h;
        if (downloadHomeToolbar.A()) {
            downloadHomeToolbar.x();
            i = 1;
        } else {
            i = 0;
        }
        return i ^ 1;
    }
}
