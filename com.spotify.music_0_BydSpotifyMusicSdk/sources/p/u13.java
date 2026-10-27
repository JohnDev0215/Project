package p;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class u13 implements i13 {
    public final /* synthetic */ int c;
    public final i13 e;
    public final i13 k;
    public final i13 l;
    public final i13 m;
    public final i13 n;
    public final i13 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i13 f194p;

    public /* synthetic */ u13(i13 i13Var, w03 w03Var, i13 i13Var2, i13 i13Var3, i13 i13Var4, i13 i13Var5, i13 i13Var6, int i) {
        this.c = i;
        this.e = i13Var;
        this.k = w03Var;
        this.l = i13Var2;
        this.m = i13Var3;
        this.n = i13Var4;
        this.o = i13Var5;
        this.f194p = i13Var6;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.c;
        i13 i13Var = this.f194p;
        i13 i13Var2 = this.o;
        i13 i13Var3 = this.n;
        i13 i13Var4 = this.m;
        i13 i13Var5 = this.l;
        i13 i13Var6 = this.k;
        i13 i13Var7 = this.e;
        switch (i) {
            case 0:
                String str = (String) i13Var7.a();
                Object objA = i13Var6.a();
                Object objA2 = i13Var5.a();
                Context contextB = ((w23) i13Var4).b();
                Object objA3 = i13Var3.a();
                f13 f13VarB = h13.b(i13Var2);
                com.google.android.play.core.assetpacks.b bVar = (com.google.android.play.core.assetpacks.b) objA;
                b23 b23Var = (b23) objA3;
                return new t13(str != null ? new File(contextB.getExternalFilesDir(null), str) : contextB.getExternalFilesDir(null), bVar, contextB, b23Var, f13VarB);
            default:
                Object objA4 = i13Var7.a();
                return new com.google.android.play.core.assetpacks.m((com.google.android.play.core.assetpacks.c) objA4, h13.b(i13Var6), (com.google.android.play.core.assetpacks.j) i13Var5.a(), h13.b(i13Var4), (g13) i13Var3.a(), (pz2) i13Var2.a(), (a23) i13Var.a());
        }
    }
}
