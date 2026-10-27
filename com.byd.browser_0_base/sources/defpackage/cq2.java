package defpackage;

import android.content.res.Resources;
import android.view.View;
import java.util.Objects;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.feed.e;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.ntp.h;

/* JADX INFO: compiled from: SnapScrollHelperImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cq2 implements bq2 {
    public final sm1 a;
    public final NewTabPageLayout b;
    public final em1 d;
    public final int e;
    public final int f;
    public View g;
    public boolean h;
    public int i = -1;
    public final a c = new a();

    /* JADX INFO: compiled from: SnapScrollHelperImpl.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            cq2 cq2Var = cq2.this;
            cq2Var.h = false;
            ((e) cq2Var.b.getScrollDelegate()).j();
        }
    }

    public cq2(h.b bVar, NewTabPageLayout newTabPageLayout) {
        this.a = bVar;
        this.b = newTabPageLayout;
        Objects.requireNonNull(newTabPageLayout);
        this.d = new em1(newTabPageLayout, 1);
        Resources resources = newTabPageLayout.getResources();
        this.e = resources.getDimensionPixelSize(R$dimen.toolbar_progress_bar_height) + resources.getDimensionPixelSize(R$dimen.toolbar_height_no_shadow);
        this.f = resources.getDimensionPixelSize(R$dimen.ntp_search_box_transition_length);
    }
}
