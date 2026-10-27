package p;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class oz1 implements fu0 {
    public static final Charset l = Charset.forName("UTF-8");
    public final File c;
    public final int e = 65536;
    public nz1 k;

    public oz1(File file) {
        this.c = file;
    }

    public final void a() {
        File file = this.c;
        if (this.k == null) {
            try {
                this.k = new nz1(file);
            } catch (IOException e) {
                Log.e("FirebaseCrashlytics", "Could not open log file: " + file, e);
            }
        }
    }

    @Override // p.fu0
    public final void b() {
        zu.q(this.k, "There was a problem closing the Crashlytics log file.");
        this.k = null;
    }

    @Override // p.fu0
    public final String c() {
        byte[] bArrD = d();
        if (bArrD != null) {
            return new String(bArrD, l);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x000a  */
    @Override // p.fu0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] d() {
        /*
            r7 = this;
            java.io.File r0 = r7.c
            boolean r0 = r0.exists()
            r1 = 0
            r2 = 0
            if (r0 != 0) goto Lc
        La:
            r4 = r1
            goto L38
        Lc:
            r7.a()
            p.nz1 r0 = r7.k
            if (r0 != 0) goto L14
            goto La
        L14:
            int[] r3 = new int[]{r2}
            int r0 = r0.E0()
            byte[] r0 = new byte[r0]
            p.nz1 r4 = r7.k     // Catch: java.io.IOException -> L29
            p.v4 r5 = new p.v4     // Catch: java.io.IOException -> L29
            r5.<init>(r7, r0, r3)     // Catch: java.io.IOException -> L29
            r4.i(r5)     // Catch: java.io.IOException -> L29
            goto L31
        L29:
            r4 = move-exception
            java.lang.String r5 = "FirebaseCrashlytics"
            java.lang.String r6 = "A problem occurred while reading the Crashlytics log file."
            android.util.Log.e(r5, r6, r4)
        L31:
            p.j6 r4 = new p.j6
            r3 = r3[r2]
            r4.<init>(r3, r0)
        L38:
            if (r4 != 0) goto L3b
            return r1
        L3b:
            int r0 = r4.c
            byte[] r1 = new byte[r0]
            java.lang.Object r3 = r4.e
            byte[] r3 = (byte[]) r3
            java.lang.System.arraycopy(r3, r2, r1, r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.oz1.d():byte[]");
    }

    @Override // p.fu0
    public final void e() {
        b();
        this.c.delete();
    }

    @Override // p.fu0
    public final void f(String str, long j) {
        boolean z;
        a();
        int i = this.e;
        if (this.k == null) {
            return;
        }
        if (str == null) {
            str = "null";
        }
        try {
            int i2 = i / 4;
            if (str.length() > i2) {
                str = "..." + str.substring(str.length() - i2);
            }
            this.k.b(String.format(Locale.US, "%d %s%n", Long.valueOf(j), str.replaceAll("\r", " ").replaceAll("\n", " ")).getBytes(l));
            while (true) {
                nz1 nz1Var = this.k;
                synchronized (nz1Var) {
                    z = nz1Var.k == 0;
                }
                if (z || this.k.E0() <= i) {
                    return;
                } else {
                    this.k.J();
                }
            }
        } catch (IOException e) {
            Log.e("FirebaseCrashlytics", "There was a problem writing to the Crashlytics log.", e);
        }
    }
}
