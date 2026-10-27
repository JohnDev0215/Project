package org.chromium.chrome.browser.feedback;

import J.N;
import android.os.SystemClock;
import android.util.Log;
import defpackage.gq2;
import defpackage.rh3;
import defpackage.yl1;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.feedback.ConnectivityChecker;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: ConnectivityTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public final a b;
    public final HashMap a = new HashMap();
    public final long c = SystemClock.elapsedRealtime();

    /* JADX INFO: compiled from: ConnectivityTask.java */
    public interface a {
    }

    /* JADX INFO: compiled from: ConnectivityTask.java */
    public static final class b {
        public final Map<Integer, Integer> a;
        public final long b;
        public final int c;

        public b(HashMap map, long j, int i) {
            this.a = map;
            this.b = j;
            this.c = i;
        }
    }

    /* JADX INFO: compiled from: ConnectivityTask.java */
    public class c implements ConnectivityChecker.a {
        public final int a;

        public c(int i) {
            this.a = i;
        }

        @Override // org.chromium.chrome.browser.feedback.ConnectivityChecker.a
        public final void a(int i) {
            Object obj = ThreadUtils.a;
            int i2 = this.a;
            d.a(i2);
            d.c(i);
            d dVar = d.this;
            dVar.a.put(Integer.valueOf(i2), Integer.valueOf(i));
            if (!(dVar.a.size() == 4) || dVar.b == null) {
                return;
            }
            PostTask.c(rh3.a, new e(this));
        }
    }

    public d(Profile profile, a aVar) {
        this.b = aVar;
        for (int i = 0; i < 4; i++) {
            c cVar = new c(i);
            if (i == 0) {
                yl1 yl1Var = ConnectivityChecker.a;
                Object obj = ThreadUtils.a;
                N.MvuVYy8Q(profile, "http://clients4.google.com/generate_204", 5000, cVar, ConnectivityChecker.a.a);
            } else if (i == 1) {
                yl1 yl1Var2 = ConnectivityChecker.a;
                Object obj2 = ThreadUtils.a;
                N.MvuVYy8Q(profile, "https://clients4.google.com/generate_204", 5000, cVar, ConnectivityChecker.a.a);
            } else if (i == 2) {
                ConnectivityChecker.a(false, cVar);
            } else if (i != 3) {
                Log.e("cr_feedback", "Failed to recognize type " + i);
            } else {
                ConnectivityChecker.a(true, cVar);
            }
        }
    }

    public static String a(int i) {
        if (i == 0) {
            return "HTTP connection check (Chrome network stack)";
        }
        if (i == 1) {
            return "HTTPS connection check (Chrome network stack)";
        }
        if (i == 2) {
            return "HTTP connection check (Android network stack)";
        }
        if (i == 3) {
            return "HTTPS connection check (Android network stack)";
        }
        throw new IllegalArgumentException(gq2.e("Unknown connection type: ", i));
    }

    public static String c(int i) {
        if (i == 0) {
            return "UNKNOWN";
        }
        if (i == 1) {
            return "CONNECTED";
        }
        if (i == 2) {
            return "NOT_CONNECTED";
        }
        if (i == 3) {
            return "TIMEOUT";
        }
        if (i == 4) {
            return "ERROR";
        }
        throw new IllegalArgumentException(gq2.e("Unknown result value: ", i));
    }

    public final b b() {
        Object obj = ThreadUtils.a;
        HashMap map = new HashMap();
        for (int i = 0; i < 4; i++) {
            HashMap map2 = this.a;
            if (map2.containsKey(Integer.valueOf(i))) {
                map.put(Integer.valueOf(i), (Integer) map2.get(Integer.valueOf(i)));
            } else {
                map.put(Integer.valueOf(i), 0);
            }
        }
        return new b(map, SystemClock.elapsedRealtime() - this.c, NetworkChangeNotifier.f.getCurrentConnectionType());
    }
}
