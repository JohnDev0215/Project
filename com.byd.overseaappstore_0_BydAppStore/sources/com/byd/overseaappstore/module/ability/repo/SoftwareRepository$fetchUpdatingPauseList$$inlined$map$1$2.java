package com.byd.overseaappstore.module.ability.repo;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2<T> implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
    public static final class AnonymousClass1 extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2.this.emit(null, this);
        }
    }

    public SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object emit(java.lang.Object r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2.AnonymousClass1
            if (r2 == 0) goto L17
            r2 = r1
            com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2.AnonymousClass1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2$1
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L36
            if (r4 != r5) goto L2e
            kotlin.ResultKt.throwOnFailure(r1)
            goto La4
        L2e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L36:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.flow.FlowCollector r1 = r0.a
            r4 = r21
            com.byd.overseaappstore.module.ability.data.ApiDataBody r4 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r4
            int r6 = r4.getCode()
            r7 = -1
            if (r6 != r7) goto L53
            com.byd.overseaappstore.module.ability.data.SourceDataBody r4 = new com.byd.overseaappstore.module.ability.data.SourceDataBody
            r9 = 0
            r11 = 0
            r12 = 5
            r13 = 0
            java.lang.String r10 = "no data"
            r8 = r4
            r8.<init>(r9, r10, r11, r12, r13)
            goto L65
        L53:
            com.byd.overseaappstore.module.ability.data.SourceDataBody r6 = new com.byd.overseaappstore.module.ability.data.SourceDataBody
            r15 = 0
            r16 = 0
            java.lang.Object r17 = r4.getData()
            r18 = 3
            r19 = 0
            r14 = r6
            r14.<init>(r15, r16, r17, r18, r19)
            r4 = r6
        L65:
            int r6 = r4.getRet()
            java.lang.Object r7 = r4.getApiData()
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L7a
            int r7 = r7.size()
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            goto L7b
        L7a:
            r7 = 0
        L7b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "repo-vm fetchUpdatingPauseList ret = "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r6 = " size = "
            r8.append(r6)
            r8.append(r7)
            java.lang.String r6 = r8.toString()
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]
            spi.ct.a(r6, r7)
            r2.label = r5
            java.lang.Object r1 = r1.emit(r4, r2)
            if (r1 != r3) goto La4
            return r3
        La4:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingPauseList$$inlined$map$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
