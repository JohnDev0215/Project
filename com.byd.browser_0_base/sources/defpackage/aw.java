package defpackage;

import J.N;
import android.os.Looper;
import android.os.SystemClock;
import androidx.activity.ComponentDialog;
import androidx.constraintlayout.helper.widget.LogJson;
import defpackage.aa0;
import defpackage.fc;
import defpackage.h20;
import defpackage.sq2;
import defpackage.t90;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.app.video_tutorials.VideoPlayerActivity;
import org.chromium.chrome.browser.autofill.e;
import org.chromium.chrome.browser.contextmenu.ContextMenuHelper;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;
import org.chromium.chrome.browser.crash.MinidumpUploadServiceImpl;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.home.filter.a;
import org.chromium.chrome.browser.feed.c;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.content_public.browser.WebContents;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.modelutil.f;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class aw implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ aw(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        f fVar;
        int i;
        int i2 = this.e;
        int i3 = 3;
        Object obj = this.f;
        switch (i2) {
            case 0:
                ComponentDialog.a((ComponentDialog) obj);
                break;
            case 1:
                LogJson logJson = (LogJson) obj;
                if (logJson.r) {
                    logJson.v();
                    logJson.postDelayed(new wv(i3, logJson), logJson.n);
                }
                break;
            case 2:
                pe0 pe0Var = (pe0) obj;
                boolean zIsPopupShowing = pe0Var.e.isPopupShowing();
                pe0Var.t(zIsPopupShowing);
                pe0Var.j = zIsPopupShowing;
                break;
            case 3:
                TraceEvent.a aVar = (TraceEvent.a) obj;
                aVar.getClass();
                Object obj2 = ThreadUtils.a;
                if (!aVar.n) {
                    Looper.myQueue().addIdleHandler(aVar);
                    aVar.n = true;
                }
                aVar.b();
                break;
            case 4:
                qn qnVar = (qn) obj;
                if (!qnVar.l && !qnVar.n && !qnVar.D) {
                    qn.L = true;
                    qnVar.j();
                    break;
                }
                break;
            case 5:
                x6 x6Var = ((i7) ((h7) obj)).e;
                if (x6Var != null && (fVar = x6Var.l) != null) {
                    fVar.notifyDataSetChanged();
                    break;
                }
                break;
            case 6:
                ((VideoPlayerActivity) obj).finish();
                break;
            case 7:
                ((e) obj).d();
                break;
            case 8:
                ((fc.a) obj).b(false);
                break;
            case 9:
                i6 i6Var = (i6) obj;
                ((i7) i6Var.f).c(Integer.valueOf(i6Var.i));
                break;
            case 10:
                y81.b((t90.d) obj);
                break;
            case 11:
                ((aa0.e) ((t90.a) obj)).c(5);
                break;
            case 12:
                ((aa0.m) ((t90.c) obj)).a(5, new r72[0]);
                break;
            case 13:
                bg3 bg3Var = (bg3) obj;
                h20.b bVar = bg3Var.e.h;
                if (bVar != null && (i = bVar.c) != 0) {
                    boolean z = i == 1;
                    if (z != bg3Var.j) {
                        bg3Var.a();
                        bg3Var.j = z;
                        bg3Var.i = SystemClock.elapsedRealtime();
                        if (bg3Var.j && !bg3Var.k) {
                            Tab tab = (Tab) bg3Var.g.f;
                            if (tab != null) {
                                WebContents webContentsC = tab.c();
                                bg3Var.f.getClass();
                                n3.X0("BrowserServices.TwaOpened");
                                if (webContentsC != null) {
                                    N.M$ejnyHh(webContentsC, "TrustedWebActivity.Open", "HasOccurred");
                                }
                            }
                            bg3Var.k = true;
                            break;
                        }
                    }
                }
                break;
            case 14:
                sq2.a aVar2 = (sq2.a) obj;
                aVar2.e.getViewTreeObserver().removeOnDrawListener(aVar2);
                break;
            case 15:
                ContextMenuHelper contextMenuHelper = (ContextMenuHelper) obj;
                oj2.b0(SystemClock.uptimeMillis() - contextMenuHelper.l, "ContextMenu.TimeToTakeAction.".concat(contextMenuHelper.m ? "SelectedItem" : "Abandoned"));
                contextMenuHelper.g = null;
                ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl = contextMenuHelper.c;
                if (contextMenuNativeDelegateImpl != null) {
                    contextMenuNativeDelegateImpl.b = 0L;
                    contextMenuHelper.c = null;
                }
                vz vzVar = contextMenuHelper.d;
                if (vzVar != null) {
                    vzVar.e();
                    contextMenuHelper.d = null;
                }
                long j = contextMenuHelper.b;
                if (j != 0) {
                    N.McrcWTzG(j, contextMenuHelper);
                    break;
                }
                break;
            case 16:
                AtomicBoolean atomicBoolean = MinidumpUploadServiceImpl.b;
                ApplicationStatus.e((ApplicationStatus.d) obj);
                break;
            case 17:
                m10 m10Var = (m10) obj;
                N.MVWVyQhp((byte[]) m10Var.a.j(n10.a), false, 1, new so(17, m10Var));
                break;
            case 18:
                BaseCustomTabActivity baseCustomTabActivity = (BaseCustomTabActivity) obj;
                int i4 = BaseCustomTabActivity.o1;
                if (((baseCustomTabActivity.getIntent().getFlags() & 268959744) != 0 ? 1 : 0) != 0) {
                    baseCustomTabActivity.finishAndRemoveTask();
                } else {
                    baseCustomTabActivity.finish();
                }
                break;
            case 19:
                ((e30) obj).a.m.setVisibility(8);
                break;
            case 20:
                ((a40) obj).a();
                break;
            case 21:
                v30 v30Var = (v30) obj;
                a30 a30Var = v30Var.a;
                int i5 = a30Var.c;
                oj ojVar = v30Var.b;
                if (i5 != 2) {
                    v30Var.c.a(ojVar);
                    break;
                } else if (ojVar.d() == 3 && NetworkChangeNotifier.a()) {
                    a30Var.b.z();
                    break;
                }
                break;
            case 22:
                ((CustomTabToolbar.b) ((bj) obj).g).z();
                break;
            case 23:
                cw1 cw1Var = (cw1) obj;
                cw1Var.u.k(cw1Var.w, cw1Var.x, cw1Var.y);
                cw1Var.u.Y0();
                break;
            case 24:
                ((dw1) obj).I();
                break;
            case 25:
                DownloadManagerService downloadManagerService = (DownloadManagerService) obj;
                downloadManagerService.o = false;
                downloadManagerService.n();
                break;
            case 26:
                ((a) obj).a();
                break;
            case 27:
                r81 r81Var = (r81) obj;
                (r81Var.k == 7 ? r81Var.h : r81Var.e).c();
                r81Var.b.a();
                break;
            case 28:
                tq1 tq1Var = (tq1) obj;
                if (tq1Var.f) {
                    HashMap map = new HashMap();
                    HashMap map2 = new HashMap();
                    for (int i6 = 0; i6 < 8; i6++) {
                        map.put(Integer.valueOf(i6), 0);
                        map2.put(Integer.valueOf(i6), 0);
                    }
                    rq1 rq1Var = tq1Var.g;
                    for (OfflineItem offlineItem : rq1Var.h()) {
                        int iIntValue = z7.l(offlineItem).intValue();
                        map.put(Integer.valueOf(iIntValue), Integer.valueOf(((Integer) map.get(Integer.valueOf(iIntValue))).intValue() + 1));
                        if (offlineItem.s > offlineItem.r) {
                            map2.put(Integer.valueOf(iIntValue), Integer.valueOf(((Integer) map2.get(Integer.valueOf(iIntValue))).intValue() + 1));
                        }
                    }
                    oj2.R(rq1Var.h().size(), "Android.DownloadManager.InitialCount.Total");
                    for (Map.Entry entry : map.entrySet()) {
                        oj2.R(((Integer) entry.getValue()).intValue(), "Android.DownloadManager.InitialCount.".concat(oj2.s(((Integer) entry.getKey()).intValue())));
                    }
                    for (Map.Entry entry2 : map2.entrySet()) {
                        oj2.R(((Integer) entry2.getValue()).intValue(), "Android.DownloadManager.InitialCount.Viewed.".concat(oj2.s(((Integer) entry2.getKey()).intValue())));
                    }
                    break;
                }
                break;
            default:
                c cVar = (c) obj;
                cVar.n.postDelayed(new zc0(i3, cVar), 50L);
                break;
        }
    }
}
