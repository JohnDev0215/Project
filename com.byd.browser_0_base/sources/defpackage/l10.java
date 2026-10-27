package defpackage;

import J.N;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.hardware.bydauto_test.TestInstrumentManagerImplCallback;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.m60;
import defpackage.nj3;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.BundleUtils;
import org.chromium.base.Callback;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.home.filter.a;
import org.chromium.chrome.browser.download.home.rename.RenameDialogCustomView;
import org.chromium.chrome.browser.feed.FeedReliabilityLoggingBridge;
import org.chromium.chrome.browser.feed.e;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.chrome.browser.firstrun.FirstRunActivity;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.gesturenav.AndroidUiNavigationGlow;
import org.chromium.chrome.browser.gesturenav.SideSlideLayout;
import org.chromium.chrome.browser.infobar.DuplicateDownloadInfoBar;
import org.chromium.chrome.browser.infobar.InfoBarContainerView;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.omnibox.status.StatusIconView;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.crash.browser.ChildProcessCrashObserver;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l10 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ l10(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 5;
        boolean z = true;
        switch (this.e) {
            case 0:
                m10 m10Var = (m10) this.f;
                N.MtL148iF((byte[]) m10Var.a.j(n10.a), false, 1, new wb(15, m10Var));
                return;
            case 1:
                h40 h40Var = (h40) this.f;
                Object obj = h40.f;
                File file = new File(h40Var.n(), h40Var.d());
                if (!file.exists() || file.delete()) {
                    return;
                }
                Log.e("cr_tabmodel", "Failed to delete file: " + file);
                return;
            case 2:
                CustomTabsConnection customTabsConnection = (CustomTabsConnection) this.f;
                HashSet hashSet = CustomTabsConnection.o;
                customTabsConnection.getClass();
                TraceEvent traceEventH0 = TraceEvent.H0("CustomTabsConnection.initializeBrowser()", null);
                try {
                    Context context = xz.a;
                    Object obj2 = ThreadUtils.a;
                    sp.a().d(true);
                    o90 o90Var = ChildProcessLauncherHelperImpl.m;
                    LauncherThread.a(new vn(context, true));
                    sp spVarA = sp.a();
                    if (!spVarA.g) {
                        spVarA.g = true;
                        TraceEvent.q("NetworkChangeNotifier.init", null);
                        NetworkChangeNotifier.init();
                        NetworkChangeNotifier.setAutoDetectConnectivityState(true);
                        TraceEvent.C("NetworkChangeNotifier.init");
                    }
                    customTabsConnection.f.set(true);
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (traceEventH0 != null) {
                        try {
                            traceEventH0.close();
                            break;
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            case 3:
                ((w20) this.f).a(1);
                return;
            case 4:
                ((lx0) this.f).b();
                return;
            case 5:
                ((bj) this.f).c.a(false);
                return;
            case 6:
                aw1 aw1Var = (aw1) this.f;
                aw1Var.t();
                aw1Var.J();
                aw1Var.y();
                if (aw1Var.v() || Build.VERSION.SDK_INT <= 31) {
                    return;
                }
                aw1Var.Q(aw1Var.e.getWindow().getAttributes().y);
                aw1Var.S((int) (r0 + 1 + aw1Var.R), true);
                aw1Var.P(0);
                return;
            case 7:
                ld0 ld0Var = ((DownloadManagerService) this.f).l;
                if (ld0Var.a() == null) {
                    return;
                }
                tp2 tp2VarA = tp2.a(xz.a.getString(R$string.download_location_no_sd_card_snackbar), ld0Var, 1, 24);
                tp2VarA.i = false;
                tp2VarA.j = TestInstrumentManagerImplCallback.SET_INSTRUMENT_THEME;
                ld0Var.a().c(tp2VarA);
                return;
            case 8:
                ((a) this.f).a();
                return;
            case 9:
                ((m60.b) this.f).a();
                return;
            case 10:
                RenameDialogCustomView renameDialogCustomView = (RenameDialogCustomView) this.f;
                if (renameDialogCustomView.f.requestFocus()) {
                    ((InputMethodManager) renameDialogCustomView.f.getContext().getSystemService("input_method")).showSoftInput(renameDialogCustomView.f, 2);
                    return;
                }
                return;
            case 11:
                ((FeedReliabilityLoggingBridge) this.f).b.getClass();
                return;
            case 12:
                ((e.b) this.f).b();
                return;
            case 13:
                i.a aVar = (i.a) this.f;
                N.MxULk9PS(0, 37);
                aVar.o();
                return;
            case 14:
                FirstRunActivity firstRunActivity = (FirstRunActivity) this.f;
                int i2 = FirstRunActivity.x0;
                if (firstRunActivity.d()) {
                    return;
                }
                firstRunActivity.n0.b(null);
                firstRunActivity.m2();
                return;
            case 15:
                AndroidUiNavigationGlow androidUiNavigationGlow = (AndroidUiNavigationGlow) this.f;
                ((ViewGroup) androidUiNavigationGlow.e).removeView(androidUiNavigationGlow.g);
                return;
            case 16:
                org.chromium.chrome.browser.gesturenav.a aVar2 = (org.chromium.chrome.browser.gesturenav.a) this.f;
                aVar2.k = null;
                SideSlideLayout sideSlideLayout = aVar2.h;
                if (sideSlideLayout != null && sideSlideLayout.getParent() != null) {
                    z = false;
                }
                if (z) {
                    return;
                }
                l10 l10Var = aVar2.k;
                if (l10Var != null) {
                    aVar2.h.removeCallbacks(l10Var);
                    aVar2.k = null;
                }
                aVar2.removeView(aVar2.h);
                return;
            case 17:
                g63.this.g.onBackPressed();
                return;
            case 18:
            default:
                StatusIconView statusIconView = (StatusIconView) this.f;
                int i3 = StatusIconView.g;
                statusIconView.getClass();
                ao3.e(statusIconView, "StatusIconView.setVisibility Runnable");
                return;
            case 19:
                dv0 dv0Var = (dv0) this.f;
                Activity activity = dv0Var.h;
                ev0 ev0Var = dv0Var.g;
                String str = ev0Var.d;
                GURL gurl = ev0Var.c;
                BookmarkModel bookmarkModel = dv0Var.u;
                lh.a(activity, bookmarkModel, str, gurl, bookmarkModel.k(), 0);
                return;
            case 20:
                ((oy0) this.f).a.run();
                return;
            case 21:
                ((DuplicateDownloadInfoBar) this.f).getClass();
                oj2.U(3, 5, "Download.DuplicateInfobarEvent.Download");
                return;
            case 22:
                InfoBarContainerView infoBarContainerView = (InfoBarContainerView) this.f;
                boolean z2 = InfoBarContainerView.v;
                infoBarContainerView.g(true);
                return;
            case 23:
                sp spVar = (sp) this.f;
                if (spVar.e) {
                    return;
                }
                spVar.e = true;
                nm0 nm0Var = new nm0();
                synchronized (ContentUriUtils.b) {
                    ContentUriUtils.a = nm0Var;
                    break;
                }
                tp tpVar = new tp();
                Object obj3 = ThreadUtils.a;
                ChildProcessCrashObserver.a = tpVar;
                xz.a.registerComponentCallbacks(new we1("Browser"));
                Context context2 = xz.a;
                boolean zIsBackgroundRestricted = ((ActivityManager) context2.getSystemService("activity")).isBackgroundRestricted();
                oj2.O("Android.BackgroundRestrictions.IsBackgroundRestricted", zIsBackgroundRestricted);
                int iA = UmaUtils.a(context2);
                oj2.U(iA, 9, "Android.BackgroundRestrictions.StandbyBucket");
                oj2.U(iA, 9, zIsBackgroundRestricted ? "Android.BackgroundRestrictions.StandbyBucket.WithUserRestriction" : "Android.BackgroundRestrictions.StandbyBucket.WithoutUserRestriction");
                wp.b.getClass();
                com.google.common.collect.e eVar = CachedFeatureFlags.a;
                SharedPreferencesManager.getInstance().n(System.currentTimeMillis(), "Chrome.Flags.LastCachedMinimalBrowserFlagsTimeMillis");
                Object[] objArr = {bq.y};
                ArrayList arrayList = new ArrayList(1);
                Object obj4 = objArr[0];
                Objects.requireNonNull(obj4);
                arrayList.add(obj4);
                CachedFeatureFlags.a(Collections.unmodifiableList(arrayList));
                Iterator<rk> it = wp.c.iterator();
                while (it.hasNext()) {
                    it.next().a();
                }
                Object obj5 = BundleUtils.a;
                AppHooks.get().getClass();
                return;
            case 24:
                ((RecyclerView) this.f).N();
                return;
            case 25:
                nf1 nf1Var = (nf1) this.f;
                nf1Var.f.c(nf1Var.k, true);
                return;
            case 26:
                nj3 nj3Var = (nj3) this.f;
                if (nj3Var.b.b != 2) {
                    return;
                }
                if (nj3Var.d == null) {
                    nj3Var.d = new nj3.c(nj3Var.b.i);
                }
                nj3.c cVar = nj3Var.d;
                if (!cVar.e) {
                    cVar.a = nj3Var.b.i.a;
                }
                if (!nj3Var.e) {
                    oj3 oj3Var = nj3Var.c;
                    ye3 ye3Var = oj3Var.a;
                    ye3Var.getClass();
                    s62 s62Var = new s62();
                    ye3Var.a.b(new zc0(21, s62Var));
                    s62Var.g(new lj1(i, oj3Var));
                    nj3Var.e = true;
                }
                Iterator<Callback<nj3.c>> it2 = nj3Var.a.iterator();
                while (true) {
                    b.a aVar3 = (b.a) it2;
                    if (!aVar3.hasNext()) {
                        return;
                    } else {
                        ((Callback) aVar3.next()).onResult(nj3Var.d);
                    }
                }
                break;
            case 27:
                xz.a.getSharedPreferences("com.google.android.apps.chrome.omaha", 0).edit().putString("UpdateProtos_Tracking", Base64.encodeToString(((kj3) this.f).q(), 0)).apply();
                return;
            case 28:
                ((ur1) this.f).f();
                return;
        }
    }
}
