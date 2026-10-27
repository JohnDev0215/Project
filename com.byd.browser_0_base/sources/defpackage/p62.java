package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import defpackage.ax0;
import defpackage.om3;
import defpackage.s62;
import defpackage.vf1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$UnfollowResults;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata;
import org.chromium.chrome.browser.feed.webfeed.f;
import org.chromium.chrome.browser.history_clusters.HistoryClustersResult;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.base.e;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p62 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ p62(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i;
        int i2;
        int i3 = 3;
        int i4 = this.e;
        int i5 = 8;
        int i6 = 4;
        objArr = 0;
        Object[] objArr = 0;
        int i7 = 1;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i4) {
            case 0:
                final s62 s62Var = (s62) obj2;
                try {
                    s62 s62Var2 = (s62) ((fp1) ((s62.a) obj3)).apply(obj);
                    Objects.requireNonNull(s62Var);
                    final int i8 = z ? 1 : 0;
                    s62Var2.h(new Callback() { // from class: r62
                        @Override // org.chromium.base.Callback
                        public final void onResult(Object obj4) {
                            int i9 = i8;
                            s62 s62Var3 = s62Var;
                            switch (i9) {
                                case 0:
                                    s62Var3.b(obj4);
                                    break;
                                default:
                                    s62Var3.b((HistoryClustersResult) obj4);
                                    break;
                            }
                        }
                    }, new o62(s62Var, 1));
                    return;
                } catch (Exception e) {
                    s62Var.e(e);
                    return;
                }
            case 1:
                PersonalDataManager personalDataManager = (PersonalDataManager) obj3;
                GURL gurl = (GURL) obj2;
                Bitmap bitmap = (Bitmap) obj;
                if (bitmap == null) {
                    personalDataManager.getClass();
                    return;
                } else {
                    personalDataManager.c.put(gurl.j(), bitmap);
                    return;
                }
            case 2:
                C0175b.r(obj3);
                s61 s61Var = r61.a;
                throw null;
            case 3:
                HashSet hashSet = CustomTabsConnection.o;
                ((CustomTabsConnection) obj3).getClass();
                ((Tab) obj).c();
                return;
            case 4:
                xe3 xe3Var = (xe3) obj3;
                View view = (View) obj2;
                if (((ArrayList) obj).size() >= 2 && xe3Var.shouldTriggerHelpUI("IPH_DownloadSettings")) {
                    View viewFindViewById = view.findViewById(R$id.settings_menu_id);
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    if (om3.g.b(view)) {
                        jd3.a(xe3Var, viewFindViewById, view);
                        return;
                    } else {
                        view.addOnAttachStateChangeListener(new hd3(xe3Var, viewFindViewById, view));
                        return;
                    }
                }
                return;
            case 5:
                lo0 lo0Var = (lo0) obj3;
                PropertyModel propertyModel = (PropertyModel) obj2;
                WebFeedBridge$UnfollowResults webFeedBridge$UnfollowResults = (WebFeedBridge$UnfollowResults) obj;
                lo0Var.getClass();
                lo0Var.a(webFeedBridge$UnfollowResults.a);
                propertyModel.p(jo0.e, Boolean.valueOf(webFeedBridge$UnfollowResults.a != 1));
                propertyModel.p(jo0.f, Boolean.TRUE);
                return;
            case 6:
                f fVar = (f) obj3;
                byte[] bArr = (byte[]) obj2;
                WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata = (WebFeedBridge$WebFeedMetadata) obj;
                fVar.getClass();
                if (webFeedBridge$WebFeedMetadata == null || webFeedBridge$WebFeedMetadata.d == 2) {
                    fVar.b.c(new me0(i7, new GestureDetector(fVar.a.getApplicationContext(), new gr3(fVar, bArr))), new gg3(i6), new o90(i7));
                    return;
                }
                return;
            case 7:
                C0196l0 c0196l0 = (C0196l0) obj3;
                o41 o41Var = (o41) obj2;
                c0196l0.getClass();
                oj2.U(o41Var.d == 6 ? !o41Var.b ? 1 : 0 : 2, 2, "KeyboardAccessory.AccessoryToggleClicked");
                c0196l0.getClass();
                throw null;
            case 8:
                String str = (String) obj2;
                int iIntValue = ((Integer) obj).intValue();
                zf1 zf1Var = (zf1) ((vf1.a) obj3);
                wf1 wf1Var = zf1Var.e;
                wf1Var.e();
                oj2.U(iIntValue, 10, "MerchantTrust.Message.DismissReason");
                wf1Var.a = false;
                wf1Var.b = false;
                wf1Var.c = 0L;
                wf1Var.d = 0L;
                if (iIntValue == 3 || iIntValue == 4) {
                    zf1Var.c(str, iIntValue == 3);
                    return;
                }
                return;
            case 9:
                NewTabPageLayout newTabPageLayout = (NewTabPageLayout) obj3;
                n52 n52Var = (n52) obj2;
                Drawable drawable = (Drawable) obj;
                if (drawable == null) {
                    int i9 = NewTabPageLayout.L;
                    newTabPageLayout.getClass();
                    return;
                } else {
                    if (newTabPageLayout.j != null) {
                        return;
                    }
                    ImageView imageView = (ImageView) ((ViewStub) newTabPageLayout.findViewById(R$id.logo_holder).findViewById(R$id.cryptid_holder)).inflate();
                    imageView.setImageDrawable(drawable);
                    newTabPageLayout.j = imageView;
                    n52Var.getClass();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    n3.X0("CryptidRendered");
                    oj2.U(0, 3, "NewTabPage.CryptidRenderResult");
                    SharedPreferencesManager.getInstance().n(jCurrentTimeMillis, "Chrome.Cryptid.LastRenderTimestamp");
                    return;
                }
            case 10:
                y5 y5Var = (y5) obj3;
                Bitmap bitmap2 = (Bitmap) obj;
                y5Var.getClass();
                Object obj4 = ThreadUtils.a;
                List list = (List) y5Var.g.remove((String) obj2);
                if (list == null || bitmap2 == null) {
                    return;
                }
                for (int i10 = 0; i10 < list.size(); i10++) {
                    PropertyModel propertyModel2 = (PropertyModel) list.get(i10);
                    e.a aVarB = e.a.b(y5Var.a, bitmap2);
                    aVarB.d = true;
                    c.g(propertyModel2, aVarB.a());
                }
                return;
            case 11:
            default:
                ax0.a aVar = (ax0.a) obj3;
                Callback callback = (Callback) obj2;
                Bitmap bitmapExtractThumbnail = (Bitmap) obj;
                if (!aVar.e) {
                    callback.onResult(bitmapExtractThumbnail);
                    return;
                }
                if (bitmapExtractThumbnail != null && (i = aVar.c) > 0 && (i2 = aVar.d) > 0 && bitmapExtractThumbnail.getWidth() != i && bitmapExtractThumbnail.getHeight() != i2) {
                    bitmapExtractThumbnail = ThumbnailUtils.extractThumbnail(bitmapExtractThumbnail, i, i2, 2);
                }
                callback.onResult(bitmapExtractThumbnail);
                return;
            case 12:
                a00 a00Var = (a00) obj3;
                Tab tab = (Tab) obj2;
                Integer num = (Integer) obj;
                a00Var.getClass();
                if (tab.isDestroyed()) {
                    return;
                }
                zp1<Tab> zp1Var = a00Var.b;
                if ((zp1Var.get() != null && zp1Var.get().getId() == tab.getId()) == true) {
                    sk skVar = bq.a;
                    if (N.M09VlOh_("ContextualPageActions") && N.M6bsIDpc("ContextualPageActions", "enable_ui", true)) {
                        objArr = 1;
                    }
                    if (objArr == 0) {
                        return;
                    }
                    a00Var.c(num.intValue());
                    return;
                }
                return;
            case 13:
                gi0 gi0Var = (gi0) obj3;
                gi0Var.getClass();
                p62 p62Var = new p62((List) obj, 14, (s62) obj2);
                UsageStatsBridge usageStatsBridge = gi0Var.a;
                N.Mot8dCyk(usageStatsBridge.b, usageStatsBridge, p62Var);
                return;
            case 14:
                List list2 = (List) obj3;
                s62 s62Var3 = (s62) obj2;
                if (!((Boolean) obj).booleanValue()) {
                    s62Var3.e(null);
                    return;
                } else {
                    list2.clear();
                    s62Var3.b(null);
                    return;
                }
            case 15:
                gi0 gi0Var2 = ((fk3) obj3).b;
                gi0Var2.getClass();
                s62 s62Var4 = new s62();
                gi0Var2.b.h(new ct3(gi0Var2, i3, (List) obj2, s62Var4), new fm3(7));
                s62Var4.a(new m62(i5));
                return;
        }
    }
}
