package defpackage;

import J.N;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.feed.FeedStream;

/* JADX INFO: compiled from: ScrollTracker.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class tf2 {
    public a a;
    public boolean b;
    public int c;

    /* JADX INFO: compiled from: ScrollTracker.java */
    public class a implements Runnable {
        public final /* synthetic */ tf2 e;

        public a(FeedStream.f fVar) {
            this.e = fVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            tf2 tf2Var = this.e;
            if (tf2Var.b) {
                tf2Var.a();
                tf2Var.a = null;
            } else if (tf2Var.c != 0) {
                tf2Var.b = true;
                PostTask.b(rh3.a, tf2Var.a, 200L);
            }
        }
    }

    public final void a() {
        int i = this.c;
        if (i != 0) {
            FeedStream feedStream = FeedStream.this;
            N.M9DpFDeH(feedStream.c, feedStream, i);
            this.c = 0;
        }
        this.b = false;
    }
}
