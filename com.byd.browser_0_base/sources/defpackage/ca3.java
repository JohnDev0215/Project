package defpackage;

import J.N;
import android.content.Context;
import defpackage.aa3;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.suggestions.mostvisited.MostVisitedSitesBridge;

/* JADX INFO: compiled from: TileGroupDelegateImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class ca3 implements aa3.b {
    public static final HashSet h = new HashSet(Arrays.asList(1, 8));
    public final Context a;
    public final wp2 b;
    public final hw2 c;
    public final MostVisitedSitesBridge d;
    public boolean e;
    public ba3 f;
    public final int g;

    public ca3(Context context, Profile profile, hw2 hw2Var, wp2 wp2Var, int i) {
        this.a = context;
        this.b = wp2Var;
        this.c = hw2Var;
        Object obj = ThreadUtils.a;
        if (d70.b == null) {
            d70.b = new d70();
        }
        d70.b.getClass();
        this.d = new MostVisitedSitesBridge(profile);
        this.g = i;
    }

    public static void d(int i, int i2) {
        String str;
        if (i != 6) {
            n3.Z0(i2, 0);
        }
        if (h.contains(Integer.valueOf(i))) {
            if (i2 == 0) {
                str = "";
            } else if (i2 == 1) {
                str = "NewTabPage";
            } else {
                if (i2 != 2) {
                    throw new AssertionError("The host surface item provided here is wrong.");
                }
                str = "StartSurface";
            }
            n3.X0("Suggestions.Tile.Tapped.".concat(str));
        }
    }

    @Override // aa3.b
    public void a(int i, w93 w93Var) {
        d(i, this.g);
        String strJ = w93Var.a.b.j();
        if (i != 6) {
            e(w93Var);
        }
        this.c.c(strJ, i, false);
        r82.b("Chrome.Querytiles.RecentMvClicks");
    }

    @Override // aa3.b
    public void b(List<w93> list) {
        MostVisitedSitesBridge mostVisitedSitesBridge;
        if (this.e) {
            return;
        }
        Iterator<w93> it = list.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            mostVisitedSitesBridge = this.d;
            if (!zHasNext) {
                break;
            }
            w93 next = it.next();
            long j = mostVisitedSitesBridge.a;
            if (j != 0) {
                int i = next.b;
                int i2 = next.c;
                int i3 = next.d;
                ao2 ao2Var = next.a;
                N.MwKG6a15(j, mostVisitedSitesBridge, i, i2, i3, ao2Var.c, ao2Var.d, ao2Var.b);
            }
        }
        int size = list.size();
        long j2 = mostVisitedSitesBridge.a;
        if (j2 != 0) {
            N.MtbOuYlk(j2, mostVisitedSitesBridge, size);
        }
        for (w93 w93Var : list) {
            if (w93Var.g != null) {
                oj2.U(w93Var.b, 12, "NewTabPage.TileOfflineAvailable");
            }
        }
    }

    public final void c() {
        this.e = true;
        ba3 ba3Var = this.f;
        if (ba3Var != null) {
            this.b.a(ba3Var);
        }
        MostVisitedSitesBridge mostVisitedSitesBridge = this.d;
        N.MdGxo8sV(mostVisitedSitesBridge.a, mostVisitedSitesBridge);
        mostVisitedSitesBridge.a = 0L;
        mostVisitedSitesBridge.b = null;
    }

    public final void e(w93 w93Var) {
        um1.a(3);
        n3.X0("MobileNTPMostVisited");
        zb2.g("Chrome.StartSurface.TapMVTilesCount");
        MostVisitedSitesBridge mostVisitedSitesBridge = this.d;
        long j = mostVisitedSitesBridge.a;
        if (j == 0) {
            return;
        }
        int i = w93Var.b;
        int i2 = w93Var.c;
        ao2 ao2Var = w93Var.a;
        N.M6eoyhxR(j, mostVisitedSitesBridge, i, i2, ao2Var.c, ao2Var.d);
    }
}
