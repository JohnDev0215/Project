package com.byd.systemsettings.ui.dialog;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import com.byd.systemsettings.cpaa.CarPlayManager;
import com.byd.systemsettings.system.ConfigurationChangedChecker;
import e4.a;
import e4.b;
import e4.c;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import k4.e;
import p2.g;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceDialogHolder {
    private static final String TAG = "ServiceDialogHolder";
    private static volatile boolean doingonConfigurationChanged = false;
    private static volatile long timeRestoreCompleted;

    public static boolean checkDialogLevel(final Context context, final Class cls) {
        if (context == null) {
            return false;
        }
        final b operateCarDialog = getOperateCarDialog(cls);
        if (operateCarDialog == null) {
            return true;
        }
        a.c(TAG, "checkDialogLevel", ", cd: " + operateCarDialog);
        if (isShowingOnTop(cls)) {
            if (!operateCarDialog.k()) {
                a.c(TAG, "checkDialogLevel", ", Page is showing on top. page: " + cls);
                return false;
            }
            a.b.a().s(cls);
        }
        operateCarDialog.d();
        x3.a.c(TAG, "checkDialogLevel", ", Page is detached. page: " + cls);
        g.a().d(new Runnable() { // from class: com.byd.systemsettings.ui.dialog.ServiceDialogHolder.3
            @Override // java.lang.Runnable
            public void run() {
                ServiceDialogHolder.invokeDialog(operateCarDialog.e(), operateCarDialog.g(), context);
                x3.a.c(ServiceDialogHolder.TAG, "checkDialogLevel", ", Page is invoked. page: " + cls);
            }
        }, 80L);
        return false;
    }

    public static void dismiss(Class<? extends z3.a> cls) {
        a.b.a().c(cls);
    }

    public static void dismissAll() {
        x3.a.c(TAG, "--dismissAll---");
        a.b.a().d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static b getDialog(z3.a aVar, boolean z7) {
        b bVarO = a.b.a().o(aVar);
        if (bVarO != null) {
            bVarO.G(z7);
            if (aVar instanceof e) {
                bVarO.E((e) aVar);
            }
            if (aVar instanceof k4.a) {
                bVarO.t((k4.a) aVar);
            }
        }
        return bVarO;
    }

    public static b getOperateCarDialog(Class<? extends z3.a> cls) {
        return a.b.a().h(cls);
    }

    public static z3.a getOperateCarDialogPager(Class<? extends z3.a> cls) {
        return a.b.a().i(cls);
    }

    public static List<b> getShowingDialogList() {
        return a.b.a().j();
    }

    public static boolean hasDialogShowing(Class<? extends z3.a>[] clsArr) {
        return a.b.a().k(clsArr);
    }

    public static boolean hasShowingDialog() {
        return e4.a.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void invokeDialog(String str, String str2, Context context) {
        x3.a.a(TAG, "invokeDialog", ", cls: " + str + ", methodName: " + str2 + ", ctx: " + context);
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, Context.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context);
        } catch (Exception e8) {
            x3.a.b(TAG, "invokeDialog", ", e: " + e8.toString());
        }
    }

    public static boolean isDoingonConfigurationChanged() {
        return doingonConfigurationChanged;
    }

    public static boolean isJustRestoreCompleted() {
        long jLongValue = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(timeRestoreCompleted)).longValue();
        x3.a.c(TAG, "isJustRestoreCompleted. delta: " + jLongValue);
        return jLongValue <= 600;
    }

    public static boolean isShowingOnTop(Class<? extends z3.a> cls) {
        return a.b.a().n(cls);
    }

    public static void onConfigurationChanged(final Context context, final Configuration configuration) {
        x3.a.a(TAG, "ue_onConfigurationChanged", ", newConfig: " + configuration);
        if (e4.a.m()) {
            doingonConfigurationChanged = true;
            final e4.a aVarA = a.b.a();
            List<b> listJ = aVarA.j();
            x3.a.a(TAG, "ue_onConfigurationChanged", ", list: " + listJ);
            ArrayList arrayList = new ArrayList();
            for (b bVar : listJ) {
                if (bVar.m() && !bVar.n()) {
                    c cVar = new c(bVar.f(), bVar.e(), bVar.g());
                    cVar.g(bVar.o());
                    cVar.h(bVar.h());
                    arrayList.add(cVar);
                    x3.a.a(TAG, "ue_onConfigurationChanged", ", dismiss dialog: " + bVar);
                    aVarA.u(bVar.h());
                    aVarA.e(bVar.e());
                }
            }
            Collections.sort(arrayList);
            final LinkedList linkedList = new LinkedList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                linkedList.offer((c) it.next());
            }
            new Handler(Looper.myLooper()).postDelayed(new Runnable() { // from class: com.byd.systemsettings.ui.dialog.ServiceDialogHolder.1
                @Override // java.lang.Runnable
                public void run() {
                    while (linkedList.size() > 0) {
                        c cVar2 = (c) linkedList.pop();
                        x3.a.a(ServiceDialogHolder.TAG, "ue_onConfigurationChanged", ", do show dialog: " + cVar2);
                        if (!cVar2.f()) {
                            x3.a.a(ServiceDialogHolder.TAG, "ue_onConfigurationChanged", ", do show dialog.isStaticMethod: " + cVar2.f());
                        } else if (!CarPlayManager.mIsShow || configuration.orientation == ConfigurationChangedChecker.curOrientation) {
                            ServiceDialogHolder.invokeDialog(cVar2.b(), cVar2.d(), context);
                            aVarA.t(cVar2.e());
                        } else {
                            x3.a.a(ServiceDialogHolder.TAG, "ue_onConfigurationChanged", "当前CP正在投屏,横竖屏变化不恢复弹框");
                        }
                    }
                    boolean unused = ServiceDialogHolder.doingonConfigurationChanged = false;
                    long unused2 = ServiceDialogHolder.timeRestoreCompleted = System.currentTimeMillis();
                    x3.a.a(ServiceDialogHolder.TAG, "ue_onConfigurationChanged", ", dialog restore completed.");
                }
            }, 180L);
        }
    }

    public static void release() {
        a.b.a().r();
    }

    public static void show(Context context, z3.a aVar, boolean z7, boolean z8) {
        getDialog(aVar, z7).I(context, aVar.getRootView(), aVar.getWidth(), aVar.getHeight(), z8);
        aVar.resume();
    }

    public static void showOnStatusbar(Context context, z3.a aVar, boolean z7) {
        getDialog(aVar, z7).L(context, aVar.getRootView(), aVar.getWidth(), aVar.getHeight(), true);
        aVar.resume();
    }

    public static void showOnStatusbarBgTrans(Context context, z3.a aVar) {
        getDialog(aVar, true).N(context, aVar.getRootView(), aVar.getWidth(), aVar.getHeight(), true);
        aVar.resume();
    }

    public static void showScreenoffOnNavibar(Context context, b bVar, z3.a aVar) {
        if (bVar == null || aVar == null) {
            return;
        }
        bVar.K(context, aVar.getRootView(), aVar.getWidth(), aVar.getHeight(), true, false);
        aVar.resume();
    }

    public static void showScreensaverOnStatusbar(Context context, b bVar, z3.a aVar) {
        if (bVar == null || aVar == null) {
            return;
        }
        bVar.M(context, aVar.getRootView(), aVar.getWidth(), aVar.getHeight(), true, false);
        aVar.resume();
    }

    public static void dismissAll(String str) {
        x3.a.c(TAG, "--dismissAll---clazzName: " + str);
        a.b.a().e(str);
    }

    public static void showOnStatusbar(Context context, final z3.a aVar) {
        b dialog = getDialog(aVar, true);
        if (aVar instanceof k4.c) {
            dialog.z(new k4.c() { // from class: com.byd.systemsettings.ui.dialog.ServiceDialogHolder.2
                @Override // k4.c
                public void onKeyboardStateChanged(boolean z7) {
                    if (ServiceDialogHolder.isJustRestoreCompleted()) {
                        return;
                    }
                    ((k4.c) aVar).onKeyboardStateChanged(z7);
                }
            });
        }
        dialog.L(context, aVar.getRootView(), aVar.getWidth(), aVar.getHeight(), true);
        aVar.resume();
    }

    private static b getDialog(Class<? extends z3.a> cls) {
        return a.b.a().h(cls);
    }
}
