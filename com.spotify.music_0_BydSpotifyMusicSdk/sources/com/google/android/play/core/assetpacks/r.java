package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import p.g23;
import p.he1;
import p.tz2;

/* JADX INFO: loaded from: classes.dex */
public final class r {
    public static final tz2 b = new tz2("VerifySliceTaskHandler", 0);
    public final c a;

    public r(c cVar) {
        this.a = cVar;
    }

    public final void a(g23 g23Var) {
        String str = g23Var.b;
        File fileJ = this.a.j(g23Var.b, g23Var.c, g23Var.d, g23Var.e);
        boolean zExists = fileJ.exists();
        String str2 = g23Var.e;
        int i = g23Var.a;
        if (!zExists) {
            throw new zzck(String.format("Cannot find unverified files for slice %s.", str2), i);
        }
        try {
            c cVar = this.a;
            int i2 = g23Var.c;
            long j = g23Var.d;
            cVar.getClass();
            File file = new File(new File(new File(cVar.c(i2, j, str), "_slices"), "_metadata"), str2);
            if (!file.exists()) {
                throw new zzck(String.format("Cannot find metadata files for slice %s.", str2), i);
            }
            try {
                if (!he1.b0(q.a(fileJ, file)).equals(g23Var.f)) {
                    throw new zzck(String.format("Verification failed for slice %s.", str2), i);
                }
                b.e("Verification of slice %s of pack %s successful.", str2, str);
                File fileK = this.a.k(g23Var.b, g23Var.c, g23Var.d, g23Var.e);
                if (!fileK.exists()) {
                    fileK.mkdirs();
                }
                if (!fileJ.renameTo(fileK)) {
                    throw new zzck(String.format("Failed to move slice %s after verification.", str2), i);
                }
            } catch (IOException e) {
                throw new zzck(String.format("Could not digest file during verification for slice %s.", str2), e, i);
            } catch (NoSuchAlgorithmException e2) {
                throw new zzck("SHA256 algorithm not supported.", e2, i);
            }
        } catch (IOException e3) {
            throw new zzck(String.format("Could not reconstruct slice archive during verification for slice %s.", str2), e3, i);
        }
    }
}
