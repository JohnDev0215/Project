package spi;

import androidx.lifecycle.LifecycleOwnerKt;
import com.byd.overseaappstore.ui.view.dialog.SettingCleanCacheDialog;
import com.byd.overseaappstore.ui.view.fragment.SettingFragment;
import com.byd.overseaappstore.ui.vm.SettingViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class cp implements SettingCleanCacheDialog.a {
    public final /* synthetic */ SettingFragment a;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$initListener$3$1$cleanCacheButtonClick$1", f = "SettingFragment.kt", i = {}, l = {82, 83}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;
        public final /* synthetic */ SettingFragment b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(SettingFragment settingFragment, Continuation<? super a> continuation) {
            super(2, continuation);
            this.b = settingFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SettingViewModel settingViewModelI = SettingFragment.i(this.b);
                this.a = 1;
                if (settingViewModelI.a(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            SettingViewModel settingViewModelI2 = SettingFragment.i(this.b);
            this.a = 2;
            if (settingViewModelI2.b(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public cp(SettingFragment settingFragment) {
        this.a = settingFragment;
    }

    @Override // com.byd.overseaappstore.ui.view.dialog.SettingCleanCacheDialog.a
    public final void a() {
    }

    @Override // com.byd.overseaappstore.ui.view.dialog.SettingCleanCacheDialog.a
    public final void b() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.a), null, null, new a(this.a, null), 3, null);
    }
}
