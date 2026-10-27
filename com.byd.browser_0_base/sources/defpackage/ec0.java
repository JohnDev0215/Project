package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import com.google.protobuf.d0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.OfflineItemShareInfo;
import org.chromium.components.offline_items_collection.ShareCallback;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ec0 implements ShareCallback, LargeIconBridge.LargeIconCallback, OptimizationGuideBridge.OptimizationGuideCallback {
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ ec0(kc0 kc0Var, ArrayList arrayList, OfflineItem offlineItem, HashSet hashSet) {
        this.e = kc0Var;
        this.f = arrayList;
        this.h = offlineItem;
        this.g = hashSet;
    }

    @Override // org.chromium.components.offline_items_collection.ShareCallback
    public final void a(OfflineItemShareInfo offlineItemShareInfo) {
        Intent intentA;
        kc0 kc0Var = (kc0) this.e;
        Collection collection = (Collection) this.f;
        OfflineItem offlineItem = (OfflineItem) this.h;
        Collection collection2 = (Collection) this.g;
        kc0Var.getClass();
        collection.add(new qv1(offlineItem, offlineItemShareInfo));
        if (collection.size() != collection2.size() || (intentA = hl2.a(collection)) == null) {
            return;
        }
        nw2<Context> nw2Var = kc0Var.a;
        try {
            nw2Var.get().startActivity(Intent.createChooser(intentA, nw2Var.get().getString(R$string.share_link_chooser_title)));
        } catch (ActivityNotFoundException unused) {
            Log.e("cr_DownloadInterstitial", "Cannot find activity for sharing");
        } catch (Exception e) {
            Log.e("cr_DownloadInterstitial", "Cannot start activity for sharing, exception: " + e);
        }
    }

    @Override // org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge.OptimizationGuideCallback
    public final void j(int i, nv nvVar) {
        xl2 xl2Var = (xl2) this.e;
        Tab tab = (Tab) this.f;
        Runnable runnable = (Runnable) this.g;
        NavigationHandle navigationHandle = (NavigationHandle) this.h;
        int i2 = xl2.t;
        xl2Var.getClass();
        if (!tab.isInitialized()) {
            if (runnable != null) {
                runnable.run();
            }
        } else {
            if (!tab.getUrl().equals(navigationHandle.e) || i != 1) {
                if (runnable != null) {
                    runnable.run();
                }
                xl2Var.p.a(System.currentTimeMillis() - CriticalPersistedTabData.m(tab).r, "NavigationComplete");
                return;
            }
            try {
                xl2Var.s(tab, d42.T(nvVar.O()), null);
                xl2Var.h = System.currentTimeMillis();
                xl2Var.p.a(System.currentTimeMillis() - CriticalPersistedTabData.m(tab).r, "NavigationComplete");
            } catch (d0 e) {
                Log.i("cr_SPTD", String.format(Locale.US, "There was a problem parsing PriceTrackingDataProto. Details %s.", e));
            }
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        Bitmap bitmapCreateScaledBitmap;
        th3 th3Var = (th3) this.e;
        PropertyModel propertyModel = (PropertyModel) this.f;
        PropertyModel.r rVar = (PropertyModel.r) this.g;
        b11 b11Var = (b11) this.h;
        th3Var.getClass();
        String str = b11Var.d;
        if (bitmap == null) {
            uc2 uc2Var = th3Var.g;
            uc2Var.e.setColor(i);
            bitmapCreateScaledBitmap = uc2Var.b(str, false);
        } else {
            int i3 = th3Var.c;
            bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, i3, i3, true);
        }
        propertyModel.p(rVar, new BitmapDrawable(th3Var.a.getResources(), bitmapCreateScaledBitmap));
    }

    public /* synthetic */ ec0(Object obj, Object obj2, Object obj3, Object obj4) {
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.h = obj4;
    }
}
