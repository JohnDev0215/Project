package coil.intercept;

import p.k51;
import p.kk0;
import p.l31;
import p.oi1;

/* JADX INFO: loaded from: classes.dex */
public final class a implements k51 {
    public final l31 a;
    public final kk0 b;
    public final oi1 c;

    public a(l31 l31Var, kk0 kk0Var) {
        this.a = l31Var;
        this.b = kk0Var;
        this.c = new oi1(l31Var, kk0Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00da -> B:24:0x00e1). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object b(coil.intercept.a r19, p.fe2 r20, p.ew r21, p.q31 r22, java.lang.Object r23, p.br1 r24, p.ii0 r25, p.c00 r26) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.a.b(coil.intercept.a, p.fe2, p.ew, p.q31, java.lang.Object, p.br1, p.ii0, p.c00):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0225 A[PHI: r1 r3
  0x0225: PHI (r1v36 java.lang.Object) = (r1v32 java.lang.Object), (r1v1 java.lang.Object) binds: [B:86:0x0222, B:14:0x0034] A[DONT_GENERATE, DONT_INLINE]
  0x0225: PHI (r3v15 android.graphics.drawable.BitmapDrawable) = (r3v13 android.graphics.drawable.BitmapDrawable), (r3v20 android.graphics.drawable.BitmapDrawable) binds: [B:86:0x0222, B:14:0x0034] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0232  */
    /* JADX WARN: Type inference failed for: r10v0, types: [kotlin.coroutines.intrinsics.CoroutineSingletons] */
    /* JADX WARN: Type inference failed for: r10v3, types: [p.ce0] */
    /* JADX WARN: Type inference failed for: r1v23, types: [T, p.br1] */
    /* JADX WARN: Type inference failed for: r1v7, types: [T, p.ew] */
    /* JADX WARN: Type inference failed for: r2v12, types: [T, p.ew] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object c(coil.intercept.a r27, p.q31 r28, java.lang.Object r29, p.br1 r30, p.ii0 r31, p.c00 r32) {
        /*
            Method dump skipped, instruction units count: 620
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.a.c(coil.intercept.a, p.q31, java.lang.Object, p.br1, p.ii0, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    @Override // p.k51
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(coil.intercept.b r18, p.c00 r19) throws java.lang.Throwable {
        /*
            r17 = this;
            r10 = r17
            r11 = r18
            r0 = r19
            p.oi1 r1 = r10.c
            boolean r2 = r0 instanceof coil.intercept.EngineInterceptor$intercept$1
            if (r2 == 0) goto L1c
            r2 = r0
            coil.intercept.EngineInterceptor$intercept$1 r2 = (coil.intercept.EngineInterceptor$intercept$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L1c
            int r3 = r3 - r4
            r2.label = r3
        L1a:
            r0 = r2
            goto L22
        L1c:
            coil.intercept.EngineInterceptor$intercept$1 r2 = new coil.intercept.EngineInterceptor$intercept$1
            r2.<init>(r10, r0)
            goto L1a
        L22:
            java.lang.Object r2 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r12 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r3 = r0.label
            r13 = 1
            if (r3 == 0) goto L47
            if (r3 != r13) goto L3f
            java.lang.Object r1 = r0.L$1
            p.j51 r1 = (p.j51) r1
            java.lang.Object r0 = r0.L$0
            r3 = r0
            coil.intercept.a r3 = (coil.intercept.a) r3
            kotlin.a.d(r2)     // Catch: java.lang.Throwable -> L3b
            goto La7
        L3b:
            r0 = move-exception
            r11 = r1
            goto La8
        L3f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L47:
            kotlin.a.d(r2)
            p.q31 r3 = r11.d     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r2 = r3.b     // Catch: java.lang.Throwable -> L7d
            p.qd2 r4 = r11.e     // Catch: java.lang.Throwable -> L7d
            android.graphics.Bitmap$Config[] r5 = p.i.a     // Catch: java.lang.Throwable -> L7d
            boolean r5 = r11 instanceof coil.intercept.b     // Catch: java.lang.Throwable -> L7d
            if (r5 == 0) goto L5a
            p.ii0 r5 = r11.f     // Catch: java.lang.Throwable -> L7d
        L58:
            r6 = r5
            goto L5d
        L5a:
            p.gi0 r5 = p.ii0.a     // Catch: java.lang.Throwable -> L7d
            goto L58
        L5d:
            p.kk0 r5 = r10.b     // Catch: java.lang.Throwable -> L7d
            p.br1 r5 = r5.x(r3, r4)     // Catch: java.lang.Throwable -> L7d
            coil.size.Scale r7 = r5.e     // Catch: java.lang.Throwable -> L7d
            r6.getClass()     // Catch: java.lang.Throwable -> L7d
            p.l31 r8 = r10.a     // Catch: java.lang.Throwable -> L7d
            coil.b r8 = (coil.b) r8     // Catch: java.lang.Throwable -> L7d
            p.ew r8 = r8.f     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r8 = r8.a(r2, r5)     // Catch: java.lang.Throwable -> L7d
            p.mi1 r9 = r1.b(r3, r8, r5, r6)     // Catch: java.lang.Throwable -> L7d
            if (r9 == 0) goto L80
            p.ni1 r1 = r1.a(r3, r9, r4, r7)     // Catch: java.lang.Throwable -> L7d
            goto L81
        L7d:
            r0 = move-exception
            r3 = r10
            goto La8
        L80:
            r1 = 0
        L81:
            if (r1 == 0) goto L88
            p.bh2 r0 = p.oi1.c(r11, r3, r9, r1)     // Catch: java.lang.Throwable -> L7d
            return r0
        L88:
            kotlinx.coroutines.b r14 = r3.x     // Catch: java.lang.Throwable -> L7d
            coil.intercept.EngineInterceptor$intercept$2 r15 = new coil.intercept.EngineInterceptor$intercept$2     // Catch: java.lang.Throwable -> L7d
            r16 = 0
            r1 = r15
            r2 = r17
            r4 = r8
            r7 = r9
            r8 = r18
            r9 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L7d
            r0.L$0 = r10     // Catch: java.lang.Throwable -> L7d
            r0.L$1 = r11     // Catch: java.lang.Throwable -> L7d
            r0.label = r13     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r2 = p.vr2.y(r14, r15, r0)     // Catch: java.lang.Throwable -> L7d
            if (r2 != r12) goto La7
            return r12
        La7:
            return r2
        La8:
            boolean r1 = r0 instanceof java.util.concurrent.CancellationException
            if (r1 != 0) goto Lba
            p.kk0 r1 = r3.b
            coil.intercept.b r11 = (coil.intercept.b) r11
            p.q31 r2 = r11.d
            r1.getClass()
            p.kf0 r0 = p.kk0.p(r2, r0)
            return r0
        Lba:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.a.a(coil.intercept.b, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ed A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00e3 -> B:29:0x00e5). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(p.ew r20, p.q31 r21, java.lang.Object r22, p.br1 r23, p.ii0 r24, p.c00 r25) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.a.d(p.ew, p.q31, java.lang.Object, p.br1, p.ii0, p.c00):java.lang.Object");
    }
}
