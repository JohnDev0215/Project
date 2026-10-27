package defpackage;

import J.N;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import defpackage.q82;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.Callback;
import org.chromium.base.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.chrome.browser.webapps.WebApkUpdateManager;
import org.chromium.components.background_task_scheduler.TaskInfo;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.browser_ui.site_settings.d;
import org.chromium.components.crash.a;
import org.chromium.components.infobars.InfoBarLayout;
import org.chromium.components.messages.MessageWrapper;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.PageInfoCookiesPreference;
import org.chromium.components.page_info.R$drawable;
import org.chromium.components.paintpreview.player.b;
import org.chromium.components.permissions.PermissionDialogController;
import org.chromium.components.signin.AccountCapabilitiesFetcher;
import org.chromium.components.signin.base.AccountCapabilities;
import org.chromium.components.subresource_filter.AdsBlockedInfoBar;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class l51 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ l51(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        TaskInfo taskInfo;
        PropertyModel propertyModel;
        int i = this.e;
        Bitmap bitmap = null;
        tr3 tr3Var = null;
        bitmap = null;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                m51 m51Var = (m51) obj2;
                m51Var.e = (String) obj;
                m51Var.a(m51Var.a.a(m51Var.f));
                break;
            case 1:
                am3 am3Var = (am3) obj2;
                Tutorial tutorial = (Tutorial) obj;
                am3Var.getClass();
                am3Var.c.l(bm3.f, tutorial != null);
                break;
            case 2:
                ((t3) obj2).e.dismissed("IPH_AddToHomescreenMessage");
                break;
            case 3:
                WebApkUpdateManager webApkUpdateManager = (WebApkUpdateManager) obj2;
                webApkUpdateManager.getClass();
                if (!((Boolean) obj).booleanValue()) {
                    ds3 ds3Var = webApkUpdateManager.k;
                    ds3Var.b(false);
                    ds3Var.b.edit().putBoolean("update_scheduled", false).apply();
                    WebApkUpdateManager.g(ds3Var, 1, false);
                    ds3Var.a();
                } else {
                    oj2.U(1, 3, "WebApk.Update.RequestQueued");
                    if (webApkUpdateManager.k.b.getBoolean("should_force_update", false)) {
                        TaskInfo.b.a aVar = new TaskInfo.b.a();
                        aVar.b = 0L;
                        TaskInfo.b bVar = new TaskInfo.b(aVar);
                        TaskInfo.a aVar2 = new TaskInfo.a(91);
                        aVar2.g = bVar;
                        aVar2.f = true;
                        aVar2.e = true;
                        taskInfo = new TaskInfo(aVar2);
                        webApkUpdateManager.k.b.edit().putBoolean("update_scheduled", true).apply();
                        webApkUpdateManager.k.b(false);
                    } else {
                        TaskInfo.a aVarA = TaskInfo.a(91, 3600000L, 82800000L);
                        aVarA.c = 2;
                        aVarA.f = true;
                        aVarA.e = true;
                        aVarA.d = true;
                        taskInfo = new TaskInfo(aVarA);
                    }
                    ((jc) a8.E()).b(xz.a, taskInfo);
                }
                break;
            case 4:
                nr2 nr2Var = (nr2) obj2;
                q82.a aVar3 = (q82.a) obj;
                nr2Var.getClass();
                String str = aVar3.a;
                sr1 sr1Var = nr2Var.c.z;
                if (sr1Var != null) {
                    sr1Var.e(str, aVar3.b);
                }
                break;
            case 5:
                ((g73) obj2).g((Profile) obj);
                break;
            case 6:
                fv1 fv1Var = (fv1) obj2;
                int iD = iv1.d(false, fv1.a(fv1Var.b));
                if (iD <= 13) {
                    fv1Var.b(iD);
                }
                break;
            case 7:
                ((so1) obj2).i((NotificationChannelGroup) obj);
                break;
            case 8:
                SingleWebsiteSettings singleWebsiteSettings = (SingleWebsiteSettings) obj2;
                String[] strArr = SingleWebsiteSettings.u0;
                singleWebsiteSettings.getClass();
                if (((Boolean) obj).booleanValue()) {
                    d.b(((kr) singleWebsiteSettings.h0).b, singleWebsiteSettings.m0, singleWebsiteSettings.t0);
                }
                break;
            case 9:
                int i2 = v9.g;
                ((AtomicBoolean) obj2).set(true);
                break;
            case 10:
                ia3 ia3Var = (ia3) obj2;
                n3.X0("Search." + ia3Var.a.a + ".Tile.Clicked");
                ia3Var.b.onResult((gx0) obj);
                break;
            case 11:
                ma3 ma3Var = (ma3) obj2;
                List list = (List) obj;
                int i3 = ma3.y;
                if (list != null) {
                    ma3Var.getClass();
                    if (!list.isEmpty()) {
                        bitmap = (Bitmap) list.get(0);
                    }
                }
                ma3Var.u(bitmap);
                break;
            case 12:
                a aVar4 = (a) obj2;
                Map.Entry entry = (Map.Entry) obj;
                aVar4.getClass();
                String str2 = (String) entry.getKey();
                String str3 = (String) entry.getValue();
                try {
                    aVar4.d.write(aVar4.f.getBytes(StandardCharsets.UTF_8));
                    break;
                } catch (IOException unused) {
                }
                try {
                    aVar4.d.write(gq2.g("Content-Disposition: form-data; name=\"", str2, "\"").getBytes(StandardCharsets.UTF_8));
                    break;
                } catch (IOException unused2) {
                }
                try {
                    aVar4.d.write(gq2.g("\r\n\r\n", str3, "\r\n").getBytes(StandardCharsets.UTF_8));
                } catch (IOException unused3) {
                    return;
                }
                break;
            case 13:
                ((InfoBarLayout) obj2).i.d();
                break;
            case 14:
                int iIntValue = ((Integer) obj).intValue();
                long j = ((MessageWrapper) obj2).e;
                if (j != 0) {
                    N.M6PkOWjr(j, iIntValue);
                    break;
                }
                break;
            case 15:
                PageInfoController pageInfoController = (PageInfoController) obj2;
                Drawable drawable = (Drawable) obj;
                Context context = pageInfoController.e;
                if (context != null) {
                    PageInfoContainer pageInfoContainer = pageInfoController.k;
                    if (drawable == null) {
                        pageInfoContainer.setFavicon(qj2.b(context, R$drawable.ic_globe_24dp));
                    } else {
                        pageInfoContainer.setFavicon(drawable);
                    }
                    break;
                }
                break;
            case 16:
                int i4 = PageInfoCookiesPreference.p0;
                ((PageInfoCookiesPreference.a) obj2).d.run();
                break;
            case 17:
                ((b.InterfaceC0137b) obj2).f(((Integer) obj).intValue());
                break;
            case 18:
                ((org.chromium.components.payments.a) obj2).z((String) obj);
                break;
            case 19:
                List list2 = (List) obj2;
                wz1 wz1Var = (wz1) obj;
                if (wz1Var != null) {
                    String str4 = wz1Var.b;
                    String str5 = wz1Var.c;
                    ry1 ry1Var = wz1Var.d;
                    tr3Var = new tr3(str4, str5, ry1Var != null ? new nr3(ry1Var.b, ry1Var.c) : null, wz1Var.e);
                }
                list2.add(tr3Var);
                break;
            case 20:
                ((Runnable) obj2).run();
                break;
            case 21:
                PermissionDialogController permissionDialogController = (PermissionDialogController) obj2;
                permissionDialogController.getClass();
                if (((Integer) obj).intValue() == 1 && (propertyModel = permissionDialogController.f) != null) {
                    permissionDialogController.i.b(5, propertyModel);
                }
                permissionDialogController.g = null;
                break;
            case 22:
                AccountCapabilitiesFetcher accountCapabilitiesFetcher = (AccountCapabilitiesFetcher) obj2;
                N.M8lmDXtQ((AccountCapabilities) obj, accountCapabilitiesFetcher.b);
                accountCapabilitiesFetcher.b = 0L;
                break;
            case 23:
                ((AdsBlockedInfoBar) obj2).d();
                break;
            case 24:
                eu3 eu3Var = (eu3) obj2;
                eu3Var.getClass();
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                aq1<Boolean> aq1Var = eu3Var.g;
                if (zBooleanValue) {
                    eu3Var.f = 1;
                    aq1Var.r(Boolean.TRUE);
                } else if (eu3Var.f == 1) {
                    eu3Var.f = 0;
                    aq1Var.r(Boolean.FALSE);
                }
                break;
            default:
                pn3 pn3Var = (pn3) obj2;
                pn3Var.h.getClass();
                ff0 ff0Var = new ff0((Bitmap) obj, pn3Var.i.a(), pn3Var.g, pn3Var.f());
                Iterator<Callback<pb2>> it = pn3Var.k.iterator();
                while (true) {
                    b.a aVar5 = (b.a) it;
                    if (aVar5.hasNext()) {
                        ((Callback) aVar5.next()).onResult(ff0Var);
                    }
                    break;
                }
                break;
        }
    }
}
