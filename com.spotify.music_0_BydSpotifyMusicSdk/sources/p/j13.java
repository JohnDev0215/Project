package p;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j13 implements q13 {
    public int c;
    public long e;
    public Object k;
    public final Object l;

    public /* synthetic */ j13(com.google.android.play.core.assetpacks.j jVar, String str, int i, long j) {
        this.k = jVar;
        this.l = str;
        this.c = i;
        this.e = j;
    }

    @Override // p.q13
    public final Object a() {
        int i;
        com.google.android.play.core.assetpacks.j jVar = (com.google.android.play.core.assetpacks.j) this.k;
        String str = (String) this.l;
        int i2 = this.c;
        long j = this.e;
        jVar.getClass();
        o13 o13Var = (o13) ((Map) jVar.c(new x42(jVar, 7, Arrays.asList(str)))).get(str);
        if (o13Var == null || (i = o13Var.c.d) == 5 || i == 6 || i == 4) {
            com.google.android.play.core.assetpacks.j.f.c(String.format("Could not find pack %s while trying to complete it", str), new Object[0]);
        }
        com.google.android.play.core.assetpacks.c cVar = jVar.a;
        if (cVar.c(i2, j, str).exists()) {
            com.google.android.play.core.assetpacks.c.g(cVar.c(i2, j, str));
        }
        o13Var.c.d = 4;
        return null;
    }

    public j13(rl0 rl0Var) {
        rl0Var.getClass();
        this.l = rl0Var;
    }
}
