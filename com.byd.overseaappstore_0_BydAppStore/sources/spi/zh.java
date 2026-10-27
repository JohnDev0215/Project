package spi;

import com.byd.car.locale.ccTLD;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.ui.vm.ManagementViewModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.ManagementViewModel$fetchUpdateAppExist$1", f = "ManagementViewModel.kt", i = {}, l = {66, 109, 109}, m = "invokeSuspend", n = {}, s = {})
public final class zh extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ ManagementViewModel b;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ ManagementViewModel a;

        /* JADX INFO: renamed from: spi.zh$a$a, reason: collision with other inner class name */
        @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.ManagementViewModel$fetchUpdateAppExist$1$1", f = "ManagementViewModel.kt", i = {0, 0, 0, 1, 1, 1, 2}, l = {81, 85, 106}, m = "emit", n = {"this", ccTLD.ITALY, "deviceSoftwareList", "this", ccTLD.ITALY, "deviceSoftwareList", "deviceSoftwareList"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0"})
        public static final class C0146a extends ContinuationImpl {
            public Object a;
            public Object b;
            public Object c;
            public /* synthetic */ Object d;
            public final /* synthetic */ a<T> e;
            public int f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C0146a(a<? super T> aVar, Continuation<? super C0146a> continuation) {
                super(continuation);
                this.e = aVar;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.d = obj;
                this.f |= Integer.MIN_VALUE;
                return this.e.emit(null, this);
            }
        }

        public a(ManagementViewModel managementViewModel) {
            this.a = managementViewModel;
        }

        /* JADX WARN: Removed duplicated region for block: B:44:0x012a  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x013a  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0181  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(@org.jetbrains.annotations.NotNull com.byd.overseaappstore.module.ability.data.SourceDataBody<java.util.List<com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp>> r16, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r17) {
            /*
                Method dump skipped, instruction units count: 422
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.zh.a.emit(com.byd.overseaappstore.module.ability.data.SourceDataBody, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public static final class b<T> implements FlowCollector {
        public final /* synthetic */ ManagementViewModel a;

        public b(ManagementViewModel managementViewModel) {
            this.a = managementViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            this.a.e.getUpdateFetchFinishDispatcher().notifyUpdateFetchFinishDispatch(true);
            ManagementViewModel managementViewModel = this.a;
            managementViewModel.f.setValue(Boxing.boxBoolean(managementViewModel.b.generateUpgradeSoftwareExistUiState((SourceDataBody) obj)));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zh(ManagementViewModel managementViewModel, Continuation<? super zh> continuation) {
        super(2, continuation);
        this.b = managementViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new zh(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((zh) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006a A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.a
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L25
            if (r1 == r4) goto L21
            if (r1 == r3) goto L1d
            if (r1 != r2) goto L15
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6b
        L15:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1d:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L59
        L21:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4c
        L25:
            kotlin.ResultKt.throwOnFailure(r7)
            com.byd.overseaappstore.ui.vm.ManagementViewModel r7 = r6.b
            com.byd.overseaappstore.module.ability.util.notify.alone.sequence.ExecutorSequenceDispatcherMgr r7 = r7.e
            com.byd.overseaappstore.module.ability.util.notify.alone.sequence.ExecutorUpdateFetchFinishDispatcher r7 = r7.getUpdateFetchFinishDispatcher()
            r1 = 0
            r7.notifyUpdateFetchFinishDispatch(r1)
            com.byd.overseaappstore.ui.vm.ManagementViewModel r7 = r6.b
            com.byd.overseaappstore.module.ability.repo.SoftwareRepository r7 = r7.a
            kotlinx.coroutines.flow.Flow r7 = r7.f()
            spi.zh$a r1 = new spi.zh$a
            com.byd.overseaappstore.ui.vm.ManagementViewModel r5 = r6.b
            r1.<init>(r5)
            r6.a = r4
            java.lang.Object r7 = r7.collect(r1, r6)
            if (r7 != r0) goto L4c
            return r0
        L4c:
            com.byd.overseaappstore.ui.vm.ManagementViewModel r7 = r6.b
            com.byd.overseaappstore.module.ability.repo.SoftwareRepository r7 = r7.a
            r6.a = r3
            java.lang.Object r7 = r7.g()
            if (r7 != r0) goto L59
            return r0
        L59:
            kotlinx.coroutines.flow.Flow r7 = (kotlinx.coroutines.flow.Flow) r7
            spi.zh$b r1 = new spi.zh$b
            com.byd.overseaappstore.ui.vm.ManagementViewModel r3 = r6.b
            r1.<init>(r3)
            r6.a = r2
            java.lang.Object r7 = r7.collect(r1, r6)
            if (r7 != r0) goto L6b
            return r0
        L6b:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.zh.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
