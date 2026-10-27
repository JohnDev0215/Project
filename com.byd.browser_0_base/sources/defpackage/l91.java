package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.bydauto.audio.tsimpl.LocalListenerHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.speech.SpeechRecognizer;
import android.telephony.CellInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.j;
import defpackage.eo1;
import defpackage.jl3;
import defpackage.k70;
import defpackage.rb2;
import defpackage.vq0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.chromium.base.BuildInfo;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.contextmenu.ContextMenuHelper;
import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.chrome.browser.infobar.InfoBarContainerView;
import org.chromium.chrome.browser.omnibox.geo.f;
import org.chromium.chrome.browser.omnibox.h;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.share.qrcode.scan_tab.CameraPreview;
import org.chromium.chrome.browser.signin.PrivacyFragment;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.accessibility.FontSizePrefs;
import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;
import org.chromium.components.content_creation.notes.models.Background;
import org.chromium.components.content_creation.notes.models.NoteTemplate;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.VisualsCallback;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.policy.AppRestrictionsProvider;
import org.chromium.components.policy.CombinedPolicyProvider;
import org.chromium.content.R$array;
import org.chromium.content.browser.SpeechRecognitionImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l91 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ l91(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        int i2 = 3;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                ((LocalListenerHelper) obj2).lambda$asyncNotifyListener$0((Consumer) obj);
                return;
            case 1:
                ((eg3) obj2).b.remove((Uri) obj);
                return;
            case 2:
                ((rb2.c) obj2).d((Typeface) obj);
                return;
            case 3:
                j jVar = (j) obj2;
                j.a aVar = (j.a) obj;
                i21.e(jVar, "this$0");
                i21.e(aVar, "$operation");
                if (jVar.b.contains(aVar)) {
                    int i3 = aVar.a;
                    View view = aVar.c.f12J;
                    i21.d(view, "operation.fragment.mView");
                    gq2.a(i3, view);
                    return;
                }
                return;
            case 4:
                int i4 = k70.b.i;
                ((Callback) obj2).onResult((k70.a) obj);
                return;
            case 5:
                ChromeActivity chromeActivity = (ChromeActivity) obj2;
                String str = (String) obj;
                k11 k11Var = ChromeActivity.Z0;
                if (chromeActivity.d()) {
                    return;
                }
                if (chromeActivity.N0.x != null) {
                    oj2.b0(chromeActivity.D0 - chromeActivity.C0, C0175b.j("MobileStartup.ToolbarInflationTime.", str));
                    chromeActivity.N0.x.E.k();
                }
                vj1.h.getClass();
                if (vj1.o(chromeActivity)) {
                    chromeActivity.X2(true, true);
                }
                long jL = b.l(chromeActivity.getIntent());
                if (jL != -1) {
                    chromeActivity.W2(chromeActivity.N - jL);
                }
                ya0 ya0VarA = ya0.a(chromeActivity);
                float f = ya0VarA.c.x;
                float f2 = ya0VarA.d;
                int i5 = (int) ((f / f2) + 0.5f);
                int i6 = (int) ((r1.y / f2) + 0.5f);
                int iMin = Math.min(i5, i6);
                int iMax = Math.max(i5, i6);
                oj2.W(iMin, 100, 1000, 92, "Android.DeviceSize.SmallestDisplaySize2");
                oj2.W(iMax, 200, 2000, 92, "Android.DeviceSize.LargestDisplaySize2");
                oj2.W((int) (chromeActivity.N0.g() * 10.0d), 40, 152, 58, "Android.DeviceSize.ScreenSizeInTensOfInches");
                oj2.O("Android.PlayServices.Installed", false);
                oj2.a0(0, "Android.PlayServices.Version");
                Profile profileD = Profile.d();
                Object obj3 = ThreadUtils.a;
                if (FontSizePrefs.c == null) {
                    FontSizePrefs.c = new FontSizePrefs(profileD);
                }
                oj2.a0((int) (FontSizePrefs.c.a() * 100.0f), "Accessibility.Android.UserFontSizePref.OnStartup");
                return;
            case 6:
                ((Callback) obj2).onResult((qa) obj);
                return;
            case 7:
                HandlerThread handlerThread = (HandlerThread) obj;
                try {
                    ((Context) obj2).getClassLoader().loadClass("org.chromium.chrome.browser.ChromeTabbedActivity$Preload").newInstance();
                    handlerThread.quit();
                    return;
                } catch (ReflectiveOperationException e) {
                    throw new uv(e);
                }
            case 8:
                ((sq2) obj2).B1((Tab) obj);
                return;
            case 9:
                eo1 eo1Var = (eo1) obj2;
                List list = (List) obj;
                eo1Var.getClass();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    NoteTemplate noteTemplate = (NoteTemplate) it.next();
                    Background background = noteTemplate.c;
                    if (background instanceof tw0) {
                        if ((((tw0) background).b == null) != false) {
                            it.remove();
                        }
                    }
                    Background background2 = noteTemplate.f;
                    if (background2 instanceof tw0) {
                        if ((((tw0) background2).b == null) != false) {
                            it.remove();
                        }
                    }
                }
                ArrayList arrayList = new ArrayList();
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList.add(new eo1.a((NoteTemplate) it2.next()));
                }
                HashSet<mh3> hashSet = new HashSet();
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    hashSet.add(((eo1.a) it3.next()).b);
                }
                do1 do1Var = new do1(eo1Var, arrayList);
                gt0 gt0Var = eo1Var.c;
                gt0Var.getClass();
                if (hashSet.isEmpty()) {
                    return;
                }
                HandlerThread handlerThreadH = C0173a.h("GoogleFontHandlerThread");
                HashMap map = new HashMap();
                for (mh3 mh3Var : hashSet) {
                    uo0.b(gt0Var.a.getApplicationContext(), new po0(R$array.ui_com_google_android_gms_fonts_certs, mh3Var.a()), 0, new hb2(new Handler(handlerThreadH.getLooper())), new rl(new ft0(gt0Var, map, mh3Var, hashSet, do1Var, handlerThreadH)));
                }
                return;
            case 10:
                ContextMenuHelper contextMenuHelper = (ContextMenuHelper) obj2;
                ContextMenuParams contextMenuParams = (ContextMenuParams) obj;
                contextMenuHelper.m = false;
                contextMenuHelper.l = SystemClock.uptimeMillis();
                WebContents webContents = contextMenuHelper.a;
                oj2.O("ContextMenu.Shown", webContents != null);
                Object[] objArr = new Object[1];
                objArr[0] = contextMenuParams.k ? "Video" : contextMenuParams.j ? contextMenuParams.i ? "ImageLink" : "Image" : contextMenuParams.p ? "SharedHighlightingInteraction" : "Link";
                oj2.O(String.format("ContextMenu.Shown.%s", objArr), webContents != null);
                return;
            case 11:
                y20 y20Var = (y20) obj2;
                Tab tab = (Tab) obj;
                y20Var.getClass();
                if (!tab.isInitialized() || z7.M(y20Var.e.f)) {
                    return;
                }
                tab.getView().setBackgroundResource(0);
                return;
            case 12:
                ((v30) obj2).c.b((oj) obj);
                return;
            case 13:
                fj2 fj2Var = (fj2) obj2;
                Tab tab2 = (Tab) obj;
                fj2Var.i.c().z().i(tab2, 0, tab2.getLaunchType(), 0);
                tab2.l(3, 7);
                fj2Var.h = xg1.a(fj2Var.f);
                Resources resources = fj2Var.e.getResources();
                PropertyModel.b bVar = new PropertyModel.b(ng1.H);
                bVar.c(ng1.a, 38);
                bVar.e(ng1.f, resources.getString(R$string.undo_restoration_title));
                bVar.c(ng1.m, R$drawable.infobar_chrome);
                bVar.e(ng1.h, resources.getString(R$string.restore_custom_tab_description));
                bVar.e(ng1.c, resources.getString(R$string.undo_restoration_button_text));
                bVar.e(ng1.d, new hs(i2, fj2Var));
                bVar.e(ng1.x, new so(22, fj2Var));
                fj2Var.h.c(bVar.a(), true);
                return;
            case 14:
                ((VisualsCallback) obj2).g(((OfflineItem) obj).e, null);
                return;
            case 15:
                int i7 = yq1.H;
                ((Callback) ((PropertyModel) obj2).j(t81.r)).onResult((OfflineItem) obj);
                return;
            case 16:
                int i8 = e.n;
                ((e) obj2).a((e.b) obj);
                return;
            case 17:
                ((y8) obj2).a((p52) obj);
                return;
            case 18:
                sp spVar = (sp) obj2;
                spVar.getClass();
                ((nj) obj).d1();
                Object obj4 = ThreadUtils.a;
                if (spVar.e) {
                    return;
                }
                AppHooks appHooks = AppHooks.get();
                CombinedPolicyProvider combinedPolicyProviderA = CombinedPolicyProvider.a();
                appHooks.getClass();
                AppRestrictionsProvider appRestrictionsProvider = new AppRestrictionsProvider(xz.a);
                if ((combinedPolicyProviderA.c == null ? (char) 0 : (char) 1) != 0) {
                    combinedPolicyProviderA.c = null;
                }
                combinedPolicyProviderA.d.add(appRestrictionsProvider);
                combinedPolicyProviderA.e.add(null);
                appRestrictionsProvider.b = r4.size() - 1;
                appRestrictionsProvider.a = combinedPolicyProviderA;
                appRestrictionsProvider.e();
                if (combinedPolicyProviderA.a != 0) {
                    appRestrictionsProvider.b();
                }
                if (SpeechRecognizer.isRecognitionAvailable(xz.a)) {
                    SpeechRecognitionImpl.f = Build.VERSION.SDK_INT >= 31 ? SpeechRecognitionImpl.a(-1, "com.google.android.tts") : SpeechRecognitionImpl.a(300207030, "com.google.android.googlequicksearchbox");
                    return;
                }
                return;
            case 19:
                vq0.a aVar2 = vq0.i;
                ((WebContents) obj2).k0(false);
                InfoBarContainer infoBarContainerD = InfoBarContainer.d((Tab) obj);
                infoBarContainerD.o = false;
                InfoBarContainerView infoBarContainerView = infoBarContainerD.q;
                if (infoBarContainerView == null) {
                    return;
                }
                infoBarContainerView.setHidden(false);
                return;
            case 20:
                Callback callback = (Callback) obj2;
                List list2 = (List) obj;
                HashSet hashSet2 = new HashSet();
                if (list2 != null) {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    for (int i9 = 0; i9 < list2.size(); i9++) {
                        f.a aVarD = org.chromium.chrome.browser.omnibox.geo.e.d((CellInfo) list2.get(i9), jElapsedRealtime, jCurrentTimeMillis);
                        if (aVarD.a != 0) {
                            hashSet2.add(aVarD);
                        }
                    }
                }
                callback.onResult(hashSet2);
                return;
            case 21:
                StatusView.a((StatusView) obj2, (us) obj);
                return;
            case 22:
                AutocompleteMatch autocompleteMatch = (AutocompleteMatch) obj;
                d dVar = (d) ((c) obj2).b;
                dVar.n(false);
                boolean z = autocompleteMatch.c;
                h hVar = (h) dVar.h;
                boolean zIsEmpty = TextUtils.isEmpty(hVar.c());
                String string = autocompleteMatch.i;
                if (z) {
                    string = TextUtils.concat(string, " ").toString();
                }
                ((org.chromium.chrome.browser.omnibox.c) dVar.g).a(string);
                dVar.i(hVar.c(), hVar.b());
                if (!z) {
                    n3.X0("MobileOmniboxRefineSuggestion.Url");
                    return;
                } else {
                    dVar.E |= zIsEmpty ? 1 : 2;
                    n3.X0("MobileOmniboxRefineSuggestion.Search");
                    return;
                }
            case 23:
                EventForwarder eventForwarder = (EventForwarder) obj2;
                Point point = (Point) obj;
                float f3 = point.x;
                float f4 = point.y;
                long j = eventForwarder.b;
                if (j == 0) {
                    return;
                }
                N.M6lTZ5w8(j, eventForwarder, f3, f4);
                return;
            case 24:
                or1 or1Var = ((rd2) obj2).a;
                or1Var.getClass();
                Object obj5 = ThreadUtils.a;
                StringBuilder sb = new StringBuilder("OmahaBase::checkForUpdates(): Current version String: \"");
                k11 k11Var2 = jl3.a;
                jl3.a.a.getClass();
                sb.append(BuildInfo.a.a.e);
                sb.append("\"");
                Log.i("cr_omaha", sb.toString());
                or1Var.a.getClass();
                AppHooks.get().getClass();
                Log.w("cr_omaha", "OmahaBase::checkForUpdates(): Request generator is null. This is probably a developer build.");
                PostTask.c(rh3.a, new sb2(i2, 4, (WeakReference) obj));
                return;
            case 25:
                ((CameraPreview) obj2).setupCamera((Camera) obj);
                return;
            case 26:
                PrivacyFragment privacyFragment = (PrivacyFragment) obj2;
                View view2 = (View) obj;
                int iMax2 = Math.max(privacyFragment.a0.getMeasuredWidth(), view2.getMeasuredWidth());
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                layoutParams.width = iMax2;
                view2.setLayoutParams(layoutParams);
                privacyFragment.a0.setLayoutParams(layoutParams);
                return;
            case 27:
                ((ViewResourceFrameLayout) obj).removeOnLayoutChangeListener(((ft2) obj2).a);
                return;
            case 28:
                pi1 pi1Var = (pi1) obj2;
                Object obj6 = pi1.c;
                pi1Var.getClass();
                new oi1((List) obj, new f52(10, pi1Var)).c(i9.e);
                return;
            default:
                HashMap map2 = l02.l;
                ((Callback) obj2).onResult((l02) obj);
                return;
        }
    }
}
