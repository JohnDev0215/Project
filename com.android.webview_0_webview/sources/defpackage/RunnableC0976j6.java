package defpackage;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: renamed from: j6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0976j6 implements Runnable {
    public final /* synthetic */ StaggeredGridLayoutManager s;

    public RunnableC0976j6(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.s = staggeredGridLayoutManager;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.d0();
    }
}
