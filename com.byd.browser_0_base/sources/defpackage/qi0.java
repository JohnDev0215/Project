package defpackage;

import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.ViewGroup;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout;
import org.chromium.chrome.browser.feed.c;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.features.start_surface.StartSurfaceConfiguration;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ExploreSurfaceCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qi0 {
    public final Activity a;
    public final c b;
    public final si0 c;
    public final boolean d;
    public long e;
    public boolean f;
    public long g;

    /* JADX INFO: compiled from: ExploreSurfaceCoordinator.java */
    public class a implements ml0 {
        @Override // defpackage.ml0
        public final boolean b(MotionEvent motionEvent) {
            return false;
        }
    }

    /* JADX INFO: compiled from: ExploreSurfaceCoordinator.java */
    public class b extends xk0 {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b() {
            super(qi0.this.c, 2);
            Activity activity = qi0.this.a;
        }

        @Override // defpackage.wk0
        public final void a() {
            qi0 qi0Var = qi0.this;
            if (qi0Var.e == 0) {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                qi0Var.e = jElapsedRealtime;
                if (qi0Var.f) {
                    long j = qi0Var.g;
                    if (j != 0 && jElapsedRealtime != 0) {
                        StartSurfaceConfiguration.b(jElapsedRealtime - j, "FeedContentFirstLoadedTime", qi0Var.d);
                    }
                    qi0Var.f = false;
                }
            }
        }
    }

    public qi0(Profile profile, Activity activity, boolean z, boolean z2, ji jiVar, uf2 uf2Var, int i, nw2 nw2Var, long j, FeedSwipeRefreshLayout feedSwipeRefreshLayout, ViewGroup viewGroup, nw2 nw2Var2, wp2 wp2Var, nw2 nw2Var3, WindowAndroid windowAndroid) {
        this.a = activity;
        this.c = new si0(nw2Var2);
        this.d = z2;
        int dimensionPixelSize = activity.getResources().getDimensionPixelSize(R$dimen.toolbar_height_no_shadow);
        a aVar = new a();
        v42 v42VarG = v42.g();
        BookmarkModel.v(profile);
        c cVar = new c(activity, wp2Var, windowAndroid, null, null, dimensionPixelSize, z, aVar, profile, z2, jiVar, nw2Var3, uf2Var, i, v42VarG, nw2Var, 2, j, feedSwipeRefreshLayout, true, viewGroup, new b(), hu0.a());
        this.b = cVar;
        cVar.q.setId(R$id.start_surface_explore_view);
    }
}
