package org.chromium.chrome.browser.password_entry_edit;

import defpackage.v62;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CredentialEditProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public static final PropertyModel.r<CredentialEntryFragmentViewBase.b> a;
    public static final PropertyModel.l<String> b;
    public static final PropertyModel.r<String> c;
    public static final PropertyModel.n d;
    public static final PropertyModel.n e;
    public static final PropertyModel.r<String> f;
    public static final PropertyModel.n g;
    public static final PropertyModel.l<String> h;
    public static final PropertyModel.n i;
    public static final v62[] j;

    static {
        PropertyModel.r<CredentialEntryFragmentViewBase.b> rVar = new PropertyModel.r<>("ui action handler", false);
        a = rVar;
        PropertyModel.l<String> lVar = new PropertyModel.l<>("url or app");
        b = lVar;
        PropertyModel.r<String> rVar2 = new PropertyModel.r<>("username", false);
        c = rVar2;
        PropertyModel.n nVar = new PropertyModel.n("duplicate username error");
        d = nVar;
        PropertyModel.n nVar2 = new PropertyModel.n("password visible");
        e = nVar2;
        PropertyModel.r<String> rVar3 = new PropertyModel.r<>("password", false);
        f = rVar3;
        PropertyModel.n nVar3 = new PropertyModel.n("empty password error");
        g = nVar3;
        PropertyModel.l<String> lVar2 = new PropertyModel.l<>("federation origin");
        h = lVar2;
        PropertyModel.n nVar4 = new PropertyModel.n("ui dismissed by native");
        i = nVar4;
        j = new v62[]{rVar, lVar, rVar2, nVar, nVar2, rVar3, nVar3, lVar2, nVar4};
    }
}
