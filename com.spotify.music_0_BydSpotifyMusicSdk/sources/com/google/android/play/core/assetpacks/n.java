package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import p.d23;
import p.d33;
import p.e13;
import p.f13;
import p.h13;
import p.he1;
import p.tz2;

/* JADX INFO: loaded from: classes.dex */
public final class n {
    public static final tz2 c = new tz2("PatchSliceTaskHandler", 0);
    public final c a;
    public final f13 b;

    public n(c cVar, f13 f13Var) {
        this.a = cVar;
        this.b = f13Var;
    }

    public final void a(d23 d23Var) throws Throwable {
        tz2 tz2Var;
        tz2 tz2Var2 = c;
        int i = d23Var.a;
        c cVar = this.a;
        int i2 = d23Var.c;
        long j = d23Var.d;
        String str = d23Var.b;
        File fileI = cVar.i(i2, j, str);
        File file = new File(cVar.i(i2, j, str), "_metadata");
        String str2 = d23Var.h;
        File file2 = new File(file, str2);
        try {
            int i3 = d23Var.g;
            InputStream inputStream = d23Var.j;
            InputStream gZIPInputStream = i3 != 2 ? inputStream : new GZIPInputStream(inputStream, 8192);
            try {
                d dVar = new d(fileI, file2);
                File fileJ = this.a.j(d23Var.b, d23Var.e, d23Var.f, d23Var.h);
                if (!fileJ.exists()) {
                    fileJ.mkdirs();
                }
                try {
                    p pVar = new p(this.a, d23Var.b, d23Var.e, d23Var.f, d23Var.h);
                    he1.c0(dVar, gZIPInputStream, new e13(fileJ, pVar), d23Var.i);
                    pVar.h(0);
                    try {
                        gZIPInputStream.close();
                        tz2Var2.e("Patching and extraction finished for slice %s of pack %s.", str2, str);
                        ((d33) ((h13) this.b).a()).g(i, str, 0, str2);
                        try {
                            inputStream.close();
                        } catch (IOException unused) {
                            tz2Var2.f("Could not close file for slice %s of pack %s.", str2, str);
                        }
                    } catch (IOException e) {
                        e = e;
                        tz2Var = tz2Var2;
                        tz2Var.c("IOException during patching %s.", e.getMessage());
                        throw new zzck(String.format("Error patching slice %s of pack %s.", str2, str), e, i);
                    }
                } catch (Throwable th) {
                    th = th;
                    tz2Var = tz2Var2;
                    try {
                        gZIPInputStream.close();
                    } catch (Throwable unused2) {
                    }
                    try {
                        throw th;
                    } catch (IOException e2) {
                        e = e2;
                        tz2Var.c("IOException during patching %s.", e.getMessage());
                        throw new zzck(String.format("Error patching slice %s of pack %s.", str2, str), e, i);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                tz2Var = tz2Var2;
            }
        } catch (IOException e3) {
            e = e3;
            tz2Var = tz2Var2;
        }
    }
}
