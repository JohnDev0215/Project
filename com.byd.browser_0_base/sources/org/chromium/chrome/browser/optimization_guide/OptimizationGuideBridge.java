package org.chromium.chrome.browser.optimization_guide;

import J.N;
import android.util.Base64;
import android.util.Log;
import com.google.protobuf.d0;
import defpackage.nv;
import defpackage.oj2;
import defpackage.ou0;
import defpackage.s72;
import defpackage.ws1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes2.dex */
public class OptimizationGuideBridge {
    public long a;

    public interface OptimizationGuideCallback {
        void j(int i, nv nvVar);
    }

    public OptimizationGuideBridge() {
        Object obj = ThreadUtils.a;
        this.a = N.M9P8SBdL();
    }

    @CalledByNative
    public static void clearCachedPushNotifications(int i) {
        ou0 ou0VarA = ou0.a(i);
        if (ou0VarA == null) {
            return;
        }
        Set<String> set = ws1.a;
        SharedPreferencesManager.getInstance().removeKey(ws1.a(ou0VarA));
    }

    @CalledByNative
    public static byte[][] getEncodedPushNotifications(int i) {
        s72[] s72VarArr;
        ou0 ou0VarA = ou0.a(i);
        if (ou0VarA == null) {
            return null;
        }
        Set<String> set = ws1.a;
        Set<String> setH = SharedPreferencesManager.getInstance().h(ws1.a(ou0VarA));
        if (setH == null || ws1.b(setH)) {
            s72VarArr = null;
        } else {
            Iterator<String> it = setH.iterator();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < setH.size(); i2++) {
                try {
                    arrayList.add(s72.W(Base64.decode(it.next(), 0)));
                    oj2.U(1, 4, "OptimizationGuide.PushNotifications.ReadCacheResult");
                } catch (d0 e) {
                    oj2.U(2, 4, "OptimizationGuide.PushNotifications.ReadCacheResult");
                    Log.e("cr_OGPNotificationMngr", Log.getStackTraceString(e));
                } catch (IllegalArgumentException e2) {
                    oj2.U(3, 4, "OptimizationGuide.PushNotifications.ReadCacheResult");
                    Log.e("cr_OGPNotificationMngr", Log.getStackTraceString(e2));
                }
            }
            s72VarArr = new s72[arrayList.size()];
            arrayList.toArray(s72VarArr);
        }
        if (s72VarArr == null) {
            return null;
        }
        byte[][] bArr = new byte[s72VarArr.length][];
        for (int i3 = 0; i3 < s72VarArr.length; i3++) {
            bArr[i3] = s72VarArr[i3].q();
        }
        return bArr;
    }

    @CalledByNative
    public static int[] getOptTypesThatOverflowedPushNotifications() {
        Set<String> set = ws1.a;
        ArrayList arrayList = new ArrayList();
        for (ou0 ou0Var : ou0.values()) {
            if (ws1.b(SharedPreferencesManager.getInstance().h(ws1.a(ou0Var)))) {
                arrayList.add(ou0Var);
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((ou0) arrayList.get(i)).e;
        }
        return iArr;
    }

    @CalledByNative
    public static int[] getOptTypesWithPushNotifications() {
        Set<String> set = ws1.a;
        ArrayList arrayList = new ArrayList();
        for (ou0 ou0Var : ou0.values()) {
            Set<String> setH = SharedPreferencesManager.getInstance().h(ws1.a(ou0Var));
            if (setH != null && setH.size() > 0 && !ws1.b(setH)) {
                arrayList.add(ou0Var);
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((ou0) arrayList.get(i)).e;
        }
        return iArr;
    }

    @CalledByNative
    public static void onOptimizationGuideDecision(OptimizationGuideCallback optimizationGuideCallback, int i, byte[] bArr) {
        nv nvVarQ = null;
        if (bArr != null) {
            try {
                nvVarQ = nv.Q(bArr);
            } catch (d0 unused) {
            }
        }
        optimizationGuideCallback.j(i, nvVarQ);
    }

    @CalledByNative
    public static void onPushNotificationNotHandledByNative(byte[] bArr) {
        try {
            s72 s72VarW = s72.W(bArr);
            Set<String> set = ws1.a;
            if (s72VarW.U() && s72VarW.T() && s72VarW.S()) {
                Set<String> setH = SharedPreferencesManager.getInstance().h(ws1.a(s72VarW.Q()));
                if (setH == null || ws1.b(setH)) {
                    return;
                }
                if (setH.size() >= ws1.b.c() - 1) {
                    SharedPreferencesManager.getInstance().p(ws1.a(s72VarW.Q()), ws1.a);
                    return;
                }
                s72.a aVarV = s72.V(s72VarW);
                aVarV.y();
                s72.O((s72) aVarV.f);
                s72 s72VarBuild = aVarV.build();
                SharedPreferencesManager.getInstance().b(ws1.a(s72VarBuild.Q()), Base64.encodeToString(s72VarBuild.q(), 0));
            }
        } catch (d0 unused) {
        }
    }
}
