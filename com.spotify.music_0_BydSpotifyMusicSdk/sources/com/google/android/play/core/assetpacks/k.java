package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import p.n13;
import p.o13;
import p.p13;
import p.pz2;
import p.tz2;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static final tz2 e = new tz2("ExtractorTaskFinder", 0);
    public final j a;
    public final c b;
    public final e c;
    public final pz2 d;

    public k(j jVar, c cVar, e eVar, pz2 pz2Var) {
        this.a = jVar;
        this.b = cVar;
        this.c = eVar;
        this.d = pz2Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:142:0x0389, code lost:
    
        r0 = new java.lang.Object[4];
        r0[0] = java.lang.Integer.valueOf(r6.f);
        r0[1] = java.lang.Integer.valueOf(r3);
        r4 = r2.a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x039e, code lost:
    
        r10 = r2.a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x03a1, code lost:
    
        r0[2] = r4;
        r0[3] = r8;
        r9.b("Found patch slice task using patch format %s for session %s, pack %s, slice %s.", r0);
        r42 = r5.a(r3, r10, 0, r8);
        r3 = r1.a;
        r4 = r2.a;
        r28.getClass();
        r0 = new p.d23(r3, r4, (int) com.google.android.play.core.assetpacks.c.b(new java.io.File(r28.d(), r4), true), com.google.android.play.core.assetpacks.c.b(new java.io.File(new java.io.File(r28.d(), r10), java.lang.String.valueOf((int) com.google.android.play.core.assetpacks.c.b(new java.io.File(r28.d(), r10), true))), true), r1.b, r2.b, r6.f, r6.a, r6.c, r42);
     */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.r13 a() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1078
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.k.a():p.r13");
    }

    public final boolean b(o13 o13Var, p13 p13Var) {
        n13 n13Var = o13Var.c;
        String str = n13Var.a;
        String str2 = p13Var.a;
        tz2 tz2Var = p.h;
        c cVar = this.b;
        cVar.getClass();
        File file = new File(new File(new File(new File(cVar.c(o13Var.b, n13Var.b, str), "_slices"), "_metadata"), str2), "checkpoint.dat");
        if (!file.exists()) {
            return false;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("fileStatus") != null) {
                    return Integer.parseInt(properties.getProperty("fileStatus")) == 4;
                }
                tz2Var.c("Slice checkpoint file corrupt while checking if extraction finished.", new Object[0]);
                return false;
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (IOException e2) {
            tz2Var.c("Could not read checkpoint while checking if extraction finished. %s", e2);
            return false;
        }
    }
}
