package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Pair;
import android.view.animation.OvershootInterpolator;
import defpackage.wu1;
import java.util.LinkedHashSet;
import org.chromium.chrome.browser.app.video_tutorials.VideoPlayerActivity;
import org.chromium.chrome.browser.base.SplitChromeApplication;
import org.chromium.chrome.browser.base.SplitCompatApplication;
import org.chromium.chrome.browser.base.SplitPreloader;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.customtabs.features.TabInteractionRecorder;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.j;
import org.chromium.chrome.browser.tasks.tab_management.o1;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.components.messages.R$dimen;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.b;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p11 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ p11(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        Tab tab = null;
        Object obj = this.f;
        switch (i) {
            case 0:
                oq oqVar = (oq) obj;
                return Long.valueOf(N.Mb4U6$Qo(oqVar, oqVar.a));
            case 1:
                VideoPlayerActivity videoPlayerActivity = (VideoPlayerActivity) obj;
                int i2 = VideoPlayerActivity.K;
                videoPlayerActivity.getClass();
                WebContents webContentsA = pq3.a(Profile.d(), false);
                ContentView contentView = new ContentView(videoPlayerActivity, webContentsA);
                webContentsA.i(new ViewAndroidDelegate(contentView), contentView, videoPlayerActivity.I, new b());
                return Pair.create(webContentsA, contentView);
            case 2:
                SplitPreloader splitPreloader = SplitChromeApplication.j;
                ((SplitChromeApplication) obj).getClass();
                return new SplitCompatApplication.b();
            case 3:
                return (EphemeralTabCoordinator) ((p61) obj).get();
            case 4:
                fj2 fj2Var = (fj2) obj;
                dj2 dj2VarF = fj2Var.g.f();
                fj2Var.l = fj2Var.i.c().k();
                ej2 ej2Var = (ej2) dj2VarF;
                if (((Tab) ej2Var.a.f) != null) {
                    ll llVar = ej2Var.c;
                    if (llVar != null) {
                        llVar.a();
                        ej2Var.c = null;
                    }
                    um3 um3Var = ej2Var.a;
                    Tab tab2 = (Tab) um3Var.f;
                    um3Var.f = null;
                    N.MaN57Yaj(((TabInteractionRecorder) N.MJqYRCCN(tab2)).a);
                    tab = tab2;
                }
                if (tab != null) {
                    l91 l91Var = new l91(fj2Var, 13, tab);
                    bb2 bb2VarB = bb2.b(tab);
                    CompositorViewHolder compositorViewHolder = fj2Var.j.get();
                    o30 o30Var = fj2Var.k.get();
                    bb2VarB.getClass();
                    compositorViewHolder.v();
                    Tab tab3 = bb2VarB.e;
                    tab3.E(fj2Var.f, o30Var);
                    N.MM6uB79X(tab3.c());
                    l91Var.run();
                }
                return 1;
            case 5:
                LinkedHashSet<Integer> linkedHashSet = i92.u;
                ((i92) obj).y1();
                return 1;
            case 6:
                return ((qq0) obj).m;
            case 7:
                return Boolean.valueOf(oj2.z((Context) obj));
            case 8:
                g63 g63Var = (g63) obj;
                return g63Var.n0.get().p0(g63Var.T.get().r());
            case 9:
                j jVar = ((o1) obj).n;
                return jVar != null ? Boolean.valueOf(jVar.isVisible()) : Boolean.FALSE;
            case 10:
                return (wu1.a) ((nw2) obj).get();
            case 11:
                Profile profile = (Profile) ((zp1) obj).get();
                if (profile != null) {
                    return oj2.t(profile);
                }
                return null;
            case 12:
                Activity activity = ((af1) obj).a;
                if (!activity.isFinishing() && !activity.isDestroyed()) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 13:
                return ((bc3) obj).d();
            case 14:
                return Boolean.valueOf(!((t6) obj).k);
            case 15:
                return Boolean.valueOf(((nr2) obj).M.k() != null);
            case 16:
                return ((l43) obj).R().n(false);
            case 17:
                return wt1.c((Intent) obj, 64);
            default:
                Resources resources = (Resources) obj;
                OvershootInterpolator overshootInterpolator = mg1.s;
                return Float.valueOf(Math.min(resources.getDimensionPixelSize(R$dimen.message_max_horizontal_translation), resources.getDisplayMetrics().widthPixels / 2.0f));
        }
    }
}
