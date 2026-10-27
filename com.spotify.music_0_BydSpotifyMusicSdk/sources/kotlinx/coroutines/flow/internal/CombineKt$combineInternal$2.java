package kotlinx.coroutines.flow.internal;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.dv0;
import p.e20;
import p.e60;
import p.fv0;
import p.fz0;
import p.hz0;
import p.lq2;
import p.py0;
import p.yq;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", f = "Combine.kt", l = {54, 76, 79}, m = "invokeSuspend")
final class CombineKt$combineInternal$2 extends SuspendLambda implements fz0 {
    final /* synthetic */ py0 $arrayFactory;
    final /* synthetic */ dv0[] $flows;
    final /* synthetic */ fv0 $this_combineInternal;
    final /* synthetic */ hz0 $transform;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1, reason: invalid class name */
    @e60(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1", f = "Combine.kt", l = {31}, m = "invokeSuspend")
    final class AnonymousClass1 extends SuspendLambda implements fz0 {
        final /* synthetic */ dv0[] $flows;
        final /* synthetic */ int $i;
        final /* synthetic */ AtomicInteger $nonClosed;
        final /* synthetic */ yq $resultChannel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(dv0[] dv0VarArr, int i, AtomicInteger atomicInteger, yq yqVar, c00 c00Var) {
            super(2, c00Var);
            this.$flows = dv0VarArr;
            this.$i = i;
            this.$nonClosed = atomicInteger;
            this.$resultChannel = yqVar;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final c00 a(Object obj, c00 c00Var) {
            return new AnonymousClass1(this.$flows, this.$i, this.$nonClosed, this.$resultChannel, c00Var);
        }

        @Override // p.fz0
        public final Object g(Object obj, Object obj2) {
            return ((AnonymousClass1) a((e20) obj, (c00) obj2)).i(lq2.a);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object i(Object obj) {
            AtomicInteger atomicInteger;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
            int i = this.label;
            try {
                if (i == 0) {
                    kotlin.a.d(obj);
                    dv0[] dv0VarArr = this.$flows;
                    int i2 = this.$i;
                    dv0 dv0Var = dv0VarArr[i2];
                    c cVar = new c(this.$resultChannel, i2);
                    this.label = 1;
                    if (dv0Var.c(cVar, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    kotlin.a.d(obj);
                }
                if (atomicInteger.decrementAndGet() == 0) {
                    this.$resultChannel.h(null);
                }
                return lq2.a;
            } finally {
                if (this.$nonClosed.decrementAndGet() == 0) {
                    this.$resultChannel.h(null);
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$combineInternal$2(c00 c00Var, py0 py0Var, hz0 hz0Var, fv0 fv0Var, dv0[] dv0VarArr) {
        super(2, c00Var);
        this.$flows = dv0VarArr;
        this.$arrayFactory = py0Var;
        this.$transform = hz0Var;
        this.$this_combineInternal = fv0Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2(c00Var, this.$arrayFactory, this.$transform, this.$this_combineInternal, this.$flows);
        combineKt$combineInternal$2.L$0 = obj;
        return combineKt$combineInternal$2;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((CombineKt$combineInternal$2) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ef, code lost:
    
        if (r9 != 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f1, code lost:
    
        r13 = (java.lang.Object[]) r12.$arrayFactory.invoke();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f9, code lost:
    
        if (r13 != null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00fb, code lost:
    
        r13 = r12.$transform;
        r14 = r12.$this_combineInternal;
        r12.L$0 = r10;
        r12.L$1 = r11;
        r12.L$2 = r2;
        r12.I$0 = r9;
        r12.I$1 = r6;
        r12.label = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x010f, code lost:
    
        if (r13.e(r14, r10, r12) != r1) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0111, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0112, code lost:
    
        p.rb.l0(r10, r13, 0, 0, 14);
        r14 = r12.$transform;
        r4 = r12.$this_combineInternal;
        r12.L$0 = r10;
        r12.L$1 = r11;
        r12.L$2 = r2;
        r12.I$0 = r9;
        r12.I$1 = r6;
        r12.label = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x012c, code lost:
    
        if (r14.e(r4, r13, r12) != r1) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x012e, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ac, code lost:
    
        if (r9 != 0) goto L19;
     */
    /* JADX WARN: Path cross not found for [B:40:0x00ef, B:34:0x00de], limit reached: 52 */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00cc A[LOOP:0: B:29:0x00cc->B:52:?, LOOP_START, PHI: r9 r13
  0x00cc: PHI (r9v3 int) = (r9v2 int), (r9v4 int) binds: [B:27:0x00c9, B:52:?] A[DONT_GENERATE, DONT_INLINE]
  0x00cc: PHI (r13v5 p.h41) = (r13v4 p.h41), (r13v12 p.h41) binds: [B:27:0x00c9, B:52:?] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x010f -> B:19:0x00ac). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x012c -> B:49:0x012f). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r21) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2.i(java.lang.Object):java.lang.Object");
    }
}
