package defpackage;

import java.util.HashSet;

/* JADX INFO: compiled from: TokenHolder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mb3 {
    public int a;
    public final HashSet b = new HashSet();
    public final Runnable c;

    public mb3(Runnable runnable) {
        this.c = runnable;
    }

    public final int a() {
        int i = this.a;
        this.a = i + 1;
        HashSet hashSet = this.b;
        hashSet.add(Integer.valueOf(i));
        if (hashSet.size() == 1) {
            this.c.run();
        }
        return i;
    }

    public final boolean b() {
        return !this.b.isEmpty();
    }

    public final void c(int i) {
        HashSet hashSet = this.b;
        if (hashSet.remove(Integer.valueOf(i)) && hashSet.isEmpty()) {
            this.c.run();
        }
    }
}
