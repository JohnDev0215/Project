package org.chromium.components.webapps.bottomsheet;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Pair;
import defpackage.aj3;
import defpackage.ee3;
import defpackage.ji;
import defpackage.t72;
import defpackage.u72;
import defpackage.w3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.browser_ui.bottomsheet.h;
import org.chromium.components.webapps.bottomsheet.PwaBottomSheetController;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes3.dex */
public class PwaBottomSheetControllerProvider {
    public static final aj3<PwaBottomSheetController> a = new aj3<>(PwaBottomSheetController.class);

    public static PwaBottomSheetController a(WebContents webContents) {
        WindowAndroid windowAndroidV = webContents.V();
        if (windowAndroidV == null) {
            return null;
        }
        return (PwaBottomSheetController) a.e(windowAndroidV.r);
    }

    @CalledByNative
    public static boolean canShowPwaBottomSheetInstaller(WebContents webContents) {
        if (a(webContents) != null) {
            return webContents.r() == 2;
        }
        return false;
    }

    @CalledByNative
    public static boolean doesBottomSheetExist(WebContents webContents) {
        PwaBottomSheetController pwaBottomSheetControllerA = a(webContents);
        return pwaBottomSheetControllerA != null && pwaBottomSheetControllerA.d();
    }

    @CalledByNative
    public static void expandPwaBottomSheetInstaller(WebContents webContents) {
        PwaBottomSheetController pwaBottomSheetControllerA = a(webContents);
        if (pwaBottomSheetControllerA != null && pwaBottomSheetControllerA.d()) {
            pwaBottomSheetControllerA.g.l();
            N.M55fWa5U(pwaBottomSheetControllerA.f);
        }
    }

    @CalledByNative
    public static void showPwaBottomSheetInstaller(long j, WebContents webContents, Bitmap bitmap, boolean z, String str, String str2, String str3) {
        PwaBottomSheetController pwaBottomSheetControllerA = a(webContents);
        if (pwaBottomSheetControllerA == null) {
            return;
        }
        WindowAndroid windowAndroidV = webContents.V();
        pwaBottomSheetControllerA.f = j;
        pwaBottomSheetControllerA.k = webContents;
        ji jiVarA = h.a(windowAndroidV);
        pwaBottomSheetControllerA.g = jiVarA;
        if (jiVarA != null) {
            if (webContents.r() == 2) {
                Context context = pwaBottomSheetControllerA.e;
                PwaBottomSheetController.c cVar = new PwaBottomSheetController.c(pwaBottomSheetControllerA, context);
                pwaBottomSheetControllerA.j = cVar;
                a aVar = new a(context, cVar);
                pwaBottomSheetControllerA.i = new u72(aVar, pwaBottomSheetControllerA);
                PropertyModel.b bVar = new PropertyModel.b(w3.j);
                bVar.e(w3.d, new Pair(bitmap, Boolean.valueOf(z)));
                bVar.e(w3.a, str);
                bVar.e(w3.b, str2);
                bVar.e(w3.c, str3);
                bVar.b(w3.f, true);
                bVar.e(w3.g, pwaBottomSheetControllerA);
                j.b(bVar.a(), aVar, new ee3(11));
                pwaBottomSheetControllerA.g.g(pwaBottomSheetControllerA.h);
                if (pwaBottomSheetControllerA.g.d(pwaBottomSheetControllerA.i, true)) {
                    new t72(pwaBottomSheetControllerA, webContents);
                }
            }
        }
    }

    @CalledByNative
    public static void updateState(WebContents webContents, int i, boolean z) {
        PwaBottomSheetController pwaBottomSheetControllerA = a(webContents);
        if (pwaBottomSheetControllerA == null) {
            return;
        }
        N.MzfzCOQC(pwaBottomSheetControllerA.f, i);
        if (z && pwaBottomSheetControllerA.d()) {
            pwaBottomSheetControllerA.g.l();
            N.M55fWa5U(pwaBottomSheetControllerA.f);
        }
    }
}
