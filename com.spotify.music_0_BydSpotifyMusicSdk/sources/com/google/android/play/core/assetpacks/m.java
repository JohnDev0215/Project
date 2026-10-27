package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import p.a23;
import p.d33;
import p.f13;
import p.g13;
import p.h13;
import p.j13;
import p.pz2;
import p.rv;
import p.y13;
import p.yf1;

/* JADX INFO: loaded from: classes.dex */
public final class m {
    public final c a;
    public final f13 b;
    public final j c;
    public final f13 d;
    public final g13 e;
    public final pz2 f;
    public final a23 g;

    public m(c cVar, f13 f13Var, j jVar, f13 f13Var2, g13 g13Var, pz2 pz2Var, a23 a23Var) {
        this.a = cVar;
        this.b = f13Var;
        this.c = jVar;
        this.d = f13Var2;
        this.e = g13Var;
        this.f = pz2Var;
        this.g = a23Var;
    }

    public final void a(y13 y13Var) {
        int i = y13Var.c;
        long j = y13Var.d;
        c cVar = this.a;
        cVar.getClass();
        File file = new File(cVar.c(i, j, y13Var.b), "_packs");
        String str = y13Var.b;
        File file2 = new File(new File(cVar.c(i, j, str), "_slices"), "_metadata");
        boolean zExists = file.exists();
        int i2 = y13Var.a;
        if (!zExists || !file2.exists()) {
            throw new zzck(String.format("Cannot find pack files to move for pack %s.", str), i2);
        }
        File fileI = cVar.i(i, j, str);
        fileI.mkdirs();
        if (!file.renameTo(fileI)) {
            throw new zzck("Cannot move merged pack files to final location.", i2);
        }
        new File(cVar.i(i, j, str), "merge.tmp").delete();
        File file3 = new File(cVar.i(i, j, str), "_metadata");
        file3.mkdirs();
        if (!file2.renameTo(file3)) {
            throw new zzck("Cannot move metadata files to final location.", i2);
        }
        boolean zA = this.f.a();
        f13 f13Var = this.d;
        if (zA) {
            try {
                this.g.b(y13Var.b, y13Var.c, y13Var.d, y13Var.e);
                ((Executor) ((h13) f13Var).a()).execute(new yf1(this, y13Var, 26));
            } catch (IOException e) {
                throw new zzck(String.format("Could not write asset pack version tag for pack %s: %s", str, e.getMessage()), i2);
            }
        } else {
            ((Executor) ((h13) f13Var).a()).execute(new rv(14, cVar));
        }
        j jVar = this.c;
        jVar.getClass();
        jVar.c(new j13(jVar, str, i, j));
        this.e.a(str);
        ((d33) ((h13) this.b).a()).d(i2, str);
    }
}
