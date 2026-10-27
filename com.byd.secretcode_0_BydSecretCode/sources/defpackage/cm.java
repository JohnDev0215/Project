package defpackage;

import android.os.Looper;
import defpackage.em;
import defpackage.fm;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: EventBusBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class cm {
    public static final ExecutorService a = Executors.newCachedThreadPool();
    public boolean f;
    public boolean h;
    public boolean i;
    public List<rm> k;
    public em l;
    public fm m;
    public boolean b = true;
    public boolean c = true;
    public boolean d = true;
    public boolean e = true;
    public boolean g = true;
    public ExecutorService j = a;

    public static Object a() {
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public em b() {
        em emVar = this.l;
        return emVar != null ? emVar : em.a.a();
    }

    public fm c() {
        Object objA;
        fm fmVar = this.m;
        if (fmVar != null) {
            return fmVar;
        }
        if (!pm.c() || (objA = a()) == null) {
            return null;
        }
        return new fm.a((Looper) objA);
    }
}
