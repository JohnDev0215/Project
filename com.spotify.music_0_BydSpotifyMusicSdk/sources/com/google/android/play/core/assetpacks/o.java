package com.google.android.play.core.assetpacks;

import java.io.File;
import java.util.concurrent.Executor;
import p.d33;
import p.e23;
import p.f13;
import p.g13;
import p.h13;
import p.j13;
import p.yf1;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    public final c a;
    public final f13 b;
    public final j c;
    public final f13 d;
    public final g13 e;

    public o(c cVar, f13 f13Var, j jVar, f13 f13Var2, g13 g13Var) {
        this.a = cVar;
        this.b = f13Var;
        this.c = jVar;
        this.d = f13Var2;
        this.e = g13Var;
    }

    public final void a(e23 e23Var) {
        long j = e23Var.e;
        c cVar = this.a;
        File fileI = cVar.i(e23Var.c, j, e23Var.b);
        boolean zExists = fileI.exists();
        String str = e23Var.b;
        int i = e23Var.a;
        if (!zExists) {
            throw new zzck(String.format("Cannot find pack files to promote for pack %s at %s", str, fileI.getAbsolutePath()), i);
        }
        int i2 = e23Var.d;
        File fileI2 = cVar.i(i2, j, str);
        fileI2.mkdirs();
        if (!fileI.renameTo(fileI2)) {
            throw new zzck(String.format("Cannot promote pack %s from %s to %s", str, fileI.getAbsolutePath(), fileI2.getAbsolutePath()), i);
        }
        ((Executor) ((h13) this.d).a()).execute(new yf1(this, e23Var, 27));
        j jVar = this.c;
        jVar.getClass();
        jVar.c(new j13(jVar, str, i2, j));
        this.e.a(str);
        ((d33) ((h13) this.b).a()).d(i, str);
    }
}
