package defpackage;

import J.N;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.View;
import androidx.core.view.accessibility.a;
import com.google.android.material.tabs.TabLayout;
import defpackage.li1;
import defpackage.sf3;
import org.chromium.base.Callback;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.feed.sections.SectionHeaderView;
import org.chromium.chrome.browser.payments.ui.PaymentRequestHeader;
import org.chromium.chrome.browser.payments.ui.j;
import org.chromium.chrome.browser.toolbar.top.b;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.offline_items_collection.OfflineItemVisuals;
import org.chromium.components.offline_items_collection.VisualsCallback;
import org.chromium.components.payments.e;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zo1 implements sf3.b, VisualsCallback, a, li1.a, FaviconHelper.FaviconImageCallback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ zo1(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // sf3.b
    public final void a(ComponentName componentName, int i) {
        dp1 dp1Var = (dp1) this.f;
        ft1 ft1Var = (ft1) this.g;
        dp1Var.getClass();
        dp1Var.a(ft1Var, 0L, componentName.getPackageName(), i);
    }

    @Override // androidx.core.view.accessibility.a
    public final boolean f(View view) {
        SectionHeaderView sectionHeaderView = (SectionHeaderView) this.f;
        sectionHeaderView.i.a((TabLayout.g) this.g);
        return true;
    }

    @Override // org.chromium.components.offline_items_collection.VisualsCallback
    public final void g(ny nyVar, OfflineItemVisuals offlineItemVisuals) {
        Bitmap bitmapCreateScaledBitmap;
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 1:
                u93 u93Var = (u93) obj2;
                Callback callback = (Callback) obj;
                u93Var.getClass();
                if (offlineItemVisuals == null || (bitmapCreateScaledBitmap = offlineItemVisuals.a) == null) {
                    callback.onResult(null);
                } else {
                    int width = bitmapCreateScaledBitmap.getWidth();
                    int height = bitmapCreateScaledBitmap.getHeight();
                    int i2 = u93Var.c;
                    if (width > i2 && height > u93Var.d) {
                        int width2 = bitmapCreateScaledBitmap.getWidth();
                        float f = ya0.a(xz.a).d;
                        if (f > 1.5f) {
                            width2 = (int) ((width2 * 1.5f) / f);
                        }
                        width = width2;
                        int height2 = bitmapCreateScaledBitmap.getHeight();
                        float f2 = ya0.a(xz.a).d;
                        if (f2 > 1.5f) {
                            height2 = (int) ((height2 * 1.5f) / f2);
                        }
                        height = height2;
                    }
                    int iMin = Math.min(bitmapCreateScaledBitmap.getWidth(), bitmapCreateScaledBitmap.getHeight());
                    if (iMin > i2) {
                        long j = i2;
                        long j2 = iMin;
                        width = (int) ((((long) bitmapCreateScaledBitmap.getWidth()) * j) / j2);
                        height = (int) ((((long) bitmapCreateScaledBitmap.getHeight()) * j) / j2);
                    }
                    if (bitmapCreateScaledBitmap.getWidth() != width || bitmapCreateScaledBitmap.getHeight() != height) {
                        bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateScaledBitmap, width, height, false);
                    }
                    callback.onResult(bitmapCreateScaledBitmap);
                }
                break;
            default:
                int i3 = yq1.H;
                ((Callback) obj).onResult(((yq1) obj2).w(offlineItemVisuals));
                break;
        }
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
    public final void onFaviconAvailable(Bitmap bitmap, GURL gurl) {
        e eVar;
        pz1 pz1Var;
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 5:
                j jVar = (j) obj2;
                FaviconHelper faviconHelper = (FaviconHelper) obj;
                if (bitmap == null && (eVar = ((yq) jVar.o).a) != null && (pz1Var = eVar.A) != null) {
                    pz1Var.V0();
                }
                org.chromium.chrome.browser.payments.ui.e eVar2 = jVar.x;
                if (eVar2 != null && bitmap != null) {
                    ((PaymentRequestHeader) eVar2.m.findViewById(R$id.header)).setTitleBitmap(bitmap);
                }
                N.Mz5mgjYL(faviconHelper.a);
                faviconHelper.a = 0L;
                break;
            default:
                b bVar = (b) obj2;
                GURL gurl2 = (GURL) obj;
                Context context = bVar.f;
                if (bitmap == null) {
                    if (bVar.p == null) {
                        bVar.p = new FaviconHelper.a();
                    }
                    bitmap = bVar.p.b(context.getResources(), gurl2, true);
                }
                if (bk3.l(gurl2) && bVar.e.h()) {
                    FaviconHelper.a aVar = bVar.p;
                    Resources resources = context.getResources();
                    int i2 = R$drawable.incognito_small;
                    aVar.getClass();
                    bitmap = FaviconHelper.a.a(resources, i2, true);
                }
                int i3 = 0;
                while (true) {
                    xk1 xk1Var = bVar.i;
                    if (i3 >= xk1Var.b()) {
                        bVar.j.notifyDataSetChanged();
                    } else {
                        NavigationEntry navigationEntryA = xk1Var.a(i3);
                        if (gurl2.equals(navigationEntryA.b)) {
                            navigationEntryA.f = bitmap;
                        }
                        i3++;
                    }
                    break;
                }
                break;
        }
    }
}
