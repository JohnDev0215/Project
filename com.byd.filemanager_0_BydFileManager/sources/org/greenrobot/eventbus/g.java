package org.greenrobot.eventbus;

import android.os.Looper;

/* JADX INFO: compiled from: MainThreadSupport.java */
/* JADX INFO: loaded from: classes.dex */
public interface g {

    /* JADX INFO: compiled from: MainThreadSupport.java */
    public static class a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Looper f4696a;

        public a(Looper looper) {
            this.f4696a = looper;
        }

        @Override // org.greenrobot.eventbus.g
        public k a(c cVar) {
            return new e(cVar, this.f4696a, 10);
        }

        @Override // org.greenrobot.eventbus.g
        public boolean b() {
            return this.f4696a == Looper.myLooper();
        }
    }

    k a(c cVar);

    boolean b();
}
