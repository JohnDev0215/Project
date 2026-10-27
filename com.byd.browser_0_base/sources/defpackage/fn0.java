package defpackage;

import android.os.SystemClock;
import java.util.LinkedList;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: FirstRunAppRestrictionInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class fn0 {
    public static fn0 g;
    public boolean a;
    public boolean b;
    public long c;
    public final LinkedList d = new LinkedList();
    public final LinkedList e = new LinkedList();
    public en0 f;

    public fn0() {
        Object obj = ThreadUtils.a;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (iv.e().g("policy")) {
            a(jElapsedRealtime, true);
            return;
        }
        try {
            en0 en0Var = new en0(this, xz.a, jElapsedRealtime);
            this.f = en0Var;
            en0Var.e(d73.k);
        } catch (RejectedExecutionException unused) {
            a(jElapsedRealtime, false);
        }
    }

    public final void a(long j, boolean z) {
        this.b = z;
        this.a = true;
        if (j > 0) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.c = jElapsedRealtime;
            long j2 = jElapsedRealtime - j;
            oj2.b0(j2, "Enterprise.FirstRun.AppRestrictionLoadTime");
            oj2.Y(j2, "Enterprise.FirstRun.AppRestrictionLoadTime.Medium");
        }
        while (true) {
            LinkedList linkedList = this.d;
            if (linkedList.isEmpty()) {
                break;
            } else {
                ((Callback) linkedList.remove()).onResult(Boolean.valueOf(this.b));
            }
        }
        while (true) {
            LinkedList linkedList2 = this.e;
            if (linkedList2.isEmpty()) {
                return;
            } else {
                ((Callback) linkedList2.remove()).onResult(Long.valueOf(this.c));
            }
        }
    }
}
