package com.byd.overseaappstore.ui.view.fragment.manage;

import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.vm.UpgradeViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$updateAdapter$1$3$1$1", f = "UpdateListFragment.kt", i = {}, l = {77}, m = "invokeSuspend", n = {}, s = {})
public final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ UpdateListFragment c;
    public final /* synthetic */ Software d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(UpdateListFragment updateListFragment, Software software, Continuation<? super e> continuation) {
        super(2, continuation);
        this.c = updateListFragment;
        this.d = software;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        e eVar = new e(this.c, this.d, continuation);
        eVar.b = obj;
        return eVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.b;
            UpdateListFragment updateListFragment = this.c;
            int i2 = UpdateListFragment.j;
            UpgradeViewModel upgradeViewModelI = updateListFragment.i();
            String packageName = this.d.getPackageName();
            this.a = 1;
            if (upgradeViewModelI.a(packageName, coroutineScope, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
