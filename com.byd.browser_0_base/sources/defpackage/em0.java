package defpackage;

import android.os.SystemClock;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.feedback.ScreenshotTask;

/* JADX INFO: compiled from: FeedbackCollector.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class em0<T> implements Runnable {
    public final long e = SystemClock.elapsedRealtime();
    public List<gm0> f;
    public List<w8> g;
    public ze2 h;
    public Callback<em0> i;

    public em0(Callback callback) {
        this.i = callback;
    }

    public final void a() {
        if (this.i == null) {
            return;
        }
        ze2 ze2Var = this.h;
        if (ze2Var == null || ((ScreenshotTask) ze2Var).b) {
            int size = this.g.size();
            long j = this.e;
            if (size > 0 && SystemClock.elapsedRealtime() - j < 500) {
                Iterator<w8> it = this.g.iterator();
                while (it.hasNext()) {
                    if (!it.next().a()) {
                        return;
                    }
                }
            }
            oj2.Y(SystemClock.elapsedRealtime() - j, "Feedback.Duration.FetchSystemInformation");
            Callback<em0> callback = this.i;
            this.i = null;
            PostTask.c(rh3.a, callback.p1(this));
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        a();
    }
}
