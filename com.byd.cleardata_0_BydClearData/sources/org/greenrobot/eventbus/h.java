package org.greenrobot.eventbus;

import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public interface h {

    public static class a implements h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Looper f3307a;

        public a(Looper looper) {
            this.f3307a = looper;
        }

        @Override // org.greenrobot.eventbus.h
        public l a(c cVar) {
            return new f(cVar, this.f3307a, 10);
        }

        @Override // org.greenrobot.eventbus.h
        public boolean b() {
            return this.f3307a == Looper.myLooper();
        }
    }

    l a(c cVar);

    boolean b();
}
