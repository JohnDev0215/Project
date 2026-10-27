package defpackage;

import android.os.PersistableBundle;

/* JADX INFO: compiled from: TaskParameters.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a73 {
    public final int a;
    public final PersistableBundle b;

    /* JADX INFO: compiled from: TaskParameters.java */
    public static final class a {
        public final int a;
        public PersistableBundle b;

        public a(int i) {
            this.a = i;
        }
    }

    public a73(a aVar) {
        this.a = aVar.a;
        PersistableBundle persistableBundle = aVar.b;
        this.b = persistableBundle == null ? new PersistableBundle() : persistableBundle;
    }
}
