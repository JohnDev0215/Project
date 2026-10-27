package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.android.webid.data.Account;
import org.chromium.chrome.browser.ui.android.webid.data.IdentityProviderMetadata;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AccountSelectionProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t1 {
    public static final PropertyModel.l<Account> a;
    public static final PropertyModel.l<IdentityProviderMetadata> b;
    public static final PropertyModel.l<Callback<Account>> c;
    public static final v62[] d;

    static {
        PropertyModel.l<Account> lVar = new PropertyModel.l<>("account");
        a = lVar;
        PropertyModel.l<IdentityProviderMetadata> lVar2 = new PropertyModel.l<>("idp_metadata");
        b = lVar2;
        PropertyModel.l<Callback<Account>> lVar3 = new PropertyModel.l<>("on_click_listener");
        c = lVar3;
        d = new v62[]{lVar, lVar2, lVar3};
    }
}
