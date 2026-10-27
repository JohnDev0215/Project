package E7;

import F0.C0204g0;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import h6.q;
import io.sentry.util.g;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class b extends ThreadLocal {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1846a;

    public /* synthetic */ b(int i7) {
        this.f1846a = i7;
    }

    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        switch (this.f1846a) {
            case 0:
                return new Random();
            case 1:
                Choreographer choreographer = Choreographer.getInstance();
                Looper looperMyLooper = Looper.myLooper();
                if (looperMyLooper == null) {
                    throw new IllegalStateException("no Looper on this thread");
                }
                C0204g0 c0204g0 = new C0204g0(choreographer, Handler.createAsync(looperMyLooper));
                return q.x(c0204g0, c0204g0.f2204w);
            case 2:
                return new PathMeasure();
            case 3:
                return new Path();
            case 4:
                return new Path();
            case 5:
                return new float[4];
            case 6:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                simpleDateFormat.setLenient(false);
                simpleDateFormat.setTimeZone(d9.b.f13648f);
                return simpleDateFormat;
            default:
                return new g();
        }
    }
}
