package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.android.webid.data.Account;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AccountSelectionProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s1 {
    public static final PropertyModel.r<a> a;
    public static final PropertyModel.l<Account> b;
    public static final PropertyModel.l<Callback<Account>> c;
    public static final v62[] d;

    /* JADX INFO: compiled from: AccountSelectionProperties.java */
    public static class a {
        public final String a;
        public final Bitmap b;
        public final int c;

        public a(int i, Bitmap bitmap, String str) {
            this.a = str;
            this.b = bitmap;
            this.c = i;
        }
    }

    static {
        PropertyModel.r<a> rVar = new PropertyModel.r<>("avatar", false);
        a = rVar;
        PropertyModel.l<Account> lVar = new PropertyModel.l<>("account");
        b = lVar;
        PropertyModel.l<Callback<Account>> lVar2 = new PropertyModel.l<>("on_click_listener");
        c = lVar2;
        d = new v62[]{rVar, lVar, lVar2};
    }
}
