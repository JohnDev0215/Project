package j7;

import android.os.Looper;

/* JADX INFO: compiled from: MainThreadSupport.java */
/* JADX INFO: loaded from: classes2.dex */
public interface g {

    /* JADX INFO: compiled from: MainThreadSupport.java */
    public static class a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Looper f5653a;

        public a(Looper looper) {
            this.f5653a = looper;
        }

        @Override // j7.g
        public k a(c cVar) {
            return new e(cVar, this.f5653a, 10);
        }

        @Override // j7.g
        public boolean b() {
            return this.f5653a == Looper.myLooper();
        }
    }

    k a(c cVar);

    boolean b();
}
