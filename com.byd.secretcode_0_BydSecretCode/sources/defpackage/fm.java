package defpackage;

import android.os.Looper;

/* JADX INFO: compiled from: MainThreadSupport.java */
/* JADX INFO: loaded from: classes.dex */
public interface fm {

    /* JADX INFO: compiled from: MainThreadSupport.java */
    public static class a implements fm {
        public final Looper a;

        public a(Looper looper) {
            this.a = looper;
        }

        @Override // defpackage.fm
        public jm a(bm bmVar) {
            return new dm(bmVar, this.a, 10);
        }

        @Override // defpackage.fm
        public boolean b() {
            return this.a == Looper.myLooper();
        }
    }

    jm a(bm bmVar);

    boolean b();
}
