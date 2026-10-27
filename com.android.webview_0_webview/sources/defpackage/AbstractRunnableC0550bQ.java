package defpackage;

import org.chromium.weblayer_private.ContentViewRenderView;

/* JADX INFO: renamed from: bQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractRunnableC0550bQ implements Runnable {
    public boolean s;
    public final /* synthetic */ ContentViewRenderView t;

    public AbstractRunnableC0550bQ(ContentViewRenderView contentViewRenderView) {
        this.t = contentViewRenderView;
        contentViewRenderView.C.add(this);
    }

    public abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        if (this.s) {
            return;
        }
        this.t.C.remove(this);
        this.s = true;
        a();
    }
}
