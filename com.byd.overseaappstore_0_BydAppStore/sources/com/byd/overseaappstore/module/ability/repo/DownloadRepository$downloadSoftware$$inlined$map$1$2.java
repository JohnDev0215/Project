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
public final class DownloadRepository$downloadSoftware$$inlined$map$1$2<T> implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.DownloadRepository$downloadSoftware$$inlined$map$1$2$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.DownloadRepository$downloadSoftware$$inlined$map$1$2", f = "DownloadRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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
            return DownloadRepository$downloadSoftware$$inlined$map$1$2.this.emit(null, this);
        }
    }

    public DownloadRepository$downloadSoftware$$inlined$map$1$2(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object emit(java.lang.Object r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.repo.DownloadRepository$downloadSoftware$$inlined$map$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.byd.overseaappstore.module.ability.repo.DownloadRepository$downloadSoftware$$inlined$map$1$2$1 r0 = (com.byd.overseaappstore.module.ability.repo.DownloadRepository$downloadSoftware$$inlined$map$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.byd.overseaappstore.module.ability.repo.DownloadRepository$downloadSoftware$$inlined$map$1$2$1 r0 = new com.byd.overseaappstore.module.ability.repo.DownloadRepository$downloadSoftware$$inlined$map$1$2$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L45
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.flow.FlowCollector r6 = r4.a
            spi.mn r5 = (spi.mn) r5
            java.io.InputStream r5 = r5.byteStream()
            r0.label = r3
            java.lang.Object r5 = r6.emit(r5, r0)
            if (r5 != r1) goto L45
            return r1
        L45:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.DownloadRepository$downloadSoftware$$inlined$map$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
