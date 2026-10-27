package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import defpackage.mh;
import java.util.concurrent.LinkedBlockingQueue;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.a;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.payments.JniPaymentApp;
import org.chromium.components.payments.PayerData;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.e;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nh implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ nh(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
        this.i = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.i;
        Object obj2 = this.h;
        Object obj3 = this.g;
        Object obj4 = this.f;
        switch (i) {
            case 0:
                mh.b bVar = (mh.b) obj4;
                mh.d dVar = (mh.d) obj3;
                bVar.getClass();
                dVar.a = new oh((LinkedBlockingQueue) obj);
                Context context = bVar.e;
                Resources resources = context.getResources();
                dVar.d = new LargeIconBridge(Profile.d());
                dVar.f = (int) resources.getDimension(R$dimen.default_favicon_min_size);
                dVar.g = resources.getDimensionPixelSize(R$dimen.default_favicon_size);
                dVar.e = a.d(context);
                dVar.h = 1;
                BookmarkModel bookmarkModelV = BookmarkModel.v(Profile.d());
                dVar.c = bookmarkModelV;
                bookmarkModelV.g(new ph(dVar, (BookmarkId) obj2));
                break;
            case 1:
                PageInfoContainer pageInfoContainer = (PageInfoContainer) obj4;
                int i2 = PageInfoContainer.l;
                pageInfoContainer.a((View) obj3, (CharSequence) obj2);
                ViewGroup viewGroup = pageInfoContainer.g;
                viewGroup.setScaleX(0.92f);
                viewGroup.setScaleY(0.92f);
                viewGroup.setAlpha(0.0f);
                viewGroup.animate().setDuration(210L).scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setInterpolator(new DecelerateInterpolator()).withEndAction((Runnable) obj);
                break;
            default:
                JniPaymentApp jniPaymentApp = (JniPaymentApp) obj4;
                String str = (String) obj3;
                String str2 = (String) obj2;
                PayerData payerData = (PayerData) obj;
                PaymentApp.b bVar2 = jniPaymentApp.o;
                if (bVar2 != null) {
                    ((e) bVar2).p(str, str2, payerData);
                    jniPaymentApp.o = null;
                    break;
                }
                break;
        }
    }
}
