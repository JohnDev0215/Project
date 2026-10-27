package spi;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PendingPost.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g8 {
    public static final List<g8> d = new ArrayList();
    public Object a;
    public pa b;
    public g8 c;

    private g8(Object obj, pa paVar) {
        this.a = obj;
        this.b = paVar;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<spi.g8>] */
    public static g8 a(pa paVar, Object obj) {
        ?? r0 = d;
        synchronized (r0) {
            int size = r0.size();
            if (size <= 0) {
                return new g8(obj, paVar);
            }
            g8 g8Var = (g8) r0.remove(size - 1);
            g8Var.a = obj;
            g8Var.b = paVar;
            g8Var.c = null;
            return g8Var;
        }
    }
}
