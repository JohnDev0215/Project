package com.qtxa.qtxadefend;

import java.util.Timer;

/* JADX INFO: loaded from: classes.dex */
public class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Timer f108a;

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static a0 f109a = new a0();
    }

    public static a0 a() {
        return b.f109a;
    }

    public void b() {
        if (f108a == null) {
            synchronized (a0.class) {
                f108a = new Timer(true);
                q.d();
                long jE = q.d().e();
                w.d("rootcheck begin for start: 1000 cycle: " + jE + "  ");
                f108a.schedule(new r(), 1000L, jE);
            }
        }
    }

    public void c() {
        if (f108a != null) {
            w.d("rootcheck cancel");
            f108a.cancel();
            f108a = null;
        }
    }

    private a0() {
    }
}
