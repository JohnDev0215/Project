package defpackage;

import J.N;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.password_check.b;
import org.chromium.chrome.browser.privacy_guide.a;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsFragmentV3;
import org.chromium.chrome.browser.toolbar.top.b;
import org.chromium.content.browser.ContentUiEventHandler;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.modelutil.l;
import org.chromium.ui.modelutil.o;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pd implements a.f, l.b, PreferenceGroup.b, gq, g.e, b.a, a.InterfaceC0110a, r02, b.InterfaceC0116b, o.a, PermissionCallback, WebContentsImpl.d {
    public final /* synthetic */ int e;

    public /* synthetic */ pd(int i) {
        this.e = i;
    }

    @Override // defpackage.r02
    public final q02 a() {
        switch (this.e) {
            case 15:
                if (m02.j == null) {
                    m02.j = new rh0();
                }
                return m02.j;
            default:
                if (m02.l == null) {
                    m02.l = new ch0();
                }
                return m02.l;
        }
    }

    @Override // org.chromium.ui.modelutil.o.a
    public final int f(Object obj) {
        return ((xa1) obj).a;
    }

    @Override // org.chromium.content.browser.webcontents.WebContentsImpl.d
    public final Object i(WebContentsImpl webContentsImpl) {
        switch (this.e) {
            case 21:
                return new ContentUiEventHandler(webContentsImpl);
            case 22:
                return new sm3(webContentsImpl);
            default:
                return new ImeAdapterImpl(webContentsImpl);
        }
    }

    @Override // org.chromium.ui.modelutil.l.b
    public final Object j(int i, RecyclerView recyclerView) {
        if (i != 0) {
            return null;
        }
        return new m4(recyclerView, R$layout.keyboard_accessory_sheet_tab_password_info, new zf(9));
    }

    @Override // org.chromium.chrome.browser.customtabs.a.f
    public final Object o(a.e eVar) {
        return Boolean.FALSE;
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        int i = PrivacySandboxSettingsFragmentV3.l0;
        if ("privacy_sandbox_toggle".equals(preference.p)) {
            return N.MNmYFihT();
        }
        return false;
    }

    @Override // org.chromium.ui.permissions.PermissionCallback
    public final void s(int[] iArr, String[] strArr) {
    }
}
