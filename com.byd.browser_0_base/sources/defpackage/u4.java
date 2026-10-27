package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import defpackage.v4;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: AndroidFontLookupImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class u4 implements s4 {
    public final Context e;
    public final yo0 f;
    public final Map<String, String> g;
    public final HashSet h;
    public final HashMap i;
    public final oi2 j;

    /* JADX INFO: compiled from: AndroidFontLookupImpl.java */
    public static class a implements a21<s4> {

        @SuppressLint({"StaticFieldLeak"})
        public static u4 e;

        @Override // defpackage.a21
        public final y11 d() {
            if (e == null) {
                e = new u4(xz.a);
            }
            return e;
        }
    }

    public u4(Context context) {
        yo0 yo0Var = new yo0();
        HashMap map = new HashMap();
        map.put("google sans regular", t(400, "Google Sans"));
        map.put("google sans medium", t(500, "Google Sans"));
        map.put("google sans bold", t(700, "Google Sans"));
        map.put("noto color emoji compat", t(400, "Noto Color Emoji Compat"));
        this.i = new HashMap();
        this.j = PostTask.a(d73.k);
        this.e = context;
        this.f = yo0Var;
        this.g = map;
        this.h = new HashSet(map.keySet());
    }

    public static String t(int i, String str) {
        return String.format(Locale.US, "name=%s&weight=%d&besteffort=false", str, Integer.valueOf(i));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0050 A[Catch: NameNotFoundException | IOException | OutOfMemoryError | RuntimeException -> 0x0075, TryCatch #0 {NameNotFoundException | IOException | OutOfMemoryError | RuntimeException -> 0x0075, blocks: (B:14:0x0034, B:17:0x0050, B:20:0x0057, B:23:0x005e, B:26:0x006d), top: B:36:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.c92 C(java.lang.String r12, defpackage.k00 r13) {
        /*
            r11 = this;
            android.content.Context r0 = r11.e
            java.util.HashMap r1 = r11.i
            java.lang.Object r2 = r1.get(r12)
            android.os.ParcelFileDescriptor r2 = (android.os.ParcelFileDescriptor) r2
            java.util.HashSet r3 = r11.h
            r4 = 0
            r5 = 0
            if (r2 == 0) goto L1b
            android.os.ParcelFileDescriptor r11 = r2.dup()     // Catch: java.io.IOException -> L15
            goto L7a
        L15:
            defpackage.a8.s(r2)
            r1.remove(r12)
        L1b:
            java.util.Map<java.lang.String, java.lang.String> r2 = r11.g
            java.lang.Object r2 = r2.get(r12)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L26
            goto L79
        L26:
            boolean r6 = r3.contains(r12)
            if (r6 != 0) goto L2d
            goto L79
        L2d:
            po0 r6 = new po0
            int r7 = org.chromium.content.R$array.ui_com_google_android_gms_fonts_certs
            r6.<init>(r7, r2)
            long r7 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L75
            yo0 r11 = r11.f     // Catch: java.lang.Throwable -> L75
            r11.getClass()     // Catch: java.lang.Throwable -> L75
            wo0 r11 = defpackage.oo0.a(r0, r6)     // Catch: java.lang.Throwable -> L75
            java.lang.String r2 = "Android.FontLookup.GmsFontRequest.Time"
            long r9 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L75
            long r9 = r9 - r7
            defpackage.oj2.b0(r9, r2)     // Catch: java.lang.Throwable -> L75
            int r2 = r11.a     // Catch: java.lang.Throwable -> L75
            if (r2 == 0) goto L50
            goto L79
        L50:
            xo0[] r11 = r11.b     // Catch: java.lang.Throwable -> L75
            int r2 = r11.length     // Catch: java.lang.Throwable -> L75
            r6 = 1
            if (r2 == r6) goto L57
            goto L79
        L57:
            r11 = r11[r4]     // Catch: java.lang.Throwable -> L75
            int r2 = r11.e     // Catch: java.lang.Throwable -> L75
            if (r2 == 0) goto L5e
            goto L79
        L5e:
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L75
            android.net.Uri r11 = r11.a     // Catch: java.lang.Throwable -> L75
            java.lang.String r2 = "r"
            android.os.ParcelFileDescriptor r11 = r0.openFileDescriptor(r11, r2)     // Catch: java.lang.Throwable -> L75
            if (r11 != 0) goto L6d
            goto L79
        L6d:
            android.os.ParcelFileDescriptor r0 = r11.dup()     // Catch: java.lang.Throwable -> L75
            r1.put(r12, r0)     // Catch: java.lang.Throwable -> L75
            goto L7a
        L75:
            r11 = move-exception
            r11.toString()
        L79:
            r11 = r5
        L7a:
            if (r11 != 0) goto L80
            r3.remove(r12)
            return r5
        L80:
            c92 r12 = new c92
            r12.<init>()
            org.chromium.mojo.system.impl.CoreImpl r13 = (org.chromium.mojo.system.impl.CoreImpl) r13
            r13.getClass()
            int r11 = r11.detachFd()
            long r0 = J.N.MDMdj6cc(r11)
            fj3 r11 = new fj3
            r11.<init>(r13, r0)
            r12.b = r11
            r12.c = r4
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.u4.C(java.lang.String, k00):c92");
    }

    @Override // defpackage.s4
    public final void K(v4.e eVar) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        CoreImpl coreImpl = CoreImpl.a.a;
        this.j.b(new vk(this, coreImpl, jElapsedRealtime, ji0.a(coreImpl), eVar));
    }

    @Override // defpackage.s4
    public final void c0(final String str, final v4.m mVar) {
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        final CoreImpl coreImpl = CoreImpl.a.a;
        final Executor executorA = ji0.a(coreImpl);
        this.j.b(new Runnable() { // from class: t4
            @Override // java.lang.Runnable
            public final void run() {
                c92 c92VarC = this.e.C(str, coreImpl);
                oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, "Android.FontLookup.MatchLocalFontByUniqueName.Time");
                executorA.execute(new y70(mVar, 25, c92VarC));
            }
        });
    }

    @Override // defpackage.s4
    public final void k0(v4.i iVar) {
        HashSet hashSet = this.h;
        String[] strArr = (String[]) hashSet.toArray(new String[hashSet.size()]);
        Arrays.sort(strArr);
        iVar.c(strArr);
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
