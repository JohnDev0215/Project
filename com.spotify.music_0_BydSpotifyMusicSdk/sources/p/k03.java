package p;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class k03 implements i13 {
    public final /* synthetic */ int c;
    public final i13 e;
    public final i13 k;
    public final i13 l;
    public final i13 m;
    public final i13 n;
    public final i13 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i13 f141p;
    public final i13 q;
    public final i13 r;

    public /* synthetic */ k03(i13 i13Var, i13 i13Var2, i13 i13Var3, i13 i13Var4, i13 i13Var5, i13 i13Var6, i13 i13Var7, i13 i13Var8, i13 i13Var9, int i) {
        this.c = i;
        this.e = i13Var;
        this.k = i13Var2;
        this.l = i13Var3;
        this.m = i13Var4;
        this.n = i13Var5;
        this.o = i13Var6;
        this.f141p = i13Var7;
        this.q = i13Var8;
        this.r = i13Var9;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.c;
        i13 i13Var = this.r;
        i13 i13Var2 = this.q;
        i13 i13Var3 = this.f141p;
        i13 i13Var4 = this.o;
        i13 i13Var5 = this.n;
        i13 i13Var6 = this.m;
        i13 i13Var7 = this.l;
        i13 i13Var8 = this.k;
        i13 i13Var9 = this.e;
        switch (i) {
            case 0:
                Context contextB = ((w23) i13Var9).b();
                Object objA = i13Var8.a();
                Object objA2 = i13Var7.a();
                f13 f13VarB = h13.b(i13Var6);
                Object objA3 = i13Var5.a();
                return new com.google.android.play.core.assetpacks.b(contextB, (com.google.android.play.core.assetpacks.j) objA, (com.google.android.play.core.assetpacks.h) objA2, f13VarB, (g13) objA3, (s03) i13Var4.a(), h13.b(i13Var3), h13.b(i13Var2), (a23) i13Var.a());
            default:
                Object objA4 = i13Var9.a();
                return new com.google.android.play.core.assetpacks.h((com.google.android.play.core.assetpacks.j) objA4, h13.b(i13Var8), (com.google.android.play.core.assetpacks.g) i13Var7.a(), (com.google.android.play.core.assetpacks.r) i13Var6.a(), (com.google.android.play.core.assetpacks.l) i13Var5.a(), (com.google.android.play.core.assetpacks.m) i13Var4.a(), (com.google.android.play.core.assetpacks.n) i13Var3.a(), (com.google.android.play.core.assetpacks.o) i13Var2.a(), (com.google.android.play.core.assetpacks.k) i13Var.a());
        }
    }
}
