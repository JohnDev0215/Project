package defpackage;

import J.N;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import defpackage.sf3;
import defpackage.wf3;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.offlinepages.RequestCoordinatorBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xi implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ xi(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
        this.i = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = false;
        int i = this.e;
        Object obj = this.i;
        Object obj2 = this.h;
        Object obj3 = this.g;
        Object obj4 = this.f;
        switch (i) {
            case 0:
                ((zi) obj4).getClass();
                try {
                    throw null;
                }
            case 1:
                Bundle bundle = (Bundle) obj3;
                sf3.b bVar = (sf3.b) obj2;
                wf3.a aVar = (wf3.a) obj;
                if (TextUtils.equals((String) obj4, "checkAndroidLocationPermission") && bundle != null) {
                    z = bundle.getBoolean("locationPermissionResult");
                }
                bVar.a(((vf3) aVar).a.b, z ? 1 : 2);
                break;
            case 2:
                String str = (String) obj4;
                ShoppingService shoppingService = (ShoppingService) obj3;
                Callback callback = (Callback) obj2;
                String str2 = (String) obj;
                if (str != null) {
                    long j = shoppingService.a;
                    if (j != 0) {
                        N.MVj6AAVT(j, shoppingService, 1, 1, 1, str, callback);
                    }
                }
                if (str2 != null) {
                    long j2 = shoppingService.a;
                    if (j2 != 0) {
                        N.MVj6AAVT(j2, shoppingService, 1, 2, 2, str2, callback);
                        break;
                    }
                }
                break;
            default:
                az2 az2Var = (az2) obj4;
                GURL gurl = (GURL) obj3;
                BookmarkModel bookmarkModel = (BookmarkModel) obj;
                wp2 wp2Var = az2Var.e.get();
                Context context = az2Var.a.getContext();
                int i2 = lh.a;
                if (bookmarkModel.d((String) obj2, gurl) != null) {
                    wp2Var.c(tp2.a(context.getString(R$string.reading_list_saved), new ih(), 0, 37));
                    oj2.t(Profile.d()).notifyEvent("read_later_article_saved");
                }
                oj2.t(Profile.d()).notifyEvent("read_later_context_menu_tapped");
                Profile profileD = Profile.d();
                Object obj5 = ThreadUtils.a;
                (profileD.h() ? null : new RequestCoordinatorBridge(profileD)).a(gurl.j(), "bookmark");
                break;
        }
    }
}
