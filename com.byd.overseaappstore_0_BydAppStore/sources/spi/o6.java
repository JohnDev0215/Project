package spi;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import spi.g6;
import spi.j6;
import spi.k6;

/* JADX INFO: loaded from: classes.dex */
public final class o6 implements g6 {
    public final File b;
    public final long c;
    public k6 e;
    public final j6 d = new j6();
    public final wn a = new wn();

    @Deprecated
    public o6(File file, long j) {
        this.b = file;
        this.c = j;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.HashMap, java.util.Map<java.lang.String, spi.j6$a>] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.util.ArrayDeque, java.util.Queue<spi.j6$a>] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.util.HashMap, java.util.Map<java.lang.String, spi.j6$a>] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // spi.g6
    public final void a(ig igVar, g6.b bVar) {
        j6.a aVar;
        boolean z;
        String strA = this.a.a(igVar);
        j6 j6Var = this.d;
        synchronized (j6Var) {
            aVar = (j6.a) j6Var.a.get(strA);
            if (aVar == null) {
                j6.b bVar2 = j6Var.b;
                synchronized (bVar2.a) {
                    aVar = (j6.a) bVar2.a.poll();
                }
                if (aVar == null) {
                    aVar = new j6.a();
                }
                j6Var.a.put(strA, aVar);
            }
            aVar.b++;
        }
        aVar.a.lock();
        try {
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                Log.v("DiskLruCacheWrapper", "Put: Obtained: " + strA + " for for Key: " + igVar);
            }
            try {
                k6 k6VarC = c();
                if (k6VarC.G(strA) == null) {
                    k6.c cVarE = k6VarC.E(strA);
                    if (cVarE == null) {
                        throw new IllegalStateException("Had two simultaneous puts for: " + strA);
                    }
                    try {
                        l5 l5Var = (l5) bVar;
                        if (l5Var.a.b(l5Var.b, cVarE.b(), l5Var.c)) {
                            k6.e(k6.this, cVarE, true);
                            cVarE.c = true;
                        }
                        if (!z) {
                            try {
                                cVarE.a();
                            } catch (IOException unused) {
                            }
                        }
                    } finally {
                        if (!cVarE.c) {
                            try {
                                cVarE.a();
                            } catch (IOException unused2) {
                            }
                        }
                    }
                }
            } catch (IOException e) {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e);
                }
            }
        } finally {
            this.d.a(strA);
        }
    }

    @Override // spi.g6
    public final File b(ig igVar) {
        String strA = this.a.a(igVar);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            Log.v("DiskLruCacheWrapper", "Get: Obtained: " + strA + " for for Key: " + igVar);
        }
        try {
            k6.e eVarG = c().G(strA);
            if (eVarG != null) {
                return eVarG.a[0];
            }
            return null;
        } catch (IOException e) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e);
            return null;
        }
    }

    public final synchronized k6 c() throws IOException {
        if (this.e == null) {
            this.e = k6.I(this.b, this.c);
        }
        return this.e;
    }
}
