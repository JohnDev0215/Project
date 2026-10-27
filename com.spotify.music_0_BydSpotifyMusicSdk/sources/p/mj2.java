package p;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class mj2 {
    public final Object c;

    public /* synthetic */ mj2(Object obj) {
        this.c = obj;
    }

    public abstract boolean a();

    public abstract String b(String str);

    public final boolean c(CharSequence charSequence, int i) {
        if (charSequence == null || i < 0 || charSequence.length() - i < 0) {
            throw new IllegalArgumentException();
        }
        lj2 lj2Var = (lj2) this.c;
        if (lj2Var == null) {
            return a();
        }
        int iG = lj2Var.g(charSequence, i);
        if (iG == 0) {
            return true;
        }
        if (iG != 1) {
            return a();
        }
        return false;
    }

    public mj2(int i) {
        if (i != 5) {
            this.c = new com.spotify.automotive.auth.a(0, this);
        } else {
            this.c = new AtomicInteger();
        }
    }
}
