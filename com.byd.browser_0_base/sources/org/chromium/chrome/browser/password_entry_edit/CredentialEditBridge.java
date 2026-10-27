package org.chromium.chrome.browser.password_entry_edit;

import android.content.Context;
import defpackage.C0175b;
import defpackage.zj;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.password_entry_edit.a;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class CredentialEditBridge implements a.b, a.InterfaceC0103a {
    public static CredentialEditBridge c;
    public long a;
    public a b;

    @CalledByNative
    public static CredentialEditBridge maybeCreate() {
        if (c != null) {
            return null;
        }
        CredentialEditBridge credentialEditBridge = new CredentialEditBridge();
        c = credentialEditBridge;
        return credentialEditBridge;
    }

    @CalledByNative
    public void destroy() {
        a aVar = this.b;
        if (aVar != null) {
            aVar.c.e.l(e.i, true);
        }
        this.a = 0L;
        c = null;
    }

    @CalledByNative
    public void initAndLaunchUi(long j, Context context, SettingsLauncher settingsLauncher, boolean z, boolean z2) {
        this.a = j;
        if (z) {
            settingsLauncher.g(context, BlockedCredentialFragmentView.class);
        } else if (z2) {
            settingsLauncher.g(context, FederatedCredentialFragmentView.class);
        } else {
            settingsLauncher.g(context, CredentialEditFragmentView.class);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CalledByNative
    public void setCredential(String str, String str2, String str3, String str4, boolean z) {
        a aVar = this.b;
        aVar.getClass();
        HashMap mapE = PropertyModel.e(e.j);
        PropertyModel.l<String> lVar = e.b;
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = str;
        mapE.put(lVar, gVar);
        PropertyModel.l<String> lVar2 = e.h;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = str4;
        PropertyModel propertyModelP = C0175b.p(mapE, lVar2, gVar2, mapE);
        aVar.f = propertyModelP;
        d dVar = aVar.c;
        dVar.e = propertyModelP;
        dVar.f = str2;
        dVar.g = str3;
        dVar.h = z;
        propertyModelP.p(e.c, str2);
        dVar.e.l(e.e, false);
        dVar.e.p(e.f, str3);
    }

    @CalledByNative
    public void setExistingUsernames(String[] strArr) {
        d dVar = this.b.c;
        dVar.getClass();
        dVar.i = new HashSet(Arrays.asList(strArr));
    }
}
