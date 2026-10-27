package defpackage;

import J.N;
import android.graphics.Rect;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ci3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ ci3(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
        this.i = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.h;
        Object obj2 = this.g;
        Object obj3 = this.i;
        Object obj4 = this.f;
        switch (i) {
            case 0:
                ((ei3) obj4).getClass();
                throw null;
            case 1:
                final h42 h42Var = (h42) obj4;
                final gm2 gm2Var = (gm2) obj2;
                final Tab tab = (Tab) obj;
                final BookmarkModel bookmarkModel = (BookmarkModel) obj3;
                ShoppingService shoppingService = h42Var.a.get();
                long j = shoppingService.a;
                if (!(j == 0 ? false : N.Mno1SAkR(j, shoppingService))) {
                    gm2Var.a = Boolean.FALSE;
                    gm2Var.a();
                    return;
                }
                GURL url = tab.getUrl();
                ShoppingService.ProductInfoCallback productInfoCallback = new ShoppingService.ProductInfoCallback() { // from class: g42
                    @Override // org.chromium.components.commerce.core.ShoppingService.ProductInfoCallback
                    public final void a(ShoppingService.ProductInfo productInfo) {
                        h42 h42Var2 = h42Var;
                        h42Var2.getClass();
                        BookmarkId bookmarkIdP = bookmarkModel.p(tab);
                        boolean z = productInfo != null;
                        gm2 gm2Var2 = gm2Var;
                        if (bookmarkIdP != null) {
                            N.Mkvg2cg$(h42Var2.c.get(), bookmarkIdP.getId(), new lj1(20, gm2Var2));
                        } else {
                            gm2Var2.a = Boolean.valueOf(z);
                            gm2Var2.a();
                        }
                    }
                };
                long j2 = shoppingService.a;
                if (j2 == 0) {
                    return;
                }
                N.MzfVLK97(j2, shoppingService, url, productInfoCallback);
                return;
            case 2:
                TabImpl tabImpl = (TabImpl) obj4;
                WebContents webContents = (WebContents) obj2;
                Rect rect = (Rect) obj;
                Rect rect2 = (Rect) obj3;
                List<String> list = TabImpl.H;
                tabImpl.getClass();
                webContents.m0(rect.width(), rect.height());
                if (rect2 != null) {
                    N.MzfONDmc(tabImpl.a, webContents, rect2.right, rect2.bottom);
                }
                tabImpl.R(webContents);
                webContents.Y();
                return;
            default:
                uv2 uv2Var = (uv2) obj4;
                uv2Var.getClass();
                uv2Var.b.onResult(new ss1((xv2) obj2, (Executor) obj, (IntConsumer) obj3));
                return;
        }
    }
}
