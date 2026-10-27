package defpackage;

import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: renamed from: d6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0642d6 implements Runnable {
    public int s;
    public int t;
    public OverScroller u;
    public Interpolator v = RecyclerView.O0;
    public boolean w = false;
    public boolean x = false;
    public final /* synthetic */ RecyclerView y;

    public RunnableC0642d6(RecyclerView recyclerView) {
        this.y = recyclerView;
        this.u = new OverScroller(recyclerView.getContext(), RecyclerView.O0);
    }

    public void a() {
        if (this.w) {
            this.x = true;
        } else {
            this.y.removeCallbacks(this);
            AbstractC1251o3.z(this.y, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00eb  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.RunnableC0642d6.run():void");
    }
}
