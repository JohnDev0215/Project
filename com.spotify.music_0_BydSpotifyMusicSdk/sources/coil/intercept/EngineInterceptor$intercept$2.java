package coil.intercept;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.br1;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.ii0;
import p.j51;
import p.lq2;
import p.mi1;
import p.q31;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "coil.intercept.EngineInterceptor$intercept$2", f = "EngineInterceptor.kt", l = {75}, m = "invokeSuspend")
final class EngineInterceptor$intercept$2 extends SuspendLambda implements fz0 {
    final /* synthetic */ mi1 $cacheKey;
    final /* synthetic */ j51 $chain;
    final /* synthetic */ ii0 $eventListener;
    final /* synthetic */ Object $mappedData;
    final /* synthetic */ br1 $options;
    final /* synthetic */ q31 $request;
    int label;
    final /* synthetic */ a this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EngineInterceptor$intercept$2(a aVar, q31 q31Var, Object obj, br1 br1Var, ii0 ii0Var, mi1 mi1Var, j51 j51Var, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = aVar;
        this.$request = q31Var;
        this.$mappedData = obj;
        this.$options = br1Var;
        this.$eventListener = ii0Var;
        this.$cacheKey = mi1Var;
        this.$chain = j51Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new EngineInterceptor$intercept$2(this.this$0, this.$request, this.$mappedData, this.$options, this.$eventListener, this.$cacheKey, this.$chain, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((EngineInterceptor$intercept$2) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0043  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r15) {
        /*
            r14 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r1 = r14.label
            r2 = 1
            if (r1 == 0) goto L15
            if (r1 != r2) goto Ld
            kotlin.a.d(r15)
            goto L2c
        Ld:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L15:
            kotlin.a.d(r15)
            coil.intercept.a r3 = r14.this$0
            p.q31 r4 = r14.$request
            java.lang.Object r5 = r14.$mappedData
            p.br1 r6 = r14.$options
            p.ii0 r7 = r14.$eventListener
            r14.label = r2
            r8 = r14
            java.lang.Object r15 = coil.intercept.a.c(r3, r4, r5, r6, r7, r8)
            if (r15 != r0) goto L2c
            return r0
        L2c:
            p.ce0 r15 = (p.ce0) r15
            coil.intercept.a r0 = r14.this$0
            p.oi1 r0 = r0.c
            p.mi1 r1 = r14.$cacheKey
            p.q31 r3 = r14.$request
            r0.getClass()
            coil.request.CachePolicy r3 = r3.t
            boolean r3 = r3.b()
            r4 = 0
            r5 = 0
            if (r3 != 0) goto L45
        L43:
            r0 = r4
            goto L99
        L45:
            p.l31 r0 = r0.a
            coil.b r0 = (coil.b) r0
            p.v81 r0 = r0.b
            java.lang.Object r0 = r0.getValue()
            p.b12 r0 = (p.b12) r0
            if (r0 == 0) goto L43
            if (r1 != 0) goto L56
            goto L43
        L56:
            android.graphics.drawable.Drawable r3 = r15.a
            boolean r6 = r3 instanceof android.graphics.drawable.BitmapDrawable
            if (r6 == 0) goto L5f
            android.graphics.drawable.BitmapDrawable r3 = (android.graphics.drawable.BitmapDrawable) r3
            goto L60
        L5f:
            r3 = r5
        L60:
            if (r3 == 0) goto L43
            android.graphics.Bitmap r3 = r3.getBitmap()
            if (r3 != 0) goto L69
            goto L43
        L69:
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
            boolean r7 = r15.b
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            java.lang.String r8 = "coil#is_sampled"
            r6.put(r8, r7)
            java.lang.String r7 = r15.d
            if (r7 == 0) goto L82
            java.lang.String r8 = "coil#disk_cache_key"
            r6.put(r8, r7)
        L82:
            java.util.Map r7 = r1.e
            java.util.Map r7 = p.l13.r(r7)
            p.mi1 r8 = new p.mi1
            java.lang.String r1 = r1.c
            r8.<init>(r1, r7)
            java.util.Map r1 = p.l13.r(r6)
            p.pg2 r0 = r0.a
            r0.d(r8, r3, r1)
            r0 = r2
        L99:
            android.graphics.drawable.Drawable r7 = r15.a
            p.q31 r8 = r14.$request
            coil.decode.DataSource r9 = r15.c
            p.mi1 r1 = r14.$cacheKey
            if (r0 == 0) goto La5
            r10 = r1
            goto La6
        La5:
            r10 = r5
        La6:
            java.lang.String r11 = r15.d
            boolean r12 = r15.b
            p.j51 r15 = r14.$chain
            android.graphics.Bitmap$Config[] r0 = p.i.a
            boolean r0 = r15 instanceof coil.intercept.b
            if (r0 == 0) goto Lba
            coil.intercept.b r15 = (coil.intercept.b) r15
            boolean r15 = r15.g
            if (r15 == 0) goto Lba
            r13 = r2
            goto Lbb
        Lba:
            r13 = r4
        Lbb:
            p.bh2 r15 = new p.bh2
            r6 = r15
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor$intercept$2.i(java.lang.Object):java.lang.Object");
    }
}
