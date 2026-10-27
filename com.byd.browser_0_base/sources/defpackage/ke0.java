package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.Size;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.widget.SearchView;
import androidx.preference.Preference;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.e;
import defpackage.af1;
import defpackage.b10;
import defpackage.dq3;
import defpackage.g72;
import defpackage.lu;
import defpackage.m60;
import defpackage.p9;
import defpackage.pz1;
import defpackage.vg2;
import defpackage.wa2;
import defpackage.xb;
import defpackage.y71;
import defpackage.z90;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.base.UnguessableToken;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataTabsFragment;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.content_creation.notes.NoteCreationDialog;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.download.home.toolbar.DownloadHomeToolbar;
import org.chromium.chrome.browser.language.settings.SelectLanguageFragment;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.o1;
import org.chromium.chrome.browser.tasks.tab_management.z0;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.top.n;
import org.chromium.chrome.browser.ui.signin.h;
import org.chromium.components.browser_ui.site_settings.AllSiteSettings;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.messages.MessageContainer;
import org.chromium.components.messages.b;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;
import org.chromium.components.paintpreview.player.a;
import org.chromium.components.paintpreview.player.b;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.ChildAccountInfoFetcher;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.webauthn.InternalAuthenticator;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.ui.base.SelectFileDialog;
import org.chromium.ui.base.SelectFileDialog.b;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ke0 implements InterfaceC0220y, xb.c, MessagePort.a, z90.a, lu.a, e.b, NoteCreationDialog.d, a.f, wa2.a, k32, SearchView.OnCloseListener, Preference.c, y71.a, n02, vi2, g72.a, j0.p, SharedPreferencesManager.b, af1.a, n.a, RadioButtonWithDescription.a, h.b, dq3.a, vg2.a, androidx.core.view.accessibility.a, a.InterfaceC0136a, pz1.a, AccountManagerFacade.a, p9.c, p9.d, PermissionCallback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ke0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // xb.c
    public boolean a() {
        boolean z;
        boolean z2 = false;
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 2:
                uc0 uc0Var = (uc0) ((qc0) obj);
                org.chromium.components.browser_ui.widget.selectable_list.a<f81> aVar = uc0Var.b.c.o;
                if (aVar.e()) {
                    aVar.b();
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
                DownloadHomeToolbar downloadHomeToolbar = uc0Var.d.h;
                if (downloadHomeToolbar.A()) {
                    downloadHomeToolbar.x();
                    z2 = true;
                }
                return z2;
            case 3:
                am3 am3Var = ((vl3) ((ul3) obj)).b;
                am3Var.getClass();
                PropertyModel.n nVar = bm3.b;
                PropertyModel propertyModel = am3Var.c;
                boolean zK = propertyModel.k(nVar);
                if ((zK || propertyModel.k(bm3.a)) ? false : true) {
                    n3.a1(am3Var.f.a, 5);
                } else if (zK) {
                    oj2.U(0, 3, "VideoTutorials.LanguagePicker.Action");
                }
                return false;
            case 4:
                return ((eg) obj).a();
            default:
                return ((gv0) obj).a();
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.n.a
    public void b(boolean z) {
        ((org.chromium.chrome.browser.toolbar.top.h) this.f).getClass();
    }

    @Override // defpackage.sl
    public void c(Boolean bool) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 2:
                ((Callback) obj).onResult(bool);
                break;
            default:
                Boolean bool2 = bool;
                oj2.O("WebAuthentication.IsUVPlatformAuthenticatorAvailable2", bool2.booleanValue());
                ((p9.c) obj).c(bool2);
                break;
        }
    }

    @Override // defpackage.tl
    public void d(Integer num, ib1 ib1Var, jq3 jq3Var) {
        Integer num2 = num;
        ib1 ib1Var2 = ib1Var;
        long j = ((InternalAuthenticator) this.f).a;
        if (j != 0) {
            N.MpeuI$e6(j, num2.intValue(), ib1Var2 == null ? null : ib1Var2.b());
        }
    }

    @Override // g72.a
    public String e(Context context, g72 g72Var) {
        int size = g72.e(context, g72Var, ((z0) this.f).a).size();
        return size == 1 ? g72Var.i() : context.getResources().getQuantityString(R$plurals.bottom_tab_grid_title_placeholder, size, Integer.valueOf(size));
    }

    @Override // androidx.core.view.accessibility.a
    public boolean f(View view) {
        MessageContainer.b bVar = ((MessageContainer) this.f).e;
        if (bVar == null) {
            return false;
        }
        b bVar2 = (b) bVar;
        bVar2.e.a(4, bVar2.d);
        return true;
    }

    @Override // org.chromium.components.paintpreview.player.a.InterfaceC0136a
    public void g(UnguessableToken unguessableToken, UnguessableToken[] unguessableTokenArr, int[] iArr, int[] iArr2, int[] iArr3, UnguessableToken[] unguessableTokenArr2, int[] iArr4, float f, long j) {
        org.chromium.components.paintpreview.player.b bVar = (org.chromium.components.paintpreview.player.b) this.f;
        bVar.getClass();
        TraceEvent.q("PlayerManager.onCompositorReady", null);
        HashMap map = new HashMap();
        for (int i = 0; i < unguessableTokenArr.length; i++) {
            boolean z = bVar.h;
            int i2 = z ? 0 : iArr2[i * 2];
            int i3 = z ? 0 : iArr2[(i * 2) + 1];
            UnguessableToken unguessableToken2 = unguessableTokenArr[i];
            int i4 = i * 2;
            map.put(unguessableToken2, new nv1(unguessableToken2, iArr[i4], iArr[i4 + 1], i2, i3));
        }
        int i5 = 0;
        for (int i6 = 0; i6 < unguessableTokenArr.length; i6++) {
            nv1 nv1Var = (nv1) map.get(unguessableTokenArr[i6]);
            int i7 = iArr3[i6];
            nv1[] nv1VarArr = new nv1[i7];
            Rect[] rectArr = new Rect[i7];
            int i8 = 0;
            while (i8 < i7) {
                nv1VarArr[i8] = (nv1) map.get(unguessableTokenArr2[i5]);
                int i9 = i5 * 4;
                int i10 = iArr4[i9];
                int i11 = iArr4[i9 + 1];
                rectArr[i8] = new Rect(i10, i11, iArr4[i9 + 2] + i10, iArr4[i9 + 3] + i11);
                i8++;
                i5++;
            }
            nv1Var.d = nv1VarArr;
            nv1Var.e = rectArr;
        }
        bVar.c = (nv1) map.get(unguessableToken);
        FrameLayout frameLayout = bVar.e;
        float fMax = Math.max(f, frameLayout.getWidth() / bVar.c.b);
        Context context = bVar.a;
        PlayerCompositorDelegateImpl playerCompositorDelegateImpl = bVar.b;
        nv1 nv1Var2 = bVar.c;
        UnguessableToken unguessableToken3 = nv1Var2.a;
        int i12 = nv1Var2.b;
        int i13 = nv1Var2.c;
        int i14 = nv1Var2.f;
        int i15 = nv1Var2.g;
        j22 j22Var = bVar.f;
        f22 f22Var = bVar.g;
        b.InterfaceC0137b interfaceC0137b = bVar.i;
        Objects.requireNonNull(interfaceC0137b);
        w12 w12Var = new w12(context, playerCompositorDelegateImpl, unguessableToken3, i12, i13, i14, i15, fMax, true, j22Var, f22Var, new g22(interfaceC0137b, 1), new yq2(25, interfaceC0137b), new es3(17, bVar));
        bVar.d = w12Var;
        bVar.a(w12Var, bVar.c);
        frameLayout.addView(bVar.d.d, new FrameLayout.LayoutParams(-1, -1));
        j22 j22Var2 = bVar.f;
        if (j22Var2 != null) {
            frameLayout.addView(j22Var2.a);
        }
        bVar.j = j;
        TraceEvent.F(bVar.hashCode(), "paint_preview PlayerManager init");
        interfaceC0137b.i();
        TraceEvent.C("PlayerManager.onCompositorReady");
    }

    @Override // defpackage.vi2
    public Object get() {
        b10.a aVar = (b10.a) this.f;
        String[] strArr = a10.q;
        return aVar.build().l().B();
    }

    @Override // com.google.android.material.tabs.e.b
    public void h(TabLayout.g gVar, int i) {
        String string;
        ClearBrowsingDataTabsFragment clearBrowsingDataTabsFragment = (ClearBrowsingDataTabsFragment) this.f;
        int i2 = ClearBrowsingDataTabsFragment.a0;
        if (i == 0) {
            string = clearBrowsingDataTabsFragment.D().getString(R$string.clear_browsing_data_basic_tab_title);
        } else {
            if (i != 1) {
                clearBrowsingDataTabsFragment.getClass();
                throw new uv(gq2.e("invalid position: ", i));
            }
            string = clearBrowsingDataTabsFragment.D().getString(R$string.prefs_section_advanced);
        }
        gVar.d(string);
    }

    @Override // org.chromium.chrome.browser.preferences.SharedPreferencesManager.b
    public void i(String str) {
        o1 o1Var = (o1) this.f;
        o1Var.getClass();
        if ("Chrome.PriceTracking.TrackPricesOnTabs".equals(str)) {
            y13 y13Var = o1Var.o;
            if (y13Var.r() || !y13Var.t()) {
                return;
            }
            o1Var.w(y13Var.u().c(), false, o1.v(o1Var.p));
        }
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription.a
    public void j(RadioButtonWithDescription radioButtonWithDescription) {
        ((org.chromium.chrome.browser.ui.signin.b) this.f).c.l(di1.l, false);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade.a
    public void k(boolean z) {
        ChildAccountInfoFetcher childAccountInfoFetcher = (ChildAccountInfoFetcher) this.f;
        CoreAccountInfo coreAccountInfo = childAccountInfoFetcher.b;
        coreAccountInfo.getEmail();
        N.MBZpZMbr(childAccountInfoFetcher.a, coreAccountInfo.getId(), z);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.j0.p
    public void l(int i, Size size, t02 t02Var, boolean z, boolean z2, boolean z3) {
        ((TabContentManager) this.f).e(i, size, t02Var, z, z2);
    }

    @Override // androidx.preference.Preference.c
    public boolean n(Preference preference, Object obj) {
        PasswordSettings passwordSettings = (PasswordSettings) this.f;
        int i = PasswordSettings.n0;
        Boolean bool = (Boolean) obj;
        passwordSettings.N0().e("credentials_enable_service", bool.booleanValue());
        oj2.O("PasswordManager.Settings.ToggleOfferToSavePasswords", bool.booleanValue());
        if (!bool.booleanValue() || !qx1.d()) {
            return true;
        }
        PrefService prefServiceK = oj2.k(Profile.d());
        if (!prefServiceK.a("unenrolled_from_google_mobile_services_due_to_errors")) {
            return true;
        }
        prefServiceK.e("unenrolled_from_google_mobile_services_due_to_errors", false);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0042  */
    @Override // org.chromium.chrome.browser.customtabs.a.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object o(org.chromium.chrome.browser.customtabs.a.e r4) {
        /*
            r3 = this;
            java.lang.Object r3 = r3.f
            java.lang.String r3 = (java.lang.String) r3
            z22 r4 = r4.d
            org.chromium.content_public.browser.MessagePort[] r0 = r4.d
            if (r0 == 0) goto L42
            r1 = 0
            r0 = r0[r1]
            boolean r0 = r0.isClosed()
            if (r0 == 0) goto L14
            goto L42
        L14:
            org.chromium.content_public.browser.WebContents r0 = r4.c
            if (r0 == 0) goto L42
            boolean r0 = r0.isDestroyed()
            if (r0 == 0) goto L1f
            goto L42
        L1f:
            org.chromium.content_public.browser.MessagePort[] r0 = r4.d
            r0 = r0[r1]
            boolean r0 = r0.c()
            if (r0 == 0) goto L31
            java.lang.String r3 = "cr_PostMessageHandler"
            java.lang.String r4 = "Not sending postMessage as channel has been transferred."
            android.util.Log.e(r3, r4)
            goto L42
        L31:
            d73 r0 = defpackage.rh3.a
            y22 r2 = new y22
            r2.<init>(r4, r3)
            org.chromium.base.task.PostTask.c(r0, r2)
            java.lang.String r3 = "CustomTabs.PostMessage.PostMessageFromClientApp"
            r4 = 1
            defpackage.oj2.O(r3, r4)
            goto L43
        L42:
            r1 = -3
        L43:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ke0.o(org.chromium.chrome.browser.customtabs.a$e):java.lang.Object");
    }

    @Override // androidx.appcompat.widget.SearchView.OnCloseListener
    public void onClose() {
        SelectLanguageFragment selectLanguageFragment = (SelectLanguageFragment) this.f;
        selectLanguageFragment.a0 = "";
        selectLanguageFragment.c0.H(selectLanguageFragment.d0);
    }

    @Override // defpackage.n02
    public void p(ByteBuffer byteBuffer, i10 i10Var) {
        a10 a10Var;
        Tab tab = (Tab) this.f;
        String[] strArr = a10.q;
        if (tab.isDestroyed()) {
            a10Var = null;
        } else {
            a10 a10Var2 = (a10) l02.d(tab, a10.class);
            a10Var = a10Var2 == null ? (a10) tab.B().d(a10.class, new a10(tab)) : a10Var2;
        }
        if (a10Var != null) {
            a10Var.c(byteBuffer);
        }
        i10Var.onResult(a10Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    @Override // defpackage.k32
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean q(defpackage.f51 r4) {
        /*
            r3 = this;
            java.lang.Object r3 = r3.f
            java.util.HashSet r3 = (java.util.HashSet) r3
            boolean r0 = r4.d
            java.lang.String r4 = r4.a
            r1 = 1
            r2 = 0
            if (r0 != 0) goto Ld
            goto L28
        Ld:
            java.lang.String r0 = "zh-CN"
            boolean r0 = android.text.TextUtils.equals(r4, r0)
            if (r0 != 0) goto L32
            java.lang.String r0 = "zh-TW"
            boolean r0 = android.text.TextUtils.equals(r4, r0)
            if (r0 == 0) goto L20
            goto L32
        L20:
            java.lang.String r0 = "nb"
            boolean r0 = android.text.TextUtils.equals(r4, r0)
            if (r0 == 0) goto L2a
        L28:
            r0 = r2
            goto L33
        L2a:
            java.lang.String r0 = "-"
            boolean r0 = r4.contains(r0)
            r0 = r0 ^ r1
            goto L33
        L32:
            r0 = r1
        L33:
            if (r0 == 0) goto L3c
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L3c
            goto L3d
        L3c:
            r1 = r2
        L3d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ke0.q(f51):boolean");
    }

    @Override // wa2.a
    public void r(String str, va2 va2Var) {
        z70 z70Var = (z70) ((m60.d) this.f);
        ((m60) z70Var.f).f.i(((OfflineItem) z70Var.g).e, str, va2Var);
    }

    @Override // org.chromium.ui.permissions.PermissionCallback
    public void s(int[] iArr, String[] strArr) {
        SelectFileDialog selectFileDialog = (SelectFileDialog) this.f;
        String[] strArr2 = SelectFileDialog.l;
        selectFileDialog.getClass();
        if (iArr[0] == -1) {
            selectFileDialog.m();
        } else {
            selectFileDialog.new b(Boolean.TRUE, selectFileDialog.f, selectFileDialog).c(i9.e);
        }
    }

    public void t(String str) {
        AllSiteSettings allSiteSettings = (AllSiteSettings) this.f;
        String str2 = allSiteSettings.n0;
        boolean z = str2 != null ? !str2.equals(str) : !(str == null || str.isEmpty());
        allSiteSettings.n0 = str;
        if (z) {
            allSiteSettings.N0();
        }
    }

    @Override // af1.a
    public void u() {
        ((g) this.f).v(12, false);
    }

    public void v(View view) {
        org.chromium.chrome.browser.content_creation.notes.a aVar = (org.chromium.chrome.browser.content_creation.notes.a) this.f;
        Activity activity = aVar.a;
        new ld3(view, aVar);
        aVar.d.S0(aVar.c);
    }
}
