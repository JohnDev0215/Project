package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.widget.PopupWindow;
import defpackage.s1;
import defpackage.to;
import defpackage.wm3;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.b;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class mc implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ mc(Object obj, int i, Object obj2, Object obj3) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        int i2 = 0;
        Object obj2 = this.h;
        Object obj3 = this.g;
        Object obj4 = this.f;
        switch (i) {
            case 0:
                List list = (List) obj;
                b.b((AccountManagerFacade) obj4, list, new k6(list, (String) obj3, (Activity) obj2));
                break;
            case 1:
                to toVar = (to) obj4;
                WebContents webContents = (WebContents) obj;
                toVar.getClass();
                SelectionPopupControllerImpl.j(webContents).j = new to.a(toVar.b, webContents, (Callback) obj3, (nw2) obj2);
                break;
            case 2:
                org.chromium.chrome.browser.b bVar = (org.chromium.chrome.browser.b) obj4;
                Intent intent = (Intent) obj2;
                LoadUrlParams loadUrlParams = (LoadUrlParams) obj;
                bVar.getClass();
                loadUrlParams.g = org.chromium.chrome.browser.b.u(intent, (String) obj3, loadUrlParams.g);
                bVar.x(loadUrlParams, 0, null, -1, intent);
                break;
            case 3:
                Tab tab = (Tab) obj4;
                Callback callback = (Callback) obj3;
                Class cls = (Class) obj2;
                l02 l02Var = (l02) obj;
                HashMap map = l02.l;
                if (tab.isDestroyed()) {
                    PostTask.c(rh3.a, new th0(callback, 2));
                } else {
                    if (l02Var != null) {
                        l02Var.h = System.currentTimeMillis();
                    }
                    if (l02Var != null) {
                    }
                    PostTask.c(rh3.a, new l91(callback, 29, l02Var));
                }
                break;
            case 4:
                Tab tab2 = (Tab) obj4;
                Class cls2 = (Class) obj2;
                String str = (String) obj3;
                l02 l02Var2 = (l02) obj;
                HashMap map2 = l02.l;
                if (l02Var2 != null) {
                    l02Var2.h = System.currentTimeMillis();
                }
                l02.k(cls2, str, tab2, l02Var2);
                break;
            case 5:
                ((PropertyModel) obj2).p(s1.a, new s1.a(((r1) obj4).g, (Bitmap) obj, (String) obj3));
                break;
            case 6:
                List list2 = (List) obj4;
                xs3 xs3Var = (xs3) obj3;
                s62 s62Var = (s62) obj2;
                if (((Boolean) obj).booleanValue()) {
                    list2.add(xs3Var);
                    s62Var.b(null);
                } else {
                    s62Var.e(null);
                }
                break;
            case 7:
                ob3 ob3Var = (ob3) obj4;
                String str2 = (String) obj3;
                s62 s62Var2 = (s62) obj2;
                Map map3 = (Map) obj;
                ob3Var.getClass();
                if (map3.containsKey(str2)) {
                    z7.d0(3);
                    HashMap map4 = new HashMap(map3);
                    map4.remove(str2);
                    sl0 sl0Var = new sl0(map3, 4, str2, s62Var2);
                    UsageStatsBridge usageStatsBridge = ob3Var.b;
                    usageStatsBridge.getClass();
                    String[] strArr = new String[map4.size()];
                    String[] strArr2 = new String[map4.size()];
                    for (Map.Entry entry : map4.entrySet()) {
                        strArr[i2] = (String) entry.getKey();
                        strArr2[i2] = (String) entry.getValue();
                        i2++;
                    }
                    N.Mz1N0m$q(usageStatsBridge.b, usageStatsBridge, strArr, strArr2, sl0Var);
                } else {
                    s62Var2.b(null);
                }
                break;
            default:
                final pk3 pk3Var = (pk3) obj4;
                final xe3 xe3Var = (xe3) obj3;
                final gw0 gw0Var = (gw0) obj2;
                pk3Var.getClass();
                final View view = gw0Var.j;
                Activity activity = pk3Var.a;
                boolean zIsFinishing = activity.isFinishing();
                Runnable runnable = gw0Var.m;
                if (zIsFinishing || activity.isDestroyed() || view == null) {
                    runnable.run();
                } else {
                    final String str3 = gw0Var.b;
                    Rect rect = gw0Var.r;
                    z92 z92Var = rect != null ? new z92(rect) : null;
                    nn3 nn3Var = gw0Var.p;
                    if (nn3Var == null && z92Var == null) {
                        nn3Var = new nn3(view);
                    }
                    final wm3.a aVar = gw0Var.q;
                    if (xe3Var.shouldTriggerHelpUI(str3)) {
                        sk skVar = bq.a;
                        if (N.M09VlOh_("AndroidScrollOptimizations")) {
                            String str4 = gw0Var.e;
                            Resources resources = gw0Var.a;
                            if (str4 == null) {
                                int i3 = gw0Var.c;
                                Object[] objArr = gw0Var.d;
                                if (objArr != null) {
                                    gw0Var.e = resources.getString(i3, objArr);
                                } else {
                                    gw0Var.e = resources.getString(i3);
                                }
                            }
                            if (gw0Var.h == null) {
                                int i4 = gw0Var.f;
                                Object[] objArr2 = gw0Var.g;
                                if (objArr2 != null) {
                                    gw0Var.h = resources.getString(i4, objArr2);
                                } else {
                                    gw0Var.h = resources.getString(i4);
                                }
                            }
                            if (gw0Var.n == null && rect == null) {
                                gw0Var.n = new Rect(0, 0, 0, resources.getDimensionPixelOffset(R$dimen.iph_text_bubble_menu_anchor_y_inset));
                            }
                        }
                        u73 u73Var = new u73(pk3Var.a, view, gw0Var.e, gw0Var.h, !gw0Var.s, nn3Var != null ? nn3Var : z92Var, null, false, qo.f().c());
                        u73Var.f.t = gw0Var.t;
                        u73Var.e(gw0Var.i);
                        u73Var.addOnDismissListener(new PopupWindow.OnDismissListener() { // from class: nk3
                            @Override // android.widget.PopupWindow.OnDismissListener
                            public final void onDismiss() {
                                String str5 = str3;
                                xe3 xe3Var2 = xe3Var;
                                gw0 gw0Var2 = gw0Var;
                                wm3.a aVar2 = aVar;
                                View view2 = view;
                                pk3 pk3Var2 = pk3Var;
                                pk3Var2.getClass();
                                pk3Var2.b.postDelayed(new ok3(str5, xe3Var2, gw0Var2, aVar2, view2, 0), 200L);
                            }
                        });
                        u73Var.d(gw0Var.o);
                        if (aVar != null) {
                            wm3.b(view, aVar);
                        }
                        if (nn3Var != null) {
                            nn3Var.e(gw0Var.n);
                        }
                        u73Var.f();
                        gw0Var.l.run();
                    } else {
                        runnable.run();
                    }
                }
                break;
        }
    }

    public /* synthetic */ mc(Object obj, int i, String str, Object obj2) {
        this.e = i;
        this.f = obj;
        this.h = obj2;
        this.g = str;
    }
}
